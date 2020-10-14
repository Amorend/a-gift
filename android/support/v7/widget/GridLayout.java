package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.widget.Space;
import android.support.v7.gridlayout.R.dimen;
import android.support.v7.gridlayout.R.styleable;
import android.util.AttributeSet;
import android.util.LogPrinter;
import android.util.Pair;
import android.util.Printer;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GridLayout
  extends ViewGroup
{
  private static final int ALIGNMENT_MODE;
  public static final int ALIGN_BOUNDS = 0;
  public static final int ALIGN_MARGINS = 1;
  public static final Alignment BASELINE = new Alignment()
  {
    public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (paramAnonymousView.getVisibility() == 8) {
        paramAnonymousInt1 = 0;
      }
      do
      {
        return paramAnonymousInt1;
        paramAnonymousInt2 = paramAnonymousView.getBaseline();
        paramAnonymousInt1 = paramAnonymousInt2;
      } while (paramAnonymousInt2 != -1);
      return Integer.MIN_VALUE;
    }
    
    public GridLayout.Bounds getBounds()
    {
      new GridLayout.Bounds()
      {
        private int size;
        
        protected int getOffset(GridLayout paramAnonymous2GridLayout, View paramAnonymous2View, GridLayout.Alignment paramAnonymous2Alignment, int paramAnonymous2Int, boolean paramAnonymous2Boolean)
        {
          return Math.max(0, super.getOffset(paramAnonymous2GridLayout, paramAnonymous2View, paramAnonymous2Alignment, paramAnonymous2Int, paramAnonymous2Boolean));
        }
        
        protected void include(int paramAnonymous2Int1, int paramAnonymous2Int2)
        {
          super.include(paramAnonymous2Int1, paramAnonymous2Int2);
          this.size = Math.max(this.size, paramAnonymous2Int1 + paramAnonymous2Int2);
        }
        
        protected void reset()
        {
          super.reset();
          this.size = Integer.MIN_VALUE;
        }
        
        protected int size(boolean paramAnonymous2Boolean)
        {
          return Math.max(super.size(paramAnonymous2Boolean), this.size);
        }
      };
    }
    
    String getDebugString()
    {
      return "BASELINE";
    }
    
    int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
    {
      return 0;
    }
  };
  public static final Alignment BOTTOM;
  static final int CAN_STRETCH = 2;
  public static final Alignment CENTER;
  private static final int COLUMN_COUNT;
  private static final int COLUMN_ORDER_PRESERVED;
  private static final int DEFAULT_ALIGNMENT_MODE = 1;
  static final int DEFAULT_CONTAINER_MARGIN = 0;
  private static final int DEFAULT_COUNT = Integer.MIN_VALUE;
  static final boolean DEFAULT_ORDER_PRESERVED = true;
  private static final int DEFAULT_ORIENTATION = 0;
  private static final boolean DEFAULT_USE_DEFAULT_MARGINS = false;
  public static final Alignment END;
  public static final Alignment FILL = new Alignment()
  {
    public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return Integer.MIN_VALUE;
    }
    
    String getDebugString()
    {
      return "FILL";
    }
    
    int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
    {
      return 0;
    }
    
    public int getSizeInCell(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return paramAnonymousInt2;
    }
  };
  public static final int HORIZONTAL = 0;
  static final int INFLEXIBLE = 0;
  private static final Alignment LEADING;
  public static final Alignment LEFT;
  static final Printer LOG_PRINTER = new LogPrinter(3, GridLayout.class.getName());
  static final int MAX_SIZE = 100000;
  static final Printer NO_PRINTER = new Printer()
  {
    public void println(String paramAnonymousString) {}
  };
  private static final int ORIENTATION = R.styleable.GridLayout_orientation;
  public static final Alignment RIGHT;
  private static final int ROW_COUNT = R.styleable.GridLayout_rowCount;
  private static final int ROW_ORDER_PRESERVED;
  public static final Alignment START;
  public static final Alignment TOP;
  private static final Alignment TRAILING;
  public static final int UNDEFINED = Integer.MIN_VALUE;
  static final Alignment UNDEFINED_ALIGNMENT;
  static final int UNINITIALIZED_HASH = 0;
  private static final int USE_DEFAULT_MARGINS;
  public static final int VERTICAL = 1;
  int mAlignmentMode = 1;
  int mDefaultGap;
  final Axis mHorizontalAxis = new Axis(true);
  int mLastLayoutParamsHashCode = 0;
  int mOrientation = 0;
  Printer mPrinter = LOG_PRINTER;
  boolean mUseDefaultMargins = false;
  final Axis mVerticalAxis = new Axis(false);
  
  static
  {
    COLUMN_COUNT = R.styleable.GridLayout_columnCount;
    USE_DEFAULT_MARGINS = R.styleable.GridLayout_useDefaultMargins;
    ALIGNMENT_MODE = R.styleable.GridLayout_alignmentMode;
    ROW_ORDER_PRESERVED = R.styleable.GridLayout_rowOrderPreserved;
    COLUMN_ORDER_PRESERVED = R.styleable.GridLayout_columnOrderPreserved;
    UNDEFINED_ALIGNMENT = new Alignment()
    {
      public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return Integer.MIN_VALUE;
      }
      
      String getDebugString()
      {
        return "UNDEFINED";
      }
      
      int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
      {
        return Integer.MIN_VALUE;
      }
    };
    LEADING = new Alignment()
    {
      public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return 0;
      }
      
      String getDebugString()
      {
        return "LEADING";
      }
      
      int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
      {
        return 0;
      }
    };
    TRAILING = new Alignment()
    {
      public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return paramAnonymousInt1;
      }
      
      String getDebugString()
      {
        return "TRAILING";
      }
      
      int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
      {
        return paramAnonymousInt;
      }
    };
    TOP = LEADING;
    BOTTOM = TRAILING;
    START = LEADING;
    END = TRAILING;
    LEFT = createSwitchingAlignment(START, END);
    RIGHT = createSwitchingAlignment(END, START);
    CENTER = new Alignment()
    {
      public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return paramAnonymousInt1 >> 1;
      }
      
      String getDebugString()
      {
        return "CENTER";
      }
      
      int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
      {
        return paramAnonymousInt >> 1;
      }
    };
  }
  
  public GridLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public GridLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public GridLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mDefaultGap = paramContext.getResources().getDimensionPixelOffset(R.dimen.default_gap);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.GridLayout);
    try
    {
      setRowCount(paramContext.getInt(ROW_COUNT, Integer.MIN_VALUE));
      setColumnCount(paramContext.getInt(COLUMN_COUNT, Integer.MIN_VALUE));
      setOrientation(paramContext.getInt(ORIENTATION, 0));
      setUseDefaultMargins(paramContext.getBoolean(USE_DEFAULT_MARGINS, false));
      setAlignmentMode(paramContext.getInt(ALIGNMENT_MODE, 1));
      setRowOrderPreserved(paramContext.getBoolean(ROW_ORDER_PRESERVED, true));
      setColumnOrderPreserved(paramContext.getBoolean(COLUMN_ORDER_PRESERVED, true));
      return;
    }
    finally
    {
      paramContext.recycle();
    }
  }
  
  static int adjust(int paramInt1, int paramInt2)
  {
    return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt1 + paramInt2), View.MeasureSpec.getMode(paramInt1));
  }
  
  static <T> T[] append(T[] paramArrayOfT1, T[] paramArrayOfT2)
  {
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT1.getClass().getComponentType(), paramArrayOfT1.length + paramArrayOfT2.length);
    System.arraycopy(paramArrayOfT1, 0, arrayOfObject, 0, paramArrayOfT1.length);
    System.arraycopy(paramArrayOfT2, 0, arrayOfObject, paramArrayOfT1.length, paramArrayOfT2.length);
    return arrayOfObject;
  }
  
  static boolean canStretch(int paramInt)
  {
    return (paramInt & 0x2) != 0;
  }
  
  private void checkLayoutParams(LayoutParams paramLayoutParams, boolean paramBoolean)
  {
    String str;
    label18:
    Interval localInterval;
    if (paramBoolean)
    {
      str = "column";
      if (!paramBoolean) {
        break label183;
      }
      paramLayoutParams = paramLayoutParams.columnSpec;
      localInterval = paramLayoutParams.span;
      if ((localInterval.min != Integer.MIN_VALUE) && (localInterval.min < 0)) {
        handleInvalidParams(str + " indices must be positive");
      }
      if (!paramBoolean) {
        break label191;
      }
    }
    label183:
    label191:
    for (paramLayoutParams = this.mHorizontalAxis;; paramLayoutParams = this.mVerticalAxis)
    {
      int i = paramLayoutParams.definedCount;
      if (i != Integer.MIN_VALUE)
      {
        if (localInterval.max > i) {
          handleInvalidParams(str + " indices (start + span) mustn't exceed the " + str + " count");
        }
        if (localInterval.size() > i) {
          handleInvalidParams(str + " span mustn't exceed the " + str + " count");
        }
      }
      return;
      str = "row";
      break;
      paramLayoutParams = paramLayoutParams.rowSpec;
      break label18;
    }
  }
  
  private static int clip(Interval paramInterval, boolean paramBoolean, int paramInt)
  {
    int j = paramInterval.size();
    if (paramInt == 0) {
      return j;
    }
    if (paramBoolean) {}
    for (int i = Math.min(paramInterval.min, paramInt);; i = 0) {
      return Math.min(j, paramInt - i);
    }
  }
  
  private int computeLayoutParamsHashCode()
  {
    int i = 1;
    int k = getChildCount();
    int j = 0;
    if (j < k)
    {
      View localView = getChildAt(j);
      if (localView.getVisibility() == 8) {}
      for (;;)
      {
        j += 1;
        break;
        i = ((LayoutParams)localView.getLayoutParams()).hashCode() + i * 31;
      }
    }
    return i;
  }
  
  private void consistencyCheck()
  {
    if (this.mLastLayoutParamsHashCode == 0)
    {
      validateLayoutParams();
      this.mLastLayoutParamsHashCode = computeLayoutParamsHashCode();
    }
    while (this.mLastLayoutParamsHashCode == computeLayoutParamsHashCode()) {
      return;
    }
    this.mPrinter.println("The fields of some layout parameters were modified in between layout operations. Check the javadoc for GridLayout.LayoutParams#rowSpec.");
    invalidateStructure();
    consistencyCheck();
  }
  
  private static Alignment createSwitchingAlignment(Alignment paramAlignment1, final Alignment paramAlignment2)
  {
    new Alignment()
    {
      public int getAlignmentValue(View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        int i = 1;
        if (ViewCompat.getLayoutDirection(paramAnonymousView) == 1) {
          if (i != 0) {
            break label37;
          }
        }
        label37:
        for (GridLayout.Alignment localAlignment = this.val$ltr;; localAlignment = paramAlignment2)
        {
          return localAlignment.getAlignmentValue(paramAnonymousView, paramAnonymousInt1, paramAnonymousInt2);
          i = 0;
          break;
        }
      }
      
      String getDebugString()
      {
        return "SWITCHING[L:" + this.val$ltr.getDebugString() + ", R:" + paramAlignment2.getDebugString() + "]";
      }
      
      int getGravityOffset(View paramAnonymousView, int paramAnonymousInt)
      {
        int i = 1;
        if (ViewCompat.getLayoutDirection(paramAnonymousView) == 1) {
          if (i != 0) {
            break label33;
          }
        }
        label33:
        for (GridLayout.Alignment localAlignment = this.val$ltr;; localAlignment = paramAlignment2)
        {
          return localAlignment.getGravityOffset(paramAnonymousView, paramAnonymousInt);
          i = 0;
          break;
        }
      }
    };
  }
  
  private void drawLine(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Paint paramPaint)
  {
    if (isLayoutRtlCompat())
    {
      int i = getWidth();
      paramCanvas.drawLine(i - paramInt1, paramInt2, i - paramInt3, paramInt4, paramPaint);
      return;
    }
    paramCanvas.drawLine(paramInt1, paramInt2, paramInt3, paramInt4, paramPaint);
  }
  
  private static boolean fits(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 > paramArrayOfInt.length) {
      return false;
    }
    do
    {
      paramInt2 += 1;
      if (paramInt2 >= paramInt3) {
        break;
      }
    } while (paramArrayOfInt[paramInt2] <= paramInt1);
    return false;
    return true;
  }
  
  static Alignment getAlignment(int paramInt, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
    {
      i = 7;
      label7:
      if (!paramBoolean) {
        break label86;
      }
    }
    label86:
    for (int j = 0;; j = 4) {
      switch ((i & paramInt) >> j)
      {
      default: 
        return UNDEFINED_ALIGNMENT;
        i = 112;
        break label7;
      }
    }
    if (paramBoolean) {
      return LEFT;
    }
    return TOP;
    if (paramBoolean) {
      return RIGHT;
    }
    return BOTTOM;
    return FILL;
    return CENTER;
    return START;
    return END;
  }
  
  private int getDefaultMargin(View paramView, LayoutParams paramLayoutParams, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!this.mUseDefaultMargins) {
      return 0;
    }
    Axis localAxis;
    label28:
    boolean bool;
    if (paramBoolean1)
    {
      paramLayoutParams = paramLayoutParams.columnSpec;
      if (!paramBoolean1) {
        break label86;
      }
      localAxis = this.mHorizontalAxis;
      paramLayoutParams = paramLayoutParams.span;
      if ((!paramBoolean1) || (!isLayoutRtlCompat())) {
        break label101;
      }
      if (paramBoolean2) {
        break label95;
      }
      bool = true;
      label52:
      if (!bool) {
        break label114;
      }
      if (paramLayoutParams.min != 0) {
        break label108;
      }
      bool = true;
    }
    for (;;)
    {
      return getDefaultMargin(paramView, bool, paramBoolean1, paramBoolean2);
      paramLayoutParams = paramLayoutParams.rowSpec;
      break;
      label86:
      localAxis = this.mVerticalAxis;
      break label28;
      label95:
      bool = false;
      break label52;
      label101:
      bool = paramBoolean2;
      break label52;
      label108:
      bool = false;
      continue;
      label114:
      if (paramLayoutParams.max == localAxis.getCount()) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
  
  private int getDefaultMargin(View paramView, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramView.getClass() == Space.class) {
      return 0;
    }
    return this.mDefaultGap / 2;
  }
  
  private int getDefaultMargin(View paramView, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    return getDefaultMargin(paramView, paramBoolean2, paramBoolean3);
  }
  
  private int getMargin(View paramView, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mAlignmentMode == 1) {
      return getMargin1(paramView, paramBoolean1, paramBoolean2);
    }
    Object localObject;
    if (paramBoolean1)
    {
      localObject = this.mHorizontalAxis;
      if (!paramBoolean2) {
        break label80;
      }
      localObject = ((Axis)localObject).getLeadingMargins();
      label37:
      paramView = getLayoutParams(paramView);
      if (!paramBoolean1) {
        break label90;
      }
      paramView = paramView.columnSpec;
      label52:
      if (!paramBoolean2) {
        break label98;
      }
    }
    label80:
    label90:
    label98:
    for (int i = paramView.span.min;; i = paramView.span.max)
    {
      return localObject[i];
      localObject = this.mVerticalAxis;
      break;
      localObject = ((Axis)localObject).getTrailingMargins();
      break label37;
      paramView = paramView.rowSpec;
      break label52;
    }
  }
  
  private int getMeasurement(View paramView, boolean paramBoolean)
  {
    if (paramBoolean) {
      return paramView.getMeasuredWidth();
    }
    return paramView.getMeasuredHeight();
  }
  
  private int getTotalMargin(View paramView, boolean paramBoolean)
  {
    return getMargin(paramView, paramBoolean, true) + getMargin(paramView, paramBoolean, false);
  }
  
  static void handleInvalidParams(String paramString)
  {
    throw new IllegalArgumentException(paramString + ". ");
  }
  
  private void invalidateStructure()
  {
    this.mLastLayoutParamsHashCode = 0;
    if (this.mHorizontalAxis != null) {
      this.mHorizontalAxis.invalidateStructure();
    }
    if (this.mVerticalAxis != null) {
      this.mVerticalAxis.invalidateStructure();
    }
    invalidateValues();
  }
  
  private void invalidateValues()
  {
    if ((this.mHorizontalAxis != null) && (this.mVerticalAxis != null))
    {
      this.mHorizontalAxis.invalidateValues();
      this.mVerticalAxis.invalidateValues();
    }
  }
  
  private boolean isLayoutRtlCompat()
  {
    return ViewCompat.getLayoutDirection(this) == 1;
  }
  
  static int max2(int[] paramArrayOfInt, int paramInt)
  {
    int j = 0;
    int k = paramArrayOfInt.length;
    int i = paramInt;
    paramInt = j;
    while (paramInt < k)
    {
      i = Math.max(i, paramArrayOfInt[paramInt]);
      paramInt += 1;
    }
    return i;
  }
  
  private void measureChildWithMargins2(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.measure(getChildMeasureSpec(paramInt1, getTotalMargin(paramView, true), paramInt3), getChildMeasureSpec(paramInt2, getTotalMargin(paramView, false), paramInt4));
  }
  
  private void measureChildrenWithMargins(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = getChildAt(i);
      if (localView.getVisibility() == 8) {}
      for (;;)
      {
        i += 1;
        break;
        LayoutParams localLayoutParams = getLayoutParams(localView);
        if (paramBoolean)
        {
          measureChildWithMargins2(localView, paramInt1, paramInt2, localLayoutParams.width, localLayoutParams.height);
        }
        else
        {
          boolean bool;
          label86:
          label98:
          Interval localInterval;
          if (this.mOrientation == 0)
          {
            bool = true;
            if (!bool) {
              break label193;
            }
            localObject = localLayoutParams.columnSpec;
            if (((Spec)localObject).getAbsoluteAlignment(bool) != FILL) {
              break label201;
            }
            localInterval = ((Spec)localObject).span;
            if (!bool) {
              break label203;
            }
          }
          int k;
          label193:
          label201:
          label203:
          for (Object localObject = this.mHorizontalAxis;; localObject = this.mVerticalAxis)
          {
            localObject = ((Axis)localObject).getLocations();
            k = localObject[localInterval.max] - localObject[localInterval.min] - getTotalMargin(localView, bool);
            if (!bool) {
              break label212;
            }
            measureChildWithMargins2(localView, paramInt1, paramInt2, k, localLayoutParams.height);
            break;
            bool = false;
            break label86;
            localObject = localLayoutParams.rowSpec;
            break label98;
            break;
          }
          label212:
          measureChildWithMargins2(localView, paramInt1, paramInt2, localLayoutParams.width, k);
        }
      }
    }
  }
  
  private static void procrusteanFill(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramArrayOfInt.length;
    Arrays.fill(paramArrayOfInt, Math.min(paramInt1, i), Math.min(paramInt2, i), paramInt3);
  }
  
  private static void setCellGroup(LayoutParams paramLayoutParams, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramLayoutParams.setRowSpecSpan(new Interval(paramInt1, paramInt1 + paramInt2));
    paramLayoutParams.setColumnSpecSpan(new Interval(paramInt3, paramInt3 + paramInt4));
  }
  
  public static Spec spec(int paramInt)
  {
    return spec(paramInt, 1);
  }
  
  public static Spec spec(int paramInt, float paramFloat)
  {
    return spec(paramInt, 1, paramFloat);
  }
  
  public static Spec spec(int paramInt1, int paramInt2)
  {
    return spec(paramInt1, paramInt2, UNDEFINED_ALIGNMENT);
  }
  
  public static Spec spec(int paramInt1, int paramInt2, float paramFloat)
  {
    return spec(paramInt1, paramInt2, UNDEFINED_ALIGNMENT, paramFloat);
  }
  
  public static Spec spec(int paramInt1, int paramInt2, Alignment paramAlignment)
  {
    return spec(paramInt1, paramInt2, paramAlignment, 0.0F);
  }
  
  public static Spec spec(int paramInt1, int paramInt2, Alignment paramAlignment, float paramFloat)
  {
    if (paramInt1 != Integer.MIN_VALUE) {}
    for (boolean bool = true;; bool = false) {
      return new Spec(bool, paramInt1, paramInt2, paramAlignment, paramFloat);
    }
  }
  
  public static Spec spec(int paramInt, Alignment paramAlignment)
  {
    return spec(paramInt, 1, paramAlignment);
  }
  
  public static Spec spec(int paramInt, Alignment paramAlignment, float paramFloat)
  {
    return spec(paramInt, 1, paramAlignment, paramFloat);
  }
  
  private void validateLayoutParams()
  {
    int i2;
    Object localObject;
    label21:
    int i3;
    label38:
    int[] arrayOfInt;
    int i4;
    int j;
    int i;
    LayoutParams localLayoutParams;
    label90:
    boolean bool1;
    int i6;
    label134:
    boolean bool2;
    int i7;
    if (this.mOrientation == 0)
    {
      i2 = 1;
      if (i2 == 0) {
        break label248;
      }
      localObject = this.mHorizontalAxis;
      if (((Axis)localObject).definedCount == Integer.MIN_VALUE) {
        break label257;
      }
      i3 = ((Axis)localObject).definedCount;
      arrayOfInt = new int[i3];
      int i5 = getChildCount();
      i4 = 0;
      j = 0;
      i = 0;
      if (i4 >= i5) {
        break label379;
      }
      localLayoutParams = (LayoutParams)getChildAt(i4).getLayoutParams();
      if (i2 == 0) {
        break label263;
      }
      localObject = localLayoutParams.rowSpec;
      Interval localInterval = ((Spec)localObject).span;
      bool1 = ((Spec)localObject).startDefined;
      i6 = localInterval.size();
      if (bool1) {
        i = localInterval.min;
      }
      if (i2 == 0) {
        break label273;
      }
      localObject = localLayoutParams.columnSpec;
      localInterval = ((Spec)localObject).span;
      bool2 = ((Spec)localObject).startDefined;
      i7 = clip(localInterval, bool2, i3);
      if (!bool2) {
        break label380;
      }
      j = localInterval.min;
    }
    label248:
    label257:
    label263:
    label273:
    label310:
    label379:
    label380:
    for (;;)
    {
      int n = j;
      int k = i;
      if (i3 != 0)
      {
        k = j;
        int m = i;
        int i1;
        if (bool1)
        {
          n = j;
          i1 = i;
          if (!bool2)
          {
            m = i;
            k = j;
          }
        }
        else
        {
          for (;;)
          {
            n = k;
            i1 = m;
            if (fits(arrayOfInt, m, k, k + i7)) {
              break label310;
            }
            if (bool2)
            {
              m += 1;
              continue;
              i2 = 0;
              break;
              localObject = this.mVerticalAxis;
              break label21;
              i3 = 0;
              break label38;
              localObject = localLayoutParams.columnSpec;
              break label90;
              localObject = localLayoutParams.rowSpec;
              break label134;
            }
            if (k + i7 <= i3)
            {
              k += 1;
            }
            else
            {
              m += 1;
              k = 0;
            }
          }
        }
        procrusteanFill(arrayOfInt, n, n + i7, i1 + i6);
        k = i1;
      }
      if (i2 != 0) {
        setCellGroup(localLayoutParams, k, i6, n, i7);
      }
      for (;;)
      {
        j = n + i7;
        i4 += 1;
        i = k;
        break;
        setCellGroup(localLayoutParams, n, i7, k, i6);
      }
      return;
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (!(paramLayoutParams instanceof LayoutParams)) {
      return false;
    }
    paramLayoutParams = (LayoutParams)paramLayoutParams;
    checkLayoutParams(paramLayoutParams, true);
    checkLayoutParams(paramLayoutParams, false);
    return true;
  }
  
  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LayoutParams)) {
      return new LayoutParams((LayoutParams)paramLayoutParams);
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getAlignmentMode()
  {
    return this.mAlignmentMode;
  }
  
  public int getColumnCount()
  {
    return this.mHorizontalAxis.getCount();
  }
  
  final LayoutParams getLayoutParams(View paramView)
  {
    return (LayoutParams)paramView.getLayoutParams();
  }
  
  int getMargin1(View paramView, boolean paramBoolean1, boolean paramBoolean2)
  {
    LayoutParams localLayoutParams = getLayoutParams(paramView);
    int i;
    if (paramBoolean1) {
      if (paramBoolean2) {
        i = localLayoutParams.leftMargin;
      }
    }
    for (;;)
    {
      int j = i;
      if (i == Integer.MIN_VALUE) {
        j = getDefaultMargin(paramView, localLayoutParams, paramBoolean1, paramBoolean2);
      }
      return j;
      i = localLayoutParams.rightMargin;
      continue;
      if (paramBoolean2) {
        i = localLayoutParams.topMargin;
      } else {
        i = localLayoutParams.bottomMargin;
      }
    }
  }
  
  final int getMeasurementIncludingMargin(View paramView, boolean paramBoolean)
  {
    if (paramView.getVisibility() == 8) {
      return 0;
    }
    return getMeasurement(paramView, paramBoolean) + getTotalMargin(paramView, paramBoolean);
  }
  
  public int getOrientation()
  {
    return this.mOrientation;
  }
  
  public Printer getPrinter()
  {
    return this.mPrinter;
  }
  
  public int getRowCount()
  {
    return this.mVerticalAxis.getCount();
  }
  
  public boolean getUseDefaultMargins()
  {
    return this.mUseDefaultMargins;
  }
  
  public boolean isColumnOrderPreserved()
  {
    return this.mHorizontalAxis.isOrderPreserved();
  }
  
  public boolean isRowOrderPreserved()
  {
    return this.mVerticalAxis.isOrderPreserved();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    consistencyCheck();
    paramInt3 -= paramInt1;
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = getPaddingRight();
    paramInt1 = getPaddingBottom();
    this.mHorizontalAxis.layout(paramInt3 - i - k);
    this.mVerticalAxis.layout(paramInt4 - paramInt2 - j - paramInt1);
    int[] arrayOfInt1 = this.mHorizontalAxis.getLocations();
    int[] arrayOfInt2 = this.mVerticalAxis.getLocations();
    paramInt4 = getChildCount();
    paramInt1 = 0;
    while (paramInt1 < paramInt4)
    {
      View localView = getChildAt(paramInt1);
      if (localView.getVisibility() == 8)
      {
        paramInt1 += 1;
      }
      else
      {
        Object localObject2 = getLayoutParams(localView);
        Object localObject1 = ((LayoutParams)localObject2).columnSpec;
        localObject2 = ((LayoutParams)localObject2).rowSpec;
        Object localObject3 = ((Spec)localObject1).span;
        Object localObject4 = ((Spec)localObject2).span;
        paramInt2 = arrayOfInt1[localObject3.min];
        int m = arrayOfInt2[localObject4.min];
        int i1 = arrayOfInt1[localObject3.max];
        int n = arrayOfInt2[localObject4.max];
        int i5 = i1 - paramInt2;
        int i7 = n - m;
        int i10 = getMeasurement(localView, true);
        int i8 = getMeasurement(localView, false);
        localObject1 = ((Spec)localObject1).getAbsoluteAlignment(true);
        localObject2 = ((Spec)localObject2).getAbsoluteAlignment(false);
        localObject3 = (Bounds)this.mHorizontalAxis.getGroupBounds().getValue(paramInt1);
        localObject4 = (Bounds)this.mVerticalAxis.getGroupBounds().getValue(paramInt1);
        int i6 = ((Alignment)localObject1).getGravityOffset(localView, i5 - ((Bounds)localObject3).size(true));
        n = ((Alignment)localObject2).getGravityOffset(localView, i7 - ((Bounds)localObject4).size(true));
        int i3 = getMargin(localView, true, true);
        i1 = getMargin(localView, false, true);
        int i4 = getMargin(localView, true, false);
        int i2 = getMargin(localView, false, false);
        int i11 = i3 + i4;
        int i12 = i1 + i2;
        int i9 = ((Bounds)localObject3).getOffset(this, localView, (Alignment)localObject1, i10 + i11, true);
        i2 = ((Bounds)localObject4).getOffset(this, localView, (Alignment)localObject2, i8 + i12, false);
        i5 = ((Alignment)localObject1).getSizeInCell(localView, i10, i5 - i11);
        i7 = ((Alignment)localObject2).getSizeInCell(localView, i8, i7 - i12);
        paramInt2 = i9 + (paramInt2 + i6);
        if (!isLayoutRtlCompat()) {
          paramInt2 += i + i3;
        }
        for (;;)
        {
          m = i2 + (j + m + n) + i1;
          if ((i5 != localView.getMeasuredWidth()) || (i7 != localView.getMeasuredHeight())) {
            localView.measure(View.MeasureSpec.makeMeasureSpec(i5, 1073741824), View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
          }
          localView.layout(paramInt2, m, i5 + paramInt2, i7 + m);
          break;
          paramInt2 = paramInt3 - i5 - k - i4 - paramInt2;
        }
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    consistencyCheck();
    invalidateValues();
    int m = getPaddingLeft() + getPaddingRight();
    int k = getPaddingTop() + getPaddingBottom();
    int n = adjust(paramInt1, -m);
    int i1 = adjust(paramInt2, -k);
    measureChildrenWithMargins(n, i1, true);
    int j;
    int i;
    if (this.mOrientation == 0)
    {
      j = this.mHorizontalAxis.getMeasure(n);
      measureChildrenWithMargins(n, i1, false);
      i = this.mVerticalAxis.getMeasure(i1);
    }
    for (;;)
    {
      j = Math.max(j + m, getSuggestedMinimumWidth());
      i = Math.max(i + k, getSuggestedMinimumHeight());
      setMeasuredDimension(ViewCompat.resolveSizeAndState(j, paramInt1, 0), ViewCompat.resolveSizeAndState(i, paramInt2, 0));
      return;
      i = this.mVerticalAxis.getMeasure(i1);
      measureChildrenWithMargins(n, i1, false);
      j = this.mHorizontalAxis.getMeasure(n);
    }
  }
  
  public void requestLayout()
  {
    super.requestLayout();
    invalidateStructure();
  }
  
  public void setAlignmentMode(int paramInt)
  {
    this.mAlignmentMode = paramInt;
    requestLayout();
  }
  
  public void setColumnCount(int paramInt)
  {
    this.mHorizontalAxis.setCount(paramInt);
    invalidateStructure();
    requestLayout();
  }
  
  public void setColumnOrderPreserved(boolean paramBoolean)
  {
    this.mHorizontalAxis.setOrderPreserved(paramBoolean);
    invalidateStructure();
    requestLayout();
  }
  
  public void setOrientation(int paramInt)
  {
    if (this.mOrientation != paramInt)
    {
      this.mOrientation = paramInt;
      invalidateStructure();
      requestLayout();
    }
  }
  
  public void setPrinter(Printer paramPrinter)
  {
    Printer localPrinter = paramPrinter;
    if (paramPrinter == null) {
      localPrinter = NO_PRINTER;
    }
    this.mPrinter = localPrinter;
  }
  
  public void setRowCount(int paramInt)
  {
    this.mVerticalAxis.setCount(paramInt);
    invalidateStructure();
    requestLayout();
  }
  
  public void setRowOrderPreserved(boolean paramBoolean)
  {
    this.mVerticalAxis.setOrderPreserved(paramBoolean);
    invalidateStructure();
    requestLayout();
  }
  
  public void setUseDefaultMargins(boolean paramBoolean)
  {
    this.mUseDefaultMargins = paramBoolean;
    requestLayout();
  }
  
  public static abstract class Alignment
  {
    abstract int getAlignmentValue(View paramView, int paramInt1, int paramInt2);
    
    GridLayout.Bounds getBounds()
    {
      return new GridLayout.Bounds();
    }
    
    abstract String getDebugString();
    
    abstract int getGravityOffset(View paramView, int paramInt);
    
    int getSizeInCell(View paramView, int paramInt1, int paramInt2)
    {
      return paramInt1;
    }
    
    public String toString()
    {
      return "Alignment:" + getDebugString();
    }
  }
  
  static final class Arc
  {
    public final GridLayout.Interval span;
    public boolean valid = true;
    public final GridLayout.MutableInt value;
    
    public Arc(GridLayout.Interval paramInterval, GridLayout.MutableInt paramMutableInt)
    {
      this.span = paramInterval;
      this.value = paramMutableInt;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder().append(this.span).append(" ");
      if (!this.valid) {}
      for (String str = "+>";; str = "->") {
        return str + " " + this.value;
      }
    }
  }
  
  static final class Assoc<K, V>
    extends ArrayList<Pair<K, V>>
  {
    private final Class<K> keyType;
    private final Class<V> valueType;
    
    private Assoc(Class<K> paramClass, Class<V> paramClass1)
    {
      this.keyType = paramClass;
      this.valueType = paramClass1;
    }
    
    public static <K, V> Assoc<K, V> of(Class<K> paramClass, Class<V> paramClass1)
    {
      return new Assoc(paramClass, paramClass1);
    }
    
    public GridLayout.PackedMap<K, V> pack()
    {
      int j = size();
      Object[] arrayOfObject1 = (Object[])Array.newInstance(this.keyType, j);
      Object[] arrayOfObject2 = (Object[])Array.newInstance(this.valueType, j);
      int i = 0;
      while (i < j)
      {
        arrayOfObject1[i] = ((Pair)get(i)).first;
        arrayOfObject2[i] = ((Pair)get(i)).second;
        i += 1;
      }
      return new GridLayout.PackedMap(arrayOfObject1, arrayOfObject2);
    }
    
    public void put(K paramK, V paramV)
    {
      add(Pair.create(paramK, paramV));
    }
  }
  
  final class Axis
  {
    static final int COMPLETE = 2;
    static final int NEW = 0;
    static final int PENDING = 1;
    public GridLayout.Arc[] arcs;
    public boolean arcsValid = false;
    GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> backwardLinks;
    public boolean backwardLinksValid = false;
    public int definedCount = Integer.MIN_VALUE;
    public int[] deltas;
    GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> forwardLinks;
    public boolean forwardLinksValid = false;
    GridLayout.PackedMap<GridLayout.Spec, GridLayout.Bounds> groupBounds;
    public boolean groupBoundsValid = false;
    public boolean hasWeights;
    public boolean hasWeightsValid = false;
    public final boolean horizontal;
    public int[] leadingMargins;
    public boolean leadingMarginsValid = false;
    public int[] locations;
    public boolean locationsValid = false;
    private int maxIndex = Integer.MIN_VALUE;
    boolean orderPreserved = true;
    private GridLayout.MutableInt parentMax = new GridLayout.MutableInt(-100000);
    private GridLayout.MutableInt parentMin = new GridLayout.MutableInt(0);
    public int[] trailingMargins;
    public boolean trailingMarginsValid = false;
    
    static
    {
      if (!GridLayout.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    Axis(boolean paramBoolean)
    {
      this.horizontal = paramBoolean;
    }
    
    private void addComponentSizes(List<GridLayout.Arc> paramList, GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> paramPackedMap)
    {
      int i = 0;
      while (i < ((GridLayout.Interval[])paramPackedMap.keys).length)
      {
        include(paramList, ((GridLayout.Interval[])paramPackedMap.keys)[i], ((GridLayout.MutableInt[])paramPackedMap.values)[i], false);
        i += 1;
      }
    }
    
    private String arcsToString(List<GridLayout.Arc> paramList)
    {
      String str;
      label33:
      label61:
      int j;
      int k;
      int m;
      if (this.horizontal)
      {
        str = "x";
        localObject = new StringBuilder();
        Iterator localIterator = paramList.iterator();
        paramList = (List<GridLayout.Arc>)localObject;
        int i = 1;
        if (!localIterator.hasNext()) {
          break label219;
        }
        localObject = (GridLayout.Arc)localIterator.next();
        if (i == 0) {
          break label159;
        }
        i = 0;
        j = ((GridLayout.Arc)localObject).span.min;
        k = ((GridLayout.Arc)localObject).span.max;
        m = ((GridLayout.Arc)localObject).value.value;
        if (j >= k) {
          break label169;
        }
      }
      label159:
      label169:
      for (Object localObject = str + k + "-" + str + j + ">=" + m;; localObject = str + j + "-" + str + k + "<=" + -m)
      {
        paramList.append((String)localObject);
        break label33;
        str = "y";
        break;
        paramList = paramList.append(", ");
        break label61;
      }
      label219:
      return paramList.toString();
    }
    
    private int calculateMaxIndex()
    {
      int k = GridLayout.this.getChildCount();
      int i = 0;
      int j = -1;
      if (i < k)
      {
        Object localObject = GridLayout.this.getChildAt(i);
        localObject = GridLayout.this.getLayoutParams((View)localObject);
        if (this.horizontal) {}
        for (localObject = ((GridLayout.LayoutParams)localObject).columnSpec;; localObject = ((GridLayout.LayoutParams)localObject).rowSpec)
        {
          localObject = ((GridLayout.Spec)localObject).span;
          j = Math.max(Math.max(Math.max(j, ((GridLayout.Interval)localObject).min), ((GridLayout.Interval)localObject).max), ((GridLayout.Interval)localObject).size());
          i += 1;
          break;
        }
      }
      if (j == -1) {
        return Integer.MIN_VALUE;
      }
      return j;
    }
    
    private float calculateTotalWeight()
    {
      float f = 0.0F;
      int j = GridLayout.this.getChildCount();
      int i = 0;
      while (i < j)
      {
        Object localObject = GridLayout.this.getChildAt(i);
        if (((View)localObject).getVisibility() == 8)
        {
          i += 1;
        }
        else
        {
          localObject = GridLayout.this.getLayoutParams((View)localObject);
          if (this.horizontal) {}
          for (localObject = ((GridLayout.LayoutParams)localObject).columnSpec;; localObject = ((GridLayout.LayoutParams)localObject).rowSpec)
          {
            f = ((GridLayout.Spec)localObject).weight + f;
            break;
          }
        }
      }
      return f;
    }
    
    private void computeArcs()
    {
      getForwardLinks();
      getBackwardLinks();
    }
    
    private void computeGroupBounds()
    {
      Object localObject = (GridLayout.Bounds[])this.groupBounds.values;
      int i = 0;
      while (i < localObject.length)
      {
        localObject[i].reset();
        i += 1;
      }
      int k = GridLayout.this.getChildCount();
      i = 0;
      if (i < k)
      {
        View localView = GridLayout.this.getChildAt(i);
        localObject = GridLayout.this.getLayoutParams(localView);
        label85:
        int m;
        if (this.horizontal)
        {
          localObject = ((GridLayout.LayoutParams)localObject).columnSpec;
          m = GridLayout.this.getMeasurementIncludingMargin(localView, this.horizontal);
          if (((GridLayout.Spec)localObject).weight != 0.0F) {
            break label156;
          }
        }
        label156:
        for (int j = 0;; j = getDeltas()[i])
        {
          ((GridLayout.Bounds)this.groupBounds.getValue(i)).include(GridLayout.this, localView, (GridLayout.Spec)localObject, this, m + j);
          i += 1;
          break;
          localObject = ((GridLayout.LayoutParams)localObject).rowSpec;
          break label85;
        }
      }
    }
    
    private boolean computeHasWeights()
    {
      int j = GridLayout.this.getChildCount();
      int i = 0;
      if (i < j)
      {
        Object localObject = GridLayout.this.getChildAt(i);
        if (((View)localObject).getVisibility() == 8) {}
        for (;;)
        {
          i += 1;
          break;
          localObject = GridLayout.this.getLayoutParams((View)localObject);
          if (this.horizontal) {}
          for (localObject = ((GridLayout.LayoutParams)localObject).columnSpec; ((GridLayout.Spec)localObject).weight != 0.0F; localObject = ((GridLayout.LayoutParams)localObject).rowSpec) {
            return true;
          }
        }
      }
      return false;
    }
    
    private void computeLinks(GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> paramPackedMap, boolean paramBoolean)
    {
      int j = 0;
      Object localObject = (GridLayout.MutableInt[])paramPackedMap.values;
      int i = 0;
      while (i < localObject.length)
      {
        localObject[i].reset();
        i += 1;
      }
      localObject = (GridLayout.Bounds[])getGroupBounds().values;
      i = j;
      if (i < localObject.length)
      {
        j = localObject[i].size(paramBoolean);
        GridLayout.MutableInt localMutableInt = (GridLayout.MutableInt)paramPackedMap.getValue(i);
        int k = localMutableInt.value;
        if (paramBoolean) {}
        for (;;)
        {
          localMutableInt.value = Math.max(k, j);
          i += 1;
          break;
          j = -j;
        }
      }
    }
    
    private void computeLocations(int[] paramArrayOfInt)
    {
      int i = 0;
      if (!hasWeights()) {
        solve(paramArrayOfInt);
      }
      while (!this.orderPreserved)
      {
        int j = paramArrayOfInt[0];
        int k = paramArrayOfInt.length;
        while (i < k)
        {
          paramArrayOfInt[i] -= j;
          i += 1;
        }
        solveAndDistributeSpace(paramArrayOfInt);
      }
    }
    
    private void computeMargins(boolean paramBoolean)
    {
      if (paramBoolean) {}
      View localView;
      for (int[] arrayOfInt = this.leadingMargins;; arrayOfInt = this.trailingMargins)
      {
        int k = GridLayout.this.getChildCount();
        int i = 0;
        for (;;)
        {
          if (i >= k) {
            return;
          }
          localView = GridLayout.this.getChildAt(i);
          if (localView.getVisibility() != 8) {
            break;
          }
          i += 1;
        }
      }
      Object localObject = GridLayout.this.getLayoutParams(localView);
      if (this.horizontal)
      {
        localObject = ((GridLayout.LayoutParams)localObject).columnSpec;
        label88:
        localObject = ((GridLayout.Spec)localObject).span;
        if (!paramBoolean) {
          break label143;
        }
      }
      label143:
      for (int j = ((GridLayout.Interval)localObject).min;; j = ((GridLayout.Interval)localObject).max)
      {
        arrayOfInt[j] = Math.max(arrayOfInt[j], GridLayout.this.getMargin1(localView, this.horizontal, paramBoolean));
        break;
        localObject = ((GridLayout.LayoutParams)localObject).rowSpec;
        break label88;
      }
    }
    
    private GridLayout.Arc[] createArcs()
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      addComponentSizes(localArrayList1, getForwardLinks());
      addComponentSizes(localArrayList2, getBackwardLinks());
      if (this.orderPreserved)
      {
        i = 0;
        while (i < getCount())
        {
          include(localArrayList1, new GridLayout.Interval(i, i + 1), new GridLayout.MutableInt(0));
          i += 1;
        }
      }
      int i = getCount();
      include(localArrayList1, new GridLayout.Interval(0, i), this.parentMin, false);
      include(localArrayList2, new GridLayout.Interval(i, 0), this.parentMax, false);
      return (GridLayout.Arc[])GridLayout.append(topologicalSort(localArrayList1), topologicalSort(localArrayList2));
    }
    
    private GridLayout.PackedMap<GridLayout.Spec, GridLayout.Bounds> createGroupBounds()
    {
      GridLayout.Assoc localAssoc = GridLayout.Assoc.of(GridLayout.Spec.class, GridLayout.Bounds.class);
      int j = GridLayout.this.getChildCount();
      int i = 0;
      if (i < j)
      {
        Object localObject = GridLayout.this.getChildAt(i);
        localObject = GridLayout.this.getLayoutParams((View)localObject);
        if (this.horizontal) {}
        for (localObject = ((GridLayout.LayoutParams)localObject).columnSpec;; localObject = ((GridLayout.LayoutParams)localObject).rowSpec)
        {
          localAssoc.put(localObject, ((GridLayout.Spec)localObject).getAbsoluteAlignment(this.horizontal).getBounds());
          i += 1;
          break;
        }
      }
      return localAssoc.pack();
    }
    
    private GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> createLinks(boolean paramBoolean)
    {
      GridLayout.Assoc localAssoc = GridLayout.Assoc.of(GridLayout.Interval.class, GridLayout.MutableInt.class);
      GridLayout.Spec[] arrayOfSpec = (GridLayout.Spec[])getGroupBounds().keys;
      int j = arrayOfSpec.length;
      int i = 0;
      if (i < j)
      {
        if (paramBoolean) {}
        for (GridLayout.Interval localInterval = arrayOfSpec[i].span;; localInterval = arrayOfSpec[i].span.inverse())
        {
          localAssoc.put(localInterval, new GridLayout.MutableInt());
          i += 1;
          break;
        }
      }
      return localAssoc.pack();
    }
    
    private GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> getBackwardLinks()
    {
      if (this.backwardLinks == null) {
        this.backwardLinks = createLinks(false);
      }
      if (!this.backwardLinksValid)
      {
        computeLinks(this.backwardLinks, false);
        this.backwardLinksValid = true;
      }
      return this.backwardLinks;
    }
    
    private GridLayout.PackedMap<GridLayout.Interval, GridLayout.MutableInt> getForwardLinks()
    {
      if (this.forwardLinks == null) {
        this.forwardLinks = createLinks(true);
      }
      if (!this.forwardLinksValid)
      {
        computeLinks(this.forwardLinks, true);
        this.forwardLinksValid = true;
      }
      return this.forwardLinks;
    }
    
    private int getMaxIndex()
    {
      if (this.maxIndex == Integer.MIN_VALUE) {
        this.maxIndex = Math.max(0, calculateMaxIndex());
      }
      return this.maxIndex;
    }
    
    private int getMeasure(int paramInt1, int paramInt2)
    {
      setParentConstraints(paramInt1, paramInt2);
      return size(getLocations());
    }
    
    private boolean hasWeights()
    {
      if (!this.hasWeightsValid)
      {
        this.hasWeights = computeHasWeights();
        this.hasWeightsValid = true;
      }
      return this.hasWeights;
    }
    
    private void include(List<GridLayout.Arc> paramList, GridLayout.Interval paramInterval, GridLayout.MutableInt paramMutableInt)
    {
      include(paramList, paramInterval, paramMutableInt, true);
    }
    
    private void include(List<GridLayout.Arc> paramList, GridLayout.Interval paramInterval, GridLayout.MutableInt paramMutableInt, boolean paramBoolean)
    {
      if (paramInterval.size() == 0) {
        return;
      }
      if (paramBoolean)
      {
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
          if (((GridLayout.Arc)localIterator.next()).span.equals(paramInterval)) {
            return;
          }
        }
      }
      paramList.add(new GridLayout.Arc(paramInterval, paramMutableInt));
    }
    
    private void init(int[] paramArrayOfInt)
    {
      Arrays.fill(paramArrayOfInt, 0);
    }
    
    private void logError(String paramString, GridLayout.Arc[] paramArrayOfArc, boolean[] paramArrayOfBoolean)
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      int i = 0;
      while (i < paramArrayOfArc.length)
      {
        GridLayout.Arc localArc = paramArrayOfArc[i];
        if (paramArrayOfBoolean[i] != 0) {
          localArrayList1.add(localArc);
        }
        if (!localArc.valid) {
          localArrayList2.add(localArc);
        }
        i += 1;
      }
      GridLayout.this.mPrinter.println(paramString + " constraints: " + arcsToString(localArrayList1) + " are inconsistent; permanently removing: " + arcsToString(localArrayList2) + ". ");
    }
    
    private boolean relax(int[] paramArrayOfInt, GridLayout.Arc paramArc)
    {
      if (!paramArc.valid) {}
      int j;
      int i;
      do
      {
        return false;
        GridLayout.Interval localInterval = paramArc.span;
        j = localInterval.min;
        i = localInterval.max;
        int k = paramArc.value.value;
        j = paramArrayOfInt[j] + k;
      } while (j <= paramArrayOfInt[i]);
      paramArrayOfInt[i] = j;
      return true;
    }
    
    private void setParentConstraints(int paramInt1, int paramInt2)
    {
      this.parentMin.value = paramInt1;
      this.parentMax.value = (-paramInt2);
      this.locationsValid = false;
    }
    
    private void shareOutDelta(int paramInt, float paramFloat)
    {
      Arrays.fill(this.deltas, 0);
      int j = GridLayout.this.getChildCount();
      int i = 0;
      Object localObject;
      if (i < j)
      {
        localObject = GridLayout.this.getChildAt(i);
        if (((View)localObject).getVisibility() != 8) {}
      }
      label137:
      for (;;)
      {
        i += 1;
        break;
        localObject = GridLayout.this.getLayoutParams((View)localObject);
        if (this.horizontal) {}
        for (localObject = ((GridLayout.LayoutParams)localObject).columnSpec;; localObject = ((GridLayout.LayoutParams)localObject).rowSpec)
        {
          float f = ((GridLayout.Spec)localObject).weight;
          if (f == 0.0F) {
            break label137;
          }
          int k = Math.round(paramInt * f / paramFloat);
          this.deltas[i] = k;
          paramFloat -= f;
          paramInt -= k;
          break;
        }
        return;
      }
    }
    
    private int size(int[] paramArrayOfInt)
    {
      return paramArrayOfInt[getCount()];
    }
    
    private boolean solve(int[] paramArrayOfInt)
    {
      return solve(getArcs(), paramArrayOfInt);
    }
    
    private boolean solve(GridLayout.Arc[] paramArrayOfArc, int[] paramArrayOfInt)
    {
      return solve(paramArrayOfArc, paramArrayOfInt, true);
    }
    
    private boolean solve(GridLayout.Arc[] paramArrayOfArc, int[] paramArrayOfInt, boolean paramBoolean)
    {
      boolean bool3 = false;
      String str;
      int n;
      Object localObject;
      int i;
      if (this.horizontal)
      {
        str = "horizontal";
        n = getCount() + 1;
        localObject = null;
        i = 0;
      }
      for (;;)
      {
        if (i >= paramArrayOfArc.length) {
          break label285;
        }
        init(paramArrayOfInt);
        int j = 0;
        int k;
        boolean bool2;
        if (j < n)
        {
          int i1 = paramArrayOfArc.length;
          k = 0;
          boolean bool1 = false;
          for (;;)
          {
            if (k < i1)
            {
              bool1 |= relax(paramArrayOfInt, paramArrayOfArc[k]);
              k += 1;
              continue;
              str = "vertical";
              break;
            }
          }
          if (!bool1)
          {
            if (localObject != null) {
              logError(str, paramArrayOfArc, (boolean[])localObject);
            }
            bool2 = true;
          }
        }
        do
        {
          return bool2;
          j += 1;
          break;
          bool2 = bool3;
        } while (!paramBoolean);
        boolean[] arrayOfBoolean = new boolean[paramArrayOfArc.length];
        j = 0;
        while (j < n)
        {
          int m = paramArrayOfArc.length;
          k = 0;
          while (k < m)
          {
            arrayOfBoolean[k] |= relax(paramArrayOfInt, paramArrayOfArc[k]);
            k += 1;
          }
          j += 1;
        }
        if (i == 0) {
          localObject = arrayOfBoolean;
        }
        j = 0;
        while (j < paramArrayOfArc.length)
        {
          GridLayout.Arc localArc;
          if (arrayOfBoolean[j] != 0)
          {
            localArc = paramArrayOfArc[j];
            if (localArc.span.min >= localArc.span.max) {}
          }
          else
          {
            j += 1;
            continue;
          }
          localArc.valid = false;
        }
        i += 1;
      }
      label285:
      return true;
    }
    
    private void solveAndDistributeSpace(int[] paramArrayOfInt)
    {
      Arrays.fill(getDeltas(), 0);
      solve(paramArrayOfInt);
      int j = this.parentMin.value * GridLayout.this.getChildCount() + 1;
      if (j < 2) {}
      float f;
      int i;
      boolean bool;
      do
      {
        return;
        f = calculateTotalWeight();
        i = -1;
        bool = true;
        int k = 0;
        if (k < j)
        {
          int m = (int)((k + j) / 2L);
          invalidateValues();
          shareOutDelta(m, f);
          bool = solve(getArcs(), paramArrayOfInt, false);
          if (bool)
          {
            i = m + 1;
            k = j;
            j = i;
            i = m;
          }
          for (;;)
          {
            m = k;
            k = j;
            j = m;
            break;
            j = k;
            k = m;
          }
        }
      } while ((i <= 0) || (bool));
      invalidateValues();
      shareOutDelta(i, f);
      solve(paramArrayOfInt);
    }
    
    private GridLayout.Arc[] topologicalSort(List<GridLayout.Arc> paramList)
    {
      return topologicalSort((GridLayout.Arc[])paramList.toArray(new GridLayout.Arc[paramList.size()]));
    }
    
    private GridLayout.Arc[] topologicalSort(final GridLayout.Arc[] paramArrayOfArc)
    {
      new Object()
      {
        GridLayout.Arc[][] arcsByVertex = GridLayout.Axis.this.groupArcsByFirstVertex(paramArrayOfArc);
        int cursor = this.result.length - 1;
        GridLayout.Arc[] result = new GridLayout.Arc[paramArrayOfArc.length];
        int[] visited = new int[GridLayout.Axis.this.getCount() + 1];
        
        static
        {
          if (!GridLayout.class.desiredAssertionStatus()) {}
          for (boolean bool = true;; bool = false)
          {
            $assertionsDisabled = bool;
            return;
          }
        }
        
        GridLayout.Arc[] sort()
        {
          int i = 0;
          int j = this.arcsByVertex.length;
          while (i < j)
          {
            walk(i);
            i += 1;
          }
          assert (this.cursor == -1);
          return this.result;
        }
        
        void walk(int paramAnonymousInt)
        {
          switch (this.visited[paramAnonymousInt])
          {
          }
          do
          {
            return;
            this.visited[paramAnonymousInt] = 1;
            GridLayout.Arc[] arrayOfArc1 = this.arcsByVertex[paramAnonymousInt];
            int j = arrayOfArc1.length;
            int i = 0;
            while (i < j)
            {
              GridLayout.Arc localArc = arrayOfArc1[i];
              walk(localArc.span.max);
              GridLayout.Arc[] arrayOfArc2 = this.result;
              int k = this.cursor;
              this.cursor = (k - 1);
              arrayOfArc2[k] = localArc;
              i += 1;
            }
            this.visited[paramAnonymousInt] = 2;
            return;
          } while ($assertionsDisabled);
          throw new AssertionError();
        }
      }.sort();
    }
    
    public GridLayout.Arc[] getArcs()
    {
      if (this.arcs == null) {
        this.arcs = createArcs();
      }
      if (!this.arcsValid)
      {
        computeArcs();
        this.arcsValid = true;
      }
      return this.arcs;
    }
    
    public int getCount()
    {
      return Math.max(this.definedCount, getMaxIndex());
    }
    
    public int[] getDeltas()
    {
      if (this.deltas == null) {
        this.deltas = new int[GridLayout.this.getChildCount()];
      }
      return this.deltas;
    }
    
    public GridLayout.PackedMap<GridLayout.Spec, GridLayout.Bounds> getGroupBounds()
    {
      if (this.groupBounds == null) {
        this.groupBounds = createGroupBounds();
      }
      if (!this.groupBoundsValid)
      {
        computeGroupBounds();
        this.groupBoundsValid = true;
      }
      return this.groupBounds;
    }
    
    public int[] getLeadingMargins()
    {
      if (this.leadingMargins == null) {
        this.leadingMargins = new int[getCount() + 1];
      }
      if (!this.leadingMarginsValid)
      {
        computeMargins(true);
        this.leadingMarginsValid = true;
      }
      return this.leadingMargins;
    }
    
    public int[] getLocations()
    {
      if (this.locations == null) {
        this.locations = new int[getCount() + 1];
      }
      if (!this.locationsValid)
      {
        computeLocations(this.locations);
        this.locationsValid = true;
      }
      return this.locations;
    }
    
    public int getMeasure(int paramInt)
    {
      int i = 0;
      int j = View.MeasureSpec.getMode(paramInt);
      paramInt = View.MeasureSpec.getSize(paramInt);
      switch (j)
      {
      default: 
        paramInt = i;
        if (!$assertionsDisabled) {
          throw new AssertionError();
        }
      case 0: 
        paramInt = getMeasure(0, 100000);
        return paramInt;
      case 1073741824: 
        return getMeasure(paramInt, paramInt);
      }
      return getMeasure(0, paramInt);
    }
    
    public int[] getTrailingMargins()
    {
      if (this.trailingMargins == null) {
        this.trailingMargins = new int[getCount() + 1];
      }
      if (!this.trailingMarginsValid)
      {
        computeMargins(false);
        this.trailingMarginsValid = true;
      }
      return this.trailingMargins;
    }
    
    GridLayout.Arc[][] groupArcsByFirstVertex(GridLayout.Arc[] paramArrayOfArc)
    {
      int j = 0;
      int i = getCount() + 1;
      GridLayout.Arc[][] arrayOfArc = new GridLayout.Arc[i][];
      int[] arrayOfInt = new int[i];
      int k = paramArrayOfArc.length;
      i = 0;
      int m;
      while (i < k)
      {
        m = paramArrayOfArc[i].span.min;
        arrayOfInt[m] += 1;
        i += 1;
      }
      i = 0;
      while (i < arrayOfInt.length)
      {
        arrayOfArc[i] = new GridLayout.Arc[arrayOfInt[i]];
        i += 1;
      }
      Arrays.fill(arrayOfInt, 0);
      k = paramArrayOfArc.length;
      i = j;
      while (i < k)
      {
        GridLayout.Arc localArc = paramArrayOfArc[i];
        j = localArc.span.min;
        GridLayout.Arc[] arrayOfArc1 = arrayOfArc[j];
        m = arrayOfInt[j];
        arrayOfInt[j] = (m + 1);
        arrayOfArc1[m] = localArc;
        i += 1;
      }
      return arrayOfArc;
    }
    
    public void invalidateStructure()
    {
      this.maxIndex = Integer.MIN_VALUE;
      this.groupBounds = null;
      this.forwardLinks = null;
      this.backwardLinks = null;
      this.leadingMargins = null;
      this.trailingMargins = null;
      this.arcs = null;
      this.locations = null;
      this.deltas = null;
      this.hasWeightsValid = false;
      invalidateValues();
    }
    
    public void invalidateValues()
    {
      this.groupBoundsValid = false;
      this.forwardLinksValid = false;
      this.backwardLinksValid = false;
      this.leadingMarginsValid = false;
      this.trailingMarginsValid = false;
      this.arcsValid = false;
      this.locationsValid = false;
    }
    
    public boolean isOrderPreserved()
    {
      return this.orderPreserved;
    }
    
    public void layout(int paramInt)
    {
      setParentConstraints(paramInt, paramInt);
      getLocations();
    }
    
    public void setCount(int paramInt)
    {
      StringBuilder localStringBuilder;
      if ((paramInt != Integer.MIN_VALUE) && (paramInt < getMaxIndex()))
      {
        localStringBuilder = new StringBuilder();
        if (!this.horizontal) {
          break label62;
        }
      }
      label62:
      for (String str = "column";; str = "row")
      {
        GridLayout.handleInvalidParams(str + "Count must be greater than or equal to the maximum of all grid indices " + "(and spans) defined in the LayoutParams of each child");
        this.definedCount = paramInt;
        return;
      }
    }
    
    public void setOrderPreserved(boolean paramBoolean)
    {
      this.orderPreserved = paramBoolean;
      invalidateStructure();
    }
  }
  
  static class Bounds
  {
    public int after;
    public int before;
    public int flexibility;
    
    Bounds()
    {
      reset();
    }
    
    protected int getOffset(GridLayout paramGridLayout, View paramView, GridLayout.Alignment paramAlignment, int paramInt, boolean paramBoolean)
    {
      return this.before - paramAlignment.getAlignmentValue(paramView, paramInt, ViewGroupCompat.getLayoutMode(paramGridLayout));
    }
    
    protected void include(int paramInt1, int paramInt2)
    {
      this.before = Math.max(this.before, paramInt1);
      this.after = Math.max(this.after, paramInt2);
    }
    
    protected final void include(GridLayout paramGridLayout, View paramView, GridLayout.Spec paramSpec, GridLayout.Axis paramAxis, int paramInt)
    {
      this.flexibility &= paramSpec.getFlexibility();
      int i = paramSpec.getAbsoluteAlignment(paramAxis.horizontal).getAlignmentValue(paramView, paramInt, ViewGroupCompat.getLayoutMode(paramGridLayout));
      include(i, paramInt - i);
    }
    
    protected void reset()
    {
      this.before = Integer.MIN_VALUE;
      this.after = Integer.MIN_VALUE;
      this.flexibility = 2;
    }
    
    protected int size(boolean paramBoolean)
    {
      if ((!paramBoolean) && (GridLayout.canStretch(this.flexibility))) {
        return 100000;
      }
      return this.before + this.after;
    }
    
    public String toString()
    {
      return "Bounds{before=" + this.before + ", after=" + this.after + '}';
    }
  }
  
  static final class Interval
  {
    public final int max;
    public final int min;
    
    public Interval(int paramInt1, int paramInt2)
    {
      this.min = paramInt1;
      this.max = paramInt2;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (Interval)paramObject;
        if (this.max != ((Interval)paramObject).max) {
          return false;
        }
      } while (this.min == ((Interval)paramObject).min);
      return false;
    }
    
    public int hashCode()
    {
      return this.min * 31 + this.max;
    }
    
    Interval inverse()
    {
      return new Interval(this.max, this.min);
    }
    
    int size()
    {
      return this.max - this.min;
    }
    
    public String toString()
    {
      return "[" + this.min + ", " + this.max + "]";
    }
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    private static final int BOTTOM_MARGIN;
    private static final int COLUMN;
    private static final int COLUMN_SPAN;
    private static final int COLUMN_WEIGHT;
    private static final int DEFAULT_COLUMN = Integer.MIN_VALUE;
    private static final int DEFAULT_HEIGHT = -2;
    private static final int DEFAULT_MARGIN = Integer.MIN_VALUE;
    private static final int DEFAULT_ROW = Integer.MIN_VALUE;
    private static final GridLayout.Interval DEFAULT_SPAN = new GridLayout.Interval(Integer.MIN_VALUE, -2147483647);
    private static final int DEFAULT_SPAN_SIZE = DEFAULT_SPAN.size();
    private static final int DEFAULT_WIDTH = -2;
    private static final int GRAVITY = R.styleable.GridLayout_Layout_layout_gravity;
    private static final int LEFT_MARGIN;
    private static final int MARGIN = R.styleable.GridLayout_Layout_android_layout_margin;
    private static final int RIGHT_MARGIN;
    private static final int ROW;
    private static final int ROW_SPAN;
    private static final int ROW_WEIGHT;
    private static final int TOP_MARGIN;
    public GridLayout.Spec columnSpec = GridLayout.Spec.UNDEFINED;
    public GridLayout.Spec rowSpec = GridLayout.Spec.UNDEFINED;
    
    static
    {
      LEFT_MARGIN = R.styleable.GridLayout_Layout_android_layout_marginLeft;
      TOP_MARGIN = R.styleable.GridLayout_Layout_android_layout_marginTop;
      RIGHT_MARGIN = R.styleable.GridLayout_Layout_android_layout_marginRight;
      BOTTOM_MARGIN = R.styleable.GridLayout_Layout_android_layout_marginBottom;
      COLUMN = R.styleable.GridLayout_Layout_layout_column;
      COLUMN_SPAN = R.styleable.GridLayout_Layout_layout_columnSpan;
      COLUMN_WEIGHT = R.styleable.GridLayout_Layout_layout_columnWeight;
      ROW = R.styleable.GridLayout_Layout_layout_row;
      ROW_SPAN = R.styleable.GridLayout_Layout_layout_rowSpan;
      ROW_WEIGHT = R.styleable.GridLayout_Layout_layout_rowWeight;
    }
    
    public LayoutParams()
    {
      this(GridLayout.Spec.UNDEFINED, GridLayout.Spec.UNDEFINED);
    }
    
    private LayoutParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, GridLayout.Spec paramSpec1, GridLayout.Spec paramSpec2)
    {
      super(paramInt2);
      setMargins(paramInt3, paramInt4, paramInt5, paramInt6);
      this.rowSpec = paramSpec1;
      this.columnSpec = paramSpec2;
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      reInitSuper(paramContext, paramAttributeSet);
      init(paramContext, paramAttributeSet);
    }
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
      this.rowSpec = paramLayoutParams.rowSpec;
      this.columnSpec = paramLayoutParams.columnSpec;
    }
    
    public LayoutParams(GridLayout.Spec paramSpec1, GridLayout.Spec paramSpec2)
    {
      this(-2, -2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, paramSpec1, paramSpec2);
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
    
    private void init(Context paramContext, AttributeSet paramAttributeSet)
    {
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.GridLayout_Layout);
      try
      {
        int i = paramContext.getInt(GRAVITY, 0);
        int j = paramContext.getInt(COLUMN, Integer.MIN_VALUE);
        int k = paramContext.getInt(COLUMN_SPAN, DEFAULT_SPAN_SIZE);
        float f = paramContext.getFloat(COLUMN_WEIGHT, 0.0F);
        this.columnSpec = GridLayout.spec(j, k, GridLayout.getAlignment(i, true), f);
        j = paramContext.getInt(ROW, Integer.MIN_VALUE);
        k = paramContext.getInt(ROW_SPAN, DEFAULT_SPAN_SIZE);
        f = paramContext.getFloat(ROW_WEIGHT, 0.0F);
        this.rowSpec = GridLayout.spec(j, k, GridLayout.getAlignment(i, false), f);
        return;
      }
      finally
      {
        paramContext.recycle();
      }
    }
    
    private void reInitSuper(Context paramContext, AttributeSet paramAttributeSet)
    {
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.GridLayout_Layout);
      try
      {
        int i = paramContext.getDimensionPixelSize(MARGIN, Integer.MIN_VALUE);
        this.leftMargin = paramContext.getDimensionPixelSize(LEFT_MARGIN, i);
        this.topMargin = paramContext.getDimensionPixelSize(TOP_MARGIN, i);
        this.rightMargin = paramContext.getDimensionPixelSize(RIGHT_MARGIN, i);
        this.bottomMargin = paramContext.getDimensionPixelSize(BOTTOM_MARGIN, i);
        return;
      }
      finally
      {
        paramContext.recycle();
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (LayoutParams)paramObject;
        if (!this.columnSpec.equals(((LayoutParams)paramObject).columnSpec)) {
          return false;
        }
      } while (this.rowSpec.equals(((LayoutParams)paramObject).rowSpec));
      return false;
    }
    
    public int hashCode()
    {
      return this.rowSpec.hashCode() * 31 + this.columnSpec.hashCode();
    }
    
    protected void setBaseAttributes(TypedArray paramTypedArray, int paramInt1, int paramInt2)
    {
      this.width = paramTypedArray.getLayoutDimension(paramInt1, -2);
      this.height = paramTypedArray.getLayoutDimension(paramInt2, -2);
    }
    
    final void setColumnSpecSpan(GridLayout.Interval paramInterval)
    {
      this.columnSpec = this.columnSpec.copyWriteSpan(paramInterval);
    }
    
    public void setGravity(int paramInt)
    {
      this.rowSpec = this.rowSpec.copyWriteAlignment(GridLayout.getAlignment(paramInt, false));
      this.columnSpec = this.columnSpec.copyWriteAlignment(GridLayout.getAlignment(paramInt, true));
    }
    
    final void setRowSpecSpan(GridLayout.Interval paramInterval)
    {
      this.rowSpec = this.rowSpec.copyWriteSpan(paramInterval);
    }
  }
  
  static final class MutableInt
  {
    public int value;
    
    public MutableInt()
    {
      reset();
    }
    
    public MutableInt(int paramInt)
    {
      this.value = paramInt;
    }
    
    public void reset()
    {
      this.value = Integer.MIN_VALUE;
    }
    
    public String toString()
    {
      return Integer.toString(this.value);
    }
  }
  
  static final class PackedMap<K, V>
  {
    public final int[] index;
    public final K[] keys;
    public final V[] values;
    
    PackedMap(K[] paramArrayOfK, V[] paramArrayOfV)
    {
      this.index = createIndex(paramArrayOfK);
      this.keys = compact(paramArrayOfK, this.index);
      this.values = compact(paramArrayOfV, this.index);
    }
    
    private static <K> K[] compact(K[] paramArrayOfK, int[] paramArrayOfInt)
    {
      int j = paramArrayOfK.length;
      Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfK.getClass().getComponentType(), GridLayout.max2(paramArrayOfInt, -1) + 1);
      int i = 0;
      while (i < j)
      {
        arrayOfObject[paramArrayOfInt[i]] = paramArrayOfK[i];
        i += 1;
      }
      return arrayOfObject;
    }
    
    private static <K> int[] createIndex(K[] paramArrayOfK)
    {
      int j = paramArrayOfK.length;
      int[] arrayOfInt = new int[j];
      HashMap localHashMap = new HashMap();
      int i = 0;
      while (i < j)
      {
        K ? = paramArrayOfK[i];
        Integer localInteger2 = (Integer)localHashMap.get(?);
        Integer localInteger1 = localInteger2;
        if (localInteger2 == null)
        {
          localInteger1 = Integer.valueOf(localHashMap.size());
          localHashMap.put(?, localInteger1);
        }
        arrayOfInt[i] = localInteger1.intValue();
        i += 1;
      }
      return arrayOfInt;
    }
    
    public V getValue(int paramInt)
    {
      return (V)this.values[this.index[paramInt]];
    }
  }
  
  public static class Spec
  {
    static final float DEFAULT_WEIGHT = 0.0F;
    static final Spec UNDEFINED = GridLayout.spec(Integer.MIN_VALUE);
    final GridLayout.Alignment alignment;
    final GridLayout.Interval span;
    final boolean startDefined;
    final float weight;
    
    Spec(boolean paramBoolean, int paramInt1, int paramInt2, GridLayout.Alignment paramAlignment, float paramFloat)
    {
      this(paramBoolean, new GridLayout.Interval(paramInt1, paramInt1 + paramInt2), paramAlignment, paramFloat);
    }
    
    private Spec(boolean paramBoolean, GridLayout.Interval paramInterval, GridLayout.Alignment paramAlignment, float paramFloat)
    {
      this.startDefined = paramBoolean;
      this.span = paramInterval;
      this.alignment = paramAlignment;
      this.weight = paramFloat;
    }
    
    final Spec copyWriteAlignment(GridLayout.Alignment paramAlignment)
    {
      return new Spec(this.startDefined, this.span, paramAlignment, this.weight);
    }
    
    final Spec copyWriteSpan(GridLayout.Interval paramInterval)
    {
      return new Spec(this.startDefined, paramInterval, this.alignment, this.weight);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (Spec)paramObject;
        if (!this.alignment.equals(((Spec)paramObject).alignment)) {
          return false;
        }
      } while (this.span.equals(((Spec)paramObject).span));
      return false;
    }
    
    public GridLayout.Alignment getAbsoluteAlignment(boolean paramBoolean)
    {
      if (this.alignment != GridLayout.UNDEFINED_ALIGNMENT) {
        return this.alignment;
      }
      if (this.weight == 0.0F)
      {
        if (paramBoolean) {
          return GridLayout.START;
        }
        return GridLayout.BASELINE;
      }
      return GridLayout.FILL;
    }
    
    final int getFlexibility()
    {
      if ((this.alignment == GridLayout.UNDEFINED_ALIGNMENT) && (this.weight == 0.0F)) {
        return 0;
      }
      return 2;
    }
    
    public int hashCode()
    {
      return this.span.hashCode() * 31 + this.alignment.hashCode();
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v7\widget\GridLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */