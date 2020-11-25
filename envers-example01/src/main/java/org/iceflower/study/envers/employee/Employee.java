package org.iceflower.study.envers.employee;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.iceflower.study.envers.Audit;
import org.iceflower.study.envers.Gender;
import org.iceflower.study.envers.web.employee.statement.AuditStatement;
import org.iceflower.study.envers.web.employee.statement.EmployeeStatement;

@Entity
@ToString
@DynamicUpdate
@Audited // spring-data-envers에 의해 이력이 관리됨
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  @Getter
  private String email;

  @Column
  @Getter
  private String firstName;

  @Column
  @Getter
  private String middleName;

  @Column
  @Getter
  private String lastName;

  @Column
  @Enumerated(EnumType.STRING)
  @Getter
  private Gender gender;

  @Embedded
  private Audit audit;

  @Builder
  public Employee(
      String email,
      String firstName,
      String middleName,
      String lastName,
      Gender gender,
      String requestedBy) {

    this.email = email;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = (gender == null) ? Gender.DO_NOT_WANT_REVEAL_GENDER : gender;
    this.audit = Audit.ofNewInsert(requestedBy);
  }

  public void setEmail(String email, String requestedBy) {
    this.email = email;
    this.audit.setLastModifiedBy(requestedBy);
  }

  public void setFirstName(String firstName, String requestedBy) {
    this.firstName = firstName;
    this.audit.setLastModifiedBy(requestedBy);
  }

  public void setMiddleName(String middleName, String requestedBy) {
    this.middleName = middleName;
    this.audit.setLastModifiedBy(requestedBy);
  }

  public void setLastName(String lastName, String requestedBy) {
    this.lastName = lastName;
    this.audit.setLastModifiedBy(requestedBy);
  }

  public void setGender(Gender gender, String requestedBy) {
    this.gender = gender;
    this.audit.setLastModifiedBy(requestedBy);
  }

  public EmployeeStatement toStatement() {
    var auditStatement = (this.audit == null)
        ? null
        : AuditStatement.builder()
            .lastModifiedAt(this.audit.getLastModifiedAt())
            .lastModifiedBy(this.audit.getLastModifiedBy())
            .build();

    return EmployeeStatement.builder()
        .employeeNo(this.id)
        .email(this.email)
        .firstName(this.firstName)
        .middleName(this.middleName)
        .lastName(this.lastName)
        .gender(this.gender)
        .auditStatement(auditStatement)
        .build();
  }
}
