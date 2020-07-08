package multisinglylinkedlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NewClass {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the path of your txt: ");
        String path = s.nextLine();
        LinkedList<Integer> theList = new LinkedList<>();
        ArrayList<Integer> repeats = new ArrayList<>();
        File file = new File(path);
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        int letter;
        
        while ((letter = br.read()) != -1) {            
            if(!theList.has(letter)){
                
            }else{
               
            }
        }
    }

}
