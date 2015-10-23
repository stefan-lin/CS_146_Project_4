import java.util.Collections;
import java.util.List;

/**
 * Created by Lambda on 10/9/2015.
 */
public class InsertionSort<T> implements SortInterface<T> {
  //@Override
  //public void sort(List<T> list, Comparison_Handler<T> cmph) {
  //  for(int index = 1; index < list.size(); index++){
  //    int tmp_idx = index;
  //    while(tmp_idx > 0 &&
  //        cmph.compare(list.get(tmp_idx), list.get(tmp_idx - 1)) < 0){
  //      Collections.swap(list, tmp_idx, tmp_idx-1);
  //      tmp_idx--;
  //    } // END WHILE LOOP
  //  } // END FOR LOOP
  //} // END sort METHOD

  /**
   * sort Public method (Override based on the interface SortInterface)
   * This public method works as the API for user. Also, as soon as, this method
   * is called, it will starts to execute the preparation for the following
   * merge sort process.
   *  - Dynamically allocate a array which has the same size of the input one.
   *  - Assign Comparison_Handler instance to its own private field for later
   *    usage. In this way, we could eliminate the number of parameters needed
   *    for sorting methods.
   *  - After the sorting process is done. De-allocated the memory.
   *
   * @param arr  Target array that would be sorted
   * @param cmph Comparison_Handler instance that would provide compare method
   */
  @Override
  public void sort(T[] arr, Comparison_Handler<T> cmph) {
    for(int index = 1; index < arr.length; index++){
      int tmp_idx = index;
      while(tmp_idx > 0 && cmph.compare(arr[tmp_idx], arr[tmp_idx - 1]) < 0){
        T tmp_item = arr[tmp_idx];
        arr[tmp_idx] = arr[tmp_idx - 1];
        arr[tmp_idx - 1] = tmp_item;
        tmp_idx--;
      } // END WHILE LOOP
    } // END FOR LOOP
  }

  /**
   * Public toString Method
   *
   * Override the toString method.
   *
   * @return A string represent Insertion Sort
   */
  @Override
  public String toString(){
    return "Insertion Sort";
  }
}
