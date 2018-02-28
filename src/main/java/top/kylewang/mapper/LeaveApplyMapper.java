package top.kylewang.mapper;

import org.springframework.stereotype.Component;
import top.kylewang.pojo.LeaveApply;

/**
 * @author Kyle.Wang
 * 2018-02-28 11:12
 */
@Component(value = "leaveApplyMapper")
public interface LeaveApplyMapper {

    void save(LeaveApply apply);

    LeaveApply get(int id);

    void update(LeaveApply app);
}
