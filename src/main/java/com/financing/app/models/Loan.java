package com.financing.app.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.financing.app.Enum.ClientRisk;
import com.financing.app.Enum.DocumentType;
import com.financing.app.Enum.LoanType;
import com.financing.app.Enum.State;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Loan {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="client_id", nullable = false)
	private Client client;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	private BigDecimal stampFee;
	private BigDecimal amount;
	
	private BigDecimal effortRate;
	
	private BigDecimal stampDuty;
	
	private String insurance;
	
	private BigDecimal liquidAmount;
	private int installment;
	
	private String liquidAmountInWords;
	
	private String purposeOfCredit;
	
	private BigDecimal interestRate;

	private Date paymentDate;
	
	private BigDecimal delayInterestRate;
	
	private Long nib;
	

	private String reference;
	
	private String comment;
	
	private String salePersonSignature;
	private String clientAuthorizationName;
	
	private Date dateAuthorizationClient;
	
	private String  clientSignature;
	
	private String managerSignature;
	
	private String managerName;
	
	private Date dateAuthorizationManager;
	
	@Enumerated(EnumType.STRING)
	private State state;
	
	@Enumerated(EnumType.STRING)
	private LoanType loanType;
	
	private String authorizer;
	
	private String inputter;
	@Column(name="created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp created_at;
	
	@Column(name="updated_at", nullable = false, updatable = false)
	@UpdateTimestamp
	private Timestamp updated_at;
	
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

	public User getUser() {
		return user;
	}

	public String getLiquidAmountInWords() {
		return liquidAmountInWords;
	}

	public void setLiquidAmountInWords(String liquidAmountInWords) {
		this.liquidAmountInWords = liquidAmountInWords;
	}

	public String getPurposeOfCredit() {
		return purposeOfCredit;
	}

	public void setPurposeOfCredit(String purposeOfCredit) {
		this.purposeOfCredit = purposeOfCredit;
	}

	public String getSalePersonSignature() {
		return salePersonSignature;
	}

	public void setSalePersonSignature(String salePersonSignature) {
		this.salePersonSignature = salePersonSignature;
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

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getStampFee() {
		return stampFee;
	}

	public void setStampFee(BigDecimal stampFee) {
		this.stampFee = stampFee;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
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
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getEffortRate() {
		return effortRate;
	}

	public void setEffortRate(BigDecimal effortRate) {
		this.effortRate = effortRate;
	}

	public BigDecimal getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(BigDecimal stampDuty) {
		this.stampDuty = stampDuty;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public BigDecimal getLiquidAmount() {
		return liquidAmount;
	}

	public void setLiquidAmount(BigDecimal liquidAmount) {
		this.liquidAmount = liquidAmount;
	}

	public int getInstallment() {
		return installment;
	}

	public void setInstallment(int installment) {
		this.installment = installment;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getDelayInterestRate() {
		return delayInterestRate;
	}

	public void setDelayInterestRate(BigDecimal delayInterestRate) {
		this.delayInterestRate = delayInterestRate;
	}

	public Long getNib() {
		return nib;
	}

	public void setNib(Long nib) {
		this.nib = nib;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", client=" + client + ", user=" + user + ", stampFee=" + stampFee + ", amount="
				+ amount + ", effortRate=" + effortRate + ", stampDuty=" + stampDuty + ", insurance=" + insurance
				+ ", liquidAmount=" + liquidAmount + ", installment=" + installment + ", liquidAmountInWords="
				+ liquidAmountInWords + ", purposeOfCredit=" + purposeOfCredit + ", interestRate=" + interestRate
				+ ", paymentDate=" + paymentDate + ", delayInterestRate=" + delayInterestRate + ", nib=" + nib
				+ ", reference=" + reference + ", comment=" + comment + ", salePersonSignature=" + salePersonSignature
				+ ", clientAuthorizationName=" + clientAuthorizationName + ", dateAuthorizationClient="
				+ dateAuthorizationClient + ", clientSignature=" + clientSignature + ", managerSignature="
				+ managerSignature + ", managerName=" + managerName + ", dateAuthorizationManager="
				+ dateAuthorizationManager + ", state=" + state + ", loanType=" + loanType + ", authorizer="
				+ authorizer + ", inputter=" + inputter + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ "]";
	}


//



}
