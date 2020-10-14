package com.androlua;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import com.luajava.LuaException;
import com.luajava.LuaJavaAPI;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import com.luajava.LuaTable;
import com.luajava.LuaTable.LuaEntry;
import java.util.Iterator;
import java.util.Set;

public class LuaPreferenceFragment
  extends PreferenceFragment
  implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener
{
  private LuaTable<Integer, LuaTable> a;
  private Preference.OnPreferenceChangeListener b;
  private Preference.OnPreferenceClickListener c;
  
  private void a(LuaTable<Integer, LuaTable> paramLuaTable)
  {
    PreferenceScreen localPreferenceScreen = getPreferenceScreen();
    int j = paramLuaTable.length();
    LuaState localLuaState = paramLuaTable.getLuaState();
    int i = 1;
    while (i <= j)
    {
      Object localObject2 = (LuaTable)paramLuaTable.get(Integer.valueOf(i));
      try
      {
        Object localObject1 = ((LuaTable)localObject2).getI(1L);
        if (((LuaObject)localObject1).isNil()) {
          throw new IllegalArgumentException("Fist value Must be a Class<Preference>, checked import package.");
        }
        localObject1 = (Preference)((LuaObject)localObject1).call(new Object[] { getActivity() });
        localObject2 = ((LuaTable)localObject2).entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          LuaTable.LuaEntry localLuaEntry = (LuaTable.LuaEntry)((Iterator)localObject2).next();
          Object localObject3 = localLuaEntry.getKey();
          boolean bool = localObject3 instanceof String;
          if (bool) {
            try
            {
              LuaJavaAPI.javaSetter(localLuaState, localObject1, (String)localObject3, localLuaEntry.getValue());
            }
            catch (LuaException localLuaException)
            {
              localLuaException.printStackTrace();
            }
          }
        }
        ((Preference)localObject1).setOnPreferenceChangeListener(this);
        ((Preference)localObject1).setOnPreferenceClickListener(this);
        localPreferenceScreen.addPreference((Preference)localObject1);
      }
      catch (Exception localException)
      {
        localLuaState.getContext().sendError("LuaPreferenceFragment", localException);
      }
      i += 1;
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (Build.VERSION.SDK_INT >= 24) {
      getPreferenceManager().setStorageDeviceProtected();
    }
    setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
    a(this.a);
  }
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    if (this.b != null) {
      return this.b.onPreferenceChange(paramPreference, paramObject);
    }
    return true;
  }
  
  public boolean onPreferenceClick(Preference paramPreference)
  {
    if (this.c != null) {
      return this.c.onPreferenceClick(paramPreference);
    }
    return false;
  }
  
  public void setOnPreferenceChangeListener(Preference.OnPreferenceChangeListener paramOnPreferenceChangeListener)
  {
    this.b = paramOnPreferenceChangeListener;
  }
  
  public void setOnPreferenceClickListener(Preference.OnPreferenceClickListener paramOnPreferenceClickListener)
  {
    this.c = paramOnPreferenceClickListener;
  }
  
  public void setPreference(LuaTable<Integer, LuaTable> paramLuaTable)
  {
    this.a = paramLuaTable;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaPreferenceFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */