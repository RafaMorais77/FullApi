package com.eventosfull.fullapi.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import com.eventosfull.fullapi.model.Log;

@Service
@Slf4j
public class LoggerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void log(String operation, String details) {
        try {
            Log logEntity = new Log(); // Renomeando o objeto para evitar ambiguidade
            logEntity.setTimestamp(new java.util.Date());
            logEntity.setOperation(operation);
            logEntity.setDetails(details);
            entityManager.persist(logEntity);
            log.info("Log registrado com sucesso: " + logEntity);
        } catch (Exception e) {
            log.error("Erro ao registrar log: " + e.getMessage());
        }
    }
}
