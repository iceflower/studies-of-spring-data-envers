package org.iceflower.study.envers.web.employee.meta.command;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.iceflower.study.envers.Gender;

@Getter
@ToString
public class EmployeeUpdateCommand {
  private final long employeeNo;
  private final String accountId;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final Gender gender;
  private final String requestedBy;

  @Builder
  public EmployeeUpdateCommand(
      @NotNull Long employeeNo,
      @NotNull String accountId,
      String firstName,
      String middleName,
      String lastName,
      Gender gender,
      @NotNull String requestedBy) {
    this.employeeNo = employeeNo;
    this.accountId = accountId;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.requestedBy = requestedBy;
  }
}
