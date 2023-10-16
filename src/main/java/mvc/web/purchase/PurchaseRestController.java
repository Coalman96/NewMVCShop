package mvc.web.purchase;

import lombok.NoArgsConstructor;
import lombok.ToString;
import mvc.service.domain.PurchaseDTO;
import mvc.service.product.ProductService;
import mvc.service.purchase.PurchaseService;
import mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/purchase/*")
@NoArgsConstructor
@ToString
public class PurchaseRestController {

  ///Field
  @Autowired
  @Qualifier("purchaseServiceImpl")
  private PurchaseService purchaseService;

  @Autowired
  @Qualifier("userServiceImpl")
  private UserService userService;

  @Autowired
  @Qualifier("productServiceImpl")
  private ProductService productService;

//	public PurchaseRestController(){
//		System.out.println(this.getClass());
//	}

  @Value("${pageUnit}")
  int pageUnit;

  @Value("${pageSize}")
  int pageSize;

  @RequestMapping(value = "json/updateTranCode/{tranNo}/{updateTranCode}", method = RequestMethod.GET)
  public void updateTranCode(@PathVariable("tranNo") int tranNo,
      @PathVariable("updateTranCode") String updateTranCode) throws Exception {

    System.out.println("purchase/json/updateTranCode");
    PurchaseDTO purchase = purchaseService.getPurchase(tranNo);
    purchase.setTranCode(updateTranCode);
    purchase.setTranNo(tranNo);

    purchaseService.updateTranCode(purchase);
  }

//  @RequestMapping(value = "json/listPurchase")
//  public Map<String, Object> listPurchase(@RequestBody Search search, HttpServletRequest request)
//      throws Exception {

//    System.out.println("json/listPurchase");
//
//    HttpSession session = request.getSession(true);
//
//    if (search.getCurrentPage() == 0) {
//      search.setCurrentPage(1);
//    }
//    search.setPageSize(pageSize);
//
//    //Business Logic
//    Map<String, Object> map = null;
//
//    Optional<UserEntity> user = userService.getUser(((UserDTO) session.getAttribute("user")).getUserId());
//
//    if (user.get().equals("admin")) {
//      map = purchaseService.getSaleList(search);
//    } else {
//      map = purchaseService.getPurchaseList(search,
//          ((UserDTO) session.getAttribute("user")).getUserId().trim());
//
//    }
//
//    PageRequestDTO resultPage = new PageRequestDTO(search.getCurrentPage(),
//        ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
//    System.out.println(resultPage);
//
//    // AutoComplete
//    List<String> resultList = new ArrayList<>();
//    List<PurchaseDTO> saleList = (List<PurchaseDTO>) map.get("list");
//
//    for (PurchaseDTO sale : saleList) {
//      if ("0".equals(search.getSearchCondition())) {
//        resultList.add("" + sale.getPurchaseProd().getProdName());
//      }
//
//    }
//
//    map.put("resultList", resultList);
//    map.put("list", map.get("list"));
//    map.put("resultPage", resultPage);
//    map.put("search", search);

//    return map;
//    return null;
  }


//}