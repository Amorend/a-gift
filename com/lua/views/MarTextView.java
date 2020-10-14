package com.lua.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class MarTextView
  extends TextView
  implements Runnable
{
  private int currentScrollX = 0;
  private boolean isMeasure = false;
  private boolean isStop = false;
  private long stime = 0;
  private int textWidth;
  
  public MarTextView(Context paramContext)
  {
    super(paramContext);
  }
  
  public MarTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MarTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void getTextWidth()
  {
    this.textWidth = ((int)getPaint().measureText(getText().toString()));
  }
  
  @Override
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (!this.isMeasure)
    {
      getTextWidth();
      this.isMeasure = true;
    }
  }
  
  public void restartScroll()
  {
    this.currentScrollX = 0;
    startScroll();
  }
  
  @Override
  public void run()
  {
    if ((this.textWidth == 0) || (getWidth() == 0)) {
      postDelayed(this, 'È');
    }
    while ((getWidth() >= this.textWidth) || (this.isStop)) {
      return;
    }
    if (this.stime == 0) {
      this.stime = System.currentTimeMillis();
    }
    this.currentScrollX = ((int)((System.currentTimeMillis() - this.stime) * 2.0D / 20));
    scrollTo(this.currentScrollX, 0);
    if (getScrollX() >= this.textWidth)
    {
      this.currentScrollX = (0 - getWidth());
      this.stime = (System.currentTimeMillis() - this.currentScrollX * 10);
      scrollTo(this.currentScrollX, 0);
    }
    postDelayed(this, 20);
  }
  
  @Override
  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    super.setText(paramCharSequence, paramBufferType);
    startScroll();
  }
  
  public void startScroll()
  {
    this.isStop = false;
    removeCallbacks(this);
    post(this);
  }
  
  public void stopScroll()
  {
    this.isStop = true;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\lua\views\MarTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */