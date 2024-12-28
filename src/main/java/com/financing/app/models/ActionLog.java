package com.financing.app.models;

import com.financing.app.Enum.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ActionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String entityName; // Ex.: "Client" ou "Loan"

	private Long entityId; // ID da entidade associada

	private String action; // Ex.: "CREATE", "UPDATE", "DELETE"

	private String details; // Detalhes da ação

	private String performedBy; // Usuário que realizou a ação

	@Column(length = 1000)
	private String inputterComment;

	@Column(length = 1000)
	private String authorizerComment;
	
	


	@Enumerated(EnumType.STRING) // Salva o nome do enum no banco (ex.: "PENDING")
	private State estado;
	private Date timestamp; // Data/hora da ação

	public Long getId() {
		return id;
	}

	public String getInputterComment() {
		return inputterComment;
	}

	public void setInputterComment(String inputterComment) {
		this.inputterComment = inputterComment;
	}
	public String getAuthorizerComment() {
		return this.authorizerComment;
	}

	public void setAuthorizerComment(String authorizerComment) {
		this.authorizerComment = authorizerComment;
	}
	public State getEstado() {
		return estado;
	}

	public void setEstado(State estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "ActionLog{" +
				"id=" + id +
				", entityName='" + entityName + '\'' +
				", entityId=" + entityId +
				", action='" + action + '\'' +
				", details='" + details + '\'' +
				", performedBy='" + performedBy + '\'' +
				", inputterComment='" + inputterComment + '\'' +
				", authorizerComment='" + authorizerComment + '\'' +
				", estado=" + estado +
				", timestamp=" + timestamp +
				'}';
	}
}
