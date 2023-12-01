package carreracaballos;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Caballo extends Thread {

    int metros;
    int num = 0;
    int numeroguions = 40;
    int velocitat = 50;
    double totalavans;
    int darrera = 0;
    int repetir2 = 0;
    double d = 0;
    boolean acabat = false;
    int contador = 0;
    boolean continuar = true;
    int t0 = 0;

    public Caballo(int km, String nom) {
        this.metros = km * 1000;
        this.setName(nom);
        this.velocitat = 50; // Velocidad inicial de 50 km/h
    }

    public int getT0() {
        return t0;
    }

    public void setT0(int t0) {
        this.t0 = t0;
    }

    public int getDarrera() {
        return darrera;
    }

    public int getRepetir2() {
        return repetir2;
    }

    public int getvelocitat() {
        return velocitat;
    }

    public double getD() {
        return d;
    }

    public boolean getAcabat() {
        return acabat;
    }

    public boolean isContinuar() {
        return continuar;
    }

    public void setAcabat(boolean acabat) {
        this.acabat = acabat;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    @Override
    public void run() {
        while (totalavans < metros) {
            if (continuar) {
                t0 = t0 + 1;

                // Cambiar la velocidad cada segundo después de empezar a 50
                if (t0 > 0 && t0 % 2 == 0) {
                    velocitat = velocitatnova();
                }

                if (velocitat == 70 || velocitat == 50 || velocitat == 30) {
                    if (contador != 3) {
                        double barres = metros / numeroguions;
                        double avansminut = velocitat * 1000 / 60;
                        double avanssegon = avansminut / 60;
                        if (totalavans + avanssegon >= metros) {
                            acabat = true;
                            totalavans = metros;
                        } else {
                            totalavans = totalavans + avanssegon;
                        }
                        int repetir = (int) (totalavans / barres);
                        darrera = num + repetir;
                        repetir2 = numeroguions - repetir;
                        double restant = metros - (int) totalavans;
                        d = restant / 1000;
                        contador++;
                    } else {
                        double barres = metros / numeroguions;
                        double avansminut = velocitat * 1000 / 60;
                        double avanssegon = avansminut / 60;
                        if (totalavans + avanssegon >= metros) {
                            acabat = true;
                            totalavans = metros;
                        } else {
                            totalavans = totalavans + avanssegon;
                        }
                        int repetir = (int) (totalavans / barres);
                        darrera = num + repetir;
                        repetir2 = numeroguions - repetir;
                        double restant = metros - (int) totalavans;
                        d = restant / 1000;
                        contador = 0;
                    }

                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    double barres = metros / numeroguions;
                    double avansminut = velocitat * 1000 / 60;
                    double avanssegon = avansminut / 60;
                    if (totalavans + avanssegon >= metros) {
                        acabat = true;
                        totalavans = metros;
                    } else {
                        totalavans = totalavans + avanssegon;
                    }
                    int repetir = (int) (totalavans / barres);
                    darrera = num + repetir;
                    repetir2 = numeroguions - repetir;
                    double restant = metros - (int) totalavans;
                    d = restant / 1000;

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CarreraCaballos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Caballo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int velocitatnova() {
        Random rand = new Random();
        int ram = rand.nextInt(1, 100);
        if (ram > 50) {
            int sumar = rand.nextInt(1, 5);
            velocitat = velocitat + sumar;
            if (velocitat > 70) {
                velocitat = 70;
            }
            if (velocitat < 15) {
                velocitat = 15;
            }
        } else {
            int restar = rand.nextInt(1, 5);
            velocitat = velocitat - restar;
            if (velocitat > 70) {
                velocitat = 70;
            }
            if (velocitat < 15) {
                velocitat = 15;
            }
        }
        return velocitat;
    }
}
