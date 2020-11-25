package org.iceflower.study.envers.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMetaRepository
    extends JpaRepository<EmployeeMeta, Long>,
    RevisionRepository<EmployeeMeta, Long, Integer>, JpaSpecificationExecutor<EmployeeMeta> {
  boolean existsByAccountId(String accountId);

}
