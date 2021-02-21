package com.uoit.network.supeasy.service;

import com.uoit.network.supeasy.model.ProductInfo;
import com.uoit.network.supeasy.model.Result;

import java.util.List;

public interface ProductService {
    Result<List<ProductInfo>> getProductsByStoreId(Integer storeId);

    Result<Integer> addProduct(ProductInfo productInfo);

    Result<ProductInfo> getProductById(Integer id);

    Result<ProductInfo> getProductByStoreIdAndName(Integer storeId,String name);

}
