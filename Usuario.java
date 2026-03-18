public class Usuario {
    private int id;
    private String nome;
    private String senha;
    Conta conta;

    public Usuario(int id, String nome, String senha, Conta conta) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
