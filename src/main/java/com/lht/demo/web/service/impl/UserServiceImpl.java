package com.lht.demo.web.service.impl;

import com.lht.demo.jpa.serviceSimple.AbstractCrudEntityService;
import com.lht.demo.web.entity.User;
import com.lht.demo.web.service.UserService;
import org.springframework.stereotype.Service;


@Service
public  class UserServiceImpl  extends AbstractCrudEntityService<User,Long> implements UserService {



}
