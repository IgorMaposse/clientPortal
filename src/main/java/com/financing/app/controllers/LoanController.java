package com.financing.app.controllers;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.financing.app.Enum.State;
import com.financing.app.models.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.financing.app.models.Loan;

import com.financing.app.service.LoanService;




@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;

	
	
	@GetMapping("/loans")
	public List<Loan> getAllLoans(Pageable page) {

		return loanService.getAllLoans(page).toList();	
	}
	
	

	@GetMapping("/loan")
	public List<Loan> loanList() {
		List<Loan> loans = loanService.LoanList();

		loans.forEach(loan -> {
			Client client = loan.getClient();
			if (client != null) {
				String formattedId = String.format("%09d", client.getId());
				client.setFormattedId(formattedId);
			}
		});

		return loans;
	}


	@GetMapping("/loans/{id}") //usando path variable para passar atribudo da url
	public Loan getLoanById(@PathVariable("id") Long id) {
		Loan loan = loanService.getLoanById(id);
		//Payment payment = paymentService.getPaymentById();


		//int days = ChronoUnit.DAYS.between(loan.getPaymentDate(), loan.getPaymentDate());
		return loanService.getLoanById(id);
	}
	@GetMapping("/loan/search/{partialId}")
	public List<Loan> loanList(@PathVariable String partialId) {
		return loanService.loanList(partialId);  // Passar o 'id' como argumento
	}
	
	@GetMapping("/loan/searchName/{name}")
	public List<Loan> loanListByName(@PathVariable  String name) {
		List<Loan> loansApproved = new ArrayList<>(); 
		System.out.println(name);
		List<Loan> loans =loanService.loanListByName(name);
		System.out.println(loans);
		for (Loan loan : loans) {
			if (loan.getState().equals(State.APPROVED)){
				
				loansApproved.add(loan);
				System.out.println(loansApproved);
			}
		}
		return loans ;  // Passar o 'id' como argumento
	}
	
	  @GetMapping("/loan/clientName")
	  public ResponseEntity<List<Loan>> getLoansByClientName(@RequestParam(required = false, defaultValue = "") String name) {
	        List<Loan> loans = loanService.getLoansByClientName(name);
	        
	        return ResponseEntity.ok(loans);
	    }
	
	@GetMapping("/loans/client/{clientId}")
	public List<Loan> getLoansByClientId(@PathVariable("clientId") String formattedClientId) {
		try {
			String numericId = formattedClientId.replace(" ", "").trim();
			Long clientId = Long.parseLong(numericId);

			return loanService.getLoanByClientId(clientId);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid formatted ID: " + formattedClientId);
		}
	}
	@GetMapping("/loans/pending")
	public List<Loan> getPendingLoans() {
		List<Loan> pendingLoans = loanService.getPendingLoans();

		pendingLoans.forEach(loan -> {
			Client client = loan.getClient();
			if (client != null) {
				String formattedId = String.format("%09d", client.getId());
				client.setFormattedId(formattedId);
			}
		});

		return pendingLoans;
	}


	@GetMapping("/loans/declined")
	public List<Loan> getDeclinedLoans() {
		return loanService.getDeclinedLoans();
	}
	
	
	
	@GetMapping("/loans/approved")
	public List<Loan> getApprovedLoans() {
		List<Loan> approvedLoans = loanService.getApprovedLoans();

		approvedLoans.forEach(loan -> {
			Client client = loan.getClient();
			if (client != null) {
				String formattedId =  String.format("%09d", client.getId());
				client.setFormattedId(formattedId);
			}
		});

		return approvedLoans;
	}


	@DeleteMapping("/loans/{id}") // usando query para para passar atributo da url
	public void deleteLoanById(@PathVariable("id") Long id, String performedBy) {
		
		loanService.deleteLoanById(id, performedBy);
		
	}
	
	@GetMapping("/loans/payment/{id}")
	public List<Loan> getLoanToPay(@PathVariable("id") Long id) {
		
		return loanService.getApprovedLoans();
	}
/*	@PostMapping("/loans")
	public void saveLoanDetails( @RequestBody Loan loan) {
		
		loanService.saveLoanDetails(loan);
	}*/
	
	@PostMapping("/loans")
	public ResponseEntity<?> saveLoanDetails(@RequestBody Loan loan, String performedBy) {
	    try {
	        Loan savedLoan = loanService.saveLoanDetails(loan,performedBy);
	        return ResponseEntity.ok(savedLoan);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body("An error occurred while creating the loan.");
	    }
	}
	
	@PutMapping("/loans/{id}")
		public Loan updateLoanDetails(@RequestBody Loan loan, @PathVariable("id") Long id ,String performedBy) {
		
		return loanService.updateLoanDetails(id, loan,performedBy);
	}

		private Long extractNumericId(String formattedId, String prefix) {
		try {
			return Long.parseLong(formattedId.replace(prefix, "").trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid ID format: " + formattedId);
		}
	}
/*	@PostMapping("/{id}/clone")
    public ResponseEntity<?> cloneLoan(@PathVariable Long id) {
        try {
            Disburse clonedLoan = loanService.cloneLoan(id);
            return ResponseEntity.ok(clonedLoan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while cloning the loan.");
        }
    }*/


}
