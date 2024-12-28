package com.financing.app.repository;

import com.financing.app.Enum.State;
import com.financing.app.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.financing.app.models.Loan;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

    List<Loan> findByClientId(Long clientId);
    
    List<Loan> findByState(State state);
    
    @Query("SELECT l FROM Loan l WHERE l.client.name LIKE %:name%")
    List<Loan> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT l FROM Loan l WHERE str(l.id) LIKE %:partialId%")
    List<Loan> findByIdContaining(@Param("partialId") String partialId);
    
    @Query("SELECT l FROM Loan l WHERE l.client.name LIKE %:name%")
    List<Loan> findLoansByClientName(@Param("name") String name);
  /*  
    @Query("SELECT l FROM Loan l WHERE str(l.id) LIKE %:partialId% or l.client.name LIKE %:name%")
    List<Loan> findByIdAndNameContaining(@Param("partialId") String partialId, @Param("name") String name);*/
 /*
    @Query("SELECT l FROM Loan l WHERE l.name LIKE %:name%")
    List<Loan> findByNameContaining(@Param("name") String name);
    /*
    @Query("SELECT l FROM Loan l WHERE l.name = :name")
    Optional<Loan> findByName(@Param("name") String name);
  */  

}
