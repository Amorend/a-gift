package com.tencent.open.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IRequestListener;
import java.io.ByteArrayOutputStream;
import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.io.NotActiveException;
import java.io.NotSerializableException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.io.SyncFailedException;
import java.io.UTFDataFormatException;
import java.io.UnsupportedEncodingException;
import java.io.WriteAbortedException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileLockInterruptionException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtils
{
  private static int a(Context paramContext)
  {
    int j = -1;
    int i;
    if (Build.VERSION.SDK_INT < 11) {
      if (paramContext != null)
      {
        j = Proxy.getPort(paramContext);
        i = j;
        if (j < 0) {
          i = Proxy.getDefaultPort();
        }
      }
    }
    for (;;)
    {
      return i;
      i = Proxy.getDefaultPort();
      continue;
      paramContext = System.getProperty("http.proxyPort");
      i = j;
      if (!TextUtils.isEmpty(paramContext)) {
        try
        {
          i = Integer.parseInt(paramContext);
        }
        catch (NumberFormatException paramContext)
        {
          i = j;
        }
      }
    }
  }
  
  private static String a(HttpResponse paramHttpResponse)
    throws IllegalStateException, IOException
  {
    Object localObject = paramHttpResponse.getEntity().getContent();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Header localHeader = paramHttpResponse.getFirstHeader("Content-Encoding");
    paramHttpResponse = (HttpResponse)localObject;
    if (localHeader != null)
    {
      paramHttpResponse = (HttpResponse)localObject;
      if (localHeader.getValue().toLowerCase().indexOf("gzip") > -1) {
        paramHttpResponse = new GZIPInputStream((InputStream)localObject);
      }
    }
    localObject = new byte['Ȁ'];
    for (;;)
    {
      int i = paramHttpResponse.read((byte[])localObject);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write((byte[])localObject, 0, i);
    }
    localObject = new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
    paramHttpResponse.close();
    return (String)localObject;
  }
  
  private static void a(Context paramContext, QQToken paramQQToken, String paramString)
  {
    if ((paramString.indexOf("add_share") > -1) || (paramString.indexOf("upload_pic") > -1) || (paramString.indexOf("add_topic") > -1) || (paramString.indexOf("set_user_face") > -1) || (paramString.indexOf("add_t") > -1) || (paramString.indexOf("add_pic_t") > -1) || (paramString.indexOf("add_pic_url") > -1) || (paramString.indexOf("add_video") > -1)) {
      a.a(paramContext, paramQQToken, "requireApi", new String[] { paramString });
    }
  }
  
  private static String b(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 11) {
      if (paramContext != null)
      {
        String str = Proxy.getHost(paramContext);
        paramContext = str;
        if (TextUtils.isEmpty(str)) {
          paramContext = Proxy.getDefaultHost();
        }
      }
    }
    for (;;)
    {
      return paramContext;
      paramContext = Proxy.getDefaultHost();
      continue;
      paramContext = System.getProperty("http.proxyHost");
    }
  }
  
  public static String encodePostBody(Bundle paramBundle, String paramString)
  {
    if (paramBundle == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = -1;
    int j = paramBundle.size();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      i += 1;
      Object localObject = paramBundle.get(str);
      if ((localObject instanceof String))
      {
        localStringBuilder.append("Content-Disposition: form-data; name=\"" + str + "\"" + "\r\n" + "\r\n" + (String)localObject);
        if (i < j - 1) {
          localStringBuilder.append("\r\n--" + paramString + "\r\n");
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      Object localObject2 = paramBundle.get((String)localObject1);
      if (((localObject2 instanceof String)) || ((localObject2 instanceof String[])))
      {
        label148:
        int j;
        if ((localObject2 instanceof String[]))
        {
          if (i != 0) {
            i = 0;
          }
          for (;;)
          {
            localStringBuilder.append(URLEncoder.encode((String)localObject1) + "=");
            localObject1 = paramBundle.getStringArray((String)localObject1);
            if (localObject1 != null) {
              break label148;
            }
            break;
            localStringBuilder.append("&");
          }
          int k = 0;
          j = i;
          if (k < localObject1.length)
          {
            if (k == 0) {
              localStringBuilder.append(URLEncoder.encode(localObject1[k]));
            }
            for (;;)
            {
              k += 1;
              break;
              localStringBuilder.append(URLEncoder.encode("," + localObject1[k]));
            }
          }
          i = j;
        }
        else
        {
          if (i != 0) {
            i = 0;
          }
          for (;;)
          {
            localStringBuilder.append(URLEncoder.encode((String)localObject1) + "=" + URLEncoder.encode(paramBundle.getString((String)localObject1)));
            j = i;
            break;
            localStringBuilder.append("&");
          }
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static int getErrorCodeFromException(IOException paramIOException)
  {
    if ((paramIOException instanceof CharConversionException)) {
      return -20;
    }
    if ((paramIOException instanceof MalformedInputException)) {
      return -21;
    }
    if ((paramIOException instanceof UnmappableCharacterException)) {
      return -22;
    }
    if ((paramIOException instanceof HttpResponseException)) {
      return -23;
    }
    if ((paramIOException instanceof ClosedChannelException)) {
      return -24;
    }
    if ((paramIOException instanceof ConnectionClosedException)) {
      return -25;
    }
    if ((paramIOException instanceof EOFException)) {
      return -26;
    }
    if ((paramIOException instanceof FileLockInterruptionException)) {
      return -27;
    }
    if ((paramIOException instanceof FileNotFoundException)) {
      return -28;
    }
    if ((paramIOException instanceof HttpRetryException)) {
      return -29;
    }
    if ((paramIOException instanceof ConnectTimeoutException)) {
      return -7;
    }
    if ((paramIOException instanceof SocketTimeoutException)) {
      return -8;
    }
    if ((paramIOException instanceof InvalidPropertiesFormatException)) {
      return -30;
    }
    if ((paramIOException instanceof MalformedChunkCodingException)) {
      return -31;
    }
    if ((paramIOException instanceof MalformedURLException)) {
      return -3;
    }
    if ((paramIOException instanceof NoHttpResponseException)) {
      return -32;
    }
    if ((paramIOException instanceof InvalidClassException)) {
      return -33;
    }
    if ((paramIOException instanceof InvalidObjectException)) {
      return -34;
    }
    if ((paramIOException instanceof NotActiveException)) {
      return -35;
    }
    if ((paramIOException instanceof NotSerializableException)) {
      return -36;
    }
    if ((paramIOException instanceof OptionalDataException)) {
      return -37;
    }
    if ((paramIOException instanceof StreamCorruptedException)) {
      return -38;
    }
    if ((paramIOException instanceof WriteAbortedException)) {
      return -39;
    }
    if ((paramIOException instanceof ProtocolException)) {
      return -40;
    }
    if ((paramIOException instanceof SSLHandshakeException)) {
      return -41;
    }
    if ((paramIOException instanceof SSLKeyException)) {
      return -42;
    }
    if ((paramIOException instanceof SSLPeerUnverifiedException)) {
      return -43;
    }
    if ((paramIOException instanceof SSLProtocolException)) {
      return -44;
    }
    if ((paramIOException instanceof BindException)) {
      return -45;
    }
    if ((paramIOException instanceof ConnectException)) {
      return -46;
    }
    if ((paramIOException instanceof NoRouteToHostException)) {
      return -47;
    }
    if ((paramIOException instanceof PortUnreachableException)) {
      return -48;
    }
    if ((paramIOException instanceof SyncFailedException)) {
      return -49;
    }
    if ((paramIOException instanceof UTFDataFormatException)) {
      return -50;
    }
    if ((paramIOException instanceof UnknownHostException)) {
      return -51;
    }
    if ((paramIOException instanceof UnknownServiceException)) {
      return -52;
    }
    if ((paramIOException instanceof UnsupportedEncodingException)) {
      return -53;
    }
    if ((paramIOException instanceof ZipException)) {
      return -54;
    }
    return -2;
  }
  
  public static HttpClient getHttpClient(Context paramContext, String paramString1, String paramString2)
  {
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    try
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        paramString2 = SSLSocketFactory.getSocketFactory();
        paramString2.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        localSchemeRegistry.register(new Scheme("https", paramString2, 443));
      }
      for (;;)
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        paramString2 = null;
        if (paramContext != null) {
          paramString2 = f.a(paramContext, paramString1);
        }
        int j = 0;
        int i = 0;
        if (paramString2 != null)
        {
          j = paramString2.a("Common_HttpConnectionTimeout");
          i = paramString2.a("Common_SocketConnectionTimeout");
        }
        if (j != 0) {
          break;
        }
        j = 15000;
        if (i != 0) {
          break label335;
        }
        i = 30000;
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, j);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, i);
        HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
        HttpProtocolParams.setUserAgent(localBasicHttpParams, "AndroidSDK_" + Build.VERSION.SDK + "_" + Build.DEVICE + "_" + Build.VERSION.RELEASE);
        paramString1 = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
        paramContext = getProxy(paramContext);
        if (paramContext != null)
        {
          paramContext = new HttpHost(paramContext.a, paramContext.b);
          paramString1.getParams().setParameter("http.route.default-proxy", paramContext);
        }
        return paramString1;
        localSchemeRegistry.register(new Scheme("https", new j(), 443));
      }
    }
    catch (Exception paramString2)
    {
      label335:
      for (;;)
      {
        localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        continue;
      }
    }
  }
  
  public static a getProxy(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localObject == null) {
      return null;
    }
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if (localObject == null) {
      return null;
    }
    if (((NetworkInfo)localObject).getType() == 0)
    {
      localObject = b(paramContext);
      int i = a(paramContext);
      if ((!TextUtils.isEmpty((CharSequence)localObject)) && (i >= 0)) {
        return new a((String)localObject, i, null);
      }
    }
    return null;
  }
  
  public static k.a openUrl2(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws MalformedURLException, IOException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException
  {
    Object localObject1;
    if (paramContext != null)
    {
      localObject1 = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localObject1 != null)
      {
        localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
        if ((localObject1 == null) || (!((NetworkInfo)localObject1).isAvailable())) {
          throw new NetworkUnavailableException("network unavailable");
        }
      }
    }
    int j;
    int i;
    if (paramBundle != null)
    {
      paramBundle = new Bundle(paramBundle);
      localObject1 = paramBundle.getString("appid_for_getting_config");
      paramBundle.remove("appid_for_getting_config");
      localObject1 = getHttpClient(paramContext, (String)localObject1, paramString1);
      paramContext = null;
      j = 0;
      if (!paramString2.equals("GET")) {
        break label328;
      }
      paramString2 = encodeUrl(paramBundle);
      i = j + paramString2.length();
      com.tencent.open.a.f.a("openSDK_LOG.HttpUtils", "-->openUrl2 before url =" + paramString1);
      if (paramString1.indexOf("?") != -1) {
        break label305;
      }
      paramContext = paramString1 + "?";
      label177:
      com.tencent.open.a.f.a("openSDK_LOG.HttpUtils", "-->openUrl2 encodedParam =" + paramString2 + " -- url = " + paramContext);
      paramContext = new HttpGet(paramContext + paramString2);
      paramContext.addHeader("Accept-Encoding", "gzip");
    }
    for (;;)
    {
      paramContext = ((HttpClient)localObject1).execute(paramContext);
      j = paramContext.getStatusLine().getStatusCode();
      if (j != 200) {
        break label706;
      }
      return new k.a(a(paramContext), i);
      paramBundle = new Bundle();
      break;
      label305:
      paramContext = paramString1 + "&";
      break label177;
      label328:
      i = j;
      if (paramString2.equals("POST"))
      {
        paramContext = new HttpPost(paramString1);
        paramContext.addHeader("Accept-Encoding", "gzip");
        paramString1 = new Bundle();
        Object localObject2 = paramBundle.keySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          Object localObject3 = paramBundle.get(str);
          if ((localObject3 instanceof byte[])) {
            paramString1.putByteArray(str, (byte[])localObject3);
          }
        }
        if (!paramBundle.containsKey("method")) {
          paramBundle.putString("method", paramString2);
        }
        paramContext.setHeader("Content-Type", "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
        paramContext.setHeader("Connection", "Keep-Alive");
        paramString2 = new ByteArrayOutputStream();
        paramString2.write(k.i("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
        paramString2.write(k.i(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f")));
        if (!paramString1.isEmpty())
        {
          int k = paramString1.size();
          i = -1;
          paramString2.write(k.i("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
          paramBundle = paramString1.keySet().iterator();
          while (paramBundle.hasNext())
          {
            localObject2 = (String)paramBundle.next();
            i += 1;
            paramString2.write(k.i("Content-Disposition: form-data; name=\"" + (String)localObject2 + "\"; filename=\"" + (String)localObject2 + "\"" + "\r\n"));
            paramString2.write(k.i("Content-Type: content/unknown\r\n\r\n"));
            localObject2 = paramString1.getByteArray((String)localObject2);
            if (localObject2 != null) {
              paramString2.write((byte[])localObject2);
            }
            if (i < k - 1) {
              paramString2.write(k.i("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n"));
            }
          }
        }
        paramString2.write(k.i("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n"));
        paramString1 = paramString2.toByteArray();
        i = j + paramString1.length;
        paramString2.close();
        paramContext.setEntity(new ByteArrayEntity(paramString1));
      }
    }
    label706:
    throw new HttpStatusException("http status code error:" + j);
  }
  
  public static JSONObject request(QQToken paramQQToken, Context paramContext, String paramString1, Bundle paramBundle, String paramString2)
    throws IOException, JSONException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException
  {
    com.tencent.open.a.f.a("openSDK_LOG.HttpUtils", "OpenApi request");
    String str2 = paramString1;
    String str1 = paramString1;
    if (!paramString1.toLowerCase().startsWith("http"))
    {
      str2 = g.a().a(paramContext, "https://openmobile.qq.com/") + paramString1;
      str1 = g.a().a(paramContext, "https://openmobile.qq.com/") + paramString1;
    }
    a(paramContext, paramQQToken, paramString1);
    paramString1 = null;
    long l2 = SystemClock.elapsedRealtime();
    int k = 0;
    int j = f.a(paramContext, paramQQToken.getAppId()).a("Common_HttpRetryCount");
    com.tencent.open.a.f.a("OpenConfig_test", "config 1:Common_HttpRetryCount            config_value:" + j + "   appid:" + paramQQToken.getAppId() + "     url:" + str1);
    if (j == 0) {
      j = 3;
    }
    for (;;)
    {
      com.tencent.open.a.f.a("OpenConfig_test", "config 1:Common_HttpRetryCount            result_value:" + j + "   appid:" + paramQQToken.getAppId() + "     url:" + str1);
      paramQQToken = paramString1;
      int m = k + 1;
      paramString1 = paramQQToken;
      QQToken localQQToken = paramQQToken;
      try
      {
        k.a locala = openUrl2(paramContext, str2, paramString2, paramBundle);
        paramString1 = paramQQToken;
        localQQToken = paramQQToken;
        paramQQToken = k.d(locala.a);
        paramString1 = paramQQToken;
        localQQToken = paramQQToken;
        try
        {
          i = paramQQToken.getInt("ret");
          paramString1 = paramQQToken;
          localQQToken = paramQQToken;
          l3 = locala.b;
          paramString1 = paramQQToken;
          localQQToken = paramQQToken;
          l4 = locala.c;
          l1 = l2;
          com.tencent.open.b.g.a().a(str1, l1, l3, l4, i);
          return paramQQToken;
        }
        catch (JSONException paramString1)
        {
          for (;;)
          {
            i = -4;
          }
        }
      }
      catch (ConnectTimeoutException paramQQToken)
      {
        for (;;)
        {
          paramQQToken.printStackTrace();
          i = -7;
          if (m >= j) {
            break label386;
          }
          l1 = SystemClock.elapsedRealtime();
          long l4 = 0L;
          long l3 = 0L;
          paramQQToken = paramString1;
          l2 = l1;
          k = m;
          if (m < j) {
            break;
          }
          paramQQToken = paramString1;
        }
        com.tencent.open.b.g.a().a(str1, l2, 0L, 0L, i);
        throw paramQQToken;
      }
      catch (SocketTimeoutException paramQQToken)
      {
        for (;;)
        {
          paramQQToken.printStackTrace();
          i = -8;
          if (m >= j) {
            break;
          }
          long l1 = SystemClock.elapsedRealtime();
          paramString1 = localQQToken;
        }
        com.tencent.open.b.g.a().a(str1, l2, 0L, 0L, i);
        throw paramQQToken;
      }
      catch (HttpStatusException paramQQToken)
      {
        paramQQToken.printStackTrace();
        paramContext = paramQQToken.getMessage();
        try
        {
          i = Integer.parseInt(paramContext.replace("http status code error:", ""));
          com.tencent.open.b.g.a().a(str1, l2, 0L, 0L, i);
          throw paramQQToken;
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            paramContext.printStackTrace();
            i = -9;
          }
        }
      }
      catch (NetworkUnavailableException paramQQToken)
      {
        paramQQToken.printStackTrace();
        throw paramQQToken;
      }
      catch (MalformedURLException paramQQToken)
      {
        paramQQToken.printStackTrace();
        com.tencent.open.b.g.a().a(str1, l2, 0L, 0L, -3);
        throw paramQQToken;
      }
      catch (IOException paramQQToken)
      {
        paramQQToken.printStackTrace();
        int i = getErrorCodeFromException(paramQQToken);
        com.tencent.open.b.g.a().a(str1, l2, 0L, 0L, i);
        throw paramQQToken;
      }
      catch (JSONException paramQQToken)
      {
        label386:
        paramQQToken.printStackTrace();
        com.tencent.open.b.g.a().a(str1, l2, 0L, 0L, -4);
        throw paramQQToken;
      }
    }
  }
  
  public static void requestAsync(QQToken paramQQToken, final Context paramContext, final String paramString1, final Bundle paramBundle, final String paramString2, final IRequestListener paramIRequestListener)
  {
    com.tencent.open.a.f.a("openSDK_LOG.HttpUtils", "OpenApi requestAsync");
    new Thread()
    {
      public void run()
      {
        try
        {
          JSONObject localJSONObject = HttpUtils.request(this.a, paramContext, paramString1, paramBundle, paramString2);
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onComplete(localJSONObject);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi onComplete");
          }
          return;
        }
        catch (MalformedURLException localMalformedURLException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onMalformedURLException(localMalformedURLException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync MalformedURLException", localMalformedURLException);
          }
          return;
        }
        catch (ConnectTimeoutException localConnectTimeoutException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onConnectTimeoutException(localConnectTimeoutException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onConnectTimeoutException", localConnectTimeoutException);
          }
          return;
        }
        catch (SocketTimeoutException localSocketTimeoutException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onSocketTimeoutException(localSocketTimeoutException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onSocketTimeoutException", localSocketTimeoutException);
          }
          return;
        }
        catch (HttpUtils.NetworkUnavailableException localNetworkUnavailableException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onNetworkUnavailableException(localNetworkUnavailableException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onNetworkUnavailableException", localNetworkUnavailableException);
          }
          return;
        }
        catch (HttpUtils.HttpStatusException localHttpStatusException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onHttpStatusException(localHttpStatusException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onHttpStatusException", localHttpStatusException);
          }
          return;
        }
        catch (IOException localIOException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onIOException(localIOException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync IOException", localIOException);
          }
          return;
        }
        catch (JSONException localJSONException)
        {
          if (paramIRequestListener != null)
          {
            paramIRequestListener.onJSONException(localJSONException);
            com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync JSONException", localJSONException);
          }
          return;
        }
        catch (Exception localException)
        {
          while (paramIRequestListener == null) {}
          paramIRequestListener.onUnknowException(localException);
          com.tencent.open.a.f.b("openSDK_LOG.HttpUtils", "OpenApi requestAsync onUnknowException", localException);
        }
      }
    }.start();
  }
  
  public static class HttpStatusException
    extends Exception
  {
    public static final String ERROR_INFO = "http status code error:";
    
    public HttpStatusException(String paramString)
    {
      super();
    }
  }
  
  public static class NetworkUnavailableException
    extends Exception
  {
    public static final String ERROR_INFO = "network unavailable";
    
    public NetworkUnavailableException(String paramString)
    {
      super();
    }
  }
  
  public static class a
  {
    public final String a;
    public final int b;
    
    private a(String paramString, int paramInt)
    {
      this.a = paramString;
      this.b = paramInt;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\tencent\open\utils\HttpUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */