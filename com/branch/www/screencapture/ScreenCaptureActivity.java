package com.branch.www.screencapture;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(21)
public class ScreenCaptureActivity
  extends Activity
{
  private TextView a;
  
  public void a()
  {
    if (Build.VERSION.SDK_INT < 21)
    {
      Toast.makeText(this, "仅支持安卓5以上系统", 0).show();
      this.a.setText("仅支持安卓5以上系统");
      return;
    }
    startActivityForResult(((MediaProjectionManager)getSystemService("media_projection")).createScreenCaptureIntent(), 18);
  }
  
  public void finish()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      finishAndRemoveTask();
      return;
    }
    super.finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 18) && (paramInt2 == -1) && (paramIntent != null))
    {
      b.a(paramIntent);
      Toast.makeText(this, "获得权限成功", 0).show();
    }
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.a = new TextView(this);
    this.a.setText("请授予权限");
    setContentView(this.a);
    a();
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\branch\www\screencapture\ScreenCaptureActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */