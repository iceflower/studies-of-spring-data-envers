package org.iceflower.study.envers.web.employee.command;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.iceflower.study.envers.Gender;

@Getter
@ToString
public class EmployeeUpdateCommand {
  private final long employeeNo;
  private final String email;
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final Gender gender;
  private final String requestedBy;

  @Builder
  public EmployeeUpdateCommand(
      @NotNull Long employeeNo,
      @NotNull String email,
      String firstName,
      String middleName,
      String lastName,
      Gender gender,
      @NotNull String requestedBy) {
    this.employeeNo = employeeNo;
    this.email = email;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = gender;
    this.requestedBy = requestedBy;
  }
}
