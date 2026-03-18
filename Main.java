import java.util.Scanner;

public class Main {
    static private Scanner sc = new Scanner(System.in);
    static private UsuarioService us = new UsuarioService();

    public static void main(String[] args) {
        switch_MAINMENU();
    }

    // Metodo para chamar e mostrar o array de String do menuLOGIN
    public static void mostrar_MENU_LOGIN() {
        System.out.println(String.join("\n", MenuString.mainMENU));
    }

    public static void switch_MAINMENU() {
        int op;
        do {
            mostrar_MENU_LOGIN();
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    cadastro_USER();
                }
                case 2 -> {
                    login_USER();
                }
                case 0 -> {
                    System.out.println("Encerrando Programa...");
                }
                default -> {
                    System.out.println("Opção incorreta/inexistente");
                }
            }
        } while (op != 0);
    }

    public static void mostrar_MENU_MAIN() {
        System.out.println(String.join("\n", MenuString.secundaryMENU));
    }

    public static void switch_MENU_POS_LOGIN() {
        int op;
        do {
            mostrar_MENU_MAIN();
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1 -> {
                    verSaldo();
                }
                case 2 -> {
                    depositarSaldo();
                }
                case 3 -> {
                    sacarSaldo();
                }
                case 4 -> {
                    transferirSaldo();
                }
                case 0 -> {
                    System.out.println("Encerrando...");
                }
                default -> {
                    System.out.println("Opção incorreta/inexistente");
                }
            }
        } while (op != 0);
    }

    public static void cadastro_USER() {
        System.out.println("==========");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.println("==========");

        boolean cadastro_CHECK = us.cadastrar(nome, senha);

        if (cadastro_CHECK) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Nome já usado!");
        }
    }

    // METODOS SWITCH_MAIN_MENU
    public static void login_USER() {
        System.out.println("==================");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        boolean login_CHECK = us.logar(nome, senha);

        if (login_CHECK) {
            System.out.println("Seja Bem-Vindo!");
            switch_MENU_POS_LOGIN();
        } else {
            System.out.println("1. Nome ou Senha incorreto/a(s)");
            System.out.println("2. Conta inexistente! Crie uma conta antes de fazer login");
        }
    }

    // METODOS DO SWITCH_POS_LOGIN
    // case 1
    public static void verSaldo() {
        System.out.println("========== VER SALDO ==========");
        System.out.print("Nome da conta: ");
        String nome = sc.nextLine();
        boolean resultado = us.check_name(nome);
        if (resultado) {
            System.out.println("Saldo: R$ " + us.mostrarSaldo(nome));
        } else {
            System.out.println("Conta inexistente.");
        }
    }

    // case 2
    public static void depositarSaldo() {
        System.out.println("========== Depositar Saldo ==========");
        System.out.print("Nome da conta à depositar: ");
        String nome = sc.nextLine();
        System.out.print("Saldo: R$ ");
        double saldo = sc.nextDouble();
        sc.nextLine();
        // Tempo de espera...
        System.out.println("Processando...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Tempo excedido.");
        }
        String deposito = us.depositar(nome, saldo);
        System.out.println(deposito);
    }

    // case 3
    public static void sacarSaldo() {
        System.out.println("========== SAQUE ==========");
        System.out.print("Nome da conta: ");
        String nome = sc.nextLine();
        System.out.print("Saldo a sacar: R$ ");
        double saldo = sc.nextDouble();
        sc.nextLine();

        String resultado_saque = us.sacar(nome, saldo);
        System.out.println(resultado_saque);
    }

    // case 4
    public static void transferirSaldo() {
        System.out.println("========== TRANSFERẼNCIA ==========");
        System.out.print("Nome da conta: ");
        String nome = sc.nextLine();
        System.out.print("Transferir para: ");
        String nome_transfer = sc.nextLine();
        System.out.print("Valor à transferir: ");
        double saldo = sc.nextDouble();
        sc.nextLine();
        boolean resultado = us.check_name(nome_transfer);
        if (resultado) {
            boolean transferencia_CHECK = us.transferir(nome, nome_transfer, saldo);
            if (transferencia_CHECK) {
                System.out.println("Transferência de R$ " + saldo +
                        " para conta: " + nome_transfer + " concluida.");
            }
        } else {
            System.out.println("Conta inexistente!");
        }
    }
}
