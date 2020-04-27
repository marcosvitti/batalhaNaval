package batalhanaval;

import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {

    public static void main(String[] args) {

        Random inicio = new Random(2);
        int[][] tabuleiroPlayer = new int[5][5];
        int[][] tabuleiroComp = new int[5][5];

        int[][] navios = new int[3][2];
        int[][] navios1 = new int[3][2];
        int[][] navios2 = new int[2][4];
        int[] tiro = new int[2];
        int tentativas = 0, acertos1 = 0, acertos2 = 0;
        int l = 0, c = 0;

        iniciaJogo();
        iniciaEmbarcacao(navios1, 3, 2);
        iniciaEmbarcacao(navios2, 2, 4);
        inicializaTabuleiro(tabuleiroPlayer);
        inicializaTabuleiro(tabuleiroComp);
        mostraTabuleiro(tabuleiroComp, tabuleiroPlayer);

        System.out.println();

        posicionaBarcoUser(navios1, navios2);
        iniciaNaviosMaq(navios);

        System.out.println();

        /*do {
            mostraTabuleiro(tabuleiroPlayer, tabuleiroComp);

            darTiro(tiro, 0, 0);
            tentativas++;

            if (acertou(tiro, navios)) {
                dica(tiro, navios, tentativas);
                acertos1++;
            } else {
                dica(tiro, navios, tentativas);
            }

            alteraTabuleiro(tiro, navios, tabuleiroPlayer);

        } while (acertos2 != 2 && acertos1 != 3);

        System.out.println("\n\n\nJogo terminado. Você acertou os 3 navios em " + tentativas + " tentativas");
        mostraTabuleiro(tabuleiroComp, tabuleiroPlayer);*/
    }

    public static void inicializaTabuleiro(int[][] tabuleiro) {
        for (int linha = 0; linha < 5; linha++) {
            for (int coluna = 0; coluna < 5; coluna++) {
                tabuleiro[linha][coluna] = -1;
            }
        }
    }

    public static void mostraTabuleiro(int[][] tabuleiroComp, int[][] tabuleiroPlayer) {
        int tabuleiro[][] = new int[5][10];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiro[i][j] = tabuleiroComp[i][j];
            }
        }
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                tabuleiro[x][y + 5] = tabuleiroPlayer[x][y];
            }
        }
        System.out.println("\t\tCOMPUTER \t \t\t\t\tPLAYER ");
        System.out.println("\t1 \t2 \t3 \t4 \t5\t | \t1 \t2 \t3 \t4 \t5");
        System.out.println();

        for (int linha = 0; linha < 5; linha++) {
            System.out.print((linha + 1) + "");
            for (int coluna = 0; coluna < 10; coluna++) {
                if (coluna == 5) {
                    System.out.print("\t | ");
                }
                if (tabuleiro[linha][coluna] == -1) {
                    System.out.print("\t" + "[A]");
                } else if (tabuleiro[linha][coluna] == 0) {
                    System.out.print("\t" + "[E]");
                } else if (tabuleiro[linha][coluna] == 1) {
                    System.out.print("\t" + "[N]");
                }
            }
            System.out.print("\t" + (linha + 1) + "\n");

        }
    }

    public static void posicionaBarcoUser(int[][] navios1, int[][] navios2) {

        Scanner entrada = new Scanner(System.in);

        int n1 = 3; // Embarcação com 1 posição
        int n2 = 2; // Embarcação com 2 posição
        int cn1 = 0;
        int cn2 = 0;
        int nt = n1 + n2;
        int op;

        for (int navio = 0; navio < nt; navio++) {

            System.out.println("Você possui " + (n1 + n2) + " embarcações, escolha qual posicionar: ");
            System.out.println("1 - Submarino Faltam " + n1 + " serem posicionadas \t [INFO: Embarcação ocupa 1 posição do tabuleiro]");
            System.out.println("2 - Cruzador Faltam " + n2 + " serem posicionadas \t [INFO: Embarcação ocupa  2 posição do tabuleiro]");

            op = entrada.nextInt();

            switch (op) {

                case 1:
                    if (cn1 < 3) {

                        System.out.println("Vamos posicionar o Submarino " + (navio + 1) + "º : ");

                        boolean erro = true;

                        while(erro) {

                            System.out.println("Digite a linha: ");
                            int x  = entrada.nextInt();

                            System.out.println("Digite a coluna: ");
                            int y = entrada.nextInt();

                            if ( (x >= 1 && x <= 5) && (y >= 1 && y <= 5) ) {

                                if (verificaPosicaoEmbarcacao(navios1, cn1, (x-1), (y-1), 3)) {

                                    navios1[cn1][0] = (x-1);
                                    navios1[cn1][1] = (y-1);

                                    n1--;
                                    cn1++;

                                    erro = false;

                                } else {

                                    System.out.println("Já existe uma embarcação na posição digitada, tente outra posição\n");

                                }

                            } else {

                                System.out.println("Foram encontados erros na posição digitada, tente denovo\n");

                            }

                        }

                        System.out.println("Submarino n°" + (navio + 1) + " posicionado em linha [" + navios1[navio][0] + "] coluna [" + navios1[navio][1] + "]\n");

                    } else {

                        System.out.println("Não há mais barcos deste para posicionar\n");

                    }
                break;

                case 2:
                    if (cn2 < 2) {

                        boolean erro = true;

                        while(erro) {

                                System.out.println("Vamos posicionar o Cruzador " + (navio + 1) + "º :");

                                System.out.println("Digite a linha para a 1° parte do Cruzador: ");
                                int x = entrada.nextInt();

                                System.out.println("Digite a coluna para a 1° parte do Cruzador: ");
                                int y = entrada.nextInt();

                                System.out.println("Digite a linha para a parte 2° do Cruzador: ");
                                int x2 = entrada.nextInt();

                                System.out.println("Digite a coluna para a parte 2° do Cruzador: ");
                                int y2 = entrada.nextInt();

                                if ( (x >= 1 && x <= 5) && (y >= 1 && y <= 5) && (x2 >= 1 && x2 <= 5) && (y2 >= 1 && y2 <= 5) ) {

                                    if (verificaPosicaoEmbarcacao2(navios2, cn2, (x-1), (y-1), (x2-1), (y2-1), 2)) {

                                            navios2[cn2][0] = (x-1);
                                            navios2[cn2][1] = (y-1);
                                            navios2[cn2][2] = (x2-1);
                                            navios2[cn2][3] = (y2-1);

                                            n2--;
                                            cn2++;

                                            erro = false;

                                    } else {

                                        System.out.println("Já existe uma embarcação na posição digitada ou foram encontados erros na posição digitada, tente outra posição\n");

                                    }

                                } else {

                                    System.out.println("Foram encontados erros na posição digitada, tente denovo\n");

                                }

                        }

                    } else {

                        System.out.println("Não há mais barcos deste para posicionar");

                    }
                     /*for(int i = 0 ; i < 2; i++){
                        for (int j = 0; j < 4; j++){
                            System.out.print(navios2[i][j] + " | ");
                        }
                         System.out.println("");
                    }*/
                break;
            }
        }
    }

    public static void iniciaNaviosMaq(int[][] navios) {
        Random sorteio = new Random();

        for (int navio = 0; navio < 3; navio++) {
            navios[navio][0] = sorteio.nextInt(5);
            navios[navio][1] = sorteio.nextInt(5);

            //agora vamos checar se esse par não foi sorteado
            //se foi, so sai do do...while enquanto sortear um diferente
            for (int anterior = 0; anterior < navio; anterior++) {
                if ((navios[navio][0] == navios[anterior][0]) && (navios[navio][1] == navios[anterior][1])) {
                    do {
                        navios[navio][0] = sorteio.nextInt(5);
                        navios[navio][1] = sorteio.nextInt(5);
                    } while ((navios[navio][0] == navios[anterior][0]) && (navios[navio][1] == navios[anterior][1]));
                }
            }

        }
    }

    public static void darTiro(int[] tiro, int l, int c) {
        Scanner entrada = new Scanner(System.in);
        if (l == 0 && c == 0) {
            System.out.print("Linha: ");
            tiro[0] = entrada.nextInt();
            tiro[0]--;

            System.out.print("Coluna: ");
            tiro[1] = entrada.nextInt();
            tiro[1]--;
        } else {
            tiro[0] = l;
            tiro[1] = c;
        }

    }

    public static boolean acertou(int[] tiro, int[][] navios) {

        for (int navio = 0; navio < navios.length; navio++) {
            if (tiro[0] == navios[navio][0] && tiro[1] == navios[navio][1]) {
                System.out.printf("Você acertou o tiro (%d,%d)\n", tiro[0] + 1, tiro[1] + 1);
                return true;
            }
        }
        return false;
    }

    public static void dica(int[] tiro, int[][] navios, int tentativa) {
        int linha = 0,
                coluna = 0;

        for (int fila = 0; fila < navios.length; fila++) {
            if (navios[fila][0] == tiro[0]) {
                linha++;
            }
            if (navios[fila][1] == tiro[1]) {
                coluna++;
            }
        }

        System.out.printf("\nDica %d: \nlinha %d -> %d navios\n"
                + "coluna %d -> %d navios\n", tentativa, tiro[0] + 1, linha, tiro[1] + 1, coluna);
    }

    public static void alteraTabuleiro(int[] tiro, int[][] navios, int[][] tabuleiro) {
        if (acertou(tiro, navios)) {
            tabuleiro[tiro[0]][tiro[1]] = 1;
        } else {
            tabuleiro[tiro[0]][tiro[1]] = 0;
        }
    }

    public static void inteligenciaFDP(int[][] tabuleiro) {

        int[] tiro = new int[2];
        int a;
        int f = 0;
        int l;
        int c;
        for (int linha = 0; linha < 5; linha++) {

            for (int coluna = 0; coluna < 5; coluna++) {
                a = tabuleiro[linha][coluna];
                if (a == 1) {
                    l = linha;
                    c = coluna;
                    f++;
                    if (tabuleiro[linha][coluna - 1] != 1 && coluna - 1 > 0 && coluna - 1 < 5) {
                        darTiro(tiro, linha, coluna + 1);
                    } else if (tabuleiro[linha][coluna + 1] != 1 && coluna + 1 > 0 && coluna + 1 < 5) {
                        darTiro(tiro, linha, coluna - 1);
                    }
                }
            }
        }
        if (f == 0)//dar tiro random
        {
            darTiro(tiro, 0, 0);

        }
    }

    private static void iniciaJogo() {
        System.out.println(
        "\t\t    ____        __        ____             _   __                  __\n" +
        "\t\t   / __ )____ _/ /_____ _/ / /_  ____ _   / | / /___ __   ______ _/ /\n" +
        "\t\t  / __  / __ `/ __/ __ `/ / __ \\/ __ `/  /  |/ / __ `/ | / / __ `/ / \n" +
        "\t\t / /_/ / /_/ / /_/ /_/ / / / / / /_/ /  / /|  / /_/ /| |/ / /_/ / /  \n" +
        "\t\t/_____/\\__,_/\\__/\\__,_/_/_/ /_/\\__,_/  /_/ |_/\\__,_/ |___/\\__,_/_/   \n\n");
    }

    private static boolean verificaPosicaoEmbarcacao(int[][] navios, int cn1, int x, int y, int k) {

        for (int i = 0; i < k; i++) {
            if (navios[cn1][0] == x && navios[cn1][1] == y) {
                return false;
            }
        }
        return true;
    }

    private static boolean verificaPosicaoEmbarcacao2(int[][] navios, int cn2, int x, int y, int x2, int y2, int k) {

        for (int i = 0; i < k; i++) {
            if (navios[cn2][0] == x && navios[cn2][1] == y && navios[cn2][2] == x2 && navios[cn2][3] == y2) {
                return false;
            }
        }

        System.out.println( (x + 1) + " == " + x2 + " \t " + (x - 1) + " == " + x2 + " \t " + (y + 1) + " == " + y2 + " \t " + (y - 1) + " == " + y2);
        return !((x + 1) == x2 || (x - 1) == x2) && ((y + 1) == y2 || (y - 1) == y2);
    }

    public static void iniciaEmbarcacao(int navios[][], int k, int l){
        
        for(int i = 0 ; i < k; i++){
            for (int j = 0; j < l; j++){
               navios[i][j]=-1;             
            }

        }
   }
}
