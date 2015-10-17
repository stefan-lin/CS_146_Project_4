public class TestQuickSort{  
  public static void main(String a[]){
    QuickSort quick = new QuickSort();
      int[] array= {24,2,45,20,56,75,2,56,99,53,12};
        quick.sort(array);
        for(int i:array){
          System.out.print(i);
          System.out.print(" ");
        }
    }
}