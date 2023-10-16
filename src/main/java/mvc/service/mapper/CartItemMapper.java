package mvc.service.mapper;

import mvc.service.domain.CartItemDTO;
import mvc.service.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartItemMapper {
  @Mappings({
      @Mapping(target = "cart", ignore = true),
      @Mapping(target = "productEntity.regDate", ignore = true)
  })
  CartItemDTO cartItemEntityToDto(CartItemEntity entity);

  @Mappings({
      @Mapping(target = "cart", ignore = true),
      @Mapping(target = "productEntity.regDate", ignore = true)
  })
  CartItemEntity cartItemDtoToEntity(CartItemDTO dto);
}
