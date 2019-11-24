package com.jhy.plateform.service.impl;

import com.jhy.plateform.dao.BookItemMapper;
import com.jhy.plateform.dao.MenuMapper;
import com.jhy.plateform.domain.Menu;
import com.jhy.plateform.exception.ExceptionKind;
import com.jhy.plateform.exception.KPException;
import com.jhy.plateform.query.MenuQuery;
import com.jhy.plateform.service.MenuService;
import com.jhy.plateform.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuQuery> implements MenuService {

	@Autowired
	public void setMenuMapper(MenuMapper menuMapper){
		this.daoMapper = menuMapper;
	}

	@Override
	public List<Menu> findByParent(Integer parentId) {
		return ((MenuMapper)this.daoMapper).findByParent(parentId);
	}
	
	@Override
	public int clearGrant(Map<String,Integer> param) {
		return((MenuMapper)this.daoMapper).clearGrant(param);
	}

	@Override
	public List<Menu> findByRoleIds(Integer [] roleId) {
		return ((MenuMapper)this.daoMapper).findByRoleIds(roleId);
	}
	
	@Override
	public void updateAuth(Integer roleId,Integer[] menuIds) throws KPException {
		
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("roleId",roleId);
		//清空已有的权限数据
		clearGrant(param);
		
		if(menuIds!=null){
			int menuCount = menuIds.length;
			int effectCount=0;
			for(int i=0;i<menuCount;i++){
				param.put("menuId",menuIds[i]);
				effectCount +=((MenuMapper)this.daoMapper).grant(param);  //TODO 建议批量添加
			}
			if(effectCount!=menuCount){
				throw new KPException(ExceptionKind.PARAM_E);
			}
		}
	}

	@Override
	public List<Integer> findByRole(Integer roleId) {
		return((MenuMapper)this.daoMapper).findByRole(roleId);
	}

	@Override
	public List<Menu> findLevelMenu() {
		return((MenuMapper)this.daoMapper).findLevelMenu();
	}
}