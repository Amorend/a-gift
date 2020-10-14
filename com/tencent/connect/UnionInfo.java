package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.e;
import com.tencent.tauth.IUiListener;

public class UnionInfo
  extends BaseApi
{
  public static final String URL_GET_UNION_ID = "https://graph.qq.com/oauth2.0/me";
  
  public UnionInfo(Context paramContext, QQToken paramQQToken)
  {
    super(paramQQToken);
  }
  
  public void getUnionId(IUiListener paramIUiListener)
  {
    Bundle localBundle = a();
    localBundle.putString("unionid", "1");
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.b, e.a(), "https://graph.qq.com/oauth2.0/me", localBundle, "GET", paramIUiListener);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\connect\UnionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */