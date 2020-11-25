package org.iceflower.study.envers.web.employee.command;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.iceflower.study.envers.Gender;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class EmployeeSaveCommand {
  @NotNull
  private final String email;
  @NotNull
  private final String firstName;
  private final String middleName;
  @NotNull
  private final String lastName;
  private final Gender gender;
  @NotNull
  private final String requestedBy;
}
