/*
 2018-2019
 BlackJack albanese (arrivare fino a 31)
 */

import java.util.Random;

public class tre {

	public final static void Clear() { // Pulisce la schermata
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}

	public static void Pausa() { // Pausa di 1 secondo
		try {

			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void Disegna(int rCarte, int rSemi) {

		String[] Carte = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		String[] Semi = { "Fiori", "Cuori", "Quadri", "Picche" };
		System.out.println(" --------- ");
		if ((Semi[rSemi] == "Quadri") || (Semi[rSemi] == "Picche"))
			System.out.println("| " + Semi[rSemi] + "  |");
		else
			System.out.println("| " + Semi[rSemi] + "   |");
		System.out.println("|         |");
		System.out.println("|         |");
		if (Carte[rCarte] == "10")
			System.out.println("| " + Carte[rCarte] + "      |");
		else
			System.out.println("| " + Carte[rCarte] + "       |");
		System.out.println("|         |");
		System.out.println("|         |");
		System.out.println("|         |");
		System.out.println(" --------- ");
	}

	public static void main(String[] args) {
		int nGioc;
		String[] Carte = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		String[] Semi = { "Fiori", "Cuori", "Quadri", "Picche" };
		int[][] Uscita = new int[13][4];
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 13; i++)
				Uscita[i][j] = 0;
		}
		int[] Punteggio = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

		System.out.println("Questo e' un gioco di carte, lo scopo e' fare 31");
		System.out.println();
		System.out.println("Per leggere le istruzioni scrivere 'info'");
		System.out.println("Altrimenti scegli il numero di giocatori (max 4): ");
		String Giocatori = SIn.readLine();
		if (Giocatori.equals("info")) {
			System.out.println("Il gioco consiste nel avvicinarsi al 31 il piu' possibile (se non compiere 31).");
			System.out.println(
					"Si perde se si supera il 31 o se l'avversario ha un punteggio piu' vicino al 31 senza superarlo.");
			System.out.println();
			System.out.println("Spero tu abbia capito, perche' non potrai ritornare indietro.");
			System.out.println();
			System.out.println("Scegli il numero di giocatori: ");
			nGioc = SIn.readInt();
		} else {
			String Avanti = "N";
			nGioc = Integer.parseInt(Giocatori);
			while (Avanti.equals("N")) {
				System.out.println("Hai scelto d giocare con " + nGioc + " giocatori. Accettare? (Y/N) ");
				Avanti = SIn.readLine();
				if (Avanti.equals("N")) {
					System.out.println("Quanti giocatori? ");
					nGioc = SIn.readInt();
				}
			}
		}
		String[][] salvaCarte = new String[nGioc][100];
		// Definisco variabili che scelgono il random
		Random caso1 = new Random();
		Random caso2 = new Random();
		// Definisco nome Giocatori
		String[] nomeGiocatori = new String[nGioc];
		for (int i = 0; i < nGioc; i++) {
			System.out.println("Giocatore " + (i + 1) + " scrivi il tuo nome: ");
			nomeGiocatori[i] = SIn.readLine();

		}

		boolean Nuova = false;
		int rCarte = -1;
		int rSemi = -1;
		int[] Somma = new int[nGioc];
		int[] Somma2 = new int[nGioc];
		// int[] Contatore = new int[32];
		int[] contaCarte = new int[4];

		for (int i = 0; i < nGioc; i++) {
			Somma[i] = 0;

		}
		for (int i = 0; i < nGioc; i++) {
			contaCarte[i] = 0;
			System.out.println("Passate il computer a " + nomeGiocatori[i]);
			Pausa();
			System.out.println("Sei pronto " + nomeGiocatori[i] + "? (Y/N) ");
			String Pronto = SIn.readLine();
			Clear();

			Nuova = false;
			rCarte = -1;
			rSemi = -1;

			for (int j = 0; j < 2; j++) {
				while (!(Nuova)) {
					rCarte = caso1.nextInt(13);
					rSemi = caso2.nextInt(4);

					if (Uscita[rCarte][rSemi] == 0) {
						Nuova = true;
					}
				}

				salvaCarte[i][contaCarte[i]] = (Carte[rCarte] + " - " + Semi[rSemi]);
				contaCarte[i] += 1;
				Uscita[rCarte][rSemi] = 1;
				Nuova = false;
				Disegna(rCarte, rSemi);
				Somma[i] += Punteggio[rCarte];

			}
			System.out.println("Somma = " + Somma[i]);
			System.out.println("Hai letto " + nomeGiocatori[i] + "? ");
			Pronto = SIn.readLine();
			Clear();
		}
		System.out.println();

		for (int i = 0; i < nGioc; i++) {
			contaCarte[i] = 2;
			String Continua = "Y";

			while (Continua.equals("Y")) {
				System.out.println(nomeGiocatori[i] + ". Vuoi continuare? (Y/N)");
				Continua = SIn.readLine();
				if (Continua.equals("Y")) {
					while ((Uscita[rCarte][rSemi] == 1)) {
						rCarte = caso1.nextInt(13);
						rSemi = caso2.nextInt(4);
					}
					salvaCarte[i][contaCarte[i]] = (Carte[rCarte] + " - " + Semi[rSemi]);
					contaCarte[i] += 1;
					Uscita[rCarte][rSemi] = 1;
					Nuova = false;
					Disegna(rCarte, rSemi);
					Somma2[i] += Punteggio[rCarte];
					System.out.println("Somma (Senza considerare le prime carte) = " + Somma2[i]);
					if (Somma2[i] > 63) {

						Continua = "N";
						System.out.println("Sei andato oltre!");
					}
				} else
					break;
			}
		}
		int indiceMax = -1;
		int sommaMax = -1;
		for (int i = 0; i < nGioc; i++) {
			Somma[i] = Somma[i] + Somma2[i];
			System.out.println(nomeGiocatori[i]);
			for (int j = 0; j < contaCarte[i]; j++) {
				if (Somma[i] > 31) {
					if ((salvaCarte[i][j].equals("A - Fiori")) || (salvaCarte[i][j].equals("A - Cuori"))
							|| (salvaCarte[i][j].equals("A - Picche")) || (salvaCarte[i][j].equals("A - Quadri"))) {
						Somma[i] -= 10;
					}
				}
				System.out.print(salvaCarte[i][j] + "    ");
			}
			System.out.print(nomeGiocatori[i] + " ha totalizzato " + Somma[i]);
			if (Somma[i] > 31)
				System.out.println(" (Squalificato) ");
			else
				System.out.println();

			System.out.println();

			if ((Somma[i] > sommaMax) && (Somma[i] <= 31)) {
				sommaMax = Somma[i];
				indiceMax = i;

			}

		}
		System.out.println();
		if (sommaMax == -1)
			System.out.println("NESSUN VINCITORE");
		else
			System.out.println(nomeGiocatori[indiceMax] + " ha vinto con " + Somma[indiceMax] + " punti!");
	}
}