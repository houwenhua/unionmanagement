package cn.pzhu.interfaces;

import java.util.List;

import cn.pzhu.model.Institute;
import cn.pzhu.model.Jurisdiction;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.SysUserVo;

public interface ISysUserService {

	Pagination getPagination(SysUserVo user, int page, int rows);

	SysUser getLogin(SysUserVo user);

	List<Jurisdiction> findJurisdiction();

	void saveUser(SysUserVo sysUser);

	void removeUser(String ids);

	List<Institute> findInstitute();

	void updatePwd(String pwd,SysUser user);

	SysUserVo getUser(Integer id);

	void updateUsers(SysUserVo sysUser);

	List<SysUser> findAllUsers();

	SysUser getUserById(Integer id);

	List<SysUser> findAllUsersExceptOwne();

	SysUserVo getSysUser(Integer id);

	void updateSysUsers(SysUserVo sysUser);
}
