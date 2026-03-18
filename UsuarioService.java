import java.util.Map;
import java.util.HashMap;

public class UsuarioService {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private int contadorConta = 1;
    private int contadorUser = 1;

    public boolean cadastrar(String nome, String senha) {
        if (usuarios.containsKey(nome)) { // verifica se a chave <String> já existe no map
            return false;
        }
        Conta conta = new Conta(contadorConta);
        Usuario user = new Usuario(contadorUser, nome, senha, conta);
        usuarios.put(nome, user);
        contadorConta++;
        contadorUser++;
        return true;
    }

    public boolean logar(String nome, String senha) {
        if (!usuarios.containsKey(nome)) {
            return false;
        }
        Usuario user = usuarios.get(nome);
        if (user.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    public double mostrarSaldo(String nome) {
        if (usuarios.containsKey(nome)) {
            Usuario user = usuarios.get(nome);
            double saldo = user.getConta().getSaldo();
            return saldo;
        }
        System.out.println("Usuário não encontrado");
        return 0;
    }

    public String depositar(String nome, double valor) {

        if (!usuarios.containsKey(nome)) {
            return "Usuário não encontrado.";
        }

        if (valor <= 0.00) {
            return "Valor inválido.";
        }
        Usuario user = usuarios.get(nome);

        Conta conta = user.getConta();

        conta.setSaldo(conta.getSaldo() + valor);

        return "Depósito realizado!";
    }

    public String sacar(String nome, double valor) {

        if (!usuarios.containsKey(nome)) {
            return "Usuário não encontrado";
        }

        Usuario user = usuarios.get(nome);

        Conta conta = user.getConta();

        if (conta.getSaldo() >= valor && valor >= 0.00) {
            conta.setSaldo(conta.getSaldo() - valor);

            return "Saque concluido!";
        }

        if (conta.getSaldo() <= 0.00 || conta.getSaldo() <= valor) {
            return "Saldo insuficiente.";
        }
        return "Saldo inválido!";
    }

    public boolean transferir(String nome, String nome_transfer, double saldo) {
        Usuario user1 = usuarios.get(nome);
        Conta conta1 = user1.getConta();

        Usuario user2 = usuarios.get(nome_transfer);
        Conta conta2 = user2.getConta();

        if (usuarios.containsKey(nome) && usuarios.containsKey(nome_transfer) && saldo >= 0.01
                && conta1.getSaldo() >= saldo) { // Verifica se o nome existe
            conta1.setSaldo(conta1.getSaldo() - saldo);
            conta2.setSaldo(conta2.getSaldo() + saldo);
            return true;
        }
        return false;
    }

    public boolean check_name(String nome) {
        if (usuarios.containsKey(nome)) {
            return true;
        }
        return false;
    }
}
