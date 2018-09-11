package cn.pzhu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.Activity;

public interface ActivityRepository
extends JpaRepository<Activity, Integer>,JpaSpecificationExecutor<Activity> {

	@Query("from Activity where user_id = ?1")
	List<Activity> findAllByUserId(int id);

    @Query(nativeQuery=true,value="select * from (select a.id,a.name,a.end_time,a.remark,a.state,u.name as username,j.grade,i.name as iname"+
    " from activity a,xt_user u,t_jurisdiction j,t_institute i where a.user_id = u.id and u.jurisdiction_id = j.id and  u.institute_id = i.id) as auij"+
    " where ((auij.grade = 1 or (auij.grade = 2 and auij.iname =?1)) and auij.state = 0)")
    List<Object[]> findAction(String iname);
}
