package com.lottchina.xdbao.demo;

public class SingletonDemo {
    private volatile static SingletonDemo instance;
    private SingletonDemo(){} 
    public static SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
}