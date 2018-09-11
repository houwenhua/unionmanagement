package cn.pzhu.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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

import cn.pzhu.interfaces.IActivityService;
import cn.pzhu.interfaces.ISysUserService;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Institute;
import cn.pzhu.model.Jurisdiction;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.Resume;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.ActivityRepository;
import cn.pzhu.repository.FamilyMemberRepository;
import cn.pzhu.repository.InstituteRepository;
import cn.pzhu.repository.JurisdictionRepository;
import cn.pzhu.repository.NoticeRepository;
import cn.pzhu.repository.ResumeRepository;
import cn.pzhu.repository.SysUserRepository;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.SysUserVo;

@Service
@Transactional
public class SysUserServiceImpl implements ISysUserService {

	@Autowired
	private SysUserRepository userRepository;

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private NoticeServiceImpl noticeService;

	@Autowired
	private FamilyMemberRepository familyMemberRepository;

	@Autowired
	private ResumeRepository resumeRepository;

	@Autowired
	private JurisdictionRepository jurisdictionRepository;

	@Autowired
	private InstituteRepository instituteRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private IActivityService activityService;

	/**
	 * 加载用户信息
	 */
	@Override
	public Pagination getPagination(SysUserVo user, int page, int rows) {

		Specification<SysUser> specificaion = new Specification<SysUser>() {
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				Path<String> path = root.get("name");
				Predicate predicate = cb.like(path, "%" + user.getName() + "%");
				return predicate;
			}
		};

		List<SysUserVo> sysUsers = new ArrayList<SysUserVo>();
		SysUserVo sysUser = null;
		if (user == null) {
			Sort sort = new Sort(Sort.Direction.DESC, "institute.name")
					.and(new Sort(Sort.Direction.ASC, "jurisdiction.grade"));
			Pageable pageable = new PageRequest(page - 1, rows, sort);
			Page<SysUser> pages = userRepository.findAll(pageable);
			List<SysUser> users = pages.getContent();
			for (SysUser su : users) {
				/*sysUser = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
						su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
						su.getAddress());*/
				sysUser = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
						su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
						su.getAddress(),su.getSex(),su.getBirthday(),su.getPolicy(),su.getPosition());
				
				sysUsers.add(sysUser);

			}

			return new Pagination(pages.getTotalElements(), page, sysUsers);
		} else {
			Sort sort = new Sort(Sort.Direction.DESC, "institute.name")
					.and(new Sort(Sort.Direction.ASC, "jurisdiction.grade"));
			Pageable pageable = new PageRequest(page - 1, rows, sort);
			Page<SysUser> pages = userRepository.findAll(specificaion, pageable);
			List<SysUser> users = pages.getContent();
			for (SysUser su : users) {
				/*sysUser = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
						su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
						su.getAddress());*/
				sysUser = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
						su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
						su.getAddress(),su.getSex(),su.getBirthday(),su.getPolicy(),su.getPosition());
				
				sysUsers.add(sysUser);

			}
			return new Pagination(pages.getTotalElements(), page, sysUsers);
		}
	}

	/**
	 * 登录
	 */
	@Override
	public SysUser getLogin(SysUserVo user) {
		List<Object[]> list = userRepository.login(user.getLoginname(), user.getPassword(), user.getJurisdiction());
		SysUser sysUser = null;
		for (Object[] obj : list) {
			sysUser = (SysUser) obj[0];
		}

		return sysUser;
	}

	/**
	 * 添加用户信息页面查询权限集合
	 */
	@Override
	public List<Jurisdiction> findJurisdiction() {
		List<Jurisdiction> jurisdictions = jurisdictionRepository.findAll();
		return jurisdictions;
	}

	/**
	 * 添加用户信息页面查询学院集合
	 */
	@Override
	public List<Institute> findInstitute() {
		List<Institute> institutes = instituteRepository.findAll();
		return institutes;
	}

	/**
	 * 增加并保存用户
	 */
	@Override
	public void saveUser(SysUserVo so) {
		Institute institute = instituteRepository.getOne(Integer.parseInt(so.getInstitute()));
		Jurisdiction jurisdiction = jurisdictionRepository.findJurisdictionByGrade(so.getJurisdiction());
		SysUser user = new SysUser(so.getName(), so.getLoginname(), so.getPassword(), so.getEmail(), so.getPhone(),
				jurisdiction, institute, so.getAddress());
		userRepository.save(user);
	}

	/**
	 * 删除用户 级联删除他：发布的公告，个人简历，家庭成员,最后增加删除活动和报名表
	 */
	@Override
	public void removeUser(String ids) {
		try {
			List<SysUser> users = new ArrayList<>();
			String[] index = ids.split(",");

			List<Notice> list = new ArrayList<>();
			String noticeIds = "";
			for (int i = 0; i < index.length; i++) {
				SysUser user = userRepository.getOne(Integer.parseInt(index[i]));

				// 判断是否是系统管理员
				if (!user.getJurisdiction().getGrade().equals("1")) {
					users.add(user);
				}

				// 删除活动
				List<Activity> aList = activityRepository.findAllByUserId(Integer.parseInt(index[i]));
				String actID = "";
				if (aList.size() > 0 && aList != null) {
					for (Activity a : aList) {
						actID += a.getId() + ",";
					}
					activityService.removeActivitys(actID);
				}

				// 获得user下面的公告的所有值
				list = noticeRepository.findNoticeByUserId(Integer.parseInt(index[i]));
			}
			if (list.size() > 0 && list != null) {
				for (Notice n : list) {
					noticeIds += n.getId().toString() + ",";
				}
				noticeIds = noticeIds.substring(0, noticeIds.length() - 1);
				noticeService.removeNotices(noticeIds);
			}

			resumeRepository.deleteResumeByUserId(ids);
			familyMemberRepository.deleteFamilyMemberByUserId(ids);
			userRepository.deleteInBatch(users);
		} catch (Exception e) {
			throw new DBException("删除失败");
		}
	}

	@Override
	public void updatePwd(String pwd, SysUser user) {
		user.setPassword(pwd);
		userRepository.save(user);
	}

	@Override
	public SysUserVo getUser(Integer id) {
		SysUser su = userRepository.getOne(id);
		SysUserVo suv = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
				su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
				su.getAddress());
		return suv;
	}

	/**
	 * 修改用户成功后，更新数据库。
	 */
	@Override
	public void updateUsers(SysUserVo su) {
		Institute institute = instituteRepository.getOne(Integer.parseInt(su.getInstitute()));
		Jurisdiction jurisdiction = jurisdictionRepository.findJurisdictionByGrade(su.getJurisdiction());
		SysUser user = userRepository.getOne(su.getId());
		user.setId(su.getId());
		user.setName(su.getName());
		user.setLoginname(su.getLoginname());
		user.setInstitute(institute);
		user.setJurisdiction(jurisdiction);
		//user.setEmail(su.getEmail());
		user.setPassword(su.getPassword());// 修改后的密码
		//user.setAddress(su.getAddress());
		userRepository.save(user);

	}

	@Override
	public List<SysUser> findAllUsers() {
		List<SysUser> users = userRepository.findAll();
		return users;
	}

	@Override
	public SysUser getUserById(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public List<SysUser> findAllUsersExceptOwne() {
		List<SysUser> users = userRepository.findAll();
		return users;
	}

	@Override
	public SysUserVo getSysUser(Integer id) {
		SysUser su = userRepository.getOne(id);
		
		SysUserVo suv = new SysUserVo(su.getId(), su.getName(), su.getLoginname(), su.getPassword(), su.getEmail(),
				su.getJurisdiction().getName(), su.getState(), su.getPhone(), su.getInstitute().getName(),
				su.getAddress(),su.getSex(),su.getBirthday(),su.getPolicy(),su.getPosition());
		 
		return suv;
	}

	@Override
	public void updateSysUsers(SysUserVo su) {
		SysUser user = userRepository.getOne(su.getId());
		user.setName(su.getName());
		user.setEmail(su.getEmail());
		user.setAddress(su.getAddress());
		user.setSex(su.getSex());
		user.setBirthday(su.getBirthday());
		user.setPhone(su.getPhone());
		user.setEmail(su.getEmail());
		user.setPolicy(su.getPolicy());
		user.setPosition(su.getPosition());
		userRepository.save(user);
	}

}
