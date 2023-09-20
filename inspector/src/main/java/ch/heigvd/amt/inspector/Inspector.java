package ch.heigvd.amt.inspector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Inspector {

    /**
     * Inspect an object and print its class name, fields and methods.
     * @param object the object to inspect
     */
    public static void inspect(Object object) {
        try {
            Class<?> clazz = object.getClass();
            System.out.println("Class name: " + clazz.getName());

            System.out.println("Fields:");
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(" - " + field.getName() + ": " + field.get(object));
            }

            System.out.println("Methods:");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(" - " + method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Demonstrate the use of the Inspector class by inspecting a dog.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        inspect(new Dog("Buddy", 5));
    }
}
