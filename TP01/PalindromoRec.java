import java.util.*;

public class PalindromoRec {

    public static boolean palindromoRec(String str, int start, int end) {
        
        if(start >= end){
            return true;
        }
        if(str.charAt(start) != str.charAt(end)){
            return false;
        }
        return palindromoRec(str,start + 1, end - 1);
}

    public static boolean Palindromo(String str){
        return palindromoRec(str, 0, str.length() - 1);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str = new String();
        while(!(str = sc.nextLine()).equals("FIM")){
            boolean resp = Palindromo(str);
            if(resp == true)
            System.out.println("SIM");
            else
            System.out.println("NAO");
        }

        sc.close();

    }
}