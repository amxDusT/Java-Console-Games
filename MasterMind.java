import java.util.Scanner;

public class MasterMind{
    private static String seq = "";

    public static void randomizza(){
        seq = "";

        for(int i=0;i<4;i++){
            seq += String.valueOf((int)(10*Math.random()));
        }
    }


    public static int gioco(){
        Scanner scan = new Scanner(System.in);
        String[][] record = new String[9][3];
        int errori = 0;
        int x=0;
        while(true){
            
            System.out.println("(Previous Combinations: )");
            for(int i = 0;i<x;i++){
                for(int j = 0;j<record[i].length;j++){
                    System.out.print(record[i][j] + ((j==0)? "\t":" "));
                }
                System.out.println();
            }
            if(x==0) System.out.println("NONE.");
            String prova;
            do{
                System.out.println("Write a sequence of 4 digits(0-9):");
                prova = scan.nextLine();
                if(prova.equals("f12"))
                System.out.println(seq);
            }while(prova.length() != 4);
            int num1=0,num2=0;
            boolean[] f = {true,true,true,true};
            for(int i=0;i<4;i++){
                if(seq.charAt(i) == prova.charAt(i))
                    num2++;
                for(int j=0;j<4;j++){
                    if(seq.charAt(i) == prova.charAt(j) && f[j]){
                        num1++;
                        f[j] = false;
                    }
                }
                
            }
            record[x][0] = prova;
            record[x][1] = String.valueOf(num1);
            record[x][2] = String.valueOf(num2);
            x++;

            if(num1==4)
                return errori;
            else{
                errori++;
                if(errori>=9)
                    return errori;
                
                System.out.println();
                System.out.println();
                System.out.println("Errors: " + errori);
            }

        }
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        randomizza();
        //System.out.println(seq);
        System.out.println(" This is MASTERMIND");
        System.out.println(" If you want to know the rules, write 'info', Otherwise press anything to start");
        String info = scan.nextLine();
        if(info.equals("info")){
            System.out.println();
            System.out.println("The game consists of finding the right combination of numbers");
            System.out.println("This combination is chosen by the computer randomly");
            System.out.println("You can choose any number between 0 and 9 (they can be repeated)");
            System.out.println("For every combination you'll be given 2 numbers:");
            System.out.println("  1. The first number indicates how many digits are effectively in the combination");
            System.out.println("  2. The second one indicates how many digits are in the right place");
            System.out.println();
            System.out.println("You have 9 tries.");
            System.out.println();
            System.out.println("These were the rules. Press anything to start the game.");
            info = scan.nextLine();
        }
        System.out.println();
        System.out.println("Sequence acquired.");
        int errori = gioco();
        if(errori>=9){
            System.out.println("You lost.");
        }
        else{
            System.out.println("You won.");
            System.err.println("You made " + errori + " errors.");
        }

        
    }
}