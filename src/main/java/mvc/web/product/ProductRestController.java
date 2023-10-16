package mvc.web.product;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mvc.service.domain.PageRequestDTO;
import mvc.service.domain.PageResultDTO;
import mvc.service.domain.ProductDTO;
import mvc.service.entity.ProductEntity;
import mvc.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/product/*")
@ToString
@RequiredArgsConstructor
@Slf4j
public class ProductRestController {

  ///Field
  @Autowired
  private ProductService productService;

  @Value("${pageUnit}")
  int pageUnit;

  @Value("${pageSize}")
  int pageSize;

  @PostMapping(value = "/json/addProduct")
  public void addProduct(@RequestBody ProductDTO productDTO,
                          HttpServletRequest request) throws Exception {
//    @RequestParam("file") MultipartFile[] files,
    log.info("/product/json/addProduct");
    log.info("addProduct에서 받은 product는 {}", productDTO);

//    List<String> fileNames = new ArrayList<>();
//    for (MultipartFile file : files) {
//      if (!file.isEmpty()) {
//        String originalFileName = file.getOriginalFilename();
//        String uploadPath = request.getServletContext().getRealPath("/images/uploadFiles/");
//
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//          uploadDir.mkdirs();
//        }
//
//        try {
//          String uploadedFilePath = uploadPath + File.separator + originalFileName;
//          file.transferTo(new File(uploadedFilePath));
//          fileNames.add(originalFileName);
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//      }
//    }
    // Set the file names to the productDTO
//    productDTO.setFileName(fileNames);

    // Business Logic
    productService.addProduct(productDTO);
  }

  @PostMapping("json/getProductList")
  public Map<String, Object> getListProduct(@RequestBody PageRequestDTO requestDTO)
      throws Exception {

    System.out.println("json/getProductList");
    log.info("json/getProductList {}", requestDTO);

    requestDTO.setPageSize(pageSize);

    PageResultDTO<ProductDTO, ProductEntity> result = productService.getProductList(requestDTO);

    log.info("getProductList의 result 는 {}", result);

    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put("data", result.getDtoList());  // 데이터 리스트를 넣음
    responseMap.put("totalPage", result.getTotalPage());  // 총 페이지 수를 넣음
    responseMap.put("currentPage", result.getCurrentPage());  // 현재 페이지를 넣음

    return responseMap;
  }

  @GetMapping("json/getProduct/{prodNo}")
  public ProductDTO getProduct(@PathVariable("prodNo") int prodNo) throws Exception {

    log.info("/product/json/getProduct");
    log.info("getProduct의 prodNo는 {}",prodNo);

    return productService.getProduct(prodNo);
  }

  	@PostMapping( "json/updateProduct")
	public void updateProduct(@RequestBody ProductDTO productDTO,
        HttpServletRequest request) throws Exception {

      log.info("json/product/updateProduct");
      log.info("updateProduct에서 받은 product는 {}", productDTO);

//	    List<String> fileNames = new ArrayList<>();
//
//	    String existingFileName = product.getFileName();
//	    System.out.println("파일네임은"+existingFileName);
//
//	    if (files != null) {
//	        for (MultipartFile file : files) {
//	            if (!file.isEmpty()) {
//	                String originalFileName = file.getOriginalFilename();
//	                String uploadPath = request.getServletContext().getRealPath("/images/uploadFiles/");
//
//	                File uploadDir = new File(uploadPath);
//	                if (!uploadDir.exists()) {
//	                    uploadDir.mkdirs();
//	                }
//
//	                try {
//	                    String uploadedFilePath = uploadPath + File.separator + originalFileName;
//	                    file.transferTo(new File(uploadedFilePath));
//
//
//	                    fileNames.add(originalFileName);
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//
//	                }
//	            }
//	        }
//	    }
//
//
//	    if (fileNames.isEmpty()) {
//
//	        if (existingFileName != null && !existingFileName.isEmpty()) {
//	            fileNames.add(existingFileName);
//	        }
//	    }
//
//
//	    product.setFileName(fileNames.toString().replace("[", "").replace("]", ""));

	    // Business Logic


	    productService.updateProduct(productDTO);
	}

}