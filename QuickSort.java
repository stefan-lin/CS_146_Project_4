import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class QuickSort<E> implements SortInterface<E>{
  //variables
  //private E [] tmp_Arr = null;
  Comparison_Handler<E> cmph = null;
    
  @SuppressWarnings("hiding")
  public void _quicksort(E[] tmp_Arr, int lowerThanPivot, int higherThanPivot){
    if(higherThanPivot - lowerThanPivot <= 9){
      new InsertionSort<E>().sort(tmp_Arr, this.cmph);
      return;
    }
    // DEALING WITH INDEX
    if(higherThanPivot - lowerThanPivot > 1) {   
      int partitionPivot = chop(tmp_Arr, lowerThanPivot, higherThanPivot);

      _quicksort(tmp_Arr, lowerThanPivot, partitionPivot);
    	//quickSort(tmp_Arr, cmph, partitionPivot - 1, higherThanPivot);
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
   *
   * @param tmp_Arr
   * @param idx1
   * @param idx2
   */
  private void _swap(E[] tmp_Arr, int idx1, int idx2){
    E tmp = tmp_Arr[idx1];
    tmp_Arr[idx1] = tmp_Arr[idx2];
    tmp_Arr[idx2] = tmp;
  }

  /**
  * Method to partition
  * @return new pivot
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
  *Method to swap index when comparing
  *
  private void swap(int pivot, int low) {
    E temp = array[low];
    array[low] = wherePivot;
	array[pivot] = temp;  
    }
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
