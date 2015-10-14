import java.util.Collections;
import java.util.List;

/**
 * Created by Lambda on 10/9/2015.
 */
public class InsertionSort<T> implements SortInterface<T> {
  @Override
  public void sort(List<T> list, Comparison_Handler<T> cmph) {
    for(int index = 1; index < list.size(); index++){
      int tmp_idx = index;
      int rslt = cmph.compare(list.get(tmp_idx), list.get(tmp_idx - 1));
      while(tmp_idx > 0 && rslt > 0){
        Collections.swap(list, tmp_idx, tmp_idx-1);
        tmp_idx--;
        if(tmp_idx != 0) {
          rslt = cmph.compare(list.get(tmp_idx), list.get(tmp_idx - 1));
        }
      } // END WHILE LOOP
    } // END FOR LOOP
  } // END sort METHOD
}
