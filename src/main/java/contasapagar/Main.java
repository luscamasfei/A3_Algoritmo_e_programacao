package contasapagar;

import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// ADAM: Por favor manter em mente que usei BigDecimal para armazenar a variavel de débito de cada documento quando forem escrever o código de suas partes :)
// ADAM: LocalDate usa o formato ANO-MÊS-DIA.

public class Main {

    static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Scanner sc = new Scanner(System.in);

    /*
     * As informações de uma conta são:
     * • No. do Documento (int)
     * • Descrição (String)
     * • Beneficiário (String)
     * • Valor do Débito (BigDecimal)
     * • Data de Vencimento (LocalDate) XXXX, XX, XX
     */

    static int[] contaNum = new int[1000];
    static String[] contaDesc = new String[1000];
    static String[] contaBenef = new String[1000];
    static BigDecimal[] contaDebit = new BigDecimal[1000];
    static LocalDate[] contaData = new LocalDate[1000];
    static int totalContas = 0; // Quantas contas existem
    static boolean[] contaQuitada = new boolean[1000];
    static BigDecimal[] contaSaldo = new BigDecimal[1000]; // Quanto falta para pagar a conta

    // ADAM: Para facilitar testes, fiz 5 documentos tipo placeholder. Para usar, é
    // só comentar as linhas acima e retirar o comentário destas.

    /*
     * static int[] contaNum = { 1, 2, 3, 4, 5 };
     * static String[] contaDesc = { "PLACEHOLDER1", "PLACEHOLDER2", "PLACEHOLDER3",
     * "PLACEHOLDER4", "PLACEHOLDER5" };
     * static String[] contaBenef = { "Adam Schneider", "Sadam Aschneider",
     * "Damdam Neineider", "Adam Schneider", "Sadam Aschneider" };
     * static BigDecimal[] contaDebit = { new BigDecimal("1000.00"), new
     * BigDecimal("2000.00"), new BigDecimal("3000.00"),
     * new BigDecimal("4000.00"),
     * new BigDecimal("5000.00") };
     * static LocalDate[] contaData = { LocalDate.of(2021, 1, 1), LocalDate.of(2021,
     * 1, 1), LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 1),
     * LocalDate.of(2021,
     * 1, 1) };
     * static int totalContas = 5;
     * static boolean[] contaQuitada = { false, false, false, false, false };
     * static BigDecimal[] contaSaldo = new BigDecimal[1000];
     */

    /**
     * @param documento Número do documento (contaNum)
     * @return Devole o valor da posição da conta, ou -1 se não encontrar
     */
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
            System.out.println("OPÇÃO: ");

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
        // Lucas: Menu de cadastro já com failsafe
        int opcaoCadastro = -1;
        do {
            System.out.println("CADASTRO DE CONTAS");
            System.out.println("1 - INCLUSÃO");
            System.out.println("2 - ALTERAÇÃO");
            System.out.println("3 - CONSULTA");
            System.out.println("4 - EXCLUSÃO");
            System.out.println("0 - RETORNAR");
            System.out.print("OPÇÃO: ");

            if (sc.hasNextInt()) {
                opcaoCadastro = sc.nextInt();
                sc.nextLine();

                switch (opcaoCadastro) {
                    case 1:
                        incluirConta();
                        break;
                    case 2:
                        alterarConta();
                        break;
                    case 3:
                        System.out.println("=== CONSULTA === ");
                        break;
                    case 4:
                        System.out.println("=== EXCLUSÃO ===");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Você precisa digitar um número!");
                sc.nextLine();
            }
        } while (opcaoCadastro != 0);
        System.out.println("==================\n");
    }

    static void incluirConta() {
        System.out.println("=== INCLUSÃO DE CONTA ===");

        // Lucas: Inserir documento
        System.out.print("DOCUMENTO: ");
        // Failsafe
        while (!sc.hasNextInt()) {
            System.out.println("Número de documento inválido.");
            sc.next();
            System.out.println("Digite o número do documento.\n");
        }
        int documento = sc.nextInt();
        sc.nextLine();

        // Lucas: Não permite cadastrar contas repetidas
        if (buscarConta(documento) != -1) {
            System.out.println("Este documento já está cadastrado.");
            return;
        }

        // Lucas: Inserir a descrição
        System.out.print("DESCRIÇÃO: ");
        String descricao = sc.nextLine();

        // Lucas: Inserir beneficiário
        System.out.print("BENEFICIÁRIO: ");
        String beneficiario = sc.nextLine();

        // Lucas: Inserir valor
        System.out.print("VALOR: ");
        BigDecimal valor = sc.nextBigDecimal();
        sc.nextLine();

        // Lucas: Transforma o texto em data usando o formato que o Adam criou
        System.out.print("VENCIMENTO (dd/mm/aaaa): ");
        LocalDate vencimento = LocalDate.parse(sc.nextLine(), sdf);

        // Lucas: Confirmação de Sim ou Não utilizando o equals para ignorar o tamanho
        // da letra
        System.out.print("CONFIRMA INCLUSÃO (S/N)? ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            contaNum[totalContas] = documento;
            contaDesc[totalContas] = descricao;
            contaBenef[totalContas] = beneficiario;
            contaDebit[totalContas] = valor;
            contaSaldo[totalContas] = valor;
            contaData[totalContas] = vencimento;
            // Lucas: Aumenta o contador de contas depois de guardar
            totalContas++;
            System.out.println("Conta incluída com sucesso!");
        } else {
            System.out.println("Inclusão cancelada!");
        }
    }

    static void alterarConta() {
        System.out.println("=== ALTERAÇÃO DE CONTA ===");

        // Lucas: Inserir documento
        System.out.print("DOCUMENTO: ");
        // Failsafe
        while (!sc.hasNextInt()) {
            System.out.println("Número de documento inválido.");
            sc.next();
            System.out.println("Digite o número do documento.\n");
        }
        int documento = sc.nextInt();
        sc.nextLine();

        // Lucas: Busca o documento e retorna -1 se não encontrar
        int i = buscarConta(documento);
        if (i == -1) {
            System.out.println("Conta não encontrada");
            return;
        }

        System.out.println("DESCRIÇÃO: " + contaDesc[i]);
        System.out.println("BENEFICIÁRIO: " + contaBenef[i]);
        System.out.println("VALOR: " + contaDebit[i]);
        System.out.println("VENCIMENTO: " + contaData[i].format(sdf));

        System.out.println("\nDigite os novos dados:");

        // Lucas: Inserir nova descrição
        System.out.print("DESCRIÇÃO: ");
        String descricao = sc.nextLine();

        // Lucas: Inserir novo beneficiario
        System.out.print("BENEFICIÁRIO: ");
        String beneficiario = sc.nextLine();

        // Lucas: O valor só muda se a conta ainda não teve pagamento
        BigDecimal valor = contaDebit[i];
        // Lucas: O compareTo retorna 3 valores: -1, 0 e 1 sendo 0 os dois valores
        // iguais
        boolean semPagamento = contaDebit[i].compareTo(contaSaldo[i]) == 0;

        if (semPagamento) {
            System.out.print("VALOR: ");
            String textoValor = sc.nextLine();
            if (!textoValor.isEmpty()) {
                valor = new BigDecimal(textoValor.replace(",", "."));
            }
        } else {
            System.out.println("O valor não pode ser alterado, essa conta já foi paga!");
        }

        // Lucas: Inserir novo vencimento
        System.out.print("VENCIMENTO (dd/mm/aaaa): ");
        String textoData = sc.nextLine();
        LocalDate vencimento = contaData[i];
        if (!textoData.isEmpty()) {
            vencimento = LocalDate.parse(textoData, sdf);
        }

        // Lucas: Confirma alteração utilizando o equals
        System.out.print("CONFIRMA ALTERAÇÃO (S/N)? ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            contaDesc[i] = descricao;
            contaBenef[i] = beneficiario;
            if (semPagamento) {
                contaDebit[i] = valor;
                contaSaldo[i] = valor;
            }
            contaData[i] = vencimento;
            System.out.println("Conta alterada com sucesso!");
        } else {
            System.out.println("Alteração cancelada!");
        }
    }

    static void baixarConta() {

        System.out.println("==================\nBAIXANDO CONTA... \n");

        System.out.println("MOVIMENTAÇÃO - BAIXA DE CONTA\n");
        System.out.println("Digite o número do documento.\n");
        System.out.println("==================\n");

        // Recebe número do documento, com failsafe.
        while (!sc.hasNextInt()) {
            System.out.println("Número de documento inválido.");
            sc.next();
            System.out.println("Digite o número do documento.\n");
        }
        int documento = sc.nextInt();

        if (buscarConta(documento) != -1) {

            int i = buscarConta(documento);
            String baixarcontaData = contaData[i].format(sdf);

            System.out.println("Documento Encontrado!");
            System.out.println("Número: " + contaNum[i]);
            System.out.println("Descrição: " + contaDesc[i]);
            System.out.println("Beneficiário: " + contaBenef[i]);
            System.out.println("Validade: " + baixarcontaData);

            if (contaQuitada[i] == true) {
                System.out.println("Status: FECHADA/QUITADA");
                System.out.println("==================");
                System.out.println("VALOR ATUAL: " + contaDebit[i]);
                System.out.println("Esta conta ja foi fechada!");
            } else {
                System.out.println("Status: ABERTA");
                System.out.println("==================");
                System.out.println("VALOR ATUAL: " + contaDebit[i]);
                System.out.println("\nDigite o valor a ser baixado.");

                BigDecimal baixa_valorPago = sc.nextBigDecimal();

                if ((baixa_valorPago.compareTo(contaDebit[i])) < 0) {
                    BigDecimal baixa_valorFinal = contaDebit[i].subtract(baixa_valorPago);
                    contaDebit[i] = baixa_valorFinal;
                    System.out.println("\nConta baixada com sucesso.");

                } else if ((baixa_valorPago.compareTo(contaDebit[i])) > 0) {
                    System.out.println(
                            "\nO valor inserido foi maior do que o débito da conta. Favor inserir um valor menor ou igual ao débito.");

                } else if ((baixa_valorPago.compareTo(contaDebit[i])) == 0) {
                    contaDebit[i] = contaDebit[i].subtract(baixa_valorPago);
                    System.out.println("\nA conta de ID " + contaNum[i] + " foi quitada.\n");
                    contaQuitada[i] = true;

                }
            }

        } else {
            System.out.println("o valor procurado não foi encontrado, tente novamente.\n");
        }

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