package com.androlua;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.io.IOException;

class LuaCameraView
  extends SurfaceView
{
  private SurfaceHolder a = null;
  
  public LuaCameraView(Context paramContext)
  {
    super(paramContext);
    this.a.addCallback(new SurfaceHolder.Callback()
    {
      private Camera b;
      
      public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        paramAnonymousSurfaceHolder = this.b.getParameters();
        paramAnonymousSurfaceHolder.setPictureFormat(256);
        paramAnonymousSurfaceHolder.setPreviewSize(854, 480);
        paramAnonymousSurfaceHolder.setFocusMode("auto");
        paramAnonymousSurfaceHolder.setPictureSize(2592, 1456);
        this.b.startPreview();
      }
      
      public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
      {
        this.b = Camera.open();
        try
        {
          this.b.setPreviewDisplay(paramAnonymousSurfaceHolder);
          return;
        }
        catch (IOException paramAnonymousSurfaceHolder)
        {
          for (;;) {}
        }
        this.b = null;
      }
      
      public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
      {
        this.b.stopPreview();
        this.b.release();
        this.b = null;
      }
    });
    this.a.setType(3);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaCameraView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */