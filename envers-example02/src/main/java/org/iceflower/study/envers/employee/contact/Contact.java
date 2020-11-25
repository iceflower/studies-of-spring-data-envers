package org.iceflower.study.envers.employee.contact;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.iceflower.study.envers.Audit;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
abstract class Contact {

  @Enumerated(EnumType.STRING)
  private ContactGroup contactGroup;

  @Column
  private boolean isDefault;

  @Column
  private long sort;

  @Column
  private String description;

  @Embedded
  private Audit audit;

  public Contact(
      ContactGroup contactGroup,
      boolean isDefault,
      long sort,
      String description,
      String requestedBy) {

    this.contactGroup = contactGroup;
    this.isDefault = isDefault;
    this.sort = sort;
    this.description = description;
    this.audit = Audit.ofNewInsert(requestedBy);
  }

  public void setContactGroup(ContactGroup contactGroup) {
    this.contactGroup = contactGroup;
  }

  public void setDefault(boolean isDefault) {
    isDefault = isDefault;
  }

  public void setSort(long sort) {
    this.sort = sort;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  protected void setLastModifiedBy(String requestedBy) {
    this.audit.setLastModifiedBy(requestedBy);
  }
}
