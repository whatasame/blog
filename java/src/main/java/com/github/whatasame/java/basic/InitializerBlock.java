package com.github.whatasame.java.basic;

public class InitializerBlock {

    static {
        System.out.println("static initializer block");
    }

    {
        System.out.println("instance initializer block");
    }

    public InitializerBlock() {
    }

    public InitializerBlock(final String msg) {
        System.out.println("constructor: " + msg);
    }
}
