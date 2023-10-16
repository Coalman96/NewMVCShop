package mvc.service.cart;

import java.util.List;
import mvc.service.domain.CartItemDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.domain.UserDTO;

public interface CartService {

  public void addCart(UserDTO user, ProductDTO product, Integer amount);

  public List<CartItemDTO> userCartView(UserDTO user);

  public void cartDelete(String userId, Long itemId);
}
