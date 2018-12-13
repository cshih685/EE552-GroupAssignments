/*
* HW - Bean_Reflection -- get all methods
* @author Chieh Shih
 * CPE-552
 * Chieh Shih
 * 10431509
 *Ge Chang
 * 10410233
*/
package session13;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;


public class HWReflection {
    public static void getAttributes(Class c) throws NoSuchMethodException {
        Method[] methods = c.getMethods();
        ArrayList<String> getters = new ArrayList<>();
        HashMap<String, Method> setters = new HashMap<>();
        System.out.println(methods.length);
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            System.out.println(name);
            if (name .startsWith("get")){
                getters.add(name);
//                System.out.println(name);
            }
            else if (name.startsWith("set")){
                setters.put(name, c.getMethod(name, String.class));
//                System.out.println(name);
            }
        }
        for (int i = 0; i < getters.size(); i++) {
            String name = getters.get(i).substring(3);
            Method setter = setters.get(name);
            if (setter != null) {
                System.out.println(setter);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        String className = "session13.HWBean";
        Class c = Class.forName(className);
//        System.out.println(c);
        System.out.println("Your class is named: " + c.getName());
        Method[] methods = c.getMethods();
        //print out all Bean methods
        for (Method m : methods) {
            System.out.println("method: " + m.getName());
        }
}
}
