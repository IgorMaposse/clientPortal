package com.financing.app.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.financing.app.models.Loan;



public interface LoanService {
	
	Page<Loan> getAllLoans(Pageable page);
	
	Loan getLoanById(Long id);
	
	void deleteLoanById( Long id,String performedBy);
	
	Loan saveLoanDetails(Loan loan,String performedBy);
	
	Loan updateLoanDetails(Long id, Loan loan,String performedBy);
	
	public List<Loan> LoanList();
	
	List<Loan> getLoanByClientId(Long clientId);
	
	List<Loan> getApprovedLoans();
	List<Loan> getPendingLoans();
	List<Loan> getDeclinedLoans();



	
	List<Loan> loanListByName( String name);
	List<Loan> loanList(String partialId);

	 
	 public List<Loan>getLoansByClientName(String name);

	

}
