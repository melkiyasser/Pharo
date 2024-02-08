package PackageJustToTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JavadocGenerator {

    static String generateJavaDoc(Class<?> targetClass) {
        var documentation = new StringBuilder("Javadoc for: " + targetClass.getSimpleName() + "\n");

        documentation.append(targetClass.getSimpleName())
                .append(" is a subclass of ")
                .append(targetClass.getSuperclass().getSimpleName())
                .append("\n");

        for (Field field : targetClass.getDeclaredFields())
            documentation.append("it has an instance variable ").append(field.getName()).append("\n");

        documentation.append("it is defined in package ").append(targetClass.getPackageName())
                .append("\nit has the following methods:\n");

        for (Method method : targetClass.getDeclaredMethods())
            documentation.append(method.getName()).append(" - ").append(method.toGenericString()).append("\n");

        return documentation.toString();
    }

    public static void main(String[] args) {
        System.out.println(generateJavaDoc(Point.class));
    }

    public class Point {
        int x, y ;
        String title ;

        void setX(int x) {
            this.x = x ;
        }

        void setY(int y) {
            this.y = y ;
        }

        int getX() {
            return 0;
        }

        int getY() {
            return 0;
        }

        String getTitle(){
            return title ;
        }
        void setTitle(String title){
            this.title = title ;
        }
    }

}
