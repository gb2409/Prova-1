package aplicativos;

import java.io.IOException;
import java.util.Scanner;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];     
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair" );
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                    System.out.println("Nome: ");
                    String nome = in.nextLine();
                    System.out.println("CPF: ");
                    String cpf = in.nextLine();
                    
                    Pessoa piloto = new Pessoa(nome, cpf);
                    pilotos[qtdCadastrados] = piloto;
                    qtdCadastrados++;
                    voltarMenu(in);

            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                    for (int i = 0; i < qtdCadastrados; i++) {
                    //informo apenas o objeto pessoa porque estou utilizando toString()
                    System.out.println(pilotos[i]);
                }

                voltarMenu(in);
            } else if (opcao == 3) {

                System.out.println("Digite o cpf do piloto que você esta buscando:");
                    String cpf2 = in.nextLine();

                    for (int i = 0; i < qtdCadastrados; i++) {

                        if (pilotos[i].getCpf().equals(cpf2)) {
                            System.out.println("O piloto que você procura é: ");
                            System.out.println(pilotos[i]);
                        }
                        if (!pilotos[i].getCpf().equals(cpf2) && i == qtdCadastrados -1) {
                            System.out.println("Piloto não encontrado na base de dados"); 
                        }
                    }

                
            } else if (opcao == 4) {
                System.out.println("Selecione um novo tamanho:");
              int novoLimite = in.nextInt();
              in.nextLine();
                

              if ( novoLimite <= MAX_ELEMENTOS){
                    System.out.println("O novo tamanho tem que ser maior que o tamanho atual!");
                
                }
                    Pessoa[] vetorNovo = new Pessoa [novoLimite];
                    for (int i = 0; i < qtdCadastrados; i++) {
                        vetorNovo[i] = pilotos[i];
                    }

                    pilotos = vetorNovo;
                    MAX_ELEMENTOS = novoLimite;

                    System.out.println("ARMAZENAMENTO ATUALIZADO!!!!!");
            } 

            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close(); 

}

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}