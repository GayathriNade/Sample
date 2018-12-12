package com.capgemini.service;

import java.util.ArrayList;

import org.mockito.asm.tree.IntInsnNode;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	@Override
	public Account depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException
	{
		Integer acc=0;
		ArrayList<Integer> accountNumberlst= new ArrayList<Integer>();
		accountNumberlst.add(101);
		accountNumberlst.add(102);
		accountNumberlst.add(103);
		for (Integer accnum : accountNumberlst) {
			acc=accnum;
			if(accountNumber!=acc){
				throw new InvalidAccountNumberException();
			}
			break;
		}
		if(accountNumber==acc){
			throw new InvalidAccountNumberException();
		}
		Account account = new Account();
     account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
		return account;
		
	}
	@Override
	public Account withdrawAmount(int accountNumber,int amount) throws InsufficientBalanceException, InvalidAccountNumberException
	{
		int initialamt=0;
		if(!accountRepository.searchAccount(accountNumber).equals(accountNumber)){
			throw new InvalidAccountNumberException();
		}
		Account account = new Account();
	     account.setAccountNumber(accountNumber);
			
	     initialamt=account.getAmount();
		if(initialamt<amount){
			throw new InsufficientBalanceException();
		}else if(initialamt>amount){
			initialamt=initialamt-amount;
			account.setAmount(amount);
			 
			if(accountRepository.save(account))
			{
				return account;
			}
			if(initialamt<500){
				throw new InsufficientBalanceException();
			}
		}
		return account;
		
	}
	
}
