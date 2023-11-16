
import java.util.*;

public class Cifrarec {
    public static String cifra(String str,int i) {
        String strCifrada = "";
        if(i == str.length()-1 ){
            strCifrada += (char) (str.charAt(i) + 3);
        }
        else{
            strCifrada += ((char) (str.charAt(i) + 3)) + cifra(str, i+1);
        }
        

        return strCifrada;
    }

    public static void main(String[] args) throws Exception {
        String str = new String();
        while (!(str = MyIO.readLine()).equals("FIM")) {
            MyIO.println(cifra(str,0));
        }
    }
}
