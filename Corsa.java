
/*
2018

Corsa con iniziali

*/
import java.util.Random;

public class Corsa {
	public final static void Clear() { // Pulisce la schermata
		for (int i = 0; i < 50; ++i)
			System.out.println();
	}

	public static void Pausa() {
		try {

			Thread.sleep(600);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void Disegna(int nGioc, String[] Nomi) {
		Random rnd = new Random();
		int[] Corsa1 = new int[nGioc];
		int[] CorsaMax = new int[nGioc];
		for (int i = 0; i < nGioc; i++)
			CorsaMax[i] = 0;
		boolean Finito = false;
		while (Finito == false) {
			System.out.println("_________________________________________________________________________________");
			for (int i = 0; i < nGioc; i++) {
				if (Nomi[i].equals(".K"))
					Corsa1[i] = rnd.nextInt(4) + 2;
				else
					Corsa1[i] = rnd.nextInt(5) + 1;
				CorsaMax[i] += Corsa1[i];
				for (int j = 0; j < CorsaMax[i]; j++)
					System.out.print(" ");
				System.out.println(Nomi[i]);
				if (CorsaMax[i] > 79)
					Finito = true;
				System.out.println("_________________________________________________________________________________");
			}

			Pausa();

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();

		}
		int maxi, salva;
		maxi = 0;
		salva = -1;
		for (int i = 0; i < (nGioc); i++) {
			if (maxi < CorsaMax[i]) {
				maxi = CorsaMax[i];
				salva = i;
			}
		}
		System.out.println("Il vincitore e' " + Nomi[salva]);

	}

	public static void main(String[] args) {

		System.out.println("    CORSA DI INIZIALI   ");
		System.out.println("Quanti giocatori? ");
		int nGioc = SIn.readInt();

		String[] Nomi = new String[nGioc];
		for (int i = 0; i < nGioc; i++) {
			System.out.println("Giocatore " + (i + 1) + ", scrivi la tua iniziale univoca:");
			Nomi[i] = SIn.readLine();
		}

		System.out.println("Questa e' una corsa. SIETE PRONTI?");
		SIn.readLine();

		Clear();

		Disegna(nGioc, Nomi);

	}

}
