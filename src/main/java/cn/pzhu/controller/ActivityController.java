package cn.pzhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.pzhu.interfaces.IActivityService;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ActivityVo;

@RestController
@RequestMapping("/ActivityController")
public class ActivityController {

	@Autowired
	private IActivityService activityService;
	
	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page, int rows, String name,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		ActivityVo av = null;
		if(name != null && !"".equals(name)){
			av = new ActivityVo();
			av.setName(name);
		}
		Pagination pagination = activityService.getPagination(page, rows,user,av);
		return pagination;
	}
	
	/**
	 * 加载对应用户所发布公告
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/paginationQueryOfEdite")
	public Pagination paginationQueryOfEdite(int page,int rows,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		ActivityVo av = null;
		Pagination pagination = activityService.getPaginationOfEdit(page, rows,user,av);
		return pagination;
	}
	
	@RequestMapping("/addActivity")
	public AjaxJson addActivity(ActivityVo av,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			activityService.saveActivity(av,user);
			result.setMsg("添加成功！");
			result.setSuccess(true);
		}catch(DBException e){
			e.printStackTrace();
			result.setMsg("添加失败");
		}
		return result;
	}
	
	
	@RequestMapping("/queryActivity")
	public ActivityVo queryActivity(Integer id){
		ActivityVo av = activityService.queryActivityById(id);
		return av;
	}
	
	@RequestMapping("/update")
	public AjaxJson update(ActivityVo av,HttpSession session){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			activityService.updateActivity(av);
			result.setMsg("修改成功");
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
			activityService.removeActivitys(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	
	
}
