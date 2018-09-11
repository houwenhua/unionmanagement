package cn.pzhu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.pzhu.interfaces.ITreeService;
import cn.pzhu.model.SysUser;
import cn.pzhu.model.Tree;
import cn.pzhu.repository.TreeRepository;
import cn.pzhu.vo.*;

@Service
@Transactional
public class TreeServiceImpl implements ITreeService {

	@Autowired
	private TreeRepository treeRepository;
	
	
	@Override
	public List<Tree> getTreeList(Integer treeid) {
		String hql = "from Tree where tid="+treeid;
		List<Tree> list = treeRepository.findAllTree(treeid);
		return list;
	}

    /**
     * 得到树的节点集合
     */
	@Override
	public List<TreeVo> getTree(int treeid, SysUser suv) {
		List<Tree> list = treeRepository.loginTree(treeid,suv.getJurisdiction().getName());
		TreeVo tv = null;
		List<TreeVo> tvList = new ArrayList<>();
		
		for(Tree t: list){
			tv = new TreeVo(t.getId(),t.getText(),t.getState(),t.getIconCls(),t.getUrl(),t.getTid());
			tvList.add(tv);
		}
		return tvList;
	}
}
