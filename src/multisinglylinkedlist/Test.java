package multisinglylinkedlist;
/*
@ Girilen herhangi bir dosya yolu.
@ Bu classta kullanıcıdan oku() metoduyla, yolu girdi olarak alınan
 dosyanın karakterlerini okuyarak her karakteri bir defa olmak üzere,
 ana node (Node<T>) ve her karakterten sonra gelen karakteri, önceki karakterin liste
 noduna  (SecondNode<T>) ekleyerek, generic olarak multi singly list yapısı oluşturmaya olanak kıldım.
@ ## Ödev 1 ##
@20-24 April 2020 ( Day n Night :) )
@author : Furkan ATAK , mail --> furkan.atak@stu.fsm.edu.tr
********* ########## ************
 */
public class Test {

    public static void main(String[] args) {
        LinkedList<Character> liste = new LinkedList<>();
        liste.oku();

        System.out.println("\n" + " Birinci metod " + "\n");
        char a = 'a';
        liste.ardisikKarakterler(a);
        
        
        System.out.println("\n" + " İkinci metod " + "\n");
        liste.enCokArdisik();
        
        System.out.println("\n" + " Üçüncü metod " + "\n");
        char b = 'a';
        liste.enCokArdisik(b);
        
        System.out.println("\n" + " Dördüncü metod " + "\n");
        liste.enAzArdisik();
        
        System.out.println("\n" + " Beşinci metod " + "\n");
        char c = 'a';
        liste.enAzArdisik(c);
        
        
        System.out.println("\n" + " ANA DÜĞÜMLER VE LİSTE DÜĞÜMLERİ " + "\n");
        liste.print();

    }
}
