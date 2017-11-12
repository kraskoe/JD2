package com.pvt.app.proxy;

public class TransferService {
    public void transfer(TransferData data) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Transferred value ").append(data.getTransferrableString());
        System.out.println(sb.toString());
    }
}
