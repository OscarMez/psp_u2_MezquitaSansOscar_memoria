package carreracaballos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CarreraCaballos {
      public int demanarint() {
        int num;
        boolean numtf = false;
        do {
            try {
                System.out.print("Tria una opcio: ");
                Scanner sc = new Scanner(System.in);
                num = sc.nextInt();
                sc.nextLine();
                 if (num != 0 && num != 1) {
                        System.out.println("la opcio ha de ser 1 o 0");
                        numtf = false;
               
                } else {
                    if (num < 0) {
                    numtf = false;
                    } else {
                        return num;
                    }

                }
            } catch (InputMismatchException e) {
                System.err.println("Nomes se accepten numeros enters");
                System.out.print("Torna a provar: ");
                numtf = false;
            }
        } while (numtf = false);
        return 0;
    }

      public CarreraCaballos(){
          
      }
    public static void main(String[] args) {
        CarreraCaballos pro = new CarreraCaballos();
        System.out.println("Benvingut/da a les carreres de cavalls versio Java:");
        int opcio;
        int contador = 0;
        Scanner sc = new Scanner(System.in);
        do {
           
            System.out.println("1 - Entrar a una carrera 🏁");
            System.out.println("0 - Sortir 🚪");
            
            opcio = pro.demanarint();
       
            switch (opcio) {
                case 1:
                    Gestion pra = new Gestion();
                    pra.demanar();
                    break;
            }
            
        } while (opcio != 0);
        System.out.println("Adeu...");
        System.exit(666);
    }
}
