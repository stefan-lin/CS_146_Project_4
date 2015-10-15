# CS_146_Project_4
Fall 2015

This project is about implementing several sorting algorithms.
  - Merge Sort
  - Insertion Sort
  - Quick Sort

(1) SortOrder enum - provides readability  

(2) Sort<<Interface>> --------- MergeSort<<Class>>
                           |
                           |--- InsertionSort<<Class>>
                           |
                           |--- QuickSort<<Class>>
                       
(3) The DataCount class had implemented with the Comparison_Handler Interface by overriding the compare method. This provides
    the ability to sort by data or counter by ascending order or descending order.

