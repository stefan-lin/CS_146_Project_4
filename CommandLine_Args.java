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

  public class Executing_Commands{
    private Data_Structure data_structure;
    private Option         option;
    private Sort_Algorithm sort_algorithm;
    private String         file;

    Executing_Commands(
            Data_Structure ds, Option opt,
            Sort_Algorithm sa, String file){
      data_structure = ds;
      option         = opt;
      sort_algorithm = sa;
      this.file      = file;
    }

    public File getInputFile(){
      return new File(this.file);
    }

    public boolean getOutputOption(){
      return (option == Option.FREQUENCY)? true: false;
    }

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

  /////////////////////// TESTING /////////////////////////////////////////
  public static void main(String[] args) {
    String arr [] = new String [4];
    arr[0] = "-a";
    arr[1] = "-is";
    arr[2] = "-frequency";
    arr[3] = "hamlet.txt";
    int num_of_entry = 0;
    try {
      Executing_Commands ec = new CommandLine_Args().parse_cmd_args(arr);
      //System.out.println(ec.getDataStructure().toString());
      //System.out.println(ec.getSortObject().toString());
      //System.out.println(ec.getOutputOption());
      //System.out.println(ec.getInputFile());

      DataCounter<String> dataStructure = ec.getDataStructure();
      FileWordReader reader = new FileWordReader(
          ec.getInputFile().getAbsolutePath());
      String word = reader.nextWord();
      while (word != null) {
        dataStructure.incCount(word);
        word = reader.nextWord();
        num_of_entry++;
      } // END WHILE LOOP
      DataCount<String>[] counts = dataStructure.getCounts();
      ec.getSortObject().sort(
          counts, counts[0].compare_by_count(SortOrder.ASCENDING)
      );
      for(DataCount dc : counts){
        System.out.println(dc);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      System.err.println("Error processing " + e);
      System.exit(1);
    }
  }
}

/**
public enum ExecuteFile{

}
 */