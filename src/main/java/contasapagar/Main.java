package contasapagar;

import java.util.Scanner;

public class Main {

    static void cadastrarConta() {
        System.out.println("CADASTRANDO CONTA...");
        // Cadastrar conta. Tela 1.1
    }

    static void baixarConta() {
        System.out.println("BAIXANDO CONTA...");
        // Baixar conta. Tela 1.2
    }

    static void gerarRelatorio() {
        System.out.println("GERANDO RELATÓRIO...");
        // Gerar relatório. Tela 1.3
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //
        System.out.println("Olá mundo!");
        System.out.println("Lucas Maffei");
        System.out.println("Adam Schneider de Andrade");
        System.out.println("Luan Schultz Ceretta");
        //

        System.out.println("CONTAS A PAGAR - A3 de Algoritmos e Programação.");
        System.out.println("===");
        System.out.println("MENU PRINCIPAL");
        System.out.println("1 - CADASTRO DE CONTAS");
        System.out.println("2 - BAIXA DE CONTAS");
        System.out.println("3 - RELATORIOS");
        System.out.println("0 - FINALIZAR");

        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                cadastrarConta();
                break;
            case 2:
                baixarConta();
                break;
            case 3:
                gerarRelatorio();
                break;
            case 0:
                System.exit(0);
        }

        sc.close();
    }

}