# Sistema de Contas a Pagar

## Trabalho A3 da disciplina **Algoritmos e Programação**.

## Alunos

| Nome Completo | RA | GitHub |
| :--- | :--- | :--- |
| **Lucas Maffei** | 10726210757 | [luscamasfei](https://github.com/luscamasfei) |
| **Adam Schneider de Andrade** | 15619194973 | [adamschneider34](https://github.com/adamschneider34) |
| **Luan Schultz Ceretta** | 1072629711 | [SupDan01](https://github.com/SupDan01) |

## Contextualização

Toda empresa tem várias despesas ao longo do mês, como fornecedores, aluguel,
energia e serviços. Controlar bem o pagamento dessas despesas ajuda a empresa a
saber quanto vai gastar em um período e, com isso, planejar melhor o seu
dinheiro e os seus investimentos.

Este projeto é um **Sistema de Contas a Pagar** para a empresa fictícia
**XYZ Comércio de Produtos Ltda.** O controle é feito atualizando todos os dias
a situação de cada conta. Primeiro, são levantadas todas as contas da empresa,
tanto as que se repetem todo mês quanto as que aparecem de vez em quando. Depois,
elas são cadastradas no sistema, que passa a mostrar exatamente a situação
financeira da empresa.

Cada conta possui as seguintes informações:

- Número do documento
- Descrição
- Beneficiário (para quem a conta é paga)
- Valor do débito
- Data de vencimento

## Funcionalidades

**Cadastro de contas**
- Inclusão, alteração, consulta e exclusão de contas.
- O número do documento não pode ser alterado; para mudá-lo, é preciso excluir
  a conta e cadastrá-la de novo.
- Toda operação pede confirmação antes de ser feita.

**Baixa de contas**
- Registro de pagamento total ou parcial de uma conta.
- A cada pagamento, o saldo que ainda falta pagar é atualizado.

**Relatórios** (exibidos na tela)
- **Contas a pagar por período**: todas as contas em aberto, em ordem de
  vencimento.
- **Contas vencidas por período**: contas atrasadas, em ordem de vencimento.
- **Balanço de despesas por período**: total gasto em cada mês e o maior
  beneficiário do mês.

## Como o sistema funciona

- Interface simples no console, baseada em menus, com mensagens explicativas.
- Todas as entradas são verificadas: se algo for digitado errado, o sistema
  avisa e pede para digitar de novo.
- O código é dividido em sub-rotinas.
- Os dados ficam guardados em vetores.
- Desenvolvido em Java, com projeto Maven.

## Declaração de uso de IA

- **Ferramenta:** Claude Opus 5.5 (Anthropic)
- **Finalidade:**
  - revisão e formatação do texto apresentado neste README;
  - revisão do código-fonte e ajuda para encontrar e corrigir erros;
  - esclarecimento de dúvidas sobre Java;
  - orientação sobre boas práticas de programação e de uso do GitHub.
- **Revisão:** o texto gerado foi lido, conferido com o enunciado do trabalho e
  ajustado pelos integrantes do grupo antes de ser publicado.
- **Código-fonte:** a IA não foi usada para gerar o código do sistema.
