package com.baidu.mobstat;

import org.json.JSONException;
import org.json.JSONObject;

public class be
{
  public boolean a = false;
  public String b = "";
  public boolean c = false;
  
  public be() {}
  
  public be(JSONObject paramJSONObject)
  {
    try
    {
      this.a = paramJSONObject.getBoolean("SDK_BPLUS_SERVICE");
    }
    catch (Exception localException1)
    {
      bd.b(localException1);
    }
    try
    {
      this.b = paramJSONObject.getString("SDK_PRODUCT_LY");
    }
    catch (Exception localException2)
    {
      bd.b(localException2);
    }
    try
    {
      this.c = paramJSONObject.getBoolean("SDK_LOCAL_SERVER");
      return;
    }
    catch (Exception paramJSONObject)
    {
      bd.b(paramJSONObject);
    }
  }
  
  public JSONObject a()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("SDK_BPLUS_SERVICE", this.a);
    }
    catch (JSONException localJSONException1)
    {
      bd.b(localJSONException1);
    }
    try
    {
      localJSONObject.put("SDK_PRODUCT_LY", this.b);
    }
    catch (JSONException localJSONException2)
    {
      bd.b(localJSONException2);
    }
    try
    {
      localJSONObject.put("SDK_LOCAL_SERVER", this.c);
      return localJSONObject;
    }
    catch (JSONException localJSONException3)
    {
      bd.b(localJSONException3);
    }
    return localJSONObject;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\baidu\mobstat\be.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */