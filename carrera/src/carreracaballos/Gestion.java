package carreracaballos;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestion {

    Scanner sc = new Scanner(System.in);
    int km = 0;
    int cavallstotals = 0;
    int jinete = 0;
    ArrayList<String> acabats = new ArrayList<String>();
    ArrayList<Integer> temps = new ArrayList<Integer>();

    boolean reiniciado = false;

    public int DemanarCavalls() {
        int num = 0;
        boolean numtf = false;
        do {
            try {
                System.out.print("Introdueix el nombre de cavalls que vols que participin a la carrera: ");
                Scanner sc = new Scanner(System.in);
                num = sc.nextInt();
                if (num < 0) {
                    numtf = false;
                } else {
                    if (num < 10) {
                        System.out.println("El minim de cavalls es 10");
                        numtf = false;
                    } else {
                        return num;
                    }

                }
            } catch (InputMismatchException e) {
                System.err.println("Nomes se accepten numeros...");
                System.out.print("Torna a provar: ");
                numtf = false;
            }
        } while (!numtf);
        return 0;
    }

    public int DemanarKm() {
        int num = 0;
        boolean numtf = false;
        do {
            try {
                System.out.print("Introdueix el nombre de Kilometres que vols que duri la carrera: ");
                Scanner sc = new Scanner(System.in);
                num = sc.nextInt();
                if (num <= 0) {
                    System.out.println("La carrera ha de ser mes llarga");
                    numtf = false;
                } else {
                    return num;

                }
            } catch (InputMismatchException e) {
                System.err.println("Nomes s'accepten numeros...");
                System.out.print("Torna a provar: ");
                numtf = false;
            }
        } while (!numtf);
        return 0;
    }

    public int demanararjinete() {
        int num = 0;
        boolean numtf = false;
        do {
            try {
                System.out.print("Introdueix el numero del cavall que vols montar: (0 = no montar cap cavall)");
                Scanner sc = new Scanner(System.in);
                num = sc.nextInt();
                if (num < 0) {
                    numtf = false;
                } else {
                    if (num > cavallstotals) {
                        numtf = false;
                    } else {
                        return num;

                    }

                }
            } catch (InputMismatchException e) {
                System.err.println("Nomes se accepten numeros...");
                System.out.print("Torna a provar: ");
                numtf = false;
            }
        } while (!numtf);
        return 0;
    }

    public void demanar() {

        cavallstotals = DemanarCavalls();

        km = DemanarKm();

        jinete = demanararjinete();
        conta_enrera();
    }

    public void conta_enrera() {
        System.out.println("3");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("2");

        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("1");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("!!!!!!!...JA...!!!!!!!");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
        }
        crearcavalls();
    }

    public Gestion() {
    }

    public void crearcavalls() {
        Caballo[] fils = new Caballo[cavallstotals];
        for (int i = 0; i < cavallstotals; i++) {
            fils[i] = new Caballo(km, Integer.toString(i + 1));
        }
       
        
carrera(fils);
    }
    
    public void carrera(Caballo[] fils){
         for (int i = 0; i < cavallstotals; i++) {
            fils[i].start();
        }
         while(acabats.size() < cavallstotals){
            for(int i = 0; i < fils.length; i++){
                mostrar(fils[i]);
                 if (fils[i].getAcabat() != false) {
                        if (!acabats.contains(fils[i].getName())) {
                            acabats.add(fils[i].getName());
                            temps.add(fils[i].getT0());
                        }
                    }
            }
           if (acabats.size() >= 3 && !reiniciado) {
                        pausarcarrera(fils);
                        if (!menu(fils)) {
                            break;
                        }
                    }
             try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        mostrarpodio();     
  }
    
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void mostrar(Caballo fil) {
                    if (fil.getName().equals(Integer.toString(jinete))) {
                        if (fil.getvelocitat() == 0) {
                            System.out.println("Cavall numero: " + fil.getName() + " (El teu cavall)");
                            System.out.println(ANSI_RED + "🏁" + "-".repeat(fil.repetir2) + "\uD83C\uDFC7" + "-".repeat(fil.darrera) + "0KM/H ❄❄❄❄❄" + ANSI_RESET);
                            System.out.printf("Queden: %.2f KM", fil.getD());
                            System.out.println(" t:" +fil.getT0() + "s");
                        } else {
                            System.out.println("Cavall numero: " + fil.getName() + " (El teu cavall)");
                            System.out.println(ANSI_RED + "🏁" + "-".repeat(fil.repetir2) + "\uD83C\uDFC7" + "-".repeat(fil.darrera) + fil.getvelocitat() + "KM/H" + ANSI_RESET);
                            System.out.printf("Queden: %.2f KM", fil.getD());
                            System.out.println(" t:" + fil.getT0() + "s");
                        }
                    } else {

                        if (fil.getvelocitat() == 0) {
                            System.out.println("Cavall: " + fil.getName());
                            System.out.println("🏁" + "-".repeat(fil.repetir2) + "\uD83C\uDFC7" + "-".repeat(fil.darrera) + "0KM/H ❄❄❄❄❄");
                            System.out.printf("Queden: %.2f KM", fil.getD());
                            System.out.println(" t:" + fil.getT0() + "s");
                        } else {
                            System.out.println("Cavall numero: " + fil.getName());
                            System.out.println("🏁" + "-".repeat(fil.repetir2) + "\uD83C\uDFC7" + "-".repeat(fil.darrera) + fil.getvelocitat() + "KM/H");
                            System.out.printf("Queden: %.2f KM", fil.getD());
                            System.out.println(" t:" + fil.getT0() + "s");
                        }
                    }
        }

    

    public void pausarcarrera(Caballo[] fils) {
        for (int i = 0; i < fils.length; i++) {
            fils[i].setContinuar(false);
        }
    }
    

    public boolean menu(Caballo[] fils) {
        Scanner sc = new Scanner(System.in);
        String opcio = "";
        do {
            System.out.print("Vols continuar la carrera S/N? ");
            opcio = sc.nextLine();
        } while (!opcio.equalsIgnoreCase("S") && !opcio.equalsIgnoreCase("N"));
        if (opcio.equalsIgnoreCase("S")) {
            reiniciado = true;
            continuarcarrera(fils);
            return true;
        } else {
            acabarcarrera(fils);
        }
        return false;
    }

    public void continuarcarrera(Caballo[] fils) {
        for (int i = 0; i < fils.length; i++) {
            fils[i].setContinuar(true);
        }
    }

    public void acabarcarrera(Caballo[] fils) {
        for (int i = 0; i < fils.length; i++) {
            fils[i].stop();
        }

    }
     public void nova(Caballo[] fils) {
        for (int i = 0; i < fils.length; i++) {
            try {
                fils[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    public void mostrarpodio() {
        System.out.println(""
                + "⠀⠀⠀⠀⠀⠀         " + "Cavall: " + acabats.get(0) + " " + temps.get(0) + "s⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠙⢹⡏⠋⠋⠋⠙⠙⠉⠻⡏⠋⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣆⢸⡂⠀⢠⡼⠶⣄⠀⠨⡇⣠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠺⣇⠀⠨⠷⠾⠅⠀⣸⠿⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢧⣤⣤⣤⣤⡼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠶⡶⢲⡶⠓⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣞⠙⠙⠙⠙⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠖⠲⠚⠖⠳⠚⠖⠺⠒⠟⠳⠚⠖⠳⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣄⣀⣄⣀⣄⣀⣄⣀⣄⣀⣄⣀⣄⣠⡽⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀" + "Cavall " + acabats.get(1) + " " + temps.get(1) + "s⠀⠀⠀⠀⠀⡗⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⣴⠚⠓⠛⠚⠓⠛⠚⠛⠚⠛⠛⡧⠀⠀⠀⠀⠀⠀⠀⠀⢸  " + "Cavall " + acabats.get(2) + " " + temps.get(2) + "s⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠷⣄⣄⣄⣄⣄⣄⣄⣠⣀⣄⣄⡗⠀⠀⠀⠀⠀⠀⠀⠀⢸⡤⡤⡤⡤⡤⡤⡤⡤⡤⡤⣤⣀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢸⠆⠀⠀⠀⠀⠀⠀⠀⠀⣏⠀⠀⠀⢘⡇⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣹⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢸⡃⠀⠀⠀⣀⠀⠀⠀⠀⡧⠀⠀⠀⢸⡇⠀⠀⠀⢸⠋⠛⠙⠋⠛⠙⠋⠛⠹⡟⠉⠁⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢸⡃⠀⠰⡋⠉⢳⡄⠀⠀⡗⠀⠀⠀⢰⡇⠀⠀⠀⢸⠀⠀⠀⣤⠶⣤⠀⠀⠨⡇⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢸⡃⠀⠀⢀⡴⠏⠀⠀⠀⣏⠀⠀⠀⠘⠇⠀⠀⠀⢸⠀⠀⠘⠁⣀⣸⠂⠀⠨⡇⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢸⡃⠀⢰⢯⢤⢤⠤⠀⠀⡧⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⢠⡀⠉⢻⠄⠀⠨⡇⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢸⡃⠀⠀⠀⠀⠀⠀⠀⠀⡗⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠈⠳⠶⠚⠁⠀⠨⡇⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⣀⣄⣼⣃⣀⣀⣀⣀⣀⣀⣠⣠⣏⣀⣀⣀⣀⣀⣀⣀⣀⣸⣄⣄⣠⣀⣀⣀⣀⣀⣨⣧⣠⣀⠀⠀⠀\n"
                + "⠀⠀⠀⠈⠀⠁⠁⠁⠁⠁⠁⠁⠁⠁⠈⠈⠈⠈⠈⠈⠈⠈⠈⠈⠈⠈⠈⠀⠁⠁⠁⠁⠁⠁⠁⠈⠀⠀⠀⠀");
        for (int i = 3; i < acabats.size(); i++) {
            int numero = i + 1;
            System.out.println("-------------------------------");
            System.out.println("| Posicio nº " + numero + " cavall nº" + acabats.get(i) + " " + temps.get(i) + "s |");
            System.out.println("-------------------------------");
        }
    }

}
