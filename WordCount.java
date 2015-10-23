import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.net.URL;

/**
 * An executable that counts the words in a files and prints out the counts in
 * descending order. You will need to modify this file.
 */
public class WordCount {
  private String            _OUTPUT_FILE    = "WordCount_Output.txt";
  private int               _length         = 0;
  private long              _insert_time    = 0;
  private long              _extract_time   = 0;
  DataCount<String> []      _original_arr   = null;
  FileWriter                _output_handler = null;

  /**
   * WordCount CONSTRUCTOR
   *
   * INITIALIZE THE FileWriter OBJECT FOR LATER USE
   */
  public WordCount(){
    // PREPARE FOR OUTPUT HANDLER
    try {
      File output_file = new File(this._OUTPUT_FILE);
      if (!output_file.exists()) {
        output_file.createNewFile();
      } // END IF STATEMENT
      _output_handler = new FileWriter(output_file, true);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * _parse_cmd_args METHOD
   *
   * THIS METHOD IS THE MAJOR METHOD IN THIS PROJECT. IT IS RESPONSIBLE TO:
   *  (1) CALLING CommandLine_Args CLASS TO PARSE COMMAND LINE ARGUMENTS
   *  (2) READING INPUT FILE BY CALLING THE FileWordReader CLASS OBJECT
   *  (3) PROCESSING THE DATA BY USER REQUESTING DATA STRUCTURE
   *  (4) SORT THE OUTPUT ARRAY BY REQUESTING SORT ALGORITHM
   *  (5) PRINT OUTPUT TO STANDARD OUTPUT
   *  (6) CALL HELPER METHOD TO MEASURE THE EFFICIENCY OF SORTING ALGORITHM
   *
   * @param args COMMAND LINE ARGUMENTS
   * @throws IOException
   */
  private void _parse_cmd_args(String [] args) throws IOException {
    DataCounter<String> dataStructure = null;
    try {
      CommandLine_Args.Executing_Commands ec =
          new CommandLine_Args().parse_cmd_args(args);

      dataStructure = ec.getDataStructure();
      FileWordReader reader = new FileWordReader(
          ec.getInputFile().getAbsolutePath());
      String word = reader.nextWord();
      while (word != null) {
        dataStructure.incCount(word);
        word = reader.nextWord();
      } // END WHILE LOOP
      DataCount<String>[] counts = dataStructure.getCounts();

      // DEEP COPY ARRAY FOR LATER USAGE(EFFICIENCY)
      _original_arr = Arrays.copyOf(counts, counts.length);

      ec.getSortObject().sort(
          counts, counts[0].compare_by_count(SortOrder.DESCENDING)
      );
      // OUTPUT
      for(DataCount dc : counts){
        if(ec.getOutputOption()) {
          System.out.println(dc);
        }
        else{
          if(dc.getCount() == 1){
            System.out.println(dc);
          }
        }
      } // END FOR LOOP

      // SHOW EFFICIENCY
      this.measure_sort_efficiency(args[3]);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.err.println("Error processing " + e);
      System.exit(1);
    }
  }

  /**
   * measure_sort_efficiency METHOD
   *
   * DEEP COPY THE ORIGINAL ARRAY AND USING DIFFERENT SORTING ALGORITHMS TO
   * SORT THE ARRAYS AND MEASURE THE ELAPSED TIME. CALL HELPER METHOD TO PRINT
   * OUT THE REPORT
   *
   * @param f_name INPUT FILE NAME (STRING)
   * @throws IOException
   */
  private void measure_sort_efficiency(String f_name) throws IOException {
    DataCount<String> [] mani_arr = Arrays.copyOf(
        _original_arr, _original_arr.length);
    DataCount<String> [] mani_arr1 = Arrays.copyOf(
        _original_arr, _original_arr.length);
    DataCount<String> [] mani_arr2 = Arrays.copyOf(
        _original_arr, _original_arr.length);
    InsertionSort<DataCount<String>> is = new InsertionSort<>();
    QuickSort<DataCount<String>> qs = new QuickSort<>();
    MergeSort<DataCount<String>> ms = new MergeSort<>();

    long startTime, totalTime;
    startTime = System.nanoTime();
    is.sort(mani_arr, mani_arr[0].compare_by_count(SortOrder.DESCENDING));
    totalTime = System.nanoTime() - startTime;
    print_efficiency_report(totalTime, is.toString(), f_name, mani_arr.length);

    //mani_arr = Arrays.copyOf(_original_arr, _original_arr.length);

    startTime = System.nanoTime();
    qs.sort(mani_arr1, mani_arr1[0].compare_by_count(SortOrder.DESCENDING));
    totalTime = System.nanoTime() - startTime;
    print_efficiency_report(totalTime, qs.toString(), f_name, mani_arr.length);

    //mani_arr = Arrays.copyOf(_original_arr, _original_arr.length);

    startTime = System.nanoTime();
    ms.sort(mani_arr2, mani_arr2[0].compare_by_count(SortOrder.DESCENDING));
    totalTime = System.nanoTime() - startTime;
    print_efficiency_report(totalTime, ms.toString(), f_name, mani_arr.length);

    this.close_output_file();
  }

  /**
   * print_efficiency_report METHOD
   *
   * THE HELPER METHOD TO PRINT OUT REPORT TO A TEXT FILE.
   *
   * @param t           ELAPSED TIME
   * @param algorithm   THE NAME OF SORTING ALGORITHM
   * @param f_name      THE NAME OF THE INPUT FILE
   * @param ttl_srt_num TOTAL NUMBER OF ENTRIES
   * @throws IOException
   */
  private void print_efficiency_report(long t, String algorithm, String f_name,
                                       int ttl_srt_num) throws IOException {
    _output_handler.write("[       File Name        ] : " + f_name + "\n");
    _output_handler.write("[       Algorithm        ] : " + algorithm + "\n");
    _output_handler.write("[Total Number of Entries ] : " + ttl_srt_num + "\n");
    _output_handler.write("[      Elapsed Time      ] : " + t + " ns\n");
    _output_handler.write("================================================\n");
  }

  /**
   * close_output_file METHOD
   *
   * HELPER METHOD TO CLOSE THE OUTPUT FILE HANDLER
   *
   * @throws IOException
   */
  private void close_output_file() throws IOException {
    _output_handler.flush();
    _output_handler.close();
  }

  /**
   * MAIN METHOD
   *
   * @param args COMMAND LINE ARGUMENTS
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    WordCount wc = new WordCount();
    wc._parse_cmd_args(args);
  }
}
