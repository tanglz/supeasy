package com.uoit.network.supeasy.model;

import java.util.List;

public class Flyer {
    private UserInfo userInfo;
    private List<ProductInfo> productInfoList;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> productInfoList) {
        this.productInfoList = productInfoList;
    }
}
