package mvc.service.cart.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mvc.service.cart.CartService;
import mvc.service.domain.CartItemDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.domain.UserDTO;
import mvc.service.entity.CartEntity;
import mvc.service.entity.CartItemEntity;
import mvc.service.entity.ProductEntity;
import mvc.service.entity.UserEntity;
import mvc.service.mapper.CartItemMapper;
import mvc.service.mapper.CartMapper;
import mvc.service.repository.CartItemRepository;
import mvc.service.repository.CartRepository;
import mvc.service.repository.ProductRepository;
import mvc.service.repository.UserRepository;
import mvc.service.user.impl.UserServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service("cartServiceImpl")
@ToString
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

  ///Field
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final UserServiceImpl userService;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;
  CartMapper cartMapper = Mappers.getMapper(CartMapper.class);
  CartItemMapper cartItemMapper = Mappers.getMapper(CartItemMapper.class);
  // 장바구니 담기
  @Transactional
  public void addCart(UserDTO user, ProductDTO newItem, Integer amount) {

    log.info("addCart에서 받은 인자는{},{},{}",user,newItem,amount);

    // DTO를 엔티티로 변환
    UserEntity userEntity = userService.dtoToEntity(user);
    if(user.getCart() == null){

      CartEntity cartEntity = CartEntity.createCart(userEntity);
      cartRepository.save(cartEntity);
      user.setCart(cartMapper.cartEntityToDto(cartEntity));  // Cart ID 설정

      // UserEntity 업데이트
      UserEntity updatedUserEntity = userRepository.findById(userEntity.getUserId()).orElse(null);
      if (updatedUserEntity != null) {
        updatedUserEntity.setCart(cartEntity);
        userRepository.save(updatedUserEntity);
      }

    }

    // 유저 id로 해당 유저의 장바구니 찾기
     Optional<CartEntity> cartOptional = cartRepository.findById(user.getCart().getId());

    // 장바구니가 존재하지 않는다면
//    CartEntity cart = cartOptional.orElseGet(() -> CartEntity.createCart(userEntity));

    Optional<ProductEntity> item = productRepository.findById(newItem.getProdNo());
    Optional<CartItemEntity> cartItem = cartItemRepository.findById(cartOptional.get().getId());

    // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가
    if (cartItem.isEmpty()) {
      CartItemEntity newCartItem = CartItemEntity.createCartItem(cartOptional.get(), item.get(), amount);
      cartItemRepository.save(newCartItem);
    }

    // 상품이 장바구니에 이미 존재한다면 수량만 증가
    else {
      Optional<CartItemEntity> optionalCartItemEntity = cartItem;
      CartItemEntity update = optionalCartItemEntity.get();
      update.setCart(cartItem.get().getCart());
      update.setProduct(cartItem.get().getProduct());
      update.addCount(amount);
      update.setCount(update.getCount());
      cartItemRepository.save(update);
    }

    // 카트 상품 총 개수 증가
    cartOptional.get().setCount(cartOptional.get().getCount()+amount);

  }

  public List<CartItemDTO> userCartView(UserDTO user) {

    log.info("userCartView에서 받은 user는{}",user);

//    CartEntity dbCart = cartMapper.cartDtoToEntity(user);

    Optional<List<CartItemDTO>> cartItems = cartItemRepository.findByCartId(user.getCart().getId())
        .map(cartItemEntities -> cartItemEntities.stream()
            .map(cartItemEntity -> cartItemMapper.cartItemEntityToDto(cartItemEntity))
            .collect(Collectors.toList())
        );

    log.info("userCartView에서 조회한 cartItems는 {}",cartItems);
//     해당 카트에 속한 카트 상품들을 필터링하여 리스트로 반환
    return cartItems.get();
  }

  @Transactional
  public List<CartItemDTO> cartDelete(String userId, Long cartItemId) throws Exception {
    // 해당 유저의 카트 아이템들 찾기
    List<CartItemEntity> cartItems = cartItemRepository.findAllByCartUserUserId(userId);

    log.info("찾은 cart는 {}", cartItems);

    for (CartItemEntity cartItem : cartItems) {
      if (cartItem.getId().equals(cartItemId)) {
        CartEntity cart = cartItem.getCart();

        // 카트 아이템 삭제
        cartItemRepository.deleteById(cartItemId);

        // 카트 아이템 수량만큼 카트의 총 상품 개수 감소
        cart.setCount(cart.getCount() - cartItem.getCount());
      }
    }

    log.info("cartDelete의 결과는 {}",userCartView(userService.getUser(userId)));
    return userCartView(userService.getUser(userId));
  }
}
