package org.iceflower.study.envers;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Embeddable
@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Audit {

  @Column
  private LocalDateTime firstCreatedAt;

  @Column
  private String firstCreatedBy;

  @Column
  @LastModifiedDate
  private LocalDateTime lastModifiedAt;

  @Column
  @LastModifiedBy
  private String lastModifiedBy;

  /**
   *
   * @param updatedBy
   * @return
   */
  public static Audit ofNewInsert(String updatedBy) {
    var now = LocalDateTime.now();
    return new Audit(now, updatedBy, now, updatedBy);
  }

  public void setLastModifiedBy(String updatedBy) {
    var now = LocalDateTime.now();
    this.lastModifiedAt = now;
    this.lastModifiedBy = updatedBy;
  }
}
