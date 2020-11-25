package org.iceflower.study.envers.web.employee.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class EmployeePagedRivisionListQueryCommand {
  @Getter
  private final long employeeNo;

  private final int page;

  private final int pageSize;

  public PageRequest getPageRequest() {
    return PageRequest.of(this.page, this.pageSize);
  }
}
