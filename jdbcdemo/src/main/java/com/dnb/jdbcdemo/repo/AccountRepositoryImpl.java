package com.dnb.jdbcdemo.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.utils.DButils;

public class AccountRepositoryImpl implements AccountRepository {
	private  AccountRepositoryImpl() {
		// TODO Auto-generated constructor stub
	}
	private static AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		Optional<Connection> connection = DButils.getConnection();
		String createAccountStatement = "insert into account "+ "(accountId, accountHolderName, accountType, balance, contactNumber, address, accountCreateDate, dob)"+ " values(?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        if(connection.isPresent()) {
        	try
        	{
        		    preparedStatement.setString(1, account.getAccountId());
        	        preparedStatement.setString(2, account.getAccountHolderName());
        	        preparedStatement.setString(3, account.getAccountType());
        	        preparedStatement.setFloat(4, account.getBalance());
        	        preparedStatement.setString(5, account.getContactNumber());
        	        preparedStatement.setString(6, account.getAddress());
        	        preparedStatement.setDate(7, Date.valueOf(account.getAccountCreateDate()));
        	        preparedStatement.setDate(8, Date.valueOf(account.getDob()));
        	        preparedStatement.setBoolean(9, account.isAccountStatus());
        	        int result = preparedStatement.executeUpdate();
        	        if(result>0)
        	        {
        	        	return account;
        	        }
        	}catch (SQLException e)
        	{
        		e.printStackTrace();
        	}
        
        finally
        {
        	if(connection.isPresent()) {
        		DButils.closeConnection(connection.get());
        	}
        }
        }else
        {
        	return null;
        }
        return account;
       

	}
	


	public static AccountRepository getInstance()
	{
		synchronized (AccountRepositoryImpl.class)
		{
			if(accountRepository == null)
			{
				accountRepository = new AccountRepositoryImpl();
			}
		}
		return accountRepository;
	}

	@Override
	public Optional<Account> getAccountById(String accountId) {
		// TODO Auto-generated method stub
		Optional<Connection> connection = DButils.getConnection();
		PreparedStatement preparedStatement= null;
		ResultSet resultset = null;
		String query= " select * from account where accountId=?";
		List<Account>  liac = new ArrayList<Account>();
		if(connection.isPresent())
		{
			try {
				preparedStatement = connection.get().prepareStatement(query);
				preparedStatement.setString(1, accountId);
				/*preparedStatement.setString(2, account.getAccountHolderName());
				preparedStatement.setString(3, account.getAccountType());
				preparedStatement.setFloat(4, account.getBalance());
				preparedStatement.setString(5, account.getContactNumber());
				preparedStatement.setString(6, account.getAddress());
				preparedStatement.setDate(7, Date.valueOf(account.getAccountCreatedDate()));
				preparedStatement.setDate(8, Date.valueOf(account.getDob()));
				preparedStatement.setBoolean(9, account.isAccountStatus());*/
				resultset = preparedStatement.executeQuery();
				if(resultset.next())
				{
					Account account = new Account();
					account.setAccountId(resultset.getString("accountId"));
					account.setDob(resultset.getDate("dob").toLocalDate());
					account.setAccountHolderName(resultset.getString("accountholdername"));
					account.setBalance(resultset.getFloat("balance"));
					account.setAccountStatus(resultset.getBoolean("accountstatus"));
					account.setAddress(resultset.getString("address"));
					account.setContactNumber(resultset.getString("contactnumber"));
					account.setAccountCreateDate(resultset.getDate("accountcreatedate").toLocalDate());
					liac.add(account);
					return Optional.ofNullable(account); 
				}
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally {
                if(connection.isPresent()) {
                    DButils.closeConnection(connection.get());
                }
            }
		}
		return null;
		
	}

}
