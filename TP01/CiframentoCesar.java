
import java.util.*;

public class CiframentoCesar {
    public static String cifra(String str) {
        String strCifrada = "";
        for (int i = 0; i < str.length(); i++) {
            strCifrada += (char) (str.charAt(i) + 3);
        }

        return strCifrada;
    }

    public static void main(String[] args) throws Exception {
        String str = new String();
        while (!(str = MyIO.readLine()).equals("FIM")) {
            MyIO.println(cifra(str));
        }
    }
}
