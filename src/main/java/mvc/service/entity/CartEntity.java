package mvc.service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cart")
@Setter
@Getter
@NoArgsConstructor
public class CartEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;  // 고유한 ID로 사용될 장바구니 식별자

  @OneToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private UserEntity user; // UserEntity와 조인하여 유저별 장바구니 관리

  private int count; // 카트에 담긴 총 상품 개수

  @OneToMany(cascade = CascadeType.ALL)
  private List<CartItemEntity> cartItems  = new ArrayList<>();

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate createDate; // 날짜

  @PrePersist
  public void createDate(){
    this.createDate = LocalDate.now();
  }

  public static CartEntity createCart(UserEntity userEntity) {
    CartEntity cart = new CartEntity();
    cart.setCount(0);
    cart.setUser(userEntity);
    return cart;
  }

}
