package mvc.service.mapper;

import mvc.service.domain.CartDTO;
import mvc.service.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
  @Mapping(target = "user", ignore = true)
  CartEntity cartDtoToEntity(CartDTO dto);
  @Mapping(target = "user", ignore = true)
  CartDTO cartEntityToDto(CartEntity entity);
}
