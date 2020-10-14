package com.androlua;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.ClientCertRequest;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.luajava.LuaException;
import com.luajava.LuaFunction;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LuaWebView
  extends WebView
  implements LuaGcable
{
  private DownloadBroadcastReceiver a;
  private HashMap<Long, String[]> b = new HashMap();
  private OnDownloadCompleteListener c;
  private LuaActivity d;
  private ProgressBar e;
  private DisplayMetrics f;
  private Dialog g;
  private ListView h;
  private ValueCallback<Uri> i;
  private String j = "/";
  private LuaFunction<Boolean> k;
  
  @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
  public LuaWebView(LuaActivity paramLuaActivity)
  {
    super(paramLuaActivity);
    paramLuaActivity.regGc(this);
    this.d = paramLuaActivity;
    getSettings().setJavaScriptEnabled(true);
    getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    getSettings().setDisplayZoomControls(true);
    getSettings().setSupportZoom(true);
    getSettings().setDomStorageEnabled(true);
    if (Build.VERSION.SDK_INT >= 21) {
      getSettings().setMixedContentMode(0);
    }
    addJavascriptInterface(new LuaJavaScriptinterface(paramLuaActivity), "androlua");
    setWebViewClient(new WebViewClient()
    {
      public void onReceivedSslError(WebView paramAnonymousWebView, final SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
      {
        paramAnonymousWebView = new AlertDialog.Builder(LuaWebView.b(LuaWebView.this));
        paramAnonymousWebView.setTitle("SslError");
        paramAnonymousWebView.setMessage(paramAnonymousSslError.toString());
        paramAnonymousWebView.setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymousSslErrorHandler.proceed();
          }
        });
        paramAnonymousWebView.setNegativeButton(17039360, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymousSslErrorHandler.cancel();
          }
        });
        paramAnonymousWebView.setCancelable(false);
        paramAnonymousWebView.create();
        paramAnonymousWebView.show();
      }
      
      public WebResourceResponse shouldInterceptRequest(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (LuaWebView.a(LuaWebView.this) != null) {
          try
          {
            paramAnonymousWebView = (Boolean)LuaWebView.a(LuaWebView.this).call(new Object[] { paramAnonymousString });
            if ((paramAnonymousWebView != null) && (paramAnonymousWebView.booleanValue()))
            {
              paramAnonymousWebView = new WebResourceResponse(null, null, null);
              return paramAnonymousWebView;
            }
          }
          catch (LuaException paramAnonymousWebView)
          {
            paramAnonymousWebView.printStackTrace();
          }
        }
        return null;
      }
      
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        if (LuaWebView.a(LuaWebView.this) != null) {
          try
          {
            Boolean localBoolean = (Boolean)LuaWebView.a(LuaWebView.this).call(new Object[] { paramAnonymousString });
            if (localBoolean != null)
            {
              boolean bool = localBoolean.booleanValue();
              if (bool) {
                return true;
              }
            }
          }
          catch (LuaException localLuaException)
          {
            localLuaException.printStackTrace();
          }
        }
        if ((!paramAnonymousString.startsWith("http")) && (!paramAnonymousString.startsWith("file"))) {
          try
          {
            LuaWebView.b(LuaWebView.this).startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString)), 0);
            return true;
          }
          catch (Exception paramAnonymousWebView)
          {
            LuaWebView.b(LuaWebView.this).sendError("LuaWebView", paramAnonymousWebView);
            return true;
          }
        }
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return true;
      }
    });
    this.f = paramLuaActivity.getResources().getDisplayMetrics();
    int m = (int)TypedValue.applyDimension(1, 2.0F, this.f);
    this.e = new ProgressBar(paramLuaActivity, null, 16842872);
    this.e.setLayoutParams(new AbsoluteLayout.LayoutParams(-1, m, 0, 0));
    addView(this.e);
    setWebChromeClient(new LuaWebChromeClient());
    setDownloadListener(new Download(null));
  }
  
  @SuppressLint({"AddJavascriptInterface"})
  public void addJSInterface(JsInterface paramJsInterface, String paramString)
  {
    super.addJavascriptInterface(new JsObject(paramJsInterface), paramString);
  }
  
  @SuppressLint({"AddJavascriptInterface"})
  public void addJsInterface(JsInterface paramJsInterface, String paramString)
  {
    super.addJavascriptInterface(new JsObject(paramJsInterface), paramString);
  }
  
  public void destroy()
  {
    if (this.a != null) {
      this.d.unregisterReceiver(this.a);
    }
    super.destroy();
  }
  
  public void gc()
  {
    destroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (canGoBack()))
    {
      goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    AbsoluteLayout.LayoutParams localLayoutParams = (AbsoluteLayout.LayoutParams)this.e.getLayoutParams();
    localLayoutParams.x = paramInt1;
    localLayoutParams.y = paramInt2;
    this.e.setLayoutParams(localLayoutParams);
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void openFile(String paramString)
  {
    if (this.g == null)
    {
      this.g = new Dialog(getContext());
      this.h = new ListView(getContext());
      this.h.setFastScrollEnabled(true);
      this.h.setFastScrollAlwaysVisible(true);
      this.g.setContentView(this.h);
      this.h.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = ((TextView)paramAnonymousView).getText().toString();
          if (paramAnonymousAdapterView.equals("../"))
          {
            paramAnonymousAdapterView = LuaWebView.this;
            paramAnonymousView = new StringBuilder();
            paramAnonymousView.append(new File(LuaWebView.c(LuaWebView.this)).getParent());
            paramAnonymousView.append("/");
            LuaWebView.a(paramAnonymousAdapterView, paramAnonymousView.toString());
            LuaWebView.this.openFile(LuaWebView.c(LuaWebView.this));
            return;
          }
          paramAnonymousView = new StringBuilder();
          paramAnonymousView.append(LuaWebView.c(LuaWebView.this));
          paramAnonymousView.append(paramAnonymousAdapterView);
          paramAnonymousAdapterView = paramAnonymousView.toString();
          if (new File(paramAnonymousAdapterView).isDirectory())
          {
            LuaWebView.a(LuaWebView.this, paramAnonymousAdapterView);
            LuaWebView.this.openFile(LuaWebView.c(LuaWebView.this));
            return;
          }
          LuaWebView.d(LuaWebView.this).onReceiveValue(Uri.parse(paramAnonymousAdapterView));
        }
      });
    }
    Object localObject = new File(paramString);
    paramString = new ArrayList();
    paramString.add("../");
    localObject = ((File)localObject).list();
    if (localObject != null)
    {
      Arrays.sort((Object[])localObject);
      int i1 = localObject.length;
      int n = 0;
      int m = 0;
      String str;
      StringBuilder localStringBuilder;
      while (m < i1)
      {
        str = localObject[m];
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(this.j);
        localStringBuilder.append(str);
        if (new File(localStringBuilder.toString()).isDirectory())
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(str);
          localStringBuilder.append("/");
          paramString.add(localStringBuilder.toString());
        }
        m += 1;
      }
      i1 = localObject.length;
      m = n;
      while (m < i1)
      {
        str = localObject[m];
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(this.j);
        localStringBuilder.append(str);
        if (new File(localStringBuilder.toString()).isFile()) {
          paramString.add(str);
        }
        m += 1;
      }
    }
    paramString = new ArrayAdapter(getContext(), 17367043, paramString);
    this.h.setAdapter(paramString);
    this.g.setTitle(this.j);
    this.g.show();
  }
  
  public void setAdsFilter(LuaFunction<Boolean> paramLuaFunction)
  {
    this.k = paramLuaFunction;
  }
  
  public void setDownloadListener(DownloadListener paramDownloadListener)
  {
    super.setDownloadListener(paramDownloadListener);
  }
  
  public void setOnDownloadCompleteListener(OnDownloadCompleteListener paramOnDownloadCompleteListener)
  {
    this.c = paramOnDownloadCompleteListener;
  }
  
  public void setOnDownloadStartListener(final OnDownloadStartListener paramOnDownloadStartListener)
  {
    setDownloadListener(new DownloadListener()
    {
      public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
      {
        paramOnDownloadStartListener.onDownloadStart(paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousString4, paramAnonymousLong);
      }
    });
  }
  
  public void setOnKeyListener(View.OnKeyListener paramOnKeyListener)
  {
    super.setOnKeyListener(paramOnKeyListener);
  }
  
  public void setProgressBar(ProgressBar paramProgressBar)
  {
    this.e = paramProgressBar;
  }
  
  public void setProgressBarEnabled(boolean paramBoolean)
  {
    ProgressBar localProgressBar;
    if (paramBoolean) {
      localProgressBar = this.e;
    }
    for (int m = 0;; m = 8)
    {
      localProgressBar.setVisibility(m);
      return;
      localProgressBar = this.e;
    }
  }
  
  public void setWebViewClient(LuaWebViewClient paramLuaWebViewClient)
  {
    super.setWebViewClient(new SimpleLuaWebViewClient(paramLuaWebViewClient));
  }
  
  private class Download
    implements DownloadListener
  {
    EditText a;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;
    private String h;
    
    private Download() {}
    
    private long a(boolean paramBoolean)
    {
      if (LuaWebView.g(LuaWebView.this) == null)
      {
        localObject1 = new IntentFilter();
        ((IntentFilter)localObject1).addAction("android.intent.action.DOWNLOAD_COMPLETE");
        LuaWebView.a(LuaWebView.this, new LuaWebView.DownloadBroadcastReceiver(LuaWebView.this, null));
        LuaWebView.b(LuaWebView.this).registerReceiver(LuaWebView.g(LuaWebView.this), (IntentFilter)localObject1);
      }
      Object localObject1 = (DownloadManager)LuaWebView.b(LuaWebView.this).getSystemService("download");
      Object localObject2 = Uri.parse(this.c);
      ((Uri)localObject2).getLastPathSegment();
      localObject2 = new DownloadManager.Request((Uri)localObject2);
      String str = LuaWebView.b(LuaWebView.this).getLuaExtDir("Download");
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(new File(LuaWebView.b(LuaWebView.this).getLuaExtDir()).getName());
      ((StringBuilder)localObject3).append("/");
      ((StringBuilder)localObject3).append("Download");
      ((DownloadManager.Request)localObject2).setDestinationInExternalPublicDir(((StringBuilder)localObject3).toString(), this.h);
      ((DownloadManager.Request)localObject2).setTitle(this.h);
      ((DownloadManager.Request)localObject2).setDescription(this.c);
      if (paramBoolean) {
        ((DownloadManager.Request)localObject2).setAllowedNetworkTypes(2);
      }
      localObject3 = new File(str, this.h);
      if (((File)localObject3).exists()) {
        ((File)localObject3).delete();
      }
      ((DownloadManager.Request)localObject2).setMimeType(this.f);
      long l = ((DownloadManager)localObject1).enqueue((DownloadManager.Request)localObject2);
      LuaWebView.e(LuaWebView.this).put(Long.valueOf(l), new String[] { new File(str, this.h).getAbsolutePath(), this.f });
      return l;
    }
    
    @SuppressLint({"DefaultLocale"})
    public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
    {
      this.c = paramString1;
      this.d = paramString2;
      this.e = paramString3;
      this.f = paramString4;
      this.g = paramLong;
      this.h = Uri.parse(this.c).getLastPathSegment();
      if (paramString3 != null)
      {
        int i = paramString3.indexOf("filename=\"");
        if (i != -1)
        {
          i += "filename=\"".length();
          int j = paramString3.indexOf('"', i);
          if (j > i) {
            this.h = paramString3.substring(i, j);
          }
        }
      }
      this.a = new EditText(LuaWebView.b(LuaWebView.this));
      this.a.setText(this.h);
      paramString1 = new StringBuilder();
      paramString1.append(String.valueOf(paramLong));
      paramString1.append("B");
      paramString1 = paramString1.toString();
      if (paramLong > 1048576L)
      {
        paramString1 = "%.2f MB";
        paramString2 = new Object[1];
        paramString2[0] = Double.valueOf(Long.valueOf(paramLong).doubleValue() / 1048576.0D);
      }
      for (;;)
      {
        paramString1 = String.format(paramString1, paramString2);
        break;
        if (paramLong <= 1024L) {
          break;
        }
        paramString1 = "%.2f KB";
        paramString2 = new Object[1];
        paramString2[0] = Double.valueOf(Long.valueOf(paramLong).doubleValue() / 1024.0D);
      }
      paramString2 = new AlertDialog.Builder(LuaWebView.b(LuaWebView.this)).setTitle("Download");
      paramString3 = new StringBuilder();
      paramString3.append("Type: ");
      paramString3.append(paramString4);
      paramString3.append("\nSize: ");
      paramString3.append(paramString1);
      paramString2.setMessage(paramString3.toString()).setView(this.a).setPositiveButton("Download", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          LuaWebView.Download.a(LuaWebView.Download.this, LuaWebView.Download.this.a.getText().toString());
          LuaWebView.Download.a(LuaWebView.Download.this, false);
        }
      }).setNegativeButton(17039360, null).setNeutralButton("Only Wifi", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          LuaWebView.Download.a(LuaWebView.Download.this, LuaWebView.Download.this.a.getText().toString());
          LuaWebView.Download.a(LuaWebView.Download.this, true);
        }
      }).create().show();
    }
  }
  
  private class DownloadBroadcastReceiver
    extends BroadcastReceiver
  {
    private DownloadBroadcastReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      long l = paramIntent.getLongExtra("extra_download_id", 0L);
      paramIntent.getExtras();
      if ((LuaWebView.e(LuaWebView.this).containsKey(Long.valueOf(l))) && (LuaWebView.f(LuaWebView.this) != null))
      {
        paramContext = (String[])LuaWebView.e(LuaWebView.this).get(Long.valueOf(l));
        LuaWebView.f(LuaWebView.this).onDownloadComplete(paramContext[0], paramContext[1]);
      }
    }
  }
  
  public static abstract interface JsInterface
  {
    @JavascriptInterface
    public abstract String execute(String paramString);
  }
  
  class JsObject
  {
    private LuaWebView.JsInterface b;
    
    public JsObject(LuaWebView.JsInterface paramJsInterface)
    {
      this.b = paramJsInterface;
    }
    
    @JavascriptInterface
    public String execute(String paramString)
    {
      return this.b.execute(paramString);
    }
  }
  
  private class LuaJavaScriptinterface
  {
    private LuaActivity b;
    
    public LuaJavaScriptinterface(LuaActivity paramLuaActivity)
    {
      this.b = paramLuaActivity;
    }
    
    @JavascriptInterface
    public Object callLuaFunction(String paramString)
    {
      return this.b.runFunc(paramString, new Object[0]);
    }
    
    @JavascriptInterface
    public Object callLuaFunction(String paramString1, String paramString2)
    {
      return this.b.runFunc(paramString1, new Object[] { paramString2 });
    }
    
    @JavascriptInterface
    public Object doLuaString(String paramString)
    {
      return this.b.doString(paramString, new Object[0]);
    }
  }
  
  class LuaWebChromeClient
    extends WebChromeClient
  {
    EditText a = new EditText(LuaWebView.b(LuaWebView.this));
    
    LuaWebChromeClient() {}
    
    public Bitmap getDefaultVideoPoster()
    {
      return BitmapFactory.decodeResource(LuaWebView.b(LuaWebView.this).getResources(), 2130771968);
    }
    
    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
    {
      new AlertDialog.Builder(LuaWebView.b(LuaWebView.this)).setTitle(paramString1).setMessage(paramString2).setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.confirm();
        }
      }).setCancelable(false).create().show();
      return true;
    }
    
    public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
    {
      paramWebView = new AlertDialog.Builder(LuaWebView.b(LuaWebView.this));
      paramWebView.setTitle(paramString1);
      paramWebView.setMessage(paramString2);
      paramWebView.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.confirm();
        }
      });
      paramWebView.setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsResult.cancel();
        }
      });
      paramWebView.setCancelable(false);
      paramWebView.create();
      paramWebView.show();
      return true;
    }
    
    public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, final JsPromptResult paramJsPromptResult)
    {
      this.a.setText(paramString3);
      paramWebView = new AlertDialog.Builder(LuaWebView.b(LuaWebView.this));
      paramWebView.setTitle(paramString1);
      paramWebView.setMessage(paramString2);
      paramWebView.setView(this.a);
      paramWebView.setPositiveButton(17039370, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface = LuaWebView.LuaWebChromeClient.this.a.getText().toString();
          paramJsPromptResult.confirm(paramAnonymousDialogInterface);
        }
      });
      paramWebView.setNegativeButton(17039360, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramJsPromptResult.cancel();
        }
      });
      paramWebView.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          paramJsPromptResult.cancel();
        }
      });
      paramWebView.show();
      return true;
    }
    
    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      if (paramInt == 100)
      {
        LuaWebView.h(LuaWebView.this).setVisibility(8);
      }
      else
      {
        LuaWebView.h(LuaWebView.this).setVisibility(0);
        LuaWebView.h(LuaWebView.this).setProgress(paramInt);
      }
      super.onProgressChanged(paramWebView, paramInt);
    }
    
    public void onReceivedIcon(WebView paramWebView, Bitmap paramBitmap)
    {
      super.onReceivedIcon(paramWebView, paramBitmap);
    }
    
    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      super.onReceivedTitle(paramWebView, paramString);
    }
    
    public void openFileChooser(ValueCallback<Uri> paramValueCallback)
    {
      openFileChooser(paramValueCallback, "");
    }
    
    public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString)
    {
      if (LuaWebView.d(LuaWebView.this) != null) {
        return;
      }
      LuaWebView.a(LuaWebView.this, paramValueCallback);
      LuaWebView.this.openFile(LuaWebView.c(LuaWebView.this));
    }
    
    public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2)
    {
      openFileChooser(paramValueCallback, paramString1);
    }
  }
  
  public static abstract interface LuaWebViewClient
  {
    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;
    
    public abstract void doUpdateVisitedHistory(WebView paramWebView, String paramString, boolean paramBoolean);
    
    public abstract void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2);
    
    public abstract void onLoadResource(WebView paramWebView, String paramString);
    
    public abstract void onPageFinished(WebView paramWebView, String paramString);
    
    public abstract void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap);
    
    public abstract void onProceededAfterSslError(WebView paramWebView, SslError paramSslError);
    
    public abstract void onReceivedClientCertRequest(WebView paramWebView, ClientCertRequest paramClientCertRequest, String paramString);
    
    public abstract void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2);
    
    public abstract void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2);
    
    public abstract void onReceivedLoginRequest(WebView paramWebView, String paramString1, String paramString2, String paramString3);
    
    public abstract void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError);
    
    public abstract void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2);
    
    @Deprecated
    public abstract void onTooManyRedirects(WebView paramWebView, Message paramMessage1, Message paramMessage2);
    
    public abstract void onUnhandledKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent);
    
    public abstract WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString);
    
    public abstract boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent);
    
    public abstract boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString);
  }
  
  public static abstract interface OnDownloadCompleteListener
  {
    public abstract void onDownloadComplete(String paramString1, String paramString2);
  }
  
  public static abstract interface OnDownloadStartListener
  {
    public abstract void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong);
  }
  
  private class SimpleLuaWebViewClient
    extends WebViewClient
  {
    private LuaWebView.LuaWebViewClient b;
    
    public SimpleLuaWebViewClient(LuaWebView.LuaWebViewClient paramLuaWebViewClient)
    {
      this.b = paramLuaWebViewClient;
    }
    
    public void doUpdateVisitedHistory(WebView paramWebView, String paramString, boolean paramBoolean)
    {
      this.b.doUpdateVisitedHistory(paramWebView, paramString, paramBoolean);
    }
    
    public void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2)
    {
      paramMessage1.sendToTarget();
    }
    
    public void onLoadResource(WebView paramWebView, String paramString)
    {
      this.b.onLoadResource(paramWebView, paramString);
    }
    
    public void onPageFinished(WebView paramWebView, String paramString)
    {
      this.b.onPageFinished(paramWebView, paramString);
    }
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      this.b.onPageStarted(paramWebView, paramString, paramBitmap);
    }
    
    public void onProceededAfterSslError(WebView paramWebView, SslError paramSslError)
    {
      this.b.onProceededAfterSslError(paramWebView, paramSslError);
    }
    
    public void onReceivedClientCertRequest(WebView paramWebView, ClientCertRequest paramClientCertRequest, String paramString)
    {
      this.b.onReceivedClientCertRequest(paramWebView, paramClientCertRequest, paramString);
    }
    
    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      this.b.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }
    
    public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
    {
      this.b.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler, paramString1, paramString2);
    }
    
    public void onReceivedLoginRequest(WebView paramWebView, String paramString1, String paramString2, String paramString3)
    {
      this.b.onReceivedLoginRequest(paramWebView, paramString1, paramString2, paramString3);
    }
    
    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      this.b.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }
    
    public void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2)
    {
      this.b.onScaleChanged(paramWebView, paramFloat1, paramFloat2);
    }
    
    @Deprecated
    public void onTooManyRedirects(WebView paramWebView, Message paramMessage1, Message paramMessage2)
    {
      paramMessage1.sendToTarget();
    }
    
    public void onUnhandledKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
    {
      this.b.onUnhandledKeyEvent(paramWebView, paramKeyEvent);
    }
    
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
      return super.shouldInterceptRequest(paramWebView, paramWebResourceRequest);
    }
    
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
      if (LuaWebView.a(LuaWebView.this) != null) {
        try
        {
          if (((Boolean)LuaWebView.a(LuaWebView.this).call(new Object[] { paramString })).booleanValue())
          {
            WebResourceResponse localWebResourceResponse = new WebResourceResponse(null, null, null);
            return localWebResourceResponse;
          }
        }
        catch (LuaException localLuaException)
        {
          localLuaException.printStackTrace();
        }
      }
      return this.b.shouldInterceptRequest(paramWebView, paramString);
    }
    
    public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
    {
      return this.b.shouldOverrideKeyEvent(paramWebView, paramKeyEvent);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      return this.b.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\androlua\LuaWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */