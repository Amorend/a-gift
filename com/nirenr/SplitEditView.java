package com.nirenr;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.a.a.b.f;
import com.androlua.LuaEditor;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitEditView
  extends LinearLayout
  implements View.OnClickListener, AdapterView.OnItemClickListener
{
  private final Context a;
  private LinearLayout b;
  private GridView c;
  private LuaEditor d;
  private int e = 0;
  private String f = "";
  private String[] g = { "" };
  private OnSaveListener h;
  private LinearLayout i;
  
  public SplitEditView(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
    this.a = paramContext;
  }
  
  private void a()
  {
    this.g = new String[] { this.f };
    switch (this.e)
    {
    default: 
      setShowEdit(true);
      return;
    case 4: 
      b();
      return;
    case 3: 
      d();
      return;
    case 2: 
      c();
      return;
    }
    e();
  }
  
  private void a(Context paramContext)
  {
    this.b = this;
    this.b.setOrientation(1);
    Object localObject = new LinearLayout.LayoutParams(-1, -1, 1.0F);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2, 1.0F);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
    this.c = new GridView(paramContext);
    this.d = new LuaEditor(paramContext);
    this.c.setOnItemClickListener(this);
    this.b.addView(this.c, (ViewGroup.LayoutParams)localObject);
    this.b.addView(this.d, (ViewGroup.LayoutParams)localObject);
    this.i = new LinearLayout(paramContext);
    localObject = new String[6];
    localObject[0] = "全文";
    localObject[1] = "按段";
    localObject[2] = "按行";
    localObject[3] = "按句";
    localObject[4] = "按字";
    localObject[5] = "确定";
    int j = 0;
    while (j < localObject.length)
    {
      CharSequence localCharSequence = localObject[j];
      Button localButton = new Button(paramContext);
      localButton.setText(localCharSequence);
      localButton.setId(j);
      localButton.setOnClickListener(this);
      this.i.addView(localButton, localLayoutParams1);
      j += 1;
    }
    this.b.addView(this.i, localLayoutParams2);
    this.c.setVisibility(8);
    this.d.setVisibility(0);
    setText("");
    setOnSaveListener(null);
  }
  
  private String[] a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    paramString2 = Pattern.compile(paramString2).matcher(paramString1);
    int j = 0;
    while (paramString2.find())
    {
      int k = paramString2.end();
      if (this.e == 1) {
        k = paramString2.start();
      }
      localArrayList.add(paramString1.substring(j, k));
      if (this.e == 1) {
        j = paramString2.end();
      } else {
        j = k;
      }
    }
    if (j != paramString1.length()) {
      localArrayList.add(paramString1.substring(j));
    }
    paramString1 = new String[localArrayList.size()];
    localArrayList.toArray(paramString1);
    return paramString1;
  }
  
  private void b()
  {
    this.f = getText();
    this.e = 4;
    this.g = new String[this.f.length()];
    int j = 0;
    while (j < this.f.length())
    {
      this.g[j] = String.valueOf(this.f.charAt(j));
      j += 1;
    }
    if (this.g.length == 0) {
      this.g = new String[] { "" };
    }
    this.c.setNumColumns(8);
    this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, this.g));
  }
  
  private void c()
  {
    this.f = getText();
    this.e = 2;
    this.g = a(this.f, "\n");
    if (this.g.length == 0) {
      this.g = new String[] { "" };
    }
    this.c.setNumColumns(1);
    this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, this.g));
  }
  
  private void d()
  {
    this.f = getText();
    this.e = 3;
    this.g = a(this.f, "\\. |[。？！，\n “”,：；;\\?!]+");
    if (this.g.length == 0) {
      this.g = new String[] { "" };
    }
    this.c.setNumColumns(1);
    this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, this.g));
  }
  
  private void e()
  {
    this.f = getText();
    this.e = 1;
    this.g = a(this.f, "\\n{2,10}");
    if (this.g.length == 0) {
      this.g = new String[] { "" };
    }
    this.c.setNumColumns(1);
    this.c.setAdapter(new ArrayListAdapter(this.a, 17367043, this.g));
  }
  
  private void f()
  {
    switch (this.e)
    {
    default: 
      return;
    case 4: 
      b();
      return;
    case 3: 
      d();
      return;
    case 2: 
      c();
      return;
    }
    e();
  }
  
  private void setShowEdit(boolean paramBoolean)
  {
    if (isShowEdit() == paramBoolean) {
      return;
    }
    if (!paramBoolean)
    {
      this.c.setVisibility(0);
      this.d.setVisibility(8);
      this.f = this.d.getText().toString();
      this.g = new String[] { this.f };
      return;
    }
    this.d.setText(getText());
    this.c.setVisibility(8);
    this.d.setVisibility(0);
  }
  
  public String getText()
  {
    if (isShowEdit()) {
      return this.d.getText().toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = this.g;
    int k = arrayOfString.length;
    int j = 0;
    while (j < k)
    {
      localStringBuilder.append(arrayOfString[j]);
      if (this.e == 1) {
        localStringBuilder.append("\n\n");
      }
      j += 1;
    }
    if (this.e == 1) {
      localStringBuilder.delete(localStringBuilder.length() - 2, localStringBuilder.length());
    }
    return localStringBuilder.toString();
  }
  
  public boolean isShowEdit()
  {
    return this.d.getVisibility() == 0;
  }
  
  public void onClick(View paramView)
  {
    Button localButton = (Button)paramView;
    switch (paramView.getId())
    {
    default: 
      
    case 5: 
      if (this.h != null)
      {
        this.h.onSave(getText());
        return;
      }
      break;
    case 4: 
      setShowEdit(false);
      b();
      return;
    case 3: 
      setShowEdit(false);
      d();
      return;
    case 2: 
      setShowEdit(false);
      c();
      return;
    case 1: 
      setShowEdit(false);
      e();
      return;
    case 0: 
      setShowEdit(true);
      this.e = 0;
    }
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    new EditDialog(paramInt).show();
  }
  
  public void setOnSaveListener(OnSaveListener paramOnSaveListener)
  {
    this.h = paramOnSaveListener;
    if (paramOnSaveListener == null) {
      paramOnSaveListener = this.i.getChildAt(5);
    }
    for (int j = 8;; j = 0)
    {
      paramOnSaveListener.setVisibility(j);
      return;
      paramOnSaveListener = this.i.getChildAt(5);
    }
  }
  
  public void setText(String paramString)
  {
    this.f = paramString;
    if (this.f == null) {
      this.f = "";
    }
    this.d.setText(this.f);
    a();
  }
  
  private class EditDialog
    implements DialogInterface.OnClickListener
  {
    private final int b;
    private final EditText c;
    private AlertDialog d;
    
    public EditDialog(int paramInt)
    {
      this.b = paramInt;
      this.c = new EditText(SplitEditView.a(SplitEditView.this));
      this.c.setText(SplitEditView.b(SplitEditView.this)[paramInt]);
      this.c.setSelection(SplitEditView.b(SplitEditView.this)[paramInt].length());
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      SplitEditView.b(SplitEditView.this)[this.b] = this.c.getText().toString();
      SplitEditView.c(SplitEditView.this);
      SplitEditView.d(SplitEditView.this).smoothScrollToPosition(this.b);
    }
    
    public void show()
    {
      this.d = new AlertDialog.Builder(SplitEditView.a(SplitEditView.this)).setTitle("输入内容").setView(this.c).setNegativeButton(17039360, null).setPositiveButton(17039370, this).setCancelable(false).create();
      Window localWindow = this.d.getWindow();
      if (localWindow != null)
      {
        localWindow.setSoftInputMode(4);
        if ((SplitEditView.a(SplitEditView.this) instanceof Service))
        {
          if (Build.VERSION.SDK_INT >= 22) {}
          for (int i = 2032;; i = 2010)
          {
            localWindow.setType(i);
            break;
          }
        }
        this.d.show();
      }
      this.c.setFocusable(true);
      this.c.requestFocus();
    }
  }
  
  public static abstract interface OnSaveListener
  {
    public abstract void onSave(String paramString);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\nirenr\SplitEditView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */