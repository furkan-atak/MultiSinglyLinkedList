
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hash;

import java.util.Random;

/**
 *
 * @author Pc
 */
public class NewClass {
    public static void main(String[] args) {
        int row,col;
        row = 3;
        col = 5;
        int[][] theMatris = matris(row, col);
        for (int i = 0; i < theMatris.length; i++) {
            for (int j = 0; j < theMatris[i].length; j++) {
                System.out.print(theMatris[i][j]);
            }
            System.out.println("");
        }
        System.out.println("*********");
        
        int[] arr = new int [col];
        float[] arr2 = new float [row];
        for (int i = 0; i < theMatris.length; i++) {
            for (int j = 0; j < theMatris[i].length; j++) {
                arr[j] = theMatris[i][j];
            }
            arr2[i] = ortalama(arr, col);
        }
        System.out.println("***************************");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println("Ort = " + arr2[i]);
        }
    }
 static int[][] matris(int row,int col){
  int[][] matris = new int[row][col];
  Random r = new Random();
  
  
    for (int i = 0; i < matris.length; i++) {
        for (int j = 0; j < matris[i].length; j++) {
            matris[i][j] = r.nextInt(10);
        }
    }
    return matris;
}
 
 static float ortalama(int[] dizi, int boyut){
 float sum,avg;
 sum=0;
 avg=0;
     for (int i = 0; i < dizi.length; i++) {
         sum += dizi[i];        
     }
  avg = sum/boyut;
     return avg;
 }

}


