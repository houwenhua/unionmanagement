package cn.pzhu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.pzhu.interfaces.IActivityService;
import cn.pzhu.interfaces.ISignUpService;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Jurisdiction;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ActivityTempVo;
import cn.pzhu.vo.ActivityVo;
import cn.pzhu.vo.SignUpVo;

@RestController
@RequestMapping("/signUpController")
public class SignUpController {

	@Autowired
	private ISignUpService signUpService;
	
	@Autowired
	private IActivityService activityService;
	
	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page, int rows, String name,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		SignUpVo svo = null;
		if(name != null && !"".equals(name)){
			svo = new SignUpVo();
			svo.setName(name);
		}
		Pagination pagination = signUpService.getPagination(page, rows,user,svo);
		return pagination;
	}
	
	@RequestMapping("/paginationQueryActivity")
	public Pagination paginationQueryActivity(int page, int rows, String name,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		SignUpVo svo = null;
		if(name != null && !"".equals(name)){
			svo = new SignUpVo();
			svo.setName(name);
		}
		Pagination pagination = signUpService.getPaginationActivity(page, rows,user,svo);
		return pagination;
	}
	
	@RequestMapping("/getActivity")
	public List<JSONObject> getActivity(HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		//根据学院查找报名时间为截止的活动
		List<ActivityTempVo> list = activityService.findActivity(user.getInstitute().getName());
		JSONObject obj = null;
		List<JSONObject> objs = new ArrayList<JSONObject>();
		for (ActivityTempVo a : list) {
			obj = new JSONObject();
			obj.put("activityid", a.getId());
			obj.put("activityname", a.getName());
			objs.add(obj);
		}
		return objs;
	}
	
	@RequestMapping("/add")
	public AjaxJson add(Integer name,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		Activity act = activityService.getActivityById(name);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String signupdate = sdf.format(date);
		String checkactivity = "0";
		
		SignUpActivity s = signUpService.getSignUpByActivityId(name,user.getLoginname());
		
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		if(s != null) {
			result.setMsg("已经参加该活动");
		} else {
			try{
				SignUpActivity sua = new SignUpActivity(user, act, signupdate, checkactivity);
				signUpService.saveSignUp(sua);
				result.setSuccess(true);
				result.setMsg("增加成功");
			} catch(DBException e){
				e.printStackTrace();
				result.setMsg("增加失败");
			}
		}
		
		return result;
	}
	
	@RequestMapping("/remove")
	public AjaxJson remove(String ids) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			signUpService.removeSignUpActivitys(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/update")
	public AjaxJson update(Integer id,Integer name,HttpSession session){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			signUpService.updateSignUp(id,name);
			result.setMsg("修改成功");
			result.setSuccess(true);
		}catch(DBException e){
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		return result;
	}
	
	@RequestMapping("/checkActivity")
	public AjaxJson checkActivity(Integer id,String checkactivity){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			signUpService.checkSignUp(id,checkactivity);
			result.setMsg("审核成功");
			result.setSuccess(true);
		}catch(DBException e){
			e.printStackTrace();
			result.setMsg("审核失败");
		}
		return result;
	}
}
