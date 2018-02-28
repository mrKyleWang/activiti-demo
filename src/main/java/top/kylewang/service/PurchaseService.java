package top.kylewang.service;

import org.activiti.engine.runtime.ProcessInstance;
import top.kylewang.pojo.PurchaseApply;

import java.util.Map;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:25
 */
public interface PurchaseService {
    ProcessInstance startWorkflow(PurchaseApply apply, String userid, Map<String, Object> variables);

    PurchaseApply getPurchase(int id);

    void updatePurchase(PurchaseApply a);
}
