package cn.pzhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.pzhu.interfaces.IActivityService;
import cn.pzhu.interfaces.IStatisticsActivityService;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.StatisticsActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ActivityVo;
import cn.pzhu.vo.StatisticsActivityVo;

@RestController
@RequestMapping("/statisticsActivityController")
public class StatisticsActivityController {

	@Autowired
	private IStatisticsActivityService statisticsService;
	
	@Autowired
	private IActivityService activityService;
	
	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page, int rows, String name,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		StatisticsActivity sa = null;
		if(name != null && !"".equals(name)){
			sa = new StatisticsActivity();
			sa.setActionname(name);
		}
		Pagination pagination = statisticsService.getPagination(page, rows,user,sa);
		return pagination;
	}
	
	@RequestMapping("/add")
	public AjaxJson add(Integer name,String number,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		Activity act = activityService.getActivityById(name);
		
		StatisticsActivity sa = new StatisticsActivity();
		sa.setActionname(act.getName());
		sa.setStatisticsname(user.getLoginname());
		sa.setInstitute(user.getInstitute().getName());
		sa.setNumber(number);
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			statisticsService.saveStatistics(sa);
			result.setMsg("添加成功！");
			result.setSuccess(true);
		}catch(DBException e){
			e.printStackTrace();
			result.setMsg("添加失败");
		}
		return result;
	}
	
	@RequestMapping("/update")
	public AjaxJson update(Integer id,Integer name,String number,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		Activity act = activityService.getActivityById(name);
		
		StatisticsActivity sa = statisticsService.getStatisticsActivity(id);
		sa.setActionname(act.getName());
		sa.setNumber(number);
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			statisticsService.updateStatistics(sa);
			result.setMsg("修改成功！");
			result.setSuccess(true);
		}catch(DBException e){
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		return result;
	}
	
	@RequestMapping("/remove")
	public AjaxJson remove(String ids) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			statisticsService.removeStatisticsActivity(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
}
