package com.a.a.b;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.androlua.LuaEditor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class n
  extends AsyncTask
{
  private static int c;
  protected final e a;
  private ProgressDialog b;
  private LuaEditor d;
  private File e;
  private long f;
  
  public n(LuaEditor paramLuaEditor, File paramFile)
  {
    this.e = paramFile;
    this.f = this.e.length();
    this.d = paramLuaEditor;
    this.a = new e(paramLuaEditor);
    this.b = new ProgressDialog(paramLuaEditor.getContext());
    this.b.setProgressStyle(1);
    this.b.setTitle("正在打开");
    this.b.setIcon(17301659);
    this.b.setMax((int)this.f);
  }
  
  private byte[] a(InputStream paramInputStream)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(4096);
    byte[] arrayOfByte = new byte['က'];
    c = 0;
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (-1 == i) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
      c += i;
      publishProgress(new Object[0]);
    }
    paramInputStream = localByteArrayOutputStream.toByteArray();
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  public void a()
  {
    execute(new Object[0]);
    this.b.show();
  }
  
  protected Object doInBackground(Object[] paramArrayOfObject)
  {
    try
    {
      paramArrayOfObject = new String(a(new FileInputStream(this.e)));
      return paramArrayOfObject;
    }
    catch (Exception paramArrayOfObject)
    {
      this.b.setMessage(paramArrayOfObject.getMessage());
    }
    return "";
  }
  
  protected void onPostExecute(Object paramObject)
  {
    super.onPostExecute(paramObject);
    this.d.setText((String)paramObject);
    this.b.dismiss();
  }
  
  protected void onProgressUpdate(Object[] paramArrayOfObject)
  {
    this.b.setProgress(c);
    super.onProgressUpdate(paramArrayOfObject);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\b\n.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */