package com.uoit.network.supeasy.controller;

import com.uoit.network.supeasy.model.*;
import com.uoit.network.supeasy.service.AccountService;
import com.uoit.network.supeasy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/store")
public class StoreController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ProductService productService;

    @GetMapping("/flyer")
    @ResponseBody
    public Result<Flyer> getFlyer(@RequestParam Integer storeId){
        Result<Flyer> result=new Result<>();
        Flyer flyer=new Flyer();
        try{
            Result<UserInfo> user = accountService.getUserById(storeId);
            if(user==null||!user.isStatus()){
                result.setStatus(false);
                result.setErrorCode("E_NO_STORE");
                result.setErrorMessage("please register first");
                return result;
            }
            flyer.setUserInfo(user.getObject());
            result.setStatus(true);
            Result<List<ProductInfo>> products = productService.getProductsByStoreId(storeId);
            if(products==null||!products.isStatus()){
                result.setStatus(false);
                result.setErrorCode(products.getErrorCode());
                result.setErrorMessage(products.getErrorMessage());
                return result;
            }
            flyer.setProductInfoList(products.getObject());
            result.setObject(flyer);
        }catch (Exception e){
            result.setStatus(false);
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }
}
