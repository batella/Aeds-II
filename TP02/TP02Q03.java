import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

class TP02Q03 {
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

    public static boolean pesqSeq(Jogador[] vet, Jogador x) {
        boolean resp = false;
        int n = vet.length;

        for (int i = 0; i < n; i++) {
            if (vet[i].getNome() == x.nome) {
                resp = true;
                i = n;
            }
        }
        return resp;
    }

    public static boolean isFim(String s) {
        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args) {
        String path = "./tmp/players.csv";
        List<Jogador> jogadores = new ArrayList<Jogador>();
        int a = 0;

        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        

        while (!isFim(id)) {
            try (RandomAccessFile br = new RandomAccessFile(path,"r")) {
                int player_id = -1;
                if (!isFim(id)) {
                    String line = br.readLine();
                    line = br.readLine();
                    boolean achou = false;
                    while (player_id != Integer.parseInt(id) && !achou) {
                        String[] array = line.split(",",-1);
                        player_id = Integer.parseInt(array[0]);
                       // System.out.println(array[0]);
                        if (player_id == Integer.parseInt(id)) {
                            achou = true;
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
                            Jogador jogador = new Jogador(player_id, nomeJogador, altura, peso, faculdade, ano, cidade,
                                    estado);
                                    
                                    jogadores.add(jogador);
                                    a++;
                        }
                        else{
                            line = br.readLine();
                        }

                    }
                    line = br.readLine();
                    br.seek(0);
                    
                }
            } catch (IOException e) {
                System.out.println("ERRO: " + e.getMessage());
            }
              id = sc.nextLine();
        }

        id = sc.nextLine();
        
        boolean resp = false;
        while (!isFim(id)) {
            resp = false;
            for (int x = 0; x < jogadores.size(); x++) {
                if (jogadores.get(x).getNome().equals(id)) {
                    resp = true;
                }
            }
            if (resp)
                System.out.println("SIM");
            else
                System.out.println("NAO");
            id = sc.nextLine();
            if(isFim(id))
                break;
        }
    }

}