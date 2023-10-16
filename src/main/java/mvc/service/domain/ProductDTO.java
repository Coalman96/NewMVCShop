package mvc.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO{

  private int prodNo;
  private String fileName;
  private String manuDate;
  private int price;
  private String prodDetail;
  private String prodName;
//  private LocalDate regDate;
  private int prodCount;

}
