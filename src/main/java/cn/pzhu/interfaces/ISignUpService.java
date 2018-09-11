package cn.pzhu.interfaces;

import cn.pzhu.model.Pagination;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.SignUpVo;

public interface ISignUpService {

	void saveSignUp(SignUpActivity sua);

	Pagination getPagination(int page, int rows, SysUser user, SignUpVo svo);

	void removeSignUpActivitys(String ids);

	void updateSignUp(Integer id, Integer name);

	SignUpActivity getSignUpByActivityId(Integer name, String loginname);

	Pagination getPaginationActivity(int page, int rows, SysUser user, SignUpVo svo);

	void checkSignUp(Integer id, String checkactivity);

}
