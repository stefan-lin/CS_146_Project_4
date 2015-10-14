import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Lambda on 10/9/2015.
 */
public class DataCount<T extends Comparable<T>>{
  private T   data  = null;
  private int count = 0;

  public DataCount(T data_in, int count_in){
    this.data  = data_in;
    this.count = count_in;
  } // END CONSTRUCTOR

  public T getData(){
    return this.data;
  }

  public int getCount(){
    return this.count;
  }

  public void copy(DataCount<T> new_obj){
    this.data = new_obj.data;
    this.count = new_obj.count;
  }

  @Override
  public String toString(){
    return "[" + data + "] - " + count;
  }

  public Comparison_Handler<DataCount<T>> compare_by_data(boolean flag){
    /**
     * flag = false -> descending
     * flag = true  -> ascending
     */
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = t1.data.compareTo(t2.data);

        return (flag)? cmpResult: cmpResult * -1;
      } // END compare METHOD
    };  // END return STATEMENT
  } // END compare_by_data

  public Comparison_Handler<DataCount<T>> compare_by_count(boolean flag){
    /**
     * flag = false -> descending
     * flag = true  -> ascending
     */
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = ((Integer)t1.count).compareTo((Integer) t2.count);

        return (flag)? cmpResult: cmpResult * -1;
      } // END compare METHOD
    }; // END return STATEMENT
  } // END compare_by_count METHOD

  public static void dummySort(DataCount<String> dc[],
                               Comparison_Handler<DataCount<String>> cmph){
    for(int i=1; i < dc.length; i++){
      DataCount<String> temp = dc[i];
      int j;
      for(j = i-1; j >= 0; j--){
        int rslt = cmph.compare(temp, dc[j]);
        if(rslt <= 0){
          break;
        } // END IF STATEMENT
        dc[j + 1] = dc[j];
      } // END FOR LOOP
      dc[j + 1] = temp;
    } // END FOR LOOP
  } // END dummysort METHOD

  /////////////////// TESTING /////////////////////////////////////////////////
  public static void main(String[] args) {
    InsertionSort<DataCount<String>> is = new InsertionSort<>();
    ArrayList<DataCount<String>> arr = new ArrayList<>();
    arr.add(new DataCount<>("Z", 4));
    arr.add(new DataCount<>("H", 3));
    arr.add(new DataCount<>("B", 5));
    arr.add(new DataCount<>("T", 1));
    arr.add(new DataCount<>("K", 2));

    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_count(false));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_count(true));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_data(false));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_data(true));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");

  } // END MAIN METHOD
}

/**
 OUTPUT
 "C:\Program Files\Java\jdk1.8.0_60\bin\java" -Didea.launcher.port=7535 "-Didea.launcher.bin.path=C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 14.1.4\bin" -Dfile.encoding=windows-1252 -classpath "C:\Program Files\Java\jdk1.8.0_60\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\rt.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\zipfs.jar;C:\Users\Lambda\IdeaProjects\Project_3_New\out\production\Project_3_New;C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 14.1.4\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain DataCount
 [Z] - 4
 [H] - 3
 [B] - 5
 [T] - 1
 [K] - 2
 ============================
 [T] - 1
 [K] - 2
 [H] - 3
 [Z] - 4
 [B] - 5
 ============================
 [B] - 5
 [Z] - 4
 [H] - 3
 [K] - 2
 [T] - 1
 ============================
 [B] - 5
 [H] - 3
 [K] - 2
 [T] - 1
 [Z] - 4
 ============================
 [Z] - 4
 [T] - 1
 [K] - 2
 [H] - 3
 [B] - 5
 ============================

 Process finished with exit code 0

 */
