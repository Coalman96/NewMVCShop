package mvc.web.purchase;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mvc.service.domain.ProductDTO;
import mvc.service.domain.PurchaseDTO;
import mvc.service.domain.UserDTO;
import mvc.service.product.ProductService;
import mvc.service.purchase.PurchaseService;
import mvc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//==> ��ǰ���� Controller
@Controller
@RequestMapping("/purchase/*")
@ToString
@NoArgsConstructor
public class PurchaseController {

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

//	public PurchaseController(){
//		System.out.println(this.getClass());
//	}

  @Value("${pageUnit}")
  int pageUnit;

  @Value("${pageSize}")
  int pageSize;

  @RequestMapping(value = "addPurchase", method = RequestMethod.GET)
  public ModelAndView addPurchaseView(@RequestParam("prodNo") int prodNo) throws Exception {

    System.out.println("purchase/addPurchaseView");

    //Business Logic
    ProductDTO product = productService.getProduct(prodNo);

    // ModelAndView
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
    modelAndView.addObject("product", product);

    return modelAndView;
  }

  @RequestMapping(value = "addPurchase", method = RequestMethod.POST)
  public ModelAndView addPurchase(@ModelAttribute("purchase") PurchaseDTO purchase,
      @RequestParam("prodNo") int prodNo,
      HttpSession session) throws Exception {

    System.out.println("purchase/addPurchase");

    ProductDTO product = productService.getProduct(prodNo);
    purchase.setPurchaseProd(product);
    purchase.setBuyer((UserDTO) session.getAttribute("user"));

    //Business Logic
    purchaseService.addPurchase(purchase);

    //updateProductCount
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("purchase", purchase);
    map.put("prodNo", prodNo);
    productService.updateProductCount(map);
    System.out.println(purchase.toString());

    // ModelAndView
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
    modelAndView.addObject("purchase", purchase);

    return modelAndView;
  }

  @RequestMapping(value = "getPurchase")
  public ModelAndView getPurchase(@RequestParam("tranNo") Integer tranNo) throws Exception {

    System.out.println("purchase/getPurchase");

    //Business Logic
    PurchaseDTO purchase = purchaseService.getPurchase(tranNo);

    // ModelAndView
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("forward:/purchase/getPurchase.jsp");
    modelAndView.addObject("purchase", purchase);

    return modelAndView;
  }


  @RequestMapping(value = "updatePurchaseView", method = RequestMethod.GET)
  public ModelAndView updatePurchaseView(@RequestParam("tranNo") int tranNo) throws Exception {

    System.out.println("purchase/updatePurchaseView");

    //Business Logic
    PurchaseDTO purchase = purchaseService.getPurchase(tranNo);
    ProductDTO product = productService.getProduct(purchase.getPurchaseProd().getProdNo());

    // ModelAndView
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("forward:/purchase/updatePurchaseView.jsp");
    modelAndView.addObject("purchase", purchase);
    modelAndView.addObject("product", product);
    return modelAndView;
  }

  @RequestMapping(value = "updatePurchase", method = RequestMethod.POST)
  public ModelAndView updatePurchase(@ModelAttribute("purchase") PurchaseDTO purchase,
      @RequestParam("productCount") int productCount,
      HttpSession session) throws Exception {

    System.out.println("purchase/updatePurchase");

    PurchaseDTO getPurchase = purchaseService.getPurchase(purchase.getTranNo());

    purchase.setBuyer(getPurchase.getBuyer());
    purchase.setPurchaseProd(getPurchase.getPurchaseProd());
    purchase.setOrderDate(getPurchase.getOrderDate());
    purchase = purchaseService.updatePurchase(purchase);

    //product count
    if (productCount != purchase.getProdCount()) {

      Map<String, Object> map = new HashMap<String, Object>();
      map.put("purchase", purchase);
      map.put("prodNo", purchase.getPurchaseProd().getProdNo());
      productService.updateProductCount(map);

    }
    System.out.println("purchase " + purchase);
    // ModelAndView
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("forward:/purchase/getPurchase.jsp");
    modelAndView.addObject("purchase", purchase);

    return modelAndView;
  }

//  @RequestMapping(value = "listPurchase")
//  public ModelAndView listPurchase(@ModelAttribute("search") Search search,
//      HttpServletRequest request) throws Exception {
//
//    System.out.println("/listPurchase");
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
//    //Optional<UserEntity> user = userService.getUser(((UserDTO) session.getAttribute("user")).getUserId());
//
//    if (user.get().equals("admin")) {
//      map = purchaseService.getSaleList(search);
//    } else {
//      map = purchaseService.getPurchaseList(search,
//          ((UserDTO) session.getAttribute("user")).getUserId().trim());
//    }
//
////    PageRequestDTO resultPage = new PageRequestDTO(search.getCurrentPage(),
////        ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
////    System.out.println(resultPage);
//
//    // ModelAndView
//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.setViewName("forward:/purchase/listPurchase.jsp");
//    modelAndView.addObject("list", map.get("list"));
//    modelAndView.addObject("resultPage", resultPage);
//    modelAndView.addObject("search", search);
//
//    return modelAndView;
//    return null;
  }

//  @RequestMapping(value = "listSale")
//  public ModelAndView listSale(@ModelAttribute("search") Search search, HttpServletRequest request)
//      throws Exception {

//    System.out.println("/listPurchase");
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
//    map = purchaseService.getSaleList(search);
//
//    PageRequestDTO resultPage = new PageRequestDTO(search.getCurrentPage(),
//        ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
//    System.out.println(resultPage);
//
//    // ModelAndView
//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.setViewName("forward:/purchase/listSale.jsp");
//    modelAndView.addObject("list", map.get("list"));
//    modelAndView.addObject("resultPage", resultPage);
//    modelAndView.addObject("search", search);
//
//    return modelAndView;
//    return null;
//  }


//}