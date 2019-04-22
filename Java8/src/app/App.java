package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class App implements MyInterface, Runnable {

    private volatile Boolean flag = false;

    public static void main(String[] args) throws Exception {

        System.out.println("Hello Java 8");

        // int[] b = new int[2];
        // String myName = "Hang Tu";
        int i = 0;
        int j = 1;

        try {
            int r = j / i;
            System.out.println(r);
        } catch (ArithmeticException e) {
            // System.err.println(e.toString());
        } catch (Exception eArithmeticException) {
            System.out.println("Hello");
            System.err.println("Erro");
        } finally {
            // System.out.println("Pasa");
        }

        // app.helloWorld(); // default method
        // System.out.println(MyInterface.value); // static variable on Interface
        Student me = SingletonExample.getFirstStudent();
        // System.out.println(me.getName()); // get static member of the class;

        MyInterface myInterface = (x) -> {
            // System.out.println(me.getId() * 4);
        };
        myInterface.multiplication(me); // Print the lambda expression;

        /* Collections */

        ArrayList<Student> aList = new ArrayList<Student>();
        aList.add(me);
        aList.add(new Student(2, "Juan"));
        aList.add(new Student(3, "Pepe"));
        aList.add(new Student(4, "Roe"));
        aList.add(new Student(5, "Handra"));
        aList.add(new Student(5, "Alma"));
        aList.add(new Student(5, "Hugo"));
        aList.add(new Student(5, "Carl"));

        // System.out.println(aList.get(1).getName());

        aList.forEach(x -> {
            // x.setName(Integer.toString(x.getId()));
        });

        aList.forEach(x -> {
            // System.out.println(x.getName());
        });

        HashMap<Integer, Student> hashMap = new HashMap<Integer, Student>();
        hashMap.put(1, me);
        hashMap.put(null, aList.get(1));

        hashMap.forEach((x, student) -> {
            // System.out.println(student.getName());
        });

        // MAP CREATE A NEW LIST
        List<Student> bList = aList.stream().map(x -> new Student(x.getId(), x.getName() + " B"))
                .collect(Collectors.toList());
        bList.forEach(student -> {
            // System.out.println(student.getName());
        });

        // STREAM FILTER

        List<Student> cList = aList.stream().filter(x -> x.getName().startsWith("H")).collect(Collectors.toList());
        cList.forEach(student -> {
            // System.out.println(student.getName());
        });

        // SORT FILTER
        List<Student> dList = aList.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());

        dList.forEach(student -> {
            // System.out.println(student.getName());
        });

        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindromeBoolean("anitalavalatinas"));

        Thread th1 = new Thread(new App());
        Thread th2 = new Thread(new App());
        th1.start();
        // th1.sleep(1000);
        th2.start();

        Set<String> hSet = new HashSet<>();
        hSet.add("b");
        hSet.add("a");
        hSet.add("c");
        hSet.add("3");

        hSet.forEach(x -> {
            // System.out.println(x);
        });

        Set<String> tSet = new TreeSet<>();
        tSet.add("b");
        tSet.add("a");
        tSet.add("c");
        tSet.add("b");

        tSet.forEach(x -> {
            // System.out.println(x);
        });

        ConcurrentHashMap m = new ConcurrentHashMap();
        m.put(100, "Hello");
        m.put(101, "Geeks");
        m.put(102, "Geeks");
        // m.put(null, "Geeks");

        m.forEach((x, y) -> {
            System.out.println(x + "" + y);
        });
    }

    @Override
    public void multiplication(Student s) {

    }

    @Override
    public void run() {
        // Thread th = Thread.currentThread();
        // System.out.println(this.flag);
        this.flag = true;
        System.gc();
    }
}