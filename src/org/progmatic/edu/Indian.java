package org.progmatic.edu;

import java.util.List;

public class Indian {
    String name;
    String tribe;
    String sex;
    int age;
    List<String> tools;

    public Indian(String name, String tribe, String sex, int age, List<String> tools) {
        this.name = name;
        this.tribe = tribe;
        this.sex = sex;
        this.age = age;
        this.tools = tools;
    }

    public String getName() {
        return name;
    }

    public String getTribe() {
        return tribe;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public List<String> getTools() {
        return tools;
    }

    @Override
    public String toString() {
        return "Indián{" +
                "name='" + name + '\'' +
                ", tribe='" + tribe + '\'' +
                ", neme='" + sex + '\'' +
                ", age=" + age +
                ", tools=" + tools +
                '}';
    }
}
