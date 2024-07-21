package com.eventosfull.fullapi.repository;

import com.eventosfull.fullapi.model.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
    
}