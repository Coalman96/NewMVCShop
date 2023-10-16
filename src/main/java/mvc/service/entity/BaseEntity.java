package mvc.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

  @CreatedDate
  @Column(updatable = false)
  public LocalDate regDate;

  // Protected 접근자를 가진 getter 추가
  public LocalDate getRegDate() {
    return this.regDate;
  }

  @PrePersist
  public void onPrePersist() {
    this.regDate = LocalDate.now();
  }
}