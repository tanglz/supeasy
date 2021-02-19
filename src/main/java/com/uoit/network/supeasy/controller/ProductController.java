package com.uoit.network.supeasy.controller;

import com.uoit.network.supeasy.model.ProductInfo;
import com.uoit.network.supeasy.model.StoreInfo;
import com.uoit.network.supeasy.service.ProductService;
import com.uoit.network.supeasy.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public @ResponseBody Integer add(@RequestParam String name, @RequestParam String imgUrl,
                @RequestParam String description, @RequestParam Integer storeId){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setImageUrl(imgUrl);
        productInfo.setDescription(description);
        productInfo.setName(name);
        productInfo.setStoreId(storeId);
        return productService.addProduct(productInfo);
    }

    @GetMapping("/all")
    public @ResponseBody List<ProductInfo> get(@RequestParam Integer storeId){
        return productService.getProductsByStoreId(storeId);
    }
}
