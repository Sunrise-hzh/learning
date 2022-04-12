package com.learning.jdbc;

/**
 * @author huangzihua
 * @date 2021-04-20
 */
public class Son extends Father {
    public Son() {
        super();
        System.out.println("son construct");
    }

    public static void main(String[] args) {
        Father son = new Son();
        Father father = new Father();
    }
}
