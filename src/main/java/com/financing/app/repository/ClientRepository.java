package com.financing.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.financing.app.Enum.State;
import com.financing.app.models.Client;
import com.financing.app.models.Loan;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE str(c.id) LIKE %:partialId%")
    List<Client> findByIdContaining(@Param("partialId") String partialId);
    
    List<Client> findByState(State state);
}
