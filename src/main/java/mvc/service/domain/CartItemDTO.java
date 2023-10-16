package mvc.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDTO {

  private String id;
  private CartDTO cart;
  private ProductDTO product;
  private int count;

  public static CartItemDTO createCartItemDTO(CartDTO cartDTO, ProductDTO productDTO, int amount) {
    CartItemDTO cartItemDTO = new CartItemDTO();
    cartItemDTO.setCart(cartDTO);
    cartItemDTO.setProduct(productDTO);
    cartItemDTO.setCount(amount);
    return cartItemDTO;
  }

  // 이미 담겨있는 물건 또 담을 경우 수량 증가
  public void addCount(int count) {
    this.count += count;
  }

}
