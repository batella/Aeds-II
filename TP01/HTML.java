
//Importaçao de dependencias
import java.io.*;
import java.net.URL;

class HTML {

    // Funçao para ler a pagina em formato HTML
    public static String readHTML(String endWeb, String nomePag) {
        // Definiçao de dados
        String HTML = "";
        String r = "";

        try {
            // Definiçao de dados
            int x[] = new int[26];
            URL url = new URL(endWeb);
            BufferedReader buff = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((HTML = buff.readLine()) != null) {
                // Verificando cada um dos caracteres
                for (int i = 0; i < HTML.length(); i++) {
                    if (HTML.charAt(i) == 'a') {
                        x[1]++;
                    } else if (HTML.charAt(i) == 'e') {
                        x[2]++;
                    } else if (HTML.charAt(i) == 'i') {
                        x[3]++;
                    } else if (HTML.charAt(i) == 'o') {
                        x[4]++;
                    } else if (HTML.charAt(i) == 'u') {
                        x[5]++;
                    } else if (HTML.charAt(i) == 225) {
                        x[6]++;
                    } else if (HTML.charAt(i) == 233) {
                        x[7]++;
                    } else if (HTML.charAt(i) == 237) {
                        x[8]++;
                    } else if (HTML.charAt(i) == 243) {
                        x[9]++;
                    } else if (HTML.charAt(i) == 250) {
                        x[10]++;
                    } else if (HTML.charAt(i) == 224) {
                        x[11]++;
                    } else if (HTML.charAt(i) == 232) {
                        x[12]++;
                    } else if (HTML.charAt(i) == 236) {
                        x[13]++;
                    } else if (HTML.charAt(i) == 242) {
                        x[14]++;
                    } else if (HTML.charAt(i) == 249) {
                        x[15]++;
                    } else if (HTML.charAt(i) == 227) {
                        x[16]++;
                    } else if (HTML.charAt(i) == 245) {
                        x[17]++;
                    } else if (HTML.charAt(i) == 226) {
                        x[18]++;
                    } else if (HTML.charAt(i) == 234) {
                        x[19]++;
                    } else if (HTML.charAt(i) == 238) {
                        x[20]++;
                    } else if (HTML.charAt(i) == 244) {
                        x[21]++;
                    } else if (HTML.charAt(i) == 251) {
                        x[22]++;
                    } else if (HTML.charAt(i) >= 'a' && HTML.charAt(i) <= 'z') {
                        x[23]++;
                    } else if (HTML.charAt(i) == '<') {
                        if (i + 3 < HTML.length()) {
                            if (HTML.charAt(i + 1) == 'b' && HTML.charAt(i + 2) == 'r' && HTML.charAt(i + 3) == '>') {
                                x[24]++;
                                x[23] = x[23] - 2;
                            }
                        }
                        if (i + 5 < HTML.length()) {
                            if (HTML.charAt(i + 1) == 't' && HTML.charAt(i + 2) == 'a' && HTML.charAt(i + 3) == 'b' &&
                                    HTML.charAt(i + 4) == 'l' && HTML.charAt(i + 5) == 'e'
                                    && HTML.charAt(i + 6) == '>') {
                                x[25]++;
                                x[23] = x[23] - 3;
                                x[1]--;
                                x[2]--;
                            }
                        }
                    }
                }
            }
            // Unindo tudo em uma unica variavel para que assim seja printada na tela
            r = "a(" + x[1] + ") e(" + x[2] + ") i(" + x[3] + ") o(" + x[4] + ") u(" + x[5] +
                    ") " + (char) 225 + "(" + x[6] + ") " + (char) 233 + "(" + x[7] + ") " + (char) 237 + "(" + x[8]
                    + ") " + (char) 243 + "("
                    + x[9]
                    + ") " + (char) 250 + "(" + x[10] +
                    ") " + (char) 224 + "(" + x[11] + ") " + (char) 232 + "(" + x[12] + ") " + (char) 236 + "(" + x[13]
                    + ") " + (char) 242 + "(" + x[14]
                    + ") " + (char) 249 + "("
                    + x[15] +
                    ") " + (char) 227 + "(" + x[16] + ") " + (char) 245 + "(" + x[17] + ") " + (char) 226 + "(" + x[18]
                    + ") " + (char) 234 + "(" + x[19] + ") " + (char) 238 + "("
                    + x[20] +
                    ") " + (char) 244 + "(" + x[21] + ") " + (char) 251 + "(" + x[22] + ") consoante(" + x[23] +
                    ") <br>(" + x[24] + ") <table>(" + x[25] + ") " + nomePag;
            buff.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // Retorno da variavel ja pronta para ser printada
        return (r);
    }
       // funcao de parada do programa
    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        // Definiçao de dados
        String[] endWeb = new String[1000];
        String[] pagWeb = new String[1000];
        int numEntrada = 0;
        // Leitura da entrada padrao
        do {
            pagWeb[numEntrada] = MyIO.readLine();
            if (isFim(pagWeb[numEntrada]) == false)
                endWeb[numEntrada] = MyIO.readLine();
        } while (isFim(pagWeb[numEntrada++]) == false);
        numEntrada = numEntrada - 1; // Desconsiderar ultima linha contendo a palavra FIM

        // Printando os resultados na tela
        for (int i = 0; i < numEntrada; i = i + 1) {
            System.out.println(readHTML(endWeb[i], pagWeb[i]));
        }
    }
}
