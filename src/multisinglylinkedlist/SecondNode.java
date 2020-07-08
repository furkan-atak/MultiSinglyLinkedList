package multisinglylinkedlist;

public class SecondNode<T> {
   /*
    Bu SecondNode<T> ana düğümlerin liste düğümleri.
    Her Ana Düğümde kaç tane aynı liste düğümü var bu bilgi için,
    SecondNode<T> count instance ını oluşturdum. Ve aynı Node yapısında olduğu gibi
    Ana Düğümün liste düğümlerinin bağlantısı için ListNodeNext instance ını oluşturdum.
    */
   T data;
   Integer count=1;
   SecondNode<T> listNodeNext;

    public SecondNode(T data) {
        this.data = data;
        this.count = count;
        this.listNodeNext = listNodeNext;
    }

   

   
    
  
    

}
