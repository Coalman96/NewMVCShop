package mvc.service.repository;


import mvc.service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer>,
    QuerydslPredicateExecutor<ProductEntity> {

}