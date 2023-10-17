package mvc.web.cart;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mvc.service.cart.CartService;
import mvc.service.domain.CartItemDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.domain.UserDTO;
import mvc.service.product.ProductService;
import mvc.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartRestController {

  private final CartService cartService;
  private final UserService userService;
  private final ProductService productService;

  @GetMapping("/json/cart/{userId}/{prodNo}/{amount}")
  public String addCartItem(@PathVariable("userId") String userId, @PathVariable("prodNo") Integer prodNo, @PathVariable("amount")Integer amount,
      HttpSession session)
      throws Exception {

    log.info("addCartItem에서 받은 인자는{},{},{}",userId,prodNo,amount);

    UserDTO user = userService.getUser(userId);
    ProductDTO item = productService.getProduct(prodNo);

    log.info("addCartItem에서 조회한 user는{}",user);
    log.info("addCartItem에서 조회한 item은{}",item);

    cartService.addCart(user, item, amount);


    return "앙 기모띠";
  }

  @GetMapping("/json/cart/{userId}")
  public List<CartItemDTO> userCartView(@PathVariable("userId") String userId) throws Exception{

    log.info("userCartView에서 받은 인자는{}",userId);

    UserDTO user = userService.getUser(userId);

    return cartService.userCartView(user);
  }

  @GetMapping("/json/cartDelete/{userId}/{itemId}")
  public List<CartItemDTO> deleteCartItem(@PathVariable("userId") String userId, @PathVariable("itemId") Long itemId) throws Exception{

    log.info("deleteCartItem에서 받은 인자는{},{}",userId,itemId);

    return cartService.cartDelete(userId,itemId);

  }

}
