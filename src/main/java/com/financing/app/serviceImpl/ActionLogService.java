package com.financing.app.serviceImpl;


import com.financing.app.Enum.State;
import com.financing.app.models.ActionLog;
import com.financing.app.repository.ActionLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActionLogService {

    @Autowired
    private ActionLogRepository actionLogRepository;

    public void logAction(String entityName, Long entityId, String action, String details, String performedBy, String inputterComment, String authorizerComment, State state) {
        ActionLog log = new ActionLog();
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setDetails(details);
        log.setPerformedBy(performedBy);
        log.setInputterComment(inputterComment);
        log.setAuthorizerComment(authorizerComment);
        log.setTimestamp(new Date());
        log.setEstado(state);
        actionLogRepository.save(log);
    }
}
