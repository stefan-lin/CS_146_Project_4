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
