package cn.pzhu.controller;

import cn.pzhu.interfaces.INoticeService;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.utils.AjaxJson;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.NoticeVo;
import cn.pzhu.vo.ResumeVo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NoticeController")
public class NoticController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping("/paginationQuery")
    public Pagination paginationQuery(int page,int rows,String title,HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("user");
    	NoticeVo notice = null;
        if(title != null && !"".equals(title)) {
            notice = new NoticeVo();
            notice.setTitle(title);
        }
        Pagination pagination = noticeService.getPagination(user,notice , page , rows);
        return pagination;
    }
    
    /**
     * 获得包括系统管理员的公告
     * @param page
     * @param rows
     * @param title
     * @param session
     * @return
     */
    @RequestMapping("/paginationQueryAll")
    public Pagination paginationQueryAll(int page,int rows,String title,HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("user");
    	NoticeVo notice = null;
        if(title != null && !"".equals(title)) {
            notice = new NoticeVo();
            notice.setTitle(title);
        }
        Pagination pagination = noticeService.getPaginationAll(user,notice , page , rows);
        return pagination;
    }

    @RequestMapping("/queryNotice")
    public NoticeVo queryNotice(Integer id){
        NoticeVo nv = noticeService.getNoticeById(id);
        return nv;
    }
    
    @RequestMapping("/queryNotices")
    public NoticeVo queryNotices(Integer id){
        NoticeVo nv = noticeService.getNoticesById(id);
        return nv;
    }
    
    @RequestMapping("/addNotice")
    public AjaxJson addNotice(String title, String mcontent, String type, HttpSession session){
        SysUser user = (SysUser) session.getAttribute("user");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        AjaxJson result = new AjaxJson();
        result.setSuccess(false);
        
        Notice notice = new Notice(user, title, date, type,mcontent);
        try{
        	noticeService.saveNotice(notice);
        	result.setSuccess(true);
        	result.setMsg("增加成功");
        } catch(DBException e){
        	e.printStackTrace();
        	result.setMsg("增加失败");
        }
        return result;
        
    }
   
    @RequestMapping("/remove")
	public AjaxJson remove(String ids) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			noticeService.removeNotices(ids);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("删除失败");
		}
		return result;
	}
    
    @RequestMapping("/update")
	public AjaxJson update(Integer id, String title, String mcontent, String type) {
		AjaxJson result = new AjaxJson();
		result.setSuccess(false);
		try{
			Notice notice = noticeService.getNotice(id);
			notice.setTitle(title);
			notice.setContent(mcontent);
			notice.setType(type);
			noticeService.updateNotice(notice);
			result.setSuccess(true);
			result.setMsg("修改成功");
		} catch(DBException e){
			e.printStackTrace();
			result.setMsg("修改失败");
		}
		return result;
	}
 
}
