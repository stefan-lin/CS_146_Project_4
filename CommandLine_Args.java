import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lambda on 10/16/2015.
 */
public class CommandLine_Args {
  static final String OPTIONS_ALG[] = {"-b", "-a", "-h"};
  static final String OPTIONS_OPT[] = {"-frequency", "-num_unique"};
  static final String OPTIONS_SRT[] = {"-is", "-qs", "-ms"};

  /**
   * Executing_Commands CLASS
   *
   * THIS CLASS IS VERY IMPORTANT TO THE WHOLE SYSTEM. IT WOULD TAKE THE INPUT
   * FROM COMMAND LINE ARGUMENTS AND ACCORDING TO THE INPUT IT WOULD GENERATE
   * THE REQUESTING OBJECTS(SortObject, DataStructure, ... etc). THIS PROVIDES
   * THE CONVENIENCE FOR THE OTHER PARTS OF THE SYSTEM.
   */
  public class Executing_Commands{
    private Data_Structure data_structure;
    private Option         option;
    private Sort_Algorithm sort_algorithm;
    private String         file;

    /**
     * Executing_Commands CONSTRUCTOR
     *
     * CONSTRUCTING THE Executing_Commands OBJECT
     *
     * @param ds   INDICATES THE REQUESTING DATA STRUCTURE
     * @param opt  INDICATES THE REQUESTING OPTION
     * @param sa   INDICATES THE REQUESTING SORTING ALGORITHM
     * @param file INDICATES THE INPUT FILE
     */
    public Executing_Commands(
            Data_Structure ds, Option opt,
            Sort_Algorithm sa, String file){
      data_structure = ds;
      option         = opt;
      sort_algorithm = sa;
      this.file      = file;
    }

    /**
     * getInputFile METHOD
     *
     * GETTER METHOD
     *
     * @return FILE OBJECT
     */
    public File getInputFile(){
      return new File(this.file);
    }

    /**
     * getOutputOption METHOD
     *
     * SIMPLE GETTER METHOD
     *
     * @return BOOLEAN VALUE: TRUE-> SHOW FREQUENCY; FALSE-> SHOW UNIQUE
     */
    public boolean getOutputOption(){
      return (option == Option.FREQUENCY)? true: false;
    }

    /**
     * getSortObject METHOD
     *
     * GENERATING THE REQUESTING SORTING OBJECT
     *
     * @return SortInterface INSTANCE
     */
    public SortInterface<DataCount<String>> getSortObject(){
      switch(sort_algorithm){
        case INSERTION_SORT:
          return new InsertionSort<DataCount<String>>();
        case QUICK_SORT:
          return new QuickSort<DataCount<String>>();
        case MERGE_SORT:
          return new MergeSort<DataCount<String>>();
        default:
          return null;
      } // END SWITCH STATEMENT
    } // END getSortObject METHOD

    /**
     * getDataStructure METHOD
     *
     * GENERATE THE REQUESTING DATA STRUCTURE OBJECT
     *
     * @return DataCounter OBJECT
     */
    public DataCounter<String> getDataStructure(){
      switch(data_structure){
        case AVL:
          return new AVL<String>();
        case BST:
          return new BST<String>();
        case HASH_TABLE:
          return new HashTable<String>();
        default:
          return null;
      } // END SWITCH STATEMENT
    } // END getDataStructure METHOD
  } // END Executing_Command CLASS

  /**
   * showUsage METHOD
   *
   * SHOWING THE INVALID USAGE MESSAGE
   */
  static public void showUsage(){
    System.err.println(
            "Usage: java WordCount [ -b | -a | -h ]" +
                    " [-is | -qs| -ms]" +
                    " [-frequency | -num_unique]" +
                    " <filename>");
    System.err.println("-b Use an Unbalanced BST in the backend ");
    System.err.println("-a Use an AVL Tree in the backend");
    System.err.println("-h Use a Hashtable in the backend");
    System.err.println("-is Use Insertion Sort");
    System.err.println("-qs Use Quick Sort");
    System.err.println("-ms Use Merge Sort");
    System.err.println("-frequency Show word frequency in descending order");
    System.err.println("-num_unique Show only the unique words");
    System.err.println("filename Input file name");
  } // END showUsage METHOD

  /**
   * parse_DataStructure_option METHOD
   *
   * HELPER METHOD TO GENERATE THE REQUESTING Data_Structure OPTION
   *
   * @param str INPUT STRING
   * @return Data_Structure OPTION
   */
  static private Data_Structure parse_DataStructure_option(String str){
    int index;
    for(index = 0; index < OPTIONS_ALG.length; index++){
      if(OPTIONS_ALG[index].compareTo(str) == 0){
        break;
      }
    } // END FOR LOOP
    switch (index){
      case 0:
        return Data_Structure.BST;
      case 1:
        return Data_Structure.AVL;
      case 2:
        return Data_Structure.HASH_TABLE;
      default:
        System.out.println("Invalid input: using Hash Table as default.");
        return Data_Structure.HASH_TABLE;
    } // END SWITCH STATEMENT
  }

  /**
   * parse_SortAlgorithm_option METHOD
   *
   * HELPER METHOD TO GENERATE THE REQUESTING Sort_Algorithm OPTION
   *
   * @param str INPUT STRING
   * @return Sort_Algorithm OPTION
   */
  static private Sort_Algorithm parse_SortAlgorithm_option(String str){
    int index;
    for(index = 0; index < OPTIONS_SRT.length; index++){
      if(OPTIONS_SRT[index].compareTo(str) == 0){
        break;
      }
    } // END FOR LOOP
    switch (index){
      case 0:
        return Sort_Algorithm.INSERTION_SORT;
      case 1:
        return Sort_Algorithm.QUICK_SORT;
      case 2:
        return Sort_Algorithm.MERGE_SORT;
      default:
        System.out.println("Invalid input: using Quick Sort as default.");
        return Sort_Algorithm.QUICK_SORT;
    } // END SWITCH STATEMENT
  }

  /**
   * parse_Option_option METHOD
   *
   * GENERATING THE REQUESTING Option
   *
   * @param str INPUT STRING
   * @return  Option
   */
  static private Option parse_Option_option(String str){
    if(str.compareTo(OPTIONS_OPT[0]) == 0){
      return Option.FREQUENCY;
    }
    else if(str.compareTo(OPTIONS_OPT[1]) == 0) {
      return Option.UNIQUE;
    }
    else{
      System.out.println("Invalid input: using Frequency option as default.");
      return Option.FREQUENCY;
    }
  }


  /**
   * parse_cmd_arges METHOD
   *
   * THIS METHOD WOULD GENERATE THE Executing_Commands OBJECT BASED ON THE
   * INPUT ARGUMENTS
   *
   * @param args INPUT ARGUMENT ARRAY
   * @return     Executing_Commands OBJECT
   * @throws Exception
   */
  public Executing_Commands parse_cmd_args(String[] args) throws Exception{
    // VALIDATE THE NUMBER OF THE ARGUMENTS ///////////////////////////////////
    if(args.length != 4){
      throw new Exception("Insufficient Command Line Arguments.\n");
    } // END IF STATEMENT

    return new Executing_Commands(
        parse_DataStructure_option(args[0]),
        parse_Option_option(args[2]),
        parse_SortAlgorithm_option(args[1]),
        args[3]
    );
  }
}