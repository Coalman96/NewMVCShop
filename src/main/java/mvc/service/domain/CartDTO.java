package mvc.service.domain;


import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {

  private Long id;
  private int count;
  private LocalDate createDate;
  private UserDTO user;
  private List<CartItemDTO> cartItems;


}
