package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

class p
{
  private String a;
  private String b;
  private String c;
  private String d;
  
  public p(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = paramString1;
    if (paramString1 == null) {
      str = "";
    }
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = "";
    }
    paramString2 = paramString3;
    if (paramString3 == null) {
      paramString2 = "";
    }
    paramString3 = paramString4;
    if (paramString4 == null) {
      paramString3 = "";
    }
    this.a = str;
    this.b = paramString1;
    this.c = paramString2;
    this.d = paramString3;
  }
  
  public JSONObject a()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("n", this.a);
      localJSONObject.put("v", this.b);
      localJSONObject.put("c", this.c);
      localJSONObject.put("a", this.d);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      bd.b(localJSONException);
    }
    return null;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */