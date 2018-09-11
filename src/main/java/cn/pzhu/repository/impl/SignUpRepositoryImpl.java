package cn.pzhu.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.NoticeVo;
import cn.pzhu.vo.SignUpVo;

@Component
public class SignUpRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Pagination searchMember(SysUser user, SignUpVo sv, int page, int rows) {
		String dataSql = "select s from SignUpActivity s ,SysUser u,Activity a where s.user.id = u.id and s.activity.id = a.id";

		String countSql = "select count(s) from SignUpActivity s ,SysUser u,Activity a where s.user.id = u.id and s.activity.id = a.id";

		if (user != null) {
			dataSql += " and u.loginname = ?1";
			countSql += " and u.loginname = ?1";
		}

		if (sv != null) {
			dataSql += " and a.name like ?2";
			countSql += " and a.name like ?2";
		}
		
		Query dataQuery = em.createQuery(dataSql);
		Query countQuery = em.createQuery(countSql);

		if (user != null) {
			dataQuery.setParameter(1, user.getLoginname());
			countQuery.setParameter(1, user.getLoginname());

		}

		if (sv != null) {
			dataQuery.setParameter(2, "%" + sv.getName() + "%");
			countQuery.setParameter(2, "%" + sv.getName() + "%");
		}

		long totalSize = (long) countQuery.getSingleResult();
		List<SignUpActivity> data = dataQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
		Pagination pagination = new Pagination(totalSize, page, data);
		return pagination;
	}
	
	public Pagination searchLoader(SysUser user, SignUpVo sv, int page, int rows) {
		String dataSql = "select s from SignUpActivity s ,SysUser u,Activity a,Institute i where s.user.id = u.id and s.activity.id = a.id and u.institute.id = i.id";

		String countSql = "select count(s) from SignUpActivity s ,SysUser u,Activity a,Institute i where s.user.id = u.id and s.activity.id = a.id and u.institute.id = i.id";

		if (user != null) {
			dataSql += " and i.name = ?1";
			countSql += " and i.name = ?1";
		}

		if (sv != null) {
			dataSql += " and a.name like ?2";
			countSql += " and a.name like ?2";
		}
		
		Query dataQuery = em.createQuery(dataSql);
		Query countQuery = em.createQuery(countSql);

		if (user != null) {
			dataQuery.setParameter(1, user.getInstitute().getName());
			countQuery.setParameter(1, user.getInstitute().getName());

		}

		if (sv != null) {
			dataQuery.setParameter(2, "%" + sv.getName() + "%");
			countQuery.setParameter(2, "%" + sv.getName() + "%");
		}

		long totalSize = (long) countQuery.getSingleResult();
		List<SignUpActivity> data = dataQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
		Pagination pagination = new Pagination(totalSize, page, data);
		return pagination;
	}
}
