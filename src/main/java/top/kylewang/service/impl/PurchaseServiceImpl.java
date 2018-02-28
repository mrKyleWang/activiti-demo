package top.kylewang.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.kylewang.mapper.PurchaseApplyMapper;
import top.kylewang.pojo.PurchaseApply;
import top.kylewang.service.PurchaseService;

import java.util.Map;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:19
 */
@Transactional(rollbackFor = Exception.class,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service
public class PurchaseServiceImpl implements PurchaseService{
	@Autowired
	PurchaseApplyMapper purchaseApplyMapper;
	@Autowired
	IdentityService identityService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;

	@Override
	public ProcessInstance startWorkflow(PurchaseApply apply, String userid,Map<String, Object> variables) {
		purchaseApplyMapper.save(apply);
		String businesskey=String.valueOf(apply.getId());//使用leaveapply表的主键作为businesskey,连接业务数据和流程数据
		identityService.setAuthenticatedUserId(userid);
		ProcessInstance instance=runtimeService.startProcessInstanceByKey("purchase",businesskey,variables);
		return instance;
	}

	@Override
	public PurchaseApply getPurchase(int id) {
		return purchaseApplyMapper.get(id);
	}

	@Override
	public void updatePurchase(PurchaseApply a) {
		purchaseApplyMapper.update(a);
	}

}
