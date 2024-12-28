package com.financing.app.repository;

import java.util.List;

import com.financing.app.Enum.State;
import com.financing.app.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.financing.app.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	 @Query("SELECT c FROM Company c WHERE str(c.id) LIKE %:partialId%")
	    List<Company> findByIdContaining(@Param("partialId") String partialId);
		List<Company> findByState(State state);

}
