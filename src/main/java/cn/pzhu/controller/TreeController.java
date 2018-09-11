package cn.pzhu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.pzhu.interfaces.ITreeService;
import cn.pzhu.model.SysUser;
import cn.pzhu.model.Tree;
import cn.pzhu.vo.SysUserVo;
import cn.pzhu.vo.TreeVo;

@RestController
@RequestMapping("/treeController")
public class TreeController {
	
	@Autowired
	private ITreeService treeService;
	
	@RequestMapping("/tree") 
	public List<TreeVo> tree(Integer id,HttpSession session) {
		SysUser suv = (SysUser) session.getAttribute("user");
		int treeid = 0;//默认节点值：0
		if (id != null) {
			treeid = id;
		}
		//得到对应节点下所有的下级节点目录
		List<TreeVo> list = treeService.getTree(treeid,suv);
		return list;
	}
}
