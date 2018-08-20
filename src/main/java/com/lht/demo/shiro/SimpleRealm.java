package com.lht.demo.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

/**
 * @Description shiro test
 * @Author LiHaitao
 * @Date 2018/8/19 13:47
 * @UpdateUser
 * @UpdateDescrip
 * @UpdateDate
 * @Version 1.0.0
 **/
public class SimpleRealm {

    public static void main(String[] args) {
        SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();
        simpleAccountRealm.addAccount("lihaitao","123456","admin","superAdmin");

        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //获取主体
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("lihaitao","123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        /*subject.logout();
        System.out.println(subject.isAuthenticated());
        */
        subject.checkRole("admin");
        subject.checkRoles("admin","superAdmin");
    }
}
