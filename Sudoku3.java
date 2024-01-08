/**
 * FUNZIONAMENTO: 
 * 1)Genera il sudoku dalla posizione [0][0] in una casella[9][9] fino alla posizione [8][8] 
 *   prendendo un valore random di quelli possibili per quella posizione. (Puo capitare che escano degli zeri 
 *   perche possono non esserci valori possibili in una posizione, allora il programma fa un ciclo che controlla 
 *   non ci siano zeri). 
 * 2)Prende un valore a caso dal sudoku completo e lo mette = 0. poi controlla se senza quel valore il sudoku ha 
 *   soluzione. se lo ha, prende un altro valore (fino a un certo numero), altrimenti rimette il numero e prende
 *   un altro valore. 
 *   
 *   Il controllo dell'unicita è fatto per "backtracking": controllo ricorsivamente per ogni posizione una cifra 
 *   e se va bene, metto una cifra nelle altre vuote. se non è possibile completarlo cosi, allora cambio l'ultima
 *   cifra possibile e cosi via. vedo per quante volte tutto questo viene vero. se succede piu di 1 volta, allora
 *   non è univoco. 
 *   Allo stesso modo ho la soluzione del puzzle nel caso fosse univoco (se non lo fosse, avrei una delle soluzioni).
 *   Nel caso non ci fossero soluzioni, si riceve un messaggio.  
 *   
 */

import java.util.Scanner;
public class Sudoku3{
    public static int[][] casella = new int[9][9]; // casella 9x9 del sudoku
    public static boolean[] val = new boolean[10]; // possibili valori per ogni casella: parte da 1 a 9 (lo 0 non lo conto).
    public static int[][] casella2 = new int[9][9]; // casella di supporto.
    public static int[][] casellaX = new int[9][9]; // caselle con solo i numeri visibili del puzzle 
    public static int soluzioni = 0;
    public static final int numcaso = 60;//81 - (int)(Math.random()*8 + 23);

    // Disegno del sudoku per entrambe le caselle.
    public static void disegna(boolean which){
        System.out.println("-------------------------------");
        for(int i=0;i<casella.length;i++){
            for(int j=0;j<casella[i].length;j++){
                System.out.print(((j)%3==0? "| ":" ") + ((which)?casella[i][j]:((casella2[i][j]==0)? " ":casella2[i][j])) + " ");
                if(j==8) System.out.print("|");
            }
            if((i+1)%3==0){
                System.out.println();
                System.out.print("-------------------------------");
            } 
            System.out.println();
        }
    }

    //controlla i valori possibili e assegna un valore random di quelli possibili
    public static void assegnaValore(){
        for(int i=0;i<casella.length;i++){
            for(int j=0;j<casella[i].length;j++){
                resettaVal();
                controllaCRQ(i,j,true);
                scegliNumero(i,j);
            }
        }
    }

    //aggiusta il controllo per l'unicita della soluzione 
    public static void fixC(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                casella2[i][j] = casellaX[i][j];
            }
        }
    }
   
    //assegna i valori a casellaX
    public static void assegna2(){
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                casellaX[i][j] = casella[i][j];

        int i,j,p=0,k=numcaso;
        while(k>0 && p<70){
            do{
                i=(int)(Math.random()*9);
                j=(int)(Math.random()*9);
            }while(casellaX[i][j]==0);
            casellaX[i][j]=0;

            if(secTot(true)){
                k--;
            }
            else{
                casellaX[i][j]=casella[i][j];
                p++; 
                //System.out.println(p);
            }
            if(p>=70) assegna2();
            
        }
    }

    //controlla colonna,riga e ogni quadro piccolo 3x3.
    private static void controllaCRQ(int riga,int col, boolean which){
        int s;
        for(int i=casella.length-1;i>=0;i--){
            s = ((which)?casella[riga][i]:casella2[riga][i]);
            val[s] = true;
        }
        for(int j=casella.length-1;j>=0;j--){
            s = ((which)?casella[j][col]:casella2[j][col]);
            val[s] = true;
        }
        int x = ((col+1)%3==0)? col:((col+2)%3==0)? (col+1):(col+2);
        int y = ((riga+1)%3==0)? riga:((riga+2)%3==0)? (riga+1):(riga+2);
        for(int i=y;i>=y-2 ;i--){
            for(int j=x;j>=x-2;j--){
                s = ((which)?casella[i][j]:casella2[i][j]);
                val[s] = true;
            }
        }

    }
    
    //resetta i numeri possibili per ogni casella
    private static void resettaVal(){
        for(int i=0;i<val.length;i++){
            val[i] = false;
        }
    }

    //scelta random dei numeri possibili per casella
    private static void scegliNumero(int riga, int col){
        int x, k=0;
        for(int i=1;i<val.length;i++){
            if(!val[i]) k++;
        }
        x = (int)(Math.random() * k + 1);
        
        k=0;
        for(int i=1;i<val.length;i++){
            if(!val[i]) k++;
            if(k==x){ casella[riga][col] = i;
            break;
            }
        }
    }

    //Ritorna true se i valori della casella vanno TUTTI bene
    public static boolean controllaSudoku(boolean vaBene){
        for(int i = 0;i<casella.length;i++){
            for(int j=0;j<casella[i].length;j++){
                if(casella[i][j]==0){
                    vaBene=false;
                    break;
                }
            }
        }
        return vaBene;
    }

    //Controllo unicita soluzione (e anche esistenza) del sudoku!
    public static boolean controlloTotale(boolean which){
        int riga = -1;
        int col = -1;
        boolean testoPD = false;
        boolean isEmpty = true;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(casella2[i][j]==0){
                    riga = i;
                    col = j;
                    isEmpty = false; break;
                }
            }
            if(!isEmpty) break;
        }
        if(isEmpty){
            if(which)
            soluzioni++;
            return true;
        } 
        else{
            for(int i=1;i<val.length;i++){
                if(isItOK(riga,col,i)){
                    casella2[riga][col]=i;
                    if(which){
                    if(soluzioni>1) return false;
                    if(controlloTotale(which)) testoPD=true;
                    }
                    else
                    {
                        if(controlloTotale(which))
                        return true;
                    }
                    casella2[riga][col]=0;
                }
            }
        }
        return ((which)?testoPD:false);
    }

    //controlla se il valore puo essere messo
    public static boolean isItOK(int riga, int col, int num){
        resettaVal();
        controllaCRQ(riga,col,false);
        return !val[num];
    }
    //fix per il controllo.
    public static boolean secTot(boolean which){
        fixC();
        soluzioni=0;
        return (ControlloTotale2(which));

    }
    public static boolean ControlloTotale2(boolean which){
        int[][] k = new int[9][9];
        boolean[][] isEdited = new boolean[9][9];
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                k[i][j]=1;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(casellaX[i][j]==0 || isEdited[i][j] ){
                    boolean isOk = false;
                    isEdited[i][j]=true;
                    do{
                        if(isItOK(i,j,k[i][j])){
                            casellaX[i][j]=k[i][j];
                            isOk=true;
                        }
                        else{
                            k[i][j]++;
                            if(k[i][j]>9){
                                isOk=true;
                                k[i][j]=1;
                                if(i>0 && j>0)
                                k[i-1][j-1]++;
                                if(j>1 && i>1){
                                    j-=2;
                                    i-=2;
                                }
                                else{return false;}
                            }
                            else
                                isOk=false;
                        }
                    }while(!isOk);
                }
            }
        }
        fixC();
        soluzioni=0;
        System.out.println(controlloTotale(false));
        return true;
    }

    
    public static void scelta(int num){
        Scanner scan = new Scanner(System.in);
        if(num==3){
            System.out.println("For every row, write its numbers separated by a space. if there are no numbers, put 0");
            System.out.println("Press enter after every row and write next one.");
            for(int i=0;i<9;i++){
                System.out.println("row:" +(i+1));
                for(int j=0;j<9;j++){

                    casellaX[i][j] = scan.nextInt();
                }
            }
            if(secTot(true)){
            System.out.println("Puzzle has only one solution."); 
            secTot(false);
            disegna(false);
            }
            else{
                System.out.println("Puzzle contains more than one solution or can't be solved.");
            }
        }
        else if(num<=2){
            boolean check = true;
            do{
                for(int i=0;i<casella.length;i++)
                for(int j=0;j<casella[i].length;j++)
                    casella[i][j]=0;
                assegnaValore();
            }while(!controllaSudoku(check));
            System.out.println("Generating a " + (81-numcaso) + " Sudoku...");
            System.out.println();
            assegna2();
            System.out.println("PUZZLE:");
            fixC();
            disegna(false);
            if(num==1){
                System.out.println("SOLUTION:");
                disegna(true);
                System.out.println();
            }
            System.out.println();
            System.out.println("The solution to this sudoku has been proved to be unique!");
            if(num==2){
                System.out.println("Press anything to show solution.");
                scan.nextLine();
                System.out.println("SOLUTION:");
                disegna(true);
                System.out.println();
            }
        }
        
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("SUDOKU PORCO DIO");
        System.out.println("Choose what to do.");
        int sceltax;
        do{
        System.out.println("Menu:");
        System.out.println("1. Generate random(23 to 32 numbers) puzzle sudoku with its solution.");
        System.out.println("2. Generate random(23 to 32 numbers) puzzle sudoku without solution.");
        System.out.println("3. Resolve a given sudoku.");
        System.out.println();
        sceltax = scan.nextInt();
        }while(sceltax<1 || sceltax>3);
        scelta(sceltax);
    }
}