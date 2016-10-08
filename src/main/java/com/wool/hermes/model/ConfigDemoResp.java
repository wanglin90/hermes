package com.wool.hermes.model;

import com.wool.hermes.model.database.ConfigDemo;

import java.util.List;

/**
 * Created by wanglin on 16-10-6.
 */
public class ConfigDemoResp {

    private int totalNum;
    private List<WoolConfigDemo> list;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<WoolConfigDemo> getList() {
        return list;
    }

    public void setList(List<WoolConfigDemo> list) {
        this.list = list;
    }

    public static class WoolConfigDemo{

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
            return "WoolConfigDemo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
