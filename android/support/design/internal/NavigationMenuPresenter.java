package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.R.dimen;
import android.support.design.R.layout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuPresenter.Callback;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationMenuPresenter
  implements MenuPresenter
{
  private static final String STATE_ADAPTER = "android:menu:adapter";
  private static final String STATE_HEADER = "android:menu:header";
  private static final String STATE_HIERARCHY = "android:menu:list";
  NavigationMenuAdapter mAdapter;
  private MenuPresenter.Callback mCallback;
  LinearLayout mHeaderLayout;
  ColorStateList mIconTintList;
  private int mId;
  Drawable mItemBackground;
  LayoutInflater mLayoutInflater;
  MenuBuilder mMenu;
  private NavigationMenuView mMenuView;
  final View.OnClickListener mOnClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = (NavigationMenuItemView)paramAnonymousView;
      NavigationMenuPresenter.this.setUpdateSuspended(true);
      paramAnonymousView = paramAnonymousView.getItemData();
      boolean bool = NavigationMenuPresenter.this.mMenu.performItemAction(paramAnonymousView, NavigationMenuPresenter.this, 0);
      if ((paramAnonymousView != null) && (paramAnonymousView.isCheckable()) && (bool)) {
        NavigationMenuPresenter.this.mAdapter.setCheckedItem(paramAnonymousView);
      }
      NavigationMenuPresenter.this.setUpdateSuspended(false);
      NavigationMenuPresenter.this.updateMenuView(false);
    }
  };
  int mPaddingSeparator;
  private int mPaddingTopDefault;
  int mTextAppearance;
  boolean mTextAppearanceSet;
  ColorStateList mTextColor;
  
  public void addHeaderView(@NonNull View paramView)
  {
    this.mHeaderLayout.addView(paramView);
    this.mMenuView.setPadding(0, 0, 0, this.mMenuView.getPaddingBottom());
  }
  
  public boolean collapseItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public void dispatchApplyWindowInsets(WindowInsetsCompat paramWindowInsetsCompat)
  {
    int i = paramWindowInsetsCompat.getSystemWindowInsetTop();
    if (this.mPaddingTopDefault != i)
    {
      this.mPaddingTopDefault = i;
      if (this.mHeaderLayout.getChildCount() == 0) {
        this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
      }
    }
    ViewCompat.dispatchApplyWindowInsets(this.mHeaderLayout, paramWindowInsetsCompat);
  }
  
  public boolean expandItemActionView(MenuBuilder paramMenuBuilder, MenuItemImpl paramMenuItemImpl)
  {
    return false;
  }
  
  public boolean flagActionItems()
  {
    return false;
  }
  
  public int getHeaderCount()
  {
    return this.mHeaderLayout.getChildCount();
  }
  
  public View getHeaderView(int paramInt)
  {
    return this.mHeaderLayout.getChildAt(paramInt);
  }
  
  public int getId()
  {
    return this.mId;
  }
  
  @Nullable
  public Drawable getItemBackground()
  {
    return this.mItemBackground;
  }
  
  @Nullable
  public ColorStateList getItemTextColor()
  {
    return this.mTextColor;
  }
  
  @Nullable
  public ColorStateList getItemTintList()
  {
    return this.mIconTintList;
  }
  
  public MenuView getMenuView(ViewGroup paramViewGroup)
  {
    if (this.mMenuView == null)
    {
      this.mMenuView = ((NavigationMenuView)this.mLayoutInflater.inflate(R.layout.design_navigation_menu, paramViewGroup, false));
      if (this.mAdapter == null) {
        this.mAdapter = new NavigationMenuAdapter();
      }
      this.mHeaderLayout = ((LinearLayout)this.mLayoutInflater.inflate(R.layout.design_navigation_item_header, this.mMenuView, false));
      this.mMenuView.setAdapter(this.mAdapter);
    }
    return this.mMenuView;
  }
  
  public View inflateHeaderView(@LayoutRes int paramInt)
  {
    View localView = this.mLayoutInflater.inflate(paramInt, this.mHeaderLayout, false);
    addHeaderView(localView);
    return localView;
  }
  
  public void initForMenu(Context paramContext, MenuBuilder paramMenuBuilder)
  {
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mMenu = paramMenuBuilder;
    this.mPaddingSeparator = paramContext.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
  }
  
  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    if (this.mCallback != null) {
      this.mCallback.onCloseMenu(paramMenuBuilder, paramBoolean);
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      paramParcelable = (Bundle)paramParcelable;
      Object localObject = paramParcelable.getSparseParcelableArray("android:menu:list");
      if (localObject != null) {
        this.mMenuView.restoreHierarchyState((SparseArray)localObject);
      }
      localObject = paramParcelable.getBundle("android:menu:adapter");
      if (localObject != null) {
        this.mAdapter.restoreInstanceState((Bundle)localObject);
      }
      paramParcelable = paramParcelable.getSparseParcelableArray("android:menu:header");
      if (paramParcelable != null) {
        this.mHeaderLayout.restoreHierarchyState(paramParcelable);
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      Bundle localBundle = new Bundle();
      SparseArray localSparseArray;
      if (this.mMenuView != null)
      {
        localSparseArray = new SparseArray();
        this.mMenuView.saveHierarchyState(localSparseArray);
        localBundle.putSparseParcelableArray("android:menu:list", localSparseArray);
      }
      if (this.mAdapter != null) {
        localBundle.putBundle("android:menu:adapter", this.mAdapter.createInstanceState());
      }
      if (this.mHeaderLayout != null)
      {
        localSparseArray = new SparseArray();
        this.mHeaderLayout.saveHierarchyState(localSparseArray);
        localBundle.putSparseParcelableArray("android:menu:header", localSparseArray);
      }
      return localBundle;
    }
    return null;
  }
  
  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    return false;
  }
  
  public void removeHeaderView(@NonNull View paramView)
  {
    this.mHeaderLayout.removeView(paramView);
    if (this.mHeaderLayout.getChildCount() == 0) {
      this.mMenuView.setPadding(0, this.mPaddingTopDefault, 0, this.mMenuView.getPaddingBottom());
    }
  }
  
  public void setCallback(MenuPresenter.Callback paramCallback)
  {
    this.mCallback = paramCallback;
  }
  
  public void setCheckedItem(MenuItemImpl paramMenuItemImpl)
  {
    this.mAdapter.setCheckedItem(paramMenuItemImpl);
  }
  
  public void setId(int paramInt)
  {
    this.mId = paramInt;
  }
  
  public void setItemBackground(@Nullable Drawable paramDrawable)
  {
    this.mItemBackground = paramDrawable;
    updateMenuView(false);
  }
  
  public void setItemIconTintList(@Nullable ColorStateList paramColorStateList)
  {
    this.mIconTintList = paramColorStateList;
    updateMenuView(false);
  }
  
  public void setItemTextAppearance(@StyleRes int paramInt)
  {
    this.mTextAppearance = paramInt;
    this.mTextAppearanceSet = true;
    updateMenuView(false);
  }
  
  public void setItemTextColor(@Nullable ColorStateList paramColorStateList)
  {
    this.mTextColor = paramColorStateList;
    updateMenuView(false);
  }
  
  public void setUpdateSuspended(boolean paramBoolean)
  {
    if (this.mAdapter != null) {
      this.mAdapter.setUpdateSuspended(paramBoolean);
    }
  }
  
  public void updateMenuView(boolean paramBoolean)
  {
    if (this.mAdapter != null) {
      this.mAdapter.update();
    }
  }
  
  private static class HeaderViewHolder
    extends NavigationMenuPresenter.ViewHolder
  {
    public HeaderViewHolder(View paramView)
    {
      super();
    }
  }
  
  private class NavigationMenuAdapter
    extends RecyclerView.Adapter<NavigationMenuPresenter.ViewHolder>
  {
    private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
    private static final String STATE_CHECKED_ITEM = "android:menu:checked";
    private static final int VIEW_TYPE_HEADER = 3;
    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_SEPARATOR = 2;
    private static final int VIEW_TYPE_SUBHEADER = 1;
    private MenuItemImpl mCheckedItem;
    private final ArrayList<NavigationMenuPresenter.NavigationMenuItem> mItems = new ArrayList();
    private boolean mUpdateSuspended;
    
    NavigationMenuAdapter()
    {
      prepareMenuItems();
    }
    
    private void appendTransparentIconIfMissing(int paramInt1, int paramInt2)
    {
      while (paramInt1 < paramInt2)
      {
        ((NavigationMenuPresenter.NavigationMenuTextItem)this.mItems.get(paramInt1)).needsEmptyIcon = true;
        paramInt1 += 1;
      }
    }
    
    private void prepareMenuItems()
    {
      if (this.mUpdateSuspended) {
        return;
      }
      this.mUpdateSuspended = true;
      this.mItems.clear();
      this.mItems.add(new NavigationMenuPresenter.NavigationMenuHeaderItem());
      int j = -1;
      int i = 0;
      boolean bool1 = false;
      int i2 = NavigationMenuPresenter.this.mMenu.getVisibleItems().size();
      int n = 0;
      Object localObject;
      int k;
      boolean bool2;
      for (;;)
      {
        if (n < i2)
        {
          localObject = (MenuItemImpl)NavigationMenuPresenter.this.mMenu.getVisibleItems().get(n);
          if (((MenuItemImpl)localObject).isChecked()) {
            setCheckedItem((MenuItemImpl)localObject);
          }
          if (((MenuItemImpl)localObject).isCheckable()) {
            ((MenuItemImpl)localObject).setExclusiveCheckable(false);
          }
          if (((MenuItemImpl)localObject).hasSubMenu())
          {
            SubMenu localSubMenu = ((MenuItemImpl)localObject).getSubMenu();
            if (localSubMenu.hasVisibleItems())
            {
              if (n != 0) {
                this.mItems.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, 0));
              }
              this.mItems.add(new NavigationMenuPresenter.NavigationMenuTextItem((MenuItemImpl)localObject));
              k = 0;
              int i3 = this.mItems.size();
              int i4 = localSubMenu.size();
              int i1 = 0;
              while (i1 < i4)
              {
                MenuItemImpl localMenuItemImpl = (MenuItemImpl)localSubMenu.getItem(i1);
                int m = k;
                if (localMenuItemImpl.isVisible())
                {
                  m = k;
                  if (k == 0)
                  {
                    m = k;
                    if (localMenuItemImpl.getIcon() != null) {
                      m = 1;
                    }
                  }
                  if (localMenuItemImpl.isCheckable()) {
                    localMenuItemImpl.setExclusiveCheckable(false);
                  }
                  if (((MenuItemImpl)localObject).isChecked()) {
                    setCheckedItem((MenuItemImpl)localObject);
                  }
                  this.mItems.add(new NavigationMenuPresenter.NavigationMenuTextItem(localMenuItemImpl));
                }
                i1 += 1;
                k = m;
              }
              if (k != 0) {
                appendTransparentIconIfMissing(i3, this.mItems.size());
              }
            }
            k = j;
            j = i;
            i = k;
            n += 1;
            k = i;
            i = j;
            j = k;
          }
          else
          {
            k = ((MenuItemImpl)localObject).getGroupId();
            if (k != j)
            {
              j = this.mItems.size();
              if (((MenuItemImpl)localObject).getIcon() != null)
              {
                bool2 = true;
                label386:
                bool1 = bool2;
                i = j;
                if (n != 0)
                {
                  i = j + 1;
                  this.mItems.add(new NavigationMenuPresenter.NavigationMenuSeparatorItem(NavigationMenuPresenter.this.mPaddingSeparator, NavigationMenuPresenter.this.mPaddingSeparator));
                  bool1 = bool2;
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        localObject = new NavigationMenuPresenter.NavigationMenuTextItem((MenuItemImpl)localObject);
        ((NavigationMenuPresenter.NavigationMenuTextItem)localObject).needsEmptyIcon = bool1;
        this.mItems.add(localObject);
        j = i;
        i = k;
        break;
        bool2 = false;
        break label386;
        if ((!bool1) && (((MenuItemImpl)localObject).getIcon() != null))
        {
          bool1 = true;
          appendTransparentIconIfMissing(i, this.mItems.size());
          continue;
          this.mUpdateSuspended = false;
          return;
        }
      }
    }
    
    public Bundle createInstanceState()
    {
      Bundle localBundle = new Bundle();
      if (this.mCheckedItem != null) {
        localBundle.putInt("android:menu:checked", this.mCheckedItem.getItemId());
      }
      SparseArray localSparseArray = new SparseArray();
      int j = this.mItems.size();
      int i = 0;
      if (i < j)
      {
        Object localObject = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(i);
        MenuItemImpl localMenuItemImpl;
        if ((localObject instanceof NavigationMenuPresenter.NavigationMenuTextItem))
        {
          localMenuItemImpl = ((NavigationMenuPresenter.NavigationMenuTextItem)localObject).getMenuItem();
          if (localMenuItemImpl == null) {
            break label131;
          }
        }
        label131:
        for (localObject = localMenuItemImpl.getActionView();; localObject = null)
        {
          if (localObject != null)
          {
            ParcelableSparseArray localParcelableSparseArray = new ParcelableSparseArray();
            ((View)localObject).saveHierarchyState(localParcelableSparseArray);
            localSparseArray.put(localMenuItemImpl.getItemId(), localParcelableSparseArray);
          }
          i += 1;
          break;
        }
      }
      localBundle.putSparseParcelableArray("android:menu:action_views", localSparseArray);
      return localBundle;
    }
    
    public int getItemCount()
    {
      return this.mItems.size();
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public int getItemViewType(int paramInt)
    {
      NavigationMenuPresenter.NavigationMenuItem localNavigationMenuItem = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(paramInt);
      if ((localNavigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuSeparatorItem)) {
        return 2;
      }
      if ((localNavigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuHeaderItem)) {
        return 3;
      }
      if ((localNavigationMenuItem instanceof NavigationMenuPresenter.NavigationMenuTextItem))
      {
        if (((NavigationMenuPresenter.NavigationMenuTextItem)localNavigationMenuItem).getMenuItem().hasSubMenu()) {
          return 1;
        }
        return 0;
      }
      throw new RuntimeException("Unknown item type.");
    }
    
    public void onBindViewHolder(NavigationMenuPresenter.ViewHolder paramViewHolder, int paramInt)
    {
      switch (getItemViewType(paramInt))
      {
      default: 
        return;
      case 0: 
        localObject = (NavigationMenuItemView)paramViewHolder.itemView;
        ((NavigationMenuItemView)localObject).setIconTintList(NavigationMenuPresenter.this.mIconTintList);
        if (NavigationMenuPresenter.this.mTextAppearanceSet) {
          ((NavigationMenuItemView)localObject).setTextAppearance(NavigationMenuPresenter.this.mTextAppearance);
        }
        if (NavigationMenuPresenter.this.mTextColor != null) {
          ((NavigationMenuItemView)localObject).setTextColor(NavigationMenuPresenter.this.mTextColor);
        }
        if (NavigationMenuPresenter.this.mItemBackground != null) {}
        for (paramViewHolder = NavigationMenuPresenter.this.mItemBackground.getConstantState().newDrawable();; paramViewHolder = null)
        {
          ViewCompat.setBackground((View)localObject, paramViewHolder);
          paramViewHolder = (NavigationMenuPresenter.NavigationMenuTextItem)this.mItems.get(paramInt);
          ((NavigationMenuItemView)localObject).setNeedsEmptyIcon(paramViewHolder.needsEmptyIcon);
          ((NavigationMenuItemView)localObject).initialize(paramViewHolder.getMenuItem(), 0);
          return;
        }
      case 1: 
        ((TextView)paramViewHolder.itemView).setText(((NavigationMenuPresenter.NavigationMenuTextItem)this.mItems.get(paramInt)).getMenuItem().getTitle());
        return;
      }
      Object localObject = (NavigationMenuPresenter.NavigationMenuSeparatorItem)this.mItems.get(paramInt);
      paramViewHolder.itemView.setPadding(0, ((NavigationMenuPresenter.NavigationMenuSeparatorItem)localObject).getPaddingTop(), 0, ((NavigationMenuPresenter.NavigationMenuSeparatorItem)localObject).getPaddingBottom());
    }
    
    public NavigationMenuPresenter.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        return new NavigationMenuPresenter.NormalViewHolder(NavigationMenuPresenter.this.mLayoutInflater, paramViewGroup, NavigationMenuPresenter.this.mOnClickListener);
      case 1: 
        return new NavigationMenuPresenter.SubheaderViewHolder(NavigationMenuPresenter.this.mLayoutInflater, paramViewGroup);
      case 2: 
        return new NavigationMenuPresenter.SeparatorViewHolder(NavigationMenuPresenter.this.mLayoutInflater, paramViewGroup);
      }
      return new NavigationMenuPresenter.HeaderViewHolder(NavigationMenuPresenter.this.mHeaderLayout);
    }
    
    public void onViewRecycled(NavigationMenuPresenter.ViewHolder paramViewHolder)
    {
      if ((paramViewHolder instanceof NavigationMenuPresenter.NormalViewHolder)) {
        ((NavigationMenuItemView)paramViewHolder.itemView).recycle();
      }
    }
    
    public void restoreInstanceState(Bundle paramBundle)
    {
      int j = 0;
      int k = paramBundle.getInt("android:menu:checked", 0);
      int i;
      Object localObject1;
      if (k != 0)
      {
        this.mUpdateSuspended = true;
        int m = this.mItems.size();
        i = 0;
        if (i < m)
        {
          localObject1 = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(i);
          if ((localObject1 instanceof NavigationMenuPresenter.NavigationMenuTextItem))
          {
            localObject1 = ((NavigationMenuPresenter.NavigationMenuTextItem)localObject1).getMenuItem();
            if ((localObject1 != null) && (((MenuItemImpl)localObject1).getItemId() == k)) {
              setCheckedItem((MenuItemImpl)localObject1);
            }
          }
        }
        else
        {
          this.mUpdateSuspended = false;
          prepareMenuItems();
        }
      }
      else
      {
        paramBundle = paramBundle.getSparseParcelableArray("android:menu:action_views");
        if (paramBundle == null) {
          return;
        }
        k = this.mItems.size();
        i = j;
        label121:
        if (i >= k) {
          return;
        }
        localObject1 = (NavigationMenuPresenter.NavigationMenuItem)this.mItems.get(i);
        if ((localObject1 instanceof NavigationMenuPresenter.NavigationMenuTextItem)) {
          break label162;
        }
      }
      for (;;)
      {
        i += 1;
        break label121;
        i += 1;
        break;
        label162:
        Object localObject2 = ((NavigationMenuPresenter.NavigationMenuTextItem)localObject1).getMenuItem();
        if (localObject2 != null)
        {
          localObject1 = ((MenuItemImpl)localObject2).getActionView();
          if (localObject1 != null)
          {
            localObject2 = (ParcelableSparseArray)paramBundle.get(((MenuItemImpl)localObject2).getItemId());
            if (localObject2 != null) {
              ((View)localObject1).restoreHierarchyState((SparseArray)localObject2);
            }
          }
        }
      }
    }
    
    public void setCheckedItem(MenuItemImpl paramMenuItemImpl)
    {
      if ((this.mCheckedItem == paramMenuItemImpl) || (!paramMenuItemImpl.isCheckable())) {
        return;
      }
      if (this.mCheckedItem != null) {
        this.mCheckedItem.setChecked(false);
      }
      this.mCheckedItem = paramMenuItemImpl;
      paramMenuItemImpl.setChecked(true);
    }
    
    public void setUpdateSuspended(boolean paramBoolean)
    {
      this.mUpdateSuspended = paramBoolean;
    }
    
    public void update()
    {
      prepareMenuItems();
      notifyDataSetChanged();
    }
  }
  
  private static class NavigationMenuHeaderItem
    implements NavigationMenuPresenter.NavigationMenuItem
  {}
  
  private static abstract interface NavigationMenuItem {}
  
  private static class NavigationMenuSeparatorItem
    implements NavigationMenuPresenter.NavigationMenuItem
  {
    private final int mPaddingBottom;
    private final int mPaddingTop;
    
    public NavigationMenuSeparatorItem(int paramInt1, int paramInt2)
    {
      this.mPaddingTop = paramInt1;
      this.mPaddingBottom = paramInt2;
    }
    
    public int getPaddingBottom()
    {
      return this.mPaddingBottom;
    }
    
    public int getPaddingTop()
    {
      return this.mPaddingTop;
    }
  }
  
  private static class NavigationMenuTextItem
    implements NavigationMenuPresenter.NavigationMenuItem
  {
    private final MenuItemImpl mMenuItem;
    boolean needsEmptyIcon;
    
    NavigationMenuTextItem(MenuItemImpl paramMenuItemImpl)
    {
      this.mMenuItem = paramMenuItemImpl;
    }
    
    public MenuItemImpl getMenuItem()
    {
      return this.mMenuItem;
    }
  }
  
  private static class NormalViewHolder
    extends NavigationMenuPresenter.ViewHolder
  {
    public NormalViewHolder(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, View.OnClickListener paramOnClickListener)
    {
      super();
      this.itemView.setOnClickListener(paramOnClickListener);
    }
  }
  
  private static class SeparatorViewHolder
    extends NavigationMenuPresenter.ViewHolder
  {
    public SeparatorViewHolder(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup)
    {
      super();
    }
  }
  
  private static class SubheaderViewHolder
    extends NavigationMenuPresenter.ViewHolder
  {
    public SubheaderViewHolder(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup)
    {
      super();
    }
  }
  
  private static abstract class ViewHolder
    extends RecyclerView.ViewHolder
  {
    public ViewHolder(View paramView)
    {
      super();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\design\internal\NavigationMenuPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */