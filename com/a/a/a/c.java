package com.a.a.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.ClipboardManager;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.method.CharacterPickerDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityRecord;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.Scroller;
import com.a.a.b.b.a;
import com.a.a.b.e.a;
import com.a.a.b.h;
import com.a.a.b.i;
import com.a.a.b.k;
import com.a.a.b.k.a;
import com.a.a.b.m;
import com.a.a.b.o;
import com.a.a.b.r;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
  extends View
  implements e.a
{
  public static final int SCROLL_DOWN = 1;
  public static final int SCROLL_LEFT = 2;
  public static final int SCROLL_RIGHT = 3;
  public static final int SCROLL_UP = 0;
  protected static float a = 0.75F;
  protected static float b = 0.5F;
  protected static int c = 4;
  protected static int d = 16;
  protected static long e = 250L;
  private static SparseArray<String> u = new SparseArray();
  private int A = 0;
  private Paint B;
  private int C = 0;
  private int D = 0;
  private boolean E = false;
  private b F;
  private ClipboardManager G;
  private float H = 1.0F;
  private int I;
  private int J;
  private f K;
  private int L;
  private Typeface M = Typeface.DEFAULT;
  private Typeface N = Typeface.DEFAULT_BOLD;
  private Typeface O = Typeface.create(Typeface.DEFAULT, 2);
  private char P;
  private boolean Q;
  private Paint R;
  private int S;
  private final Runnable T = new Runnable()
  {
    public void run()
    {
      c.a(c.this).c();
      if (!c.this.d()) {
        c.this.postDelayed(c.b(c.this), c.e);
      }
    }
  };
  private final Runnable U = new Runnable()
  {
    public void run()
    {
      c.a(c.this).d();
      if (!c.this.c()) {
        c.this.postDelayed(c.c(c.this), c.e);
      }
    }
  };
  private final Runnable V = new Runnable()
  {
    public void run()
    {
      c.a(c.this).b(false);
      if ((c.this.i > 0) && (c.d(c.this) == c.this.h.b(c.this.i - 1))) {
        c.this.postDelayed(c.e(c.this), c.e);
      }
    }
  };
  private final Runnable W = new Runnable()
  {
    public void run()
    {
      c.a(c.this).a(false);
      if ((!c.this.e()) && (c.d(c.this) == c.this.h.b(c.this.i + 1))) {
        c.this.postDelayed(c.f(c.this), c.e);
      }
    }
  };
  private int aa;
  private long ab;
  private boolean ac = false;
  private MotionEvent ad;
  private float ae;
  private float af;
  protected boolean f = false;
  protected g g = new g(this);
  protected com.a.a.b.f h = new com.a.a.b.f(this);
  protected int i = 0;
  protected int j = -1;
  protected int k = -1;
  protected int l = c;
  protected com.a.a.b.b m = new com.a.a.b.d();
  protected boolean n = false;
  protected boolean o = false;
  protected boolean p = true;
  protected int q = 4;
  protected boolean r = false;
  protected a s;
  protected boolean t = true;
  private final Scroller v;
  private a w;
  private b x;
  private o y;
  private e z;
  
  static
  {
    u.put(65, "ÀÁÂÄÆÃÅĄĀ");
    u.put(67, "ÇĆČ");
    u.put(68, "Ď");
    u.put(69, "ÈÉÊËĘĚĒ");
    u.put(71, "Ğ");
    u.put(76, "Ł");
    u.put(73, "ÌÍÎÏĪİ");
    u.put(78, "ÑŃŇ");
    u.put(79, "ØŒÕÒÓÔÖŌ");
    u.put(82, "Ř");
    u.put(83, "ŚŠŞ");
    u.put(84, "Ť");
    u.put(85, "ÙÚÛÜŮŪ");
    u.put(89, "ÝŸ");
    u.put(90, "ŹŻŽ");
    u.put(97, "àáâäæãåąā");
    u.put(99, "çćč");
    u.put(100, "ď");
    u.put(101, "èéêëęěē");
    u.put(103, "ğ");
    u.put(105, "ìíîïīı");
    u.put(108, "ł");
    u.put(110, "ñńň");
    u.put(111, "øœõòóôöō");
    u.put(114, "ř");
    u.put(115, "§ßśšş");
    u.put(116, "ť");
    u.put(117, "ùúûüůū");
    u.put(121, "ýÿ");
    u.put(122, "źżž");
    u.put(61185, "…¥•®©±[]{}\\|");
    u.put(47, "\\");
    u.put(49, "¹½⅓¼⅛");
    u.put(50, "²⅔");
    u.put(51, "³¾⅜");
    u.put(52, "⁴");
    u.put(53, "⅝");
    u.put(55, "⅞");
    u.put(48, "ⁿ∅");
    u.put(36, "¢£€¥₣₤₱");
    u.put(37, "‰");
    u.put(42, "†‡");
    u.put(45, "–—");
    u.put(43, "±");
    u.put(40, "[{<");
    u.put(41, "]}>");
    u.put(33, "¡");
    u.put(34, "“”«»˝");
    u.put(63, "¿");
    u.put(44, "‚„");
    u.put(61, "≠≈∞");
    u.put(60, "≤«‹");
    u.put(62, "≥»›");
  }
  
  public c(Context paramContext)
  {
    super(paramContext);
    this.v = new Scroller(paramContext);
    f();
  }
  
  public c(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.v = new Scroller(paramContext);
    f();
  }
  
  public c(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.v = new Scroller(paramContext);
    f();
  }
  
  private int a(Canvas paramCanvas)
  {
    return paramCanvas.getClipBounds().top / a();
  }
  
  private int a(Canvas paramCanvas, char paramChar, int paramInt1, int paramInt2)
  {
    int i2 = this.B.getColor();
    int i3 = getAdvance(paramChar, paramInt1);
    if ((paramInt1 > getScrollX()) || (paramInt1 < getScrollX() + getContentWidth()))
    {
      switch (paramChar)
      {
      default: 
        if (this.P == 0) {
          break label307;
        }
        int i1 = this.P;
        f1 = paramInt1;
        f2 = paramInt2;
        localObject = this.B;
        paramCanvas.drawText(new char[] { i1, paramChar }, 0, 2, f1, f2, (Paint)localObject);
        this.P = '\000';
        return i3;
      case '?': 
      case '?': 
        this.P = paramChar;
        return i3;
      case ' ': 
        if (this.o)
        {
          this.B.setColor(this.m.a(b.a.i));
          localObject = "·";
        }
        else
        {
          paramCanvas.drawText(" ", 0, 1, paramInt1, paramInt2, this.B);
          return i3;
        }
        break;
      case '\n': 
      case '￿': 
        if (!this.o) {
          break label341;
        }
        this.B.setColor(this.m.a(b.a.i));
        localObject = "↵";
        break;
      case '\t': 
        if (!this.o) {
          break label341;
        }
        this.B.setColor(this.m.a(b.a.i));
        localObject = "»";
      }
      paramCanvas.drawText((String)localObject, 0, 1, paramInt1, paramInt2, this.B);
      this.B.setColor(i2);
      return i3;
      label307:
      float f1 = paramInt1;
      float f2 = paramInt2;
      Object localObject = this.B;
      paramCanvas.drawText(new char[] { paramChar }, 0, 1, f1, f2, (Paint)localObject);
    }
    label341:
    return i3;
  }
  
  private int a(Canvas paramCanvas, String paramString, int paramInt1, int paramInt2)
  {
    paramCanvas.drawText(paramString, paramInt1, paramInt2, this.R);
    return 0;
  }
  
  private void a(char paramChar)
  {
    a locala;
    if ((Character.isLowerCase(paramChar)) && (paramChar == this.h.charAt(this.i - 1)))
    {
      this.w.a('\b');
      locala = this.w;
      paramChar = Character.toUpperCase(paramChar);
    }
    else
    {
      locala = this.w;
    }
    locala.a(paramChar);
  }
  
  private void a(float paramFloat1, float paramFloat2)
  {
    int i2 = (int)paramFloat1 + getScrollX();
    int i3 = (int)paramFloat2 + getScrollY();
    int i1 = Math.max(getMaxScrollX(), getScrollX());
    if (i2 <= i1)
    {
      i1 = i2;
      if (i2 < 0) {
        i1 = 0;
      }
    }
    i2 = Math.max(getMaxScrollY(), getScrollY());
    if (i3 <= i2)
    {
      i2 = i3;
      if (i3 < 0) {
        i2 = 0;
      }
    }
    smoothScrollTo(i1, i2);
  }
  
  private void a(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.isShiftPressed()) && (!isSelectText()))
    {
      h();
      this.w.c(true);
    }
    else if ((!paramKeyEvent.isShiftPressed()) && (isSelectText()))
    {
      i();
      this.w.c(false);
    }
    switch (paramInt)
    {
    default: 
      return;
    case 22: 
      this.w.a(false);
      return;
    case 21: 
      this.w.b(false);
      return;
    case 20: 
      this.w.c();
      return;
    }
    this.w.d();
  }
  
  private void a(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    int i1 = this.B.getColor();
    this.I = paramInt1;
    this.J = paramInt2;
    int i2 = this.m.a(b.a.g);
    this.B.setColor(i2);
    a(paramCanvas, paramInt1 - 1, paramInt2, 2);
    this.B.setColor(i1);
  }
  
  private void a(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3)
  {
    Paint.FontMetricsInt localFontMetricsInt = this.B.getFontMetricsInt();
    paramCanvas.drawRect(paramInt1, localFontMetricsInt.ascent + paramInt2, paramInt1 + paramInt3, paramInt2 + localFontMetricsInt.descent, this.B);
  }
  
  private void a(String paramString, final boolean paramBoolean)
  {
    final SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
    Selection.setSelection(localSpannableStringBuilder, 0);
    paramString = new CharacterPickerDialog(getContext(), this, localSpannableStringBuilder, paramString, true);
    paramString.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (localSpannableStringBuilder.length() > 0)
        {
          if (paramBoolean) {
            c.a(c.this).a('\b');
          }
          c.a(c.this).a(localSpannableStringBuilder.charAt(0));
        }
      }
    });
    paramString.show();
  }
  
  private int b(Canvas paramCanvas)
  {
    return (paramCanvas.getClipBounds().bottom - 1) / a();
  }
  
  private int b(Canvas paramCanvas, char paramChar, int paramInt1, int paramInt2)
  {
    int i1 = this.B.getColor();
    int i2 = getAdvance(paramChar);
    this.B.setColor(this.m.a(b.a.d));
    a(paramCanvas, paramInt1, paramInt2, i2);
    this.B.setColor(this.m.a(b.a.c));
    a(paramCanvas, paramChar, paramInt1, paramInt2);
    this.B.setColor(i1);
    return i2;
  }
  
  private void b(char paramChar)
  {
    int i1;
    if (Character.isUpperCase(this.h.charAt(this.i - 1))) {
      i1 = Character.toUpperCase(paramChar);
    } else {
      i1 = paramChar;
    }
    String str = (String)u.get(i1);
    if (str != null)
    {
      this.w.f();
      a(str, true);
      return;
    }
    this.w.a(paramChar);
  }
  
  private void c(Canvas paramCanvas)
  {
    int i10 = a(paramCanvas);
    int i6 = this.h.d(i10);
    if (i6 < 0) {
      return;
    }
    this.h.length();
    if (isWordWrap()) {}
    int i3;
    for (int i1 = this.h.c(i6) + 1;; i1 = i10 + 1)
    {
      i3 = i1;
      break;
    }
    Object localObject1;
    Object localObject2;
    if (this.E)
    {
      localObject1 = this.R;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(this.h.f());
      ((StringBuilder)localObject2).append(" ");
      this.D = ((int)((Paint)localObject1).measureText(((StringBuilder)localObject2).toString()));
    }
    int i16 = b(paramCanvas);
    int i9 = getPaintBaseline(i10);
    List localList = this.h.i();
    r.a(localList.isEmpty() ^ true, "No spans to paint in TextWarrior.paint()");
    if (localList.isEmpty()) {
      localList.add(new m(0, 0));
    }
    Object localObject4 = (m)localList.get(0);
    int i5 = localList.size();
    int i2 = 0;
    i1 = 1;
    for (;;)
    {
      i4 = i2 + ((m)localObject4).a();
      if (i1 < i5)
      {
        localObject1 = (m)localList.get(i1);
        i1 += 1;
      }
      else
      {
        localObject1 = null;
      }
      if ((localObject1 == null) || (i4 > i6)) {
        break;
      }
      i2 = i4;
      localObject4 = localObject1;
    }
    int i7 = ((m)localObject4).b();
    i2 = ((m)localObject4).b();
    Object localObject3;
    if (i2 != 1) {
      if (i2 != 30)
      {
        localObject2 = this.B;
        localObject3 = this.M;
      }
    }
    for (;;)
    {
      ((Paint)localObject2).setTypeface((Typeface)localObject3);
      break;
      localObject2 = this.B;
      localObject3 = this.O;
      continue;
      localObject2 = this.B;
      localObject3 = this.N;
    }
    i2 = this.m.a(((m)localObject4).b());
    this.B.setColor(i2);
    int i8 = this.h.f();
    if (this.E)
    {
      this.R.setColor(this.m.a(b.a.i));
      paramCanvas.drawLine(this.D - this.aa / 2, getScrollY(), this.D - this.aa / 2, getScrollY() + getHeight(), this.R);
    }
    i2 = i6;
    if (i7 != 1)
    {
      if (i7 != 30) {
        localObject2 = this.M;
      } else {
        localObject2 = this.O;
      }
    }
    else {
      localObject2 = this.N;
    }
    this.B.setTypeface((Typeface)localObject2);
    int i11 = 0;
    i6 = i1;
    i1 = i4;
    int i4 = i11;
    while (i10 <= i16)
    {
      int i14 = this.h.g(i10);
      if (i10 >= i8) {
        break;
      }
      if ((this.E) && (i3 != i4))
      {
        a(paramCanvas, String.valueOf(i3), 0, i9);
        i4 = i3;
      }
      i11 = this.D;
      localObject3 = localObject2;
      int i12 = i7;
      int i13 = i6;
      int i15 = 0;
      i7 = i1;
      i6 = i2;
      i2 = i12;
      i1 = i13;
      localObject2 = localObject1;
      i12 = i14;
      i13 = i15;
      localObject1 = localObject3;
      while (i13 < i12)
      {
        if ((localObject2 != null) && (i6 >= i7))
        {
          i7 += ((m)localObject2).a();
          i14 = ((m)localObject2).b();
          if (i2 != i14)
          {
            if (i14 != 1)
            {
              if (i14 != 30) {
                localObject2 = this.M;
              } else {
                localObject2 = this.O;
              }
            }
            else {
              localObject2 = this.N;
            }
            localObject3 = localObject1;
            if (localObject1 != localObject2)
            {
              this.B.setTypeface((Typeface)localObject2);
              localObject3 = localObject2;
            }
            i2 = this.m.a(i14);
            this.B.setColor(i2);
            localObject1 = localObject3;
          }
          if (i1 < i5)
          {
            i2 = i1 + 1;
            localObject2 = (m)localList.get(i1);
            i1 = i2;
          }
          for (;;)
          {
            localObject3 = localObject1;
            i2 = i14;
            localObject1 = localObject2;
            localObject2 = localObject3;
            break;
            localObject2 = null;
          }
        }
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        if (i6 == this.i) {
          a(paramCanvas, i11, i9);
        }
        char c1 = this.h.charAt(i6);
        if (this.w.b(i6)) {}
        for (i14 = b(paramCanvas, c1, i11, i9);; i14 = a(paramCanvas, c1, i11, i9))
        {
          i11 += i14;
          break;
        }
        i6 += 1;
        i13 += 1;
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
      i12 = i3;
      if (this.h.charAt(i6 - 1) == '\n') {
        i12 = i3 + 1;
      }
      i9 += a();
      if (i11 > this.C) {
        this.C = i11;
      }
      i10 += 1;
      i11 = i1;
      i13 = i6;
      i1 = i7;
      localObject3 = localObject2;
      localObject2 = localObject1;
      i3 = i12;
      i7 = i2;
      localObject1 = localObject3;
      i6 = i11;
      i2 = i13;
    }
    e(paramCanvas);
    if (!isWordWrap()) {
      d(paramCanvas);
    }
  }
  
  private void d(int paramInt1, int paramInt2)
  {
    boolean bool;
    if ((paramInt1 <= paramInt2) && (paramInt1 >= 0)) {
      bool = true;
    } else {
      bool = false;
    }
    r.a(bool, "Invalid startRow and/or endRow");
    Rect localRect = this.g.a();
    Paint.FontMetricsInt localFontMetricsInt = this.B.getFontMetricsInt();
    super.invalidate(0, Math.max(0, paramInt1 * a() + getPaddingTop() - Math.max(localRect.top, localFontMetricsInt.descent)), getScrollX() + getWidth(), paramInt2 * a() + getPaddingTop() + localRect.bottom);
  }
  
  private void d(Canvas paramCanvas)
  {
    Object localObject = k.d();
    if (localObject != null)
    {
      if (((ArrayList)localObject).isEmpty()) {
        return;
      }
      Rect localRect = paramCanvas.getClipBounds();
      int i1 = localRect.top;
      int i2 = localRect.bottom;
      localObject = ((ArrayList)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        localRect = (Rect)((Iterator)localObject).next();
        int i3 = (localRect.top + 1) * a();
        int i4 = localRect.bottom * a();
        if ((i4 >= i1) && (i3 <= i2))
        {
          float f1 = Math.min(b(localRect.left).a(), b(localRect.right).a());
          paramCanvas.drawLine(f1, i3, f1, i4, this.R);
        }
      }
    }
  }
  
  private void e(Canvas paramCanvas)
  {
    if (this.n)
    {
      int i1 = getPaintBaseline(this.A);
      int i2 = this.B.getColor();
      this.B.setColor(this.m.a(b.a.h));
      a(paramCanvas, 0, i1, Math.max(this.C, getContentWidth()));
      this.B.setColor(i2);
    }
  }
  
  private final boolean e(int paramInt1, int paramInt2)
  {
    return (paramInt1 >= 0) && (paramInt1 < getWidth()) && (paramInt2 >= 0) && (paramInt2 < getHeight());
  }
  
  private int f(int paramInt)
  {
    int i2 = View.MeasureSpec.getMode(paramInt);
    int i1 = View.MeasureSpec.getSize(paramInt);
    paramInt = i1;
    if (i2 != 1073741824)
    {
      paramInt = i1;
      if (i2 != Integer.MIN_VALUE)
      {
        paramInt = Integer.MAX_VALUE;
        r.a("MeasureSpec cannot be UNSPECIFIED. Setting dimensions to max.");
      }
    }
    return paramInt;
  }
  
  private void f()
  {
    this.ac = ((AccessibilityManager)getContext().getSystemService("accessibility")).isTouchExplorationEnabled();
    this.w = new a(null);
    this.G = ((ClipboardManager)getContext().getSystemService("clipboard"));
    this.B = new Paint();
    this.B.setAntiAlias(true);
    this.B.setTextSize(d);
    this.R = new Paint();
    this.R.setAntiAlias(true);
    this.R.setTextSize(d);
    setLongClickable(true);
    setFocusableInTouchMode(true);
    setHapticFeedbackEnabled(true);
    this.y = new o()
    {
      public void a(int paramAnonymousInt) {}
    };
    this.z = new e()
    {
      public void a(boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (paramAnonymousBoolean)
        {
          c.g(c.this).a();
          return;
        }
        c.g(c.this).b();
      }
    };
    this.K = new f()
    {
      public void a(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (c.h(c.this))
        {
          paramAnonymousCharSequence = AccessibilityEvent.obtain(16);
          paramAnonymousCharSequence.setFromIndex(paramAnonymousInt1 - paramAnonymousInt2);
          paramAnonymousCharSequence.setRemovedCount(paramAnonymousInt2);
          paramAnonymousCharSequence.setBeforeText(c.this.h);
          c.this.sendAccessibilityEventUnchecked(paramAnonymousCharSequence);
        }
        c.this.s.b();
      }
      
      public void a(String paramAnonymousString, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (c.h(c.this))
        {
          paramAnonymousString = AccessibilityEvent.obtain(16);
          paramAnonymousString.setFromIndex(paramAnonymousInt1 - 1);
          paramAnonymousString.setAddedCount(1);
          c.this.sendAccessibilityEventUnchecked(paramAnonymousString);
        }
        c.this.s.b();
      }
      
      public void b(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (c.h(c.this))
        {
          paramAnonymousCharSequence = AccessibilityEvent.obtain(16);
          paramAnonymousCharSequence.setFromIndex(paramAnonymousInt1 - paramAnonymousInt2);
          paramAnonymousCharSequence.setAddedCount(paramAnonymousInt2);
          c.this.sendAccessibilityEventUnchecked(paramAnonymousCharSequence);
        }
        if (!c.this.t) {
          return;
        }
        paramAnonymousInt1 = c.this.i;
        while (paramAnonymousInt1 >= 0)
        {
          char c = c.this.h.charAt(paramAnonymousInt1 - 1);
          if ((!Character.isLetterOrDigit(c)) && (c != '_') && (c != '.')) {
            break;
          }
          paramAnonymousInt1 -= 1;
        }
        if (c.this.i - paramAnonymousInt1 > 0)
        {
          c.this.s.a(c.this.h.subSequence(paramAnonymousInt1, c.this.i - paramAnonymousInt1));
          return;
        }
        c.this.s.b();
      }
    };
    g();
    this.F = new b(this);
    this.s = new a(this);
    this.s.a(i.g());
    invalidate();
  }
  
  private void g()
  {
    this.i = 0;
    this.A = 0;
    this.C = 0;
    this.w.c(false);
    this.w.f();
    this.h.h();
    if ((getContentWidth() > 0) || (!this.h.j())) {
      this.h.k();
    }
    this.y.a(0);
    scrollTo(0, 0);
  }
  
  private void g(int paramInt)
  {
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    }
    r.a(bool, "Invalid startRow");
    Rect localRect = this.g.a();
    Paint.FontMetricsInt localFontMetricsInt = this.B.getFontMetricsInt();
    super.invalidate(0, Math.max(0, paramInt * a() + getPaddingTop() - Math.max(localRect.top, localFontMetricsInt.descent)), getScrollX() + getWidth(), getScrollY() + getHeight());
  }
  
  private void h()
  {
    d(this.A, this.A + 1);
  }
  
  private boolean h(int paramInt)
  {
    boolean bool;
    if ((paramInt >= 0) && (paramInt < this.h.g())) {
      bool = true;
    } else {
      bool = false;
    }
    r.a(bool, "Invalid charOffset given");
    int i1 = i(paramInt);
    paramInt = j(paramInt);
    if ((i1 == 0) && (paramInt == 0)) {
      return false;
    }
    scrollBy(paramInt, i1);
    return true;
  }
  
  private int i(int paramInt)
  {
    paramInt = this.h.b(paramInt) * a();
    int i1 = a() + paramInt;
    if (paramInt < getScrollY()) {
      return paramInt - getScrollY();
    }
    if (i1 > getScrollY() + getContentHeight()) {
      return i1 - getScrollY() - getContentHeight();
    }
    return 0;
  }
  
  private void i()
  {
    d(this.h.b(this.j), this.h.b(this.k) + 1);
  }
  
  private int j(int paramInt)
  {
    m localm = b(paramInt);
    int i1 = localm.a();
    paramInt = localm.b();
    if (paramInt > getScrollX() + getContentWidth()) {
      paramInt = paramInt - getScrollX() - getContentWidth();
    } else {
      paramInt = 0;
    }
    if (i1 < getScrollX() + this.S) {
      paramInt = i1 - getScrollX() - this.S;
    }
    return paramInt;
  }
  
  protected int a()
  {
    Paint.FontMetricsInt localFontMetricsInt = this.B.getFontMetricsInt();
    return localFontMetricsInt.descent - localFontMetricsInt.ascent;
  }
  
  protected int a(int paramInt)
  {
    if (this.o) {
      return this.l * (int)this.B.measureText("·", 0, "·".length());
    }
    paramInt = (paramInt - this.D) / this.aa;
    int i1 = this.l;
    return (this.l - paramInt % i1) * this.aa;
  }
  
  int a(int paramInt1, int paramInt2)
  {
    paramInt2 /= a();
    if (paramInt2 > this.h.f()) {
      return this.h.g() - 1;
    }
    int i5 = this.h.d(paramInt2);
    if (i5 < 0) {
      return -1;
    }
    if (paramInt1 < 0) {
      return i5;
    }
    String str = this.h.a(paramInt2);
    int i2 = this.D;
    int i6 = str.length();
    int i3 = 0;
    for (int i4 = 0; i3 < i6; i4 = paramInt2)
    {
      char c1 = str.charAt(i3);
      switch (c1)
      {
      default: 
        if (i4 != 0) {
          paramInt2 = 0;
        }
        break;
      case '?': 
      case '?': 
        int i1 = str.charAt(i3 + 1);
        i2 += (int)this.B.measureText(new char[] { c1, i1 }, 0, 2);
        paramInt2 = 1;
        break;
      case ' ': 
        paramInt2 = getSpaceAdvance();
        break;
      case '\n': 
      case '￿': 
        paramInt2 = getEOLAdvance();
        break;
      }
      for (paramInt2 = a(i2);; paramInt2 = getCharAdvance(c1))
      {
        i2 += paramInt2;
        paramInt2 = i4;
        break;
      }
      if (i2 >= paramInt1) {
        break;
      }
      i3 += 1;
    }
    if (i3 < str.length()) {
      return i5 + i3;
    }
    return i5 + i3 - 1;
  }
  
  void a(boolean paramBoolean)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    if (paramBoolean)
    {
      localInputMethodManager.showSoftInput(this, 0);
      return;
    }
    localInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
  }
  
  int b(int paramInt1, int paramInt2)
  {
    paramInt2 /= a();
    int i5 = this.h.d(paramInt2);
    if (i5 >= 0)
    {
      if (paramInt1 < 0) {
        return -1;
      }
      String str = this.h.a(paramInt2);
      int i6 = str.length();
      int i3 = 0;
      int i4 = 0;
      int i2 = 0;
      while (i3 < i6)
      {
        char c1 = str.charAt(i3);
        switch (c1)
        {
        default: 
          if (i4 != 0) {
            paramInt2 = 0;
          }
          break;
        case '?': 
        case '?': 
          int i1 = str.charAt(i3 + 1);
          i2 += (int)this.B.measureText(new char[] { c1, i1 }, 0, 2);
          paramInt2 = 1;
          break;
        case ' ': 
          paramInt2 = getSpaceAdvance();
          break;
        case '\n': 
        case '￿': 
          paramInt2 = getEOLAdvance();
          break;
        }
        for (paramInt2 = a(i2);; paramInt2 = getCharAdvance(c1))
        {
          i2 += paramInt2;
          paramInt2 = i4;
          break;
        }
        if (i2 >= paramInt1) {
          break;
        }
        i3 += 1;
        i4 = paramInt2;
      }
      if (i3 < str.length()) {
        return i5 + i3;
      }
    }
    return -1;
  }
  
  protected m b(int paramInt)
  {
    int i4 = this.h.b(paramInt);
    int i7 = this.h.d(i4);
    int i2 = this.D;
    int i3 = this.D;
    String str = this.h.a(i4);
    int i8 = str.length();
    i4 = 0;
    int i5 = 0;
    while ((i7 + i5 <= paramInt) && (i5 < i8))
    {
      char c1 = str.charAt(i5);
      switch (c1)
      {
      default: 
        if (i4 != 0)
        {
          i2 = i3;
          i4 = 0;
        }
        break;
      case '?': 
      case '?': 
        int i1 = str.charAt(i5 + 1);
        i2 = (int)this.B.measureText(new char[] { c1, i1 }, 0, 2) + i3;
        i4 = 1;
        break;
      case ' ': 
        i2 = getSpaceAdvance();
        break;
      case '\n': 
      case '￿': 
        i2 = getEOLAdvance();
        break;
      }
      for (i2 = a(i3);; i2 = getCharAdvance(c1))
      {
        i2 += i3;
        break;
      }
      i5 += 1;
      int i6 = i2;
      i2 = i3;
      i3 = i6;
    }
    return new m(i2, i3);
  }
  
  void b()
  {
    removeCallbacks(this.T);
    removeCallbacks(this.U);
    removeCallbacks(this.V);
    removeCallbacks(this.W);
  }
  
  Rect c(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.h.g()))
    {
      int i1 = this.h.b(paramInt) * a();
      int i2 = a();
      m localm = b(paramInt);
      return new Rect(localm.a(), i1, localm.b(), i2 + i1);
    }
    return new Rect(-1, -1, -1, -1);
  }
  
  void c(int paramInt1, int paramInt2)
  {
    this.v.fling(getScrollX(), getScrollY(), paramInt1, paramInt2, 0, getMaxScrollX(), 0, getMaxScrollY());
    postInvalidate();
  }
  
  protected boolean c()
  {
    return this.A == 0;
  }
  
  public void cancelSpanning()
  {
    this.w.b();
  }
  
  public void computeScroll()
  {
    if (this.v.computeScrollOffset())
    {
      scrollTo(this.v.getCurrX(), this.v.getCurrY());
      postInvalidate();
    }
  }
  
  protected int computeVerticalScrollOffset()
  {
    return getScrollY();
  }
  
  protected int computeVerticalScrollRange()
  {
    return this.h.f() * a() + getPaddingTop() + getPaddingBottom();
  }
  
  public void copy()
  {
    if (this.j != this.k) {
      this.w.b(this.G);
    }
    selectText(false);
  }
  
  public void copy(ClipboardManager paramClipboardManager)
  {
    this.w.b(paramClipboardManager);
  }
  
  public AccessibilityNodeInfo createAccessibilityNodeInfo()
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = super.createAccessibilityNodeInfo();
    if (Build.VERSION.SDK_INT > 20)
    {
      localAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
      localAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
      localAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_NEXT_AT_MOVEMENT_GRANULARITY);
      localAccessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY);
    }
    else if (Build.VERSION.SDK_INT > 15)
    {
      localAccessibilityNodeInfo.addAction(4096);
      localAccessibilityNodeInfo.addAction(8192);
      localAccessibilityNodeInfo.addAction(256);
      localAccessibilityNodeInfo.addAction(512);
    }
    if (Build.VERSION.SDK_INT >= 18) {
      localAccessibilityNodeInfo.setTextSelection(getSelectionStart(), getSelectionEnd());
    }
    localAccessibilityNodeInfo.setFocusable(true);
    if (Build.VERSION.SDK_INT >= 18) {
      localAccessibilityNodeInfo.setEditable(true);
    }
    if (Build.VERSION.SDK_INT >= 19) {
      localAccessibilityNodeInfo.setMultiLine(true);
    }
    return localAccessibilityNodeInfo;
  }
  
  public com.a.a.b.f createDocumentProvider()
  {
    return new com.a.a.b.f(this.h);
  }
  
  public void cut()
  {
    if (this.j != this.k) {
      this.w.a(this.G);
    }
  }
  
  public void cut(ClipboardManager paramClipboardManager)
  {
    this.w.a(paramClipboardManager);
  }
  
  protected boolean d()
  {
    return this.A == this.h.f() - 1;
  }
  
  boolean d(int paramInt)
  {
    Runnable localRunnable;
    switch (paramInt)
    {
    default: 
      r.a("Invalid scroll direction");
      break;
    case 3: 
      removeCallbacks(this.W);
      if ((e()) || (this.A != this.h.b(this.i + 1))) {
        break label182;
      }
      localRunnable = this.W;
      break;
    case 2: 
      removeCallbacks(this.V);
      if ((this.i <= 0) || (this.A != this.h.b(this.i - 1))) {
        break label182;
      }
      localRunnable = this.V;
      break;
    case 1: 
      removeCallbacks(this.T);
      if (d()) {
        break label182;
      }
      localRunnable = this.T;
      break;
    case 0: 
      removeCallbacks(this.U);
      if (c()) {
        break label182;
      }
      localRunnable = this.U;
    }
    post(localRunnable);
    return true;
    label182:
    return false;
  }
  
  protected int e(int paramInt)
  {
    int i1 = this.h.b(paramInt);
    boolean bool;
    if (i1 >= 0) {
      bool = true;
    } else {
      bool = false;
    }
    r.a(bool, "Invalid char offset given to getColumn");
    return paramInt - this.h.d(i1);
  }
  
  protected boolean e()
  {
    return this.i == this.h.g() - 1;
  }
  
  public void focusCaret()
  {
    h(this.i);
  }
  
  public void focusSelectionEnd()
  {
    this.w.d(false);
  }
  
  public void focusSelectionStart()
  {
    this.w.d(true);
  }
  
  public void format()
  {
    selectText(false);
    CharSequence localCharSequence = com.a.a.b.a.a(new com.a.a.b.f(this.h), this.q);
    this.h.d();
    this.h.a(0, this.h.g() - 1, System.nanoTime());
    this.h.a(localCharSequence.toString().toCharArray(), 0, System.nanoTime());
    this.h.e();
    this.h.h();
    respan();
    invalidate();
  }
  
  public int getAdvance(char paramChar)
  {
    int i1;
    switch (paramChar)
    {
    default: 
      if (this.P != 0) {
        i1 = this.P;
      }
    case ' ': 
    case '\n': 
    case '￿': 
    case '\t': 
      for (float f1 = this.B.measureText(new char[] { i1, paramChar }, 0, 2);; f1 = this.B.measureText(new char[] { paramChar }, 0, 1))
      {
        return (int)f1;
        return getSpaceAdvance();
        return getEOLAdvance();
        return getTabAdvance();
      }
    }
    return 0;
  }
  
  public int getAdvance(char paramChar, int paramInt)
  {
    int i1;
    switch (paramChar)
    {
    default: 
      if (this.P != 0) {
        i1 = this.P;
      }
    case ' ': 
    case '\n': 
    case '￿': 
    case '\t': 
      for (float f1 = this.B.measureText(new char[] { i1, paramChar }, 0, 2);; f1 = this.B.measureText(new char[] { paramChar }, 0, 1))
      {
        return (int)f1;
        return getSpaceAdvance();
        return getEOLAdvance();
        return a(paramInt);
      }
    }
    return 0;
  }
  
  public int getAutoIndentWidth()
  {
    return this.q;
  }
  
  public int getCaretPosition()
  {
    return this.i;
  }
  
  public int getCaretRow()
  {
    return this.A;
  }
  
  public int getCaretX()
  {
    return this.I;
  }
  
  public int getCaretY()
  {
    return this.J;
  }
  
  public int getCharAdvance(char paramChar)
  {
    return (int)this.B.measureText(new char[] { paramChar }, 0, 1);
  }
  
  public com.a.a.b.b getColorScheme()
  {
    return this.m;
  }
  
  protected int getContentHeight()
  {
    return getHeight() - getPaddingTop() - getPaddingBottom();
  }
  
  protected int getContentWidth()
  {
    return getWidth() - getPaddingLeft() - getPaddingRight();
  }
  
  protected int getEOLAdvance()
  {
    if (this.o) {}
    for (float f1 = this.B.measureText("↵", 0, "↵".length());; f1 = a * this.B.measureText(" ", 0, 1)) {
      return (int)f1;
    }
  }
  
  public int getLeftOffset()
  {
    return this.D;
  }
  
  public int getLength()
  {
    return this.h.g();
  }
  
  int getMaxScrollX()
  {
    if (isWordWrap()) {
      return this.D;
    }
    return Math.max(0, this.C - getContentWidth() + this.g.a().right + this.S);
  }
  
  int getMaxScrollY()
  {
    return Math.max(0, this.h.f() * a() - getContentHeight() / 2 + this.g.a().bottom);
  }
  
  protected int getNumVisibleRows()
  {
    return (int)Math.ceil(getContentHeight() / a());
  }
  
  public int getPaintBaseline(int paramInt)
  {
    Paint.FontMetricsInt localFontMetricsInt = this.B.getFontMetricsInt();
    return (paramInt + 1) * a() - localFontMetricsInt.descent;
  }
  
  public final int getRowWidth()
  {
    return getContentWidth() - this.D;
  }
  
  public int getSelectionEnd()
  {
    if (this.k < 0) {
      return this.i;
    }
    return this.k;
  }
  
  public int getSelectionStart()
  {
    if (this.j < 0) {
      return this.i;
    }
    return this.j;
  }
  
  protected int getSpaceAdvance()
  {
    if (this.o) {
      return (int)this.B.measureText("·", 0, "·".length());
    }
    return this.aa;
  }
  
  protected int getTabAdvance()
  {
    int i1;
    if (this.o) {
      i1 = this.l;
    }
    for (int i2 = (int)this.B.measureText("·", 0, "·".length());; i2 = this.aa)
    {
      return i1 * i2;
      i1 = this.l;
    }
  }
  
  public float getTextSize()
  {
    return this.B.getTextSize();
  }
  
  public int getTopOffset()
  {
    return this.L;
  }
  
  public Parcelable getUiState()
  {
    return new c(this);
  }
  
  public float getZoom()
  {
    return this.H;
  }
  
  public boolean hasLayout()
  {
    return getWidth() == 0;
  }
  
  public boolean inSelectionRange(int paramInt)
  {
    return this.w.b(paramInt);
  }
  
  public boolean isAccessibilityEnabled()
  {
    return this.ac;
  }
  
  public boolean isEdited()
  {
    return this.f;
  }
  
  public boolean isFlingScrolling()
  {
    return this.v.isFinished() ^ true;
  }
  
  public boolean isSaveEnabled()
  {
    return true;
  }
  
  public final boolean isSelectText()
  {
    return this.w.g();
  }
  
  public final boolean isSelectText2()
  {
    return this.w.h();
  }
  
  public boolean isShowLineNumbers()
  {
    return this.E;
  }
  
  public boolean isWordWrap()
  {
    return this.h.j();
  }
  
  public void moveCaret(int paramInt)
  {
    this.w.a(paramInt);
  }
  
  public void moveCaretDown()
  {
    this.w.c();
  }
  
  public void moveCaretLeft()
  {
    this.w.b(false);
  }
  
  public void moveCaretRight()
  {
    this.w.a(false);
  }
  
  public void moveCaretUp()
  {
    this.w.d();
  }
  
  public boolean onCheckIsTextEditor()
  {
    return true;
  }
  
  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    paramEditorInfo.inputType = 131073;
    paramEditorInfo.imeOptions = 1342177286;
    if (this.x == null) {
      this.x = new b(this);
    } else {
      this.x.a();
    }
    return this.x;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.clipRect(getScrollX() + getPaddingLeft(), getScrollY() + getPaddingTop(), getScrollX() + getWidth() - getPaddingRight(), getScrollY() + getHeight() - getPaddingBottom());
    paramCanvas.translate(getPaddingLeft(), getPaddingTop());
    c(paramCanvas);
    paramCanvas.restore();
    this.g.a(paramCanvas);
  }
  
  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    h();
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (((paramMotionEvent.getSource() & 0x2) != 0) && (paramMotionEvent.getAction() == 8))
    {
      a(0.0F, -paramMotionEvent.getAxisValue(9) * a());
      return true;
    }
    return super.onGenericMotionEvent(paramMotionEvent);
  }
  
  public boolean onHoverEvent(MotionEvent paramMotionEvent)
  {
    if (this.ac)
    {
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      int i1 = paramMotionEvent.getAction();
      if (i1 != 7) {
        switch (i1)
        {
        default: 
          break;
        case 10: 
          this.g.a(paramMotionEvent);
          break;
        case 9: 
          this.ad = paramMotionEvent;
          break;
        }
      } else {
        this.g.onScroll(this.ad, paramMotionEvent, this.ae - f1, this.af - f2);
      }
      this.ae = f1;
      this.af = f2;
    }
    return super.onHoverEvent(paramMotionEvent);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.g.a(paramInt, paramKeyEvent)) {
      return true;
    }
    if (d.b(paramKeyEvent))
    {
      a(paramInt, paramKeyEvent);
      return true;
    }
    if ((paramInt != 63) && (paramInt != 61185))
    {
      char c1 = d.a(paramKeyEvent);
      if (c1 == 0) {
        return super.onKeyDown(paramInt, paramKeyEvent);
      }
      paramInt = paramKeyEvent.getRepeatCount();
      if (paramInt == 1)
      {
        if (this.r)
        {
          a(c1);
          return true;
        }
        b(c1);
        return true;
      }
      if ((paramInt == 0) || ((this.r) && (!Character.isLowerCase(c1))) || ((!this.r) && (u.get(c1) == null))) {
        this.w.a(c1);
      }
      return true;
    }
    a((String)u.get(61185), false);
    return true;
  }
  
  public boolean onKeyPreIme(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((this.r) && (paramKeyEvent.getRepeatCount() == 1) && (paramKeyEvent.getAction() == 0))
    {
      char c1 = d.a(paramKeyEvent);
      if ((Character.isLowerCase(c1)) && (c1 == Character.toLowerCase(this.h.charAt(this.i - 1))))
      {
        this.w.a('\b');
        this.w.a(Character.toUpperCase(c1));
        return true;
      }
    }
    return super.onKeyPreIme(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (this.g.b(paramInt, paramKeyEvent)) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramBoolean)
    {
      Rect localRect = new Rect();
      getWindowVisibleDisplayFrame(localRect);
      this.L = (localRect.top + localRect.height() - getHeight());
      if (!this.Q) {
        respan();
      }
      boolean bool;
      if (paramInt3 > 0) {
        bool = true;
      } else {
        bool = false;
      }
      this.Q = bool;
      invalidate();
      this.s.c(getWidth() / 2);
    }
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(f(paramInt1), f(paramInt2));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((this.h.j()) && (paramInt3 != paramInt1)) {
      this.h.k();
    }
    this.w.e();
    if (paramInt2 < paramInt4) {
      h(this.i);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (isFocused())
    {
      this.g.b(paramMotionEvent);
      return true;
    }
    if (((paramMotionEvent.getAction() & 0xFF) == 1) && (e((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()))) {
      requestFocus();
    }
    return true;
  }
  
  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    int i1 = Math.round(paramMotionEvent.getX());
    int i3 = Math.round(paramMotionEvent.getY());
    int i2;
    for (;;)
    {
      i2 = i1;
      if (i1 <= 0) {
        break;
      }
      this.w.a(false);
      i1 -= 1;
    }
    for (;;)
    {
      i1 = i3;
      if (i2 >= 0) {
        break;
      }
      this.w.b(false);
      i2 += 1;
    }
    for (;;)
    {
      i2 = i1;
      if (i1 <= 0) {
        break;
      }
      this.w.c();
      i1 -= 1;
    }
    while (i2 < 0)
    {
      this.w.d();
      i2 += 1;
    }
    return true;
  }
  
  public void paste()
  {
    CharSequence localCharSequence = this.G.getText();
    if (localCharSequence != null) {
      this.w.a(localCharSequence.toString());
    }
  }
  
  public void paste(String paramString)
  {
    this.w.a(paramString);
  }
  
  public boolean performAccessibilityAction(int paramInt, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT < 16) {
      return super.performAccessibilityAction(paramInt, paramBundle);
    }
    if (paramInt != 256)
    {
      if (paramInt != 512) {
        return super.performAccessibilityAction(paramInt, paramBundle);
      }
      paramInt = paramBundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
      if (paramInt != 1)
      {
        if (paramInt != 4) {
          return true;
        }
        moveCaretUp();
        return true;
      }
      moveCaretLeft();
      return true;
    }
    paramInt = paramBundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
    if (paramInt != 1)
    {
      if (paramInt != 4) {
        return true;
      }
      moveCaretDown();
      return true;
    }
    moveCaretRight();
    return true;
  }
  
  public void replaceText(int paramInt1, int paramInt2, String paramString)
  {
    this.h.d();
    this.w.a(paramInt1, paramInt2, paramString);
    this.w.f();
    this.h.e();
  }
  
  public void respan()
  {
    this.w.a();
  }
  
  public void restoreUiState(Parcelable paramParcelable)
  {
    paramParcelable = (c)paramParcelable;
    final int i1 = paramParcelable.a;
    if (paramParcelable.d)
    {
      post(new Runnable()
      {
        public void run()
        {
          c.this.setSelectionRange(this.a, this.b - this.a);
          if (i1 < this.b) {
            c.this.focusSelectionStart();
          }
        }
      });
      return;
    }
    post(new Runnable()
    {
      public void run()
      {
        c.this.moveCaret(i1);
      }
    });
  }
  
  public void selectAll()
  {
    this.w.a(0, this.h.g() - 1, false, true);
  }
  
  public void selectText(boolean paramBoolean)
  {
    a locala;
    if ((this.w.g()) && (!paramBoolean))
    {
      i();
      locala = this.w;
    }
    for (paramBoolean = false;; paramBoolean = true)
    {
      locala.c(paramBoolean);
      return;
      if ((this.w.g()) || (!paramBoolean)) {
        break;
      }
      h();
      locala = this.w;
    }
  }
  
  public void setAutoComplete(boolean paramBoolean)
  {
    this.t = paramBoolean;
  }
  
  public void setAutoIndent(boolean paramBoolean)
  {
    this.p = paramBoolean;
  }
  
  public void setAutoIndentWidth(int paramInt)
  {
    this.q = paramInt;
  }
  
  public void setBoldTypeface(Typeface paramTypeface)
  {
    this.N = paramTypeface;
  }
  
  public void setChirality(boolean paramBoolean)
  {
    this.g.a(paramBoolean);
  }
  
  public void setColorScheme(com.a.a.b.b paramb)
  {
    this.m = paramb;
    this.g.a(paramb);
    setBackgroundColor(paramb.a(b.a.b));
  }
  
  public void setDocumentProvider(com.a.a.b.f paramf)
  {
    this.h = paramf;
    g();
    this.w.b();
    this.w.a();
    invalidate();
    if (this.ac) {
      setContentDescription(this.h);
    }
  }
  
  public void setEdited(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }
  
  public void setHighlightCurrentRow(boolean paramBoolean)
  {
    this.n = paramBoolean;
    h();
  }
  
  public void setItalicTypeface(Typeface paramTypeface)
  {
    this.O = paramTypeface;
  }
  
  public void setLongPressCaps(boolean paramBoolean)
  {
    this.r = paramBoolean;
  }
  
  public void setNavigationMethod(g paramg)
  {
    this.g = paramg;
  }
  
  public void setNonPrintingCharVisibility(boolean paramBoolean)
  {
    if ((this.o ^ paramBoolean))
    {
      this.o = paramBoolean;
      if (this.h.j()) {
        this.h.k();
      }
      this.w.e();
      if (!h(this.i)) {
        invalidate();
      }
    }
  }
  
  public void setOnSelectionChangedListener(e parame)
  {
    this.z = parame;
  }
  
  public void setRowListener(o paramo)
  {
    this.y = paramo;
  }
  
  public void setSelection(int paramInt1, int paramInt2)
  {
    this.w.a(paramInt1, paramInt2, true, false);
  }
  
  public void setSelectionRange(int paramInt1, int paramInt2)
  {
    this.w.a(paramInt1, paramInt2, true, true);
  }
  
  public void setShowLineNumbers(boolean paramBoolean)
  {
    this.E = paramBoolean;
  }
  
  public void setTabSpaces(int paramInt)
  {
    if (paramInt < 0) {
      return;
    }
    this.l = paramInt;
    if (this.h.j()) {
      this.h.k();
    }
    this.w.e();
    if (!h(this.i)) {
      invalidate();
    }
  }
  
  public void setTextSize(int paramInt)
  {
    if ((paramInt > 8) && (paramInt < 80))
    {
      float f1 = paramInt;
      if (f1 == this.B.getTextSize()) {
        return;
      }
      double d2 = a();
      double d3 = getAdvance('a');
      this.H = (paramInt / d);
      this.B.setTextSize(f1);
      this.R.setTextSize(f1);
      if (this.h.j()) {
        this.h.k();
      }
      this.w.e();
      double d1 = getScrollX();
      d3 = getAdvance('a') / d3;
      double d4 = getScrollY();
      d2 = a() / d2;
      scrollTo((int)(d1 * d3), (int)(d4 * d2));
      this.S = ((int)this.B.measureText("a"));
      this.aa = ((int)this.B.measureText(" "));
      invalidate();
    }
  }
  
  public void setTypeface(Typeface paramTypeface)
  {
    this.M = paramTypeface;
    this.N = Typeface.create(paramTypeface, 1);
    this.O = Typeface.create(paramTypeface, 2);
    this.B.setTypeface(paramTypeface);
    this.R.setTypeface(paramTypeface);
    if (this.h.j()) {
      this.h.k();
    }
    this.w.e();
    if (!h(this.i)) {
      invalidate();
    }
  }
  
  public void setWordWrap(boolean paramBoolean)
  {
    this.h.a(paramBoolean);
    if (paramBoolean)
    {
      this.C = 0;
      scrollTo(0, 0);
    }
    this.w.e();
    if (!h(this.i)) {
      invalidate();
    }
  }
  
  public void setZoom(float paramFloat)
  {
    if ((paramFloat > 0.5D) && (paramFloat < 5.0F))
    {
      if (paramFloat == this.H) {
        return;
      }
      this.H = paramFloat;
      int i1 = (int)(paramFloat * d);
      Paint localPaint = this.B;
      paramFloat = i1;
      localPaint.setTextSize(paramFloat);
      this.R.setTextSize(paramFloat);
      if (this.h.j()) {
        this.h.k();
      }
      this.w.e();
      this.S = ((int)this.B.measureText("a"));
      invalidate();
    }
  }
  
  public final void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (getHeight() == 0) {
      return;
    }
    if (AnimationUtils.currentAnimationTimeMillis() - this.ab > 250L)
    {
      int i1 = getScrollY();
      int i2 = getScrollX();
      this.v.startScroll(i2, i1, paramInt1, paramInt2);
      postInvalidate();
    }
    else
    {
      if (!this.v.isFinished()) {
        this.v.abortAnimation();
      }
      scrollBy(paramInt1, paramInt2);
    }
    this.ab = AnimationUtils.currentAnimationTimeMillis();
  }
  
  public final void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollBy(paramInt1 - getScrollX(), paramInt2 - getScrollY());
  }
  
  public void stopFlingScrolling()
  {
    this.v.forceFinished(true);
  }
  
  private class a
    implements k.a
  {
    private final k b = new k(this);
    private boolean c = false;
    private boolean d;
    
    private a() {}
    
    private void b(int paramInt1, int paramInt2)
    {
      if ((c.h(c.this)) && (Build.VERSION.SDK_INT >= 16))
      {
        AccessibilityRecord.obtain();
        AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(131072);
        int i = paramInt1 - paramInt2;
        if (i * i == 1) {
          localAccessibilityEvent.setMovementGranularity(1);
        }
        if (paramInt1 > paramInt2) {}
        for (i = 512;; i = 256)
        {
          localAccessibilityEvent.setAction(i);
          break;
        }
        localAccessibilityEvent.setFromIndex(Math.min(paramInt1, paramInt2));
        localAccessibilityEvent.setToIndex(Math.max(paramInt1, paramInt2));
        c.this.sendAccessibilityEventUnchecked(localAccessibilityEvent);
      }
      if (!this.c) {
        return;
      }
      if (paramInt1 < c.this.k) {
        if (paramInt2 > c.this.k)
        {
          c.this.j = c.this.k;
          c.this.k = paramInt2;
          return;
        }
      }
      for (;;)
      {
        c.this.j = paramInt2;
        return;
        if (paramInt2 >= c.this.j) {
          break;
        }
        c.this.k = c.this.j;
      }
    }
    
    private char[] j()
    {
      int i = c.this.h.c(c.this.i);
      int k = c.this.h.e(i);
      c.this.h.f(k);
      int j = 0;
      i = 0;
      while (c.this.h.a())
      {
        m = c.this.h.b();
        if (((m != 32) && (m != 9)) || (k + i >= c.this.i)) {
          break;
        }
        i += 1;
      }
      int m = i + c.this.q * com.a.a.b.a.a(c.this.h.subSequence(k, c.this.i - k));
      if (m < 0) {
        return new char[] { '\n' };
      }
      char[] arrayOfChar = new char[m + 1];
      arrayOfChar[0] = '\n';
      c.this.h.f(k);
      i = j;
      while (i < m)
      {
        i += 1;
        arrayOfChar[i] = ' ';
      }
      return arrayOfChar;
    }
    
    private void k()
    {
      int i = c.d(c.this);
      e();
      if (!c.b(c.this, c.this.i))
      {
        c.a(c.this, i, i + 1);
        c.m(c.this);
      }
      f();
    }
    
    public void a()
    {
      this.b.a(c.this.h);
    }
    
    public void a(char paramChar)
    {
      int i;
      if (this.c)
      {
        i();
        i = 1;
      }
      else
      {
        i = 0;
      }
      int j = c.d(c.this);
      int k = c.this.h.d(j);
      Object localObject;
      StringBuilder localStringBuilder;
      if (paramChar != '\b') {
        if (paramChar != '\n')
        {
          c.this.h.a(paramChar, c.this.i, System.nanoTime());
          a(true);
          localObject = c.i(c.this);
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramChar);
          localStringBuilder.append("");
          ((f)localObject).b(localStringBuilder.toString(), c.this.i, 1);
          if (!c.this.h.j()) {
            break label585;
          }
          i = j;
          if (k == c.this.h.d(j)) {}
        }
      }
      for (;;)
      {
        i = j - 1;
        do
        {
          for (;;)
          {
            c.a(c.this, i);
            break;
            if (c.this.p)
            {
              localObject = j();
              c.this.h.a((char[])localObject, c.this.i, System.nanoTime());
              a(c.this.i + localObject.length);
            }
            else
            {
              c.this.h.a(paramChar, c.this.i, System.nanoTime());
              a(true);
            }
            i = j;
            if (c.this.h.j())
            {
              i = j;
              if (k != c.this.h.d(j)) {
                i = j - 1;
              }
            }
            localObject = c.i(c.this);
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(paramChar);
            localStringBuilder.append("");
            ((f)localObject).a(localStringBuilder.toString(), c.this.i, 1);
          }
          if ((i != 0) || (c.this.i <= 0)) {
            break;
          }
          localObject = c.i(c.this);
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramChar);
          localStringBuilder.append("");
          ((f)localObject).a(localStringBuilder.toString(), c.this.i, 1);
          c.this.h.a(c.this.i - 1, System.nanoTime());
          if ((c.this.h.charAt(c.this.i - 2) == 55357) || (c.this.h.charAt(c.this.i - 2) == 55356))
          {
            c.this.h.a(c.this.i - 2, System.nanoTime());
            b(true);
          }
          b(true);
          if (c.d(c.this) < j)
          {
            c.a(c.this, c.d(c.this));
            break;
          }
          if (!c.this.h.j()) {
            break;
          }
          i = j;
        } while (k == c.this.h.d(j));
      }
      label585:
      c.this.setEdited(true);
      a();
    }
    
    public void a(int paramInt)
    {
      if ((paramInt >= 0) && (paramInt < c.this.h.g()))
      {
        b(c.this.i, paramInt);
        c.this.i = paramInt;
        k();
        return;
      }
      r.a("Invalid caret position");
    }
    
    void a(int paramInt1, int paramInt2)
    {
      int i = c.this.i - paramInt1;
      paramInt1 = i;
      if (i < 0) {
        paramInt1 = 0;
      }
      i = c.this.i + paramInt2;
      int j = c.this.h.g() - 1;
      paramInt2 = i;
      if (i > j) {
        paramInt2 = j;
      }
      b(paramInt1, paramInt2 - paramInt1, "");
    }
    
    void a(int paramInt1, int paramInt2, String paramString)
    {
      boolean bool = this.c;
      int i3 = 0;
      int n;
      int i2;
      if (bool)
      {
        j = c.this.h.b(c.this.j);
        n = c.this.h.d(j);
        i = c.this.k - c.this.j;
        if (i > 0)
        {
          c.this.i = c.this.j;
          c.this.h.a(c.this.j, i, System.nanoTime());
          if (j != c.d(c.this)) {
            i = 0;
          } else {
            i = 1;
          }
          i2 = 1;
        }
        else
        {
          i2 = 0;
          i = 1;
        }
        c(false);
      }
      else
      {
        j = c.d(c.this);
        n = c.this.h.d(c.d(c.this));
        i2 = 0;
        i = 1;
      }
      int k = j;
      int m = n;
      int i1 = i;
      if (paramInt2 > 0)
      {
        i1 = c.this.h.b(paramInt1);
        k = j;
        m = n;
        if (i1 < j)
        {
          m = c.this.h.d(i1);
          k = i1;
        }
        if (k != c.d(c.this)) {
          i = 0;
        }
        c.this.i = paramInt1;
        c.this.h.a(paramInt1, paramInt2, System.nanoTime());
        i2 = 1;
        i1 = i;
      }
      paramInt2 = k;
      int i = m;
      int j = i2;
      if (paramString != null)
      {
        paramInt2 = k;
        i = m;
        j = i2;
        if (paramString.length() > 0)
        {
          paramInt1 = c.this.h.b(paramInt1);
          paramInt2 = k;
          if (paramInt1 < k)
          {
            m = c.this.h.d(paramInt1);
            paramInt2 = paramInt1;
          }
          c.this.h.a(paramString.toCharArray(), c.this.i, System.nanoTime());
          c localc = c.this;
          localc.i += paramString.length();
          j = 1;
          i = m;
        }
      }
      if (j != 0)
      {
        c.this.setEdited(true);
        a();
      }
      paramInt1 = c.d(c.this);
      e();
      if (paramInt1 != c.d(c.this)) {
        paramInt1 = i3;
      } else {
        paramInt1 = i1;
      }
      if (!c.b(c.this, c.this.i))
      {
        j = paramInt2;
        if (c.this.h.j())
        {
          j = paramInt2;
          if (i != c.this.h.d(paramInt2)) {
            j = paramInt2 - 1;
          }
        }
        if ((paramInt1 != 0) && (!c.this.h.j()))
        {
          c.a(c.this, c.d(c.this), c.d(c.this) + 1);
          return;
        }
        c.a(c.this, j);
      }
    }
    
    public void a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
    {
      boolean bool;
      if ((paramInt1 >= 0) && (paramInt2 <= c.this.h.g() - 1) && (paramInt2 >= 0)) {
        bool = true;
      } else {
        bool = false;
      }
      r.a(bool, "Invalid range to select");
      if (this.c)
      {
        c.p(c.this);
      }
      else
      {
        c.m(c.this);
        if (paramBoolean2) {
          c(true);
        } else {
          this.c = true;
        }
      }
      c.this.j = paramInt1;
      c.this.k = (c.this.j + paramInt2);
      c.this.i = c.this.k;
      f();
      e();
      if (paramBoolean2) {
        c.o(c.this).a(g(), c.this.j, c.this.k);
      }
      paramBoolean2 = c.b(c.this, c.this.k);
      if (paramBoolean1) {
        paramBoolean2 = c.b(c.this, c.this.j);
      }
      if (!paramBoolean2) {
        c.p(c.this);
      }
    }
    
    public void a(ClipboardManager paramClipboardManager)
    {
      b(paramClipboardManager);
      i();
    }
    
    public void a(String paramString)
    {
      if (paramString == null) {
        return;
      }
      c.this.h.d();
      i();
      int j = c.d(c.this);
      int i = c.this.h.d(j);
      c.this.h.a(paramString.toCharArray(), c.this.i, System.nanoTime());
      c.this.h.e();
      c localc = c.this;
      localc.i += paramString.length();
      e();
      c.this.setEdited(true);
      a();
      f();
      if (!c.b(c.this, c.this.i))
      {
        if ((c.this.h.j()) && (i != c.this.h.d(j))) {
          i = j - 1;
        } else {
          i = j;
        }
        if ((j == c.d(c.this)) && (!c.this.h.j()))
        {
          c.a(c.this, i, i + 1);
          return;
        }
        c.a(c.this, i);
      }
    }
    
    public void a(final List<m> paramList)
    {
      c.this.post(new Runnable()
      {
        public void run()
        {
          c.this.h.a(paramList);
          c.this.invalidate();
        }
      });
    }
    
    public void a(boolean paramBoolean)
    {
      if (!c.this.e())
      {
        int i = c.d(c.this);
        c localc = c.this;
        localc.i += 1;
        e();
        b(c.this.i - 1, c.this.i);
        if (!c.b(c.this, c.this.i)) {
          c.a(c.this, i, c.d(c.this) + 1);
        }
        if (!paramBoolean) {
          f();
        }
      }
    }
    
    public void b()
    {
      this.b.b();
    }
    
    void b(int paramInt1, int paramInt2, String paramString)
    {
      boolean bool = this.c;
      int i3 = 0;
      int i2;
      if (bool)
      {
        j = c.this.h.b(c.this.j);
        n = c.this.h.d(j);
        i = c.this.k - c.this.j;
        if (i > 0)
        {
          c.this.i = c.this.j;
          c.this.h.a(c.this.j, i, System.nanoTime());
          if (j != c.d(c.this)) {
            i = 0;
          } else {
            i = 1;
          }
          i2 = 1;
        }
        else
        {
          i2 = 0;
          i = 1;
        }
        c(false);
      }
      else
      {
        j = c.d(c.this);
        n = c.this.h.d(c.d(c.this));
        i2 = 0;
        i = 1;
      }
      int k = j;
      int m = n;
      int i1 = i;
      if (paramInt2 > 0)
      {
        i1 = c.this.h.b(paramInt1);
        k = j;
        m = n;
        if (i1 < j)
        {
          m = c.this.h.d(i1);
          k = i1;
        }
        if (k != c.d(c.this)) {
          i = 0;
        }
        c.this.i = paramInt1;
        c.this.h.a(paramInt1, paramInt2, System.nanoTime());
        i2 = 1;
        i1 = i;
      }
      int i = k;
      int j = m;
      int n = i2;
      if (paramString != null)
      {
        i = k;
        j = m;
        n = i2;
        if (paramString.length() > 0)
        {
          paramInt1 = c.this.h.b(paramInt1);
          i = k;
          if (paramInt1 < k)
          {
            m = c.this.h.d(paramInt1);
            i = paramInt1;
          }
          c.this.h.a(paramString.toCharArray(), c.this.i, System.nanoTime());
          c localc = c.this;
          localc.i += paramString.length();
          n = 1;
          j = m;
        }
      }
      c.i(c.this).b(paramString, c.this.i, paramString.length() - paramInt2);
      if (n != 0)
      {
        c.this.setEdited(true);
        a();
      }
      paramInt1 = c.d(c.this);
      e();
      if (paramInt1 != c.d(c.this)) {
        paramInt1 = i3;
      } else {
        paramInt1 = i1;
      }
      if (!c.b(c.this, c.this.i))
      {
        paramInt2 = i;
        if (c.this.h.j())
        {
          paramInt2 = i;
          if (j != c.this.h.d(i)) {
            paramInt2 = i - 1;
          }
        }
        if ((paramInt1 != 0) && (!c.this.h.j()))
        {
          c.a(c.this, c.d(c.this), c.d(c.this) + 1);
          return;
        }
        c.a(c.this, paramInt2);
      }
    }
    
    public void b(ClipboardManager paramClipboardManager)
    {
      if ((this.c) && (c.this.j < c.this.k)) {
        paramClipboardManager.setText(c.this.h.subSequence(c.this.j, c.this.k - c.this.j));
      }
    }
    
    public void b(boolean paramBoolean)
    {
      if (c.this.i > 0)
      {
        int i = c.d(c.this);
        c localc = c.this;
        localc.i -= 1;
        e();
        b(c.this.i + 1, c.this.i);
        if (!c.b(c.this, c.this.i)) {
          c.a(c.this, c.d(c.this), i + 1);
        }
        if (!paramBoolean) {
          f();
        }
      }
    }
    
    public boolean b(int paramInt)
    {
      int i = c.this.j;
      boolean bool2 = false;
      if (i < 0) {
        return false;
      }
      boolean bool1 = bool2;
      if (c.this.j <= paramInt)
      {
        bool1 = bool2;
        if (paramInt < c.this.k) {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    String c(int paramInt)
    {
      int i = c.this.h.g();
      if (c.this.i + paramInt > i - 1) {}
      for (CharSequence localCharSequence = c.this.h.subSequence(c.this.i, i - c.this.i - 1);; localCharSequence = c.this.h.subSequence(c.this.i, paramInt)) {
        return localCharSequence.toString();
      }
    }
    
    public void c()
    {
      if (!c.this.d())
      {
        int i = c.this.i;
        int j = c.d(c.this);
        int k = j + 1;
        int m = c.this.e(i);
        int n = c.this.h.g(j);
        int i1 = c.this.h.g(k);
        c localc;
        if (m < i1)
        {
          localc = c.this;
          localc.i += n;
        }
        else
        {
          localc = c.this;
          localc.i += n - m + i1 - 1;
        }
        c.j(c.this);
        b(i, c.this.i);
        if (!c.b(c.this, c.this.i)) {
          c.a(c.this, j, k + 1);
        }
        c.k(c.this).a(k);
        f();
      }
    }
    
    public void c(boolean paramBoolean)
    {
      if (!(this.c ^ paramBoolean)) {
        return;
      }
      c localc;
      int i;
      if (paramBoolean)
      {
        c.this.j = c.this.i;
        localc = c.this;
        i = c.this.i;
      }
      for (;;)
      {
        localc.k = i;
        break;
        localc = c.this;
        i = -1;
        localc.j = -1;
        localc = c.this;
      }
      this.c = paramBoolean;
      this.d = paramBoolean;
      c.o(c.this).a(paramBoolean, c.this.getSelectionStart(), c.this.getSelectionEnd());
    }
    
    String d(int paramInt)
    {
      int i = c.this.i - paramInt;
      paramInt = i;
      if (i < 0) {
        paramInt = 0;
      }
      return c.this.h.subSequence(paramInt, c.this.i - paramInt).toString();
    }
    
    public void d()
    {
      if (!c.this.c())
      {
        int i = c.this.i;
        int j = c.d(c.this);
        int k = j - 1;
        int m = c.this.e(i);
        int n = c.this.h.g(k);
        c localc;
        if (m < n)
        {
          localc = c.this;
          localc.i -= n;
        }
        else
        {
          localc = c.this;
          localc.i -= m + 1;
        }
        c.l(c.this);
        b(i, c.this.i);
        if (!c.b(c.this, c.this.i)) {
          c.a(c.this, k, j + 1);
        }
        c.k(c.this).a(k);
        f();
      }
    }
    
    public void d(boolean paramBoolean)
    {
      if (this.c)
      {
        c localc;
        if ((paramBoolean) && (c.this.i != c.this.j)) {
          localc = c.this;
        }
        for (int i = c.this.j;; i = c.this.k)
        {
          localc.i = i;
          k();
          return;
          if ((paramBoolean) || (c.this.i == c.this.k)) {
            break;
          }
          localc = c.this;
        }
      }
    }
    
    void e()
    {
      int i = c.this.h.b(c.this.i);
      if (c.d(c.this) != i)
      {
        c.c(c.this, i);
        c.k(c.this).a(i);
      }
    }
    
    public void f()
    {
      ((InputMethodManager)c.this.getContext().getSystemService("input_method")).restartInput(c.this);
      if ((c.n(c.this) != null) && (c.n(c.this).b())) {
        c.n(c.this).a();
      }
    }
    
    public final boolean g()
    {
      return this.c;
    }
    
    public final boolean h()
    {
      return this.d;
    }
    
    public void i()
    {
      if (!this.c) {
        return;
      }
      int j = c.this.k - c.this.j;
      if (j > 0)
      {
        int k = c.this.h.b(c.this.j);
        int m = c.this.h.d(k);
        int i;
        if (c.this.h.b(c.this.k) == k) {
          i = 1;
        } else {
          i = 0;
        }
        c.i(c.this).a("", c.this.i, j);
        c.this.h.a(c.this.j, j, System.nanoTime());
        c.this.i = c.this.j;
        e();
        c.this.setEdited(true);
        a();
        c(false);
        f();
        if (!c.b(c.this, c.this.i))
        {
          j = k;
          if (c.this.h.j())
          {
            j = k;
            if (m != c.this.h.d(k)) {
              j = k - 1;
            }
          }
          if ((i != 0) && (!c.this.h.j()))
          {
            c.a(c.this, j, j + 1);
            return;
          }
          c.a(c.this, j);
        }
      }
      else
      {
        c(false);
        c.m(c.this);
      }
    }
  }
  
  private class b
    extends BaseInputConnection
  {
    private boolean b = false;
    private int c = 0;
    
    public b(c paramc)
    {
      super(true);
    }
    
    public void a()
    {
      this.c = 0;
      this.b = false;
      c.this.h.e();
    }
    
    public boolean b()
    {
      return this.b;
    }
    
    public boolean commitText(CharSequence paramCharSequence, int paramInt)
    {
      c.a(c.this).b(c.this.getCaretPosition() - this.c, this.c, paramCharSequence.toString());
      this.c = 0;
      c.this.h.e();
      if (paramInt > 1) {
        c.a(c.this).a(c.this.i + paramInt - 1);
      } else if (paramInt <= 0) {
        c.a(c.this).a(c.this.i - paramCharSequence.length() - paramInt);
      }
      this.b = false;
      return true;
    }
    
    public boolean deleteSurroundingText(int paramInt1, int paramInt2)
    {
      if (this.c != 0) {
        Log.i("lua", "Warning: Implmentation of InputConnection.deleteSurroundingText will not skip composing text");
      }
      c.a(c.this).a(paramInt1, paramInt2);
      return true;
    }
    
    public boolean finishComposingText()
    {
      a();
      return true;
    }
    
    public int getCursorCapsMode(int paramInt)
    {
      int k = 1;
      int i;
      if ((paramInt & 0x2000) == 8192)
      {
        i = c.this.i - 1;
        if ((i < 0) || (k.a().b(c.this.h.charAt(i))))
        {
          if ((paramInt & 0x4000) == 16384) {
            return 24576;
          }
          return 8192;
        }
      }
      else
      {
        h localh = k.a();
        paramInt = c.this.i - 1;
        i = 0;
        int j;
        for (;;)
        {
          j = k;
          if (paramInt < 0) {
            break;
          }
          char c1 = c.this.h.charAt(paramInt);
          if (c1 == '\n')
          {
            j = k;
            break;
          }
          if (!localh.b(c1))
          {
            if (i != 0)
            {
              j = k;
              if (localh.c(c1)) {
                break;
              }
            }
            j = 0;
            break;
          }
          i += 1;
          paramInt -= 1;
        }
        if (j != 0) {
          return 16384;
        }
      }
      return 0;
    }
    
    public CharSequence getTextAfterCursor(int paramInt1, int paramInt2)
    {
      return c.a(c.this).c(paramInt1);
    }
    
    public CharSequence getTextBeforeCursor(int paramInt1, int paramInt2)
    {
      return c.a(c.this).d(paramInt1);
    }
    
    public boolean performContextMenuAction(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        switch (paramInt)
        {
        }
        break;
      case 16908322: 
        c.this.paste();
        break;
      case 16908321: 
        c.this.copy();
        break;
      case 16908320: 
        c.this.cut();
        break;
      case 16908319: 
        c.this.selectAll();
      }
      return false;
    }
    
    public boolean reportFullscreenMode(boolean paramBoolean)
    {
      return false;
    }
    
    public boolean sendKeyEvent(KeyEvent paramKeyEvent)
    {
      int i = paramKeyEvent.getKeyCode();
      if (i != 59)
      {
        switch (i)
        {
        default: 
          switch (i)
          {
          default: 
            return super.sendKeyEvent(paramKeyEvent);
          case 123: 
            c.this.moveCaret(c.this.h.length());
            return true;
          }
          c.this.moveCaret(0);
          return true;
        case 22: 
          c.this.moveCaretRight();
          return true;
        case 21: 
          c.this.moveCaretLeft();
          return true;
        case 20: 
          c.this.moveCaretDown();
          return true;
        }
        c.this.moveCaretUp();
        return true;
      }
      if (c.this.isSelectText())
      {
        c.this.selectText(false);
        return true;
      }
      c.this.selectText(true);
      return true;
    }
    
    public boolean setComposingText(CharSequence paramCharSequence, int paramInt)
    {
      this.b = true;
      if (!c.this.h.c()) {
        c.this.h.d();
      }
      c.a(c.this).b(c.this.getCaretPosition() - this.c, this.c, paramCharSequence.toString());
      this.c = paramCharSequence.length();
      if (paramInt > 1)
      {
        c.a(c.this).a(c.this.i + paramInt - 1);
        return true;
      }
      if (paramInt <= 0) {
        c.a(c.this).a(c.this.i - paramCharSequence.length() - paramInt);
      }
      return true;
    }
    
    public boolean setSelection(int paramInt1, int paramInt2)
    {
      if (paramInt1 == paramInt2)
      {
        c.a(c.this).a(paramInt1);
        return true;
      }
      c.a(c.this).a(paramInt1, paramInt2 - paramInt1, false, true);
      return true;
    }
  }
  
  public static class c
    implements Parcelable
  {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator()
    {
      public c.c a(Parcel paramAnonymousParcel)
      {
        return new c.c(paramAnonymousParcel, null);
      }
      
      public c.c[] a(int paramAnonymousInt)
      {
        return new c.c[paramAnonymousInt];
      }
    };
    final int a;
    final int b;
    final int c;
    final boolean d;
    final int e;
    final int f;
    
    private c(Parcel paramParcel)
    {
      this.a = paramParcel.readInt();
      this.b = paramParcel.readInt();
      this.c = paramParcel.readInt();
      boolean bool;
      if (paramParcel.readInt() != 0) {
        bool = true;
      } else {
        bool = false;
      }
      this.d = bool;
      this.e = paramParcel.readInt();
      this.f = paramParcel.readInt();
    }
    
    public c(c paramc)
    {
      this.a = paramc.getCaretPosition();
      this.b = paramc.getScrollX();
      this.c = paramc.getScrollY();
      this.d = paramc.isSelectText();
      this.e = paramc.getSelectionStart();
      this.f = paramc.getSelectionEnd();
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */