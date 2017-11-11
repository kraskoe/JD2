package com.pvt.app.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Configuration
@Import(BaseCDPlayerConfig.class)
@ComponentScan(basePackages = {"com.pvt.app.beans"}, excludeFilters = {})
//@ComponentScan(basePackages = {"com.pvt.app.beans"}, excludeFilters = {@ComponentScan.Filter(value = AutowiredCD.class, type = FilterType.ANNOTATION)})
//@ComponentScan(basePackages = {"com.pvt.app.beans"}, excludeFilters = {@ComponentScan.Filter(value = Component.class, type = FilterType.ANNOTATION, pattern = {"rammstein"})})
//@ComponentScan(basePackageClasses = {CardigansCD.class})
public class CDPlayerConfig {
}
