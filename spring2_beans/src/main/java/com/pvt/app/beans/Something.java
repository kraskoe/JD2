package com.pvt.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Yauheni Krasko on 08.11.2017.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Something {
    private Long id;
    private String someName;
    private SomeField someField;
    private List<Integer> numbers;
    private Set<String> letters;
    private Map<Integer, String> someMap;
    private Properties properties;

    public Something(String name, SomeField field, List<Integer> list, Set<String> set, Map<Integer, String> map, Properties props){
        this.someName = name;
        this.someField = field;
        numbers = list;
        letters = set;
        someMap = map;
        properties = props;
    }

    @Override
    public String toString(){
        return "Something{ " +
                "id: " + id +
                ", someName: " + someName +
                ", someField: " + someField +
                ", list of numbers: " + numbers +
                ", set of letters: " + letters +
                ", someMap: " + someMap +
                ", properties: " + properties +
                " }";
    }

    public void init(){
        System.out.println("Initialization of Something");
    }

    public void destroy(){
        System.out.println("Destroying of Something");
    }
}
