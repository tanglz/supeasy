package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.data.StoreDO;
import com.uoit.network.supeasy.data.StoreRepository;
import com.uoit.network.supeasy.model.StoreInfo;
import com.uoit.network.supeasy.service.StoreService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Integer addStore(StoreInfo storeInfo) {
        StoreDO storeDO=new StoreDO();
        storeDO= (StoreDO) ConvertUtil.modelToDo(storeInfo,storeDO);
        StoreDO save = storeRepository.save(storeDO);
        return save.getId();
    }

    @Override
    public StoreInfo getStoreByName(String name) {
        StoreInfo storeInfo=new StoreInfo();
        List<StoreDO> storeDOS = storeRepository.findByName(name);
        if(storeDOS==null||storeDOS.isEmpty()){
            return storeInfo;
        }
        storeInfo= (StoreInfo) ConvertUtil.modelToDo(storeDOS.get(0),storeInfo);
        return storeInfo;
    }


}
