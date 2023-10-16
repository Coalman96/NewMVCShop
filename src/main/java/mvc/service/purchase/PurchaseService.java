package mvc.service.purchase;

import mvc.service.domain.PurchaseDTO;

public interface PurchaseService {

  public PurchaseDTO addPurchase(PurchaseDTO purchase) throws Exception;

  public PurchaseDTO getPurchase(int tranNo) throws Exception;

//  public Map<String, Object> getPurchaseList(Search search, String userId) throws Exception;

//  public Map<String, Object> getSaleList(Search search) throws Exception;

  public PurchaseDTO updatePurchase(PurchaseDTO purchase) throws Exception;

  public void updateTranCode(PurchaseDTO purchase) throws Exception;


}
