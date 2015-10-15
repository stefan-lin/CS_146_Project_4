import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Lambda on 10/14/2015.
 */
public class MergeSort<E> implements SortInterface<E> {
  private E [] tmp_arr = null;
  Comparison_Handler<E> cmph = null;

  private void _sort(List<E> list, int idx_fnt, int idx_bck){

  }

  private void _sort(E[] arr, int idx_fnt, int idx_bck){
    // BASE CASE - 1
    //if(idx_bck == idx_fnt){
    //  /**
    //   * Only one element
    //   */
    //  return;
    //}
    //// BASE CASE - 2
    //if(idx_bck - idx_fnt == 1){
    //  /**
    //   * Only two elements
    //   */
    //  if(cmph.compare(arr[idx_fnt], arr[idx_bck]) < 0){
    //    // SWAP
    //    E tmp_item = arr[idx_fnt];
    //    arr[idx_fnt] = arr[idx_bck];
    //    arr[idx_bck] = tmp_item;
    //  } // END IF STTEMENT
    //  return;
    //} // END IF STATEMENT - BASE CASE - 2
    if(idx_fnt < idx_bck) {
      int idx_mid = (idx_fnt + idx_bck) / 2;
      _sort(arr, idx_fnt, idx_mid);
      _sort(arr, idx_mid + 1, idx_bck);
      _merge(arr, idx_fnt, idx_mid, idx_bck);
    }
  }

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
      if(rslt < 0){
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

  @Override
  public void sort(List<E> list, Comparison_Handler<E> cmph) {

  }

  @Override
  public void sort(E[] arr, Comparison_Handler<E> cmph) {
    //@SuppressWarnings("unchecked");
    tmp_arr = (E[]) new Object[arr.length];
    this.cmph = cmph;
    _sort(arr, 0, arr.length-1);
    tmp_arr = null;
    this.cmph = null;
  }
}
