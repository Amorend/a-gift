package android.widget;

import android.view.View;

public class PageAdapter
  extends BasePageAdapter
{
  private final Adapter a;
  private View[] b;
  
  public PageAdapter(Adapter paramAdapter)
  {
    this.a = paramAdapter;
    this.b = new View[paramAdapter.getViewTypeCount()];
  }
  
  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
    paramObject = (View)paramObject;
    ((PageView)paramView).removeView((View)paramObject);
    this.b[this.a.getItemViewType(paramInt)] = paramObject;
  }
  
  public int getCount()
  {
    return this.a.getCount();
  }
  
  public Object instantiateItem(View paramView, int paramInt)
  {
    int i = this.a.getItemViewType(paramInt);
    if (this.b[i] != null) {
      ((PageView)paramView).removeView(this.b[i]);
    }
    Object localObject = this.a;
    View localView = this.b[i];
    paramView = (PageView)paramView;
    localObject = ((Adapter)localObject).getView(paramInt, localView, paramView);
    paramView.addView((View)localObject, 0);
    this.b[i] = null;
    return localObject;
  }
  
  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\PageAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */