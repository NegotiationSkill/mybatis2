package enitry;

import java.io.Serializable;

/**
 * @author 刘棋军
 * @date2019-03-17
 */

public class Person implements Serializable {
    private  int id;
    private  String name;
    private int age;
    private  boolean sex;


    public Person(int id, String name, int age, boolean sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Person() {
        super();
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Person(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name+"-"+this.age+"-"+this.id+"-"+this.sex;
    }
}
