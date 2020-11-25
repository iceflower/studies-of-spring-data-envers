package org.iceflower.study.envers.employee.contact;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.iceflower.study.envers.employee.EmployeeMeta;

@Entity
@Getter
@DynamicUpdate
public class ContactDescription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(targetEntity = EmployeeMeta.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private EmployeeMeta employeeMeta;

  @OneToMany
  private List<TelephoneNumber> telephoneNumber;

  @OneToMany
  private List<EmailAddress> emailAddress;

}
