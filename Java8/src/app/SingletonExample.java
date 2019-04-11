package app;
/**
 * SingletonExample
 */
public class SingletonExample {

    private static Student firstStudent;
    
    public static Student getFirstStudent(){
        if(firstStudent == null){
             firstStudent = new Student(1,"Hang Tu");
        }
        return firstStudent;
    }
}