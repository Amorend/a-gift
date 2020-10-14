package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AppCompatSpinner
  extends Spinner
  implements TintableBackgroundView
{
  private static final int[] ATTRS_ANDROID_SPINNERMODE = { 16843505 };
  private static final int MAX_ITEMS_MEASURED = 15;
  private static final int MODE_DIALOG = 0;
  private static final int MODE_DROPDOWN = 1;
  private static final int MODE_THEME = -1;
  private static final String TAG = "AppCompatSpinner";
  private AppCompatBackgroundHelper mBackgroundTintHelper;
  int mDropDownWidth;
  private ForwardingListener mForwardingListener;
  DropdownPopup mPopup;
  private Context mPopupContext;
  private boolean mPopupSet;
  private SpinnerAdapter mTempAdapter;
  final Rect mTempRect;
  
  public AppCompatSpinner(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatSpinner(Context paramContext, int paramInt)
  {
    this(paramContext, null, R.attr.spinnerStyle, paramInt);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, null);
  }
  
  /* Error */
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, final Resources.Theme paramTheme)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial 79	android/widget/Spinner:<init>	(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new 81	android/graphics/Rect
    //   11: dup
    //   12: invokespecial 83	android/graphics/Rect:<init>	()V
    //   15: putfield 85	android/support/v7/widget/AppCompatSpinner:mTempRect	Landroid/graphics/Rect;
    //   18: aload_1
    //   19: aload_2
    //   20: getstatic 90	android/support/v7/appcompat/R$styleable:Spinner	[I
    //   23: iload_3
    //   24: iconst_0
    //   25: invokestatic 96	android/support/v7/widget/TintTypedArray:obtainStyledAttributes	(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   28: astore 10
    //   30: aload_0
    //   31: new 98	android/support/v7/widget/AppCompatBackgroundHelper
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 101	android/support/v7/widget/AppCompatBackgroundHelper:<init>	(Landroid/view/View;)V
    //   39: putfield 103	android/support/v7/widget/AppCompatSpinner:mBackgroundTintHelper	Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   42: aload 5
    //   44: ifnull +285 -> 329
    //   47: aload_0
    //   48: new 105	android/support/v7/view/ContextThemeWrapper
    //   51: dup
    //   52: aload_1
    //   53: aload 5
    //   55: invokespecial 108	android/support/v7/view/ContextThemeWrapper:<init>	(Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    //   58: putfield 110	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   61: aload_0
    //   62: getfield 110	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   65: ifnull +184 -> 249
    //   68: iload 4
    //   70: istore 7
    //   72: iload 4
    //   74: iconst_m1
    //   75: if_icmpne +71 -> 146
    //   78: getstatic 115	android/os/Build$VERSION:SDK_INT	I
    //   81: bipush 11
    //   83: if_icmplt +361 -> 444
    //   86: aload_1
    //   87: aload_2
    //   88: getstatic 55	android/support/v7/widget/AppCompatSpinner:ATTRS_ANDROID_SPINNERMODE	[I
    //   91: iload_3
    //   92: iconst_0
    //   93: invokevirtual 120	android/content/Context:obtainStyledAttributes	(Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   96: astore 8
    //   98: iload 4
    //   100: istore 6
    //   102: aload 8
    //   104: astore 5
    //   106: aload 8
    //   108: iconst_0
    //   109: invokevirtual 126	android/content/res/TypedArray:hasValue	(I)Z
    //   112: ifeq +16 -> 128
    //   115: aload 8
    //   117: astore 5
    //   119: aload 8
    //   121: iconst_0
    //   122: iconst_0
    //   123: invokevirtual 130	android/content/res/TypedArray:getInt	(II)I
    //   126: istore 6
    //   128: iload 6
    //   130: istore 7
    //   132: aload 8
    //   134: ifnull +12 -> 146
    //   137: aload 8
    //   139: invokevirtual 133	android/content/res/TypedArray:recycle	()V
    //   142: iload 6
    //   144: istore 7
    //   146: iload 7
    //   148: iconst_1
    //   149: if_icmpne +100 -> 249
    //   152: new 13	android/support/v7/widget/AppCompatSpinner$DropdownPopup
    //   155: dup
    //   156: aload_0
    //   157: aload_0
    //   158: getfield 110	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   161: aload_2
    //   162: iload_3
    //   163: invokespecial 136	android/support/v7/widget/AppCompatSpinner$DropdownPopup:<init>	(Landroid/support/v7/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   166: astore 5
    //   168: aload_0
    //   169: getfield 110	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   172: aload_2
    //   173: getstatic 90	android/support/v7/appcompat/R$styleable:Spinner	[I
    //   176: iload_3
    //   177: iconst_0
    //   178: invokestatic 96	android/support/v7/widget/TintTypedArray:obtainStyledAttributes	(Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroid/support/v7/widget/TintTypedArray;
    //   181: astore 8
    //   183: aload_0
    //   184: aload 8
    //   186: getstatic 139	android/support/v7/appcompat/R$styleable:Spinner_android_dropDownWidth	I
    //   189: bipush -2
    //   191: invokevirtual 142	android/support/v7/widget/TintTypedArray:getLayoutDimension	(II)I
    //   194: putfield 144	android/support/v7/widget/AppCompatSpinner:mDropDownWidth	I
    //   197: aload 5
    //   199: aload 8
    //   201: getstatic 147	android/support/v7/appcompat/R$styleable:Spinner_android_popupBackground	I
    //   204: invokevirtual 151	android/support/v7/widget/TintTypedArray:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   207: invokevirtual 155	android/support/v7/widget/AppCompatSpinner$DropdownPopup:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   210: aload 5
    //   212: aload 10
    //   214: getstatic 158	android/support/v7/appcompat/R$styleable:Spinner_android_prompt	I
    //   217: invokevirtual 162	android/support/v7/widget/TintTypedArray:getString	(I)Ljava/lang/String;
    //   220: invokevirtual 166	android/support/v7/widget/AppCompatSpinner$DropdownPopup:setPromptText	(Ljava/lang/CharSequence;)V
    //   223: aload 8
    //   225: invokevirtual 167	android/support/v7/widget/TintTypedArray:recycle	()V
    //   228: aload_0
    //   229: aload 5
    //   231: putfield 169	android/support/v7/widget/AppCompatSpinner:mPopup	Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;
    //   234: aload_0
    //   235: new 8	android/support/v7/widget/AppCompatSpinner$1
    //   238: dup
    //   239: aload_0
    //   240: aload_0
    //   241: aload 5
    //   243: invokespecial 172	android/support/v7/widget/AppCompatSpinner$1:<init>	(Landroid/support/v7/widget/AppCompatSpinner;Landroid/view/View;Landroid/support/v7/widget/AppCompatSpinner$DropdownPopup;)V
    //   246: putfield 174	android/support/v7/widget/AppCompatSpinner:mForwardingListener	Landroid/support/v7/widget/ForwardingListener;
    //   249: aload 10
    //   251: getstatic 177	android/support/v7/appcompat/R$styleable:Spinner_android_entries	I
    //   254: invokevirtual 181	android/support/v7/widget/TintTypedArray:getTextArray	(I)[Ljava/lang/CharSequence;
    //   257: astore 5
    //   259: aload 5
    //   261: ifnull +28 -> 289
    //   264: new 183	android/widget/ArrayAdapter
    //   267: dup
    //   268: aload_1
    //   269: ldc -72
    //   271: aload 5
    //   273: invokespecial 187	android/widget/ArrayAdapter:<init>	(Landroid/content/Context;I[Ljava/lang/Object;)V
    //   276: astore_1
    //   277: aload_1
    //   278: getstatic 192	android/support/v7/appcompat/R$layout:support_simple_spinner_dropdown_item	I
    //   281: invokevirtual 196	android/widget/ArrayAdapter:setDropDownViewResource	(I)V
    //   284: aload_0
    //   285: aload_1
    //   286: invokevirtual 200	android/support/v7/widget/AppCompatSpinner:setAdapter	(Landroid/widget/SpinnerAdapter;)V
    //   289: aload 10
    //   291: invokevirtual 167	android/support/v7/widget/TintTypedArray:recycle	()V
    //   294: aload_0
    //   295: iconst_1
    //   296: putfield 202	android/support/v7/widget/AppCompatSpinner:mPopupSet	Z
    //   299: aload_0
    //   300: getfield 204	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   303: ifnull +16 -> 319
    //   306: aload_0
    //   307: aload_0
    //   308: getfield 204	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   311: invokevirtual 200	android/support/v7/widget/AppCompatSpinner:setAdapter	(Landroid/widget/SpinnerAdapter;)V
    //   314: aload_0
    //   315: aconst_null
    //   316: putfield 204	android/support/v7/widget/AppCompatSpinner:mTempAdapter	Landroid/widget/SpinnerAdapter;
    //   319: aload_0
    //   320: getfield 103	android/support/v7/widget/AppCompatSpinner:mBackgroundTintHelper	Landroid/support/v7/widget/AppCompatBackgroundHelper;
    //   323: aload_2
    //   324: iload_3
    //   325: invokevirtual 208	android/support/v7/widget/AppCompatBackgroundHelper:loadFromAttributes	(Landroid/util/AttributeSet;I)V
    //   328: return
    //   329: aload 10
    //   331: getstatic 211	android/support/v7/appcompat/R$styleable:Spinner_popupTheme	I
    //   334: iconst_0
    //   335: invokevirtual 214	android/support/v7/widget/TintTypedArray:getResourceId	(II)I
    //   338: istore 6
    //   340: iload 6
    //   342: ifeq +20 -> 362
    //   345: aload_0
    //   346: new 105	android/support/v7/view/ContextThemeWrapper
    //   349: dup
    //   350: aload_1
    //   351: iload 6
    //   353: invokespecial 216	android/support/v7/view/ContextThemeWrapper:<init>	(Landroid/content/Context;I)V
    //   356: putfield 110	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   359: goto -298 -> 61
    //   362: getstatic 115	android/os/Build$VERSION:SDK_INT	I
    //   365: bipush 23
    //   367: if_icmpge +15 -> 382
    //   370: aload_1
    //   371: astore 5
    //   373: aload_0
    //   374: aload 5
    //   376: putfield 110	android/support/v7/widget/AppCompatSpinner:mPopupContext	Landroid/content/Context;
    //   379: goto -318 -> 61
    //   382: aconst_null
    //   383: astore 5
    //   385: goto -12 -> 373
    //   388: astore 9
    //   390: aconst_null
    //   391: astore 8
    //   393: aload 8
    //   395: astore 5
    //   397: ldc 35
    //   399: ldc -38
    //   401: aload 9
    //   403: invokestatic 224	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   406: pop
    //   407: iload 4
    //   409: istore 7
    //   411: aload 8
    //   413: ifnull -267 -> 146
    //   416: aload 8
    //   418: invokevirtual 133	android/content/res/TypedArray:recycle	()V
    //   421: iload 4
    //   423: istore 7
    //   425: goto -279 -> 146
    //   428: astore_1
    //   429: aconst_null
    //   430: astore 5
    //   432: aload 5
    //   434: ifnull +8 -> 442
    //   437: aload 5
    //   439: invokevirtual 133	android/content/res/TypedArray:recycle	()V
    //   442: aload_1
    //   443: athrow
    //   444: iconst_1
    //   445: istore 7
    //   447: goto -301 -> 146
    //   450: astore_1
    //   451: goto -19 -> 432
    //   454: astore 9
    //   456: goto -63 -> 393
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	459	0	this	AppCompatSpinner
    //   0	459	1	paramContext	Context
    //   0	459	2	paramAttributeSet	AttributeSet
    //   0	459	3	paramInt1	int
    //   0	459	4	paramInt2	int
    //   0	459	5	paramTheme	Resources.Theme
    //   100	252	6	i	int
    //   70	376	7	j	int
    //   96	321	8	localObject	Object
    //   388	14	9	localException1	Exception
    //   454	1	9	localException2	Exception
    //   28	302	10	localTintTypedArray	TintTypedArray
    // Exception table:
    //   from	to	target	type
    //   86	98	388	java/lang/Exception
    //   86	98	428	finally
    //   106	115	450	finally
    //   119	128	450	finally
    //   397	407	450	finally
    //   106	115	454	java/lang/Exception
    //   119	128	454	java/lang/Exception
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable)
  {
    if (paramSpinnerAdapter == null) {
      return 0;
    }
    int n = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int i1 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int i = Math.max(0, getSelectedItemPosition());
    int i2 = Math.min(paramSpinnerAdapter.getCount(), i + 15);
    int j = Math.max(0, i - (15 - (i2 - i)));
    View localView = null;
    int k = 0;
    i = 0;
    if (j < i2)
    {
      int m = paramSpinnerAdapter.getItemViewType(j);
      if (m == i) {
        break label204;
      }
      localView = null;
      i = m;
    }
    label204:
    for (;;)
    {
      localView = paramSpinnerAdapter.getView(j, localView, this);
      if (localView.getLayoutParams() == null) {
        localView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
      }
      localView.measure(n, i1);
      k = Math.max(k, localView.getMeasuredWidth());
      j += 1;
      break;
      if (paramDrawable != null)
      {
        paramDrawable.getPadding(this.mTempRect);
        return this.mTempRect.left + this.mTempRect.right + k;
      }
      return k;
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if (this.mBackgroundTintHelper != null) {
      this.mBackgroundTintHelper.applySupportBackgroundTint();
    }
  }
  
  public int getDropDownHorizontalOffset()
  {
    if (this.mPopup != null) {
      return this.mPopup.getHorizontalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownHorizontalOffset();
    }
    return 0;
  }
  
  public int getDropDownVerticalOffset()
  {
    if (this.mPopup != null) {
      return this.mPopup.getVerticalOffset();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownVerticalOffset();
    }
    return 0;
  }
  
  public int getDropDownWidth()
  {
    if (this.mPopup != null) {
      return this.mDropDownWidth;
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getDropDownWidth();
    }
    return 0;
  }
  
  public Drawable getPopupBackground()
  {
    if (this.mPopup != null) {
      return this.mPopup.getBackground();
    }
    if (Build.VERSION.SDK_INT >= 16) {
      return super.getPopupBackground();
    }
    return null;
  }
  
  public Context getPopupContext()
  {
    if (this.mPopup != null) {
      return this.mPopupContext;
    }
    if (Build.VERSION.SDK_INT >= 23) {
      return super.getPopupContext();
    }
    return null;
  }
  
  public CharSequence getPrompt()
  {
    if (this.mPopup != null) {
      return this.mPopup.getHintText();
    }
    return super.getPrompt();
  }
  
  @Nullable
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportBackgroundTintList()
  {
    if (this.mBackgroundTintHelper != null) {
      return this.mBackgroundTintHelper.getSupportBackgroundTintList();
    }
    return null;
  }
  
  @Nullable
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportBackgroundTintMode()
  {
    if (this.mBackgroundTintHelper != null) {
      return this.mBackgroundTintHelper.getSupportBackgroundTintMode();
    }
    return null;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if ((this.mPopup != null) && (this.mPopup.isShowing())) {
      this.mPopup.dismiss();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if ((this.mPopup != null) && (View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)) {
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight());
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mForwardingListener != null) && (this.mForwardingListener.onTouch(this, paramMotionEvent))) {
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick()
  {
    if (this.mPopup != null)
    {
      if (!this.mPopup.isShowing()) {
        this.mPopup.show();
      }
      return true;
    }
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    if (!this.mPopupSet) {
      this.mTempAdapter = paramSpinnerAdapter;
    }
    do
    {
      return;
      super.setAdapter(paramSpinnerAdapter);
    } while (this.mPopup == null);
    if (this.mPopupContext == null) {}
    for (Context localContext = getContext();; localContext = this.mPopupContext)
    {
      this.mPopup.setAdapter(new DropDownAdapter(paramSpinnerAdapter, localContext.getTheme()));
      return;
    }
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    super.setBackgroundDrawable(paramDrawable);
    if (this.mBackgroundTintHelper != null) {
      this.mBackgroundTintHelper.onSetBackgroundDrawable(paramDrawable);
    }
  }
  
  public void setBackgroundResource(@DrawableRes int paramInt)
  {
    super.setBackgroundResource(paramInt);
    if (this.mBackgroundTintHelper != null) {
      this.mBackgroundTintHelper.onSetBackgroundResource(paramInt);
    }
  }
  
  public void setDropDownHorizontalOffset(int paramInt)
  {
    if (this.mPopup != null) {
      this.mPopup.setHorizontalOffset(paramInt);
    }
    while (Build.VERSION.SDK_INT < 16) {
      return;
    }
    super.setDropDownHorizontalOffset(paramInt);
  }
  
  public void setDropDownVerticalOffset(int paramInt)
  {
    if (this.mPopup != null) {
      this.mPopup.setVerticalOffset(paramInt);
    }
    while (Build.VERSION.SDK_INT < 16) {
      return;
    }
    super.setDropDownVerticalOffset(paramInt);
  }
  
  public void setDropDownWidth(int paramInt)
  {
    if (this.mPopup != null) {
      this.mDropDownWidth = paramInt;
    }
    while (Build.VERSION.SDK_INT < 16) {
      return;
    }
    super.setDropDownWidth(paramInt);
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable)
  {
    if (this.mPopup != null) {
      this.mPopup.setBackgroundDrawable(paramDrawable);
    }
    while (Build.VERSION.SDK_INT < 16) {
      return;
    }
    super.setPopupBackgroundDrawable(paramDrawable);
  }
  
  public void setPopupBackgroundResource(@DrawableRes int paramInt)
  {
    setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence)
  {
    if (this.mPopup != null)
    {
      this.mPopup.setPromptText(paramCharSequence);
      return;
    }
    super.setPrompt(paramCharSequence);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList)
  {
    if (this.mBackgroundTintHelper != null) {
      this.mBackgroundTintHelper.setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode)
  {
    if (this.mBackgroundTintHelper != null) {
      this.mBackgroundTintHelper.setSupportBackgroundTintMode(paramMode);
    }
  }
  
  private static class DropDownAdapter
    implements ListAdapter, SpinnerAdapter
  {
    private SpinnerAdapter mAdapter;
    private ListAdapter mListAdapter;
    
    public DropDownAdapter(@Nullable SpinnerAdapter paramSpinnerAdapter, @Nullable Resources.Theme paramTheme)
    {
      this.mAdapter = paramSpinnerAdapter;
      if ((paramSpinnerAdapter instanceof ListAdapter)) {
        this.mListAdapter = ((ListAdapter)paramSpinnerAdapter);
      }
      if (paramTheme != null)
      {
        if ((Build.VERSION.SDK_INT < 23) || (!(paramSpinnerAdapter instanceof android.widget.ThemedSpinnerAdapter))) {
          break label66;
        }
        paramSpinnerAdapter = (android.widget.ThemedSpinnerAdapter)paramSpinnerAdapter;
        if (paramSpinnerAdapter.getDropDownViewTheme() != paramTheme) {
          paramSpinnerAdapter.setDropDownViewTheme(paramTheme);
        }
      }
      label66:
      do
      {
        do
        {
          return;
        } while (!(paramSpinnerAdapter instanceof ThemedSpinnerAdapter));
        paramSpinnerAdapter = (ThemedSpinnerAdapter)paramSpinnerAdapter;
      } while (paramSpinnerAdapter.getDropDownViewTheme() != null);
      paramSpinnerAdapter.setDropDownViewTheme(paramTheme);
    }
    
    public boolean areAllItemsEnabled()
    {
      ListAdapter localListAdapter = this.mListAdapter;
      if (localListAdapter != null) {
        return localListAdapter.areAllItemsEnabled();
      }
      return true;
    }
    
    public int getCount()
    {
      if (this.mAdapter == null) {
        return 0;
      }
      return this.mAdapter.getCount();
    }
    
    public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (this.mAdapter == null) {
        return null;
      }
      return this.mAdapter.getDropDownView(paramInt, paramView, paramViewGroup);
    }
    
    public Object getItem(int paramInt)
    {
      if (this.mAdapter == null) {
        return null;
      }
      return this.mAdapter.getItem(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      if (this.mAdapter == null) {
        return -1L;
      }
      return this.mAdapter.getItemId(paramInt);
    }
    
    public int getItemViewType(int paramInt)
    {
      return 0;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      return getDropDownView(paramInt, paramView, paramViewGroup);
    }
    
    public int getViewTypeCount()
    {
      return 1;
    }
    
    public boolean hasStableIds()
    {
      return (this.mAdapter != null) && (this.mAdapter.hasStableIds());
    }
    
    public boolean isEmpty()
    {
      return getCount() == 0;
    }
    
    public boolean isEnabled(int paramInt)
    {
      ListAdapter localListAdapter = this.mListAdapter;
      if (localListAdapter != null) {
        return localListAdapter.isEnabled(paramInt);
      }
      return true;
    }
    
    public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      if (this.mAdapter != null) {
        this.mAdapter.registerDataSetObserver(paramDataSetObserver);
      }
    }
    
    public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
    {
      if (this.mAdapter != null) {
        this.mAdapter.unregisterDataSetObserver(paramDataSetObserver);
      }
    }
  }
  
  private class DropdownPopup
    extends ListPopupWindow
  {
    ListAdapter mAdapter;
    private CharSequence mHintText;
    private final Rect mVisibleRect = new Rect();
    
    public DropdownPopup(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      setAnchorView(AppCompatSpinner.this);
      setModal(true);
      setPromptPosition(0);
      setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          AppCompatSpinner.this.setSelection(paramAnonymousInt);
          if (AppCompatSpinner.this.getOnItemClickListener() != null) {
            AppCompatSpinner.this.performItemClick(paramAnonymousView, paramAnonymousInt, AppCompatSpinner.DropdownPopup.this.mAdapter.getItemId(paramAnonymousInt));
          }
          AppCompatSpinner.DropdownPopup.this.dismiss();
        }
      });
    }
    
    void computeContentWidth()
    {
      Object localObject = getBackground();
      int i;
      int m;
      int n;
      int i1;
      int j;
      if (localObject != null)
      {
        ((Drawable)localObject).getPadding(AppCompatSpinner.this.mTempRect);
        if (ViewUtils.isLayoutRtl(AppCompatSpinner.this))
        {
          i = AppCompatSpinner.this.mTempRect.right;
          m = AppCompatSpinner.this.getPaddingLeft();
          n = AppCompatSpinner.this.getPaddingRight();
          i1 = AppCompatSpinner.this.getWidth();
          if (AppCompatSpinner.this.mDropDownWidth != -2) {
            break label240;
          }
          j = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, getBackground());
          int k = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
          if (j <= k) {
            break label288;
          }
          j = k;
        }
      }
      label165:
      label240:
      label288:
      for (;;)
      {
        setContentWidth(Math.max(j, i1 - m - n));
        if (ViewUtils.isLayoutRtl(AppCompatSpinner.this)) {
          i = i1 - n - getWidth() + i;
        }
        for (;;)
        {
          setHorizontalOffset(i);
          return;
          i = -AppCompatSpinner.this.mTempRect.left;
          break;
          localObject = AppCompatSpinner.this.mTempRect;
          AppCompatSpinner.this.mTempRect.right = 0;
          ((Rect)localObject).left = 0;
          i = 0;
          break;
          if (AppCompatSpinner.this.mDropDownWidth == -1)
          {
            setContentWidth(i1 - m - n);
            break label165;
          }
          setContentWidth(AppCompatSpinner.this.mDropDownWidth);
          break label165;
          i += m;
        }
      }
    }
    
    public CharSequence getHintText()
    {
      return this.mHintText;
    }
    
    boolean isVisibleToUser(View paramView)
    {
      return (ViewCompat.isAttachedToWindow(paramView)) && (paramView.getGlobalVisibleRect(this.mVisibleRect));
    }
    
    public void setAdapter(ListAdapter paramListAdapter)
    {
      super.setAdapter(paramListAdapter);
      this.mAdapter = paramListAdapter;
    }
    
    public void setPromptText(CharSequence paramCharSequence)
    {
      this.mHintText = paramCharSequence;
    }
    
    public void show()
    {
      boolean bool = isShowing();
      computeContentWidth();
      setInputMethodMode(2);
      super.show();
      getListView().setChoiceMode(1);
      setSelection(AppCompatSpinner.this.getSelectedItemPosition());
      if (bool) {}
      ViewTreeObserver localViewTreeObserver;
      do
      {
        return;
        localViewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
      } while (localViewTreeObserver == null);
      final ViewTreeObserver.OnGlobalLayoutListener local2 = new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          if (!AppCompatSpinner.DropdownPopup.this.isVisibleToUser(AppCompatSpinner.this))
          {
            AppCompatSpinner.DropdownPopup.this.dismiss();
            return;
          }
          AppCompatSpinner.DropdownPopup.this.computeContentWidth();
          AppCompatSpinner.DropdownPopup.this.show();
        }
      };
      localViewTreeObserver.addOnGlobalLayoutListener(local2);
      setOnDismissListener(new PopupWindow.OnDismissListener()
      {
        public void onDismiss()
        {
          ViewTreeObserver localViewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
          if (localViewTreeObserver != null) {
            localViewTreeObserver.removeGlobalOnLayoutListener(local2);
          }
        }
      });
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\AppCompatSpinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */