/*
    2018
    Impiccato dove il PC indovina la parola
*/

import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ImpiccatoPC {

    // 60k.txt - italiano
    // 20k.txt - inglese
    private static String[] linguaTxt = { "", "eng.txt", "ita.txt" };
    private static String FILE_WORDS = "";

    private static void sceltaLingua() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose WORD's language:");
        System.out.println("1. English");
        System.out.println("2. Italiano");
        int nLingua = scan.nextInt();
        FILE_WORDS = linguaTxt[nLingua];
    }

    public static int lunParola() {
        System.out.println("Think of a word (between length 5-9) and write its length");
        Scanner scan = new Scanner(System.in);
        int lunghezza = scan.nextInt();
        if (lunghezza < 5 && lunghezza > 9) {
            System.out.println("Error, length not possible");
            System.out.println("Retry");
            lunParola();
        }
        System.out.println("Let's start!");
        System.out.println();
        return lunghezza;
    }

    public static void disegna(char[] lettere) {
        for (int i = 0; i < lettere.length; i++) {
            if (lettere[i] == ' ') {
                System.out.print("_ ");
            } else {
                System.out.print((char) (lettere[i] - ' ') + " ");
            }
        }
        System.out.println();
    }

    public static int chiediLettera(char lettera, char[] lettere) {
        int res = -1;
        System.out.println("Let me think...");
        System.out.println("I'll try with  '" + (char) (lettera - ' ') + "'");
        System.out.println("Is it in your word(y/n)");
        Scanner scan = new Scanner(System.in);
        char yesNo = scan.next().charAt(0);
        if (yesNo != 'n' && yesNo != 'N') {
            System.out.println();
            System.out.println();
            disegna(lettere);
            System.out.println("Awesome!");
            System.out.println("Choose the letter's position");
            System.out.println("If the letter is in the word more than once, don't worry. You'll be asked later");
            System.out.println("As for now, choose the letter's position where it isn't opened yet");
            res = scan.nextInt() - 1;
        }

        return res;
    }

    public static int chiediAncora(char lettera, char[] lettere) {
        System.out.println("Are there other positions for letter '" + (char) (lettera - ' ') + "' (y/n) ?");
        int res = -1;
        Scanner scan = new Scanner(System.in);
        char yesNo = scan.next().charAt(0);
        if (yesNo != 'n' && yesNo != 'N') {
            System.out.println();
            System.out.println();
            disegna(lettere);
            System.out.println("Awesome!");
            System.out.println("Choose the letter's position");
            System.out.println("If the letter is in the word more than once, don't worry. You'll be asked later");
            System.out.println("As for now, choose the letter's position where it isn't opened yet");
            res = scan.nextInt() - 1;
        }

        return res;
    }

    public static boolean controllaGuessed(String s, char[] l) {
        boolean isOk = true;
        for (int i = 0; i < l.length; i++) {
            if (l[i] != ' ') {
                if (l[i] != s.charAt(i))
                    isOk = false;
            }
        }
        return isOk;
    }

    public static char[] controllaParole(char[] lettere) throws Exception {
        char tutteLettere[] = new char[26];
        int contaLettere = 0;
        int conta = 0;
        int errori = 0;
        for (int i = 0; i < tutteLettere.length; i++) {
            tutteLettere[i] = ' ';
        }
        int indice = 0;
        boolean isEmpty = true;
        Scanner inGuesser = new Scanner(new FileReader(FILE_WORDS));
        boolean finito = true;
        while (inGuesser.hasNextLine() && finito) {
            String guessed = inGuesser.nextLine();
            if (guessed.length() == lettere.length) {

                boolean continuaParola = false;
                do {
                    if (!controllaGuessed(guessed, lettere))
                        break;
                    isEmpty = false;

                    do {
                        conta = 0;
                        isEmpty = false;
                        indice = (int) (lettere.length * Math.random());
                        for (int i = 0; i < lettere.length; i++) {
                            if (lettere[i] == guessed.charAt(indice) && !controllaFine(lettere))
                                isEmpty = true;
                        }
                        for (int i = 0; i < tutteLettere.length; i++) {
                            for (int j = 0; j < guessed.length(); j++) {
                                if (tutteLettere[i] == guessed.charAt(j)) {
                                    conta++;
                                    if (tutteLettere[i] == guessed.charAt(indice))
                                        isEmpty = true;
                                }
                            }
                        }
                        if (!isEmpty) {
                            tutteLettere[contaLettere] = guessed.charAt(indice);
                            contaLettere++;
                        }
                        if (conta == lettere.length)
                            isEmpty = false;

                    } while (isEmpty);

                    if (conta == guessed.length())
                        break;
                    disegna(lettere);
                    int isLetteraOk = chiediLettera(guessed.charAt(indice), lettere);
                    if (isLetteraOk >= 0)
                        continuaParola = true;
                    else {
                        errori++;
                        System.out.println("Damn. I've made " + errori + " mistake(s)   (max: 8)");
                        continuaParola = false;
                        if (errori >= 8)
                            finito = false;
                    }
                    while (isLetteraOk >= 0) {
                        lettere[isLetteraOk] = guessed.charAt(indice);
                        disegna(lettere);
                        isLetteraOk = chiediAncora(guessed.charAt(indice), lettere);
                    }
                } while (continuaParola);
            }
            if (controllaFine(lettere) && errori < 8) {
                System.out.println("I found your word!");
                System.out.println("In " + errori + " mistake(s).");
                finito = false;
            } else if (controllaFine(lettere) && errori >= 8) {
                System.out.println("You won!");
                System.out.println("I've made 8 mistakes :(");
                finito = false;
            }
        }

        if (finito) {
            System.out.println("Word not found! Are you sure you spelled it right?");
            System.out.println("Contact developer in case.");
        }
        return lettere;
    }

    public static boolean controllaFine(char[] lettere) {
        boolean finish = true;
        for (int i = 0; i < lettere.length; i++) {
            if (lettere[i] == ' ')
                finish = false;
        }
        return finish;
    }

    public static void main(String args[]) throws Exception {
        sceltaLingua();
        int len = lunParola();
        char lettere[] = new char[len];

        for (int i = 0; i < len; i++) {
            lettere[i] = ' ';
        }
        lettere = controllaParole(lettere);
    }
}