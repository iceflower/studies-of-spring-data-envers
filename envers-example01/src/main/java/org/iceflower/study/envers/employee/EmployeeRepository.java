package org.iceflower.study.envers.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
    extends JpaRepository<Employee, Long>,
    RevisionRepository<Employee, Long, Integer>, JpaSpecificationExecutor<Employee> {
  boolean existsByEmail(String email);

}
