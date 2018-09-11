package cn.pzhu.interfaces;


import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.FamilyMemberVo;

public interface IFamilyMemberService {

	Pagination getPagination(int page, int rows, SysUser user);

	void saveFamilyMember(FamilyMemberVo memberVo, SysUser user);

	void updateFamilyMember(FamilyMemberVo memberVo, SysUser user);

	FamilyMemberVo getFamilyMemberById(int id);

	void removeFamilyMembers(String ids);

}
