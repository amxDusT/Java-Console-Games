
/*
2018

Tic Tac Toe

*/
import java.util.Scanner;

/*
1 2 3   [0,0] [0,1] [0,2]
4 5 6   [1,0] [1,1] [1,2]
7 8 9   [2,0] [2,1] [2,2]

pos = i*3 + j;

*/

public class Tris {

    private static String g1 = "";
    private static String g2 = "";
    private static boolean whoPlays = true;

    public static void inizializza() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire il nome del Giocatore 1:");
        g1 = scan.nextLine();
        System.out.println("Va bene " + g1 + ", tu sei il simbolo 'X'");
        System.out.println("Inserire il nome del Giocatore 2:");
        g2 = scan.nextLine();
        System.out.println("Va bene " + g2 + ", tu sei il simbolo 'O'");

    }

    public static void disegna(char[] d) {
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
        for (int i = 0; i < d.length; i++) {
            System.out.print("  " + d[i] + "  ");
            if (i % 3 < 2)
                System.out.print("|");
            if (i % 3 == 2) {
                System.out.println();
                if (i < 8)
                    System.out.println("-----------------");
            }

        }
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    public static char[] scegliPosizione(char[] c, boolean g) {
        System.out.println("Tocca al giocatore " + (g ? g1 : g2));
        Scanner scan = new Scanner(System.in);
        boolean isContinue = true;
        int pos;
        do {
            System.out.println((g ? g1 : g2) + ", Scegli in che casella mettere il tuo simbolo (1-9)");
            pos = scan.nextInt();
            if (c[pos - 1] == ' ')
                isContinue = false;

            if (isContinue)
                System.out.println("Attentione, non puoi selezionare quella casella. Riprova");
        } while (isContinue);

        c[pos - 1] = g ? 'X' : 'O';

        return c;
    }

    public static boolean finito(char[] c) {
        for (int i = 0; i < 3; i++) {
            if (c[i] == c[i + 3] && c[i] == c[i + 6] && c[i] != ' ') {

                return true;
            }
        }
        for (int i = 0; i < 9; i = i + 2) {
            if (c[i] == c[i + 1] && c[i] == c[i + 2] && c[i] != ' ') {

                return true;
            }
            i++;
        }
        if (c[0] == c[4] && c[0] == c[8] && c[4] != ' ') {

            return true;
        }
        if (c[2] == c[4] && c[2] == c[6] && c[4] != ' ') {

            return true;
        }
        int conta = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] != ' ')
                conta++;
        }
        if (conta == 9)
            return true;

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Gioco del TRIS semplice.");
        System.out.println("Fatto per 2 giocatori");
        inizializza();
        char[] casella = new char[9];
        boolean isFinished = false;

        // Assegno un valore nullo a tutte le posizioni.
        for (int i = 0; i < casella.length; i++) {
            casella[i] = ' ';
        }
        disegna(casella);
        while (!isFinished) {

            casella = scegliPosizione(casella, whoPlays);
            whoPlays = !whoPlays;
            isFinished = finito(casella);
            disegna(casella);
        }

    }
}