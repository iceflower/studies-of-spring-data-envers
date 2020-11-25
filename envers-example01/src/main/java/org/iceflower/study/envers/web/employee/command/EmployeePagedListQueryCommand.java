package org.iceflower.study.envers.web.employee.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.iceflower.study.envers.Gender;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class EmployeePagedListQueryCommand {
  @Getter
  private final Long employeeNo;
  @Getter
  private final String email;
  @Getter
  private final String firstName;
  @Getter
  private final String middleName;
  @Getter
  private final String lastName;
  @Getter
  private final Gender gender;

  private final int page;

  private final int pageSize;

  public PageRequest getPageRequest() {
    return PageRequest.of(this.page, this.pageSize);
  }
}
