package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.data.ProductDO;
import com.uoit.network.supeasy.data.ProductRepository;
import com.uoit.network.supeasy.model.ProductInfo;
import com.uoit.network.supeasy.service.ProductService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductInfo> getProductsByStoreId(Integer storeId) {
        List<ProductDO> productDOList=productRepository.findAllByStoreId(storeId);
        List<ProductInfo> productInfoList=new ArrayList<>();
        productDOList.forEach(productDO -> {
            ProductInfo productInfo=new ProductInfo();
            productInfo= (ProductInfo) ConvertUtil.modelToDo(productDO,productInfo);
            productInfoList.add(productInfo);
        });
        return productInfoList;
    }

    @Override
    public Integer addProduct(ProductInfo productInfo) {
        ProductDO productDO=new ProductDO();
        productDO= (ProductDO) ConvertUtil.modelToDo(productInfo,productDO);
        ProductDO save = productRepository.save(productDO);
        return save.getId();
    }

    @Override
    public ProductInfo getProductById(Integer id) {
        Optional<ProductDO> product = productRepository.findById(id);
        ProductDO productDO=product.get();
        ProductInfo productInfo=new ProductInfo();
        productInfo= (ProductInfo) ConvertUtil.modelToDo(productDO,productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo getProductByNameAndStoreId(String name, Integer storeId) {
        //TODO
        return null;
    }
}
