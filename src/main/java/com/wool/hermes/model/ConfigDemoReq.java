package com.wool.hermes.model;

/**
 * Created by wanglin on 16-10-6.
 */
public class ConfigDemoReq {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ConfigDemoReq{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
