package app;

import java.util.ArrayList;

public class App implements MyInterface {
    public static void main(String[] args) throws Exception {
 
        System.out.println("Hello Java 8");


        int [] b = new int [2];
        String myName = "Hang Tu";
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
        // System.out.println(me.getName()); // get static member of the class;


        MyInterface myInterface = (x) -> { System.out.println(me.getId() * 4); };
        myInterface.multiplication(me); // Print the lambda expression;


        /*Collections*/

        ArrayList <Student> aList = new ArrayList <Student> (); 
        aList.add(me);
        aList.add(new Student(2,"Juan"));
        aList.add(new Student(3,"Pepe"));
        aList.add(new Student(4,"Roe"));
        aList.add(new Student(5,"Sandra"));

        System.out.println(aList.get(1).getName());

        aList.forEach(x -> {
            //x.setName(Integer.toString(x.getId()));
        });

        aList.forEach(x -> {
             System.out.println(x.getName());
        });

    }

    @Override
    public void multiplication(Student s) {

    }
}