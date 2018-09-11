package cn.pzhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.pzhu.model.SysUser;
import cn.pzhu.model.Tree;

@Repository
public interface TreeRepository extends JpaRepository<Tree,Integer> ,JpaSpecificationExecutor<Tree>{

	@Query("from Tree where tid = ?1")
	List<Tree> findAllTree(Integer treeid);

	//select s.students from Teacher s where s.name= 'xxx' 
	//from Student s left outer join fetch s.courses
	
	//查询出一个权限下的所有目录  使用SQL语句nativeQuery = true
	@Query(value = "select t.id,t.icon_cls,t.state,t.tid,t.text,t.url from t_nav t left join tree_jurisdiction tj ON t.id=tj.tree_id " 
                  +"left JOIN t_jurisdiction j on j.id=tj.jurisdiction_id where t.tid=?1 and j.name=?2 ", nativeQuery = true)
	List<Tree> loginTree(int treeid, String jurisdiction);

}
