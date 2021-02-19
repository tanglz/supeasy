package com.uoit.network.supeasy.service;

import com.uoit.network.supeasy.model.UserInfo;

public interface AccountService {
    /**
     * add a user
     * @param userInfo
     * @return
     */
    Integer addAccount(UserInfo userInfo);

    /**
     * find a user by name
     * @param name
     * @return
     */
    UserInfo getUserByName(String name);

}
