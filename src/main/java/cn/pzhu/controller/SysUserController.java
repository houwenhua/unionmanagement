package cn.pzhu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.pzhu.interfaces.ISysUserService;
import cn.pzhu.model.Institute;
import cn.pzhu.model.Jurisdiction;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.Constants;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.SysUserVo;

@RestController //Controller加载时自动创建对象 Rest：将返回值变成json对象
@RequestMapping("/SysUserController")
public class SysUserController {

	@Autowired
	private ISysUserService userService;

	@RequestMapping("/goLogin")
	public ModelAndView goLogin() {
		ModelAndView mav = new ModelAndView("login.html");//跳转页面
		return mav;
	}

	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page, int rows, String name) {
		SysUserVo user = null;
		if (name != null) {
			user = new SysUserVo();
			user.setName(name);
		}
		Pagination pagination = userService.getPagination(user, page, rows);
		return pagination;
	}

	@RequestMapping("/login")
	public String login(String loginname, String pwd, String jurisdiction, HttpSession session) {
		SysUserVo user = new SysUserVo();
		user.setLoginname(loginname);
		user.setPassword(pwd);
		user.setJurisdiction(jurisdiction);
		SysUser sysUser = userService.getLogin(user);
		//查询数据表，若用户存在则登录成功
		if (sysUser != null) {
			session.setAttribute("user", sysUser);//在回话中保存此对象。
			return Constants.SUCCESS;
		} else {
			return Constants.ERROR;
		}
	}

	/**
	 * 返回SysUserVo对象，加载导航栏基本信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUser")
	public SysUserVo getUser(HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		//将所获取的SysUser对象封装成一个SysUserVo对象，避免json转换异常
		SysUserVo sv = new SysUserVo(user.getId(), user.getName(), user.getLoginname(), user.getPassword(),
				user.getEmail(), user.getJurisdiction().getName(), user.getState(), user.getPhone(),
				user.getInstitute().getName());
		return sv;
	}

	/**
	 * 关闭session注销登录
	 */
	@RequestMapping("/closeSession")
	public ModelAndView closeSession(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/login.html");
		request.getSession().invalidate();//使session失效
		request.getSession().removeAttribute("user");
		return mav;
	}

	/**
	 * 动态加载添加管理员添加用户中权限下拉框
	 */
	@RequestMapping("/getJurisdiction")
	public List<JSONObject> getJurisdiction() {
		List<Jurisdiction> jurisdictions = userService.findJurisdiction();
		JSONObject obj = null;
		List<JSONObject> objs = new ArrayList<JSONObject>();
		for (Jurisdiction jurisdiction : jurisdictions) {
			obj = new JSONObject();
			obj.put("grade", jurisdiction.getGrade());
			obj.put("name", jurisdiction.getName());
			objs.add(obj);
		}
		return objs;
	}

	/**
	 * 动态加载学院下拉框
	 */
	@RequestMapping("/getInstituteName")
	public List<JSONObject> getInstituteName() {
		List<Institute> institutes = userService.findInstitute();
		JSONObject obj = null;
		List<JSONObject> objs = new ArrayList<JSONObject>();
		for (Institute institute : institutes) {
			obj = new JSONObject();
			obj.put("InstituteId", institute.getId());
			obj.put("InstituteName", institute.getName());
			objs.add(obj);
		}
		return objs;
	}

	/**
	 * 增加用户
	 * 
	 * @param sysUser
	 */
	@RequestMapping("/addUser")
	public AjaxJson addUser(SysUserVo sysUser, String institute) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		List<SysUser> users = userService.findAllUsers();
		Boolean flag = true;
		for (SysUser user : users) {
			if (sysUser.getLoginname().equals(user.getLoginname())) {
				result.setMsg("添加失败，此用户名已经存在！！");
				flag = false;
				break;
			}
		}
		if (flag) {
			try {
				userService.saveUser(sysUser);
				result.setSuccess(true);
				result.setMsg("增加成功");
			} catch (DBException e) {
				e.printStackTrace();
				result.setMsg("增加失败");
			}
		}
		return result;
	}

	@RequestMapping("/deleteUser")
	public AjaxJson deleteUser(String ids, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		// 得到用户id
		Integer id = user.getId();
		boolean flag = true;

		AjaxJson result = new AjaxJson();
		result.setSuccess(false);

		String[] index = ids.split(",");
		for (String in : index) {
			if (Integer.parseInt(in) == id) {
				result.setMsg("系统管理员，不支持删除");
				flag = false;
			}
		}

		if (flag) {
			try {
				userService.removeUser(ids);
				result.setSuccess(true);
				result.setMsg("删除成功");
			} catch (DBException e) {
				e.printStackTrace();
				result.setMsg("删除失败");
			}
		}

		return result;
	}

	/**
	 * 修改密码
	 * @param pwdone
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePwd")
	public AjaxJson updatePwd(String pwdone, HttpSession session) {
		SysUser user = (SysUser) session.getAttribute("user");
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try {
			userService.updatePwd(pwdone, user);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch (DBException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		return result;
	}

	@RequestMapping("/queryUser")
	public SysUserVo queryUser(Integer id) {
		SysUserVo user = userService.getUser(id);
		return user;
	}

	@RequestMapping("/updateUser")
	public AjaxJson updateUser(SysUserVo sysUser) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
	//	List<SysUser> users = userService.findAllUsersExceptOwne();
		try {
			userService.updateUsers(sysUser);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch (DBException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		return result;
	}
	
	/**
	 * 修改个人信息
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("/updateSysUser")
	public AjaxJson updateSysUser(SysUserVo sysUser,HttpSession session) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try {
			userService.updateSysUsers(sysUser);
			result.setSuccess(true);
			result.setMsg("修改成功");
			SysUser user = userService.getUserById(sysUser.getId());
			session.setAttribute("user", user);//在回话中保存此对象。
		} catch (DBException e) {
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		return result;
	}
	
	/**
	 * 加载用户基本信息
	 * @param sysUser
	 * @param user
	 */
	@RequestMapping("/querySysUser")
	public SysUserVo querySysUser(SysUserVo sysUser,HttpSession user) {
		SysUser su = (SysUser) user.getAttribute("user");
		SysUserVo suv = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
				su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
				su.getAddress());
		suv.setSex(su.getSex());
		suv.setBirthday(su.getBirthday());
		suv.setPolicy(su.getPolicy());
		suv.setPosition(su.getPosition());
		/*Integer id = sysUser.getId();
		SysUserVo suo = userService.getSysUser(id);*/
		return suv;
	}
}
