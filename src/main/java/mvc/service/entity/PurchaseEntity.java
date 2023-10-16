package mvc.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "transaction")
@Builder
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {

  ///Field
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int tranNo;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity buyer;

  private String divyAddr;

  private String divyDate;

  private String divyRequest;

  private LocalDate orderDate;

  private String paymentOption;

  @ManyToOne
  @JoinColumn(name = "prod_no")
  private ProductEntity purchaseProd;

  private String receiverName;

  private String receiverPhone;

  private String tranCode;

  private int prodCount;

}