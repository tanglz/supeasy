package com.uoit.network.supeasy.service;

import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.model.UserInfo;

public interface AccountService {
    /**
     * add a user
     * @param userInfo
     * @return
     */
    Result<Integer> addAccount(UserInfo userInfo);

    /**
     * find a user by name
     * @param name
     * @return
     */
    Result<UserInfo> getUserByName(String name);

    /**
     * find a user by id
     * @param id
     * @return
     */
    Result<UserInfo> getUserById(Integer id);

}
