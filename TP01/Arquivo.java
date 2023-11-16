import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class Arquivo {
    public static void main(String[] args) throws Exception {
        int numero = MyIO.readInt();
        RandomAccessFile arquivo = new RandomAccessFile("entrada.txt", "rw");
        double lidos;
        for (int i = 0; i < numero; i++) {
            lidos = MyIO.readDouble();
            arquivo.writeDouble(lidos);
        }
        arquivo.close();

        RandomAccessFile leitura = new RandomAccessFile("entrada.txt", "r");
        int fim = (8 * (numero - 1));
        double numeroEscrito;
        int inteirinho;
        for (int i = fim; i >= 0; i = i - 8) {
            leitura.seek(i);
            numeroEscrito = leitura.readDouble();

            if (numeroEscrito % 1 == 0) {
                inteirinho = (int) (numeroEscrito);
                MyIO.println(inteirinho);
            } else {
                MyIO.println(numeroEscrito);
            }
        }
        leitura.close();
    }
}
