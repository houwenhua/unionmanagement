package cn.pzhu.interfaces;

import java.util.List;

import cn.pzhu.model.SysUser;
import cn.pzhu.model.Tree;
import cn.pzhu.vo.SysUserVo;
import cn.pzhu.vo.TreeVo;

public interface ITreeService {

	List<Tree> getTreeList(Integer treeid);

	List<TreeVo> getTree(int treeid, SysUser suv);

}
