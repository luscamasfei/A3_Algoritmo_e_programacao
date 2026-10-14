package contasapagar;

import java.time.LocalDate;

public class Conta {
    private int documento;
    private String descricao;
    private String beneficiario;
    private double valor;
    private double saldo;
    private LocalDate vencimento;
    
    public Conta(int documento, String descricao, String beneficiario, double valor, LocalDate vencimento) {
        this.documento = documento;
        this.descricao = descricao;
        this.beneficiario = beneficiario;
        this.valor = valor;
        this.vencimento = vencimento;
        this.saldo = valor;
    }

    public int getDocumento() {
        return documento;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }
}


