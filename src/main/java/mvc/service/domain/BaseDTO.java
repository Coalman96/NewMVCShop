package mvc.service.domain;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseDTO {
  public LocalDate regDate;

  public void setRegDate(LocalDate regDate) {
    this.regDate = regDate;
  }
}
