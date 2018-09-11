package cn.pzhu.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import cn.pzhu.model.Pagination;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.StatisticsActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.vo.SignUpVo;

@Component
public class StatisticsActivityRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public Pagination searchLoader(SysUser user, StatisticsActivity sa, int page, int rows) {
		String dataSql = "select s from StatisticsActivity s";

		String countSql = "select count(s) from StatisticsActivity s";

		if (user != null) {
			dataSql += " where s.statisticsname = ?1";
			countSql += " where s.statisticsname = ?1";
		}

		if (sa != null) {
			dataSql += " and s.actionname like ?2";
			countSql += " and s.actionname like ?2";
		}
		
		Query dataQuery = em.createQuery(dataSql);
		Query countQuery = em.createQuery(countSql);

		if (user != null) {
			dataQuery.setParameter(1, user.getLoginname());
			countQuery.setParameter(1, user.getLoginname());

		}

		if (sa != null) {
			dataQuery.setParameter(2, "%" + sa.getActionname() + "%");
			countQuery.setParameter(2, "%" + sa.getActionname() + "%");
		}

		long totalSize = (long) countQuery.getSingleResult();
		List<StatisticsActivity> data = dataQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
		Pagination pagination = new Pagination(totalSize, page, data);
		return pagination;
	}
	
	public Pagination searchManager(SysUser user, StatisticsActivity sa, int page, int rows) {
		String dataSql = "select s from StatisticsActivity s";

		String countSql = "select count(s) from StatisticsActivity s";

		if (sa != null) {
			dataSql += " where s.actionname like ?1";
			countSql += " where s.actionname like ?1";
		}
		
		Query dataQuery = em.createQuery(dataSql);
		Query countQuery = em.createQuery(countSql);


		if (sa != null) {
			dataQuery.setParameter(1, "%" + sa.getActionname() + "%");
			countQuery.setParameter(1, "%" + sa.getActionname() + "%");
		}

		long totalSize = (long) countQuery.getSingleResult();
		List<StatisticsActivity> data = dataQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
		Pagination pagination = new Pagination(totalSize, page, data);
		return pagination;
	}
}
