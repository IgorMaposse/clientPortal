package com.financing.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financing.app.models.ActionLog;
import com.financing.app.repository.ActionLogRepository;

@RestController
public class ActionLogController {

	    @Autowired
	    private ActionLogRepository actionLogRepository;
	    
	    
	@RequestMapping("/api/logs")
	public List<ActionLog> getAllLogs() {
		return actionLogRepository.findAll();
	}
	    
	@GetMapping("/{id}")
	public ResponseEntity<ActionLog> getLogById(@PathVariable Long id) {
		return actionLogRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(null));
	}
	@GetMapping("/api/logs/{entityId}/{entityName}")
	public ResponseEntity<List<ActionLog>> getLogByEntityIDName(
			@PathVariable Long entityId,
			@PathVariable String entityName) {
		List<ActionLog> logs = actionLogRepository.findByEntityIdAndEntityName(entityId, entityName);
		if (logs.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(logs);
	}
}