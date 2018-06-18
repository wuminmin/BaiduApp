package com.ysc.baiduapp.service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CreatJson {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //创建json对象
        JsonObject object = new JsonObject();
        //添加该键值对 相当于 name: 西游记
        object.addProperty("name", "水浒传");
        //我们在创建一个数组并添加到json对象中
        JsonArray  arr = new JsonArray();
        //在创建一个全新的jsonObject对象
        JsonObject object2 = new JsonObject();
        object2.addProperty("反贼", "宋江");
        object2.addProperty("反贼年龄", 45);
        //添加到该数组的键值对的值中
        arr.add(object2);
        JsonObject object3 = new JsonObject();
        object3.addProperty("反贼", "武松");
        object3.addProperty("反贼年龄",23);
        arr.add(object3);
        //将数组添加到 我们构建的 json对象中
        object.add("梁山好汉", arr);
        //toStirng 方法打印 我们构建好的json数据
        System.out.println(object.toString());

    }
}
