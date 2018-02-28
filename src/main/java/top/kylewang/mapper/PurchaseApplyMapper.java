package top.kylewang.mapper;

import top.kylewang.po.PurchaseApply;

public interface PurchaseApplyMapper {
	void save(PurchaseApply apply);
	PurchaseApply get(int id);
	void update(PurchaseApply apply);
}
