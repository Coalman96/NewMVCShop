package mvc.service.mapper;

import javax.annotation.processing.Generated;
import mvc.service.domain.CartItemDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.entity.CartItemEntity;
import mvc.service.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-06T03:31:20+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CartItemMapperImpl implements CartItemMapper {

    @Override
    public CartItemDTO cartItemEntityToDto(CartItemEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CartItemDTO cartItemDTO = new CartItemDTO();

        if ( entity.getId() != null ) {
            cartItemDTO.setId( String.valueOf( entity.getId() ) );
        }
        cartItemDTO.setProduct( productEntityToProductDTO( entity.getProduct() ) );
        cartItemDTO.setCount( entity.getCount() );

        return cartItemDTO;
    }

    @Override
    public CartItemEntity cartItemDtoToEntity(CartItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CartItemEntity cartItemEntity = new CartItemEntity();

        if ( dto.getId() != null ) {
            cartItemEntity.setId( Long.parseLong( dto.getId() ) );
        }
        cartItemEntity.setProduct( productDTOToProductEntity( dto.getProduct() ) );
        cartItemEntity.setCount( dto.getCount() );

        return cartItemEntity;
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
}
