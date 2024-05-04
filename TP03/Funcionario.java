import java.util.Scanner;
public class Funcionario {
    private String nome;
    private double SalarioBruto;
    private double taxa;
    private double salarioLiquido;
    
    public Funcionario(String nome, double salarioBruto, double taxa) {
        this.nome = nome;
        SalarioBruto = salarioBruto;
        this.taxa = taxa;
    }

    public void mostrarDados(){
         this.salarioLiquido = this.SalarioBruto - this.taxa;
        System.out.println("FUNCIONÁRIO: "+ this.nome+", Salário Líquido: R$ "+salarioLiquido);
    }

    public double aumento(double x){
        double bonus = (this.SalarioBruto * x) / 100;
        this.salarioLiquido += bonus;
        return salarioLiquido;
    }

    public void mostrarAumento(){
    System.out.println("Salario modificado: "+ this.nome+", R$: "+salarioLiquido);
   }


public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Nome:");
    String nome = sc.nextLine();
    System.out.println("Salario Bruto:");
    double bruto = sc.nextDouble();
    System.out.println("Taxa:");
    double taxa = sc.nextDouble();

    Funcionario funcionario = new Funcionario(nome, bruto, taxa);
    funcionario.mostrarDados();

    System.out.println("Qual porcentagem quer aumentar no salário?");
    Double aumento = sc.nextDouble();
    funcionario.aumento(aumento);
    funcionario.mostrarAumento();


    
}
    
}
