package contasapagar;

import java.util.Scanner;
import java.math.BigDecimal;

// ADAM: Por favor manter em mente que usei BigDecimal para armazenar a variavel de débito de cada documento quando forem escrever o código de suas partes :)

public class Main {

    static Scanner sc = new Scanner(System.in);

    /*
     * As informações de uma conta são:
     * • No. do Documento (int)
     * • Descrição (String)
     * • Beneficiário (String)
     * • Valor do Débito (double)
     * • Data de Vencimento (String) XX-XX-XXXX
     */

    static int[] contaNum = new int[1000];
    static String[] contaDesc = new String[1000];
    static String[] contaBenef = new String[1000];
    static BigDecimal[] contaDebit = new BigDecimal[1000];
    static String[] contaData = new String[1000];
    static int totalContas = 0; // Quantas contas existem
    static BigDecimal[] contaSaldo = new BigDecimal[1000]; // Quanto falta para pagar a conta

    // Devole o valor da posição da conta ou -1 se não encontrar
    static int buscarConta(int documento) {
        for (int i = 0; i < totalContas; i++) {
            if (contaNum[i] == documento) {
                return i;
            }
        }
        return -1;
    }

    public static void Menu() {
        int opcao = 0;
        do {
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

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();

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
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção inválida. Digite um número.");
                sc.next();
            }
        } while (opcao != 0);
    }

    static void cadastrarConta() {
        System.out.println("==================\nCADASTRANDO CONTA... \n");
        // Cadastrar conta. Tela 1.1
        System.out.println("==================\n");
    }

    static void baixarConta() {
        int i, flag;
        System.out.println("==================\nBAIXANDO CONTA... \n");

        System.out.println("MOVIMENTAÇÃO - BAIXA DE CONTA\n");
        System.out.println("Digite o número do documento.\n");
        System.out.println("==================\n");
        int baixa_numDoc = sc.nextInt();

        flag = 0;
        for (i = 0; i < contaNum.length; i++) {
            if (contaNum[i] == baixa_numDoc) {

                System.out.println("Documento Encontrado!");
                System.out.println("Número: " + contaNum[i]);
                System.out.println("Descrição: " + contaDesc[i]);
                System.out.println("Beneficiário: " + contaBenef[i]);
                System.out.println("Validade: " + contaData[i]);
                System.out.println("==================\n");
                System.out.println("VALOR ATUAL: " + contaDebit[i]);
                System.out.println("Digite o valor a ser pago.");

                BigDecimal baixa_valorPago = sc.nextBigDecimal();
                if ((baixa_valorPago.compareTo(contaDebit[i])) < 0) {
                    BigDecimal baixa_valorFinal = contaDebit[i].subtract(baixa_valorPago);
                    contaDebit[i] = baixa_valorFinal;
                    System.out.println("\n Conta baixada com sucesso.");

                } else if ((baixa_valorPago.compareTo(contaDebit[i])) > 0) {
                    System.out.println(
                            "\n O valor inserido foi maior do que o débito da conta. Favor inserir um valor menor ou igual ao débito.");

                } else if ((baixa_valorPago.compareTo(contaDebit[i])) == 0) {
                    BigDecimal baixa_valorFinal = contaDebit[i].subtract(baixa_valorPago);
                    contaDebit[i] = baixa_valorFinal;
                    System.out.println("\n Conta quitada.");
                }

                flag = 1;
            }
        }
        if (flag == 0) {
            System.out.println("o valor procurado não foi encontrado, tente novamente.\n");
        }

        System.out.println("==================\n");
        // Baixar conta. Tela 1.2
        System.out.println("==================\n");
    }

    static void gerarRelatorio() {
        System.out.println("==================\nGERANDO RELATÓRIO... \n");
        // Gerar relatório. Tela 1.3
        System.out.println("==================\n");
    }

    public static void main(String[] args) {
        Menu();
        sc.close();
    }

}