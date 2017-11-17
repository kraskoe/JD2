package com.pvt.app.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.inject.Qualifier;

/**
 * Created by Yauheni Krasko on 09.11.2017.
 */

@Configuration
@ComponentScan(basePackages = {"com.pvt.app.beans"},
//        includeFilters = {@ComponentScan.Filter(value = AutowiredCD.class, type = FilterType.ANNOTATION)})
        includeFilters = {@ComponentScan.Filter(value = Component.class, type = FilterType.ANNOTATION)},
        excludeFilters = {@ComponentScan.Filter(value = AutowiredCD.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = CardigansCD.class, type = FilterType.ASSIGNABLE_TYPE),
        @ComponentScan.Filter(value = CDPlayerConfig.class, type = FilterType.ASSIGNABLE_TYPE),
        @ComponentScan.Filter(value = BaseCDPlayerConfig.class, type = FilterType.ASSIGNABLE_TYPE),
//        @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern="com.pvt.app..*Disc+"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*CD")
        })
public class DVDPlayerConfig {
}
