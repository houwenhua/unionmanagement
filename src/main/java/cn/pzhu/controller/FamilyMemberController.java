package cn.pzhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.pzhu.interfaces.IFamilyMemberService;
import cn.pzhu.interfaces.ISysUserService;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.FamilyMemberVo;

@RestController
@RequestMapping("/FamilyMemberController")
public class FamilyMemberController {

	@Autowired
	private IFamilyMemberService familyMemberService;
	
	@Autowired
	private ISysUserService userService;
	
	/**
	 * 加载家庭成员信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page,int rows,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		Pagination pagination = familyMemberService.getPagination(page, rows,user);
		return pagination;
	}
	
	@RequestMapping("/paginationQueryFamily")
	public Pagination paginationQueryFamily(int page,int rows,Integer id){
		SysUser user = userService.getUserById(id);
		Pagination pagination = familyMemberService.getPagination(page, rows,user);
		return pagination;
	}
	
	@RequestMapping("/addFamilyMember")
	public AjaxJson addFamilyMember(FamilyMemberVo memberVo,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			familyMemberService.saveFamilyMember(memberVo,user);
			result.setSuccess(true);
			result.setMsg("添加成功！");
		}catch(DBException e){
			result.setMsg("添加失败！");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/queryFamilyMember")
	public FamilyMemberVo queryFamilyMember(String id){
		FamilyMemberVo familyMemberVo = familyMemberService.getFamilyMemberById(Integer.parseInt(id));
		return familyMemberVo;
	}
	
	@RequestMapping("/update")
	public AjaxJson update(FamilyMemberVo memberVo,HttpSession session){
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			familyMemberService.updateFamilyMember(memberVo,user);
			result.setSuccess(true);
			result.setMsg("更改成功！");
		}catch(DBException e){
			e.printStackTrace();
		    result.setMsg("更改失败！");
		    
		}
		return result;
	}
	
	@RequestMapping("/remove")
	public AjaxJson remove(String ids){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			familyMemberService.removeFamilyMembers(ids);
			result.setMsg("删除成功！");
			result.setSuccess(true);
		}catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败！");
		}
		return result;
	}
}
