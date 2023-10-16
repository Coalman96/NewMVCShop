package mvc.service.domain;

import java.time.LocalDate;

public class BaseDTO {
  private LocalDate regDate;

  public LocalDate getRegDate() {
    return this.regDate;
  }

  public void setRegDate(LocalDate regDate) {
    this.regDate = regDate;
  }
}
