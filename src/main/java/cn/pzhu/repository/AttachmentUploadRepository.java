package cn.pzhu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.pzhu.model.Attachment;
import cn.pzhu.model.Notice;

public interface AttachmentUploadRepository 
				extends JpaRepository<Attachment, Integer>,JpaSpecificationExecutor<Attachment> {

	/*@Query("from Attachment a,Notice n where a.notice.id = n.id and n.id = ?1")*/
	@Query("from Attachment where notice_id = ?1")
	List<Attachment> findAllAtt(int id);
	
	@Query("from Attachment where signup_id = ?1")
	List<Attachment> findAllActivity(int id);
	
	@Query("from Attachment where statistics_activity_id = ?1")
	List<Attachment> findAllStatisticsActivity(int id);

	@Modifying
	@Query("delete from Attachment where notice_id = ?1")
	void deleteAttachment(int id);
	
	@Modifying
	@Query("delete from Attachment where signup_id = ?1")
	void deleteAttachmentActivity(int id);
}
