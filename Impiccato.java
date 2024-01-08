//GIOCO DELL'IMPICCATO
/*
			if(Indovinato[i]>0){
				System.out.println(" --- ");
				System.out.println("| " + Parola[i] + " |");
				System.out.println(" --- ");
			}
			else {
				System.out.println(" --- ");
				System.out.println("|   |");
				System.out.println(" --- ");
			}
			*/

public class Impiccato {
	
	
	public final static void Clear(){														//Pulisce la schermata
        for (int i = 0; i < 50; ++i) System.out.println();
    }
	
	
	
	public static void Pausa(){																//Pausa di 1 secondo
		try
		{
			
			Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}
	
	
	
	public static void Disegna(String[] Parola, int[] Indovinato, int Errori){            
		
		for(int i = 0; i < Parola.length; i++){
			System.out.print(" --- ");
		}
		System.out.println();
		for(int i = 0; i < Parola.length; i++){
			if(Indovinato[i]>0)
				System.out.print("| " + Parola[i] + " |");
			else
				System.out.print("|   |");
		}
		System.out.println();
		for(int i = 0; i < Parola.length; i++){
			System.out.print(" --- ");
		}
		System.out.println();
		System.out.println("Errori: " + Errori);
	}
	
	
	
	
	public static void main(String[] args){
		System.out.println("GIOCO DELL'IMPICCATO");
		System.out.println();
		System.out.println("Scrivi la parola che gli avversari devono indovinare: ");
		String temp = SIn.readLine();
		
		String[] Parola = temp.split("");
		int[] Indovinato = new int[Parola.length];
		
		for(int i = 0; i< Indovinato.length; i++){
			Indovinato[i] = 0;
		}
		
		Indovinato[0] = 1;
		Indovinato[(Indovinato.length - 1)] = 1;
		int Errori = 0;
		boolean Error = true;
		
		Clear();
		Disegna(Parola, Indovinato,Errori);
		int tuttoFatto = 2;
		String[] Parola2; //= new String[];
		
		while((tuttoFatto != Indovinato.length) && (Errori < 8)){
			System.out.println();
			System.out.println("Scrivi una lettera: ");
			temp = SIn.readLine();
			tuttoFatto = 0;
			Parola2 = temp.split("");
			if (Parola2.length==Parola.length){
				for(int i=0;i < Parola.length; i++){
					if(Parola[i].equals(Parola2[i])){
						tuttoFatto++;
					}
				}
			}
			if(tuttoFatto==Indovinato.length){
				for(int i = 0; i< Indovinato.length; i++){
					Indovinato[i] = 1;
					
				}	
				Disegna(Parola, Indovinato, Errori);
				break;
			}
			
			Error = true;
			for(int i = 1; i < (Indovinato.length-1); i++){
				if(temp.equals(Parola[i])){
					Indovinato[i]=1;
					Error = false;
				}
				
				if(Indovinato[i]==1)
					tuttoFatto++;
			}
			if(Error)
					Errori++;
			Disegna(Parola, Indovinato, Errori);
		}
		if(Errori<8){
			System.out.println();
			System.out.println("Hai Vinto!");
			System.out.println("Con " + Errori + " errori.");
		}
		else {
			System.out.println();
			System.out.println("Hai Perso!");
			System.out.println("Hai superato gli 8 errori.");
			//System.out.println("Con " + Errori + " errori.");
		}
	}
}