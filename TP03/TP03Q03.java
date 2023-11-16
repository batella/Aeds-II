
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

class Jogador {
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
        return "## " + nome + " ## " + altura + " ## " + peso
                + " ## " + anoNasc + " ## " + universidade + " ## "
                + cidadeNasc + " ## " + estadoNasc + " ## ";
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

class Lista {

    private Jogador[] array;
    private int n;

    public Lista() {
        this(86);
    }

    public Lista(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
    }

    public void inserirInicio(Jogador jogador) throws Exception {

        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }

        array[0] = jogador;
        n++;
    }

    public void inserirFim(Jogador jogador) throws Exception {

        if (n >= array.length) {
            throw new Exception("Erro ao inserir!");
        }

        array[n] = jogador;
        n++;
    }

    public void inserir(int pos, Jogador jogador) throws Exception {

        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        }

        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }

        array[pos] = jogador;
        n++;
    }

    public Jogador removerInicio() throws Exception {

        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[0];
        n--;

        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public Jogador removerFim() throws Exception {

        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        return array[--n];
    }

    public Jogador remover(int pos) throws Exception {

        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        Jogador resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }

        return resp;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }

    public boolean pesquisar(String str) {
        for (int i = 0; i < n; i++) {
            if (str.compareTo(array[i].getNome()) == 0)
                return true;
        }
        return false;
    }

    public void PrintAll() {
        for (int i = 0; i < n; i++) {            
            System.out.println("[" + i + "] " + array[i].toString());
        }
    }

    public void sort() {
        Jogador[] tmp = new Jogador[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;

        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(tamHeap);
        }

        int tamHeap = n;
        while (tamHeap > 1) {
            swap(1, tamHeap--);
            reconstruir(tamHeap);
        }

        tmp = array;
        array = new Jogador[n];
        for (int i = 0; i < n; i++) {
            array[i] = tmp[i + 1];
        }
        sortname();
    }

    public void construir(int tamHeap) {
        for (int i = tamHeap; i > 1 && array[i].getPeso() > array[i / 2].getPeso(); i /= 2) {
            swap(i, i / 2);
        }
    }

    public void reconstruir(int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap);
            if (array[i].getPeso() < array[filho].getPeso()) {
                swap(i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public int getMaiorFilho(int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap || (array[2 * i].getPeso() > array[2 * i + 1].getPeso())) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    public void swap(int i, int j) {
        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void sortname() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].getNome().compareTo(array[j + 1].getNome()) > 0
                        && array[j].getPeso() == array[j + 1].getPeso()) {
                    // Troca os elementos
                    Jogador temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

class TP03Q03 {

    public static boolean isFim(String s) {
        return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void swap(Jogador[] array, int i, int j) {
        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void sortname(Jogador[] array, int n) {

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;

            for (int j = i + 1; j < n; j++) {
                if (array[j] != null) {
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

    public static void main(String[] args) throws Exception {
        String path = "/tmp/players.csv";
        // List<Jogador> jogadores = new ArrayList<Jogador>();
        Lista lista = new Lista(1000);
        Scanner sc = new Scanner(System.in);
        String id = new String();
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
             id = sc.nextLine();
            while (!isFim(id)) {// while para resgatar o jogador do array contendo todos os jogadores em ordem
                                // pelo id e inserir ele no inicio da lista
                //id = sc.nextLine();
                Jogador auxi = arrCompleto[Integer.parseInt(id)];
                lista.inserirFim(auxi);
                id = sc.nextLine();
            }

            int n = sc.nextInt();// recebendo a quantidade de operações
            Jogador[] removidos = new Jogador[100];
            int z = 0;

            for (int i = 0; i <= n; i++) {// iterando as quantidades de operações
                String linha = sc.nextLine();
                String[] arrLinha = linha.split(" ");
                if ("I".equals(arrLinha[0])) {
                    int idJogador = Integer.parseInt(arrLinha[1]);
                    Jogador auxi = arrCompleto[idJogador];
                    lista.inserirFim(auxi);
                } else if ("R".equals(arrLinha[0])) {
                    removidos[z++] = lista.removerFim();
                } 
            }
            for (int i = 0; i < removidos.length && removidos[i] != null; i++) {
                System.out.println("(R) " + removidos[i].getNome());
            }
            lista.PrintAll();

        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        sc.close();
    }

}
