package app;

public class App implements MyInterface {
    public static void main(String[] args) throws Exception {
 
        System.out.println("Hello Java 8");
        
        int i = 0;
        int j = 1;

        try {
            int r = j/i;
        } catch (ArithmeticException e) {
           // System.err.println(e.toString());
        }catch(Exception eArithmeticException){
            System.out.println("Hello");
            System.err.println("Erro");
        }
            finally{
           // System.out.println("Pasa");
        }

        // app.helloWorld(); // default method
        // System.out.println(MyInterface.value); // static variable on Interface
        Student me = SingletonExample.getFirstStudent();
        System.out.println(me.getName());


        MyInterface myInterface = (x) -> { System.out.println(me.getId() * 4); };
        myInterface.multiplication(me);

    }

    @Override
    public void multiplication(Student s) {

    }
}