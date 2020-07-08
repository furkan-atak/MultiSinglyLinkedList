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
package multisinglylinkedlist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinkedList<T> {

    private Node<T> head;

    /* 
    Bu metod her karakteri bir defa ana node a (Node<T>) ve her sonraki karakteri öncekinin 
    liste noduna uygun şartların kontrolunu yapıp (lowercase,isalphabetic) ekleyerek multi singly list yapısını oluşturur.
     */
    void oku() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the path of your txt: ");
        String path = s.nextLine();

        File file = new File(path);
        int letter;
        int nextLet;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            Node<T> temp = head;
            br2.read();
            // bre2.read(); sonraki harf bundan dolayı dışarda bir adım attırıyorum.
            while ((letter = br.read()) != -1) {

                nextLet = br2.read();
                Character theLetter = (char) letter;
                Character nextLetter = (char) nextLet;
                if (Character.isAlphabetic(theLetter)) {
                    Character lowChar = Character.toLowerCase(theLetter);
                    if (!has((T) lowChar)) {
                        // labdaki addLast() metodu 
                        // sadece bi önceki adımda listede zaten olup olmadığı kontrol ediliyor

                        addLast((T) lowChar);
                    }
                    if (Character.isAlphabetic(nextLetter)) {
                        Character lowNext = Character.toLowerCase(nextLetter);
                        // ekleneceği ana düğümün datasını ve listedüğümü alıyor
                        addListNode((T) lowChar, (T) lowNext);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    Bu metodda datası girilen Node döndürülmüştür.(Ana node listesinde gezerek) 
    Bir çok metodda kullanılmıştır.
     */
    Node<T> findNode(T searched) {
        // en baştan başlıyoruz head'i temp değişkenine atarak
        Node<T> temp = head;

        while (temp != null) { // liste sonuna kadar dönüyoruz
            if (isEmpty()) {
                System.out.println("Empty list !"); // eğer liste boşsa 
                return null;
            } else if (temp.data.equals(searched)) { // eğer datası girilen node ile eşleşme olursa o Node u döndürüyor.
                return temp;
            }
            temp = temp.nextNode; // liste üzerinde ilerliyoruz
        }
        return temp;
    }

    /*   
    verilen data ile ana düğüm listesi üzerinde gezerek
    node un listede olup olmadığını kontrol eden metod.
    Aslında find() metodu ile benzer fakat bu metod sadece
    true false, find metodu ise direk Node döndürüyor.
     */
    boolean has(T searched) {
        Node<T> temp = head;

        while (temp != null) {
            if (isEmpty()) {
                System.out.println("Empty list !");
                return false;
            } else if (temp.data.equals(searched)) {
                return true;
            }
            temp = temp.nextNode;
        }
        return false;
    }

    //  has metodu Node<T> parametreli override.
    boolean has(Node<T> theNode) {
        return has(theNode.data);
    }

    /*
    Ana düğüm ve eklenecek liste düğümü parametre olarak alınarak,
    anadüğüm
     */
    private void addListNode(Node<T> theNode, SecondNode<T> listNode) {
        if (theNode.listNode == null) { // eğer girilen Node un liste düğümü boşsa direk ekliyor
            theNode.listNode = listNode;
        } else {
            // ana düğümü ve onun ilk list node unu değişkenlere atıyoruz (liste üzerinde oynamaları önlemek için)          
            Node<T> temp = theNode;
            SecondNode<T> listTemp = temp.listNode;
            /* 
            liste üzerinde geziniyoruz ve eğer liste sonuna ulaşırsak veya 
            daha önce bu liste node u ana node a eklenmişse listeden çıkıyoruz.
             */
            while (listTemp.listNodeNext != null && !listTemp.data.equals(listNode.data)) {
                listTemp = listTemp.listNodeNext;
            }
            if (listTemp != null && listTemp.data.equals(listNode.data)) { // eğer bulduysa onu arttırıyoruz
                temp.listNode.count++;
            } else {       // bulamadıysa verilen liste node unu (SecondNode<T>) ana node un liste nodu unun en sonuna ekliyoruz.
                listTemp.listNodeNext = listNode;
            }

        }

    }

    // addListNode() metodu data parametresi ile oluşturma override.
    private void addListNode(T nodeData, T listData) {
        Node<T> theNode = findNode(nodeData);
        SecondNode<T> listNode = new SecondNode<>(listData);
        addListNode(theNode, listNode);
    }
    // datası girilen değerin liste Node unu döndürür

    void ardisikKarakterler(T nodeData) {
        // node u datasıyla buluyoruz ve kendi listesi üzerinde dolanıyoruz.
        Node<T> theNode;
        theNode = findNode(nodeData);
        if (theNode != null) { // eğer node ana düğüm listesinde varsa if e girebilir
            System.out.println("Ana harfiniz: " + theNode.data + "\n" + "Sonrasında gelen karakterler ve sayıları:");
            // bu metod girilen ana node un liste nodelarını döndürür
            printList(theNode);
        } else {
            System.out.println("Girdiniz ana düğümde bulunamadı. ");
        }
    }

    // en çok art arda gelen harfi yani liste node count değeri en yüksek ana node u ve onun liste node unu bulur
    void enCokArdisik() {
        Node<T> temp = head;
        Integer bigCount = 1;
        Node<T> bigNode = null; // variable might not been initialized hatası için null atıyorum 
        SecondNode<T> listTemp;
        SecondNode<T> bigList = null;  // aradığımı bulduğumda kaydetmek için boş node ve list node u oluşturdum

        //hem ana node hem onun liste node u üzerinde dönüyorum en çok tekrar edeni bulmak için
        while (temp != null) {
            listTemp = temp.listNode;
            while (listTemp != null) {
                if (listTemp.count > bigCount) { //default değer olandan büyük count değerine sahipse bu liste nodu içeri girer
                    bigCount = listTemp.count;  // en büyük mü kontrolu devam etmesi için count u değiştirir
                    bigNode = temp;
                    bigList = listTemp;
                }
                listTemp = listTemp.listNodeNext;  // list nodelar üzerinde ilerleme
            }
            temp = temp.nextNode;  // ana nodelar üzerinde ilerleme
        }
        if (bigNode != null && bigList != null) {
            System.out.println("Ana düğüm: " + bigNode.data + " ve en çok tekrar edeni: " + bigList.data + " " + bigList.count + " kere.");
        }
    }
    // datası verilen node un en çok tekrar eden list node unu bulur

    void enCokArdisik(T nodeData) {
        Node<T> theNode = findNode(nodeData);
        SecondNode<T> listTemp = theNode.listNode;
        SecondNode<T> bigList = null;
        Integer count = 0;
        while (listTemp != null) {
            if (listTemp.count > count) {
                count = listTemp.count;
                bigList = listTemp;
            }
            listTemp = listTemp.listNodeNext;
        }
        if (bigList != null) {
            System.out.println(theNode.data + " karakterinden sonra en çok gelen karakter " + bigList.data + " ve " + bigList.count + " kere.");
        }
    }

    // en çok işleminin tersi tek farkı < işareti
    void enAzArdisik() {
        Node<T> temp = head;
        SecondNode<T> tempList = temp.listNode;
        Integer lessCount = tempList.count;   // en az için headin list node unu tutuyorum
        Node<T> smallNode = null;
        SecondNode<T> listTemp;
        SecondNode<T> smallList = null;

        while (temp != null) {
            listTemp = temp.listNode;
            while (listTemp != null) {

                if (listTemp.count < lessCount) {
                    lessCount = listTemp.count;
                    smallNode = temp;
                    smallList = listTemp;
                }
                listTemp = listTemp.listNodeNext;
            }
            temp = temp.nextNode;
        }
        if (smallNode != null && smallList != null) {
            System.out.println("Ana düğüm: " + smallNode.data + " ve en az arkasından gelen: " + smallList.data + " " + smallList.count + " kere.");
        }
    }

    // yine en çok işlemini verilen node üzerinden yapılan metodun en az metodu.
    void enAzArdisik(T nodeData) {
        Node<T> theNode = findNode(nodeData);
        if (theNode != null) {  // eğer girilen node yoksa kod çalışmaz.
            SecondNode<T> listTemp = theNode.listNode;
            SecondNode<T> smallList = null;
            Integer count = listTemp.count;
            while (listTemp != null) {
                if (listTemp.count < count) {
                    count = listTemp.count;
                    smallList = listTemp;
                }
                listTemp = listTemp.listNodeNext;
            }
            if (smallList != null) {
                System.out.println(theNode.data + " karakterinden sonra en az gelen karakter " + smallList.data + " ve " + smallList.count + " kere.");
            } else { // ilk list node en düşük olunca burası çalışır.
                count = theNode.listNode.count;
                smallList = theNode.listNode;
                System.out.println(theNode.data + " karakterinden sonra en az gelen karakter " + smallList.data + " ve " + smallList.count + " kere.");
            }
        } else {
            System.out.println("Girdiğiniz karakter ana düğümde yer almamaktadır.");
        }
    }
    // Lab metodu
    void addFirst(Node<T> newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.nextNode = head;
            head = newNode;
        }
    }
    
    void addFirst(T newData) {
        addFirst(new Node<>(newData));
    }

    void addLast(Node<T> newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> temp = head;

            while (temp.nextNode != null) {
                temp = temp.nextNode;
            }
            temp.nextNode = newNode;
        }
    }

    void addLast(T newData) {
        addLast(new Node<>(newData));
    }

    boolean addAfter(T search, T newData) {
        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {
            Node<T> temp = head;

            while (temp != null && !temp.data.equals(search)) {
                temp = temp.nextNode;
            }

            if (temp != null) {
                Node<T> newNode = new Node<>(newData);
                newNode.nextNode = temp.nextNode;
                temp.nextNode = newNode;
                return true;
            }
        }
        return false;
    }

    Node<T> remove(T data) {
        Node<T> removedNode = null;

        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {

            if (head.data.equals(data)) {
                removedNode = head;
                head = head.nextNode;
            } else {
                Node<T> temp = head;

                while (temp.nextNode != null && !temp.nextNode.data.equals(data)) {
                    temp = temp.nextNode;
                }

                if (temp.nextNode != null) {
                    removedNode = temp.nextNode;
                    temp.nextNode = temp.nextNode.nextNode;
                }
            }
        }

        return removedNode;
    }
    // Labda Yazdığımız print kodunu ana düğümlerin liste düğümlerini de ekleyecek şekilde revize ettim.
    void print() {
        Node<T> temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            System.out.println("Listesi: ");
            // bu metodu başka yerlerde direk kullandığım için ayırdım
            printList(temp);
            System.out.println(" idi.");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }
    // parametre olarak girilen node un liste node larını yazdırır.
    void printList(Node<T> node) {
        SecondNode<T> temp = node.listNode;
        while (temp != null) {
            System.out.print(temp.data + " harfi " + temp.count + " kere ");
            temp = temp.listNodeNext;
        }

        System.out.println("null");
    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        Node<T> temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }

        return count;
    }
}
