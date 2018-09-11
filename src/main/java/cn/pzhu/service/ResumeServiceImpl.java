package cn.pzhu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pzhu.interfaces.IResumeService;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.Resume;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.ResumeRepository;
import cn.pzhu.repository.SysUserRepository;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ResumeVo;
import cn.pzhu.vo.SysUserVo;

@Service
@Transactional
public class ResumeServiceImpl implements IResumeService {

	@Autowired
	private ResumeRepository resumeRepository;
	
	@Autowired
	private SysUserRepository sysUserRepository;
	
	@Override
	public Pagination getPagination(int page, int rows,SysUser user) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		Specification<Resume> spec = new Specification<Resume>() {
			
			@Override
			public Predicate toPredicate(Root<Resume> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate useridEq = null;
				if(user != null) {
					useridEq = cb.equal(root.get("user").get("id"), user.getId());
				}
				if(user != null) {
					query.where(useridEq);
				}
				return useridEq;
			}
		};
		
		
		Page<Resume> pages = resumeRepository.findAll(spec,pageable);
		List<Resume> list = pages.getContent();
		List<ResumeVo> vList = new ArrayList<>();
		ResumeVo rv = null;
		for(Resume r : list){
		   rv = new ResumeVo(r.getId(),r.getStartdate(),r.getEnddate(),r.getDescription());
		   vList.add(rv);
		}
		return new Pagination(pages.getTotalElements(), page, vList);
	}
	
	@Override
	public ResumeVo getResumeById(int id) {
		Resume resume = resumeRepository.getOne(id);
		ResumeVo rv = new ResumeVo(resume.getId(), resume.getStartdate(), resume.getEnddate(), resume.getDescription());
		return rv;
	}
	
	@Override
	public void updateResume(ResumeVo rv,SysUser su) {
		try{
			Resume resume = new Resume(rv.getId(), rv.getStartdate(), rv.getEnddate(), rv.getDescription(), su);
			resumeRepository.save(resume);
		} catch(Exception e) {
			throw new DBException("修改失败");
		}
	}
	
	@Override
	public void saveResume(ResumeVo resume,SysUser user) {
		try{
			Resume r = new Resume(resume.getId(),resume.getStartdate(),resume.getEnddate(),resume.getDescription(),user);
		    resumeRepository.save(r);
		} catch(Exception e) {
			throw new DBException("增加失败");
		}
	}
	
	@Override
	public void removeResumes(String ids) {
		try{
			List<Resume> resumes = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				Resume resume = resumeRepository.getOne(Integer.parseInt(index[i]));
				resumes.add(resume);
			}
			resumeRepository.deleteInBatch(resumes);
		} catch(Exception e) {
			throw new DBException("删除失败");
		}
		
	}

}
