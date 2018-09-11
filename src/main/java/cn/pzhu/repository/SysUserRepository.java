package cn.pzhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer>,JpaSpecificationExecutor<SysUser> {

	@Query("from SysUser s,Jurisdiction j where s.jurisdiction.id=j.id and s.loginname=?1 and s.password=?2 and j.name=?3")
	List<Object[]> login(String loginname, String password, String jurisdiction);

}
