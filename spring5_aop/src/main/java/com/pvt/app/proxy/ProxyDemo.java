package com.pvt.app.proxy;

import org.springframework.aop.framework.ProxyFactory;

public class ProxyDemo {
    private static TransferService target;
    private static TransferService proxy;
    public static void init() {
        target = new TransferService();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new Sniffer());
        proxyFactory.setTarget(target);
        proxy = (TransferService) proxyFactory.getProxy();
    }
    public static void main(String [] args) {
        init();
        process(new TransferData("Kto hodit v gosti po utram"));
        System.out.println("----- BACK DOOR -----");
        processProxy(new TransferData("Tot postupaet mudro"));
        processProxy(new TransferData("Taram-param disable pama-pam"));
    }
    public static void process(TransferData data) {
        target.transfer(data);
    }
    public static void processProxy(TransferData data) {
        proxy.transfer(data);
    }
}