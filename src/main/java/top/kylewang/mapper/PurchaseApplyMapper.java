package top.kylewang.mapper;

import org.springframework.stereotype.Component;
import top.kylewang.pojo.PurchaseApply;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:20
 */
@Component("purchaseApplyMapper")
public interface PurchaseApplyMapper {
    void save(PurchaseApply apply);

    PurchaseApply get(int id);

    void update(PurchaseApply apply);
}
