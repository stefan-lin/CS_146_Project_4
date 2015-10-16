import java.util.List;

/**
 * Created by Lambda on 10/9/2015.
 */
public interface SortInterface<E> {
  //public void sort(List<E> list, Comparison_Handler<E> cmph);
  public void sort(E[] arr, Comparison_Handler<E> cmph);
}
