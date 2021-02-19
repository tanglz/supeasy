package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.data.UserDO;
import com.uoit.network.supeasy.data.UserRepository;
import com.uoit.network.supeasy.model.UserInfo;
import com.uoit.network.supeasy.service.AccountService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Integer addAccount(UserInfo userInfo) {
        if(userInfo.getId()!=null){
            return userInfo.getId();
        }
        UserDO userDO=new UserDO();
        userDO= (UserDO) ConvertUtil.modelToDo(userInfo,userDO);
        UserDO user = userRepository.save(userDO);
        return user.getId();
    }

    @Override
    public UserInfo getUserByName(String name) {
        UserInfo userInfo=new UserInfo();
        List<UserDO> userDOList = userRepository.findByName(name);
        if(userDOList==null||userDOList.isEmpty()){
            return userInfo;
        }
        UserDO userDO=userDOList.get(0);
        userInfo= (UserInfo) ConvertUtil.modelToDo(userDO,userInfo);
        return userInfo;
    }
}
