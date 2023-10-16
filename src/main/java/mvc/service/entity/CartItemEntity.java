package mvc.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "cart_item")
@Entity
public class CartItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name="cart_id", referencedColumnName = "cart_id")
  private CartEntity cart;

  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name="prod_no", referencedColumnName = "prod_no")
  private ProductEntity product;

  private int count; // 상품 개수

  public static CartItemEntity createCartItem(CartEntity cart, ProductEntity product, int amount) {
    CartItemEntity cartItem = new CartItemEntity();
    cartItem.setCart(cart);
    cartItem.setProduct(product);
    cartItem.setCount(amount);
    return cartItem;
  }

  // 이미 담겨있는 물건 또 담을 경우 수량 증가
  public void addCount(int count) {
    this.count += count;
  }

}
