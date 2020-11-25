package org.iceflower.study.envers.employee.contact;

import javax.persistence.Column;
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
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Audited // spring-data-envers에 의해 이력이 관리됨
public class TelephoneNumber extends Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  @Enumerated(EnumType.STRING)
  private TelephoneNumberType numberType;

  @Column
  private String number;

  @Builder
  public TelephoneNumber(
      ContactGroup contactGroup,
      boolean isDefault,
      long sort,
      TelephoneNumberType numberType,
      String number,
      String description,
      String requestedBy) {
    super(contactGroup, isDefault, sort, description, requestedBy);
    this.numberType = numberType;
    this.number = number;
  }

  public void setNumberType(TelephoneNumberType numberType, String requestedBy) {
    this.numberType = numberType;
    super.setLastModifiedBy(requestedBy);
  }

  public void setNumber(String number, String requestedBy) {
    this.number = number;
    super.setLastModifiedBy(requestedBy);
  }

  @Override
  public String toString() {
    return "TelephoneNumber{" +
        "numberType=" + numberType +
        ", number='" + number + '\'' +
        "} " + super.toString();
  }
}
