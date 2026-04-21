package com.zilong.dubao;

import java.io.Serializable;

public class Fish implements Serializable {
    String name;
    int num;
    double price;
    Fish(String name,int num,double price){
        this.name=name;
        this.num=num;
        this.price=price;

    }
}
