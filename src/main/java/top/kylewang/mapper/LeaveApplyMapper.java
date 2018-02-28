package top.kylewang.mapper;

import top.kylewang.po.LeaveApply;

public interface LeaveApplyMapper {
	void save(LeaveApply apply);
	LeaveApply get(int id);
	void update(LeaveApply app);
}
