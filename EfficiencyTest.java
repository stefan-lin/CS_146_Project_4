import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Lambda on 10/23/2015.
 */
public class EfficiencyTest {
  // SAME LEVEL AS src DIRECTORY
  final private String     _INPUT_DIR   = ".\\input";
  final private String     _OUTPUT_DIR  = ".\\output";
  final private String     _OUTPUT_FILE = "Efficiency_Report.txt";
  final private double     _RATE        = Math.pow(10, 6);

  private InsertionSort<DataCount<String>>    _insertionSort = null;
  private QuickSort<DataCount<String>>        _quickSort     = null;
  private MergeSort<DataCount<String>>        _mergeSort     = null;
  private File[]                              _input_files   = null;
  private FileWriter                          _output_msgr   = null;
  private LinkedList<DataCount<String> []> _files_list    = null;

  public EfficiencyTest(){
    _insertionSort = new InsertionSort<>();
    _quickSort     = new QuickSort<>();
    _mergeSort     = new MergeSort<>();
    _input_files   = new File(_INPUT_DIR).listFiles();
    _files_list    = new LinkedList<>();
  }

  private void _run_output_preparation() throws SecurityException, IOException{
    // SET UP OUTPUT ENVIRONMENT
    File out_dir = new File(_OUTPUT_DIR);
    if(!out_dir.exists()){
      // NOT EXISTS
      out_dir.mkdir();
    } // END IF STATEMENT

    // SET UP OUTPUT HANDLER
    File outFile = new File(this._OUTPUT_DIR+"\\"+this._OUTPUT_FILE);
    if(!outFile.exists()){
      // OUTPUT FILE DOES NOT EXISTS
      outFile.createNewFile();
    }
    this._output_msgr = new FileWriter(outFile, true);
    // WRITE TITLE
    String str = String.format(" TEST : %10s %9s %9s %9s \n",
        "Entries", "InsertSort", "MergeSort", "QuickSort");
    this._output_msgr.write(str);
    this._output_msgr.write(
        "=====================================================\n");
  }

  /**
   * _run_input_preparation METHOD
   *
   * THIS METHOD WILL READ ALL FILES UNDER THE input DIRECTORY INTO AN
   * ARRAYLIST OBJECT FOR LATER USAGE. (SORT ASCENDING BASED ON THE NUMBER
   * OF ENTRIES IN EACH FILE)
   *
   * @throws IOException
   */
  private void _run_input_preparation() throws IOException{
    int counter = 1;
    for(File inputFile : _input_files){
      DataCounter<String> dataStructure = new AVL<>();
      FileWordReader file_reader = new FileWordReader(
          inputFile.getAbsolutePath());
      // READ FILE
      String word = file_reader.nextWord();
      while (word != null) {
        dataStructure.incCount(word);
        word = file_reader.nextWord();
      } // END WHILE LOOP
      _files_list.push(dataStructure.getCounts()); /// PUSH INTO LINKEDLIST
      System.out.println("Input file #" + counter + " read...");
      counter++;
    }
    System.out.println("Input directory had been processed.");
    // SORT LINKEDLIST BY ASCENDING ORDER ACCORDING TO NUMBER OF ENTRIES
    Collections.sort(this._files_list, new Comparator<DataCount<String>[]>() {
      @Override
      public int compare(DataCount<String>[] o1, DataCount<String>[] o2) {
        return o1.length - o2.length;
      }
    });
    System.out.println("Input file had been sorted.");
  }

  public void run_test(){
    try {
      // PREPARATION
      if(this._output_msgr == null) {
        this._run_output_preparation();
      } // END IF STATEMENT
      this._run_input_preparation();

      // RUN TEST SEPARATELY
      this._run_sort_tests();

      this._close_output_file();
    }
    catch (SecurityException se){
      System.out.println("[ERROR] : " + this._OUTPUT_DIR + se);
    }
    catch (IOException ie){
      ie.printStackTrace();
      System.out.println("[ERROR] : " + this._OUTPUT_DIR +
          this._OUTPUT_FILE + ie);
      System.out.println("[ERROR] : " + ie);
    }
  }

  private void _close_output_file() throws IOException{
    this._output_msgr.flush();
    this._output_msgr.close();
  }

  private void _run_sort_tests() throws IOException {
    System.out.println("\n START SORTING TESTS \n");
    int counter = 1;
    double isTime = 0.0, msTime = 0.0, qsTime = 0.0;
    long startTime;
    int size = -1;
    while(!this._files_list.isEmpty()){
      DataCount<String>[] origin = this._files_list.removeFirst();
      size = origin.length;
      // RUN TESTS
      for(int i=0; i<3; i++){
        // FIRST MAKE DEEP COPY OF THE INPUT ARRAY OF DataCount<String> OBJECTS
        DataCount<String>[] arr = Arrays.copyOf(origin, origin.length);
        switch (i){
          case 0:
            startTime = System.nanoTime();
            _insertionSort.sort(arr,
                arr[0].compare_by_count(SortOrder.DESCENDING));
            isTime = (System.nanoTime() - startTime)/_RATE;
            System.out.print("* ");
            break;
          case 1:
            startTime = System.nanoTime();
            _mergeSort.sort(arr,
                arr[0].compare_by_count(SortOrder.DESCENDING));
            msTime = (System.nanoTime() - startTime)/_RATE;
            System.out.print("* ");
            break;
          case 2:
            startTime = System.nanoTime();
            _quickSort.sort(arr,
                arr[0].compare_by_count(SortOrder.DESCENDING));
            qsTime = (System.nanoTime() - startTime)/_RATE;
            System.out.print("* | ");
            break;
          default:
            System.out.println("[ERROR] - SWITCH STATEMENT IN _run_sort_test");
            break;
        } // END SWITCH STATEMENT
      } // END FOR LOOP
      if(counter % 10 == 0){
        System.out.println();
      }

      // TESTS ARE DONE, WRITE OUTPUT TO FILE
      String outString = String.format("%3d. %10d  %10.3f %10.3f %10.3f \n",
         counter, size, isTime, msTime, qsTime);
      this._output_msgr.write(outString);
      this._output_msgr.write(
          "-----------------------------------------------------\n");
      counter++;
    } // END WHILE LOOP
  }

  ////////////////// TEST /////////////////////////////////////////////////////
  public static void main(String[] args) {
    EfficiencyTest et = new EfficiencyTest();
    et.run_test();
  }
}
