package cn.pzhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.SignUpActivity;

public interface SignUpRepository extends JpaRepository<SignUpActivity, Integer>,JpaSpecificationExecutor<SignUpActivity>{

	@Query("select s from SignUpActivity s,SysUser u where s.user.id = u.id and activity_id = ?1 and u.loginname = ?2")
	SignUpActivity findByActId(Integer actid,String loginname);

	@Query("from SignUpActivity where id = ?1")
	SignUpActivity findBySignUpId(int id);

	@Query("from SignUpActivity where activity_id = ?1")
	List<SignUpActivity> findAllByActivityId(int id);
}
