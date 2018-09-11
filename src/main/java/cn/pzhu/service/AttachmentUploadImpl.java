package cn.pzhu.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cn.pzhu.interfaces.IAttachmentUploadService;
import cn.pzhu.model.Attachment;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.Resume;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.StatisticsActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.AttachmentUploadRepository;
import cn.pzhu.repository.NoticeRepository;
import cn.pzhu.repository.SignUpRepository;
import cn.pzhu.repository.StatisticsActivityRepository;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.AttachmentVo;

@Service
@Transactional
public class AttachmentUploadImpl implements IAttachmentUploadService {

	@Autowired
	private AttachmentUploadRepository attachmentUploadRepository;
	
	@Autowired
    private NoticeRepository noticeRepository;
	 
	@Autowired
	private SignUpRepository signUpRepository;
	 
	@Autowired 
	private StatisticsActivityRepository statisticsRepository;

	@Override
	public void saveAttachment(Attachment att) {
		attachmentUploadRepository.save(att);
	}

	@Override
	public void saveAndUpload(MultipartFile file, Integer id) {
		if(file.isEmpty()){
			throw new DBException("上传或者保存失败");
		}
		try{
			String originallyname = file.getOriginalFilename();
			String servername = UUID.randomUUID().toString()+originallyname;
			
			String path = "D:/uploadfile";
	        File dest = new File(path + "/" + servername);
	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在 若不存在则创建
	            dest.getParentFile().mkdir();
	        }
	        
	        //获得notice对象
	        Notice notice = noticeRepository.getOne(id);
	        
	        Attachment att = new Attachment(originallyname,servername,notice);
	        
	        attachmentUploadRepository.save(att);
	        
	        file.transferTo(dest); //保存文件到目标文件夹
		} catch(Exception e){
			e.printStackTrace();
			throw new DBException("上传或者保存失败");
		}
		
		
	}
	
	/**
	 * 上传活动报名文件
	 */
	@Override
	public void saveAndUploadActivity(MultipartFile file, Integer id) {
		if(file.isEmpty()){
			throw new DBException("上传或者保存失败");
		}
		try{
			String originallyname = file.getOriginalFilename();
			String servername = UUID.randomUUID().toString()+originallyname;
			
			String path = "D:/uploadfile/activity";
	        File dest = new File(path + "/" + servername);
	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
	            dest.getParentFile().mkdir();
	        }
	        
	        //获得SignUpActivity对象
	        SignUpActivity sua = signUpRepository.getOne(id);
	        
	        Attachment att = new Attachment(originallyname,servername,sua);
	        
	        attachmentUploadRepository.save(att);
	        
	        file.transferTo(dest); //保存文件
		} catch(Exception e){
			e.printStackTrace();
			throw new DBException("上传或者保存失败");
		}
		
	}
	
	/**
	 * 上传活动统计文件
	 */
	@Override
	public void saveAndUploadStatisticsActivity(MultipartFile file, Integer id) {
		if(file.isEmpty()){
			throw new DBException("上传或者保存失败");
		}
		try{
			String originallyname = file.getOriginalFilename();
			String servername = UUID.randomUUID().toString()+originallyname;
			
			String path = "D:/uploadfile/statisticsactivity";
	        File dest = new File(path + "/" + servername);
	        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
	            dest.getParentFile().mkdir();
	        }
	        
	        //获得StatisticsActivity对象
	        StatisticsActivity sa = statisticsRepository.getOne(id);
	        
	        Attachment att = new Attachment(originallyname,servername,sa);
	        
	        attachmentUploadRepository.save(att);
	        
	        file.transferTo(dest); //保存文件
		} catch(Exception e){
			e.printStackTrace();
			throw new DBException("上传或者保存失败");
		}
	}

	@Override
	public Pagination getPagination(int page, int rows, int id) {
		List<Attachment> list = attachmentUploadRepository.findAllAtt(id);
		List<AttachmentVo> vList = new ArrayList<>();
		AttachmentVo av = null;
		for(Attachment a : list){
			av = new AttachmentVo(a.getId(), a.getOriginallyname(), a.getServername());
			vList.add(av);
		}
		return new Pagination(new Long(list.size()), page, vList);
	}
	
	@Override
	public Pagination getPaginationActivity(int page, int rows, int id) {
		List<Attachment> list = attachmentUploadRepository.findAllActivity(id);
		List<AttachmentVo> vList = new ArrayList<>();
		AttachmentVo av = null;
		for(Attachment a : list){
			av = new AttachmentVo(a.getId(), a.getOriginallyname(), a.getServername());
			vList.add(av);
		}
		return new Pagination(new Long(list.size()), page, vList);
	}
	

	@Override
	public Pagination getPaginationStatisticsActivity(int page, int rows, int id) {
		List<Attachment> list = attachmentUploadRepository.findAllStatisticsActivity(id);
		List<AttachmentVo> vList = new ArrayList<>();
		AttachmentVo av = null;
		for(Attachment a : list){
			av = new AttachmentVo(a.getId(), a.getOriginallyname(), a.getServername());
			vList.add(av);
		}
		return new Pagination(new Long(list.size()), page, vList);
	}


	@Override
	public void removeAttachments(String ids) {
		try{
			List<Attachment> attachments = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				Attachment attachment = attachmentUploadRepository.getOne(Integer.parseInt(index[i]));
				attachments.add(attachment);
				//删除硬盘下的文件
				String servername = attachment.getServername();
				String path = "D:/uploadfile";
				File dest = new File(path + "/" + servername); 
				dest.delete();
			}
			attachmentUploadRepository.deleteInBatch(attachments);
		} catch(Exception e) {
			throw new DBException("删除失败");
		}
		
	}
	
	@Override
	public void removeAttachmentsActivity(String ids) {
		try{
			List<Attachment> attachments = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				Attachment attachment = attachmentUploadRepository.getOne(Integer.parseInt(index[i]));
				attachments.add(attachment);
				//删除硬盘下的文件
				String servername = attachment.getServername();
				String path = "D:/uploadfile/activity";
				File dest = new File(path + "/" + servername); 
				dest.delete();
			}
			attachmentUploadRepository.deleteInBatch(attachments);
		} catch(Exception e) {
			throw new DBException("删除失败");
		}
	}
	
	@Override
	public void removeAttachmentsStatisticsActivity(String ids) {
		try{
			List<Attachment> attachments = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				Attachment attachment = attachmentUploadRepository.getOne(Integer.parseInt(index[i]));
				attachments.add(attachment);
				//删除硬盘下的文件
				String servername = attachment.getServername();
				String path = "D:/uploadfile/statisticsactivity";
				File dest = new File(path + "/" + servername); 
				dest.delete();
			}
			attachmentUploadRepository.deleteInBatch(attachments);
		} catch(Exception e) {
			throw new DBException("删除失败");
		}
	}


	@Override
	public List<AttachmentVo> findAllAttachment(Integer id) {
		List<Attachment> list = attachmentUploadRepository.findAllAtt(id);
		List<AttachmentVo> vList = new ArrayList<>();
		AttachmentVo av = null;
		for(Attachment a : list) {
			av = new AttachmentVo(a.getId(), a.getOriginallyname(), a.getServername());
			vList.add(av);
		}
		return vList;
	}

	
}
