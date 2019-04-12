package app;

/**
 * Palindrome
 */
public class Palindrome {

    public Boolean isPalindromeBoolean(String str){

        for (int i = 0; i < str.length(); i++) {
           // System.out.println(str.length() - (1+i));
            if(str.charAt(i) != str.charAt( str.length() - (1+i)  )){
                return false;
            }
        }
        return true;
    }
    
}