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
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

@Entity
@DynamicUpdate
@Audited // spring-data-envers에 의해 이력이 관리됨
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(targetEntity = ContactDescription.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "contract_id")
  private ContactDescription contactDescription;

  @Column
  private String zipcode;

  @Column
  private String detailedDescriptionOfDistrict;

  @Column
  private String detailedDescriptionOfBuilding;

  @Column
  private String building;

  @Column
  private String avenue;

  @Column
  private String city;

  @Column
  private String providence;

  @Column
  private String country;

  @Builder
  public Address(
      ContactGroup contactGroup,
      boolean isDefault,
      long sort,
      ContactDescription contactDescription,
      String zipcode,
      String detailedDescriptionOfDistrict,
      String detailedDescriptionOfBuilding,
      String building,
      String avenue,
      String city,
      String providence,
      String country,
      String description,
      String requestedBy) {
    super(contactGroup, isDefault, sort, description, requestedBy);
    this.contactDescription = contactDescription;
    this.zipcode = zipcode;
    this.detailedDescriptionOfDistrict = detailedDescriptionOfDistrict;
    this.detailedDescriptionOfBuilding = detailedDescriptionOfBuilding;
    this.building = building;
    this.avenue = avenue;
    this.city = city;
    this.providence = providence;
    this.country = country;
  }

  public void setZipcode(String zipcode, String requestedBy) {
    this.zipcode = zipcode;
    super.setLastModifiedBy(requestedBy);
  }

  public void setDetailedDescriptionOfDistrict(String detailedDescriptionOfDistrict,
      String requestedBy) {
    this.detailedDescriptionOfDistrict = detailedDescriptionOfDistrict;
    super.setLastModifiedBy(requestedBy);
  }

  public void setDetailedDescriptionOfBuilding(String detailedDescriptionOfBuilding,
      String requestedBy) {
    this.detailedDescriptionOfBuilding = detailedDescriptionOfBuilding;
    super.setLastModifiedBy(requestedBy);
  }

  public void setBuilding(String building, String requestedBy) {
    this.building = building;
    super.setLastModifiedBy(requestedBy);
  }

  public void setAvenue(String avenue, String requestedBy) {
    this.avenue = avenue;
    super.setLastModifiedBy(requestedBy);
  }

  public void setCity(String city, String requestedBy) {
    this.city = city;
    super.setLastModifiedBy(requestedBy);
  }

  public void setProvidence(String providence, String requestedBy) {
    this.providence = providence;
    super.setLastModifiedBy(requestedBy);
  }

  public void setCountry(String country, String requestedBy) {
    this.country = country;
    super.setLastModifiedBy(requestedBy);
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", contactDescription=" + contactDescription +
        ", zipcode='" + zipcode + '\'' +
        ", detailedDescriptionOfDistrict='" + detailedDescriptionOfDistrict + '\'' +
        ", detailedDescriptionOfBuilding='" + detailedDescriptionOfBuilding + '\'' +
        ", building='" + building + '\'' +
        ", avenue='" + avenue + '\'' +
        ", city='" + city + '\'' +
        ", providence='" + providence + '\'' +
        ", country='" + country + '\'' +
        "} " + super.toString();
  }
}
