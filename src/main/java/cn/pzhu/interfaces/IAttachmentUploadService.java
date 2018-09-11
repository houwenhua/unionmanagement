package cn.pzhu.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.pzhu.model.Attachment;
import cn.pzhu.model.Pagination;
import cn.pzhu.vo.AttachmentVo;

public interface IAttachmentUploadService {

	void saveAttachment(Attachment att);

	void saveAndUpload(MultipartFile file, Integer id);

	Pagination getPagination(int page, int rows, int id);

	void removeAttachments(String ids);

	List<AttachmentVo> findAllAttachment(Integer id);

	void saveAndUploadActivity(MultipartFile file, Integer id);

	Pagination getPaginationActivity(int page, int rows, int id);

	void removeAttachmentsActivity(String ids);

	Pagination getPaginationStatisticsActivity(int page, int rows, int id);

	void saveAndUploadStatisticsActivity(MultipartFile file, Integer id);

	void removeAttachmentsStatisticsActivity(String ids);

}
