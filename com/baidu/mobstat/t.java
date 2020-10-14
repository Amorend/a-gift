package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

class t
{
  private String a;
  private String b;
  private String c;
  
  public t(String paramString1, String paramString2, String paramString3)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "";
    }
    this.a = str;
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    this.b = paramString1;
    paramString1 = paramString3;
    if (paramString3 == null) {
      paramString1 = "";
    }
    this.c = paramString1;
  }
  
  public JSONObject a()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("n", this.a);
      localJSONObject.put("v", this.b);
      localJSONObject.put("w", this.c);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      bd.b(localJSONException);
    }
    return null;
  }
  
  public String b()
  {
    return this.a;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */