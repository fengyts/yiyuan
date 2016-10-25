package ng.bayue.backend.ao.index;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.enums.SysMenuTypeEnum;
import ng.bayue.backend.service.SysMenuService;
import ng.bayue.backend.util.Messages;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.util.Page;

@Service
public class SysMenuAO {
	
	private static final Logger logger = LoggerFactory.getLogger(SysMenuAO.class);
	
	@Autowired
	private SysMenuService sysMenuService;
	
	public Page<SysMenuDO> pageQueryList(SysMenuDO sysMenuDO,Integer pageNo,Integer pageSize){
		Page<SysMenuDO> page = sysMenuService.queryPageListBySysMenuDOAndStartPageSize(sysMenuDO, pageNo, pageSize);
		
		return page;
	}
	
	public List<SysMenuDO> listMenu(SysMenuDO sysMenuDO){
		List<SysMenuDO> list = sysMenuService.selectDynamic(sysMenuDO);
		return list;
	}
	
	public SysMenuDO selectById(Long id){
		if(null == id){return null;}
		return sysMenuService.selectById(id);
	}
	
	/**
	 * <pre>
	 * 获取所有父级菜单(包括导航菜单和主菜单)
	 * </pre>
	 *
	 * @return
	 */
	public List<SysMenuDO> listBeforeTwoMenu(){
		SysMenuDO sysMenuDO = new SysMenuDO();
		sysMenuDO.setMenuType(SysMenuTypeEnum.NAVIGATION.getCode());
		List<SysMenuDO> plist = listMenu(sysMenuDO);
		List<SysMenuDO> list = sysMenuService.findListByParentIds(plist);
		plist.addAll(list);
		return plist;
	}
	
	public ResultMessage addSysMenu(SysMenuDO sysMenuDO){
		if(null == sysMenuDO){ return new ResultMessage(ResultMessage.Failure,Messages.ParameterError);}
		if(StringUtils.isEmpty(sysMenuDO.getName())){
			return new ResultMessage(ResultMessage.Failure,Messages.parameterErrMsgs("name"));
		}
		Date date = new Date();
		sysMenuDO.setCreateTime(date);
		sysMenuDO.setModifyTime(date);
		Long userId = UserHandler.getUser().getId();
		sysMenuDO.setCreateUserId(userId);
		sysMenuDO.setModifyUserId(userId);
		sysMenuDO = sysMenuService.save(sysMenuDO);
		return new ResultMessage(ResultMessage.Message,sysMenuDO);
	}
	
	public ResultMessage updateSysMenu(SysMenuDO sysMenuDO){
		if(null == sysMenuDO){ return new ResultMessage(ResultMessage.Failure,Messages.ParameterError);}
		sysMenuDO.setModifyTime(new Date());
		sysMenuDO.setModifyUserId(UserHandler.getUser().getId());
		int res = sysMenuService.update(sysMenuDO, false);
		if(1 == res){
			return new ResultMessage();
		}else{
			return new ResultMessage(ResultMessage.Failure,Messages.HandleFailure);
		}
	}

}
