package contasapagar;

import java.time.Month;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
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
/*
    static int[] contaNum = new int[1000];
    static String[] contaDesc = new String[1000];
    static String[] contaBenef = new String[1000];
    static BigDecimal[] contaDebit = new BigDecimal[1000];
    static LocalDate[] contaData = new LocalDate[1000];
    static int totalContas = 0; // Quantas contas existem
    static boolean[] contaQuitada = new boolean[1000];
    static BigDecimal[] contaSaldo = new BigDecimal[1000]; // Quanto falta para pagar a conta
*/
    // ADAM: Para facilitar testes, fiz 5 documentos tipo placeholder. Para usar, é
    // só comentar as linhas acima e retirar o comentário destas.


     static int[] contaNum = { 1, 2, 3, 4, 5 };
     static String[] contaDesc = { "PLACEHOLDER1", "PLACEHOLDER2", "PLACEHOLDER3",
     "PLACEHOLDER4", "PLACEHOLDER5" };
     static String[] contaBenef = { "Adam Schneider", "Sadam Aschneider",
     "Damdam Neineider", "Adam Schneider", "Sadam Aschneider" };
     static BigDecimal[] contaDebit = { new BigDecimal("1000.00"), new
     BigDecimal("2000.00"), new BigDecimal("3000.00"),
     new BigDecimal("4000.00"),
     new BigDecimal("5000.00") };
     static LocalDate[] contaData = { LocalDate.of(2016, 3, 22), LocalDate.of(2016,
     11, 22), LocalDate.of(2016, 3, 14), LocalDate.of(2016, 4, 28),
     LocalDate.of(2016,
     4, 4) };
     static int totalContas = 5;
     static boolean[] contaQuitada = { false, false, false, false, false };
     static BigDecimal[] contaSaldo = new BigDecimal[1000];


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
                        consultarConta();
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

        // ADAM: Recebe uma String e converte em LocalDate com failsafe.
        LocalDate vencimentoFormatado = null;
        while (vencimentoFormatado == null) {
            System.out.print("VENCIMENTO (dd/mm/aaaa): ");
            String vencimento = sc.nextLine();
            try {
                vencimentoFormatado = LocalDate.parse(vencimento, sdf);
            } catch (DateTimeParseException e) {
                System.out.println("Formatação inválida! Por favor use dd/mm/aaaa.");
            }
        }

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
            contaData[totalContas] = vencimentoFormatado;
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

    static void consultarConta() {
        System.out.println("=== CONSULTA DE CONTA ===");

        // Lucas: Inserir documento
        System.out.print("DOCUMENTO: ");
        int documento = sc.nextInt();
        sc.nextLine();

        // Lucas: Busca o documento e retorna -1 se não encontrar
        int i = buscarConta(documento);
        if (i == -1) {
            System.out.println("Conta não encontrada");
            return;
        }


        System.out.println("DOCUMENTO: " + contaNum[i]);
        System.out.println("DESCRIÇÃO: " + contaDesc[i]);
        System.out.println("BENEFICIÁRIO: " + contaBenef[i]);
        System.out.println("VALOR ORIGINAL: " + contaSaldo[i]);
        System.out.println("FALTA PAGAR: " + contaDebit[i]);
        System.out.println("VENCIMENTO: " + contaData[i].format(sdf));

        // Lucas: Mostra se a conta já foi paga usando o vetor contaQuitada do Adam
        if (contaQuitada[i]) {
            System.out.println("CONTA QUITADA!");
        } else {
            System.out.println("CONTA EM ABERTO!");
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

    static void listContasAPagar() {
        // Luan: quaseNoVencimento é um vetor separado para não modificar tudo direto do contaData.
        // Além disso, serve apenas para os que não passaram da data de vencimento.
        LocalDate[] quaseNoVencimento = new LocalDate[totalContas];
        LocalDate hoje = LocalDate.now();
        int _index = 0;
        int tempTotalContas = 0;

        // Luan: outros vetores para usar no System.out para mostrar o restante dos dados.
        int[] tempContaNum = new int[totalContas];
        String[] tempContaDesc = new String[totalContas];
        String[] tempContaBenf = new String[totalContas];
        BigDecimal[] tempContaDebit = new BigDecimal[totalContas];

        // Luan: Preenche o vetor com as mesmas coisas que tem nos outros vetores, mas apenas com os que não venceram.
        for (int i = 0; i < totalContas; i++) {
            if (contaData[i].isAfter(hoje) || contaData[i].isEqual(hoje)) {
                quaseNoVencimento[_index] = contaData[i];
                tempContaNum[_index] = contaNum[i];
                tempContaDesc[_index] = contaDesc[i];
                tempContaBenf[_index] = contaBenef[i];
                tempContaDebit[_index] = contaDebit[i];
                _index += 1;
                tempTotalContas += 1;
            }
        }

        // Luan: Sort para arrumar a ordem por vencimento mais próximo da data atual do pc.
        for (int t = 0; t < tempTotalContas; t++) {
            LocalDate data1 = quaseNoVencimento[t];
            int _index1 = t;
            int tempNum = tempContaNum[t];
            String tempDesc = tempContaDesc[t];
            String tempBenf = tempContaBenf[t];
            BigDecimal tempDebit = tempContaDebit[t];

            for (int j = t + 1; j < tempTotalContas; j++) {
                if (quaseNoVencimento[j].isBefore(data1)) {
                    data1 = quaseNoVencimento[j];
                    _index1 = j;
                    tempNum = tempContaNum[j];
                    tempDesc = tempContaDesc[j];
                    tempBenf = tempContaBenf[j];
                    tempDebit = tempContaDebit[j];
                }
            }
            // Mexe no LocalDate.
            LocalDate temp1 = quaseNoVencimento[t];
            quaseNoVencimento[t] = data1;
            quaseNoVencimento[_index1] = temp1;
            // Mexe no tempContaNum.
            int temp2 = tempContaNum[t];
            tempContaNum[t] = tempNum;
            tempContaNum[_index1] = temp2;
            // Mexe no tempContaDesc.
            String temp3 = tempContaDesc[t];
            tempContaDesc[t] = tempDesc;
            tempContaDesc[_index1] = temp3;
            // Mexe no tempContaBenf.
            String temp4 = tempContaBenf[t];
            tempContaBenf[t] = tempBenf;
            tempContaBenf[_index1] = temp4;
            // Mexe no tempContaDebit.
            BigDecimal temp5 = tempContaDebit[t];
            tempContaDebit[t] = tempDebit;
            tempContaDebit[_index1] = temp5;
        }

        // Luan: Após o sort, ele imprime tudo com todos os detalhes.
        System.out.println("CONTAS A PAGAR POR DATA");
        System.out.println(hoje.format(sdf) + "\n");
        for (int i = 0; i < tempTotalContas; i++) {
            System.out.println("VENCIMENTO: " + quaseNoVencimento[i].format(sdf) + " | VALOR: "
            + tempContaDebit[i] + " | DOCUMENTO: " + tempContaNum[i] + " | DESCRIÇÃO: " + tempContaDesc[i]
            + " | BENEFICIÁRIO: " + tempContaBenf[i]);
        }

        System.out.println("==============================\n");
    }

    static void listContasVencidas() {
        // Cria vetores temporarios para não causar problemas nos principais do sistema.

        int[] tempContaNum = new int[totalContas];
        String[] tempContaDesc = new String[totalContas];
        String[] tempContaBenf = new String[totalContas];
        BigDecimal[] tempContaDebit = new BigDecimal[totalContas];
        LocalDate[] contasVencidas = new LocalDate[totalContas];

        // Luan: A variavel "hoje" é usado como condição.
        LocalDate hoje = LocalDate.now();
        // Variaveis.
        int _index = 0;
        int tempTotalContas = 0;

        // Luan: Busca encontrar quais já estão vencidas. Ou seja, já passaram do prazo de vencimento.
        for (int i = 0; i < totalContas; i++) {
            if (contaData[i].isBefore(hoje)) {
                contasVencidas[_index] = contaData[i];
                tempContaNum[_index] = contaNum[i];
                tempContaDesc[_index] = contaDesc[i];
                tempContaBenf[_index] = contaBenef[i];
                tempContaDebit[_index] = contaDebit[i];
                _index += 1;
                tempTotalContas += 1;
            }
        }

        for (int t = 0; t < tempTotalContas; t++) {
            LocalDate data1 = contasVencidas[t];
            int _index1 = t;
            int Num1 = tempContaNum[t];
            String Desc1 = tempContaDesc[t];
            String Benf1 = tempContaBenf[t];
            BigDecimal Debit1 = tempContaDebit[t];

            for (int i = t + 1; i < tempTotalContas; i++) {
                if (contasVencidas[i].isBefore(data1)) {
                    data1 = contasVencidas[i];
                    _index1 = i;
                    Num1 = tempContaNum[i];
                    Desc1 = tempContaDesc[i];
                    Benf1 = tempContaBenf[i];
                    Debit1 = tempContaDebit[i];
                }
            }
            // Mexe no contasVencidas.
            LocalDate temp1 = contasVencidas[t];
            contasVencidas[t] = data1;
            contasVencidas[_index1] = temp1;
            // Mexe no tempContaNum.
            int temp2 = tempContaNum[t];
            tempContaNum[t] = Num1;
            tempContaNum[_index1] = temp2;
            // Mexe no tempContaDesc.
            String temp3 = tempContaDesc[t];
            tempContaDesc[t] = Desc1;
            tempContaDesc[_index1] = temp3;
            // Mexe no tempContaBenf.
            String temp4 = tempContaBenf[t];
            tempContaBenf[t] = Benf1;
            tempContaBenf[_index1] = temp4;
            // Mexe no tempContaDebit.
            BigDecimal temp5 = tempContaDebit[t];
            tempContaDebit[t] = Debit1;
            tempContaDebit[_index1] = temp5;
        }

        // Imprime a lista.
        System.out.println("CONTAS VENCIDAS");
        System.out.println(hoje.format(sdf) + "\n");
        for (int i = 0; i < tempTotalContas; i++) {
            System.out.println("VENCIMENTO: " + contasVencidas[i].format(sdf) + " | VALOR: "
                    + tempContaDebit[i] + " | DOCUMENTO: " + tempContaNum[i] + " | DESCRIÇÃO: " + tempContaDesc[i]
                    + " | BENEFICIÁRIO: " + tempContaBenf[i]);
        }

        System.out.println("==============================\n");
    }

    static void balancoDespesas() {
        System.out.println("====================\n");
        System.out.println("Escolha um ano para mostrar as despesas:\n");
        int anoEscolhido = 0;

        while (!sc.hasNextInt()) {
            System.out.println("Opção inválida.");
            sc.next();
            System.out.println("Escolha um ano para mostrar as despesas:\n");
        }
        anoEscolhido = sc.nextInt();

        BigDecimal[] debitoMes = new BigDecimal[12];
        String[] maiorBenf = new String[12];
        // Luan: inicia todos os meses com valor 0.
        for (int i = 0; i < 12; i++) {
            debitoMes[i] = BigDecimal.valueOf(0);
        }

        System.out.println("BALANÇO DE DESPESAS DE " + anoEscolhido);
        System.out.println("JANEIRO A DEZEMBRO");
        System.out.println("MÊS | TOTAL DE DESPESAS | MAIOR BENEFICIÁRIO\n");
        for (int i = 0; i < 12; i++) {
            BigDecimal debitoMensalTotal = BigDecimal.valueOf(0);
            String temp1 = "a";
            double compararDebit = 0;
            int mes = i;

            for (int j = 0; j < totalContas; j++) {
                if ((contaData[j].getMonthValue()) == mes && contaData[j].getYear() == anoEscolhido) {
                    debitoMensalTotal = debitoMensalTotal.add(contaDebit[j]);

                    double compararDebit2 = contaDebit[j].doubleValue();
                    if (compararDebit2 > compararDebit) {
                        compararDebit = compararDebit2;
                        temp1 = contaBenef[j];
                    }
                }
            }
            debitoMes[i] = debitoMes[i].add(debitoMensalTotal);

            // Luan: Garante que não há um mês sem nenhum débito.
            // Se houver um mês sem nada devendo, ele não imprime e pula.
            if (debitoMes[i].compareTo(BigDecimal.valueOf(0)) > 0) {
                System.out.println(
                        Month.of(i).getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase()
                        + " | R$" + debitoMes[i]
                        + " | " + temp1
                );
            }
        }

        System.out.println("==============================\n");
    }

    static void gerarRelatorio() {
        System.out.println("==================\nGERANDO RELATÓRIO... \n");
        int opcaoRelatorio = -1;

        do {
            System.out.println("RELATORIO\n");
            System.out.println("1 - CONTAS A PAGAR");
            System.out.println("2 - CONTAS VENCIDAS");
            System.out.println("3 - BALANÇO DE DESPESAS");
            System.out.println("0 - VOLTAR\n");

            if (sc.hasNextInt()) {
                opcaoRelatorio = sc.nextInt();
                sc.nextLine();

                switch (opcaoRelatorio) {
                    // Os prints são testes.
                    case 1:
                        listContasAPagar();
                        break;
                    case 2:
                        listContasVencidas();
                        break;
                    case 3:
                        balancoDespesas();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção inválida. Digite um número.");
                sc.next();
            }
        } while (opcaoRelatorio != 0);
        System.out.println("==================\n");
    }

    public static void main(String[] args) {
        Menu();
        sc.close();
    }
}
