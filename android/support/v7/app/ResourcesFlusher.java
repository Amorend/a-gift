package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher
{
  private static final String TAG = "ResourcesFlusher";
  private static Field sDrawableCacheField;
  private static boolean sDrawableCacheFieldFetched;
  private static Field sResourcesImplField;
  private static boolean sResourcesImplFieldFetched;
  private static Class sThemedResourceCacheClazz;
  private static boolean sThemedResourceCacheClazzFetched;
  private static Field sThemedResourceCache_mUnthemedEntriesField;
  private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;
  
  static boolean flush(@NonNull Resources paramResources)
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 24) {
      return flushNougats(paramResources);
    }
    if (i >= 23) {
      return flushMarshmallows(paramResources);
    }
    if (i >= 21) {
      return flushLollipops(paramResources);
    }
    return false;
  }
  
  private static boolean flushLollipops(@NonNull Resources paramResources)
  {
    if (!sDrawableCacheFieldFetched) {}
    try
    {
      sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
      sDrawableCacheField.setAccessible(true);
      sDrawableCacheFieldFetched = true;
      if (sDrawableCacheField == null) {}
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      try
      {
        paramResources = (Map)sDrawableCacheField.get(paramResources);
        if (paramResources != null)
        {
          paramResources.clear();
          return true;
          localNoSuchFieldException = localNoSuchFieldException;
          Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", localNoSuchFieldException);
        }
      }
      catch (IllegalAccessException paramResources)
      {
        for (;;)
        {
          Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", paramResources);
          paramResources = null;
        }
      }
    }
    return false;
  }
  
  private static boolean flushMarshmallows(@NonNull Resources paramResources)
  {
    boolean bool = true;
    if (!sDrawableCacheFieldFetched) {}
    try
    {
      sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
      sDrawableCacheField.setAccessible(true);
      sDrawableCacheFieldFetched = true;
      if (sDrawableCacheField == null) {}
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        try
        {
          paramResources = sDrawableCacheField.get(paramResources);
          if (paramResources != null) {
            break;
          }
          return false;
        }
        catch (IllegalAccessException paramResources)
        {
          Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", paramResources);
        }
        localNoSuchFieldException = localNoSuchFieldException;
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", localNoSuchFieldException);
        continue;
        paramResources = null;
      }
      if (paramResources == null) {
        break label90;
      }
    }
    if (flushThemedResourcesCache(paramResources)) {}
    for (;;)
    {
      return bool;
      label90:
      bool = false;
    }
  }
  
  private static boolean flushNougats(@NonNull Resources paramResources)
  {
    boolean bool = true;
    if (!sResourcesImplFieldFetched) {}
    for (;;)
    {
      try
      {
        sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
        sResourcesImplField.setAccessible(true);
        sResourcesImplFieldFetched = true;
        if (sResourcesImplField == null) {
          return false;
        }
      }
      catch (NoSuchFieldException localNoSuchFieldException1)
      {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", localNoSuchFieldException1);
        continue;
      }
      try
      {
        paramResources = sResourcesImplField.get(paramResources);
        if (paramResources == null) {
          continue;
        }
        if (sDrawableCacheFieldFetched) {}
      }
      catch (IllegalAccessException paramResources)
      {
        try
        {
          sDrawableCacheField = paramResources.getClass().getDeclaredField("mDrawableCache");
          sDrawableCacheField.setAccessible(true);
          sDrawableCacheFieldFetched = true;
          if (sDrawableCacheField != null)
          {
            try
            {
              paramResources = sDrawableCacheField.get(paramResources);
              if ((paramResources == null) || (!flushThemedResourcesCache(paramResources))) {
                break label161;
              }
              return bool;
            }
            catch (IllegalAccessException paramResources)
            {
              Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", paramResources);
            }
            paramResources = paramResources;
            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", paramResources);
            paramResources = null;
          }
        }
        catch (NoSuchFieldException localNoSuchFieldException2)
        {
          for (;;)
          {
            Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", localNoSuchFieldException2);
            continue;
            paramResources = null;
            continue;
            label161:
            bool = false;
          }
        }
      }
    }
  }
  
  private static boolean flushThemedResourcesCache(@NonNull Object paramObject)
  {
    if (!sThemedResourceCacheClazzFetched) {}
    try
    {
      sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
      sThemedResourceCacheClazzFetched = true;
      if (sThemedResourceCacheClazz == null) {
        return false;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", localClassNotFoundException);
      }
      if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {}
      try
      {
        sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
        sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
        sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
        if (sThemedResourceCache_mUnthemedEntriesField == null) {
          return false;
        }
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        for (;;)
        {
          Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", localNoSuchFieldException);
        }
        try
        {
          paramObject = (LongSparseArray)sThemedResourceCache_mUnthemedEntriesField.get(paramObject);
          if (paramObject != null)
          {
            ((LongSparseArray)paramObject).clear();
            return true;
          }
        }
        catch (IllegalAccessException paramObject)
        {
          for (;;)
          {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", (Throwable)paramObject);
            paramObject = null;
          }
        }
      }
    }
    return false;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\app\ResourcesFlusher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */