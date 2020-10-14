package com.a.a.a;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;

public class b
{
  protected c a;
  private Context b;
  private ActionMode c;
  
  public b(c paramc)
  {
    this.a = paramc;
    this.b = paramc.getContext();
  }
  
  public void a()
  {
    c();
  }
  
  public void b()
  {
    d();
  }
  
  public void c()
  {
    if (this.c == null) {
      this.a.startActionMode(new ActionMode.Callback()
      {
        public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
        {
          switch (paramAnonymousMenuItem.getItemId())
          {
          default: 
            break;
          case 3: 
            b.this.a.paste();
            break;
          case 2: 
            b.this.a.copy();
            break;
          case 1: 
            b.this.a.cut();
            paramAnonymousActionMode.finish();
            break;
          }
          b.this.a.selectAll();
          return false;
        }
        
        public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
        {
          b.a(b.this, paramAnonymousActionMode);
          paramAnonymousActionMode.setTitle(17039382);
          paramAnonymousActionMode = b.a(b.this).getTheme().obtainStyledAttributes(new int[] { 16843646, 16843537, 16843538, 16843539 });
          paramAnonymousMenu.add(0, 0, 0, b.a(b.this).getString(17039373)).setShowAsActionFlags(2).setAlphabeticShortcut('a').setIcon(paramAnonymousActionMode.getDrawable(0));
          paramAnonymousMenu.add(0, 1, 0, b.a(b.this).getString(17039363)).setShowAsActionFlags(2).setAlphabeticShortcut('x').setIcon(paramAnonymousActionMode.getDrawable(1));
          paramAnonymousMenu.add(0, 2, 0, b.a(b.this).getString(17039361)).setShowAsActionFlags(2).setAlphabeticShortcut('c').setIcon(paramAnonymousActionMode.getDrawable(2));
          paramAnonymousMenu.add(0, 3, 0, b.a(b.this).getString(17039371)).setShowAsActionFlags(2).setAlphabeticShortcut('v').setIcon(paramAnonymousActionMode.getDrawable(3));
          paramAnonymousActionMode.recycle();
          return true;
        }
        
        public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
        {
          b.this.a.selectText(false);
          b.a(b.this, null);
        }
        
        public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
        {
          return false;
        }
      });
    }
  }
  
  public void d()
  {
    if (this.c != null)
    {
      this.c.finish();
      this.c = null;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\a\a\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */