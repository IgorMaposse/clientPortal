package com.financing.app.controllers;

import com.financing.app.models.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.financing.app.service.CompanyService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;


	@PostMapping("/company")
	public ResponseEntity<Company> save( @Valid @RequestBody Company company, String performedBy) {

		return new ResponseEntity<Company>(companyService.save(company, performedBy),HttpStatus.CREATED);
	}
	
	@RequestMapping(path="company/{id}", method=RequestMethod.DELETE)
	public void  delete (@PathVariable long id,String performedBy){
		if(companyService.select(id)!=null) {
			companyService.delete(id, performedBy);
		}
	}

	@GetMapping("/company")
	public List<Company> ClientList(){
		return companyService.companyList();
	}
	@GetMapping("/company/{id}")
	public List<Company> ClientList(@PathVariable String id) {
		return companyService.companyList(id);  // Passar o 'id' como argumento
	}
	@PutMapping(value = "company/{id}")
	public Company update(@RequestBody Company company,@PathVariable  long id, String performedBy){

	return  companyService.update(company,id, performedBy);
	}
	@GetMapping("/company/approved")
	public List<Company> getAllApprovedCompany(){
		return companyService.getApprovedCompany();
	}
	@GetMapping("/company/pending")
	public List<Company> getAllPendingCompany(){
		return companyService.getPendingCompany();
	}

}
