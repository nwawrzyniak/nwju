package nwawsoft.util;

public class CharFunctions
{
  public CharFunctions()
  {
    
  }
  
  public boolean isNumeric(char pInput)
  {
    if (((int)pInput >= 48 && (int)pInput <=57) || (int)pInput == 44 || (int)pInput == 46)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}