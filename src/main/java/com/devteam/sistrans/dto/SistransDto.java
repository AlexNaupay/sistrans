package com.devteam.sistrans.dto;

import java.util.HashMap;
import java.util.Map;

public class SistransDto {

    private Map<String, Object> map;
    private  static final String ERROR_KEY = "error_cod";
    private  static final String ERROR_DESC_KEY = "error_desc";
    private static final String DATA_KEY = "data";

    public SistransDto() {
        map = new HashMap<>();
        setErrorCod(0);
        setErrorDesc("Ejecución correcta");
    }

    public void setErrorCod(int errorCod){
        map.put(ERROR_KEY, errorCod);
    }

    public void setErrorDesc(String errorDescripcion){
        map.put(ERROR_DESC_KEY, errorDescripcion);
    }

    public void setData(Object data){
        map.put(DATA_KEY, data);
    }

    public void put(String key, Object data){
        map.put(key, data);
    }

    public Object get(String key){
        return map.get(key);
    }

    public int getErrorCod(){
        return (int) map.get(ERROR_KEY);
    }

    public String getErrorDesc(){
        return (String) map.get(ERROR_DESC_KEY);
    }

    public Object getData(){
        return map.get(DATA_KEY);
    }

}
