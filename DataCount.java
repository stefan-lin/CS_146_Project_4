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

  public Comparison_Handler<DataCount<T>> compare_by_data(boolean flag){
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = t1.data.compareTo(t2.data);

        return (flag)? cmpResult: cmpResult * -1;
      } // END compare METHOD
    };  // END return STATEMENT
  } // END compare_by_data

  public Comparison_Handler<DataCount<T>> compare_by_count(boolean flag){
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = ((Integer)t1.count).compareTo((Integer) t2.count);

        return (flag)? cmpResult: cmpResult * -1;
      } // END compare METHOD
    }; // END return STATEMENT
  } // END compare_by_count METHOD

  public static void dummySort(DataCount<String> dc[],
                               Comparison_Handler<DataCount<String>> cmph){
    for(int i=1; i < dc.length; i++){
      DataCount<String> temp = dc[i];
      int j;
      for(j = i-1; j >= 0; j--){
        int rslt = cmph.compare(temp, dc[j]);
        if(rslt <= 0){
          break;
        } // END IF STATEMENT
        dc[j + 1] = dc[j];
      } // END FOR LOOP
      dc[j + 1] = temp;
    } // END FOR LOOP
  } // END dummysort METHOD

  //public static void main(String[] args) {
  //  DataCount<String> arr []= new DataCount[5];
//
  //  arr[0] = new DataCount<>("Z", 4);
  //  arr[1] = new DataCount<>("H", 3);
  //  arr[2] = new DataCount<>("B", 5);
  //  arr[3] = new DataCount<>("T", 1);
  //  arr[4] = new DataCount<>("K", 2);
//
  //  for(DataCount<String> i : arr){
  //    System.out.println(i);
  //  }
  //  System.out.println("-----------------");
//
  //  dummySort(arr, arr[0].compare_by_data(true));
//
  //  for(DataCount<String> i : arr){
  //    System.out.println(i);
  //  }
  //  System.out.println("============================");
//
  //  dummySort(arr, arr[0].compare_by_data(false));
//
  //  for(DataCount<String> i : arr){
  //    System.out.println(i);
  //  }
  //  System.out.println();
//
  //  System.out.println("-----------------");
//
  //  // counter comparison
  //  dummySort(arr, arr[0].compare_by_count(true));
//
  //  for(DataCount<String> i : arr){
  //    System.out.println(i);
  //  }
  //  System.out.println("============================");
//
  //  dummySort(arr, arr[0].compare_by_count(false));
//
  //  for(DataCount<String> i : arr){
  //    System.out.println(i);
  //  }
  //  System.out.println();
  //}

  public static void main(String[] args) {
    InsertionSort<DataCount<String>> is = new InsertionSort<>();
    ArrayList<DataCount<String>> arr = new ArrayList<>();
    arr.add(new DataCount<>("Z", 4));
    arr.add(new DataCount<>("H", 3));
    arr.add(new DataCount<>("B", 5));
    arr.add(new DataCount<>("T", 1));
    arr.add(new DataCount<>("K", 2));

    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_count(false));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_count(true));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_data(false));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_data(true));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");

  } // END MAIN METHOD
}
