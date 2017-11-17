package com.pvt.app.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Configuration
//@ComponentScan("com.pvt.app.beans")
//@ComponentScan(basePackages = {"com.pvt.app.beans"})
@ComponentScan(basePackageClasses = {CardigansCD.class})
public class CDPlayerConfig {
}
