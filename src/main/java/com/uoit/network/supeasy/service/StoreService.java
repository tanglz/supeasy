package com.uoit.network.supeasy.service;

import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.model.StoreInfo;

public interface StoreService {
    Result<Integer> addStore(StoreInfo storeInfo);

    Result<StoreInfo> getStoreByName(String name);
}
