package multisinglylinkedlist;


public class Node<T> {
    /*
    Bildiğimiz Node ekstra olarak kendisine ait bir liste nodu tutuyor. (SecondNode<T>)
    Bu sayede kendi bağlantısı dışında ekstra bir dallanda daha oluyor
    */
    
    
    T data; 
    Node<T> nextNode;
    SecondNode<T> listNode;
    public Node(T data) {
        this.data = data;
    }

   
   
}
