/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wang.blair.Personbook;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blair
 */
public class HelperForData {
    public static List<Person> generateSamplePersonRecords() {
        List<Person> people = new ArrayList<>();
        
        people.add(new Person("Andrew Bob"));
        people.add(new Person("Charlie Dennis"));
        people.add(new Person("Evan Foucault"));
        people.add(new Person("Gary Habermas"));
        people.add(new Person("Ivan Jung"));
        people.add(new Person("Kevin Larry"));
        people.add(new Person("Michael Noland"));
        
        return people;
    }
}
