package cn.pzhu.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.pzhu.interfaces.IAttachmentUploadService;
import cn.pzhu.interfaces.INoticeService;
import cn.pzhu.model.Attachment;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.AttachmentVo;

@RestController
@RequestMapping("/AttachmentUploadController")
public class AttachmentUploadController {

	@Autowired
	private IAttachmentUploadService attachmentService;
	
	@RequestMapping("/paginationQuery")
	public Pagination paginationQuery(int page, int rows, int id) {
		Pagination pagination = attachmentService.getPagination(page, rows, id);
		return pagination;
	}
	
	@RequestMapping("/paginationQueryActivity")
	public Pagination paginationQueryActivity(int page, int rows, int id) {
		Pagination pagination = attachmentService.getPaginationActivity(page, rows, id);
		return pagination;
	}
	
	@RequestMapping("/paginationQueryStatisticsActivity")
	public Pagination paginationQueryStatisticsActivity(int page, int rows, int id) {
		Pagination pagination = attachmentService.getPaginationStatisticsActivity(page, rows, id);
		return pagination;
	}
	
	@RequestMapping("/upload")
	public AjaxJson upload(MultipartFile file, Integer id){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			attachmentService.saveAndUpload(file,id);
			result.setSuccess(true);
			result.setMsg("上传成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("上传失败");
		}
		return result;
	}
	
	@RequestMapping("/uploadActivity")
	public AjaxJson uploadActivity(MultipartFile file, Integer id){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			attachmentService.saveAndUploadActivity(file,id);
			result.setSuccess(true);
			result.setMsg("上传成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("上传失败");
		}
		return result;
	}
	
	@RequestMapping("/uploadStaticsActivity")
	public AjaxJson uploadStaticsActivity(MultipartFile file, Integer id){
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			attachmentService.saveAndUploadStatisticsActivity(file,id);
			result.setSuccess(true);
			result.setMsg("上传成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("上传失败");
		}
		return result;
	}
	
	@RequestMapping("/remove")
	public AjaxJson remove(String ids) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			attachmentService.removeAttachments(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/removeActivity")
	public AjaxJson removeActivity(String ids) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			attachmentService.removeAttachmentsActivity(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	
	@RequestMapping("/removeStatisticsActivity")
	public AjaxJson removeStatisticsActivity(String ids) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			attachmentService.removeAttachmentsStatisticsActivity(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
	
	/**
	 * 
	 */
	
	@RequestMapping("/queryAttachments")
	public List<AttachmentVo> queryAttachments(Integer id) {
		List<AttachmentVo> list = attachmentService.findAllAttachment(id);
		return list;
	}
	
	/**
	 * 文件下载
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/download")
	public void download(@RequestParam("name") String name, HttpServletResponse response) throws UnsupportedEncodingException {
		String filePath = "D:/uploadfile";
        File file = new File(filePath + "/" + name);
        if(file.exists()){
        	
        	response.setHeader("content-type", "application/octet-stream");
        	response.setContentType("application/octet-stream;charset=utf-8");
        	//解决文件名中文乱码
        	name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        	response.setHeader("Content-Disposition", "attachment; filename=\"" +name+ "\"; filename*=utf-8''" + name);
        	
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
                
                //解决文件过大下载出错
                buffer = new byte[bis.available()];
                
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 活动文件下载
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/downloadAtt")
	public void downloadAtt(@RequestParam("name") String name, HttpServletResponse response) throws UnsupportedEncodingException {
		String filePath = "D:/uploadfile/activity";
        File file = new File(filePath + "/" + name);
        
        if(file.exists()){
        	
        	response.setHeader("content-type", "application/octet-stream");
        	response.setContentType("application/octet-stream;charset=utf-8");
        	//解决文件名中文乱码
        	name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        	response.setHeader("Content-Disposition", "attachment; filename=\"" +name+ "\"; filename*=utf-8''" + name);
        	
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
              //解决文件过大下载出错
                buffer = new byte[bis.available()];
                
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	@RequestMapping("/downloadAct")
	public void downloadAct(@RequestParam("name") String name, HttpServletResponse response) throws UnsupportedEncodingException {
		String filePath = "D:/uploadfile/statisticsactivity";
        File file = new File(filePath + "/" + name);
        
        if(file.exists()){
        	
        	response.setHeader("content-type", "application/octet-stream");
        	response.setContentType("application/octet-stream;charset=utf-8");
        	//解决文件名中文乱码
        	name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        	response.setHeader("Content-Disposition", "attachment; filename=\"" +name+ "\"; filename*=utf-8''" + name);
        	
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file); 
                bis = new BufferedInputStream(fis);
              //解决文件过大下载出错
                buffer = new byte[bis.available()];
                
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
}
