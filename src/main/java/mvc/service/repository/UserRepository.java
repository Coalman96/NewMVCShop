package mvc.service.repository;

import java.util.Optional;
import mvc.service.entity.Role;
import mvc.service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<UserEntity, String>,
    QuerydslPredicateExecutor<UserEntity> {

  Optional<UserEntity> findByEmail(String email);

  UserEntity findByRole(Role role);

}