/*

inizio : 06/11/2018
fine   : 07/11/2018

Campo Minato

*/

import java.util.Scanner;

public class CampoMinato{

    //costanti
    private static final int[] GRANDEZZA = {10 , 15 , 20} ;
    private static int numeroMine;

    //scelta della difficolta 
    public static int inizializza(){
        Scanner scan = new Scanner(System.in);
        boolean tuttoGiusto;
        int difficolta;
        do{
            String[] livello = {"Easy", "Medium", "Hard"};
            tuttoGiusto = true;
            System.out.println("Ciao! Giochiamo a Campo Minato.");
            System.out.println("Scegli la grandezza del campo:");
            System.out.println("1. Easy \t (" + GRANDEZZA[0] + " x " + GRANDEZZA[0] + ")");
            System.out.println("2. Medium \t (" + GRANDEZZA[1] + " x " + GRANDEZZA[1] + ")");
            System.out.println("3. Hard \t (" + GRANDEZZA[2] + " x " + GRANDEZZA[2] + ")");
            difficolta = scan.nextInt();
            if(difficolta < 0 && difficolta > 3){
                System.out.println("Immetti un valore valido.");
                tuttoGiusto = false;
            }
            else {
                System.out.println("Hai scelto la modalita' " + livello[difficolta - 1]);
                System.out.println("Sei sicuro? (y/n)");
                char yesNo = scan.next().charAt(0);
                if(yesNo == 'n' || yesNo == 'N')
                    tuttoGiusto = false;
                else 
                    System.out.println("Va bene. Andiamo avanti.");
            }
        }while(!tuttoGiusto);
        return difficolta;
    }

    //metti in posizioni random le mine
    public static int[][] inizializzaMine(int dim, int[][] punto){
        numeroMine = ((dim*dim)/10);
        int riga;
        int colonna;

        for(int i = 0; i < numeroMine; i++){
            do{
                riga = (int)(dim * Math.random());
                colonna = (int)(dim * Math.random());
            } while (punto[riga][colonna] == 9);
            punto[riga][colonna] = 9;
        }
        return punto;
    }
    //assegno tutti i numero all'array "punto" (che contiene i numeri delle bombe vicine per ogni casella)
    public static int[][] controllo(int[][] punto){
        int tempI, tempJ, scriviNumero = 0;
        int x=0, y=0;

        for(int i = 0; i<punto.length; i++){
			for(int j = 0; j<punto[i].length; j++){

				while(x<=2){
					while(y<=2){
						tempI = i - 1 + x ;
						tempJ = j - 1 + y ;
						if(tempI>=0 && tempI<punto.length && tempJ>=0 && tempJ<punto.length && (x!=1 || y!=1)){ // (y=1 || x=1) falso 
							if(punto[tempI][tempJ] == 9){
								scriviNumero++;
                            }
						}
						y++;
					} 
					x++;
					y = 0;
                }
                if(punto[i][j] != 9)
				    punto[i][j] = scriviNumero;
                scriviNumero = 0;
                x = 0;
				}
        }
        return punto;
        
    }

    //Disegna la tabella con le X,F o il numero.
    public static void Disegna(int dim, int[][] aperto, int[][] punto){        
        System.out.print("    ");
        for(int i=0; i<dim;i++)
        if((i+1)>=10)
            System.out.print((i+1) + "  ");
        else
            System.out.print((i+1) + "   ");
        
        System.out.println();
        

        for(int i = 0; i < dim; i++){
            if((i+1)>=10)
                System.out.print((i+1) + "  ");
            else
            System.out.print((i+1) + "   ");
            for(int j = 0; j < dim; j++){
                if(aperto[i][j]==0){
                    System.out.print("X   ");
					//System.out.print(punto[i][j] + "   ");
                }
                else if(aperto[i][j] == 1){
                    // da aggiustare;
                    //int scriviNumero = controllo(punto,mine);
                    System.out.print(punto[i][j] + "   ");
                }
                else if(aperto[i][j] == 2){
                    System.out.print("F   ");
                }

            }
            System.out.println();
            System.out.println();
        }
    }
    
    //se la casella aperta = 0, allora apre le caselle vicine che sono 0.
    public static int[][] controllaCaselle(int i, int j,int[][] aperto, int [][] punto){
        aperto[i][j] = 1;
        int tempI, tempJ;
        int x = 0, y = 0;
        if(punto[i][j] == 0){
            while(x <= 2){
                while(y <= 2){
                    tempI = i - 1 + x ;
                    tempJ = j - 1 + y ;
                    if(tempI>=0 && tempI<punto.length && tempJ>=0 && tempJ<punto.length && (x!=1 || y!=1)){ // (y=1 || x=1) falso 
                        if(punto[tempI][tempJ] == 0 && aperto[tempI][tempJ] == 0){
                            controllaCaselle(tempI,tempJ,aperto,punto);
                        }
                        else if(punto[tempI][tempJ] != 0 && aperto[tempI][tempJ] ==0)
                            aperto[tempI][tempJ] = 1;
                    }
                    y++;
                } 
                x++;
                y = 0;
            }
        }
        return aperto;
    }

    //scegli che casella aprire o mettere la bandiera.
    public static int[][] selezionaCasella(int[][] aperto, int[][] punto){
        Scanner scan = new Scanner(System.in);
        boolean giaAperta;
        int riga, colonna;
        char removeFlag;
        char yesNo = ' ';
        do{
            giaAperta = false;
            System.out.println("Scrivi la colonna della casella da aprire");
            colonna = scan.nextInt();
            System.out.println("Scrivi la riga della casella da aprire");
            riga = scan.nextInt();
            if(aperto[riga - 1][colonna - 1] == 1){
                giaAperta = true; 
                System.out.println("Casella gia aperta!");
            }
            else if(aperto[riga - 1][colonna - 1] == 2)
            {
                giaAperta = true;
                System.out.println("La casella selezionata e' contrassegnata.");
                System.out.println("vuoi rimuovere il contrassegno? (y/n)");
                removeFlag = scan.next().charAt(0);
                if(removeFlag == 'y' || removeFlag == 'Y') 
                    aperto[riga - 1][colonna - 1] = 0;
            }
            else{
            System.out.println("Hai selezionato la casella in posizione [" + colonna + "," + riga + "]. Accettare? (y/n)");
            System.out.println("Scrivi 'f'/'F' per mettere contrassegnare la casella come bomba");
            yesNo = scan.next().charAt(0);
                if(yesNo == 'n' || yesNo == 'N')
                    giaAperta = true;    
            }
        } while(giaAperta);
        if(yesNo == 'f' || yesNo == 'F')
            aperto[riga - 1][colonna - 1] = 2;
        else{
            aperto = controllaCaselle((riga-1), (colonna-1),aperto, punto);
        }
        return aperto;

    }   
    //controlla se la partita è terminata
    public static boolean finito(int[][] aperto, int[][] punto){
        boolean isFinished = false;
        int contaMine = 0;
        for(int i = 0; i<punto.length;i++){
            for(int j = 0; j < punto[i].length;j++){
                if((aperto[i][j] == 1 && punto[i][j] == 9)){
                    isFinished = true;
                    System.out.println("Hai perso!");
                }
                else if((aperto[i][j] == 0 && punto[i][j] == 9)){
                    contaMine++;
                }
            }
        }
        if(contaMine == numeroMine){
            isFinished = true;
            System.out.println("Hai vinto!");
        }
        contaMine = 0;
        return isFinished;
    }

    public static void main(String[] args){
        int level = inizializza();
        int dim = GRANDEZZA[level-1];
        // aperto mi dice quali caselle ho aperto.
        int[][] aperto = new int[dim][dim];
        // punto mi da il numero di tutte le mine vicine per ogni casella 
        int[][] punto = new int[dim][dim];

        

        for(int i = 0; i < dim;i++){
            for(int j = 0; j < dim; j++){
                aperto[i][j] = 0;
            }
        }
        punto = inizializzaMine(dim, punto);

        punto = controllo(punto);

        Disegna(dim, aperto, punto);
        boolean isFinished = false;
        do{
            aperto = selezionaCasella(aperto, punto);
            Disegna(dim, aperto, punto);
            isFinished = finito(aperto, punto);
        }while(!isFinished);
    }
}
