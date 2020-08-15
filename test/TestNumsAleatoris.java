
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Josep
 */
public class TestNumsAleatoris {

    public static void main(String[] args) {
        TestNumsAleatoris app = new TestNumsAleatoris();
    }

    public TestNumsAleatoris() {
        generarNums();
    }

    private void generarNums() {
        int numRandom;
        for (int i = 0; i < 10000; i++) {
            Random randomizer = new Random();
            numRandom = randomizer.nextInt(10)+1;
            //System.out.println("i" + i + ": " + numRandom);
            if (numRandom == 0) {
                System.out.println("0!!!");
            }else if (numRandom == 11){
                System.out.println("11!!!");
            }
        }
    }
}
