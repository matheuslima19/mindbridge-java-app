import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class LoginCadConsole {

    public static void main(String[] args) {

        Scanner leitorMenu = new Scanner(System.in);
        Scanner leitorSubmenu = new Scanner(System.in);
        Scanner leitorInput = new Scanner(System.in);

        Integer opcao = 0;
        Integer opcaoUsuario = 0;
        Integer opcaoAdmin = 0;

        List<String> usuarios = new ArrayList<>();
        List<String> tipoAcessos = new ArrayList<>();
        List<String> senhas = new ArrayList<>();

        Boolean existeUsuario = false;
        Boolean isAdmin = false;

        String username = "";
        String password = "";
        String tipoUsuario = "";

        usuarios.add("rubi");
        usuarios.add("safira");
        usuarios.add("esmeralda");
        usuarios.add("patricia");

        tipoAcessos.add("user");
        tipoAcessos.add("user");
        tipoAcessos.add("user");
        tipoAcessos.add("admin");

        senhas.add("1234");
        senhas.add("1234");
        senhas.add("1234");
        senhas.add("admin");

        do {

            System.out.println("""
                +---------------------+
                |      MindBridge     |
                +---------------------+
                |    Pay Attention!   |
                +---------------------+
                |    1 - Login        |
                |    2 - Sair         |
                +---------------------+
                """);

            opcao = leitorMenu.nextInt();

            if (opcao == 1) {
                System.out.println("Insira seu username: ");
                username = leitorInput.nextLine();
                System.out.println("Insira sua senha: ");
                password = leitorInput.nextLine();

                for (int i = 0; i < usuarios.size(); i++) {

                    if (usuarios.get(i).equals(username) && senhas.get(i).equals(password) && tipoAcessos.get(i).equals("admin")) {
                        existeUsuario = true;
                        isAdmin = true;
                        break;
                    } else if (usuarios.get(i).equals(username) && senhas.get(i).equals(password) && tipoAcessos.get(i).equals("user")) {
                        existeUsuario = true;
                        isAdmin = false;
                        break;
                    } else {
                        existeUsuario = false;
                    }
                }

                if (existeUsuario && isAdmin) {
                    System.out.println("Login efetuado com sucesso!");

                    while (opcaoAdmin != 4) {
                        System.out.println("""
                            +------------------------------------------+
                                        INFORMAÇÕES DO USUÁRIO
                            +------------------------------------------+
                            Olá, %s!
                            +------------------------------------------+
                            1 - Exibir usuários
                            2 - Cadastrar usuários
                            3 - Remover usuário
                            4 - Logout
                            """.formatted(username));

                        opcaoAdmin = leitorSubmenu.nextInt();


                        if (opcaoAdmin == 1) {
                            System.out.println("Usuários cadastrados no sistema");
                            System.out.println("-------------------------------");
                            for (int i = 0; i < usuarios.size(); i++) {
                                System.out.println("""
                                        %s | Tipo: %s
                                        """.formatted(usuarios.get(i),tipoAcessos.get(i)));
                            }
                        } else if (opcaoAdmin == 2) {
                            System.out.println("Cadastre o nome de usuário:");
                            username = leitorInput.nextLine();

                            System.out.println("Cadastre uma senha:");
                            password = leitorInput.nextLine();

                            System.out.println("Tipo de usuário -> admin/user:");
                            tipoUsuario = leitorInput.nextLine();

                            usuarios.add(username);
                            senhas.add(password);
                            tipoAcessos.add(tipoUsuario);

                            System.out.println("Usuário cadastrado com sucesso!");
                        } else if (opcaoAdmin == 3) {

                            Boolean validarUsuario = false;

                            System.out.println("Digite o nome de um usuário para remover");
                            String pesquisa = leitorInput.nextLine();
                            for (int i = 0; i < usuarios.size(); i++) {

                                if (usuarios.get(i).equals(pesquisa)) {
                                    usuarios.remove(i);
                                    senhas.remove(i);
                                    tipoAcessos.remove(i);
                                    validarUsuario = true;
                                    break;
                                } else {
                                    validarUsuario = false;
                                }
                            }

                            if (validarUsuario) {
                                System.out.println("Usuário deletado com sucesso");
                            } else {
                                System.out.println("Usuário não encontrado");

                            }
                        } else if (opcaoAdmin == 4) {
                            System.out.println("Saindo...");
                        } else {
                            System.out.println("Opção inválida, tente novamente.");
                        }
                    }

                } else if (existeUsuario) {

                    while (opcaoUsuario != 1) {
                        System.out.println("""
                            +------------------------------------------+
                                        INFORMAÇÕES DO USUÁRIO
                            +------------------------------------------+
                            Olá, %s!
                            +------------------------------------------+
                            1 - Logout
                            """.formatted(username));

                        opcaoUsuario = leitorMenu.nextInt();
                    }

                } else {
                    System.out.println("Username ou senha incorreto");
                }
                
            } else if (opcao == 2) {
                System.out.println("Encerrando programa...");
            } else {
                System.out.println("Opção inválida, tente novamente");
            }
        } while (opcao != 2);
    }
}