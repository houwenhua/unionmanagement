package cn.pzhu.interfaces;

import java.util.List;

import cn.pzhu.model.Activity;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.ActivityTempVo;
import cn.pzhu.vo.ActivityVo;

public interface IActivityService {

	Pagination getPagination(int page, int rows,SysUser user, ActivityVo av);
	
	Pagination getPaginationOfEdit(int page, int rows, SysUser user, ActivityVo av);

	void saveActivity(ActivityVo av, SysUser user);

	ActivityVo queryActivityById(Integer id);

	void updateActivity(ActivityVo av);

	void removeActivitys(String ids);

	List<ActivityTempVo> findActivity(String string);

	Activity getActivityById(Integer id);

}
