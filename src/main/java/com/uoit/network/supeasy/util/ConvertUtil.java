package com.uoit.network.supeasy.util;
import com.uoit.network.supeasy.data.UserDO;
import com.uoit.network.supeasy.model.UserInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ConvertUtil {
    public static Object modelToDo(Object model, Object dataObject){
        Class modelClass = model.getClass();
        Class doClass = dataObject.getClass();
        Field[] modelFields = modelClass.getDeclaredFields();
        Field[] superModelFields = modelClass.getSuperclass().getDeclaredFields();
        modelFields = (Field[]) mergeArray(modelFields, superModelFields);

        for (Field f : modelFields) {
            String fieldName = f.getName();
            //get Do setMethod
            String doSetMethodName = "set" + firstToBig(fieldName);
            Method doSetMethod = null;
            try {
                doSetMethod = doClass.getMethod(doSetMethodName, f.getType());
            } catch (NoSuchMethodException e) {
                continue;
            }
            String modelGetMethodName = "get" + firstToBig(fieldName);
            //get model getMethod
            Method modelGetMethod =null;
            try{
                 modelGetMethod = modelClass.getMethod(modelGetMethodName);
            }catch (NoSuchMethodException e){
                continue;
            }
            // TODO LOG
            try {
                doSetMethod.invoke(dataObject, modelGetMethod.invoke(model));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return dataObject;
    }

    public static void main(String[] args){
        UserInfo userInfo=new UserInfo();
        userInfo.setId(2);
        userInfo.setEmail("test@gmail.com");
        userInfo.setName("aaa");
        userInfo.setPhone("1234567890");
        userInfo.setPassword("md5");
        UserDO userDO=new UserDO();
        try{
            UserDO userDo = (UserDO) ConvertUtil.modelToDo(userInfo, userDO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(userDO.getEmail());
    }
    private static Object[] mergeArray(Object[] a, Object[] b) {
        Object[] c = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    private static String firstToBig(String fieldName) {
        if (fieldName != null && fieldName != "") {
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        return fieldName;
    }
}
