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

import com.mysql.fabric.xmlrpc.base.Array;

import cn.pzhu.interfaces.IFamilyMemberService;
import cn.pzhu.model.FamilyMember;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.Resume;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.FamilyMemberRepository;
import cn.pzhu.repository.ResumeRepository;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.FamilyMemberVo;
import cn.pzhu.vo.ResumeVo;

@Service
@Transactional
public class FamilyMemberServiceImpl implements IFamilyMemberService {

	@Autowired
	private FamilyMemberRepository familyMemberRepository;

	@Override
	public Pagination getPagination(int page, int rows, SysUser user) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(page - 1, rows, sort);
		Specification<FamilyMember> spec = new Specification<FamilyMember>() {

			@Override
			public Predicate toPredicate(Root<FamilyMember> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate useridEq = null;
				if (user != null) {
					useridEq = cb.equal(root.get("user").get("id"), user.getId());
				}
				if (user != null) {
					query.where(useridEq);
				}
				return useridEq;
			}
		};

		Page<FamilyMember> pages = familyMemberRepository.findAll(spec,pageable);
		List<FamilyMember> list = pages.getContent();
		List<FamilyMemberVo> mList = new ArrayList<>();
		FamilyMemberVo menber = null;
		for (FamilyMember m : list) {
			menber = new FamilyMemberVo(m.getId(), m.getName(), m.getRelationship(), m.getPolicy(), m.getUnit(),
					m.getPosition());
			mList.add(menber);
		}
		return new Pagination(pages.getTotalElements(), page, mList);
	}

	@Override
	public void saveFamilyMember(FamilyMemberVo memberVo, SysUser user) {
		try {
			FamilyMember familyMember = new FamilyMember(memberVo.getId(), memberVo.getName(),
					memberVo.getRelationship(), memberVo.getPolicy(), memberVo.getUnit(), memberVo.getPosition(), user);
			familyMemberRepository.save(familyMember);
		} catch (DBException e) {
			throw new DBException("增加失败");
		}
	}

	@Override
	public void updateFamilyMember(FamilyMemberVo memberVo, SysUser user) {
		try {
			FamilyMember familyMember = new FamilyMember(memberVo.getId(), memberVo.getName(),
					memberVo.getRelationship(), memberVo.getPolicy(), memberVo.getUnit(), memberVo.getPosition(), user);
			familyMemberRepository.save(familyMember);
		} catch (DBException e) {
			throw new DBException("修改失败");
		}
	}

	@Override
	public FamilyMemberVo getFamilyMemberById(int id) {
		FamilyMember familyMember = familyMemberRepository.getOne(id);
		FamilyMemberVo familyMemberVo = new FamilyMemberVo(familyMember.getId(), familyMember.getName(),
				familyMember.getRelationship(), familyMember.getPolicy(), familyMember.getUnit(),
				familyMember.getPosition());
		return familyMemberVo;
	}

	/**
	 * 删除多条记录
	 */
	@Override
	public void removeFamilyMembers(String ids) {
		try {
			List<FamilyMember> mList = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				FamilyMember familyMember = familyMemberRepository.getOne(Integer.parseInt(index[i]));
				mList.add(familyMember);
			}
			familyMemberRepository.deleteInBatch(mList);
		} catch (Exception e) {
			throw new DBException("删除失败！");
		}

	}

}
