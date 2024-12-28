package com.financing.app.serviceImpl;

import com.financing.app.Enum.State;
import com.financing.app.models.Company;
import com.financing.app.repository.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financing.app.service.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{



	@Autowired
	private CompanyRepository companyRepository;

	private Company existingCompany;
	
	 @Autowired
	 private ActionLogService actionLogService;

	@Override
	public Company save(Company company, String performedBy) {
		// TODO Auto-generated method stub
		//client.setState(State.PENDING);
		 Company savedCompany =companyRepository.save(company);
		actionLogService.logAction("Entidade", savedCompany.getId(), "CREATE", "Entidade: " + savedCompany.getName(),performedBy,savedCompany.getInputter(),savedCompany.getAuthorizer(),savedCompany.getState() );
		
		return company;
	}

	
	@Override
	public List<Company> companyList(String partialId) {
		// Implementação para retornar clientes com base no partialId
		return companyRepository.findByIdContaining(partialId);
	}
	@Override
	public void delete(long id, String performedBy) {
		// TODO Auto-generated method stub
		Company company= companyRepository.findById(id).orElseThrow();
	//	client.setState(State.PENDING);
		actionLogService.logAction("Entidade", company.getId(), "DELETE", "Entidade: " + company.getName(),performedBy,company.getInputter(),company.getAuthorizer(),company.getState() );
		companyRepository.deleteById(id);
    }
	@Override
	public  Company select( long id){
		Company company= companyRepository.findById(id).orElseThrow();
		//client.setState(State.PENDING);
		return companyRepository.getById(id);
	}

	@Override
	public List<Company> companyList() {
		
		return companyRepository.findAll();
	}
	@Override
	public List<Company> getApprovedCompany(){
		return companyRepository.findByState(State.APPROVED);
	}
	@Override
	public List<Company> getPendingCompany(){
		return companyRepository.findByState(State.PENDING);
	}
	@Override
	public Company update(Company company, long id,String performedBy) {
		// TODO Auto-generated metghod stub
	
		//client.setState(State.PENDING);
		existingCompany= companyRepository.findById(id).orElse(null);
		existingCompany.setName(company.getName());
		existingCompany.setNuit(company.getNuit());
		existingCompany.setSector(company.getSector());
		existingCompany.setCertificate(company.getCertificate());
		existingCompany.setContact(company.getContact());
		existingCompany.setState(company.getState());
		existingCompany.setAuthorizer(company.getAuthorizer());
		existingCompany.setInputter(company.getInputter());
		Company savedCompany= companyRepository.save(existingCompany);

		actionLogService.logAction("Entidade", savedCompany.getId(), "UPDATE", "Entidade: " + savedCompany.getName(),performedBy,savedCompany.getInputter(),savedCompany.getAuthorizer(),savedCompany.getState() );
		return savedCompany;

	}

}
