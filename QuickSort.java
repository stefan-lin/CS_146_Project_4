import java.lang.reflect.Array;
import java.util.List;

public class QuickSort<E> implements SortInterface<E>{
  //variables
  private E [] tmp_Arr = null;
  Comparison_Handler<E> cmph = null;
    
  public <E> void _quicksort(E[] tmp_Arr, <E> cmph, int lowerThanPivot, int higherThanPivot){
    if(higherThanPivot - lowerThanPivot > 1) {   
      int partitionPivot = chop(tmp_Arr, cmph, lowerThanPivot, higherThanPivot);
        quickSort(tmp_Arr, cmph, lowerThanPivot, partitionPivot);
    	//quickSort(tmp_Arr, cmph, partitionPivot - 1, higherThanPivot);
        quickSort(tmp_Arr, cmph, partitionPivot + 1, higherThanPivot);
  }
  
  /**
  * Base case of recursive function quick sort
  *
  */
  public <E> void sort(E[] tmp_Arr) {
    if (tmp_Arr == null || tmp_Arr.length == 0) {
      return;
    }
    this.array = tmp_Arr;
    _quickSort(0, length - 1);
  }
      
  /**
  * Method to partition
  * @return new pivot
  */
  public <E> void chop(E[] tmp_Arr, <E> cmph, int lowerThanPivot, int higherThanPivot){
      int pivot = (lowerThanPivot + higherThanPivot)/2;
      int toLeftofPivot = lowerThanPivot;
      int toRightofPivot = higherThanPivot;
      
      //Identify number from left side > the pivot value && a number from right side < pivot value
      //Then swap.
      while (lowerThanPivot < higherThanPivot) {
		if (comparator.compare(array[toRightofPivot],pivot) > 0) {
				toRightofPivot--;
		}
        //move to the right if the pivot is 
		else if (cmph.compare(array[toLeftofPivot],pivot) <= 0) {
          toLeftofPivot++;
        } 
        else {
          E swap = tmp_Arr[toLeftofPivot];
          tmp_Arr[toLeftofPivot] = tmp_Arr[toRightofPivot];
		  tmp_Arr[right] = swap;
        }
      }
      tmp_Arr[lowerThanPivot] = tmp_Arr[toLeftofPivot];
	  tmp_Arr[toLeftofPivot] = pivot;
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