package cn.pzhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.FamilyMember;


public interface FamilyMemberRepository
extends JpaRepository<FamilyMember, Integer>,JpaSpecificationExecutor<FamilyMember> {
	
	@Modifying
	@Query("delete from FamilyMember where user_id in (?1)")
	void deleteFamilyMemberByUserId(String ids);
}
