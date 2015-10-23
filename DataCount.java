import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Lambda on 10/9/2015.
 */
public class DataCount<T extends Comparable<T>>{
  private T   data  = null;
  private int count = 0;

  /**
   * DataCount CONSTRUCTOR
   *
   * CONSTRUCT THE DataCount OBJECT
   *
   * @param data_in  INPUT DATA
   * @param count_in DATA COUNT
   */
  public DataCount(T data_in, int count_in){
    this.data  = data_in;
    this.count = count_in;
  } // END CONSTRUCTOR

  /**
   * SIMPLE GETTER METHOD
   *
   * @return DATA
   */
  public T getData(){
    return this.data;
  }

  /**
   * SIMPLE GETTER METHOD
   *
   * @return NUMBER OF COUNTS
   */
  public int getCount(){
    return this.count;
  }

  /**
   * COPY METHOD
   *
   * COPYING BETWEEN TWO DataCount OBJECTS (DEEP COPY)
   *
   * @param new_obj
   */
  public void copy(DataCount<T> new_obj){
    this.data = new_obj.data;
    this.count = new_obj.count;
  }

  /**
   * OVERRIDE toString METHOD
   *
   * @return STRING INDICATES DataCount OBJECT
   */
  @Override
  public String toString(){
    return count + " \t" + data;
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
   * [ASCENDING MODE]
   * A, B, ......, Z
   * small ....... large
   *
   * 1, 2, ......, 10
   * small ....... large
   *
   * [DESCENDING MODE]
   * A, B, ......, Z
   * large ....... small
   *
   * 1, 2, ......, 10
   * large ....... small
   */

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
}

/**
 * Interface for a data structure that allows you to count the number of times
 * you see each piece of data.
 *
 * Although you will be using this interface only with Strings, we have tried to
 * "genericize" the code as much as possible. DataCounter counts elements of an
 * unconstrained generic type E, and BinarySearchTree restricts E to Comparable
 * types. HashTable is String-only, because you'll be implementing your own
 * hashcode and will need access to the actual String contents.
 *
 * @param <E> The type of data to be counted.
 */
interface DataCounter<E extends Comparable<E>> {

  /**
   * Increment the count for a particular data element.
   *
   * @param data data element whose count to increment.
   */
  public void incCount(E data);

  /**
   * The number of unique data elements in the structure.
   *
   * @return the number of unique data elements in the structure.
   */
  public int getSize();

  /**
   * Get an array of all of the data counts in the DataCounter structure. The
   * array should contain exactly one DataCount instance for each unique
   * element inserted into the structure. The elements do not need to be in
   * any particular order.
   *
   * @return an array of the data counts.
   */
  public DataCount<E>[] getCounts();

}

/**
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

 QuickSort<DataCount<String>> qs = new QuickSort<>();
 ////////////////////////////// QUICK SORT /////////////////////////////////
 qs.sort(dArr, dArr[0].compare_by_count(SortOrder.ASCENDING));
 System.out.println("Quick Sort - Ascending - Count");
 for(DataCount<String> dc : dArr){
 System.out.println(dc);
 }
 System.out.println("============================");
 qs.sort(dArr, dArr[0].compare_by_count(SortOrder.DESCENDING));
 System.out.println("Quick Sort - Descending - Count");
 for(DataCount<String> dc : dArr){
 System.out.println(dc);
 }
 System.out.println("============================");
 qs.sort(dArr, dArr[0].compare_by_data(SortOrder.ASCENDING));
 System.out.println("Quick Sort - Ascending - Data");
 for(DataCount<String> dc : dArr){
 System.out.println(dc);
 }
 System.out.println("============================");
 qs.sort(dArr, dArr[0].compare_by_data(SortOrder.DESCENDING));
 System.out.println("Quick Sort - Descending - Data");
 for(DataCount<String> dc : dArr){
 System.out.println(dc);
 }
 System.out.println("============================");

 //InsertionSort<DataCount<String>> is = new InsertionSort<>();
 //MergeSort<DataCount<String>> ms = new MergeSort<>();
 ////////////////////////////// MERGE SORT ///////////////////////////////////
 //System.out.println("Merge Sort - Ascending - Data");
 //ms.sort(dArr, dArr[0].compare_by_data(SortOrder.ASCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //System.out.println("Merge Sort - Descending - Data");
 //ms.sort(dArr, dArr[0].compare_by_data(SortOrder.DESCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //System.out.println("Merge Sort - Ascending - Count");
 //ms.sort(dArr, dArr[0].compare_by_count(SortOrder.ASCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //System.out.println("Merge Sort - Descending - Count");
 //ms.sort(dArr, dArr[0].compare_by_count(SortOrder.DESCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //
 ///////////////////////// INSERTION SORT ////////////////////////////////////
 //System.out.println("Insertion Sort - Ascending - Data");
 //is.sort(dArr, dArr[0].compare_by_data(SortOrder.ASCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //System.out.println("Insertion Sort - Descending - Data");
 //is.sort(dArr, dArr[0].compare_by_data(SortOrder.DESCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //System.out.println("Insertion Sort - Ascending - Count");
 //is.sort(dArr, dArr[0].compare_by_count(SortOrder.ASCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 //System.out.println("Insertion Sort - Descending - Count");
 //is.sort(dArr, dArr[0].compare_by_count(SortOrder.DESCENDING));
 //for(DataCount<String> dc : dArr){
 //  System.out.println(dc);
 //}
 //System.out.println("============================");
 } // END MAIN METHOD
*/