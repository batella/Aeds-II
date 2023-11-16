import java.util.*;

public class Palindromo {

    public static boolean palindromo(String str) {
        boolean resp = true;
        
        char [] vetPal = new char[str.length()];
        for(int i = 0; i < str.length();i++){
            vetPal[i] = str.charAt(i);
        }


        for (int i = 0; i < str.length()/2; i++) {
            if(vetPal[i] != vetPal[vetPal.length - i - 1]){
                resp = false;
            }
        }
        return resp;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str = new String();
        while(!(str = sc.nextLine()).equals("FIM")){
            boolean resp = palindromo(str);
            if(resp == true)
            System.out.println("SIM");
            else
            System.out.println("NAO");
        }

        sc.close();

    }
}