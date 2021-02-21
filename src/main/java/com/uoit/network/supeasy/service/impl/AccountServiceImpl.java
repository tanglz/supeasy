package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.data.UserDO;
import com.uoit.network.supeasy.data.UserRepository;
import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.model.UserInfo;
import com.uoit.network.supeasy.service.AccountService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Result<Integer> addAccount(UserInfo userInfo) {
        Result<Integer> result=new Result<>();
        result.setStatus(false);
        if(userInfo.getId()!=null){
            result.setObject(userInfo.getId());
            result.setErrorCode("E_P");
            result.setErrorMessage("parameter error");
            return result;
        }
        String name=userInfo.getName();
        Result<UserInfo> existUser = getUserByName(name);
        if(existUser!=null&&existUser.isStatus()&&existUser.getObject().getId()>0){
            result.setObject(existUser.getObject().getId());
            result.setErrorCode("E_EXIST_NAME");
            result.setErrorMessage("username is exist");
            return result;
        }
        UserDO userDO=new UserDO();
        userDO= (UserDO) ConvertUtil.modelToDo(userInfo,userDO);
        UserDO user = userRepository.save(userDO);
        result.setStatus(true);
        result.setObject(user.getId());
        return result;
    }

    @Override
    public Result<UserInfo> getUserByName(String name) {
        Result<UserInfo> result=new Result<>();
        List<UserDO> userDOList = userRepository.findByName(name);
        if(userDOList==null||userDOList.isEmpty()){
            result.setStatus(false);
            result.setErrorCode("E_NO_USER");
            result.setErrorMessage("username is not exist, please register first.");
            return result;
        }
        UserDO userDO=userDOList.get(0);
        UserInfo userInfo=new UserInfo();
        userInfo= (UserInfo) ConvertUtil.modelToDo(userDO,userInfo);
        result.setObject(userInfo);
        result.setStatus(true);
        return result;
    }

    @Override
    public Result<UserInfo> getUserById(Integer id) {
        Result<UserInfo> result=new Result<>();
        Optional<UserDO> user = userRepository.findById(id);
        if(user==null||user.get()==null){
            result.setStatus(false);
            result.setErrorCode("E_NO_USER");
            result.setErrorMessage("id is not exist, please register first.");
            return result;
        }
        UserDO userDO=user.get();
        UserInfo userInfo=new UserInfo();
        userInfo= (UserInfo) ConvertUtil.modelToDo(userDO,userInfo);
        result.setObject(userInfo);
        result.setStatus(true);
        return result;
    }
}
