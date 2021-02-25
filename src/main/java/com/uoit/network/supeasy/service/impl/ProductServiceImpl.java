package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.data.ProductDO;
import com.uoit.network.supeasy.data.ProductRepository;
import com.uoit.network.supeasy.model.ProductInfo;
import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.service.ProductService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Result<List<ProductInfo>> getProductsByStoreId(Integer storeId) {
        Result<List<ProductInfo>> result = new Result<>();
        try {
            List<ProductDO> productDOList = productRepository.findAllByStoreId(storeId);
            if (productDOList == null || productDOList.isEmpty()) {
                result.setStatus(true);
                result.setObject(Collections.emptyList());
                return result;
            }
            List<ProductInfo> productInfoList = new ArrayList<>();
            productDOList.forEach(productDO -> {
                ProductInfo productInfo = new ProductInfo();
                productInfo = (ProductInfo) ConvertUtil.modelToDo(productDO, productInfo);
                productInfoList.add(productInfo);
            });
            result.setStatus(true);
            result.setObject(productInfoList);
        } catch (Exception e) {
            result.setStatus(false);
            result.setErrorCode("E_SYS");
            result.setErrorMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<Integer> addProduct(ProductInfo productInfo) {
        Result<Integer> result = new Result<>();
        Integer storeId = productInfo.getStoreId();
        String name = productInfo.getName();
        if (storeId == null || storeId < 0 || !StringUtils.hasLength(name)) {
            result.setStatus(false);
            result.setErrorCode("E_NO_STORE_ID");
            result.setErrorMessage("parameter error");
            return result;
        }

        Result<ProductInfo> product = getProductByStoreIdAndName(storeId, name);
        if (product != null && product.isStatus() && product.getObject() != null
                && StringUtils.hasLength(product.getObject().getName())) {
            result.setStatus(false);
            result.setErrorCode("E_EXIST_PRODUCT");
            result.setErrorMessage("It's a exist product.");
            return result;
        }
        ProductDO productDO = new ProductDO();
        productDO = (ProductDO) ConvertUtil.modelToDo(productInfo, productDO);
        try {
            ProductDO save = productRepository.save(productDO);
            result.setStatus(true);
            result.setObject(save.getId());
        } catch (Exception e) {
            result.setStatus(false);
            result.setErrorCode("E_SYS");
            result.setErrorMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Result<ProductInfo> getProductById(Integer id) {
        Result<ProductInfo> result = new Result<>();
        Optional<ProductDO> product = productRepository.findById(id);
        ProductDO productDO = product.get();
        ProductInfo productInfo = new ProductInfo();
        productInfo = (ProductInfo) ConvertUtil.modelToDo(productDO, productInfo);
        result.setStatus(true);
        result.setObject(productInfo);
        return result;
    }

    @Override
    public Result<ProductInfo> getProductByStoreIdAndName(Integer storeId, String name) {
        Result<ProductInfo> result = new Result<>();
        ProductInfo productInfo = new ProductInfo();
        try {
            ProductDO productDO = productRepository.findByStoreIdAndName(storeId, name);
            productInfo = (ProductInfo) ConvertUtil.modelToDo(productDO, productInfo);
            result.setStatus(true);
            result.setObject(productInfo);
        } catch (Exception e) {
            result.setStatus(false);
            result.setErrorCode("E_SYS");
            result.setErrorMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }
}
