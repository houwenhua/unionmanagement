package cn.pzhu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pzhu.interfaces.IAttachmentUploadService;
import cn.pzhu.interfaces.IStatisticsActivityService;
import cn.pzhu.model.Attachment;
import cn.pzhu.model.Notice;
import cn.pzhu.model.Pagination;
import cn.pzhu.model.SignUpActivity;
import cn.pzhu.model.StatisticsActivity;
import cn.pzhu.model.SysUser;
import cn.pzhu.repository.StatisticsActivityRepository;
import cn.pzhu.repository.impl.StatisticsActivityRepositoryImpl;
import cn.pzhu.utils.DBException;
import cn.pzhu.vo.ActivityVo;
import cn.pzhu.vo.NoticeVo;
import cn.pzhu.vo.StatisticsActivityVo;

@Service
public class StatisticsServiceImpl implements IStatisticsActivityService {

	@Autowired 
	private StatisticsActivityRepository statisticsRepository;
	
	@Autowired 
	private StatisticsActivityRepositoryImpl statisticsImpl;
	
	@Autowired
	private IAttachmentUploadService attachmentService;
	
	@Override
	public Pagination getPagination(int page, int rows,SysUser user, StatisticsActivity sa) {
		if("1".equals(user.getJurisdiction().getGrade())) {
			Pagination pages = statisticsImpl.searchManager(user, sa, page, rows);
			List<StatisticsActivity> list = pages.getRows();
			List<StatisticsActivityVo> saList = new ArrayList<>();
			StatisticsActivityVo sav = null;
			for (StatisticsActivity sat : list) {
				sav = new StatisticsActivityVo(sat.getId(), sat.getActionname(), sat.getInstitute(), sat.getStatisticsname(), sat.getNumber());
				saList.add(sav);
			}
			return new Pagination(pages.getTotal(), page, saList);
		}else {
			Pagination pages = statisticsImpl.searchLoader(user, sa, page, rows);
			List<StatisticsActivity> list = pages.getRows();
			List<StatisticsActivityVo> saList = new ArrayList<>();
			StatisticsActivityVo sav = null;
			for (StatisticsActivity sat : list) {
				sav = new StatisticsActivityVo(sat.getId(), sat.getActionname(), sat.getInstitute(), sat.getStatisticsname(), sat.getNumber());
				saList.add(sav);
			}
			return new Pagination(pages.getTotal(), page, saList);
		}
	}

	@Override
	public void saveStatistics(StatisticsActivity sa) {
		statisticsRepository.save(sa);
	}

	@Override
	public void updateStatistics(StatisticsActivity sa) {
		statisticsRepository.save(sa);
	}

	@Override
	public StatisticsActivity getStatisticsActivity(Integer id) {
		return statisticsRepository.findById(id).get();
	}

	@Override
	public void removeStatisticsActivity(String ids) {
		try {
			List<StatisticsActivity> sas = new ArrayList<>();
			String[] index = ids.split(",");
			for (int i = 0; i < index.length; i++) {
				StatisticsActivity sa = statisticsRepository.findById(Integer.parseInt(index[i])).get();
				sas.add(sa);
				
				String attids = "";
				for(Attachment a :sa.getAttachments()){
					attids += a.getId() + ",";
				}
				if(!attids.equals("")){
					attachmentService.removeAttachmentsStatisticsActivity(attids.substring(0, attids.length()-1));
				}

			}
			statisticsRepository.deleteInBatch(sas);
		} catch (Exception e) {
			throw new DBException("删除失败");
		}
	}
}
