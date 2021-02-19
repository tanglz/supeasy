package com.uoit.network.supeasy.controller;

import com.uoit.network.supeasy.model.StoreInfo;
import com.uoit.network.supeasy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/add")
    public @ResponseBody Integer add(@RequestParam String name, @RequestParam String address,
                       @RequestParam String description){
        StoreInfo storeInfo=new StoreInfo();
        storeInfo.setAddress(address);
        storeInfo.setDescription(description);
        storeInfo.setName(name);
        return storeService.addStore(storeInfo);
    }

    @GetMapping("/get")
    public @ResponseBody StoreInfo get(@RequestParam String name){
        return storeService.getStoreByName(name);
    }
}
