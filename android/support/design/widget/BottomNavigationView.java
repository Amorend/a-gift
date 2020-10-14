package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R.color;
import android.support.design.R.dimen;
import android.support.design.R.style;
import android.support.design.R.styleable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuBuilder.Callback;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BottomNavigationView
  extends FrameLayout
{
  private static final int[] CHECKED_STATE_SET = { 16842912 };
  private static final int[] DISABLED_STATE_SET = { -16842910 };
  private static final int MENU_PRESENTER_ID = 1;
  private final MenuBuilder mMenu;
  private MenuInflater mMenuInflater;
  private final BottomNavigationMenuView mMenuView;
  private final BottomNavigationPresenter mPresenter = new BottomNavigationPresenter();
  private OnNavigationItemReselectedListener mReselectedListener;
  private OnNavigationItemSelectedListener mSelectedListener;
  
  public BottomNavigationView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public BottomNavigationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public BottomNavigationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    ThemeUtils.checkAppCompatTheme(paramContext);
    this.mMenu = new BottomNavigationMenu(paramContext);
    this.mMenuView = new BottomNavigationMenuView(paramContext);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 17;
    this.mMenuView.setLayoutParams(localLayoutParams);
    this.mPresenter.setBottomNavigationMenuView(this.mMenuView);
    this.mPresenter.setId(1);
    this.mMenuView.setPresenter(this.mPresenter);
    this.mMenu.addMenuPresenter(this.mPresenter);
    this.mPresenter.initForMenu(getContext(), this.mMenu);
    paramAttributeSet = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.BottomNavigationView, paramInt, R.style.Widget_Design_BottomNavigationView);
    if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_itemIconTint))
    {
      this.mMenuView.setIconTintList(paramAttributeSet.getColorStateList(R.styleable.BottomNavigationView_itemIconTint));
      if (!paramAttributeSet.hasValue(R.styleable.BottomNavigationView_itemTextColor)) {
        break label313;
      }
      this.mMenuView.setItemTextColor(paramAttributeSet.getColorStateList(R.styleable.BottomNavigationView_itemTextColor));
    }
    for (;;)
    {
      if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_elevation)) {
        ViewCompat.setElevation(this, paramAttributeSet.getDimensionPixelSize(R.styleable.BottomNavigationView_elevation, 0));
      }
      paramInt = paramAttributeSet.getResourceId(R.styleable.BottomNavigationView_itemBackground, 0);
      this.mMenuView.setItemBackgroundRes(paramInt);
      if (paramAttributeSet.hasValue(R.styleable.BottomNavigationView_menu)) {
        inflateMenu(paramAttributeSet.getResourceId(R.styleable.BottomNavigationView_menu, 0));
      }
      paramAttributeSet.recycle();
      addView(this.mMenuView, localLayoutParams);
      if (Build.VERSION.SDK_INT < 21) {
        addCompatibilityTopDivider(paramContext);
      }
      this.mMenu.setCallback(new MenuBuilder.Callback()
      {
        public boolean onMenuItemSelected(MenuBuilder paramAnonymousMenuBuilder, MenuItem paramAnonymousMenuItem)
        {
          if ((BottomNavigationView.this.mReselectedListener != null) && (paramAnonymousMenuItem.getItemId() == BottomNavigationView.this.getSelectedItemId())) {
            BottomNavigationView.this.mReselectedListener.onNavigationItemReselected(paramAnonymousMenuItem);
          }
          while ((BottomNavigationView.this.mSelectedListener != null) && (!BottomNavigationView.this.mSelectedListener.onNavigationItemSelected(paramAnonymousMenuItem))) {
            return true;
          }
          return false;
        }
        
        public void onMenuModeChange(MenuBuilder paramAnonymousMenuBuilder) {}
      });
      return;
      this.mMenuView.setIconTintList(createDefaultColorStateList(16842808));
      break;
      label313:
      this.mMenuView.setItemTextColor(createDefaultColorStateList(16842808));
    }
  }
  
  private void addCompatibilityTopDivider(Context paramContext)
  {
    View localView = new View(paramContext);
    localView.setBackgroundColor(ContextCompat.getColor(paramContext, R.color.design_bottom_navigation_shadow_color));
    localView.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_shadow_height)));
    addView(localView);
  }
  
  private ColorStateList createDefaultColorStateList(int paramInt)
  {
    Object localObject = new TypedValue();
    if (!getContext().getTheme().resolveAttribute(paramInt, (TypedValue)localObject, true)) {}
    ColorStateList localColorStateList;
    do
    {
      return null;
      localColorStateList = AppCompatResources.getColorStateList(getContext(), ((TypedValue)localObject).resourceId);
    } while (!getContext().getTheme().resolveAttribute(R.attr.colorPrimary, (TypedValue)localObject, true));
    paramInt = ((TypedValue)localObject).data;
    int i = localColorStateList.getDefaultColor();
    localObject = DISABLED_STATE_SET;
    int[] arrayOfInt1 = CHECKED_STATE_SET;
    int[] arrayOfInt2 = EMPTY_STATE_SET;
    int j = localColorStateList.getColorForState(DISABLED_STATE_SET, i);
    return new ColorStateList(new int[][] { localObject, arrayOfInt1, arrayOfInt2 }, new int[] { j, paramInt, i });
  }
  
  private MenuInflater getMenuInflater()
  {
    if (this.mMenuInflater == null) {
      this.mMenuInflater = new SupportMenuInflater(getContext());
    }
    return this.mMenuInflater;
  }
  
  @DrawableRes
  public int getItemBackgroundResource()
  {
    return this.mMenuView.getItemBackgroundRes();
  }
  
  @Nullable
  public ColorStateList getItemIconTintList()
  {
    return this.mMenuView.getIconTintList();
  }
  
  @Nullable
  public ColorStateList getItemTextColor()
  {
    return this.mMenuView.getItemTextColor();
  }
  
  public int getMaxItemCount()
  {
    return 5;
  }
  
  @NonNull
  public Menu getMenu()
  {
    return this.mMenu;
  }
  
  @IdRes
  public int getSelectedItemId()
  {
    return this.mMenuView.getSelectedItemId();
  }
  
  public void inflateMenu(int paramInt)
  {
    this.mPresenter.setUpdateSuspended(true);
    getMenuInflater().inflate(paramInt, this.mMenu);
    this.mPresenter.setUpdateSuspended(false);
    this.mPresenter.updateMenuView(true);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.mMenu.restorePresenterStates(paramParcelable.menuPresenterState);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.menuPresenterState = new Bundle();
    this.mMenu.savePresenterStates(localSavedState.menuPresenterState);
    return localSavedState;
  }
  
  public void setItemBackgroundResource(@DrawableRes int paramInt)
  {
    this.mMenuView.setItemBackgroundRes(paramInt);
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList)
  {
    this.mMenuView.setIconTintList(paramColorStateList);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList)
  {
    this.mMenuView.setItemTextColor(paramColorStateList);
  }
  
  public void setOnNavigationItemReselectedListener(@Nullable OnNavigationItemReselectedListener paramOnNavigationItemReselectedListener)
  {
    this.mReselectedListener = paramOnNavigationItemReselectedListener;
  }
  
  public void setOnNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener paramOnNavigationItemSelectedListener)
  {
    this.mSelectedListener = paramOnNavigationItemSelectedListener;
  }
  
  public void setSelectedItemId(@IdRes int paramInt)
  {
    MenuItem localMenuItem = this.mMenu.findItem(paramInt);
    if ((localMenuItem != null) && (!this.mMenu.performItemAction(localMenuItem, this.mPresenter, 0))) {
      localMenuItem.setChecked(true);
    }
  }
  
  public static abstract interface OnNavigationItemReselectedListener
  {
    public abstract void onNavigationItemReselected(@NonNull MenuItem paramMenuItem);
  }
  
  public static abstract interface OnNavigationItemSelectedListener
  {
    public abstract boolean onNavigationItemSelected(@NonNull MenuItem paramMenuItem);
  }
  
  static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
    {
      public BottomNavigationView.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new BottomNavigationView.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public BottomNavigationView.SavedState[] newArray(int paramAnonymousInt)
      {
        return new BottomNavigationView.SavedState[paramAnonymousInt];
      }
    });
    Bundle menuPresenterState;
    
    public SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      readFromParcel(paramParcel, paramClassLoader);
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    private void readFromParcel(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      this.menuPresenterState = paramParcel.readBundle(paramClassLoader);
    }
    
    public void writeToParcel(@NonNull Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeBundle(this.menuPresenterState);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\widget\BottomNavigationView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */