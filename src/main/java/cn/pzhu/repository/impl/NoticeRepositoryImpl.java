package cn.pzhu.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.NoticeTempVo;
import cn.pzhu.vo.NoticeVo;

@Component
public class NoticeRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Pagination search(SysUser user,NoticeVo nv,int page, int rows) {
		String dataSql = "select n from Notice n,SysUser u,Jurisdiction j,Institute i" +
	" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
		
		String countSql = "select count(n) from Notice n,SysUser u,Jurisdiction j,Institute i" +
				" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
		
		if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
			dataSql += " and i.name = ?1 and j.grade = ?2 and u.name = ?3 and u.loginname = ?4";
			countSql += " and i.name = ?1 and j.grade = ?2 and u.name = ?3 and u.loginname = ?4";
		}
		
		if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
			dataSql += " and n.title like ?5";
			countSql += " and n.title like ?5";
		}
		dataSql += " order by str_to_date(n.date,'%Y-%m-%d %h:%i:%s') desc";
		Query dataQuery = em.createQuery(dataSql);
        Query countQuery = em.createQuery(countSql);
        
        if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
        	dataQuery.setParameter(1, user.getInstitute().getName());
        	countQuery.setParameter(1, user.getInstitute().getName());
        	dataQuery.setParameter(2, user.getJurisdiction().getGrade());
        	countQuery.setParameter(2, user.getJurisdiction().getGrade());
        	dataQuery.setParameter(3, user.getName());
        	countQuery.setParameter(3, user.getName());
        	dataQuery.setParameter(4, user.getLoginname());
        	countQuery.setParameter(4, user.getLoginname());
		}
        
        if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
        	dataQuery.setParameter(5, "%"+nv.getTitle()+"%");
        	countQuery.setParameter(5, "%"+nv.getTitle()+"%");
		}
        
        long totalSize = (long) countQuery.getSingleResult();
        //List<Notice> data = dataQuery.getResultList();
        List<Notice> data = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
        Pagination pagination = new Pagination(totalSize, page, data);
        return pagination;
	}
	/**
	 * 用于系统管理员查看所有同级别的公告
	 * @param user
	 * @param nv
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pagination searchAll(SysUser user,NoticeVo nv,int page, int rows) {
		String dataSql = "select n from Notice n,SysUser u,Jurisdiction j,Institute i" +
	" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
		
		String countSql = "select count(n) from Notice n,SysUser u,Jurisdiction j,Institute i" +
				" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
		
		if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
			dataSql += " and (j.grade = 1 or j.grade = 2)";
			countSql += " and (j.grade = 1 or j.grade = 2)";
		}
		
		if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
			dataSql += " and n.title like ?1";
			countSql += " and n.title like ?1";
		}
		dataSql += " order by str_to_date(n.date,'%Y-%m-%d %T') DESC";
		
		Query dataQuery = em.createQuery(dataSql);
        Query countQuery = em.createQuery(countSql);
        
        if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
        	dataQuery.setParameter(1, "%"+nv.getTitle()+"%");
        	countQuery.setParameter(1, "%"+nv.getTitle()+"%");
		}
        
        long totalSize = (long) countQuery.getSingleResult();
        //List<Notice> data = dataQuery.getResultList();
        List<Notice> data = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
        Pagination pagination = new Pagination(totalSize, page, data);
        return pagination;
	}
	
	/**
	 * 得到普通会员所能查看到的公告
	 * @param user
	 * @param nv
	 * @param page
	 * @param rows
	 * @return
	 */
	/*public Pagination searchAllMember(SysUser user,NoticeVo nv,int page, int rows) {
		String hql = "(select n.id,title,date,type,content,u.id as userId,j.grade,i.name from Notice n,SysUser u,Jurisdiction j,Institute i" +
				" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id) as nuji";
		
		String dataSql = "select nuji from "+hql;
		
		String countSql = "select count(nuji) from "+hql;
		String dataSql = "select n from Notice n,SysUser u,Jurisdiction j,Institute i" +
	" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
		
		String countSql = "select count(n) from Notice n,SysUser u,Jurisdiction j,Institute i" +
				" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
		
		if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
			dataSql += " and i.name = ?1 and (j.grade = ?2 or j.grade = 1 or j.grade = 2)";
			countSql += " and i.name = ?1 and (j.grade = ?2 or j.grade = 1 or j.grade = 2)";
			dataSql += " where (nuji.grade = 1 or (nuji.name = ?1 and nuji.grade = ?2))";
			countSql += " where (nuji.grade = 1 or (nuji.name = ?1 and nuji.grade = ?2))";
		}
		
		if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
			dataSql += " and nuji.title like ?3";
			countSql += " and nuji.title like ?3";
		}
		
		Query dataQuery = em.createQuery(dataSql);
        Query countQuery = em.createQuery(countSql);
        
        if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
        	dataQuery.setParameter(1, user.getInstitute().getName());
        	countQuery.setParameter(1, user.getInstitute().getName());
        	dataQuery.setParameter(2, user.getJurisdiction().getGrade());
        	countQuery.setParameter(2, user.getJurisdiction().getGrade());
		}
        
        if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
        	dataQuery.setParameter(3, "%"+nv.getTitle()+"%");
        	countQuery.setParameter(3, "%"+nv.getTitle()+"%");
		}
        
        long totalSize = (long) countQuery.getSingleResult();
        //List<Notice> data = dataQuery.getResultList();
        List data = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
        
        Pagination pagination = new Pagination(totalSize, page, data);
        return pagination;
	}*/
	
	public Pagination searchAllMember(SysUser user,NoticeVo nv,int page, int rows) {
		String sql = "(select n.id,title,date,type,content,u.id as userid,j.grade,i.name,u.name as username" +
	             " from t_notice n,xt_user u,t_jurisdiction j,t_institute i where n.user_id = u.id and u.jurisdiction_id = j.id and  u.institute_id = i.id) as nuji";
		 Session session = em.unwrap(org.hibernate.Session.class);
		 
		 String dataSql = "select * from "+sql;
		 String countSql = "select count(*) from "+sql;
		 
		 if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
				dataSql += " where (nuji.grade = 1 OR (nuji.grade=2 and nuji.name = ?0))";
				countSql += " where (nuji.grade = 1 OR (nuji.grade=2 and nuji.name = ?0))";
			}
			
			if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
				dataSql += " and nuji.title like ?2";
				countSql += " and nuji.title like ?2";
			}
			
	     dataSql += " order by str_to_date(nuji.date,'%Y-%m-%d %T') DESC";
	     SQLQuery dataQuery = session.createSQLQuery(dataSql);
	     SQLQuery countQuery = session.createSQLQuery(countSql);
	     
	     if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
	        	dataQuery.setParameter(0, user.getInstitute().getName());
	        	countQuery.setParameter(0, user.getInstitute().getName());
		}
	        
	    if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
	        	dataQuery.setParameter(2, "%"+nv.getTitle()+"%");
	        	countQuery.setParameter(2, "%"+nv.getTitle()+"%");
	    }
	    
		
	     Long totalSize = new Long (countQuery.getSingleResult().toString());
	     List<Object[]> list = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
	     List<NoticeTempVo> nList = new ArrayList<>();
	     NoticeTempVo ntv = null;
	     for(Object[] obj : list) {
	    	 ntv = new NoticeTempVo(Integer.parseInt(obj[0].toString()), obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), Integer.parseInt(obj[5].toString()), obj[6].toString(), obj[7].toString(),obj[8].toString());
	    	 nList.add(ntv);
	     }
	     Pagination pagination = new Pagination(totalSize, page, nList);
	     return pagination;
	}

	/**
	 * 领导级别
	 * 未使用
	 * @param user
	 * @param nv
	 * @param page
	 * @param rows
	 * @return
	 */
	public Pagination searchAllLoader(SysUser user, NoticeVo nv, int page, int rows) {
		    String dataSql = "select n from Notice n,SysUser u,Jurisdiction j,Institute i" +
		        " where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
			
			String countSql = "select count(n) from Notice n,SysUser u,Jurisdiction j,Institute i" +
					" where n.sysUser.id = u.id and u.jurisdiction.id = j.id and u.institute.id = i.id";
			
			if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
				dataSql += " and i.name = ?1 and (j.grade = ?2 or j.grade = 1)";
				countSql += " and i.name = ?1 and (j.grade = ?2 or j.grade = 1)";
			}
			
			if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
				dataSql += " and n.title like ?3";
				countSql += " and n.title like ?3";
			}
			
			Query dataQuery = em.createQuery(dataSql);
	        Query countQuery = em.createQuery(countSql);
	        
	        if(user != null && user.getInstitute() != null && !StringUtils.containsWhitespace(user.getInstitute().getName()) && user.getJurisdiction() != null && !StringUtils.containsWhitespace(user.getJurisdiction().getGrade())) {
	        	dataQuery.setParameter(1, user.getInstitute().getName());
	        	countQuery.setParameter(1, user.getInstitute().getName());
	        	dataQuery.setParameter(2, user.getJurisdiction().getGrade());
	        	countQuery.setParameter(2, user.getJurisdiction().getGrade());
			}
	        
	        if(nv != null && !StringUtils.containsWhitespace(nv.getTitle())) {
	        	dataQuery.setParameter(3, "%"+nv.getTitle()+"%");
	        	countQuery.setParameter(3, "%"+nv.getTitle()+"%");
			}
	        
	        long totalSize = (long) countQuery.getSingleResult();
	        //List<Notice> data = dataQuery.getResultList();
	        List<Notice> data = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
	        Pagination pagination = new Pagination(totalSize, page, data);
	        return pagination;
	}
}
