package com.qatest.demo.utils;

import lombok.extern.log4j.Log4j;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


@Log4j
public class PropertiesUtil {

    // 是否是文件
    public boolean isFile = false;

    // 路径
    public String path;

    // 单列properties
    private Properties properties = null;

    public PropertiesUtil(String path) {
        this.path = path;
        File file = new File(path);
        isFile = file.isFile();
    }

    public boolean isFile(String path){
        return isFile;
    }

    // 把配置文件转化为对象
    public Object propertiesToObject(Object object,String path){
        if(!isFile(path)){
            return null;
        }
        Field[] files = object.getClass().getDeclaredFields();
        Properties properties = load(path);
        for(Field field:files){
            String fieldName = field.getName();
            Class type = field.getType();
            String methodFieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            Method method = null;
            try {

                //如果获取参数值为空则继续循环
                if(properties.get(fieldName) == null){
                    log.warn("WARNING：配置 " + fieldName + " 获取为空，环境变量配置文件中未配置，若有默认值或未引用该变量，可忽略~");
                    continue;
                }

                method = object.getClass().getMethod(methodFieldName, type);
                if(type.toString().equals("int")){
                    method.invoke(object, new Integer(properties.get(fieldName).toString()));

                }else if(type.toString().equals("long")){
                    method.invoke(object, new Long(properties.get(fieldName).toString()));

                }else if(type.toString().equals("boolean")){
                    method.invoke(object, new Boolean(properties.get(fieldName).toString()));
                }else {
                    method.invoke(object, properties.get(fieldName));

                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
    // 获取配置文件中所有的键值
    public List<Object> getListKey(String path){
        if(!isFile(path)){
            return null;
        }
        Properties properties = load(path);
        Set<Object> set = properties.keySet();
        List<Object> list = new ArrayList<Object>(set);
        return list;
    }

    // 获取配置文件中所有的值
    public List<Object> getListValue(String path){
        if(!isFile(path)){
            return null;
        }
        Properties properties = load(path);
        List<Object> list = new ArrayList<Object>();
        for(Object key:properties.keySet()){
            list.add(properties.get(key));
        }
        return list;
    }

    // 配置文件转成map集合
    public Map<String,Object> getMapKeyValue(String path){
        if(!isFile(path)){
            return null;
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        Properties properties = load(path);
        for(Object key : getListKey(path)){
            resultMap.put((String)key, properties.get(key));
        }
        return resultMap;
    }

    public Properties load(String path) {
        if(properties == null){
            InputStream stream = null;
            try {
                stream = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            properties = new Properties();
            try {
                properties.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

}
