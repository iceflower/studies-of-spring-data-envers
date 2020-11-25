package org.iceflower.study.envers.web.employee.meta.statement;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Builder
@ToString
@Getter
public class AuditStatement {
  private final LocalDateTime lastModifiedAt;
  private final String lastModifiedBy;
}
