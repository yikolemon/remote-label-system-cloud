package cn.zko0.remotelabel.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.zko0.remotelabel.feign.LoginAndRegisterFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private LoginAndRegisterFeign loginAndRegisterFeign;

    /**
     * 不使用基于权限的方式校验，使用角色方式校验
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表 
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        String role = loginAndRegisterFeign.getRoleById((String) loginId);
        ArrayList<String> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

}
