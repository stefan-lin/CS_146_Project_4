import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Lambda on 10/9/2015.
 */
public class DataCount<T extends Comparable<T>>{
  private T   data  = null;
  private int count = 0;

  public DataCount(T data_in, int count_in){
    this.data  = data_in;
    this.count = count_in;
  } // END CONSTRUCTOR

  public T getData(){
    return this.data;
  }

  public int getCount(){
    return this.count;
  }

  public void copy(DataCount<T> new_obj){
    this.data = new_obj.data;
    this.count = new_obj.count;
  }

  @Override
  public String toString(){
    return "[" + data + "] - " + count;
  }

  /**
   * compare_by_data Public method
   * This method will generate a Comparison_Handler instance that knows how to
   * compare the DataCount objects. Based on the user requests, it would compare
   * the value by ascending order or descending order.
   *
   * @param order The parameter that represents what kind of sorting order.
   * @return  A Compare_Handler instance
   */
  public Comparison_Handler<DataCount<T>> compare_by_data(SortOrder order){
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = t1.data.compareTo(t2.data);

        return (order == SortOrder.DESCENDING)? -1 * cmpResult: cmpResult;
      } // END compare METHOD
    };  // END return STATEMENT
  } // END compare_by_data

  /**
   * compare_by_count Public method
   * This method will generate a Comparison_Handler instance that knows how to
   * compare the DataCount objects. Based on the user requests, it would compare
   * the value by ascending order or descending order.
   *
   * @param order The parameter that represents what kind of sorting order.
   * @return  A Compare_Handler instance
   */
  public Comparison_Handler<DataCount<T>> compare_by_count(SortOrder order){
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = ((Integer)t1.count).compareTo((Integer) t2.count);

        return (order == SortOrder.DESCENDING)? -1 * cmpResult: cmpResult;
      } // END compare METHOD
    }; // END return STATEMENT
  } // END compare_by_count METHOD

  /////////////////// TESTING /////////////////////////////////////////////////
  public static void main(String[] args) {
    System.out.println("======== MERGE SORT ==========");
    DataCount<String> [] dArr = new DataCount[10];
    dArr[0] = new DataCount<>("Z", 5);
    dArr[1] = new DataCount<>("A", 19);
    dArr[2] = new DataCount<>("F", 4);
    dArr[3] = new DataCount<>("G", 8);
    dArr[4] = new DataCount<>("D", 8);
    dArr[5] = new DataCount<>("E", 11);
    dArr[6] = new DataCount<>("R", 3);
    dArr[7] = new DataCount<>("T", 1);
    dArr[8] = new DataCount<>("I", 6);
    dArr[9] = new DataCount<>("M", 9);
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");

    InsertionSort<DataCount<String>> is = new InsertionSort<>();
    MergeSort<DataCount<String>> ms = new MergeSort<>();
    //////////////////////////// MERGE SORT ///////////////////////////////////
    System.out.println("Merge Sort - Ascending - Data");
    ms.sort(dArr, dArr[0].compare_by_data(SortOrder.ASCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    System.out.println("Merge Sort - Descending - Data");
    ms.sort(dArr, dArr[0].compare_by_data(SortOrder.DESCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    System.out.println("Merge Sort - Ascending - Count");
    ms.sort(dArr, dArr[0].compare_by_count(SortOrder.ASCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    System.out.println("Merge Sort - Descending - Count");
    ms.sort(dArr, dArr[0].compare_by_count(SortOrder.DESCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");

    /////////////////////// INSERTION SORT ////////////////////////////////////
    System.out.println("Insertion Sort - Ascending - Data");
    is.sort(dArr, dArr[0].compare_by_data(SortOrder.ASCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    System.out.println("Insertion Sort - Descending - Data");
    is.sort(dArr, dArr[0].compare_by_data(SortOrder.DESCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    System.out.println("Insertion Sort - Ascending - Count");
    is.sort(dArr, dArr[0].compare_by_count(SortOrder.ASCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    System.out.println("Insertion Sort - Descending - Count");
    is.sort(dArr, dArr[0].compare_by_count(SortOrder.DESCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");

  } // END MAIN METHOD
}