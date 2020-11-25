package org.iceflower.study.envers.employee.contact;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class EmailAddress extends Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(targetEntity = ContactDescription.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "contract_id")
  private ContactDescription contactDescription;

  @Column
  private String address;

  @Builder
  public EmailAddress(
      ContactGroup contactGroup,
      boolean isDefault,
      long sort,
      String description,
      String address,
      String requestedBy) {
    super(contactGroup, isDefault, sort, description, requestedBy);
    this.address = address;
  }

  public void setAddress(String address, String requestedBy) {
    this.address = address;
    super.setLastModifiedBy(requestedBy);
  }
}
