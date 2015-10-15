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

  public Comparison_Handler<DataCount<T>> compare_by_data(SortOrder order){
    /**
     * flag = false -> descending
     * flag = true  -> ascending
     */
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = t1.data.compareTo(t2.data);

        return (order == SortOrder.DESCENDING)? cmpResult: cmpResult * -1;
      } // END compare METHOD
    };  // END return STATEMENT
  } // END compare_by_data

  public Comparison_Handler<DataCount<T>> compare_by_count(SortOrder order){
    /**
     * flag = false -> descending
     * flag = true  -> ascending
     */
    return new Comparison_Handler<DataCount<T>>() {
      @Override
      public int compare(DataCount<T> t1, DataCount<T> t2) {
        int cmpResult = ((Integer)t1.count).compareTo((Integer) t2.count);

        return (order == SortOrder.DESCENDING)? cmpResult: cmpResult * -1;
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
    is.sort(arr, arr.get(0).compare_by_count(SortOrder.DESCENDING));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_count(SortOrder.ASCENDING));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_data(SortOrder.DESCENDING));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");
    is.sort(arr, arr.get(0).compare_by_data(SortOrder.ASCENDING));
    for(DataCount<String> dc : arr){
      System.out.println(dc);
    }
    System.out.println("============================");

    System.out.println("======== MERGE SORT ==========");
    DataCount<String> [] dArr = new DataCount[10];
    dArr[0] = new DataCount<>("Z", 5);
    dArr[1] = new DataCount<>("A", 19);
    dArr[2] = new DataCount<>("F", 4);
    dArr[3] = new DataCount<>("G", 8);
    dArr[4] = new DataCount<>("D", 7);
    dArr[5] = new DataCount<>("E", 11);
    dArr[6] = new DataCount<>("R", 3);
    dArr[7] = new DataCount<>("T", 1);
    dArr[8] = new DataCount<>("I", 6);
    dArr[9] = new DataCount<>("M", 9);
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");

    MergeSort<DataCount<String>> ms = new MergeSort<>();
    ms.sort(dArr, dArr[0].compare_by_data(SortOrder.ASCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    ms.sort(dArr, dArr[0].compare_by_data(SortOrder.DESCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    ms.sort(dArr, dArr[0].compare_by_count(SortOrder.ASCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");
    ms.sort(dArr, dArr[0].compare_by_count(SortOrder.DESCENDING));
    for(DataCount<String> dc : dArr){
      System.out.println(dc);
    }
    System.out.println("============================");

  } // END MAIN METHOD
}

/**
 OUTPUT
 "C:\Program Files\Java\jdk1.8.0_60\bin\java" -Didea.launcher.port=7534 "-Didea.launcher.bin.path=C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 14.1.4\bin" -Dfile.encoding=windows-1252 -classpath "C:\Program Files\Java\jdk1.8.0_60\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\rt.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_60\jre\lib\ext\zipfs.jar;C:\Users\Lambda\IdeaProjects\Project_3_New\out\production\Project_3_New;C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 14.1.4\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain DataCount
 [Z] - 4
 [H] - 3
 [B] - 5
 [T] - 1
 [K] - 2
 ============================
 [B] - 5
 [Z] - 4
 [H] - 3
 [K] - 2
 [T] - 1
 ============================
 [T] - 1
 [K] - 2
 [H] - 3
 [Z] - 4
 [B] - 5
 ============================
 [Z] - 4
 [T] - 1
 [K] - 2
 [H] - 3
 [B] - 5
 ============================
 [B] - 5
 [H] - 3
 [K] - 2
 [T] - 1
 [Z] - 4
 ============================
 ======== MERGE SORT ==========
 [Z] - 5
 [A] - 19
 [F] - 4
 [G] - 8
 [D] - 7
 [E] - 11
 [R] - 3
 [T] - 1
 [I] - 6
 [M] - 9
 ============================
 [A] - 19
 [D] - 7
 [E] - 11
 [F] - 4
 [G] - 8
 [I] - 6
 [M] - 9
 [R] - 3
 [T] - 1
 [Z] - 5
 ============================
 [Z] - 5
 [T] - 1
 [R] - 3
 [M] - 9
 [I] - 6
 [G] - 8
 [F] - 4
 [E] - 11
 [D] - 7
 [A] - 19
 ============================
 [T] - 1
 [R] - 3
 [F] - 4
 [Z] - 5
 [I] - 6
 [D] - 7
 [G] - 8
 [M] - 9
 [E] - 11
 [A] - 19
 ============================
 [A] - 19
 [E] - 11
 [M] - 9
 [G] - 8
 [D] - 7
 [I] - 6
 [Z] - 5
 [F] - 4
 [R] - 3
 [T] - 1
 ============================

 Process finished with exit code 0


 */
