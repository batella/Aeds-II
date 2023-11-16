import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

class TP02Q05 {
    public static class Jogador {
        // atributos
        private int id;
        private String nome;
        private int altura;
        private int peso;
        private String universidade;
        private int anoNasc;
        private String cidadeNasc;
        private String estadoNasc;

        // construtores

        public Jogador() {

        }

        public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNasc, String cidadeNasc,
                String estadoNasc) {
            this.id = id;
            this.nome = nome;
            this.altura = altura;
            this.peso = peso;
            this.universidade = universidade;
            this.anoNasc = anoNasc;
            this.cidadeNasc = cidadeNasc;
            this.estadoNasc = estadoNasc;
        }

        // getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getAltura() {
            return altura;
        }

        public void setAltura(int altura) {
            this.altura = altura;
        }

        public int getPeso() {
            return peso;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

        public String getUniversidade() {
            return universidade;
        }

        public void setUniversidade(String universidade) {
            this.universidade = universidade;
        }

        public int getAnoNasc() {
            return anoNasc;
        }

        public void setAnoNasc(int anoNasc) {
            this.anoNasc = anoNasc;
        }

        public String getCidadeNasc() {
            return cidadeNasc;
        }

        public void setCidadeNasc(String cidadeNasc) {
            this.cidadeNasc = cidadeNasc;
        }

        public String getEstadoNasc() {
            return estadoNasc;
        }

        public void setEstadoNasc(String estadoNasc) {
            this.estadoNasc = estadoNasc;
        }

        // metodo toString
        public String toString() {
            return "[" + id + " ## " + nome + " ## " + altura + " ## " + peso
                    + " ## " + anoNasc + " ## " + universidade + " ## "
                    + cidadeNasc + " ## " + estadoNasc + "]";
        }

        // metodo clone
        public Jogador clone() {
            Jogador JogadorNovo = new Jogador();
            JogadorNovo.id = this.id;
            JogadorNovo.nome = this.nome;
            JogadorNovo.altura = this.altura;
            JogadorNovo.peso = this.peso;
            JogadorNovo.universidade = this.universidade;
            JogadorNovo.anoNasc = this.anoNasc;
            JogadorNovo.cidadeNasc = this.cidadeNasc;
            JogadorNovo.estadoNasc = this.estadoNasc;

            return JogadorNovo;
        }

    }

    public static boolean isFim(String s) {
        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void swap(Jogador[] array, int i, int j) {
        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void sortname(Jogador[] array,int n) {


        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;

            for (int j = i + 1; j < n; j++) {
                if(array[j] != null){
                if (array[j].getNome().compareTo(array[indiceMenor].getNome()) < 0) {
                    indiceMenor = j;
                }
            }
            }
            // Troque os array de posição
            Jogador temp = array[i];
            array[i] = array[indiceMenor];
            array[indiceMenor] = temp;
        }

    }

    /*
     * public static void sort(Jogador[] array) {
     * for (int i = 0; i < array.length; i++) {
     * int menor = i;
     * for (int j = (i + 1); j < array.length; j++) {
     * if (array[menor].getNome().compareTo(array[j].getNome()) > 0) {
     * menor = j;
     * }
     * }
     * swap(array, menor, i);
     * }
     * }
     */

    /*
     * public void sort() {
     * for (int i = 0; i < (arrayJog.length - 1); i++) {
     * int menor = i;
     * for (int j = (i + 1); j < arrayJog.length; j++){
     * if (arrayJog[menor].getNome().compareTo(arrayJog[j].getNome()) > 0){
     * menor = j;
     * }
     * }
     * Jogador temp = arrayJog[menor];
     * arrayJog[menor] = arrayJog[i];
     * arrayJog[i] = temp;
     * }
     * }
     */

    public static void main(String[] args) {
        String path = "/tmp/players.csv";
        List<Jogador> jogadores = new ArrayList<Jogador>();
        Scanner sc = new Scanner(System.in);
        String id = MyIO.readLine();
        Jogador[] arrJogador = new Jogador[3922];
        int aux = 0;
        Jogador[] arrCompleto = new Jogador[3922];
        try (RandomAccessFile br = new RandomAccessFile(path, "r")) {
            String line = br.readLine();
            for (int i = 0; i < arrCompleto.length; i++) {
                line = br.readLine();
                String[] array = line.split(",", -1);
                String nomeJogador = array[1];
                int altura = Integer.parseInt(array[2]);
                int peso = Integer.parseInt(array[3]);
                String faculdade = array[4];
                if (faculdade == "") {
                    faculdade = "nao informado";
                }
                int ano = Integer.parseInt(array[5]);
                String cidade = array[6];
                if (cidade == "") {
                    cidade = "nao informado";
                }
                String estado = array[7];
                if (estado == "") {
                    estado = "nao informado";
                }
                arrCompleto[i] = new Jogador(Integer.parseInt(array[0]), nomeJogador, altura, peso, faculdade, ano,
                        cidade, estado);

            }
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    
        while (!isFim(id)) {
            arrJogador[aux++] = arrCompleto[Integer.parseInt(id)];
        id = MyIO.readLine();

        }
        
        // ordenando array
        sortname(arrJogador,aux);
        for (int i = 0; i < aux; i++){
            if(arrJogador[i] != null){
            MyIO.println(arrJogador[i].toString());
            }

        }
    
    

    }

}

