package com.uoit.network.supeasy;

import com.uoit.network.supeasy.data.UserInfo;
import com.uoit.network.supeasy.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/db")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/get")
    public @ResponseBody Iterable<UserInfo> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam String name,@RequestParam String email, @RequestParam String phone,@RequestParam String address){
        UserInfo userInfo=new UserInfo();
        userInfo.setAddress(address);
        userInfo.setEmail(email);
        userInfo.setName(name);
        userInfo.setPhone(phone);
        userRepository.save(userInfo);
        return "success";
    }

}
