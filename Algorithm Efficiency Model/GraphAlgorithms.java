//Konrad Jasman, CSC 225
//Summer 2023 

import java.awt.Color;
import java.util.*;

public class GraphAlgorithms{

  /* 
   * To draw a list of integers int_list (of type List<Integer)
   * to the canvas, call drawSequence(int_list, writer).
   *
   * The index of each integer in the list will be
   * plotted along the x-axis; the integer value itself
   * is plotted on the y-axis.                                                  
   * */

  //Helper method to merge into an ordered array
  public static List<Integer> mergeArrays(List<Integer> left, List<Integer> right, PixelWriter writer){
    List<Integer> result = new ArrayList<Integer>();
    int leftSide = 0;
    int rightSide = 0;
    
    while(leftSide < left.size() && rightSide < right.size()){
      int leftXVal = left.get(leftSide);
      int rightXVal = right.get(rightSide);

      if(leftXVal < rightXVal){
        result.add(left.get(leftSide));
        leftSide++;
    }
    else{
      result.add(right.get(rightSide));
      rightSide++;
    }
  } 
    result.addAll(left.subList(leftSide, left.size()));
    result.addAll(right.subList(rightSide, right.size()));
    return result; 
  }

  //Method to implement Merge Sort
  public static List<Integer> MergeSort(List<Integer> S, PixelWriter writer){
    if(S.size()<=1){
      return S;
    }
    int pivot = S.size()/2;
    List<Integer> left = S.subList(0, pivot);
    List<Integer> right = S.subList(pivot, S.size());
    //Recursively split left and right arrays
    left = MergeSort(left, writer);
    right = MergeSort(right, writer);

    //Combine arrays together
    List<Integer> result = mergeArrays(left, right, writer);
    drawSequence(result, writer);
    return result;
  }
  //Method to implement quickSort
  public static List<Integer> QuickSort(List<Integer> S, PixelWriter writer){
    if(S.size() <= 1){
      return S;
    }
    //Find pivot using random selection
    Random rand = new Random();
    int pivotIndex = rand.nextInt(S.size());
    int pivotVal = S.get(pivotIndex);

    //Create left and right arrays
    List<Integer> right = new ArrayList<>();
    List<Integer> left = new ArrayList<>();

    for(int ii = 0; ii< S.size(); ii++){
      if(ii==pivotIndex){
        continue;
      } 
      int val = S.get(ii);

      if(val < pivotVal){
        left.add(val);
      }
      else{
        right.add(val);
      }
    }
    
    //Recursively sort left and right arrays
    List<Integer> qSortedLeft = QuickSort(left, writer);
    List<Integer> qSortedRight = QuickSort(right, writer);

    //Combine arrays together
    List<Integer> result = new ArrayList<>();
    result.addAll(qSortedLeft);
    result.add(pivotVal);
    result.addAll(qSortedRight);

    drawSequence(result, writer);
    //return compiled 
    return result;
  }
  //Method to implement InsertionSort
  public static List<Integer> InsertionSort(List<Integer> S, PixelWriter writer){
    
    for(int ii = 1; ii<S.size(); ii++){
      int key = S.get(ii);
      int jj = ii - 1;
      while(jj >= 0 && S.get(jj) > key){
        S.set(jj+1, S.get(jj));
        jj--;
      }
  
      S.set(jj+1, key);
      drawSequence(S, writer);
    }
    return S;
  }

  //finds max element, helper for RadixSort
  public static int getMax(List<Integer> S){
    if(S.isEmpty()){
      return 0;
    }
    int max = S.get(0);
    for(int ii = 1; ii <S.size(); ii++){
      if(S.get(ii) > max){
        max = S.get(ii);
      }
    }
    return max;
  }

  //Implement Bucket Sort as helper
  private static void bucketSort(List<Integer> S, int exp, PixelWriter writer){
    //Create buckets for each decimal
    List<List<Integer>> buckets = new ArrayList<>();
    for(int ii = 0; ii <10; ii++){
      buckets.add(new ArrayList<>());
    }
    //Sort into buckets
    for(int ii : S){
      int bucketIndex = (ii/exp)%10; // mod 10 to get decimal
      buckets.get(bucketIndex).add(ii);
    }
    //Clear list
    S.clear();

    //Merge buckets back into list with bucket ordering, draw points
    for(List<Integer> bucket : buckets){
      S.addAll(bucket);
      drawSequence(S, writer);
    }
  }
  //Method to implement RadixSort
  public static List<Integer> RadixSort(List<Integer> S, PixelWriter writer){
    if(S.isEmpty()){
      return S;
    }

    //Find max 
    int max = getMax(S);

    //Bucket sort each node, from LSD
    for(int exp = 1; max/exp > 0; exp*=10){
      bucketSort(S, exp, writer);
    }
    return S;
  }

  /* DO NOT CHANGE THIS METHOD */
  private static void drawSequence(List<Integer> sequence, PixelWriter writer) {
    for (Integer curr : sequence) {
      for (int j=0; j<sequence.size(); j++) {
        Color c = writer.getColor(j, curr);
        if (c.equals(Color.BLACK))
          writer.setPixel(j, curr, Color.WHITE);
      }
      int x = sequence.indexOf(curr);
      if (!writer.getColor(x, curr).equals(Color.BLACK)) {
        writer.setPixel(sequence.indexOf(curr), curr, Color.BLACK);
      }
    }
  } 

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){

	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){

	}
	
}
