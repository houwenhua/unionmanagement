package cn.pzhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.pzhu.interfaces.IResumeService;
import cn.pzhu.interfaces.ISysUserService;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ResumeVo;
import cn.pzhu.vo.SysUserVo;

@RestController
@RequestMapping("/ResumeController")
public class ResumeController {

	@Autowired
	private IResumeService resumeService;
	
	@Autowired
	private ISysUserService userService;
	
	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page, int rows,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		Pagination pagination = resumeService.getPagination(page, rows,user);
		return pagination;
	}
	
	@RequestMapping("/paginationQueryResume")
	public Pagination paginationQueryResume(int page, int rows,Integer id) {
		SysUser user = userService.getUserById(id);
		Pagination pagination = resumeService.getPagination(page, rows,user);
		return pagination;
	}
	
	@RequestMapping("/queryResume")
	public ResumeVo queryResume(String id) {
		ResumeVo rv = resumeService.getResumeById(Integer.parseInt(id));
		return rv;
	}
	/**
	 * 添加简历信息
	 * @param resume
	 */
	@RequestMapping("/addResume")
	public AjaxJson addResume(ResumeVo resume,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		
		try{
			resumeService.saveResume(resume,user);
			result.setSuccess(true);
			result.setMsg("增加成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("增加失败");
		}
		return result;
	}
	
	/**
	 * 修改简历条目
	 * @param rv
	 * @param session
	 */
	@RequestMapping("/update")
	public AjaxJson update(ResumeVo rv,HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			resumeService.updateResume(rv,user);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch(DBException e){
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
			resumeService.removeResumes(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
}
