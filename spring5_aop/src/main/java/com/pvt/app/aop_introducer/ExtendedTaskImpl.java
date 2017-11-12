package com.pvt.app.aop_introducer;

import org.springframework.stereotype.Service;

public class ExtendedTaskImpl implements DoExtendedTask {
    @Override
    public void doExtraJob() {
        System.out.println("Класс-аспект ExtendedTaskImpl выполняет дополнительную работу.");
    }
}
