package mvc.service.product.impl;

import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.entity.ProductEntity;
import mvc.service.entity.QProductEntity;
import mvc.service.product.ProductService;
import mvc.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("productServiceImpl")
@Transactional
@RequiredArgsConstructor
@Slf4j
@ToString
public class ProductServiceImpl implements ProductService {

  ///Field
  @Autowired
  private ProductRepository productRepository;


  @Override
  public ProductDTO getProduct(int prodNo) throws Exception {

    Optional<ProductEntity> productEntityOptional = productRepository.findById(prodNo);

    // ProductEntity를 ProductDTO로 변환
    return productEntityOptional.map(this::entityToDTO).orElse(null);
  }

  @Override
  public void addProduct(ProductDTO productDTO) throws Exception {

    ProductEntity productEntity = ProductEntity.builder()
        .prodNo(productDTO.getProdNo())
        .prodCount(productDTO.getProdCount())
        .fileName(productDTO.getFileName())
        .manuDate(productDTO.getManuDate())
        .price(productDTO.getPrice())
        .prodDetail(productDTO.getProdDetail())
        .prodName(productDTO.getProdName())
        .build();

    productRepository.save(productEntity);
  }

  @Override
  public void updateProduct(ProductDTO productDTO) throws Exception {
    // DTO를 엔티티로 변환
    ProductEntity productEntity = dtoToEntity(productDTO);

    // 상품 업데이트
    productRepository.save(productEntity);
  }

  @Override
  public void updateProductCount(Map<String, Object> purchase) throws Exception {
//		productRepository.updateProductCount(purchase);
  }

  public PageResultDTO<ProductDTO, ProductEntity> getProductList(PageRequestDTO requestDTO) {

    //DTO를 받아서 정렬
    Pageable pageable = requestDTO.getPageable(Sort.by("prodNo"));

    //검색조건 설정
    BooleanBuilder booleanBuilder = getSearch(requestDTO);

    //JPA 결과 값을 result에 저장
    //Querydsl추가(검색조건)
    Page<ProductEntity> result = productRepository.findAll(booleanBuilder, pageable);

    //result를 화면에 뿌리기위해 DTO로 변환
    Function<ProductEntity, ProductDTO> fn = (this::entityToDTO);

    return new PageResultDTO<>(result, fn);
  }

  private BooleanBuilder getSearch(PageRequestDTO requestDTO) {

    // PageRequestDTO에서 검색 유형 및 키워드 가져오기
    String searchType = requestDTO.getSearchType();
    String searchKeyword = requestDTO.getSearchKeyword();

    // 초기화된 BooleanBuilder 객체 생성 -> WHERE 절
    BooleanBuilder booleanBuilder = new BooleanBuilder();

    // 사용할 엔터티(QUserEntity) 설정
    //Qentity가 userEntity를 참조
    QProductEntity qProductEntity = QProductEntity.productEntity;

    // 검색 키워드가 null이면 빈 BooleanBuilder 반환
    if(searchKeyword == null){
      return booleanBuilder;
    }

    // 실제 검색 조건을 담는 새로운 BooleanBuilder 생성
    BooleanBuilder conditionBuilder = new BooleanBuilder();

    // 검색 유형에 따라 검색 조건 설정
    if(searchType.contains("0")){
      conditionBuilder.or(qProductEntity.prodName.contains(searchKeyword));
    }
//    else if(searchType.contains("1")){
//      conditionBuilder.or(qProductEntity.price.contains(searchKeyword));
//    }

    // 기본 BooleanBuilder에 조건을 추가하여 최종 검색 조건 구성
    booleanBuilder.and(conditionBuilder);

    // 최종 검색 조건이 추가된 BooleanBuilder 반환
    return booleanBuilder;
  }
}
