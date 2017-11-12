package com.pvt.app.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Sniffer implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] args = invocation.getArguments();
        TransferData data = (TransferData) args[0];
        StringBuilder sb = new StringBuilder(100);
        sb.append(" Data was stolen:" + data.getTransferrableString());
        System.out.println(sb.toString());
        if(data.getTransferrableString().matches(".*disable.*")) {
            System.out.println("Invocation method wasn't executed");
            return null;
        } else return invocation.proceed();
    }
}