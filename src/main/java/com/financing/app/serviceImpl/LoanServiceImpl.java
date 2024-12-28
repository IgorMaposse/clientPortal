package com.financing.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.financing.app.Enum.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.financing.app.exceptions.ResourceNotFoundException;
import com.financing.app.models.Client;

import com.financing.app.models.Loan;
import com.financing.app.repository.ClientRepository;

import com.financing.app.repository.LoanRepository;

import com.financing.app.service.LoanService;
import com.financing.app.service.UserService;
@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private LoanRepository loanRepository; 

	private Loan existingLoan;
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private ActionLogService actionLogService;
	 
	@Autowired
	private ClientRepository clientRepository;
	

	
	//@Autowired
	//private UserService userService;
	@Override
	public List<Loan> LoanList() {
		return loanRepository.findAll();
	}
	
	@Override
	public List<Loan> loanList(String partialId){
		return loanRepository.findByIdContaining(partialId);
	}
	

	@Override
	public List<Loan> loanListByName(String name){
		return loanRepository.findByNameContaining(name);
	}

	@Override
	public List<Loan> getLoanByClientId(Long clientId) {
		//  buscar empréstimos pelo ID do cliente
		return loanRepository.findByClientId(clientId);
	}
	
	@Override
	public List<Loan> getApprovedLoans() {
		return loanRepository.findByState(State.APPROVED);
	}
	@Override
	public List<Loan> getPendingLoans() {
		return loanRepository.findByState(State.PENDING);
	}

	
	
	
	@Override
	public Page<Loan> getAllLoans(Pageable page) {
		// TODO Auto-generated method stub

		return loanRepository.findAll(page);
	}

	@Override
	public Loan getLoanById(Long id) {
		// TODO Auto-generated method stub
		Optional<Loan> loan =loanRepository.findById(id);
		if(loan.isPresent()) {
			
			return loan.get();
		}  
		throw new ResourceNotFoundException("Loan is not founf for id"+id);
	}

	@Override
	public void deleteLoanById(Long id,String performedBy) {
		// TODO Auto-generated method stub
		Loan loan=getLoanById(id);
		loanRepository.delete(loan);
		actionLogService.logAction("Loan", loan.getId(), "DELETE", "Cliente: " + loan.getClient().getName(), performedBy,loan.getInputter(),loan.getAuthorizer(),loan.getState());
	}

	@Override
	public Loan saveLoanDetails(Loan loan, String performedBy) {
	    if (loan.getClient() == null || loan.getClient().getId() == null) {
	        throw new IllegalArgumentException("Client information is missing or incomplete.");
	    }

	    // Fetch the client from the database
	    Client client = clientRepository.findById(loan.getClient().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Client with ID " + loan.getClient().getId() + " not found"));

	    // Attach the client to the loan
	    loan.setClient(client);
	    Loan savedLoan=loanRepository.save(loan);
		actionLogService.logAction("Loan", savedLoan.getId(), "CREATE", "Cliente: " + savedLoan.getClient().getName(), performedBy,savedLoan.getInputter(),savedLoan.getAuthorizer(),savedLoan.getState());
	    // Save the loan
	    return savedLoan;
	}

	@Override
	public Loan updateLoanDetails(Long id, Loan loan, String performedBy) {
		
		
		existingLoan =getLoanById(id);
		System.out.println("Existente:"+existingLoan.getId()+""+existingLoan.getState());
		System.out.println("Actualicao:"+loan.getId()+""+loan.getState());
		System.out.println("id que entra:"+id);

		
		existingLoan.setAmount(loan.getAmount());
		existingLoan.setComment(loan.getComment());
		existingLoan.setDelayInterestRate(loan.getDelayInterestRate());
		existingLoan.setEffortRate(loan.getEffortRate());
		existingLoan.setInstallment(loan.getInstallment());
		existingLoan.setInsurance(loan.getInsurance());
		existingLoan.setLiquidAmount(loan.getLiquidAmount());
		existingLoan.setNib(loan.getNib());
		existingLoan.setPaymentDate(loan.getPaymentDate());
		existingLoan.setReference(loan.getReference());
		existingLoan.setStampDuty(loan.getStampDuty());
		existingLoan.setStampFee(loan.getStampFee());
		existingLoan.setState(loan.getState());
		existingLoan.setClient(loan.getClient());
		existingLoan.setAuthorizer(loan.getAuthorizer());
		existingLoan.setInputter(loan.getInputter());
		existingLoan.setInterestRate(loan.getInterestRate());

		Loan savedLoan =loanRepository.save(existingLoan);
	    // Clone the loan only if its state is APPROVED
	/*    if (State.APPROVED.equals(loan.getState())) {
	        cloneLoan(existingLoan.getId());
	    }*/
		actionLogService.logAction("Loan", savedLoan.getId(), "UPDATE", "Cliente: " + savedLoan.getClient().getName(), performedBy,savedLoan.getInputter(),savedLoan.getAuthorizer(),savedLoan.getState());
		ResponseEntity.ok("Loan updated successfully");
		return existingLoan;
	}

	@Override
	public List<Loan> getLoansByClientName(String name) {
	    return loanRepository.findLoansByClientName(name);
	}
/*
	@Override
	public Loan findByName(String name) {
		// TODO Auto-generated method stub
		 return loanRepository.findByName(name)
		            .orElseThrow(() -> new ResourceNotFoundException("Loan not found for name: " + name));
	}
*/
	
/*
	    public Disburse cloneLoan(Long loanId) {
	        // Fetch the source Loan
	        Loan loan = loanRepository.findById(loanId)
	            .orElseThrow(() -> new IllegalArgumentException("Loan not found with ID: " + loanId));

	        // Create a new LoanClone object and copy attributes
	        Disburse loanClone = new Disburse();
	        loanClone.setId(loan.getId()); // Copy the same ID
	        
	        loanClone.setClient(loan.getClient());
	        loanClone.setPayments(loan.getPayments());
	        loanClone.setUser(loan.getUser());
	        loanClone.setStampFee(loan.getStampFee());
	        loanClone.setAmount(loan.getAmount());
	        loanClone.setEffortRate(loan.getEffortRate());
	        loanClone.setStampDuty(loan.getStampDuty());
	        loanClone.setInsurance(loan.getInsurance());
	        loanClone.setLiquidAmount(loan.getLiquidAmount());
	        loanClone.setInstallment(loan.getInstallment());
	        loanClone.setInterestRate(loan.getInterestRate());
	        loanClone.setPaymentDate(loan.getPaymentDate());
	        loanClone.setDelayInterestRate(loan.getDelayInterestRate());
	        loanClone.setNib(loan.getNib());
	        loanClone.setReference(loan.getReference());
	        loanClone.setComment(loan.getComment());
	        loanClone.setState(loan.getState());
	        loanClone.setAuthorizer(loan.getAuthorizer());
	        loanClone.setInputter(loan.getInputter());
	        loanClone.setCreated_at(loan.getCreated_at());
	        loanClone.setUpdated_at(loan.getUpdated_at());

	        // Persist the cloned entity
	        return loanCloneRepository.save(loanClone);
	    }*/


		@Override
		public List<Loan> getDeclinedLoans() {
			// TODO Auto-generated method stub
			return loanRepository.findByState(State.DECLINED);
		}
}
