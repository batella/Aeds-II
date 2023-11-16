import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

class TP02Q13 {
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

   /* public static void sortname(Jogador[] array,int n) {


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

    }  */

/*public static void sortUniv(Jogador[] array,int n) {


        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;

            for (int j = i + 1; j < n; j++) {
                if(array[j] != null){
                if (array[j].getUniversidade().compareTo(array[indiceMenor].getUniversidade()) < 0) {
                    indiceMenor = j;
                }
            }
            }
            // Troque os array de posição
            Jogador temp = array[i];
            array[i] = array[indiceMenor];
            array[indiceMenor] = temp;
        }

    } */

    public static void SortUniv(Jogador[] array,int n) {
		for (int i = 1; i < n; i++) {
			Jogador tmp = array[i];
         int j = i - 1;

         while ((j >= 0) && (array[j].getUniversidade().compareTo(tmp.getUniversidade()) > 0)) {
            array[j + 1] = array[j];
            j--;
         }
         array[j + 1] = tmp;
      }
	}

    public static void SortName(Jogador[] array,int n) {
		for (int i = 1; i < n; i++) {
			Jogador tmp = array[i];
         int j = i - 1;

         while ((j >= 0) && (array[j].getNome().compareTo(tmp.getNome()) > 0)) {
            array[j + 1] = array[j];
            j--;
         }
         array[j + 1] = tmp;
      }
	}

   


    public static void CoutingSort(Jogador[]array,int n) {
        //Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[getMaior(array) + 1];
        Jogador[] ordenado = new Jogador[n];
  
        //Inicializar cada posicao do array de contagem 
          for (int i = 0; i < count.length; count[i] = 0, i++);
  
        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; count[array[i].getAltura()]++, i++);
  
        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for(int i = 1; i < count.length; count[i] += count[i-1], i++);
  
        //Ordenando
        for(int i = n-1; i >= 0; ordenado[count[array[i].getAltura()]-1] = array[i], count[array[i].getAltura()]--, i--);
  
        //Copiando para o array original
        for(int i = 0; i < n; array[i] = ordenado[i], i++);
     }

    public static int getMaior(Jogador[]array) {
	   int maior = array[0].getAltura();

		for (int i = 0; i < array.length; i++) {
         if(maior < array[i].getAltura()){
            maior = array[i].getAltura();
         }
		}
	   return maior;	
	}

    public static void mergesort(Jogador [] array, int esq, int dir) {
        if (esq < dir){
           int meio = (esq + dir) / 2;
           mergesort(array,esq, meio);
           mergesort(array,meio + 1, dir);
           intercalar(array,esq, meio, dir);
        }
     }
  
     /**
      * Algoritmo que intercala os elementos entre as posicoes esq e dir
      * @param int esq inicio do array a ser ordenado
      * @param int meio posicao do meio do array a ser ordenado
      * @param int dir fim do array a ser ordenado
      */ 
     public static void intercalar(Jogador[] array ,int esq, int meio, int dir){
        int n1, n2, i, j, k;
  
        //Definir tamanho dos dois subarrays
        n1 = meio-esq+1;
        n2 = dir - meio;
  
        Jogador[] a1 = new Jogador[n1+1];
        Jogador[] a2 = new Jogador[n2+1];
  
        //Inicializar primeiro subarray
        for(i = 0; i < n1; i++){
           a1[i] = array[esq+i];
        }
  
        //Inicializar segundo subarray
        for(j = 0; j < n2; j++){
           a2[j] = array[meio+j+1];
        }
  
        //Sentinela no final dos dois arrays
        a1[i] = a2[j] = null;
  
        //Intercalacao propriamente dita
        for(i = j = 0, k = esq; k <= dir; k++){
          if(a1[i] != null && a2[j] != null){
            array[k] = (a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) < 0) ? a1[i++] : a2[j++];
          }
        }
     }

//a1[i].getNome().compareTo(a2[j].getNome()) < 0

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
        SortName(arrJogador,aux);
        SortUniv(arrJogador,aux);
        for (int i = 0; i < aux; i++){
            if(arrJogador[i] != null){
            MyIO.println(arrJogador[i].toString());
            }

        }
    
    

    }

}

