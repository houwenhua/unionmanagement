package cn.pzhu.interfaces;

import cn.pzhu.model.Pagination;
import cn.pzhu.model.StatisticsActivity;
import cn.pzhu.model.SysUser;

public interface IStatisticsActivityService {

	Pagination getPagination(int page, int rows, SysUser user, StatisticsActivity sa);

	void saveStatistics(StatisticsActivity sa);

	void updateStatistics(StatisticsActivity sa);

	StatisticsActivity getStatisticsActivity(Integer id);

	void removeStatisticsActivity(String ids);

}
