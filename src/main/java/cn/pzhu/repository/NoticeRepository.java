package cn.pzhu.repository;

import cn.pzhu.model.Notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository
        extends JpaRepository<Notice, Integer>,JpaSpecificationExecutor<Notice> {
	
	@Modifying
	@Query("delete from Notice where user_Id in (?1)")
	void deleteNoticeByUserId(String ids);

	@Query("from Notice where user_Id = ?1")
	List<Notice> findNoticeByUserId(int id);
}
