package org.iceflower.study.envers.web.employee.meta.statement;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.iceflower.study.envers.Gender;

@Getter
@AllArgsConstructor
@Builder
public class EmployeeStatement {

  private final long employeeNo;
  private final String accountId;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final Gender gender;
  private final AuditStatement auditStatement;
}
