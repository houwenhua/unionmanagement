package cn.pzhu.interfaces;

import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.ResumeVo;
import cn.pzhu.vo.SysUserVo;

public interface IResumeService {

	//Pagination getPagination(int page, int rows);

	ResumeVo getResumeById(int id);

	void updateResume(ResumeVo rv, SysUser user);
	
	void saveResume(ResumeVo resume, SysUser user);

	void removeResumes(String ids);

	Pagination getPagination(int page, int rows, SysUser user);

}
