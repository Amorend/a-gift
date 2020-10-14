package android.support.transition;

import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TransitionValues
{
  public final Map<String, Object> values = new HashMap();
  public View view;
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof TransitionValues)) && (this.view == ((TransitionValues)paramObject).view) && (this.values.equals(((TransitionValues)paramObject).values));
  }
  
  public int hashCode()
  {
    return this.view.hashCode() * 31 + this.values.hashCode();
  }
  
  public String toString()
  {
    String str1 = "TransitionValues@" + Integer.toHexString(hashCode()) + ":\n";
    str1 = str1 + "    view = " + this.view + "\n";
    str1 = str1 + "    values:";
    Iterator localIterator = this.values.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      str1 = str1 + "    " + str2 + ": " + this.values.get(str2) + "\n";
    }
    return str1;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\TransitionValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */