public class Conta {
    private int num_conta;
    private double saldo;

    public Conta(int num_conta) {
        this.num_conta = num_conta;
        this.saldo = 0;
    }

    public int getNUMCONTA() {
        return num_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
