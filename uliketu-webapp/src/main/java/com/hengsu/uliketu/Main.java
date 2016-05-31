package com.hengsu.uliketu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haiquanli on 16/5/14.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        Map<String,Map<String,Map>> urls = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(FileUtils.readFileToString(new File("/Users/haiquanli/Documents/work/hengsu/code/uliketu/uliketu-webapp/api")));
        JSONArray folders = jsonObject.getJSONArray("folders");
        JSONArray requests = jsonObject.getJSONArray("requests");
        Map<String,Map> requestMaps = new HashMap<>();
        for(Object obj:requests){
            JSONObject request = (JSONObject)obj;
            requestMaps.put(request.getString("id"),request);
        }
        for(Object obj:folders){
            JSONObject folder = (JSONObject)obj;
            JSONArray orders = folder.getJSONArray("order");
            Map module = new HashMap();
            for(Object object:orders){
                String id = object.toString();
                Map oriRequest = requestMaps.get(id);
                module.put(oriRequest.get("name").toString(), ImmutableMap.of("url",oriRequest.get("url"),
                        "method",oriRequest.get("method").toString(),
                        "auth",oriRequest.get("headers").toString().contains("Authorization")));
            }

            urls.put(folder.getString("name"),module);
        }


        System.out.println(JSON.toJSONString(urls));

    }
}
