package app;

/**
 * MyInterface
 */
public interface MyInterface {

    static final int value = 1;

    public void multiplication(Student s);
    
    default void helloWorld(){
        int x = 1;
        int y = 2;
        System.out.println(x+y);
    }

    default int helloWorldInt(){
        return 5;
    }
    
}