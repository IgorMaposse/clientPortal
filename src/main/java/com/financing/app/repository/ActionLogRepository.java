package com.financing.app.repository;



import com.financing.app.models.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {
    List<ActionLog> findByEntityIdAndEntityName(Long entityId, String entityName);

}