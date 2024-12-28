package com.financing.app.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.financing.app.Enum.State;
import com.financing.app.Enum.ClientRisk;
import com.financing.app.Enum.ContractType;

import com.financing.app.Enum.DocumentType;
import com.financing.app.Enum.Gender;
import com.financing.app.Enum.Marital_status;
import com.financing.app.Enum.Qualifications;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Client{


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	@JsonIgnore // To avoid circular references during JSON serialization
	private List<Loan> loans;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="company_id", nullable = false)
	private Company company;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", nullable = false)
	private User user;

	private String name;

	private Date birthDate;

	private String id_Number;

	private String nationality;

	private Date issueDate;

	private Date validationDate;

	private String mother_name;

	private String naturality;

	private String father_name;

	private String address;

	private String city;

	private String nuit;
	
	 private String documentName; // To store the file name
	 private String documentTypeToUpload; // Optional: File type (e.g., PDF, JPEG)
	 private String documentPath; // To store the file path or URL if stored externally
	
	@Enumerated(EnumType.STRING)
	private State state;

	@Enumerated (EnumType.STRING)
	private Marital_status marital_status;

	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;
	
	@Enumerated(EnumType.STRING)
	private ClientRisk classification;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String formattedId;


	private String pep;

	private  String profession;

	private String employer;

	private BigDecimal salary;

	private String contact;

	private String email;

	private String contactPersonName;

	private String contactPersonPhone;
	
	private String inputter;
	
	private String authorizer;
	
	private String documentPlaceOfIssuance;
	
	private String resident;
	
	private String houseNumber;
	
	private String floor;
	
	private String country;
	
	private String province;
	
	private String contact2;
	
	private String alternativeContact;
	
	private String employerContact;
	
	private String employerFisicalAdreess;
	
	private String politic;
	
	private String politicPosition;
	
	private String familyPolitic;
	
	private String businessPolitic;
	
	private String 	businessRelation;
	
	private String clientAuthorizationName;
	
	private Date dateAuthorizationClient;
	
	private String  clientSignature;
	
	private String managerSignature;
	
	private String managerName;
	
	private Date dateAuthorizationManager;
	
	
	@Enumerated(EnumType.STRING)
	private Qualifications qualifications;
	
	@Enumerated(EnumType.STRING)
	private ContractType contractType;
	

	private String degreeOfKinship;
	
	@Column(name="created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp created_at;
	
	@Column(name="updated_at", nullable = false, updatable = false)
	@UpdateTimestamp
	private Timestamp updated_at;
	
	
	public String getDocumentPlaceOfIssuance() {
		return documentPlaceOfIssuance;
	}


	public void setDocumentPlaceOfIssuance(String documentPlaceOfIssuance) {
		this.documentPlaceOfIssuance = documentPlaceOfIssuance;
	}


	public String getResident() {
		return resident;
	}


	public void setResident(String resident) {
		this.resident = resident;
	}


	public String getHouseNumber() {
		return houseNumber;
	}


	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}


	public String getFloor() {
		return floor;
	}


	public void setFloor(String floor) {
		this.floor = floor;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getContact2() {
		return contact2;
	}


	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}


	public String getAlternativeContact() {
		return alternativeContact;
	}


	public void setAlternativeContact(String alternativeContact) {
		this.alternativeContact = alternativeContact;
	}


	public String getEmployerContact() {
		return employerContact;
	}


	public void setEmployerContact(String employerContact) {
		this.employerContact = employerContact;
	}


	public String getEmployerFisicalAdreess() {
		return employerFisicalAdreess;
	}


	public void setEmployerFisicalAdreess(String employerFisicalAdreess) {
		this.employerFisicalAdreess = employerFisicalAdreess;
	}


	public String getPolitic() {
		return politic;
	}


	public void setPolitic(String politic) {
		this.politic = politic;
	}


	public String getPoliticPosition() {
		return politicPosition;
	}


	public void setPoliticPosition(String politicPosition) {
		this.politicPosition = politicPosition;
	}


	public String getFamilyPolitic() {
		return familyPolitic;
	}


	public void setFamilyPolitic(String familyPolitic) {
		this.familyPolitic = familyPolitic;
	}


	public String getBusinessPolitic() {
		return businessPolitic;
	}


	public void setBusinessPolitic(String businessPolitic) {
		this.businessPolitic = businessPolitic;
	}


	public String getBusinessRelation() {
		return businessRelation;
	}


	public void setBusinessRelation(String businessRelation) {
		this.businessRelation = businessRelation;
	}


	public String getClientAuthorizationName() {
		return clientAuthorizationName;
	}


	public void setClientAuthorizationName(String clientAuthorizationName) {
		this.clientAuthorizationName = clientAuthorizationName;
	}


	public Date getDateAuthorizationClient() {
		return dateAuthorizationClient;
	}


	public void setDateAuthorizationClient(Date dateAuthorizationClient) {
		this.dateAuthorizationClient = dateAuthorizationClient;
	}


	public String getClientSignature() {
		return clientSignature;
	}


	public void setClientSignature(String clientSignature) {
		this.clientSignature = clientSignature;
	}


	public String getManagerSignature() {
		return managerSignature;
	}


	public void setManagerSignature(String managerSignature) {
		this.managerSignature = managerSignature;
	}


	public String getManagerName() {
		return managerName;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}


	public Date getDateAuthorizationManager() {
		return dateAuthorizationManager;
	}


	public void setDateAuthorizationManager(Date dateAuthorizationManager) {
		this.dateAuthorizationManager = dateAuthorizationManager;
	}


	public Qualifications getQualifications() {
		return qualifications;
	}


	public void setQualifications(Qualifications qualifications) {
		this.qualifications = qualifications;
	}


	public ContractType getContractType() {
		return contractType;
	}


	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}




	public String getDegreeOfKinship() {
		return degreeOfKinship;
	}


	public void setDegreeOfKinship(String degreeOfKinship) {
		this.degreeOfKinship = degreeOfKinship;
	}


	public String getDocumentName() {
		return documentName;
	}


	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


	public String getDocumentTypeToUpload() {
		return documentTypeToUpload;
	}


	public void setDocumentTypeToUpload(String documentTypeToUpload) {
		this.documentTypeToUpload = documentTypeToUpload;
	}


	public String getDocumentPath() {
		return documentPath;
	}


	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}


	public String getFormattedId() {
		return formattedId;
	}

	
	public void setFormattedId(String formattedId) {
		this.formattedId = formattedId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}
	

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public ClientRisk getClassification() {
		return classification;
	}

	public void setClassification(ClientRisk classification) {
		this.classification = classification;
	}
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getInputter() {
		return inputter;
	}

	public void setInputter(String inputter) {
		this.inputter = inputter;
	}
	public String getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getId_Number() {
		return id_Number;
	}

	public void setId_Number(String id_Number) {
		this.id_Number = id_Number;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate)	 {
		this.validationDate = validationDate;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	public String getNaturality() {
		return naturality;
	}

	public void setNaturality(String naturality) {
		this.naturality = naturality;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNuit() {
		return nuit;
	}

	public void setNuit(String nuit) {
		this.nuit = nuit;
	}

	public Marital_status getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(Marital_status marital_status) {
		this.marital_status = marital_status;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPep() {
		return pep;
	}

	public void setPep(String pep) {
		this.pep = pep;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", loans=" + loans + ", company=" + company + ", user=" + user + ", name=" + name
				+ ", birthDate=" + birthDate + ", id_Number=" + id_Number + ", nationality=" + nationality
				+ ", issueDate=" + issueDate + ", validationDate=" + validationDate + ", mother_name=" + mother_name
				+ ", naturality=" + naturality + ", father_name=" + father_name + ", address=" + address + ", city="
				+ city + ", nuit=" + nuit + ", documentName=" + documentName + ", documentTypeToUpload="
				+ documentTypeToUpload + ", documentPath=" + documentPath + ", state=" + state + ", marital_status="
				+ marital_status + ", gender=" + gender + ", documentType=" + documentType + ", classification="
				+ classification + ", formattedId=" + formattedId + ", pep=" + pep + ", profession=" + profession
				+ ", employer=" + employer + ", salary=" + salary + ", contact=" + contact + ", email=" + email
				+ ", contactPersonName=" + contactPersonName + ", contactPersonPhone=" + contactPersonPhone
				+ ", inputter=" + inputter + ", authorizer=" + authorizer + ", documentPlaceOfIssuance="
				+ documentPlaceOfIssuance + ", resident=" + resident + ", houseNumber=" + houseNumber + ", floor="
				+ floor + ", country=" + country + ", province=" + province + ", contact2=" + contact2
				+ ", alternativeContact=" + alternativeContact + ", employerContact=" + employerContact
				+ ", employerFisicalAdreess=" + employerFisicalAdreess + ", politic=" + politic + ", politicPosition="
				+ politicPosition + ", familyPolitic=" + familyPolitic + ", businessPolitic=" + businessPolitic
				+ ", businessRelation=" + businessRelation + ", clientAuthorizationName=" + clientAuthorizationName
				+ ", dateAuthorizationClient=" + dateAuthorizationClient + ", clientSignature=" + clientSignature
				+ ", managerSignature=" + managerSignature + ", managerName=" + managerName
				+ ", dateAuthorizationManager=" + dateAuthorizationManager + ", qualifications=" + qualifications
				+ ", contractType=" + contractType + ", degreeOfKinship=" + degreeOfKinship + ", created_at="
				+ created_at + ", updated_at=" + updated_at + "]";
	}



	








}
