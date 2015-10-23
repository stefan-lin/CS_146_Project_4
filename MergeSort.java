import java.lang.reflect.Array;
import java.util.List;

/**
 * MergeSort Class
 *
 * This class provides the sorting ability to any lists( including the
 * sub-classes which inherited from List class) and arrays. It would perform
 * sorting based on merge algorithm.
 *
 * NOTE:
 * This implementation will directly manipulate the original containers. It will
 * not produce a new one.
 */
public class MergeSort<E> implements SortInterface<E> {
  /**
   * Private fields
   */
  private E [] tmp_arr = null;
  Comparison_Handler<E> cmph = null;

  /**
   * _sort Private method
   * Private sorting helper method. It would be called by the API method sort.
   * This method will be in charge of splitting the array and merging the array.
   *
   * NOTE:
   * We treat the scenario that the partial array has exact two elements as a
   * special case. We compare the value right away and swap the elements if
   * it is necessary. By doing this, we will be able to save an extra recursion
   * method call. Comparing the amount of resource of calling a method and a
   * simple if-condition compare, the if-condition is a lot smaller due to the
   * memory allocation and copy process.
   *
   * @param arr The input array which will eventually be sorted
   * @param idx_fnt The beginning index of arr
   * @param idx_bck The ending index of arr
   */
  private void _sort(E[] arr, int idx_fnt, int idx_bck){
    // SPECIAL CASE
    if(idx_bck - idx_fnt == 1){
      /**
       * Only two elements
       */
      if(cmph.compare(arr[idx_fnt], arr[idx_bck]) > 0){
        // SWAP
        E tmp_item = arr[idx_fnt];
        arr[idx_fnt] = arr[idx_bck];
        arr[idx_bck] = tmp_item;
      } // END IF STTEMENT
      return;
    } // END IF STATEMENT - SPECIAL CASE
    /**
     * Calculating the midpoint of the current array. Then splitting the array
     * into two small ones and merging them back together as long as the arr
     * does not contain only one element.
     */
    if(idx_fnt < idx_bck) {
      int idx_mid = (idx_fnt + idx_bck) / 2;
      _sort(arr, idx_fnt, idx_mid);
      _sort(arr, idx_mid + 1, idx_bck);
      _merge(arr, idx_fnt, idx_mid, idx_bck);
    }
  }

  /**
   * _merge Private method
   * This method will first make a copy of the current array and using the
   * indexes received and compare elements in different portion of the array.
   * Then it will store either smaller/greater value back into the original
   * array.
   *
   * @param arr The input array
   * @param idx_fnt The index of the beginning position
   * @param idx_mid The index of the midpoint
   * @param idx_bck The index of the ending position
   */
  private void _merge(E[] arr, int idx_fnt, int idx_mid, int idx_bck){
    // FIRST MAKE A DEEP COPY OF INPUT ARRAY TO TEMP ARRAY
    for(int i=idx_fnt; i<=idx_bck; i++){
      this.tmp_arr[i] = arr[i];
    } // END FOR LOOP

    int itr_1 = idx_fnt;        // INDEX OF FIRST ELEMENT IN LEFT PORTION
    int itr_2 = idx_mid + 1;    // INDEX OF SECOND ELEMENT IN RIGHT PORTION
    int idx = idx_fnt;
    while(itr_1 <= idx_mid && itr_2 <= idx_bck){
      int rslt = this.cmph.compare(this.tmp_arr[itr_1], this.tmp_arr[itr_2]);
      if(rslt > 0){
        arr[idx++] = this.tmp_arr[itr_2++];
      }
      else{
        arr[idx++] = this.tmp_arr[itr_1++];
      }
    } // END WHILE LOOP
    while(itr_1 <= idx_mid){
      arr[idx++] = this.tmp_arr[itr_1++];
    } //  END WHILE LOOP
  } // END _merge METHOD

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
   * @param arr
   * @param cmph
   */
  @Override
  public void sort(E[] arr, Comparison_Handler<E> cmph) {
    //@SuppressWarnings("unchecked");
    tmp_arr = (E[]) new Object[arr.length];
    this.cmph = cmph;
    _sort(arr, 0, arr.length-1);
    tmp_arr = null;
    this.cmph = null;
  }

  /**
   * Public toString Method
   *
   * Override the toString method.
   *
   * @return A string represent Merge Sort
   */
  @Override
  public String toString(){
    return "Merge Sort";
  }
}
