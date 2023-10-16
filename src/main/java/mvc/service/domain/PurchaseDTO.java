package mvc.service.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseDTO {

  private int tranNo;
  private UserDTO buyer;
  private String divyAddr;
  private String divyDate;
  private String divyRequest;
  private LocalDate orderDate;
  private String paymentOption;
  private ProductDTO purchaseProd;
  private String receiverName;
  private String receiverPhone;
  private String tranCode;
  private int prodCount;

  // 생성자, getter, setter 등은 필요에 따라 구현합니다.
}
