package com.financing.app.service;

import com.financing.app.models.Company;

import java.util.List;

public interface CompanyService {

	
	public Company save(Company company,String performedBy);
	public  void delete(long id,String performedBy);

	List<Company> getApprovedCompany();
	List<Company> getPendingCompany();

	public  Company update(Company company, long id, String performedBy);
	public  Company select(long id);
	public List<Company> companyList();
	List<Company> companyList(String partialId);

}
