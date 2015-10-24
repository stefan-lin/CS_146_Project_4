import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class QuickSort<E> implements SortInterface<E>{
  //variables
  //private E [] tmp_Arr = null;
  Comparison_Handler<E> cmph = null;
  InsertionSort<E> _insertion_sort = null;

  public QuickSort(){
    _insertion_sort = new InsertionSort<>();
  }

  /**
   * _quicksort METHOD
   *
   * QUICK SORT HELPER METHOD. THIS METHOD WOULD FIRST CHECK IF THE ARRAY SIZE
   * IS LESS THAN OR EQUALS TO FOUR. IF IT IS, THEN USE INSERTION SORT TO
   * PROVIDE EXTRA PERFORMANCE. IF NOT, SPLIT ARRAY INTO TWO PARTITION AND
   * RECURSIVELY CALL _quicksort AGAIN.
   *
   * @param tmp_Arr         INPUT ARRAY THAT WOULD BE SORTED
   * @param lowerThanPivot  BEGINNING INDEX
   * @param higherThanPivot ENDING INDEX
   */
  @SuppressWarnings("hiding")
  public void _quicksort(E[] tmp_Arr, int lowerThanPivot, int higherThanPivot){
    if(higherThanPivot - lowerThanPivot <= 9){
      _insertion_sort.partial_sort(tmp_Arr, lowerThanPivot,
          higherThanPivot, this.cmph);
      return;
    }
    // DEALING WITH INDEX
    if(higherThanPivot - lowerThanPivot > 1) {   
      int partitionPivot = chop(tmp_Arr, lowerThanPivot, higherThanPivot);

      _quicksort(tmp_Arr, lowerThanPivot, partitionPivot);
      _quicksort(tmp_Arr, partitionPivot + 1, higherThanPivot);
    } // END IF STATEMENT
    else{
      return;
    } // END ELSE STATEMENT
  } // END _quicksort PRIVATE METHOD

  /**
   * _find_median METHOD
   *
   * DETERMINE THE MEDIAN INDEX BASED ON COMPARING THE VALUES HOLDS AT THE
   * LOCATION low, high, median. THE MEDIAN INDEX WILL BE FULFILLED THE
   * FOLLOWING CONDITION:
   *  tmp_Arr[low] < tmp_Arr[median] < tmp_Arr[high]
   *
   * @param tmp_Arr INPUT ARRAY (COULD BE A PARTIAL ARRAY)
   * @param low INDEX OF THE BEGINNING OF THE ARRAY
   * @param high INDEX OF THE END OF THE ARRAY
   * @return MEDIAN INDEX
   */
  private int _find_median(E[] tmp_Arr, int low, int high){
    int median = (low + high)/2;

    if(this.cmph.compare(tmp_Arr[low], tmp_Arr[high]) > 0){
      int temp = low;
      low = high;
      high = temp;
    }
    if(this.cmph.compare(tmp_Arr[low], tmp_Arr[median]) > 0){
      median = low;
    }
    if(this.cmph.compare(tmp_Arr[median], tmp_Arr[high]) > 0){
      median = high;
    }

    //System.out.println("Median index = " + median); /////////////////////////

    return median;
  }

  /**
   * _swap METHOD
   *
   * A HELPER METHOD THAT SWAP ELEMENTS IN THE ARRAY
   *
   * @param tmp_Arr TARGET ARRAY
   * @param idx1    ELEMENT ONE INDEX
   * @param idx2    ELEMENT TWO INDEX
   */
  private void _swap(E[] tmp_Arr, int idx1, int idx2){
    E tmp = tmp_Arr[idx1];
    tmp_Arr[idx1] = tmp_Arr[idx2];
    tmp_Arr[idx2] = tmp;
  }

  /**
   * chop METHOD
   *
   * THE METHOD THAT ACTUALLY SORTS THE ARRAY. FIRST FIND THE PIVOT AND THEN
   * SWAP THE PIVOT ELEMENT TO THE BEGINNING OF ARRAY AND THEN, MOVE THE
   * ELEMENTS WHICH IS LESS THAN PIVOT TO THE LEFT AND THE ELEMENTS WHICH IS
   * GREATER THAN PIVOT TO THE RIGHT. SWAP THE PIVOT WITH THE GREATEST ELEMENT
   * THAT NEAR THE PIVOT AND RETURN THE PIVOT INDEX AT THE END.
   *
   * @param tmp_Arr         TARGET ARRAY
   * @param lowerThanPivot  THE BEGINNING INDEX OF ARRAY
   * @param higherThanPivot THE ENDING INDEX OF ARRAY
   * @return PIVOT INDEX
   */
  public int chop(E[] tmp_Arr, int lowerThanPivot, int higherThanPivot){
    int pivot = _find_median(tmp_Arr, lowerThanPivot, higherThanPivot);
    int toLeftofPivot = lowerThanPivot + 1;
    int toRightofPivot = higherThanPivot;

    // FIRST SWAP THE PIVOT TO THE BEGINNING OF THE ARRAY
    _swap(tmp_Arr, pivot, lowerThanPivot);

    //Identify number from left side > the pivot value && a number from right
    // side < pivot value. Then swap.
    while (toLeftofPivot < toRightofPivot) {
      if (cmph.compare(tmp_Arr[toRightofPivot], tmp_Arr[lowerThanPivot]) >= 0) {
        toRightofPivot--;
      }
      else if (cmph.compare(tmp_Arr[toLeftofPivot],tmp_Arr[lowerThanPivot]) < 0) {
        //move to the right if the pivot is
        toLeftofPivot++;
      }
      else {
        _swap(tmp_Arr, toLeftofPivot, toRightofPivot);
      }
    }
    _swap(tmp_Arr, toLeftofPivot, lowerThanPivot);
	  return toLeftofPivot;
  }

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
  public void sort(E[] arr, Comparison_Handler<E> cmph) {
    if (arr == null || arr.length == 0) {
      return;
    }
    else {
      this.cmph = cmph;

      // CAN HAVE A TRY-CATCH BLOCK HERE
      this._quicksort(arr, 0, arr.length - 1);

      this.cmph = null;
    } // END IF-ELSE STATEMENT
  } // END sort METHOD

  /**
   * Public toString Method
   *
   * Override the toString method.
   *
   * @return A string represent Quick Sort
   */
  @Override
  public String toString(){
    return "Quick Sort";
  }
} // END QuickSort CLASS
