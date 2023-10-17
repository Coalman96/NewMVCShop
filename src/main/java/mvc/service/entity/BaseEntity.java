package mvc.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Setter
abstract class BaseEntity {

  @CreatedDate
  @Column(updatable = false)
  public LocalDate regDate;

  @PrePersist
  public void onPrePersist() {
    this.regDate = LocalDate.now();
  }
}