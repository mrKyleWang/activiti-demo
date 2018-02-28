package top.kylewang.service;

import org.activiti.engine.runtime.ProcessInstance;
import top.kylewang.po.PurchaseApply;

import java.util.Map;

public interface PurchaseService {
	public ProcessInstance startWorkflow(PurchaseApply apply,String userid,Map<String,Object> variables);
	PurchaseApply getPurchase(int id);
	void updatePurchase(PurchaseApply a);
}
