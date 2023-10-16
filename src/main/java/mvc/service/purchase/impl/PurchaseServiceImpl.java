package mvc.service.purchase.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mvc.service.domain.PurchaseDTO;
import mvc.service.purchase.PurchaseService;
import mvc.service.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional()
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {


  @Autowired
  private PurchaseRepository purchaseRepository;

//  public PurchaseServiceImpl() {
//    this.purchaseDao = purchaseDao;
//  }

  @Override
  public PurchaseDTO addPurchase(PurchaseDTO purchase) throws Exception {
//    purchase = purchaseDao.insertPurchase(purchase);
    return null;
  }

  @Override
  public PurchaseDTO getPurchase(int tranNo) throws Exception {
//    return purchaseDao.findPurchase(tranNo);
    return null;
  }

//  @Override
//  public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception {
////    return purchaseDao.getPurchaseList(search, userId);
//    return null;
//  }
//
//  @Override
//  public Map<String, Object> getSaleList(Search search) throws Exception {
////    return purchaseDao.getSaleList(search);
//    return null;
//  }

  @Override
  public PurchaseDTO updatePurchase(PurchaseDTO purchase) throws Exception {
//    return purchaseDao.updatePurchase(purchase);
    return null;
  }

  @Override
  public void updateTranCode(PurchaseDTO purchase) throws Exception {
//    purchaseDao.updateTranCode(purchase);
  }

}
