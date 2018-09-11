package cn.pzhu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import cn.pzhu.interfaces.IActivityService;
import cn.pzhu.interfaces.ISignUpService;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.Resume;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.ActivityRepository;
import cn.pzhu.repository.SignUpRepository;
import cn.pzhu.repository.impl.ActivityRespositoryImpl;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ActivityTempVo;
import cn.pzhu.vo.ActivityVo;
import cn.pzhu.vo.NoticeVo;

@Service
public class ActivityServiceImpl implements IActivityService {
	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private ActivityRespositoryImpl activityRespositoryImpl;
	
	@Autowired
	private ISignUpService signUpService;
	
	@Autowired
	private SignUpRepository signUpRepository;

	@Override
	public Pagination getPagination(int page, int rows,SysUser user, ActivityVo av) {
		Pagination pages = null;
		if("1".equals(user.getJurisdiction().getGrade())){
			pages = activityRespositoryImpl.searchXTUser(user, av, page, rows);
			List<Activity> list = pages.getRows();
			List<ActivityVo> avList = new ArrayList<>();
			ActivityVo activityVo = null;
			for (Activity a : list) {
				String state = "否";
				if (a.getState().equals("1")) {
					state = "是";
				}
				activityVo = new ActivityVo(a.getId(), a.getName(), a.getEndTime(), a.getRemark(),
						a.getUser().getName(), state);
				avList.add(activityVo);
			}

			return new Pagination(pages.getTotal(), page, avList);
		} else {
			pages = activityRespositoryImpl.searchLoader(user, av, page, rows);
			List<ActivityTempVo> list = pages.getRows();
			List<ActivityVo> avList = new ArrayList<>();
			ActivityVo activityVo = null;
			for (ActivityTempVo a : list) {
				String state = "否";
				if (a.getState().equals("1")) {
					state = "是";
				}
				activityVo = new ActivityVo(a.getId(), a.getName(), a.getEndTime(), a.getRemark(),
						a.getUsername(), state);
				avList.add(activityVo);
			}

			return new Pagination(pages.getTotal(), page, avList);
		}
	}

	@Override
	public Pagination getPaginationOfEdit(int page, int rows, SysUser user, ActivityVo av) {
		Pagination pages = activityRespositoryImpl.search(user, av, page, rows);
		List<Activity> list = pages.getRows();
		List<ActivityVo> vList = new ArrayList<>();
		ActivityVo rv = null;
		Date date = null;
		for (Activity a : list) {
			String state = "否";
			if (a.getState().equals("1")) {
				state = "是";
			}
			rv = new ActivityVo(a.getId(), a.getName(), a.getEndTime(), a.getRemark(), a.getUser().getLoginname(),
					state);
			vList.add(rv);
		}
		return new Pagination(pages.getTotal(), page, vList);
	}

	@Override
	public void saveActivity(ActivityVo av, SysUser user) {
		String state = "0";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		String newDate = sdf.format(date);
		if (av.getEndTime().compareTo(newDate) < 0) {
			state = "1";
		}
		Activity activity = new Activity(av.getId(), user, av.getName(), av.getEndTime(), state, av.getRemark());
		activityRepository.save(activity);

	}

	@Override
	public ActivityVo queryActivityById(Integer id) {
		Activity act = activityRepository.getOne(id);
		ActivityVo av = new ActivityVo();
		av.setName(act.getName());
		av.setEndTime(act.getEndTime());
		av.setRemark(act.getRemark());
		return av;
	}

	@Override
	public void updateActivity(ActivityVo av) {
		Activity act = activityRepository.getOne(av.getId());
		act.setName(av.getName());
		act.setEndTime(av.getEndTime());
		act.setRemark(av.getRemark());
		activityRepository.save(act);
	}

	@Override
	public void removeActivitys(String ids) {
		
		try {
			List<Activity> activitys = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				Activity activity = activityRepository.getOne(Integer.parseInt(index[i]));
				activitys.add(activity);
				
				//得到signupactivity集合
				List<SignUpActivity> sList = signUpRepository.findAllByActivityId(Integer.parseInt(index[i]));
				String actId = "";
				if(sList.size() > 0) {
					for(SignUpActivity sua : sList) {
						actId += sua.getId() + ",";
					}
					signUpService.removeSignUpActivitys(actId.substring(0, actId.length()-1));
				}
			}
			activityRepository.deleteInBatch(activitys);
		} catch (Exception e) {
			throw new DBException("删除失败");
		}
	}

	@Override
	public List<ActivityTempVo> findActivity(String iname) {
		List<Object[]> list = activityRepository.findAction(iname);
		 List<ActivityTempVo> aList = new ArrayList<>();
		 ActivityTempVo atv = null;
	     for(Object[] obj : list) {
	    	 atv = new ActivityTempVo(Integer.parseInt(obj[0].toString()), obj[1].toString(), obj[2].toString(), obj[3].toString(), obj[4].toString(), obj[5].toString(), obj[6].toString(), obj[7].toString());
	    	 aList.add(atv);
	     }
		return aList;
	}

	@Override
	public Activity getActivityById(Integer id) {	
		return activityRepository.getOne(id);
	}
}
