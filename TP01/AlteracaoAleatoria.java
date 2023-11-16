import java.util.*;
public class AlteracaoAleatoria {
    public static String alteracao(String str){
        Random gerador = new Random();
        gerador.setSeed(4);
        char letraNova = ((char)('a' + (Math.abs(gerador.nextInt())%26)));
        char letraAserSubstituida = ((char)('a' + (Math.abs(gerador.nextInt())%26)));
        char [] letras  = new char[str.length()];


        for(int i = 0; i<str.length(); i++){
            letras[i] = str.charAt(i);
        }

        for(int i = 0; i<str.length(); i++){
            if(letras[i] == letraAserSubstituida){
                letras[i] = letraNova;
            }
        }

        String resultado = new String(letras);


        
    return resultado;
    }
    
    public static void main(String[] args) {
        String str = new String();
        while(!(str = MyIO.readLine()).equals("FIM")){
            MyIO.println(alteracao(str));
        }
    }
}
