package mvc.service.product;

import java.util.Map;
import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.entity.ProductEntity;


public interface ProductService {

  default ProductEntity dtoToEntity(ProductDTO dto){

    ProductEntity entity = ProductEntity.builder()
        .prodNo(dto.getProdNo())
        .prodCount(dto.getProdCount())
        .fileName(dto.getFileName())
        .manuDate(dto.getManuDate())
        .price(dto.getPrice())
        .prodDetail(dto.getProdDetail())
        .prodName(dto.getProdName())
        .build();

    return entity;
  }

  default ProductDTO entityToDTO(ProductEntity entity){

    ProductDTO dto = ProductDTO.builder()
        .prodNo(entity.getProdNo())
        .prodCount(entity.getProdCount())
        .fileName(entity.getFileName())
        .manuDate(entity.getManuDate())
        .price(entity.getPrice())
        .prodDetail(entity.getProdDetail())
        .prodName(entity.getProdName())
        .build();

    return dto;
  }

  public ProductDTO getProduct(int prodNo) throws Exception;

//  public Map<String, Object> getProductList(Search search) throws Exception;

  public void addProduct(ProductDTO productDTO) throws Exception;

  public void updateProduct(ProductDTO product) throws Exception;

  public void updateProductCount(Map<String, Object> purchase) throws Exception;

  public PageResultDTO<ProductDTO, ProductEntity> getProductList(PageRequestDTO requestDTO);
}

