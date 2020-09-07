package com.sort_system;

public class User {

    private int id;
    private  String name;
    private  int seq;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                '}';
    }

    @Override
    public boolean equals(Object arg0) {
        User o = (User) arg0;
        return this.id==o.getId();
    }

}
