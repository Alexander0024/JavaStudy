package selfstudy;

import java.io.Serializable;

/**
 * Created by Alexander on 2016/6/15.
 */
public class Study07_Serialization {
    public static void main(String[] args) {
        Person alex = new Person();
        System.out.println(alex.toString());
    }
}

class Person implements Serializable {
    public Person() {
        this.name = "Alexander";
        this.password = "admin888";
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private String name;
    private transient String password;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
