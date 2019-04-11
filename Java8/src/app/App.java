package app;

public class App {
    public static void main(String[] args) throws Exception {
        
        System.out.println("Hello Java 8");

        int i = 0;
        int j = 1;

        try {
            int r = j/i;
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally{
            System.out.println("Pasa");
        }

    }
}