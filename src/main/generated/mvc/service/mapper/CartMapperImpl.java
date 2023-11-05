package mvc.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import mvc.service.domain.CartDTO;
import mvc.service.domain.CartItemDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.entity.CartEntity;
import mvc.service.entity.CartItemEntity;
import mvc.service.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T03:31:20+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartEntity cartDtoToEntity(CartDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CartEntity cartEntity = new CartEntity();

        cartEntity.setId( dto.getId() );
        cartEntity.setCount( dto.getCount() );
        cartEntity.setCartItems( cartItemDTOListToCartItemEntityList( dto.getCartItems() ) );
        cartEntity.setCreateDate( dto.getCreateDate() );

        return cartEntity;
    }

    @Override
    public CartDTO cartEntityToDto(CartEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setId( entity.getId() );
        cartDTO.setCount( entity.getCount() );
        cartDTO.setCreateDate( entity.getCreateDate() );
        cartDTO.setCartItems( cartItemEntityListToCartItemDTOList( entity.getCartItems() ) );

        return cartDTO;
    }

    protected ProductEntity productDTOToProductEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        ProductEntity.ProductEntityBuilder productEntity = ProductEntity.builder();

        productEntity.prodNo( productDTO.getProdNo() );
        productEntity.fileName( productDTO.getFileName() );
        productEntity.manuDate( productDTO.getManuDate() );
        productEntity.price( productDTO.getPrice() );
        productEntity.prodDetail( productDTO.getProdDetail() );
        productEntity.prodName( productDTO.getProdName() );
        productEntity.prodCount( productDTO.getProdCount() );

        return productEntity.build();
    }

    protected CartItemEntity cartItemDTOToCartItemEntity(CartItemDTO cartItemDTO) {
        if ( cartItemDTO == null ) {
            return null;
        }

        CartItemEntity cartItemEntity = new CartItemEntity();

        if ( cartItemDTO.getId() != null ) {
            cartItemEntity.setId( Long.parseLong( cartItemDTO.getId() ) );
        }
        cartItemEntity.setCart( cartDtoToEntity( cartItemDTO.getCart() ) );
        cartItemEntity.setProduct( productDTOToProductEntity( cartItemDTO.getProduct() ) );
        cartItemEntity.setCount( cartItemDTO.getCount() );

        return cartItemEntity;
    }

    protected List<CartItemEntity> cartItemDTOListToCartItemEntityList(List<CartItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemEntity> list1 = new ArrayList<CartItemEntity>( list.size() );
        for ( CartItemDTO cartItemDTO : list ) {
            list1.add( cartItemDTOToCartItemEntity( cartItemDTO ) );
        }

        return list1;
    }

    protected ProductDTO productEntityToProductDTO(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.prodNo( productEntity.getProdNo() );
        productDTO.fileName( productEntity.getFileName() );
        productDTO.manuDate( productEntity.getManuDate() );
        productDTO.price( productEntity.getPrice() );
        productDTO.prodDetail( productEntity.getProdDetail() );
        productDTO.prodName( productEntity.getProdName() );
        productDTO.prodCount( productEntity.getProdCount() );

        return productDTO.build();
    }

    protected CartItemDTO cartItemEntityToCartItemDTO(CartItemEntity cartItemEntity) {
        if ( cartItemEntity == null ) {
            return null;
        }

        CartItemDTO cartItemDTO = new CartItemDTO();

        if ( cartItemEntity.getId() != null ) {
            cartItemDTO.setId( String.valueOf( cartItemEntity.getId() ) );
        }
        cartItemDTO.setCart( cartEntityToDto( cartItemEntity.getCart() ) );
        cartItemDTO.setProduct( productEntityToProductDTO( cartItemEntity.getProduct() ) );
        cartItemDTO.setCount( cartItemEntity.getCount() );

        return cartItemDTO;
    }

    protected List<CartItemDTO> cartItemEntityListToCartItemDTOList(List<CartItemEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemDTO> list1 = new ArrayList<CartItemDTO>( list.size() );
        for ( CartItemEntity cartItemEntity : list ) {
            list1.add( cartItemEntityToCartItemDTO( cartItemEntity ) );
        }

        return list1;
    }
}
