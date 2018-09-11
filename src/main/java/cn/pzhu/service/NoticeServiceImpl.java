package cn.pzhu.service;

import cn.pzhu.interfaces.INoticeService;
import cn.pzhu.model.Attachment;
import cn.pzhu.model.Institute;
import cn.pzhu.model.Jurisdiction;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.Resume;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.AttachmentUploadRepository;
import cn.pzhu.repository.NoticeRepository;
import cn.pzhu.repository.impl.NoticeRepositoryImpl;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.NoticeTempVo;
import cn.pzhu.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements INoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeRepositoryImpl noticeRepositoryImpl;

	@Autowired
	private AttachmentUploadRepository attachmentUploadRepository;

	@Override
	public Pagination getPagination(NoticeVo notice, int page, int rows) {
		Specification<Notice> specification = new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Path<String> path = root.get("title");
				Predicate predicate = criteriaBuilder.like(path, "%" + notice.getTitle() + "%");
				return predicate;
			}
		};

		Sort sort = new Sort(Sort.Direction.DESC, "date");
		Pageable pageable = new PageRequest(page - 1, rows, sort);

		if (notice == null) {
			Page<Notice> pages = noticeRepository.findAll(pageable);

			List<Notice> list = pages.getContent();
			List<NoticeVo> nvList = new ArrayList<>();
			NoticeVo nv = null;
			for (Notice n : list) {
				nv = new NoticeVo(n.getId(), n.getTitle(), n.getType(), n.getDate(), n.getSysUser().getName());
				nvList.add(nv);
			}

			return new Pagination(pages.getTotalElements(), page, nvList);
		} else {
			Page<Notice> pages = noticeRepository.findAll(specification, pageable);

			List<Notice> list = pages.getContent();
			List<NoticeVo> nvList = new ArrayList<>();
			NoticeVo nv = null;
			for (Notice n : list) {
				nv = new NoticeVo(n.getId(), n.getTitle(), n.getType(), n.getDate(), n.getSysUser().getName());
				nvList.add(nv);
			}
			return new Pagination(pages.getTotalElements(), page, nvList);
		}
	}
	
	/**
	 * 重载getPagination方法，实现根据权限查询相应的公告
	 */
	@Override
	public Pagination getPagination(SysUser user, NoticeVo notice, int page, int rows) {
		/*Sort sort = new Sort(Sort.Direction.DESC, "date");
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		Page<Notice> pages = noticeRepository.findAll(new Specification<Notice>() {
			@Override
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {*/
				/*
				//根据主题判断
				Predicate titleNameLike = null;
				if(notice != null && !StringUtils.isEmpty(notice.getTitle())) {
					Path<String> pathTitle= root.get("title");
					titleNameLike = cb.like(pathTitle, "%"+notice.getTitle()+"%");
				} 
				
				//根据学院判断
				Predicate instituteName = null;
				if(user != null && user.getInstitute() != null && !StringUtils.isEmpty(user.getInstitute().getName())) {
					
					instituteName = cb.equal(root.<String>get("institute").<String>get("name"), user.getInstitute().getName());
				}
				
				//权限判断 判断的是权限等级grade
				Predicate jurisdictName = null;
				if(user != null && user.getJurisdiction() != null && !StringUtils.isEmpty(user.getJurisdiction().getGrade())){
					jurisdictName = cb.equal(root.<String> get("jurisdiction").<String> get("grade"), user.getJurisdiction().getGrade());
				}
				
				//组装判断语句
				if(titleNameLike != null) {
					query.where(titleNameLike);
				}
				if(instituteName != null) {
					query.where(instituteName);
				}
				if(jurisdictName != null) {
					query.where(jurisdictName);
				}*/
				
				//因为同时关联了多个对象而且都是以User为主，上面的不行
				//方法二
				/*Join<Notice,SysUser> userJoin = root.join("notices",JoinType.LEFT);
				Join<SysUser,Institute> instituteJoin =root.join("SysUser",JoinType.LEFT);
				Join<SysUser,Jurisdiction> jurJoin = root.join("sysUsers",JoinType.LEFT);
				Predicate p1 = cb.like(userJoin.get("title"), "%"+notice.getTitle()+"%");
                Predicate p2 = cb.equal(instituteJoin.get("name"), user.getInstitute().getName());
                Predicate p3 = cb.like(jurJoin.get("grade"), user.getJurisdiction().getGrade());
                
                if(notice != null && !StringUtils.isEmpty(notice.getTitle())) {
                	query.where(p1,p2,p3);
				} else {
					query.where(p2,p3);
				}*/
				//失败写不出来
				//尝试第三种，通过一个实现类，实现SQL拼装
                
				/*return null;
			}
		}, pageable);*/
		
		
		/*List<Notice> list = pages.getContent();
		List<NoticeVo> nvList = new ArrayList<>();
		NoticeVo nv = null;
		for (Notice n : list) {
			nv = new NoticeVo(n.getId(), n.getTitle(), n.getType(), n.getDate(), n.getSysUser().getName());
			nvList.add(nv);
		}
		return new Pagination(pages.getTotalElements(), page, nvList);*/
		Pagination pages = noticeRepositoryImpl.search(user, notice, page, rows);
		List<Notice> list = pages.getRows();
		List<NoticeVo> nvList = new ArrayList<>();
		NoticeVo nv = null;
		for (Notice n : list) {
			nv = new NoticeVo(n.getId(), n.getTitle(), n.getType(), n.getDate(), n.getSysUser().getName());
			nvList.add(nv);
		}
		return new Pagination(pages.getTotal(), page, nvList);
	}
	
	@Override
	public Pagination getPaginationAll(SysUser user, NoticeVo notice, int page, int rows) {
		Pagination pages = null;
		if(user.getJurisdiction().getGrade().equals("3")){
			pages = noticeRepositoryImpl.searchAllMember(user, notice, page, rows);
		}else if(user.getJurisdiction().getGrade().equals("2")) {
			pages = noticeRepositoryImpl.searchAllMember(user, notice, page, rows);
		}else {
			pages = noticeRepositoryImpl.searchAll(user, notice, page, rows);
			List<Notice> list = pages.getRows();
			List<NoticeVo> nvList = new ArrayList<>();
			NoticeVo nv = null;
			for (Notice n : list) {
				nv = new NoticeVo(n.getId(), n.getTitle(), n.getType(), n.getDate(), n.getSysUser().getName());
				nvList.add(nv);
			}
			return new Pagination(pages.getTotal(), page, nvList);
		}
		
		List<NoticeTempVo> list = pages.getRows();
		List<NoticeVo> nvList = new ArrayList<>();
		NoticeVo nv = null;
		for (NoticeTempVo n : list) {
			nv = new NoticeVo(n.getId(), n.getTitle(), n.getType(), n.getDate(), n.getUsername());
			nvList.add(nv);
		}
		return new Pagination(pages.getTotal(), page, nvList);
	}

	
	
	@Override
	public void saveNotice(Notice notice) {
		try {
			noticeRepository.save(notice);
		} catch (Exception e) {
			throw new DBException("增加失败");
		}
	}

	@Override
	public void removeNotices(String ids) {
		try {
			List<Notice> notices = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				Notice notice = noticeRepository.getOne(Integer.parseInt(index[i]));
				notices.add(notice);

				// 删除附件
				List<Attachment> list = attachmentUploadRepository.findAllAtt(Integer.parseInt(index[i]));
				for (Attachment a : list) {
					// 删除硬盘下的文件
					String servername = a.getServername();
					String path = "D:/uploadfile";
					File dest = new File(path + "/" + servername);
					dest.delete();
				}
				attachmentUploadRepository.deleteAttachment(Integer.parseInt(index[i]));

			}
			noticeRepository.deleteInBatch(notices);
		} catch (Exception e) {
			throw new DBException("删除失败");
		}

	}

	@Override
	public Notice getNotice(Integer id) {
		return noticeRepository.getOne(id);
	}

	@Override
	public NoticeVo getNoticeById(Integer id) {
		Notice notice = noticeRepository.getOne(id);
		NoticeVo vo = new NoticeVo(id, notice.getTitle(), notice.getType(), notice.getDate(),
				notice.getSysUser().getLoginname(),notice.getContent());
		return vo;
	}

	@Override
	public NoticeVo getNoticesById(Integer id) {
		Notice notice = noticeRepository.getOne(id);
		NoticeVo nv = new NoticeVo(notice.getId(), notice.getTitle(), notice.getType(), notice.getContent());
		return nv;
	}

	@Override
	public void updateNotice(Notice notice) {
		noticeRepository.save(notice);
	}


}
