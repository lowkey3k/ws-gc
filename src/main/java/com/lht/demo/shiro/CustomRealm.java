package com.lht.demo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description Custom:自定义
 * @Author LiHaitao
 * @Date 2018/8/19 14:22
 * @UpdateUser
 * @UpdateDescrip
 * @UpdateDate
 * @Version 1.0.0
 **/
public class CustomRealm extends AuthorizingRealm {

   //simulated DB:模拟数据库
    Map<String,String> map=new HashMap<>();
    {
        //87510683a330b5f74475fd8c569bd653
        map.put("lihaitao","87510683a330b5f74475fd8c569bd653");
        map.put("role","admin");
        super.setName("customRealm");
    }


    /**
     * Get authority roles:获取权限角色
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username= (String) principalCollection.getPrimaryPrincipal();
        Set<String> roles=getRolesByUserName(username);
        Set<String> permissons=getPermissionsByUserName(username);
        //Authorization:授权

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissons);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }


    /**
     * 获取认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从主体传过来的认证信息中获取用户名
        String username=(String)authenticationToken.getPrincipal();
        //From DB
        String password=getPasswordByUserName(username);
        if(password==null ){
            return null;
        }
        //Authentication:认证
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,"customRealm");
        //加盐后将盐设置进去
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("lihaitao"));
        return authenticationInfo;
    }


    private Set<String> getPermissionsByUserName(String username) {
        Set<String> set=new HashSet<>();
        set.add("user:delete");
        set.add("user:update");
        return set;
    }

    private Set<String> getRolesByUserName(String username) {
        Set<String> set=new HashSet<>();
        set.add("admin");
        set.add("superAdmin");
        return set;
    }
    private String getPasswordByUserName(String username) {
        return map.get(username);
    }


    public static void main(String[] args) {
        //计算加密后密码，再加盐：salt
        Md5Hash md5Hash=new Md5Hash("123456","lihaitao");
        System.out.println(md5Hash.toString());
    }
}
