package cn.pzhu.interfaces;

import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.NoticeVo;

public interface INoticeService {
    Pagination getPagination(NoticeVo notice, int page, int rows);

    NoticeVo getNoticeById(Integer id);

	void saveNotice(Notice notice);

	void removeNotices(String ids);

	Notice getNotice(Integer id);

	NoticeVo getNoticesById(Integer id);

	void updateNotice(Notice notice);

	Pagination getPagination(SysUser user, NoticeVo notice, int page, int rows);

	Pagination getPaginationAll(SysUser user, NoticeVo notice, int page, int rows);
}
