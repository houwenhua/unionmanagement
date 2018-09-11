package cn.pzhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.Resume;


public interface ResumeRepository 
	extends JpaRepository<Resume, Integer>,JpaSpecificationExecutor<Resume> {
	
	@Modifying
	@Query("delete from Resume where user_id in (?1)")
	void deleteResumeByUserId(String ids);
}
