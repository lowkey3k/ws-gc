package com.lht.demo.web.dao;

import com.lht.demo.jpa.baseDao.JpaCrudDao;
import com.lht.demo.web.entity.User;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserDao extends JpaCrudDao<User,Long> {

}
