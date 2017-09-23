package ng.bayue.backend.controller.index;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import ng.bayue.backend.ao.index.SysMenuAO;
import ng.bayue.backend.controller.common.AbstractBaseController;
import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.dto.SysUserVO;
import ng.bayue.backend.enums.SysMenuTypeEnum;

import org.apache.shiro.SecurityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PermissionController extends AbstractBaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private SysMenuAO sysMenuAO;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "loadAdminRights")
    @ResponseBody
    public JSONArray loadAdminRights(HttpServletResponse resp) {
        JSONArray root = new JSONArray();
//        List<SysMenuDO> sm = UserHandler.getUser().getSysMenuList();
//        List<SysMenuDO> sm = sysMenuAO.selectDynamic(new SysMenuDO());
        SysUserVO userVO = (SysUserVO) SecurityUtils.getSubject().getPrincipal();
        List<SysMenuDO> sm = userVO.getSysMenus();
        
        logger.debug("拥有权限菜单数量:" + (null != sm ? sm.size() : 0));

        if (null != sm && !sm.isEmpty()) {
            for (SysMenuDO sysMenuDO : sm) {
            	if(null == sysMenuDO.getParentId()){continue;}//所有菜单的最顶级菜单root
                if (SysMenuTypeEnum.NAVIGATION.getCode().longValue() == sysMenuDO.getParentId().longValue()) { // 获取导航菜单
                    JSONObject first = new JSONObject();
                    first.put("id", sysMenuDO.getId());
                    first.put("text", sysMenuDO.getName());
                    first.put("url", sysMenuDO.getUrl());
                    sysMenuDO.setLocation(1L);
                    first.put("location", sysMenuDO.getLocation());
                    this.buildRights(first, sysMenuDO, sm);

                    root.add(first);
                }
            }
        }
        logger.debug("rights JSON:" + root);
        return root;
    }
    

    /**
     * 构建子菜单
     *
     * @param first
     * @param permission
     */
    @SuppressWarnings("unchecked")
    private void buildRights(final JSONObject po, final SysMenuDO parent, final List<SysMenuDO> permissions) {
        JSONArray children = new JSONArray();
        po.put("children", children);
        for (SysMenuDO permission : permissions) {
            if (null != permission.getParentId() && permission.getParentId().equals(parent.getId()) && SysMenuDO.MENU_TYPE_2.equals(permission.getMenuType())) {
                JSONObject node = new JSONObject();
                node.put("id", permission.getId());
                node.put("text", permission.getName());
                node.put("url", permission.getUrl());
                node.put("location", null != parent.getLocation() ? parent.getLocation() : 1);

                this.buildRights(node, permission, permissions);
                children.add(node);

            }
        }

    }

    class Right {
        public Right() {

        }

        public Right(final Integer id, final String name, final String url, final Integer location, final Integer parent) {
            this.id = Long.valueOf(id);
            this.name = name;
            this.url = url;
            this.location = location;
            this.parentId = Long.valueOf(parent);
        }

        Long id;
        String name;
        String url;
        String pcode;
        Integer location;
        Long parentId;

        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(final String pcode) {
            this.pcode = pcode;
        }

        public Integer getLocation() {
            return location;
        }

        public void setLocation(final Integer location) {
            this.location = location;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(final Long parentId) {
            this.parentId = parentId;
        }
    }
    //
    // /**
    // * 构建
    // *
    // * @param first
    // * @param permission
    // */
    // @SuppressWarnings("unchecked")
    // private void buildRights(JSONObject po, Right parent,
    // List<Right> permissions) {
    // JSONArray children = new JSONArray();
    // po.put("children", children);
    // for (int i = 0; i < permissions.size(); i++) {
    // Right permission = permissions.get(i);
    // if (null != permission.getParentId()
    // && permission.getParentId().equals(parent.getId())) {
    // JSONObject node = new JSONObject();
    // node.put("id", permission.getId());
    // node.put("pcode", permission.getPcode());
    // node.put("text", permission.getName());
    // node.put("url", permission.getUrl());
    // node.put("location", permission.getLocation());
    //
    // this.buildRights(node, permission, permissions);
    // children.add(node);
    //
    // }
    // }
    // }
    //
    // class Right{
    // public Right(){
    //
    // }
    // public Right(Integer id,String name,String url,Integer location,Integer parent){
    // this.id=Long.valueOf(id);
    // this.name=name;
    // this.url=url;
    // this.location=location;
    // this.parentId=Long.valueOf(parent);
    // }
    // Long id;
    // String name;
    // String url;
    // String pcode;
    // Integer location;
    // Long parentId;
    // public Long getId() {
    // return id;
    // }
    // public void setId(Long id) {
    // this.id = id;
    // }
    // public String getName() {
    // return name;
    // }
    // public void setName(String name) {
    // this.name = name;
    // }
    // public String getUrl() {
    // return url;
    // }
    // public void setUrl(String url) {
    // this.url = url;
    // }
    // public String getPcode() {
    // return pcode;
    // }
    // public void setPcode(String pcode) {
    // this.pcode = pcode;
    // }
    // public Integer getLocation() {
    // return location;
    // }
    // public void setLocation(Integer location) {
    // this.location = location;
    // }
    // public Long getParentId() {
    // return parentId;
    // }
    // public void setParentId(Long parentId) {
    // this.parentId = parentId;
    // }
    // }
}
