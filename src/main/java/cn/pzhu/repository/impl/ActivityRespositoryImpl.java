package cn.pzhu.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.ActivityTempVo;
import cn.pzhu.vo.ActivityVo;
import cn.pzhu.vo.NoticeTempVo;

@Component
public class ActivityRespositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Pagination search(SysUser user,ActivityVo av,int page, int rows) {
		String dataSql = "select a from SysUser u,Activity a where a.user.id = u.id";

		String countSql = "select count(a) from SysUser u,Activity a where a.user.id = u.id";
		
		if(user != null){
			dataSql += " and u.loginname = ?1";
			countSql += " and u.loginname = ?1";
		}
		
		if(av != null) {
			dataSql += " and a.name like ?2";
			countSql += " and a.name like ?2";
		}
		
		dataSql += " order by a.endTime desc,a.state asc";//排序
		Query dataQuery = em.createQuery(dataSql);
        Query countQuery = em.createQuery(countSql);
       

		if(user != null){
			dataQuery.setParameter(1, user.getLoginname());
			countQuery.setParameter(1, user.getLoginname());
		}
		
		if(av != null) {
			dataQuery.setParameter(2, "%"+av.getName()+"%");
			countQuery.setParameter(2, "%"+av.getName()+"%");
		}
        
        long totalSize = (long) countQuery.getSingleResult();
        List<Activity> data = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
        Pagination pagination = new Pagination(totalSize, page, data);
        return pagination;
	}
	
	@SuppressWarnings("unchecked")
	public Pagination searchXTUser(SysUser user,ActivityVo av,int page, int rows) {
		String dataSql = "select a from SysUser u,Activity a where a.user.id = u.id";

		String countSql = "select count(a) from SysUser u,Activity a where a.user.id = u.id";
		
		if(av != null) {
			dataSql += " and a.name like ?1";
			countSql += " and a.name like ?1";
		}
		
		dataSql += " order by a.endTime desc,a.state asc";//排序
		Query dataQuery = em.createQuery(dataSql);
        Query countQuery = em.createQuery(countSql);
		
		if(av != null) {
			dataQuery.setParameter(1, "%"+av.getName()+"%");
			countQuery.setParameter(1, "%"+av.getName()+"%");
		}
        
        long totalSize = (long) countQuery.getSingleResult();
        List<Activity> data = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
        Pagination pagination = new Pagination(totalSize, page, data);
        return pagination;
	}
	
	public Pagination searchLoader(SysUser user,ActivityVo av,int page, int rows) {
		
		String sql = "(select a.id,a.name,a.end_time,a.remark,a.state,u.name as username,j.grade,i.name as iname" +
	             " from activity a,xt_user u,t_jurisdiction j,t_institute i where a.user_id = u.id and u.jurisdiction_id = j.id and  u.institute_id = i.id) as auij";
		 Session session = em.unwrap(org.hibernate.Session.class);
		
		
		
		String dataSql = "select * from "+sql;
		String countSql = "select count(*) from "+sql;
		 
		if(user != null){
			dataSql += " where (auij.grade = 1 or (auij.grade = 2 and auij.iname =?1))";
			countSql += " where (auij.grade = 1 or (auij.grade = 2 and auij.iname =?1))";
		}
		
		if(av != null) {
			dataSql += " and auij.name like ?2";
			countSql += " and auij.name like ?2";
		}
		
		dataSql += " order by auij.end_time desc,auij.state asc";//排序
		 SQLQuery dataQuery = session.createSQLQuery(dataSql);
	     SQLQuery countQuery = session.createSQLQuery(countSql);
       

		if(user != null){
			dataQuery.setParameter(1, user.getInstitute().getName());
			countQuery.setParameter(1, user.getInstitute().getName());
		}
		
		if(av != null) {
			dataQuery.setParameter(2, "%"+av.getName()+"%");
			countQuery.setParameter(2, "%"+av.getName()+"%");
		}
        
		 Long totalSize = new Long (countQuery.getSingleResult().toString());
		 List<Object[]> list = dataQuery.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
		 
		 List<ActivityTempVo> aList = new ArrayList<>();
		 ActivityTempVo atv = null;
	     for(Object[] obj : list) {
	    	 atv = new ActivityTempVo(Integer.parseInt(obj[0].toString()), obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString());
	    	 aList.add(atv);
	     }
        Pagination pagination = new Pagination(totalSize, page, aList);
        return pagination;
	}
}
