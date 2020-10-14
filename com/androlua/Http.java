package com.androlua;

import android.util.Log;
import com.androlua.util.AsyncTaskX;
import com.luajava.LuaException;
import com.luajava.LuaObject;
import com.luajava.LuaState;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Http
{
  private static HashMap<String, String> a;
  
  private static String a(HashMap<String, String> paramHashMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
      localStringBuilder.append("&");
    }
    if (!paramHashMap.isEmpty()) {
      localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    }
    return localStringBuilder.toString();
  }
  
  private static byte[] a(HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "UTF-8";
    }
    paramString = new ByteArrayOutputStream();
    paramHashMap1 = paramHashMap1.entrySet().iterator();
    while (paramHashMap1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramHashMap1.next();
      try
      {
        paramString.write(String.format("--%s\r\nContent-Disposition:form-data;name=\"%s\"\r\n\r\n%s\r\n", new Object[] { "----qwertyuiopasdfghjklzxcvbnm", localEntry.getKey(), localEntry.getValue() }).getBytes(str));
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
    paramHashMap1 = paramHashMap2.entrySet().iterator();
    while (paramHashMap1.hasNext())
    {
      paramHashMap2 = (Map.Entry)paramHashMap1.next();
      try
      {
        paramString.write(String.format("--%s\r\nContent-Disposition:form-data;name=\"%s\";filename=\"%s\"\r\nContent-Type:application/octet-stream\r\n\r\n", new Object[] { "----qwertyuiopasdfghjklzxcvbnm", paramHashMap2.getKey(), paramHashMap2.getValue() }).getBytes(str));
        paramString.write(LuaUtil.readAll(new FileInputStream((String)paramHashMap2.getValue())));
        paramString.write("\r\n".getBytes(str));
      }
      catch (IOException paramHashMap2)
      {
        paramHashMap2.printStackTrace();
      }
    }
    return paramString.toByteArray();
  }
  
  public static HttpTask delete(String paramString, LuaObject paramLuaObject)
  {
    paramString = new HttpTask(paramString, "DELETE", null, null, null, paramLuaObject);
    paramString.execute(new Object[0]);
    return paramString;
  }
  
  public static HttpTask delete(String paramString1, String paramString2, LuaObject paramLuaObject)
  {
    if ((paramString2.matches("[\\w\\-\\.:]+")) && (Charset.isSupported(paramString2))) {
      paramString1 = new HttpTask(paramString1, "DELETE", null, paramString2, null, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "DELETE", paramString2, null, null, paramLuaObject);
    }
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask delete(String paramString1, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "DELETE", paramString2, paramString3, null, paramLuaObject);
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask delete(String paramString1, String paramString2, String paramString3, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "DELETE", paramString2, paramString3, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask delete(String paramString1, String paramString2, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    if ((paramString2.matches("[\\w\\-\\.:]+")) && (Charset.isSupported(paramString2))) {
      paramString1 = new HttpTask(paramString1, "DELETE", null, paramString2, paramHashMap, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "DELETE", paramString2, null, paramHashMap, paramLuaObject);
    }
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask delete(String paramString, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString = new HttpTask(paramString, "DELETE", null, null, paramHashMap, paramLuaObject);
    paramString.execute(new Object[0]);
    return paramString;
  }
  
  public static HttpTask download(String paramString1, String paramString2, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "GET", null, null, null, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask download(String paramString1, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "GET", paramString3, null, null, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask download(String paramString1, String paramString2, String paramString3, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "GET", paramString3, null, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask download(String paramString1, String paramString2, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "GET", null, null, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask get(String paramString, LuaObject paramLuaObject)
  {
    paramString = new HttpTask(paramString, "GET", null, null, null, paramLuaObject);
    paramString.execute(new Object[0]);
    return paramString;
  }
  
  public static HttpTask get(String paramString1, String paramString2, LuaObject paramLuaObject)
  {
    if ((paramString2.matches("[\\w\\-\\.:]+")) && (Charset.isSupported(paramString2))) {
      paramString1 = new HttpTask(paramString1, "GET", null, paramString2, null, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "GET", paramString2, null, null, paramLuaObject);
    }
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask get(String paramString1, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "GET", paramString2, paramString3, null, paramLuaObject);
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask get(String paramString1, String paramString2, String paramString3, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "GET", paramString2, paramString3, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask get(String paramString1, String paramString2, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    if ((paramString2.matches("[\\w\\-\\.:]+")) && (Charset.isSupported(paramString2))) {
      paramString1 = new HttpTask(paramString1, "GET", null, paramString2, paramHashMap, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "GET", paramString2, null, paramHashMap, paramLuaObject);
    }
    paramString1.execute(new Object[0]);
    return paramString1;
  }
  
  public static HttpTask get(String paramString, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString = new HttpTask(paramString, "GET", null, null, paramHashMap, paramLuaObject);
    paramString.execute(new Object[0]);
    return paramString;
  }
  
  public static HashMap<String, String> getHeader()
  {
    return a;
  }
  
  public static HttpTask post(String paramString1, String paramString2, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "POST", null, null, null, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask post(String paramString1, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    if ((paramString3.matches("[\\w\\-.:]+")) && (Charset.isSupported(paramString3))) {
      paramString1 = new HttpTask(paramString1, "POST", null, paramString3, null, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "POST", paramString3, null, null, paramLuaObject);
    }
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask post(String paramString1, String paramString2, String paramString3, String paramString4, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "POST", paramString3, paramString4, null, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask post(String paramString1, String paramString2, String paramString3, String paramString4, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "POST", paramString3, paramString4, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask post(String paramString1, String paramString2, String paramString3, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    if ((paramString3.matches("[\\w\\-.:]+")) && (Charset.isSupported(paramString3))) {
      paramString1 = new HttpTask(paramString1, "POST", null, paramString3, paramHashMap, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "POST", paramString3, null, paramHashMap, paramLuaObject);
    }
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask post(String paramString1, String paramString2, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "POST", null, null, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask post(String paramString, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    return post(paramString, a(paramHashMap), paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap, String paramString2, LuaObject paramLuaObject)
  {
    return post(paramString1, a(paramHashMap), paramString2, paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    return post(paramString1, a(paramHashMap), paramString2, paramString3, paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap1, String paramString2, String paramString3, HashMap<String, String> paramHashMap2, LuaObject paramLuaObject)
  {
    return post(paramString1, a(paramHashMap1), paramString2, paramString3, paramHashMap2, paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap1, String paramString2, HashMap<String, String> paramHashMap2, LuaObject paramLuaObject)
  {
    return post(paramString1, a(paramHashMap1), paramString2, paramHashMap2, paramLuaObject);
  }
  
  public static HttpTask post(String paramString, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, LuaObject paramLuaObject)
  {
    return post(paramString, paramHashMap1, paramHashMap2, null, null, null, paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, String paramString2, LuaObject paramLuaObject)
  {
    return post(paramString1, paramHashMap1, paramHashMap2, paramString2, new HashMap(), paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    return post(paramString1, paramHashMap1, paramHashMap2, paramString2, paramString3, null, paramLuaObject);
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, String paramString2, String paramString3, HashMap<String, String> paramHashMap3, LuaObject paramLuaObject)
  {
    Object localObject = paramHashMap3;
    if (paramHashMap3 == null) {
      localObject = new HashMap();
    }
    ((HashMap)localObject).put("Content-Type", "multipart/form-data;boundary=----qwertyuiopasdfghjklzxcvbnm");
    paramString1 = new HttpTask(paramString1, "POST", paramString2, paramString3, (HashMap)localObject, paramLuaObject);
    paramString1.execute(new Object[] { a(paramHashMap1, paramHashMap2, paramString3) });
    return paramString1;
  }
  
  public static HttpTask post(String paramString1, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, String paramString2, HashMap<String, String> paramHashMap3, LuaObject paramLuaObject)
  {
    if ((paramString2.matches("[\\w\\-.:]+")) && (Charset.isSupported(paramString2))) {
      return post(paramString1, paramHashMap1, paramHashMap2, paramString2, null, paramHashMap3, paramLuaObject);
    }
    return post(paramString1, paramHashMap1, paramHashMap2, null, paramString2, paramHashMap3, paramLuaObject);
  }
  
  public static HttpTask post(String paramString, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, HashMap<String, String> paramHashMap3, LuaObject paramLuaObject)
  {
    return post(paramString, paramHashMap1, paramHashMap2, null, paramHashMap3, paramLuaObject);
  }
  
  public static HttpTask put(String paramString1, String paramString2, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "PUT", null, null, null, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask put(String paramString1, String paramString2, String paramString3, LuaObject paramLuaObject)
  {
    if ((paramString3.matches("[\\w\\-\\.:]+")) && (Charset.isSupported(paramString3))) {
      paramString1 = new HttpTask(paramString1, "PUT", null, paramString3, null, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "PUT", paramString3, null, null, paramLuaObject);
    }
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask put(String paramString1, String paramString2, String paramString3, String paramString4, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "PUT", paramString3, paramString4, null, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask put(String paramString1, String paramString2, String paramString3, String paramString4, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "PUT", paramString3, paramString4, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask put(String paramString1, String paramString2, String paramString3, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    if ((paramString3.matches("[\\w\\-\\.:]+")) && (Charset.isSupported(paramString3))) {
      paramString1 = new HttpTask(paramString1, "PUT", null, paramString3, paramHashMap, paramLuaObject);
    } else {
      paramString1 = new HttpTask(paramString1, "PUT", paramString3, null, paramHashMap, paramLuaObject);
    }
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static HttpTask put(String paramString1, String paramString2, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
  {
    paramString1 = new HttpTask(paramString1, "PUT", null, null, paramHashMap, paramLuaObject);
    paramString1.execute(new Object[] { paramString2 });
    return paramString1;
  }
  
  public static void setHeader(HashMap<String, String> paramHashMap)
  {
    a = paramHashMap;
  }
  
  public static void setUserAgent(String paramString)
  {
    if (a == null) {
      a = new HashMap();
    }
    a.put("User-Agent", paramString);
  }
  
  public static class HttpTask
    extends AsyncTaskX<Object, Object, Object>
  {
    private String a;
    private LuaObject b;
    private byte[] c;
    private String d;
    private String e;
    private HashMap<String, String> f;
    private String g;
    
    public HttpTask(String paramString1, String paramString2, String paramString3, String paramString4, HashMap<String, String> paramHashMap, LuaObject paramLuaObject)
    {
      this.a = paramString1;
      this.g = paramString2;
      this.e = paramString3;
      this.d = paramString4;
      this.f = paramHashMap;
      this.b = paramLuaObject;
    }
    
    private byte[] a(Map<String, String> paramMap)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localStringBuilder.append((String)localEntry.getKey());
        localStringBuilder.append("=");
        localStringBuilder.append((String)localEntry.getValue());
        localStringBuilder.append("&");
      }
      return localStringBuilder.toString().getBytes(this.d);
    }
    
    private byte[] d(Object[] paramArrayOfObject)
    {
      if (paramArrayOfObject.length == 1)
      {
        paramArrayOfObject = paramArrayOfObject[0];
        if ((paramArrayOfObject instanceof String)) {
          return ((String)paramArrayOfObject).getBytes(this.d);
        }
        if (paramArrayOfObject.getClass().getComponentType() == Byte.TYPE) {
          return (byte[])paramArrayOfObject;
        }
        if ((paramArrayOfObject instanceof File)) {
          return LuaUtil.readAll(new FileInputStream((File)paramArrayOfObject));
        }
        if ((paramArrayOfObject instanceof Map)) {
          return a((Map)paramArrayOfObject);
        }
      }
      return null;
    }
    
    protected Object a(Object[] paramArrayOfObject)
    {
      try
      {
        Object localObject1 = (HttpURLConnection)new URL(this.a).openConnection();
        ((HttpURLConnection)localObject1).setConnectTimeout(6000);
        HttpURLConnection.setFollowRedirects(true);
        ((HttpURLConnection)localObject1).setDoInput(true);
        ((HttpURLConnection)localObject1).setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
        if (this.d == null) {
          this.d = "UTF-8";
        }
        ((HttpURLConnection)localObject1).setRequestProperty("Accept-Charset", this.d);
        if (this.e != null) {
          ((HttpURLConnection)localObject1).setRequestProperty("Cookie", this.e);
        }
        if (Http.a() != null)
        {
          localObject2 = Http.a().entrySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (Map.Entry)((Iterator)localObject2).next();
            ((HttpURLConnection)localObject1).setRequestProperty((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue());
          }
        }
        if (this.f != null)
        {
          localObject2 = this.f.entrySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (Map.Entry)((Iterator)localObject2).next();
            ((HttpURLConnection)localObject1).setRequestProperty((String)((Map.Entry)localObject3).getKey(), (String)((Map.Entry)localObject3).getValue());
          }
        }
        if (this.g != null) {
          ((HttpURLConnection)localObject1).setRequestMethod(this.g);
        }
        if ((!"GET".equals(this.g)) && (paramArrayOfObject.length != 0))
        {
          this.c = d(paramArrayOfObject);
          ((HttpURLConnection)localObject1).setDoOutput(true);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("");
          ((StringBuilder)localObject2).append(this.c.length);
          ((HttpURLConnection)localObject1).setRequestProperty("Content-length", ((StringBuilder)localObject2).toString());
        }
        ((HttpURLConnection)localObject1).connect();
        if (("GET".equals(this.g)) && (paramArrayOfObject.length != 0))
        {
          localObject2 = new File((String)paramArrayOfObject[0]);
          if (!((File)localObject2).getParentFile().exists()) {
            ((File)localObject2).getParentFile().mkdirs();
          }
          localObject2 = new FileOutputStream((File)localObject2);
          LuaUtil.copyFile(((HttpURLConnection)localObject1).getInputStream(), (OutputStream)localObject2);
          return new Object[] { Integer.valueOf(((HttpURLConnection)localObject1).getResponseCode()), paramArrayOfObject[0], ((HttpURLConnection)localObject1).getHeaderFields() };
        }
        if (paramArrayOfObject.length != 0) {
          ((HttpURLConnection)localObject1).getOutputStream().write(this.c);
        }
        int k = ((HttpURLConnection)localObject1).getResponseCode();
        paramArrayOfObject = ((HttpURLConnection)localObject1).getHeaderFields();
        ((HttpURLConnection)localObject1).getContentEncoding();
        Object localObject3 = (List)paramArrayOfObject.get("Set-Cookie");
        Object localObject2 = new StringBuilder();
        if (localObject3 != null)
        {
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            ((StringBuilder)localObject2).append((String)((Iterator)localObject3).next());
            ((StringBuilder)localObject2).append(";");
          }
        }
        localObject3 = (List)paramArrayOfObject.get("Content-Type");
        Object localObject4;
        if (localObject3 != null)
        {
          localObject3 = ((List)localObject3).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (String)((Iterator)localObject3).next();
            int i = ((String)localObject4).indexOf("charset");
            if (i != -1)
            {
              int m = ((String)localObject4).indexOf("=", i);
              if (m != -1)
              {
                int j = ((String)localObject4).indexOf(";", m);
                i = j;
                if (j == -1) {
                  i = ((String)localObject4).length();
                }
                this.d = ((String)localObject4).substring(m + 1, i);
              }
            }
          }
        }
        localObject3 = new StringBuilder();
        Object localObject5;
        try
        {
          localObject4 = ((HttpURLConnection)localObject1).getInputStream();
          localObject5 = new BufferedReader(new InputStreamReader((InputStream)localObject4, this.d));
          for (;;)
          {
            String str = ((BufferedReader)localObject5).readLine();
            if ((str == null) || (isCancelled())) {
              break;
            }
            ((StringBuilder)localObject3).append(str);
            ((StringBuilder)localObject3).append('\n');
          }
          ((InputStream)localObject4).close();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        localObject1 = ((HttpURLConnection)localObject1).getErrorStream();
        if (localObject1 != null)
        {
          BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader((InputStream)localObject1, this.d));
          for (;;)
          {
            localObject5 = localBufferedReader.readLine();
            if ((localObject5 == null) || (isCancelled())) {
              break;
            }
            ((StringBuilder)localObject3).append((String)localObject5);
            ((StringBuilder)localObject3).append('\n');
          }
          ((InputStream)localObject1).close();
        }
        localObject1 = new String((StringBuilder)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
        return new Object[] { Integer.valueOf(k), localObject1, localObject2, paramArrayOfObject };
      }
      catch (Exception paramArrayOfObject)
      {
        paramArrayOfObject.printStackTrace();
      }
      return tmp882_875;
    }
    
    protected void a(Object paramObject)
    {
      if (isCancelled()) {
        return;
      }
      try
      {
        this.b.call((Object[])paramObject);
        return;
      }
      catch (LuaException paramObject) {}
      try
      {
        this.b.getLuaState().getLuaObject("print").call(new Object[] { ((LuaException)paramObject).getMessage() });
        Log.i("lua", ((LuaException)paramObject).getMessage());
        return;
      }
      catch (LuaException localLuaException)
      {
        for (;;) {}
      }
    }
    
    public boolean cancel()
    {
      return super.cancel(true);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\Http.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */