package org.progmatic.edu;

import java.util.ArrayList;
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
