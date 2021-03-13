package com.cap.exs.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cap.exs.entities.Employee;
import com.cap.exs.entities.Expense;
import com.cap.exs.entities.ExpenseClaim;
import com.cap.exs.entities.Project;


public interface IExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer> {
	
	public List<ExpenseClaim> findByEmployee(Employee employee);
	
	public List<ExpenseClaim> findByProject(Project project);
	
	public List<ExpenseClaim> findByExpense(Expense expense);
	
	@Query("select ec from ExpenseClaim ec where ec.startDate >= :startDate AND ec.endDate <= :endDate")
    public List<ExpenseClaim> findAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
