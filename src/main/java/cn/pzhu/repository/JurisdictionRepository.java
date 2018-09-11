package cn.pzhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.Jurisdiction;

public interface JurisdictionRepository  extends JpaRepository<Jurisdiction, Integer>,JpaSpecificationExecutor<Jurisdiction>{

	@Query("from Jurisdiction where grade = ?1")
	Jurisdiction findJurisdictionByGrade(String jurisdiction);

}
