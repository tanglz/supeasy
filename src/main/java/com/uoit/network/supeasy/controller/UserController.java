package com.uoit.network.supeasy.controller;

import com.uoit.network.supeasy.interceptor.LoginRequired;
import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.model.UserInfo;
import com.uoit.network.supeasy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/get")
    @ResponseBody
    @LoginRequired
    public Result<UserInfo> getUser(@RequestParam String name) {
        if (name.isEmpty()) {
            return null;
        }
        return accountService.getUserByName(name);
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public Result<Integer> register(@RequestParam String name, @RequestParam String email,
                                    @RequestParam String phone, @RequestParam String password,
                                    HttpSession session) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName(name);
        userInfo.setPhone(phone);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        userInfo.setPassword(md5Password);
        Result<Integer> result = accountService.addAccount(userInfo);
        if (result != null && result.isStatus()) {
            session.setAttribute("user_name", name);
            session.setAttribute("user_id", result.getObject());
        }
        return result;
    }

    @PostMapping(path = "/register_store")
    @ResponseBody
    public Result<Integer> registerAndStore(@RequestParam String name, @RequestParam String email,
                                            @RequestParam String phone, @RequestParam String password,
                                            @RequestParam String storeName,
                                            @RequestParam String address, @RequestParam String description,
                                            HttpSession session) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setName(name);
        userInfo.setPhone(phone);
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        userInfo.setPassword(md5Password);
        userInfo.setStoreName(storeName);
        userInfo.setAddress(address);
        userInfo.setDescription(description);
        Result<Integer> result = accountService.addAccount(userInfo);
        if (result != null && result.isStatus()) {
            session.setAttribute("user_name", name);
            session.setAttribute("user_id", result.getObject());
        }
        return result;
    }

    @PostMapping(path = "/login")
    @ResponseBody
    public Result<UserInfo> login(@RequestParam String name, @RequestParam String password,
                                  HttpSession session) {
        Result<UserInfo> result = accountService.getUserByName(name);
        if (result == null || result.isStatus()) {
            return result;
        }
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!result.getObject().getPassword().equals(md5Password)) {
            result.setErrorCode("E_PASSWORD_ERROR");
            result.setErrorMessage("password is not correct,please try again.");
            return result;
        }
        session.setAttribute("user_name", name);
        session.setAttribute("user_id", result.getObject());
        return result;
    }

    @PostMapping(path = "/logout")
    @ResponseBody
    @LoginRequired
    public Result<Boolean> logout(HttpSession session) {
        Result<Boolean> result = new Result<>();
        session.removeAttribute("user_name");
        session.removeAttribute("user_id");
        return result;
    }
}
