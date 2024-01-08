/*

2018

Indovina il numero (2 giocatori)

*/
public class Gioco2 {

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

	public static void main(String[] args) {
		String[] Players = new String[2];

		System.out.println("Gioco fatto per 2 giocatori."); // Leggo i nomi dei 2 giocatori
		System.out.println("INIZIAMO");
		System.out.println();
		System.out.println("Inizia il GIOCATORE 1.");
		System.out.println("GIOCATORE 1: COME TI CHIAMI? ");
		Players[0] = SIn.readLine();
		System.out.println("BENE, " + Players[0] + ". ADESSO PASSA IL PC AL GIOCATORE 2.");
		System.out.println();
		System.out.println("GIOCATORE 2: COME TI CHIAMI? ");
		Players[1] = SIn.readLine();
		System.out.println("BENE, " + Players[1] + ".");

		Pausa();
		Clear();

		int Numero[] = new int[2];
		boolean controllo = false;
		System.out.println("TOCCA A TE, " + Players[0]);
		System.out.println();
		while (!(controllo)) { // Controlla se il numero è compreso tra 1 e 100
			System.out.println("Scegli il tuo numero (deve essere compreso tra 1 e 100): ");
			Numero[0] = SIn.readInt();
			if ((Numero[0] > 0) && (Numero[0] <= 100))
				controllo = true;
		}
		System.out.println("VA BENE, PASSA IL PC A " + Players[1]);
		Pausa();
		Clear();

		System.out.println("TOCCA A TE, " + Players[1]);
		System.out.println();
		while (controllo) {
			System.out.println("Scegli il tuo numero (deve essere compreso tra 1 e 100): ");
			Numero[1] = SIn.readInt();
			if ((Numero[1] > 0) && (Numero[1] <= 100))
				controllo = false;
		}

		Pausa();
		Clear();

		boolean vincita[] = new boolean[2];

		vincita[0] = false;
		vincita[1] = false;

		while ((vincita[0] != true) && (vincita[1] != true)) { // se nessuno ha vinto, eseguo le istruzioni
			System.out.println("TOCCA A TE, " + Players[0]);
			System.out.println("PROVA A INDOVINARE IL NUMERO DI " + Players[1]);
			System.out.println("SCRIVI IL NUMERO: ");
			int ProvaNumero1 = SIn.readInt();
			if (ProvaNumero1 > Numero[1])
				System.out.println("IL NUMERO CHE HAI SCRITTO E' TROPPO GRANDE!");
			if (ProvaNumero1 < Numero[1])
				System.out.println("IL NUMERO CHE HAI SCRITTO E' TROPPO PICCOLO!");
			if (ProvaNumero1 == Numero[1]) {
				System.out.println("HAI INDOVINATO. VEDIAMO SE IL TUO AVVERSARIO INDOVINA ANCHE!");
				vincita[0] = true;
			}
			System.out.println("VA BENE, PASSA IL PC A " + Players[1]);
			Pausa();
			Pausa();
			Clear();

			System.out.println("TOCCA A TE, " + Players[1]);
			System.out.println("PROVA A INDOVINARE IL NUMERO DI " + Players[0]);
			System.out.println("SCRIVI IL NUMERO: ");
			int ProvaNumero2 = SIn.readInt();
			if (ProvaNumero2 > Numero[0])
				System.out.println("IL NUMERO CHE HAI SCRITTO E' TROPPO GRANDE!");
			if (ProvaNumero2 < Numero[0])
				System.out.println("IL NUMERO CHE HAI SCRITTO E' TROPPO PICCOLO!");
			if (ProvaNumero2 == Numero[0]) {
				System.out.println("HAI INDOVINATO.");
				vincita[1] = true;
			} else {
				System.out.println("VA BENE, PASSA IL PC A " + Players[1]);
				Pausa();
				Pausa();
				Pausa();
				Clear();
			}
		}

		if ((vincita[0]) && (!vincita[1])) // Controllo chi va vinto
			System.out.println(Players[0] + "HA VINTO!");
		if ((!vincita[0]) && (vincita[1]))
			System.out.println(Players[1] + "HA VINTO!");
		if ((vincita[0]) && (vincita[1]))
			System.out.println("PARITA'");
	}
}
