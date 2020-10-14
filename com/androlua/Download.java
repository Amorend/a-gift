package com.androlua;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.widget.EditText;
import java.io.File;
import java.util.HashMap;

public class Download
{
  private final LuaContext a;
  private EditText b;
  private String c;
  private DownloadBroadcastReceiver d;
  private HashMap<Long, String[]> e = new HashMap();
  private OnDownloadCompleteListener f;
  private String g;
  private String h;
  private String i;
  private String j;
  private long k;
  private String l;
  
  public Download(LuaContext paramLuaContext)
  {
    this.a = paramLuaContext;
  }
  
  public long getContentLength()
  {
    return this.k;
  }
  
  public String getDestinationDir()
  {
    return this.h;
  }
  
  public String getFilePath()
  {
    return this.l;
  }
  
  public String getMimeType()
  {
    return this.j;
  }
  
  public String getUrl()
  {
    return this.c;
  }
  
  public String getUserAgent()
  {
    return this.i;
  }
  
  public void setContentLength(long paramLong)
  {
    this.k = paramLong;
  }
  
  public void setDestinationDir(String paramString)
  {
    this.h = paramString;
  }
  
  public void setFilePath(String paramString)
  {
    this.l = paramString;
  }
  
  public void setMessage(String paramString)
  {
    this.g = paramString;
  }
  
  public void setMimeType(String paramString)
  {
    this.j = paramString;
  }
  
  public void setOnDownloadCompleteListener(OnDownloadCompleteListener paramOnDownloadCompleteListener)
  {
    this.f = paramOnDownloadCompleteListener;
  }
  
  public void setUrl(String paramString)
  {
    this.c = paramString;
  }
  
  public void setUserAgent(String paramString)
  {
    this.i = paramString;
  }
  
  public long start(boolean paramBoolean)
  {
    if (this.d == null)
    {
      localObject1 = new IntentFilter();
      ((IntentFilter)localObject1).addAction("android.intent.action.DOWNLOAD_COMPLETE");
      this.d = new DownloadBroadcastReceiver(null);
      this.a.getContext().registerReceiver(this.d, (IntentFilter)localObject1);
    }
    Object localObject1 = (DownloadManager)this.a.getContext().getSystemService("download");
    Object localObject2 = Uri.parse(this.c);
    ((Uri)localObject2).getLastPathSegment();
    localObject2 = new DownloadManager.Request((Uri)localObject2);
    if (this.h == null) {
      this.h = "Download";
    }
    ((DownloadManager.Request)localObject2).setDestinationInExternalPublicDir(this.h, this.l);
    ((DownloadManager.Request)localObject2).setTitle(this.l);
    ((DownloadManager.Request)localObject2).setDescription(this.c);
    if (paramBoolean) {
      ((DownloadManager.Request)localObject2).setAllowedNetworkTypes(2);
    }
    if (this.j == null) {
      this.j = "*/*";
    }
    ((DownloadManager.Request)localObject2).setMimeType(this.j);
    long l1 = ((DownloadManager)localObject1).enqueue((DownloadManager.Request)localObject2);
    this.e.put(Long.valueOf(l1), new String[] { new File(this.h, this.l).getAbsolutePath(), this.j });
    return l1;
  }
  
  public void start() {}
  
  public void start(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.c = paramString1;
    this.g = paramString4;
    paramString4 = Uri.parse(this.c);
    this.l = paramString3;
    if (paramString3 == null) {
      this.l = paramString4.getLastPathSegment();
    }
    if (paramString2 == null) {
      this.h = "Download";
    }
    this.b = new EditText(this.a.getContext());
    this.b.setText(this.l);
    if (this.g == null) {
      this.g = paramString1;
    }
    new AlertDialog.Builder(this.a.getContext()).setTitle("Download").setMessage(this.g).setView(this.b).setPositiveButton("Download", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Download.a(Download.this, Download.a(Download.this).getText().toString());
        Download.this.start(false);
      }
    }).setNegativeButton("Cancel", null).setNeutralButton("Only Wifi", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Download.a(Download.this, Download.a(Download.this).getText().toString());
        Download.this.start(true);
      }
    }).create().show();
  }
  
  private class DownloadBroadcastReceiver
    extends BroadcastReceiver
  {
    private DownloadBroadcastReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      long l = paramIntent.getLongExtra("extra_download_id", 0L);
      paramIntent.getExtras();
      if ((Download.b(Download.this).containsKey(Long.valueOf(l))) && (Download.c(Download.this) != null))
      {
        paramContext = (String[])Download.b(Download.this).get(Long.valueOf(l));
        Download.c(Download.this).onDownloadComplete(paramContext[0], paramContext[1]);
      }
    }
  }
  
  public static abstract interface OnDownloadCompleteListener
  {
    public abstract void onDownloadComplete(String paramString1, String paramString2);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\Download.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */