package cn.pzhu.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.pzhu.interfaces.IAttachmentUploadService;
import cn.pzhu.interfaces.ISignUpService;
import cn.pzhu.model.Activity;
import cn.pzhu.model.Attachment;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.ActivityRepository;
import cn.pzhu.repository.AttachmentUploadRepository;
import cn.pzhu.repository.SignUpRepository;
import cn.pzhu.repository.impl.SignUpRepositoryImpl;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.NoticeVo;
import cn.pzhu.vo.SignUpVo;

@Service
public class SignUpServiceImpl implements ISignUpService {

	@Autowired
	private SignUpRepository signUpRepository;
	
	@Autowired
	private SignUpRepositoryImpl signUpRepositoryImpl;
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private AttachmentUploadRepository attachmentUploadRepository;
	
	@Autowired
	private IAttachmentUploadService attachmentService;
	


	@Override
	public Pagination getPagination(int page, int rows, SysUser user, SignUpVo svo) {

		Pagination pages = signUpRepositoryImpl.searchMember(user, svo, page, rows);

		List<SignUpActivity> list = pages.getRows();
		List<SignUpVo> sList = new ArrayList<>();
		SignUpVo sv = null;
		String flag = "未审核";
		for (SignUpActivity s : list) {
			flag = "未审核";
			if(s.getCheckactivity().equals("1")){
				flag = "未通过";
			} else if(s.getCheckactivity().equals("2")){
				flag = "审核通过";
			}
			sv = new SignUpVo(s.getId(),s.getActivity().getName(),s.getSignupdate(),s.getUser().getName(),flag);
			sList.add(sv);
		}

		return new Pagination(pages.getTotal(), page, sList);

	}
	
	@Override
	public Pagination getPaginationActivity(int page, int rows, SysUser user, SignUpVo svo) {
		Pagination pages = signUpRepositoryImpl.searchLoader(user, svo, page, rows);

		List<SignUpActivity> list = pages.getRows();
		List<SignUpVo> sList = new ArrayList<>();
		SignUpVo sv = null;
		String flag = "未审核";
		for (SignUpActivity s : list) {
			flag = "未审核";
			if(s.getCheckactivity().equals("1")){
				flag = "未通过";
			} else if(s.getCheckactivity().equals("2")){
				flag = "审核通过";
			}
			sv = new SignUpVo(s.getId(),s.getActivity().getName(),s.getSignupdate(),s.getUser().getName(),flag);
			sList.add(sv);
		}

		return new Pagination(pages.getTotal(), page, sList);
	}

	@Override
	public void saveSignUp(SignUpActivity sua) {
		signUpRepository.save(sua);
	}

	@Override
	public void removeSignUpActivitys(String ids) {
		
		try {
			List<SignUpActivity> signups = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				SignUpActivity signup = signUpRepository.findBySignUpId(Integer.parseInt(index[i]));
				signups.add(signup);
				
				String attids = "";
				for(Attachment a :signup.getAttachments()){
					attids += a.getId() + ",";
				}
				if(!attids.equals("")){
					attachmentService.removeAttachmentsActivity(attids.substring(0, attids.length()-1));
				}

			}
			signUpRepository.deleteInBatch(signups);
		} catch (Exception e) {
			throw new DBException("删除失败");
		}
		
	}

	@Override
	public void updateSignUp(Integer id, Integer name) {
		SignUpActivity sua = signUpRepository.getOne(id);
		Activity activity = activityRepository.getOne(name);
		sua.setActivity(activity);
		signUpRepository.save(sua);
	}

	@Override
	public SignUpActivity getSignUpByActivityId(Integer actid,String loginname) {
		SignUpActivity s = signUpRepository.findByActId(actid,loginname);
		return s;
	}

	@Override
	public void checkSignUp(Integer id, String checkactivity) {
		SignUpActivity sua = signUpRepository.findBySignUpId(id);
		sua.setCheckactivity(checkactivity);
		signUpRepository.save(sua);
	}

}
