/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Josep
 */
public class PremiGrossa {

    ArrayList<Integer> premis = new ArrayList<>();
    int ronda = 0;
    int RONDES_TOTALS = 1000000;
    int[] participacions = {8029, 725, 99123, 15287};
    int dinersInvertits = 0;
    int dinersGuanyats = 0;
    final int PREU_BITLLET = 10;

    final int counter_c1premi = 0;
    final int counter_c2premi = 1;
    final int counter_c3premi = 2;
    final int counter_c4premi = 3;
    final int counter_c5premi = 4;

    final int counter_ap1premi = 5;
    final int counter_ap2premi = 6;
    final int counter_ap3premi = 7;
    final int counter_ap4premi = 8;
    final int counter_ap5premi = 9;

    final int counter_c41premi = 10;
    final int counter_c42premi = 11;
    final int counter_c43premi = 12;
    final int counter_c44premi = 13;
    final int counter_c45premi = 14;

    final int counter_c31premi = 15;
    final int counter_c32premi = 16;
    final int counter_c33premi = 17;
    final int counter_c34premi = 18;
    final int counter_c35premi = 19;

    final int counter_c21premi = 20;
    final int counter_c22premi = 21;
    final int counter_c23premi = 22;
    final int counter_c24premi = 23;
    final int counter_c25premi = 24;

    final int counter_c11premi = 25;

    private int[] comptadors = new int[26];

    private final String[] nomComptadors = {
        "Comptador primer premi: ",
        "Comptador segon premi: ",
        "Comptador tercer premi: ",
        "Comptador quart premi: ",
        "Comptador cinqué premi: ",
        "Comptador primer premi aproximació: ",
        "Comptador segon premi aproximació: ",
        "Comptador tercer premi aproximació: ",
        "Comptador quart premi aproximació: ",
        "Comptador cinqué premi aproximació: ",
        "Comptador coincidencia 4 xifres primer premi: ",
        "Comptador coincidencia 4 xifres segon premi: ",
        "Comptador coincidencia 4 xifres tercer premi: ",
        "Comptador coincidencia 4 xifres quart premi: ",
        "Comptador coincidencia 4 xifres cinqué premi: ",
        "Comptador coincidencia 3 xifres primer premi: ",
        "Comptador coincidencia 3 xifres segon premi: ",
        "Comptador coincidencia 3 xifres tercer premi: ",
        "Comptador coincidencia 3 xifres quart premi: ",
        "Comptador coincidencia 3 xifres cinqué premi: ",
        "Comptador coincidencia 2 xifres primer premi: ",
        "Comptador coincidencia 2 xifres segon premi: ",
        "Comptador coincidencia 2 xifres tercer premi: ",
        "Comptador coincidencia 2 xifres quart premi: ",
        "Comptador coincidencia 2 xifres cinqué premi: ",
        "Comptador coincidencia 1 xifra primer premi: "
    };
    
    private String resumText = "";

//    public static void main(String[] args) {
//        int participacions[] = {10};
//        PremiGrossa app = new PremiGrossa(5,participacions);
//    }
    
    public PremiGrossa() {
        run();
    }

    public PremiGrossa(int rondes, int[] participacions) {
        this.RONDES_TOTALS = rondes;
        this.participacions = participacions;
        run();
        
    }

    public String getResumText() {
        return resumText;
    }

    public void run(){
        for (int i = 0; i < comptadors.length; i++) {
            comptadors[i] = 0;
        }

        printParticipacions();
        for (ronda = 0; ronda < RONDES_TOTALS; ronda++) {
            generaPremis();
            dinersInvertits += participacions.length * PREU_BITLLET;
            revisarPremis();
            System.out.println("Diners invertits: " + dinersInvertits);
            System.out.println("Diners guanyats: " + dinersGuanyats);
            premis.removeAll(premis);
        }
        mostrarResum();
        
    }

    private int generaNum() {
        Random randomizer = new Random();
        //genera un num entre 0 i 99998 i se li suma 1
        //el núm serà 1-99999 ambdos inclosos
        return randomizer.nextInt(99999) + 1;
    }

    private void generaPremis() {
        for (int i = 0; i < 8; i++) {
            int nouPremi = generaNum();
            //validar que no existeixi amb cap premi existent
            //si coincideix amb un premi inferior es regenera l'inferior
            int posTrobat = validaNouNum(nouPremi);
            premis.add(nouPremi);
            switch (posTrobat) {
                //primer premi coincidint amb un segon
                case 6:
                //segon o primer premi coincidint amb un tercer
                case 5:
                //primer segon o tercer premi coincidint amb un quart
                case 4:
                case 3:
                //primer, segon, tercer o quart premi coincidint amb un cinque
                case 2:
                //cinqué premi coincidint amb un altre cinqué
                case 1:
                case 0:
                    //es genera un nou num pel premi anterior
                    nouPremi = generaNum();
                    while (validaNouNum(nouPremi) != -1) {
                        nouPremi = generaNum();
                    }
                    premis.remove(posTrobat);
                    premis.add(posTrobat, nouPremi);
                    break;
                default:
                    //no s'ha trobat cap coincidencia i per tant el premi entra
                    //a la llista sense fer res
                    break;
            }

        }
        System.out.println("Ronda " + (ronda + 1) + ": " + premis);

    }

    private int validaNouNum(int nouNum) {
        for (int i = 0; i < premis.size(); i++) {
            if (nouNum == premis.get(i)) {
                return i;
            }
        }
        return -1;
    }

    private void printParticipacions() {
        System.out.print("Jugues als números: ");
        for (int i = 0; i < participacions.length; i++) {
            System.out.print(participacions[i] + ", ");
        }
        System.out.println();
    }

    private void revisarPremis() {
        for (int i = 0; i < participacions.length; i++) {
            for (int j = 0; j < premis.size(); j++) {
                String sPremi = Integer.toString(premis.get(j));
                String sParticipacio = Integer.toString(participacions[i]);
                sPremi = "00000" + sPremi;
                sParticipacio = "00000" + sParticipacio;

                int xifresCoincidents = xifresCoincidents(sPremi, sParticipacio);

                if (participacions[i] == premis.get(j)) {
                    //coincidencia premi
                    switch (j) {
                        case 7:
                            //coincidencia primer premi
                            System.out.println("Has guanyat un primer premi!!! (+200.000€)");
                            dinersGuanyats += 200000;
                            comptadors[counter_c1premi]++;
                            break;
                        case 6:
                            //coincidencia segon premi
                            System.out.println("Has guanyat un segon premi!!! (+65.000€)");
                            dinersGuanyats += 65000;
                            comptadors[counter_c2premi]++;
                            break;
                        case 5:
                            //coincidencia tercer premi
                            System.out.println("Has guanyat un tercer premi!!! (+30.000€)");
                            dinersGuanyats += 30000;
                            comptadors[counter_c3premi]++;
                            break;
                        case 4:
                        case 3:
                            //coincidencia quart premi
                            System.out.println("Has guanyat un quart premi!!! (+10.000€)");
                            dinersGuanyats += 10000;
                            comptadors[counter_c4premi]++;
                            break;
                        case 2:
                        case 1:
                        case 0:
                            //coincidencia cinque premi
                            System.out.println("Has guanyat un cinque premi!!! (+5.000€)");
                            dinersGuanyats += 5000;
                            comptadors[counter_c5premi]++;
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (participacions[i] - 1 == premis.get(j) || participacions[i] + 1 == premis.get(j)) {
                    //coincidencia per aproximacio +-1
                    switch (j) {
                        case 7:
                            //coincidencia primer premi
                            System.out.println("Has guanyat un primer premi un amunt un avall!!! (+2.000€)");
                            dinersGuanyats += 2000;
                            comptadors[counter_ap1premi]++;
                            break;
                        case 6:
                            //coincidencia segon premi
                            System.out.println("Has guanyat un segon premi un amunt un avall!!! (+650€)");
                            dinersGuanyats += 650;
                            comptadors[counter_ap2premi]++;
                            break;
                        case 5:
                            //coincidencia tercer premi
                            System.out.println("Has guanyat un tercer premi un amunt un avall!!! (+500€)");
                            dinersGuanyats += 500;
                            comptadors[counter_ap3premi]++;
                            break;
                        case 4:
                        case 3:
                            //coincidencia quart premi
                            System.out.println("Has guanyat un quart premi un amunt un avall!!! (+200€)");
                            dinersGuanyats += 200;
                            comptadors[counter_ap4premi]++;
                            break;
                        case 2:
                        case 1:
                        case 0:
                            //coincidencia cinque premi
                            System.out.println("Has guanyat un cinque premi un amunt un avall!!! (+150€)");
                            dinersGuanyats += 150;
                            comptadors[counter_ap5premi]++;
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (xifresCoincidents == 4) {
                    switch (j) {
                        case 7:
                            //coincidencia primer premi
                            System.out.println("Coincideixen les darreres quatre xifres d'un primer premi!!! (+1.000€)");
                            dinersGuanyats += 1000;
                            comptadors[counter_c41premi]++;
                            break;
                        case 6:
                            //coincidencia segon premi
                            System.out.println("Coincideixen les darreres quatre xifres d'un segon premi!!! (+300€)");
                            dinersGuanyats += 300;
                            comptadors[counter_c42premi]++;
                            break;
                        case 5:
                            //coincidencia tercer premi
                            System.out.println("Coincideixen les darreres quatre xifres d'un tercer premi!!! (+200€)");
                            dinersGuanyats += 200;
                            comptadors[counter_c43premi]++;
                            break;
                        case 4:
                        case 3:
                            //coincidencia quart premi
                            System.out.println("Coincideixen les darreres quatre xifres d'un quart premi!!! (+100€)");
                            dinersGuanyats += 100;
                            comptadors[counter_c44premi]++;
                            break;
                        case 2:
                        case 1:
                        case 0:
                            //coincidencia cinque premi
                            System.out.println("Coincideixen les darreres quatre xifres d'un cinqué premi!!! (+75€)");
                            dinersGuanyats += 75;
                            comptadors[counter_c45premi]++;
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (xifresCoincidents == 3) {
                    switch (j) {
                        case 7:
                            //coincidencia primer premi
                            System.out.println("Coincideixen les darreres tres xifres d'un primer premi!!! (+250€)");
                            dinersGuanyats += 250;
                            comptadors[counter_c31premi]++;
                            break;
                        case 6:
                            //coincidencia segon premi
                            System.out.println("Coincideixen les darreres tres xifres d'un segon premi!!! (+75€)");
                            dinersGuanyats += 75;
                            comptadors[counter_c32premi]++;
                            break;
                        case 5:
                            //coincidencia tercer premi
                            System.out.println("Coincideixen les darreres tres xifres d'un tercer premi!!! (+35€)");
                            dinersGuanyats += 35;
                            comptadors[counter_c33premi]++;
                            break;
                        case 4:
                        case 3:
                            //coincidencia quart premi
                            System.out.println("Coincideixen les darreres tres xifres d'un quart premi!!! (+30€)");
                            dinersGuanyats += 30;
                            comptadors[counter_c34premi]++;
                            break;
                        case 2:
                        case 1:
                        case 0:
                            //coincidencia cinque premi
                            System.out.println("Coincideixen les darreres tres xifres d'un cinqué premi!!! (+25€)");
                            dinersGuanyats += 25;
                            comptadors[counter_c35premi]++;
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (xifresCoincidents == 2) {
                    switch (j) {
                        case 7:
                            //coincidencia primer premi
                            System.out.println("Coincideixen les darreres dues xifres d'un primer premi!!! (+35€)");
                            dinersGuanyats += 35;
                            comptadors[counter_c21premi]++;
                            break;
                        case 6:
                            //coincidencia segon premi
                            System.out.println("Coincideixen les darreres dues xifres d'un segon premi!!! (+25€)");
                            dinersGuanyats += 25;
                            comptadors[counter_c22premi]++;
                            break;
                        case 5:
                            //coincidencia tercer premi
                            System.out.println("Coincideixen les darreres dues xifres d'un tercer premi!!! (+20€)");
                            dinersGuanyats += 20;
                            comptadors[counter_c23premi]++;
                            break;
                        case 4:
                        case 3:
                            //coincidencia quart premi
                            System.out.println("Coincideixen les darreres dues xifres d'un quart premi!!! (+15€)");
                            dinersGuanyats += 15;
                            comptadors[counter_c24premi]++;
                            break;
                        case 2:
                        case 1:
                        case 0:
                            //coincidencia cinque premi
                            System.out.println("Coincideixen les darreres dues xifres d'un cinqué premi!!! (+10€)");
                            dinersGuanyats += 10;
                            comptadors[counter_c25premi]++;
                            break;
                        default:
                            //error
                            break;
                    }
                } else if (xifresCoincidents == 1) {
                    switch (j) {
                        case 7:
                            //coincidencia primer premi
                            System.out.println("Coincideix la darrera xifra d'un primer premi!!! (+10€)");
                            dinersGuanyats += 10;
                            comptadors[counter_c11premi]++;
                            break;
                        case 6:
                            //coincidencia segon premi
                            //System.out.println("Coincideix la darrera xifra d'un segon premi!!!");
                            break;
                        case 5:
                            //coincidencia tercer premi
                            //System.out.println("Coincideix la darrera xifra d'un tercer premi!!!");
                            break;
                        case 4:
                        case 3:
                            //coincidencia quart premi
                            //System.out.println("Coincideix la darrera xifra d'un quart premi!!!");
                            break;
                        case 2:
                        case 1:
                        case 0:
                            //coincidencia cinque premi
                            //System.out.println("Coincideix la darrera xifra d'un cinqué premi!!!");
                            break;
                        default:
                            //error
                            break;
                    }
                }
            }
        }
    }

    private int xifresCoincidents(String s1, String s2) {
        String s1_4 = s1.substring(s1.length() - 4, s1.length());
        String s2_4 = s2.substring(s2.length() - 4, s2.length());

        String s1_3 = s1.substring(s1.length() - 3, s1.length());
        String s2_3 = s2.substring(s2.length() - 3, s2.length());

        String s1_2 = s1.substring(s1.length() - 2, s1.length());
        String s2_2 = s2.substring(s2.length() - 2, s2.length());

        String s1_1 = s1.substring(s1.length() - 1, s1.length());
        String s2_1 = s2.substring(s2.length() - 1, s2.length());
        if (s1_4.equals(s2_4)) {
            return 4;
        } else if (s1_3.equals(s2_3)) {
            return 3;
        } else if (s1_2.equals(s2_2)) {
            return 2;
        } else if (s1_1.equals(s2_1)) {
            return 1;
        } else {
            return 0;
        }
    }

    private void mostrarResum() {
        for (int i = 0; i < comptadors.length; i++) {
            System.out.println(nomComptadors[i] + comptadors[i]);
            resumText += nomComptadors[i] + comptadors[i] + "\n";
        }
        resumText += "Diners invertits: " + dinersInvertits + "\n";
        resumText += "Diners guanyats: " + dinersGuanyats;
    }

}
