package com.uoit.network.supeasy.controller;
import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.model.UserInfo;
import com.uoit.network.supeasy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import com.uoit.network.supeasy.util.JwtHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/check")
    @ResponseBody
    public Result<Map<String,Object>> check() {
        Result<Map<String,Object>> result=new Result<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status","success");
        map.put("Date",new Date());
        result.setStatus(true);
        result.setObject(map);
        return result;
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public Result<UserInfo> getUser(@RequestParam String name) {
        if (name.isEmpty()) {
            return null;
        }
        return accountService.getUserByName(name);
    }

    @PostMapping(path = "/register_store")
    @ResponseBody
    public Result<Integer> registerAndStore(@RequestBody UserInfo userInfo) {
        String password=userInfo.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        userInfo.setPassword(md5Password);
        Result<Integer> result = accountService.addAccount(userInfo);
        return result;
    }

    @CrossOrigin
    @PostMapping(path = "/login")
    @ResponseBody
    public Result<Map<String,String>> login(@RequestBody UserInfo userInfo) {
        Result<Map<String,String>> result=new Result<>();
        Map<String,String> map=new HashMap<>();
        Result<UserInfo> user = accountService.getUserByName(userInfo.getName());
        if (user == null ||!user.isStatus()) {
            result.setStatus(false);
            return result;
        }
        String md5Password = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes());
        if (!user.getObject().getPassword().equals(md5Password)) {
            user.setErrorCode("E_PASSWORD_ERROR");
            user.setErrorMessage("password is not correct,please try again.");
            user.setStatus(false);
            return result;
        }
        result.setStatus(true);
        String jwtToken = JwtHelper.createJWT(userInfo.getName(),
                user.getObject().getId().toString(),
                "098f6bcd4621d373cade4e832627b4f6",
                "api",
                3600*1000, //1 hours
                "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");

        map.put("token",jwtToken);
        map.put("name",userInfo.getName());
        map.put("id",user.getObject().getId().toString());
        result.setObject(map);
        return result;
    }

}
