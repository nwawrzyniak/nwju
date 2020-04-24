package nwawsoft.util.natives;

import nwawsoft.util.tools.DebugPrinter;

/**
 * Supplies functions to create a new Array of any type with a new size.
 * This can bypass the static size of arrays in Java.
 * However, you should really never do this.
 * Using any of these functions means you should have taken a dynamic data structure (like {@code List}) in the first place.
 */
public class ArrayFunctions {
  /**
   * Returns an array with size oldArray.length+additions.
   * It contains as much of the old Array's content as possible, starting at index 0 and filling the additional spaces
   * with null.
   *
   * @param oldArray an array of any type.
   * @param additions how many spaces more the new array shall have.
   * @return an array with size oldArray.length+additions which contains as much content of oldArray as possible.
   */
  public static Object[] expandArray(final Object[] oldArray, final int additions) {
    int oldSize = oldArray.length;
    int newSize = oldSize + additions;
    Object[] newArray = new Object[newSize];
    System.arraycopy(oldArray, 0, newArray, 0, oldSize);
    for (int i = oldSize; i < newSize; i++) {
      try {
        newArray[i] = null;
      } catch (Exception e) {
        DebugPrinter.dp("Tried to write null to a data type that does not accept null. Skipping.");
      }
    }
    return newArray;
  }

  /**
   * Returns an array with size oldArray.length-subtractions.
   * It contains as much of the old Array's content as possible.
   *
   * @param oldArray an array of any type.
   * @param subtractions how many spaces less the new array shall have.
   * @return an array with size oldArray.length-subtractions which contains as much content of oldArray as possible.
   */
  public static Object[] reduceArray(final Object[] oldArray, final int subtractions) {
    int oldSize = oldArray.length;
    int newSize = oldSize-subtractions;
    Object[] newArray = new Object[newSize];
    System.arraycopy(oldArray, 0, newArray, 0, newSize);
    return newArray;
  }

  /**
   * Returns an Object-Array with the size of the old Array - 1.
   * It contains all information of the old Array's except the first content gets deleted.
   * All contents move one position more to the beginning of the Array.
   *
   * @param oldArray an array of any type.
   * @return a new array of size oldArray-1 with the contents of oldArray, except for entry oldArray[0].
   */
  public static Object[] deleteFirst(final Object[] oldArray) {
    int oldSize = oldArray.length;
    Object[] newArray = new Object[oldSize-1];
    System.arraycopy(oldArray, 1, newArray, 0, oldSize - 1);
    return newArray;
  }

  /**
   * Returns an Object-Array with the specified size.
   * It contains as much of the old Array's content as possible.
   *
   * @param oldArray an array of any type.
   * @param newSize the size of the new array.
   * @return an array with size newSize and as much content of oldArray as possible.
   */
  public static Object[] changeArraySize(final Object[] oldArray, final int newSize) {
    int oldSize = oldArray.length;
    Object[] newArray = new Object[newSize];
    if (newSize > oldSize)
    {
      System.arraycopy(oldArray, 0, newArray, 0, oldSize);
      for (int i = oldSize; i < newSize; i++) {
        try {
          newArray[i] = null;
        } catch (Exception e) {
          DebugPrinter.dp("Tried to write null to a data type that does not accept null. Skipping.");
        }
      }
    } else {
      System.arraycopy(oldArray, 0, newArray, 0, newSize);
    }
    return newArray;
  }

  /**
   * Returns an empty Object-Array with the size of the old Array.
   *
   * @param oldArray an array of any type.
   * @return an empty array with the same size as oldArray.
   */
  public static Object[] clear(final Object[] oldArray) {
    Object[] newArray = new Object[oldArray.length];
    for (int i = 0; i < newArray.length; i++) {
      try {
        newArray[i] = null;
      } catch (Exception e) {
        DebugPrinter.dp("Tried to write null to a data type that does not accept null. Skipping.");
      }
    }
    return newArray;
  }
}
