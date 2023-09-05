package com.dnb.jdbcdemo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.service.AccountServiceImpl;

public class jdcbapplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String x = "Sravya";
		String y = null;
		Optional<String> opt = Optional.ofNullable("a");
		System.out.println(opt.isPresent());
		java.sql.Date date = Date.valueOf(LocalDate.now());*/
		Account acc = new Account();
		acc.setAccountHolderName("Sravya");
		acc.setAccountId("12");
		acc.setBalance(1800000);
		acc.setAccountType("savings");
	    acc.setAddress("hyderabad");
	    acc.setContactNumber("8688695214");
        LocalDate ld = LocalDate.of(2023, 4, 3);
        acc.setDob(ld);
        AccountServiceImpl sim = new AccountServiceImpl();
       // System.out.println(sim.createAccount(acc));
        while(true)
        {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your case");
        int cases= sc.nextInt();
        switch(cases) 
        {
        	case 1: sim.createAccount(acc);
        		break;
        	case 2: Optional<Account> acc1 = sim.getAccountById("12");
        		break;
        	case 3: System.exit(0);	
        	default: System.out.println("give correct value");
        	 break;
        	
        }
		
	
	}
	}
}
