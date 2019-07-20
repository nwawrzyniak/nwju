package nwawsoft.util;

/**
 * Provided functions to create a new Array of any type with a new size.
 * This can bypass the static size of arrays in Java.
 * However, you should really never do this.
 * Using any of this functions means you should have taken a different data structure in the first place.
 */
public class ArrayFunctions
{
  public ArrayFunctions()
  {
    
  }
  
  /**
   * Returns an Object-Array with the size of the old Array + an Integer.
   * It contains as much of the old Array's content as possible.
   */
  public Object[] expandArray(Object[] oldArray, int additions)
  {
    int oldSize = oldArray.length;
    int newSize = oldSize + additions;
    Object[] biggerArray = new Object[newSize];
    for (int i = 0; i < oldSize; i++)
    {
      biggerArray[i] = oldArray[i];
    }
    return biggerArray;
  }
  
  /**
   * Returns an Object-Array with the size of the old Array - an Integer.
   * It contains as much of the old Array's content as possible.
   */
  public Object[] reduceArray(Object[] oldArray, int subtractions)
  {
    int oldSize = oldArray.length;
    int newSize = oldSize-subtractions;
    Object[] smallerArray = new Object[newSize];
    for (int i = 0; i < newSize; i++)
    {
      smallerArray[i] = oldArray[i];
    }
    return smallerArray;
  }
  
  /**
   * Returns an Object-Array with the size of the old Array - 1.
   * It contains all information of the old Array's except the first content gets deleted.
   * All contents move one position more to the beginning of the Array.
   */
  public Object[] deleteFirst(Object[] oldArray)
  {
    int oldSize = oldArray.length;
    Object[] smallerArray = new Object[oldSize-1];
    for (int i = 0; i < oldSize-1; i++)
    {
      smallerArray[i] = oldArray[i+1];
    }
    return smallerArray;
  }
  
  /**
   * Returns an Object-Array with the specified size.
   * It contains as much of the old Array's content as possible.
   */
  public Object[] changeArraySize(Object[] oldArray, int newSize)
  {
    int oldSize = oldArray.length;
    Object[] newArray = new Object[newSize];
    if (newSize > oldSize)
    {
      for (int i=0; i < oldSize; i++)
      {
        newArray[i] = oldArray[i];
      }
    }
    if (newSize < oldSize)
    {
      for (int i = 0; i < newSize; i++)
      {
        newArray[i] = oldArray[i];
      }
    }
    if (newSize == oldSize)
    {
      for (int i = 0; i < newSize; i++)
      {
        newArray[i] = oldArray[i];
      }
    }
    return newArray;
  }
  
  /**
   * Returns an empty Object-Array with the size of the old Array.
   */
  public Object[] clear(Object[] oldArray)
  {
    int oldSize = oldArray.length;
    Object[] smallerArray = new Object[oldSize];
    return smallerArray;
  }
}