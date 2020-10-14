package com.androlua;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ResolveInfo.DisplayNameComparator;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.hardware.display.VirtualDisplay.Callback;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import com.androlua.util.ClickRunnable;
import com.androlua.util.ClickRunnable.ClickCallback;
import com.androlua.util.GlobalActionAutomator;
import com.branch.www.screencapture.a;
import com.branch.www.screencapture.b;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import com.luajava.LuaTable;
import com.nirenr.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"NewApi"})
public class LuaAccessibilityService
  extends AccessibilityService
{
  private static AccessibilityServiceCallbacks a;
  private static LuaAccessibilityService d;
  public static LuaFunction onAccessibilityEvent;
  private LuaApplication b;
  private Map c;
  private HashMap<String, ComponentName> e = new HashMap();
  private boolean f;
  private Handler g;
  private GlobalActionAutomator h;
  private b i;
  private int j;
  private int k;
  private int l;
  
  private AccessibilityNodeInfo a(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return null;
    }
    if (isListView2(paramAccessibilityNodeInfo)) {
      return paramAccessibilityNodeInfo;
    }
    int n = paramAccessibilityNodeInfo.getChildCount();
    int m = 0;
    while (m < n)
    {
      AccessibilityNodeInfo localAccessibilityNodeInfo = a(paramAccessibilityNodeInfo.getChild(m));
      if (localAccessibilityNodeInfo != null) {
        return localAccessibilityNodeInfo;
      }
      m += 1;
    }
    return null;
  }
  
  private void a()
  {
    WindowManager localWindowManager = (WindowManager)getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    if (localWindowManager == null) {
      return;
    }
    localWindowManager.getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    this.j = localDisplayMetrics.densityDpi;
    this.k = localDisplayMetrics.widthPixels;
    this.l = localDisplayMetrics.heightPixels;
  }
  
  private void a(AccessibilityNodeInfo paramAccessibilityNodeInfo, ArrayList<String> paramArrayList)
  {
    if (paramAccessibilityNodeInfo == null) {
      return;
    }
    getNodeInfoText(paramAccessibilityNodeInfo);
    int n = paramAccessibilityNodeInfo.getChildCount();
    if (n > 0)
    {
      int m = 0;
      while (m < n)
      {
        a(paramAccessibilityNodeInfo.getChild(m), paramArrayList);
        m += 1;
      }
    }
  }
  
  private void a(AccessibilityNodeInfo paramAccessibilityNodeInfo1, ArrayList<String> paramArrayList, AccessibilityNodeInfo paramAccessibilityNodeInfo2)
  {
    if (paramAccessibilityNodeInfo1 == null) {
      return;
    }
    if (!this.f) {
      this.f = paramAccessibilityNodeInfo1.equals(paramAccessibilityNodeInfo2);
    }
    Object localObject = getNodeInfoText(paramAccessibilityNodeInfo1);
    if ((this.f) && (localObject != null)) {
      paramArrayList.add(((CharSequence)localObject).toString());
    }
    int n = paramAccessibilityNodeInfo1.getChildCount();
    if (n > 0)
    {
      int m = 0;
      while (m < n)
      {
        localObject = paramAccessibilityNodeInfo1.getChild(m);
        if (localObject != null)
        {
          if (!this.f) {
            this.f = ((AccessibilityNodeInfo)localObject).equals(paramAccessibilityNodeInfo2);
          }
          a((AccessibilityNodeInfo)localObject, paramArrayList, paramAccessibilityNodeInfo2);
        }
        m += 1;
      }
    }
  }
  
  private void a(String paramString, LuaException paramLuaException) {}
  
  private void a(List<AccessibilityNodeInfo> paramList, AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    if (paramAccessibilityNodeInfo == null) {
      return;
    }
    CharSequence[] arrayOfCharSequence = new CharSequence[2];
    String[] arrayOfString = paramString.split("\\|");
    Object localObject1 = paramAccessibilityNodeInfo.getContentDescription();
    int i1 = 0;
    arrayOfCharSequence[0] = localObject1;
    arrayOfCharSequence[1] = paramAccessibilityNodeInfo.getText();
    int i2 = arrayOfString.length;
    int m = 0;
    while (m < i2)
    {
      Object localObject2 = arrayOfString[m];
      boolean bool1 = ((String)localObject2).startsWith("*") ^ true;
      boolean bool2 = ((String)localObject2).endsWith("*") ^ true;
      localObject1 = localObject2;
      if (!bool1) {
        localObject1 = ((String)localObject2).substring(1);
      }
      localObject2 = localObject1;
      if (!bool2) {
        localObject2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
      int i3 = arrayOfCharSequence.length;
      n = 0;
      while (n < i3)
      {
        localObject1 = arrayOfCharSequence[n];
        if (localObject1 != null)
        {
          localObject1 = ((CharSequence)localObject1).toString().trim();
          if ((bool1) && (bool2))
          {
            if (!((String)localObject2).equals(localObject1)) {}
          }
          else {
            while (bool1 ? ((String)localObject1).startsWith((String)localObject2) : bool2 ? ((String)localObject1).endsWith((String)localObject2) : ((String)localObject1).contains((CharSequence)localObject2))
            {
              paramList.add(paramAccessibilityNodeInfo);
              break;
            }
          }
        }
        n += 1;
      }
      m += 1;
    }
    int n = paramAccessibilityNodeInfo.getChildCount();
    m = i1;
    while (m < n)
    {
      a(paramList, paramAccessibilityNodeInfo.getChild(m), paramString);
      m += 1;
    }
  }
  
  private AccessibilityNodeInfo b(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = paramAccessibilityNodeInfo;
    for (;;)
    {
      if (localAccessibilityNodeInfo != null) {
        try
        {
          if (isClickable(localAccessibilityNodeInfo)) {
            return localAccessibilityNodeInfo;
          }
          localAccessibilityNodeInfo = localAccessibilityNodeInfo.getParent();
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    return paramAccessibilityNodeInfo;
  }
  
  private void b()
  {
    new AsyncTask()
    {
      protected HashMap<String, ComponentName> a(String... paramAnonymousVarArgs)
      {
        paramAnonymousVarArgs = new HashMap();
        PackageManager localPackageManager = LuaAccessibilityService.this.getPackageManager();
        Object localObject1 = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject1).addCategory("android.intent.category.DEFAULT");
        int j = 0;
        localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
        Collections.sort((List)localObject1, new ResolveInfo.DisplayNameComparator(localPackageManager));
        int k = ((List)localObject1).size();
        int i = 0;
        Object localObject2;
        CharSequence localCharSequence;
        while (i < k)
        {
          localObject2 = (ResolveInfo)((List)localObject1).get(i);
          localCharSequence = ((ResolveInfo)localObject2).loadLabel(localPackageManager);
          localObject2 = new ComponentName(((ResolveInfo)localObject2).activityInfo.applicationInfo.packageName, ((ResolveInfo)localObject2).activityInfo.name);
          paramAnonymousVarArgs.put(localCharSequence.toString().toLowerCase(), localObject2);
          i += 1;
        }
        localObject1 = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
        localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
        Collections.sort((List)localObject1, new ResolveInfo.DisplayNameComparator(localPackageManager));
        k = ((List)localObject1).size();
        i = j;
        while (i < k)
        {
          localObject2 = (ResolveInfo)((List)localObject1).get(i);
          localCharSequence = ((ResolveInfo)localObject2).loadLabel(localPackageManager);
          localObject2 = new ComponentName(((ResolveInfo)localObject2).activityInfo.applicationInfo.packageName, ((ResolveInfo)localObject2).activityInfo.name);
          paramAnonymousVarArgs.put(localCharSequence.toString().toLowerCase(), localObject2);
          i += 1;
        }
        return paramAnonymousVarArgs;
      }
      
      protected void a(HashMap<String, ComponentName> paramAnonymousHashMap)
      {
        super.onPostExecute(paramAnonymousHashMap);
        if ((paramAnonymousHashMap != null) && (!paramAnonymousHashMap.isEmpty())) {
          LuaAccessibilityService.a(LuaAccessibilityService.this, paramAnonymousHashMap);
        }
      }
    }.execute(new String[] { "" });
  }
  
  private void c()
  {
    PackageManager localPackageManager = getPackageManager();
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    int m = 0;
    localObject1 = localPackageManager.queryIntentActivities((Intent)localObject1, 0);
    Collections.sort((List)localObject1, new ResolveInfo.DisplayNameComparator(localPackageManager));
    if (localObject1 != null)
    {
      int n = ((List)localObject1).size();
      while (m < n)
      {
        Object localObject2 = (ResolveInfo)((List)localObject1).get(m);
        CharSequence localCharSequence = ((ResolveInfo)localObject2).loadLabel(localPackageManager);
        localObject2 = new ComponentName(((ResolveInfo)localObject2).activityInfo.applicationInfo.packageName, ((ResolveInfo)localObject2).activityInfo.name);
        this.e.put(localCharSequence.toString().toLowerCase(), localObject2);
        m += 1;
      }
    }
  }
  
  public static LuaAccessibilityService getInstance()
  {
    return d;
  }
  
  public static void setCallback(AccessibilityServiceCallbacks paramAccessibilityServiceCallbacks)
  {
    a = paramAccessibilityServiceCallbacks;
  }
  
  public boolean appendCopy()
  {
    return appendCopy(getText(getFocusView()));
  }
  
  public boolean appendCopy(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return false;
    }
    ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
    Object localObject2 = localClipboardManager.getText();
    Object localObject1 = "";
    if (localObject2 != null) {
      localObject1 = ((CharSequence)localObject2).toString();
    }
    localObject2 = localObject1;
    if (((String)localObject1).length() > 1)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("\n");
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append((String)localObject2);
    ((StringBuilder)localObject1).append(paramCharSequence);
    localClipboardManager.setPrimaryClip(ClipData.newPlainText("label", ((StringBuilder)localObject1).toString()));
    return true;
  }
  
  public boolean click(int paramInt1, int paramInt2)
  {
    if (this.h != null) {
      return this.h.click(paramInt1, paramInt2);
    }
    return false;
  }
  
  public boolean click(LuaTable paramLuaTable)
  {
    return new ClickRunnable(this, paramLuaTable).canClick();
  }
  
  public boolean click(LuaTable paramLuaTable, final LuaFunction paramLuaFunction)
  {
    new ClickRunnable(this, paramLuaTable).canClick(new ClickRunnable.ClickCallback()
    {
      public void onDone(boolean paramAnonymousBoolean, LuaTable paramAnonymousLuaTable, String paramAnonymousString, int paramAnonymousInt)
      {
        try
        {
          paramLuaFunction.call(new Object[] { Boolean.valueOf(paramAnonymousBoolean), paramAnonymousLuaTable, paramAnonymousString, Integer.valueOf(paramAnonymousInt) });
          return;
        }
        catch (LuaException paramAnonymousLuaTable)
        {
          paramAnonymousLuaTable.printStackTrace();
          LuaAccessibilityService.a(LuaAccessibilityService.this, "click", paramAnonymousLuaTable);
        }
      }
    });
  }
  
  public boolean click(Point paramPoint)
  {
    return click(paramPoint.x, paramPoint.y);
  }
  
  public boolean click(String paramString1, String paramString2, int[] paramArrayOfInt)
  {
    if (paramString1 != null)
    {
      if (paramString2 == null) {
        return false;
      }
      if (!paramString1.equals(getAppName(getFocusView()))) {
        return false;
      }
      paramString1 = getRootInActiveWindow();
      if (paramString1 == null) {
        return false;
      }
      if (paramString1.findAccessibilityNodeInfosByText(paramString2).isEmpty()) {
        return false;
      }
      int n = paramArrayOfInt.length;
      int m = 0;
      while (m < n)
      {
        int i1 = paramArrayOfInt[m];
        if (paramString1.getChildCount() <= i1) {
          return false;
        }
        paramString1 = paramString1.getChild(i1);
        if (paramString1 == null) {
          return false;
        }
        m += 1;
      }
      return toClick(paramString1);
    }
    return false;
  }
  
  public boolean copy()
  {
    return copy(getText(getFocusView()));
  }
  
  public boolean copy(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return false;
    }
    paramCharSequence = paramCharSequence.toString();
    ((ClipboardManager)getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", paramCharSequence));
    return true;
  }
  
  public boolean deleteApp(String paramString)
  {
    this.e.clear();
    c();
    paramString = paramString.toLowerCase();
    paramString = (ComponentName)this.e.get(paramString);
    if (paramString == null) {
      return false;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString.getPackageName());
    paramString = new Intent("android.intent.action.DELETE", Uri.parse(localStringBuilder.toString()));
    paramString.setFlags(270532608);
    startActivity(paramString);
    return true;
  }
  
  public boolean execute(String paramString, AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    switch (paramString.hashCode())
    {
    default: 
      break;
    case 1119180876: 
      if (paramString.equals("追加复制")) {
        m = 8;
      }
      break;
    case 821558167: 
      if (paramString.equals("最近任务")) {
        m = 5;
      }
      break;
    case 697331565: 
      if (paramString.equals("增加进度")) {
        m = 3;
      }
      break;
    case 661386612: 
      if (paramString.equals("向下翻页")) {
        m = 1;
      }
      break;
    case 661385651: 
      if (paramString.equals("向上翻页")) {
        m = 0;
      }
      break;
    case 647728589: 
      if (paramString.equals("减少进度")) {
        m = 2;
      }
      break;
    case 36429412: 
      if (paramString.equals("通知栏")) {
        m = 13;
      }
      break;
    case 20002657: 
      if (paramString.equals("主屏幕")) {
        m = 9;
      }
      break;
    case 1211754: 
      if (paramString.equals("长按")) {
        m = 12;
      }
      break;
    case 1163658: 
      if (paramString.equals("返回")) {
        m = 10;
      }
      break;
    case 1024924: 
      if (paramString.equals("粘贴")) {
        m = 4;
      }
      break;
    case 915554: 
      if (paramString.equals("点击")) {
        m = 11;
      }
      break;
    case 904469: 
      if (paramString.equals("清空")) {
        m = 6;
      }
      break;
    case 727753: 
      if (paramString.equals("复制")) {
        m = 7;
      }
      break;
    case -1762910153: 
      if (paramString.equals("打开通知栏")) {
        m = 14;
      }
      break;
    }
    int m = -1;
    switch (m)
    {
    default: 
      return false;
    case 13: 
    case 14: 
      toNotifications();
      return true;
    case 12: 
      toLongClick(paramAccessibilityNodeInfo);
      return true;
    case 11: 
      toClick(paramAccessibilityNodeInfo);
      return true;
    case 10: 
      toBack();
      return true;
    case 9: 
      toHome();
      return true;
    case 8: 
      appendCopy(getText(paramAccessibilityNodeInfo));
      return true;
    case 7: 
      copy(getText(paramAccessibilityNodeInfo));
      return true;
    case 6: 
      if (Build.VERSION.SDK_INT >= 21) {
        return paramAccessibilityNodeInfo.performAction(2097152);
      }
      return false;
    case 5: 
      toRecents();
      return true;
    case 4: 
      paste(paramAccessibilityNodeInfo);
      return true;
    case 3: 
      return scrollForward(paramAccessibilityNodeInfo);
    case 2: 
      return scrollBackward(paramAccessibilityNodeInfo);
    case 1: 
      paramString = a(getRootInActiveWindow());
      if (paramString == null) {
        return false;
      }
      return scrollForward(paramString);
    }
    paramString = a(getRootInActiveWindow());
    if (paramString == null) {
      return false;
    }
    return scrollBackward(paramString);
  }
  
  public AccessibilityNodeInfo findAccessibilityNodeInfo(String paramString)
  {
    int m = paramString.lastIndexOf("@");
    String str = paramString;
    if (m > 0)
    {
      if (!paramString.substring(m + 1).equals(getAppName(getFocusView()))) {
        return null;
      }
      str = paramString.substring(0, m);
    }
    int i1 = str.lastIndexOf("#");
    m = -1;
    int n = m;
    paramString = str;
    if (i1 > 0) {}
    try
    {
      n = Integer.valueOf(str.substring(i1 + 1)).intValue();
      m = n;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    paramString = str.substring(0, i1);
    n = m;
    m = paramString.charAt(0);
    if (m != 62)
    {
      if (m != 64)
      {
        switch (m)
        {
        default: 
          return findAccessibilityNodeInfoByText(paramString, n);
        case 37: 
          if (execute(paramString.substring(1), getFocusView())) {
            return AccessibilityNodeInfo.obtain();
          }
          return null;
        }
        return findAccessibilityNodeInfoByIndex(paramString.substring(1));
      }
      return findAccessibilityNodeInfoById(paramString.substring(1), n);
    }
    if (startApp(paramString.substring(1))) {
      return AccessibilityNodeInfo.obtain();
    }
    return null;
  }
  
  public AccessibilityNodeInfo findAccessibilityNodeInfoById(String paramString, int paramInt)
  {
    paramString = findAccessibilityNodeInfoById(paramString);
    if (paramString.isEmpty()) {
      return null;
    }
    int m = paramString.size();
    if (paramInt + 1 <= m)
    {
      if (0 - paramInt > m) {
        return null;
      }
      if (paramInt < 0) {}
      for (paramString = paramString.get(paramString.size() + paramInt);; paramString = paramString.get(paramInt)) {
        return (AccessibilityNodeInfo)paramString;
      }
    }
    return null;
  }
  
  public List<AccessibilityNodeInfo> findAccessibilityNodeInfoById(String paramString)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = getRootInActiveWindow();
    if (localAccessibilityNodeInfo == null) {
      return new ArrayList();
    }
    return localAccessibilityNodeInfo.findAccessibilityNodeInfosByText(paramString);
  }
  
  public AccessibilityNodeInfo findAccessibilityNodeInfoByIndex(String paramString)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = getRootInActiveWindow();
    if (localAccessibilityNodeInfo == null) {
      return null;
    }
    String[] arrayOfString = paramString.split("-");
    int n = arrayOfString.length;
    int m = 0;
    paramString = localAccessibilityNodeInfo;
    while (m < n)
    {
      localAccessibilityNodeInfo = arrayOfString[m];
      try
      {
        int i1 = Integer.valueOf(localAccessibilityNodeInfo).intValue();
        if (paramString.getChildCount() <= i1) {
          return null;
        }
        paramString = paramString.getChild(i1);
        if (paramString == null) {
          return null;
        }
        m += 1;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
    }
    return paramString;
  }
  
  public AccessibilityNodeInfo findAccessibilityNodeInfoByText(String paramString, int paramInt)
  {
    paramString = findAccessibilityNodeInfoByText(paramString);
    if (paramString.isEmpty()) {
      return null;
    }
    int m = paramString.size();
    if (paramInt + 1 <= m)
    {
      if (0 - paramInt > m) {
        return null;
      }
      if (paramInt < 0) {}
      for (paramString = paramString.get(paramString.size() + paramInt);; paramString = paramString.get(paramInt)) {
        return (AccessibilityNodeInfo)paramString;
      }
    }
    return null;
  }
  
  public List<AccessibilityNodeInfo> findAccessibilityNodeInfoByText(String paramString)
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo1 = getRootInActiveWindow();
    ArrayList localArrayList = new ArrayList();
    if (localAccessibilityNodeInfo1 == null) {
      return localArrayList;
    }
    String[] arrayOfString = paramString.split("\\|");
    int n = arrayOfString.length;
    int m = 0;
    while (m < n)
    {
      Object localObject2 = arrayOfString[m];
      if (!((String)localObject2).isEmpty())
      {
        if (((String)localObject2).charAt(0) == '%') {
          break label421;
        }
        int i1 = ((String)localObject2).lastIndexOf('&');
        Object localObject1 = localObject2;
        if (i1 > 0)
        {
          if (findAccessibilityNodeInfo(((String)localObject2).substring(i1 + 1)) != null) {
            localObject1 = ((String)localObject2).substring(0, i1);
          }
        }
        else
        {
          boolean bool1 = ((String)localObject1).startsWith("*") ^ true;
          boolean bool2 = ((String)localObject1).endsWith("*") ^ true;
          localObject2 = localObject1;
          if (!bool1) {
            localObject2 = ((String)localObject1).substring(1);
          }
          localObject1 = localObject2;
          if (!bool2) {
            localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
          }
          localObject2 = localAccessibilityNodeInfo1.findAccessibilityNodeInfosByText((String)localObject1).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            AccessibilityNodeInfo localAccessibilityNodeInfo2 = (AccessibilityNodeInfo)((Iterator)localObject2).next();
            Object localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(localAccessibilityNodeInfo2.getText());
            ((StringBuilder)localObject3).append("");
            localObject3 = ((StringBuilder)localObject3).toString().trim();
            Object localObject4 = new StringBuilder();
            ((StringBuilder)localObject4).append(localAccessibilityNodeInfo2.getContentDescription());
            ((StringBuilder)localObject4).append("");
            localObject4 = ((StringBuilder)localObject4).toString().trim();
            if ((bool1) && (bool2)) {
              if ((!((String)localObject1).equals(localObject3)) && (!((String)localObject1).equals(localObject4))) {
                break;
              }
            } else {
              for (;;)
              {
                localArrayList.add(localAccessibilityNodeInfo2);
                break;
                if (bool1)
                {
                  if (((String)localObject3).startsWith((String)localObject1)) {
                    continue;
                  }
                  if (!((String)localObject4).startsWith((String)localObject1)) {
                    break;
                  }
                  continue;
                }
                if (bool2)
                {
                  if (((String)localObject3).endsWith((String)localObject1)) {
                    continue;
                  }
                  if (!((String)localObject4).endsWith((String)localObject1)) {
                    break;
                  }
                  continue;
                }
                if (!((String)localObject3).contains((CharSequence)localObject1)) {
                  if (!((String)localObject4).contains((CharSequence)localObject1)) {
                    break;
                  }
                }
              }
            }
          }
        }
      }
      m += 1;
      continue;
      label421:
      execute(((String)localObject2).substring(1), getFocusView());
      return localArrayList;
    }
    if (localArrayList.isEmpty()) {
      a(localArrayList, localAccessibilityNodeInfo1, paramString);
    }
    return localArrayList;
  }
  
  public boolean findClick(String[] paramArrayOfString)
  {
    int n = paramArrayOfString.length;
    int m = 0;
    while (m < n)
    {
      AccessibilityNodeInfo localAccessibilityNodeInfo = findAccessibilityNodeInfoByText(paramArrayOfString[m], 0);
      if ((localAccessibilityNodeInfo != null) && (b(localAccessibilityNodeInfo).performAction(16))) {
        return true;
      }
      m += 1;
    }
    return false;
  }
  
  public ArrayList<AccessibilityNodeInfo> getAllEditTextList()
  {
    ArrayList localArrayList = new ArrayList();
    getEditText(getRootInActiveWindow(), localArrayList);
    return localArrayList;
  }
  
  public String getAllText(int paramInt)
  {
    Object localObject = getAllTextList();
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (str.length() > paramInt)
      {
        localStringBuilder.append(str);
        localStringBuilder.append("\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  public ArrayList<String> getAllTextList()
  {
    ArrayList localArrayList = new ArrayList();
    a(getRootInActiveWindow(), localArrayList);
    return localArrayList;
  }
  
  public ArrayList<String> getAllTextList(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    ArrayList localArrayList = new ArrayList();
    AccessibilityNodeInfo localAccessibilityNodeInfo = getRootInActiveWindow();
    this.f = (paramAccessibilityNodeInfo.isVisibleToUser() ^ true);
    a(localAccessibilityNodeInfo, localArrayList, paramAccessibilityNodeInfo);
    return localArrayList;
  }
  
  public String getAppName(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return "";
    }
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.getPackageName();
    if (paramAccessibilityNodeInfo == null) {
      return "";
    }
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.toString();
    PackageManager localPackageManager = getPackageManager();
    try
    {
      paramAccessibilityNodeInfo = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(paramAccessibilityNodeInfo, 0)).toString();
      return paramAccessibilityNodeInfo;
    }
    catch (PackageManager.NameNotFoundException paramAccessibilityNodeInfo)
    {
      paramAccessibilityNodeInfo.printStackTrace();
    }
    return "";
  }
  
  public int getDensity()
  {
    if (this.j == 0) {
      a();
    }
    return this.j;
  }
  
  public AccessibilityNodeInfo getEditText()
  {
    Object localObject = getAllEditTextList();
    if (((ArrayList)localObject).isEmpty()) {
      return null;
    }
    localObject = (AccessibilityNodeInfo)((ArrayList)localObject).get(0);
    if (localObject != null) {
      ((AccessibilityNodeInfo)localObject).performAction(64);
    }
    return (AccessibilityNodeInfo)localObject;
  }
  
  public void getEditText(AccessibilityNodeInfo paramAccessibilityNodeInfo, ArrayList<AccessibilityNodeInfo> paramArrayList)
  {
    if (paramAccessibilityNodeInfo == null) {
      return;
    }
    if (paramAccessibilityNodeInfo.isEditable()) {
      paramArrayList.add(paramAccessibilityNodeInfo);
    }
    int n = paramAccessibilityNodeInfo.getChildCount();
    if (n > 0)
    {
      int m = 0;
      while (m < n)
      {
        getEditText(paramAccessibilityNodeInfo.getChild(m), paramArrayList);
        m += 1;
      }
    }
  }
  
  public AccessibilityNodeInfo getFocusView()
  {
    return getRootInActiveWindow();
  }
  
  public Handler getHandler()
  {
    return this.g;
  }
  
  public int getHeight()
  {
    if (this.l == 0) {
      a();
    }
    return this.l;
  }
  
  public String getNodeInfoText(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return null;
    }
    Object localObject = paramAccessibilityNodeInfo.getContentDescription();
    CharSequence localCharSequence = paramAccessibilityNodeInfo.getText();
    if (localObject != null) {
      localObject = ((CharSequence)localObject).toString();
    } else {
      localObject = null;
    }
    if ((localObject != null) && (((String)localObject).trim().length() > 0) && ((!paramAccessibilityNodeInfo.isEditable()) || (localCharSequence == null))) {
      return (String)localObject;
    }
    if ((localCharSequence != null) && (localCharSequence.length() > 0)) {
      return localCharSequence.toString();
    }
    return null;
  }
  
  public Bitmap getScreenshot()
  {
    if (this.i != null) {
      return this.i.a();
    }
    return null;
  }
  
  public void getScreenshot(final LuaFunction paramLuaFunction)
  {
    b.a(this, new a()
    {
      public void onScreenCaptureDone(Bitmap paramAnonymousBitmap)
      {
        try
        {
          paramLuaFunction.call(new Object[] { paramAnonymousBitmap });
          return;
        }
        catch (LuaException paramAnonymousBitmap)
        {
          paramAnonymousBitmap.printStackTrace();
        }
      }
      
      public void onScreenCaptureError(String paramAnonymousString)
      {
        try
        {
          paramLuaFunction.call(new Object[] { null, paramAnonymousString });
          return;
        }
        catch (LuaException paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
      }
    });
  }
  
  public String getText(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    return getNodeInfoText(paramAccessibilityNodeInfo);
  }
  
  public int getWidth()
  {
    if (this.k == 0) {
      a();
    }
    return this.k;
  }
  
  public boolean insert(AccessibilityNodeInfo paramAccessibilityNodeInfo, CharSequence paramCharSequence)
  {
    if (paramAccessibilityNodeInfo == null) {
      return false;
    }
    if (paramCharSequence == null) {
      return false;
    }
    if (paramAccessibilityNodeInfo.isEditable())
    {
      if (!paramAccessibilityNodeInfo.isFocused()) {
        paramAccessibilityNodeInfo.performAction(1);
      }
      ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
      localClipboardManager.setPrimaryClip(ClipData.newPlainText("label", paramCharSequence));
      localClipboardManager.setText(paramCharSequence);
      if (paramAccessibilityNodeInfo.performAction(32768)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean installApp(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("market://search?q=");
    localStringBuilder.append(paramString);
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
    paramString.setFlags(270532608);
    try
    {
      startActivity(paramString);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public boolean isClickable(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return false;
    }
    if (paramAccessibilityNodeInfo.isClickable()) {
      return true;
    }
    if (paramAccessibilityNodeInfo.isCheckable()) {
      return true;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      if (paramAccessibilityNodeInfo.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK)) {
        return true;
      }
    }
    else if ((paramAccessibilityNodeInfo.getActions() & 0x10) != 0) {
      return true;
    }
    return false;
  }
  
  public boolean isListView2(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return false;
    }
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.getClassName();
    if (paramAccessibilityNodeInfo != null)
    {
      paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.toString();
      int m = -1;
      switch (paramAccessibilityNodeInfo.hashCode())
      {
      default: 
        break;
      case 1977625610: 
        if (paramAccessibilityNodeInfo.equals("android.widget.AbsListView")) {
          m = 3;
        }
        break;
      case 1928354017: 
        if (paramAccessibilityNodeInfo.equals("android.widget.HorizontalScrollView")) {
          m = 8;
        }
        break;
      case 1799238850: 
        if (paramAccessibilityNodeInfo.equals("android.widget.ExpandableListView")) {
          m = 4;
        }
        break;
      case 841510749: 
        if (paramAccessibilityNodeInfo.equals("android.widget.ScrollView")) {
          m = 7;
        }
        break;
      case -405438610: 
        if (paramAccessibilityNodeInfo.equals("android.widget.ListView")) {
          m = 1;
        }
        break;
      case -438596061: 
        if (paramAccessibilityNodeInfo.equals("flyme.support.v7.widget.RecyclerView")) {
          m = 6;
        }
        break;
      case -703660929: 
        if (paramAccessibilityNodeInfo.equals("android.support.v7.widget.RecyclerView")) {
          m = 5;
        }
        break;
      case -1102376577: 
        if (paramAccessibilityNodeInfo.equals("com.tencent.widget.GridView")) {
          m = 9;
        }
        break;
      case -1154346071: 
        if (paramAccessibilityNodeInfo.equals("android.widget.AdapterView")) {
          m = 0;
        }
        break;
      case -1433025002: 
        if (paramAccessibilityNodeInfo.equals("android.widget.GridView")) {
          m = 2;
        }
        break;
      }
      switch (m)
      {
      default: 
        if (!paramAccessibilityNodeInfo.endsWith("ScrollView")) {
          break;
        }
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
      case 9: 
        return true;
      }
      if (paramAccessibilityNodeInfo.endsWith("GridView")) {
        return true;
      }
      if (paramAccessibilityNodeInfo.endsWith("RecyclerView")) {
        return true;
      }
      if (paramAccessibilityNodeInfo.endsWith("ListView")) {
        return true;
      }
    }
    return false;
  }
  
  public boolean longClick(int paramInt1, int paramInt2)
  {
    if (this.h != null) {
      return this.h.longClick(paramInt1, paramInt2);
    }
    return false;
  }
  
  public boolean longClick(Point paramPoint)
  {
    return longClick(paramPoint.x, paramPoint.y);
  }
  
  public ClickRunnable loopClick(final LuaTable paramLuaTable)
  {
    ClickRunnable localClickRunnable = new ClickRunnable(this, paramLuaTable);
    localClickRunnable.canClick(new ClickRunnable.ClickCallback()
    {
      public void onDone(boolean paramAnonymousBoolean, LuaTable paramAnonymousLuaTable, String paramAnonymousString, int paramAnonymousInt)
      {
        LuaAccessibilityService.this.loopClick(paramLuaTable);
      }
    });
    return localClickRunnable;
  }
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (a != null) {
      a.onAccessibilityEvent(this, paramAccessibilityEvent);
    }
    if (onAccessibilityEvent != null) {
      try
      {
        onAccessibilityEvent.call(new Object[] { paramAccessibilityEvent });
        return;
      }
      catch (LuaException paramAccessibilityEvent)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("onAccessibilityEvent: ");
        ((StringBuilder)localObject).append(paramAccessibilityEvent.toString());
        Log.i("lua", ((StringBuilder)localObject).toString());
        return;
      }
    }
    if (!this.c.containsKey("LuaAccessibilityService")) {
      return;
    }
    Object localObject = (LuaTable)this.c.get("LuaAccessibilityService");
    if (localObject == null) {
      return;
    }
    try
    {
      ((LuaFunction)((LuaTable)localObject).get("onAccessibilityEvent")).call(new Object[] { paramAccessibilityEvent });
      return;
    }
    catch (LuaException paramAccessibilityEvent)
    {
      localObject = (LuaFunction)((LuaTable)localObject).get("onError");
      if (localObject == null)
      {
        Log.i("onAccessibilityEvent", paramAccessibilityEvent.getMessage());
        return;
      }
    }
    try
    {
      ((LuaFunction)localObject).call(new Object[] { paramAccessibilityEvent });
      return;
    }
    catch (LuaException localLuaException)
    {
      for (;;) {}
    }
    Log.i("onAccessibilityEvent", paramAccessibilityEvent.getMessage());
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (a != null) {
      a.onConfigurationChanged(this, paramConfiguration);
    }
    if (this.i != null) {
      this.i.b();
    }
  }
  
  public void onCreate()
  {
    Log.i("lua", "onCreate");
    super.onCreate();
    this.g = new Handler();
    d = this;
    if (Build.VERSION.SDK_INT >= 24)
    {
      this.h = new GlobalActionAutomator(this, new Handler());
      this.h.setService(this);
    }
    if (a != null) {
      a.onCreate(this);
    }
    b();
    this.b = ((LuaApplication)getApplication());
    this.c = this.b.getGlobalData();
    if (!this.c.containsKey("LuaAccessibilityService")) {
      return;
    }
    Object localObject = (LuaTable)this.c.get("LuaAccessibilityService");
    if (localObject == null) {
      return;
    }
    try
    {
      ((LuaFunction)((LuaTable)localObject).get("onCreate")).call(new Object[] { this });
      return;
    }
    catch (LuaException localLuaException1)
    {
      localObject = (LuaFunction)((LuaTable)localObject).get("onError");
      if (localObject == null)
      {
        Log.i("onCreate", localLuaException1.getMessage());
        return;
      }
    }
    try
    {
      ((LuaFunction)localObject).call(new Object[] { localLuaException1 });
      return;
    }
    catch (LuaException localLuaException2)
    {
      for (;;) {}
    }
    Log.i("onCreate", localLuaException1.getMessage());
  }
  
  public void onDestroy()
  {
    if (a != null) {
      a.onDestroy(this);
    }
    stopScreenshot();
    super.onDestroy();
  }
  
  public void onInterrupt()
  {
    if (a != null) {
      a.onInterrupt(this);
    }
  }
  
  protected boolean onKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((a != null) && (a.onKeyEvent(this, paramKeyEvent))) {
      return true;
    }
    return super.onKeyEvent(paramKeyEvent);
  }
  
  protected void onServiceConnected()
  {
    Log.i("lua", "onServiceConnected");
    super.onServiceConnected();
    if (a != null) {
      a.onServiceConnected(this);
    }
    if (!this.c.containsKey("LuaAccessibilityService")) {
      return;
    }
    Object localObject = (LuaTable)this.c.get("LuaAccessibilityService");
    if (localObject == null) {
      return;
    }
    try
    {
      ((LuaFunction)((LuaTable)localObject).get("onServiceConnected")).call(new Object[] { this });
      return;
    }
    catch (LuaException localLuaException1)
    {
      localObject = (LuaFunction)((LuaTable)localObject).get("onError");
      if (localObject == null)
      {
        Log.i("onServiceConnected", localLuaException1.getMessage());
        return;
      }
    }
    try
    {
      ((LuaFunction)localObject).call(new Object[] { localLuaException1 });
      return;
    }
    catch (LuaException localLuaException2)
    {
      for (;;) {}
    }
    Log.i("onServiceConnected", localLuaException1.getMessage());
  }
  
  public boolean paste()
  {
    ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
    return paste(getFocusView(), localClipboardManager.getText());
  }
  
  public boolean paste(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    return paste(paramAccessibilityNodeInfo, ((ClipboardManager)getSystemService("clipboard")).getText());
  }
  
  public boolean paste(AccessibilityNodeInfo paramAccessibilityNodeInfo, CharSequence paramCharSequence)
  {
    if (paramAccessibilityNodeInfo == null) {
      return false;
    }
    if (paramCharSequence == null) {
      return false;
    }
    if (paramAccessibilityNodeInfo.isEditable())
    {
      if (!paramAccessibilityNodeInfo.isFocused()) {
        paramAccessibilityNodeInfo.performAction(1);
      }
      ClipboardManager localClipboardManager = (ClipboardManager)getSystemService("clipboard");
      localClipboardManager.setPrimaryClip(ClipData.newPlainText("label", paramCharSequence));
      localClipboardManager.setText(paramCharSequence);
      if (paramAccessibilityNodeInfo.performAction(32768)) {
        return true;
      }
    }
    return paste(paramCharSequence);
  }
  
  public boolean paste(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      return false;
    }
    AccessibilityNodeInfo localAccessibilityNodeInfo = getEditText();
    if (localAccessibilityNodeInfo == null) {
      return false;
    }
    Object localObject = paramCharSequence;
    if (getFocusView().isEditable())
    {
      localObject = paramCharSequence;
      if (getFocusView().getText() != null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(getFocusView().getText().toString());
        ((StringBuilder)localObject).append(paramCharSequence);
        localObject = ((StringBuilder)localObject).toString();
      }
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramCharSequence = new Bundle();
      paramCharSequence.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", (CharSequence)localObject);
      return localAccessibilityNodeInfo.performAction(2097152, paramCharSequence);
    }
    return false;
  }
  
  public void postClick(long paramLong, final LuaTable paramLuaTable)
  {
    this.g.postDelayed(new Runnable()
    {
      public void run()
      {
        LuaAccessibilityService.this.click(paramLuaTable);
      }
    }, paramLong);
  }
  
  public void postClick(long paramLong, final LuaTable paramLuaTable, final LuaFunction paramLuaFunction)
  {
    this.g.postDelayed(new Runnable()
    {
      public void run()
      {
        LuaAccessibilityService.this.click(paramLuaTable, paramLuaFunction);
      }
    }, paramLong);
  }
  
  public void postExecute(long paramLong, final String paramString, final AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    this.g.postDelayed(new Runnable()
    {
      public void run()
      {
        LuaAccessibilityService.this.execute(paramString, paramAccessibilityNodeInfo);
      }
    }, paramLong);
  }
  
  public void postExecute(long paramLong, final String paramString, final AccessibilityNodeInfo paramAccessibilityNodeInfo, final LuaFunction paramLuaFunction)
  {
    this.g.postDelayed(new Runnable()
    {
      public void run()
      {
        try
        {
          paramLuaFunction.call(new Object[] { Boolean.valueOf(LuaAccessibilityService.this.execute(paramString, paramAccessibilityNodeInfo)), paramString, paramAccessibilityNodeInfo });
          return;
        }
        catch (LuaException localLuaException)
        {
          localLuaException.printStackTrace();
          LuaAccessibilityService.a(LuaAccessibilityService.this, "postExecute", localLuaException);
        }
      }
    }, paramLong);
  }
  
  public boolean press(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.h != null) {
      return this.h.press(paramInt1, paramInt2, paramInt3);
    }
    return false;
  }
  
  public boolean press(Point paramPoint, int paramInt)
  {
    return press(paramPoint.x, paramPoint.y, paramInt);
  }
  
  public boolean scrollBackward(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT < 21)
    {
      if ((paramAccessibilityNodeInfo.getActions() & 0x2000) == 0) {
        return false;
      }
    }
    else if (!paramAccessibilityNodeInfo.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD)) {
      return false;
    }
    return paramAccessibilityNodeInfo.performAction(8192);
  }
  
  public boolean scrollForward(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo == null) {
      return false;
    }
    if (Build.VERSION.SDK_INT < 21)
    {
      if ((paramAccessibilityNodeInfo.getActions() & 0x1000) == 0) {
        return false;
      }
    }
    else if (!paramAccessibilityNodeInfo.getActionList().contains(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)) {
      return false;
    }
    return paramAccessibilityNodeInfo.performAction(4096);
  }
  
  public boolean setText(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    if ((paramAccessibilityNodeInfo != null) && (paramAccessibilityNodeInfo.isEditable()))
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        Bundle localBundle = new Bundle();
        localBundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", paramString);
        return paramAccessibilityNodeInfo.performAction(2097152, localBundle);
      }
      return paste(paramAccessibilityNodeInfo, paramString);
    }
    return false;
  }
  
  public boolean setText(String paramString)
  {
    return setText(getEditText(), paramString);
  }
  
  public boolean startApp(String paramString)
  {
    b();
    paramString = paramString.toLowerCase();
    paramString = (ComponentName)this.e.get(paramString);
    if (paramString == null) {
      return false;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setComponent(paramString);
    localIntent.setFlags(270532608);
    try
    {
      startActivity(localIntent);
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public void startScreenshot()
  {
    this.i = new b(this, null);
  }
  
  public void startScreenshot(VirtualDisplay.Callback paramCallback)
  {
    this.i = new b(this, paramCallback);
  }
  
  public void stopScreenshot()
  {
    if (this.i != null) {
      this.i.e();
    }
    this.i = null;
  }
  
  public boolean swipe(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (this.h != null) {
      return this.h.swipe(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    }
    return false;
  }
  
  public boolean swipe(Path paramPath, int paramInt)
  {
    if (this.h != null) {
      return this.h.gesture(0L, paramInt, paramPath);
    }
    return false;
  }
  
  public boolean swipe(Point paramPoint1, Point paramPoint2, int paramInt)
  {
    return swipe(paramPoint1.x, paramPoint1.y, paramPoint2.x, paramPoint2.y, paramInt);
  }
  
  public void toBack()
  {
    performGlobalAction(1);
  }
  
  public boolean toClick(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo != null) {
      try
      {
        boolean bool = paramAccessibilityNodeInfo.performAction(16);
        return bool;
      }
      catch (Exception paramAccessibilityNodeInfo)
      {
        paramAccessibilityNodeInfo.printStackTrace();
      }
    }
    return false;
  }
  
  public void toClick2(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    toClick(b(paramAccessibilityNodeInfo));
  }
  
  public void toHome()
  {
    performGlobalAction(2);
  }
  
  public boolean toLongClick(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo != null) {
      try
      {
        boolean bool = paramAccessibilityNodeInfo.performAction(32);
        return bool;
      }
      catch (Exception paramAccessibilityNodeInfo)
      {
        paramAccessibilityNodeInfo.printStackTrace();
      }
    }
    return false;
  }
  
  public void toNotifications()
  {
    performGlobalAction(4);
  }
  
  public void toRecents()
  {
    performGlobalAction(3);
  }
  
  public static abstract interface AccessibilityServiceCallbacks
  {
    public abstract void onAccessibilityEvent(LuaAccessibilityService paramLuaAccessibilityService, AccessibilityEvent paramAccessibilityEvent);
    
    public abstract void onConfigurationChanged(LuaAccessibilityService paramLuaAccessibilityService, Configuration paramConfiguration);
    
    public abstract void onCreate(LuaAccessibilityService paramLuaAccessibilityService);
    
    public abstract void onDestroy(LuaAccessibilityService paramLuaAccessibilityService);
    
    public abstract void onInterrupt(LuaAccessibilityService paramLuaAccessibilityService);
    
    public abstract boolean onKeyEvent(LuaAccessibilityService paramLuaAccessibilityService, KeyEvent paramKeyEvent);
    
    public abstract void onServiceConnected(LuaAccessibilityService paramLuaAccessibilityService);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaAccessibilityService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */