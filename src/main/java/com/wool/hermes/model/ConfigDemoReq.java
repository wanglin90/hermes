package com.wool.hermes.model;

/**
 * Created by wanglin on 16-10-6.
 */
public class ConfigDemoReq {

    private String name;
    private Integer age;
    private Integer offset;
    private Integer limit;

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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ConfigDemoReq{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
