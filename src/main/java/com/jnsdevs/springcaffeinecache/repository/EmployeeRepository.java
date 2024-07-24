package com.jnsdevs.springcaffeinecache.repository;

import com.jnsdevs.springcaffeinecache.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Autor Jairo Nascimento
 * @Created 24/07/2024 - 13:47
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
