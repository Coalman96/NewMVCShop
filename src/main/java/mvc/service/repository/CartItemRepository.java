package mvc.service.repository;

import java.util.List;
import java.util.Optional;
import mvc.service.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Long> {

  Optional<List<CartItemEntity>> findByCartId(Long cart_id);
  List<CartItemEntity> findAllByCartUserUserId(String userId);
}
