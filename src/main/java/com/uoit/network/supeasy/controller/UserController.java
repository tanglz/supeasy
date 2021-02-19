package com.uoit.network.supeasy.controller;

import com.uoit.network.supeasy.data.UserDO;
import com.uoit.network.supeasy.data.UserRepository;
import com.uoit.network.supeasy.model.UserInfo;
import com.uoit.network.supeasy.service.AccountService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/get")
    public @ResponseBody UserInfo getUser(@RequestParam String name){
        UserInfo userInfo= accountService.getUserByName(name);
        return userInfo;
    }

    @PostMapping(path = "/add")
    public @ResponseBody Integer addUser(@RequestParam String name, @RequestParam String email,
                                         @RequestParam String phone, @RequestParam String password){
        UserInfo userInfo =new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName(name);
        userInfo.setPhone(phone);
        String md5Password=DigestUtils.md5DigestAsHex(password.getBytes());
        userInfo.setPassword(md5Password);
        Integer id = accountService.addAccount(userInfo);
        return id;
    }

}
