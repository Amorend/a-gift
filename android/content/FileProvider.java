package android.content;

import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

public class FileProvider
  extends ContentProvider
{
  private static final String[] a = { "_display_name", "_size" };
  private static final File b = new File("/");
  private static HashMap<String, a> c = new HashMap();
  private a d;
  
  private static int a(String paramString)
  {
    if ("r".equals(paramString)) {
      return 268435456;
    }
    if ((!"w".equals(paramString)) && (!"wt".equals(paramString)))
    {
      if ("wa".equals(paramString)) {
        return 704643072;
      }
      if ("rw".equals(paramString)) {
        return 939524096;
      }
      if ("rwt".equals(paramString)) {
        return 1006632960;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid mode: ");
      localStringBuilder.append(paramString);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return 738197504;
  }
  
  private static a a(Context paramContext, String paramString)
  {
    synchronized (c)
    {
      a locala2 = (a)c.get(paramString);
      a locala1 = locala2;
      if (locala2 == null) {
        try
        {
          locala1 = b(paramContext, paramString);
          c.put(paramString, locala1);
        }
        catch (XmlPullParserException paramContext)
        {
          throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", paramContext);
        }
        catch (IOException paramContext)
        {
          throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", paramContext);
        }
      }
      return locala1;
    }
  }
  
  public static Uri a(Context paramContext, String paramString, File paramFile)
  {
    return a(paramContext, paramString).a(paramFile);
  }
  
  private static File a(File paramFile, String... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      String str = paramVarArgs[i];
      File localFile = paramFile;
      if (str != null) {
        localFile = new File(paramFile, str);
      }
      i += 1;
      paramFile = localFile;
    }
    return paramFile;
  }
  
  private static Object[] a(Object[] paramArrayOfObject, int paramInt)
  {
    Object[] arrayOfObject = new Object[paramInt];
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, paramInt);
    return arrayOfObject;
  }
  
  private static String[] a(String[] paramArrayOfString, int paramInt)
  {
    String[] arrayOfString = new String[paramInt];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramInt);
    return arrayOfString;
  }
  
  private static a b(Context paramContext, String paramString)
  {
    b localb = new b(paramString);
    XmlResourceParser localXmlResourceParser = paramContext.getPackageManager().resolveContentProvider(paramString, 128).loadXmlMetaData(paramContext.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
    if (localXmlResourceParser == null) {
      throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
    }
    for (;;)
    {
      int i = localXmlResourceParser.next();
      if (i == 1) {
        break;
      }
      if (i == 2)
      {
        Object localObject1 = localXmlResourceParser.getName();
        Object localObject2 = null;
        String str1 = localXmlResourceParser.getAttributeValue(null, "name");
        String str2 = localXmlResourceParser.getAttributeValue(null, "path");
        if ("root-path".equals(localObject1))
        {
          paramString = b;
        }
        else if ("files-path".equals(localObject1))
        {
          paramString = paramContext.getFilesDir();
        }
        else if ("cache-path".equals(localObject1))
        {
          paramString = paramContext.getCacheDir();
        }
        else if ("external-path".equals(localObject1))
        {
          paramString = Environment.getExternalStorageDirectory();
        }
        else if ("external-files-path".equals(localObject1))
        {
          localObject1 = new File[0];
          if (Build.VERSION.SDK_INT >= 19) {
            localObject1 = paramContext.getExternalFilesDirs(null);
          }
          paramString = (String)localObject2;
          if (localObject1.length > 0) {
            paramString = localObject1[0];
          }
        }
        else
        {
          paramString = (String)localObject2;
          if ("external-cache-path".equals(localObject1))
          {
            localObject1 = new File[0];
            if (Build.VERSION.SDK_INT >= 19) {
              localObject1 = paramContext.getExternalCacheDirs();
            }
            paramString = (String)localObject2;
            if (localObject1.length > 0) {
              paramString = localObject1[0];
            }
          }
        }
        if (paramString != null) {
          localb.a(str1, a(paramString, new String[] { str2 }));
        }
      }
    }
    return localb;
  }
  
  public void attachInfo(Context paramContext, ProviderInfo paramProviderInfo)
  {
    super.attachInfo(paramContext, paramProviderInfo);
    if (paramProviderInfo.exported) {
      throw new SecurityException("Provider must not be exported");
    }
    if (!paramProviderInfo.grantUriPermissions) {
      throw new SecurityException("Provider must grant uri permissions");
    }
    this.d = a(paramContext, paramProviderInfo.authority);
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String getType(Uri paramUri)
  {
    paramUri = this.d.a(paramUri);
    int i = paramUri.getName().lastIndexOf('.');
    if (i >= 0)
    {
      paramUri = paramUri.getName().substring(i + 1);
      paramUri = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramUri);
      if (paramUri != null) {
        return paramUri;
      }
    }
    return "application/octet-stream";
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    throw new UnsupportedOperationException("No external inserts");
  }
  
  public boolean onCreate()
  {
    return true;
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
  {
    return ParcelFileDescriptor.open(this.d.a(paramUri), a(paramString));
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    paramString1 = this.d.a(paramUri);
    paramUri = paramArrayOfString1;
    if (paramArrayOfString1 == null) {
      paramUri = a;
    }
    paramArrayOfString2 = new String[paramUri.length];
    paramArrayOfString1 = new Object[paramUri.length];
    int m = paramUri.length;
    int j = 0;
    int k;
    for (int i = 0; j < m; i = k)
    {
      paramString2 = paramUri[j];
      if ("_display_name".equals(paramString2))
      {
        paramArrayOfString2[i] = "_display_name";
        k = i + 1;
        paramArrayOfString1[i] = paramString1.getName();
      }
      for (i = k;; i = k)
      {
        k = i;
        break;
        k = i;
        if (!"_size".equals(paramString2)) {
          break;
        }
        paramArrayOfString2[i] = "_size";
        k = i + 1;
        paramArrayOfString1[i] = Long.valueOf(paramString1.length());
      }
      j += 1;
    }
    paramUri = a(paramArrayOfString2, i);
    paramArrayOfString1 = a(paramArrayOfString1, i);
    paramUri = new MatrixCursor(paramUri, 1);
    paramUri.addRow(paramArrayOfString1);
    return paramUri;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    throw new UnsupportedOperationException("No external updates");
  }
  
  static abstract interface a
  {
    public abstract Uri a(File paramFile);
    
    public abstract File a(Uri paramUri);
  }
  
  static class b
    implements FileProvider.a
  {
    private final String a;
    private final HashMap<String, File> b = new HashMap();
    
    public b(String paramString)
    {
      this.a = paramString;
    }
    
    public Uri a(File paramFile)
    {
      try
      {
        Object localObject2 = paramFile.getCanonicalPath();
        paramFile = null;
        Iterator localIterator = this.b.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localObject1 = (Map.Entry)localIterator.next();
          String str = ((File)((Map.Entry)localObject1).getValue()).getPath();
          if ((((String)localObject2).startsWith(str)) && ((paramFile == null) || (str.length() > ((File)paramFile.getValue()).getPath().length()))) {
            paramFile = (File)localObject1;
          }
        }
        if (paramFile == null)
        {
          paramFile = new StringBuilder();
          paramFile.append("Failed to find configured root that contains ");
          paramFile.append((String)localObject2);
          throw new IllegalArgumentException(paramFile.toString());
        }
        localObject1 = ((File)paramFile.getValue()).getPath();
        if (((String)localObject1).endsWith("/")) {}
        for (int i = ((String)localObject1).length();; i = ((String)localObject1).length() + 1)
        {
          localObject1 = ((String)localObject2).substring(i);
          break;
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(Uri.encode((String)paramFile.getKey()));
        ((StringBuilder)localObject2).append('/');
        ((StringBuilder)localObject2).append(Uri.encode((String)localObject1, "/"));
        paramFile = ((StringBuilder)localObject2).toString();
        return new Uri.Builder().scheme("content").authority(this.a).encodedPath(paramFile).build();
      }
      catch (IOException localIOException)
      {
        Object localObject1;
        for (;;) {}
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Failed to resolve canonical path for ");
      ((StringBuilder)localObject1).append(paramFile);
      throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
    }
    
    public File a(Uri paramUri)
    {
      Object localObject2 = paramUri.getEncodedPath();
      int i = ((String)localObject2).indexOf('/', 1);
      Object localObject1 = Uri.decode(((String)localObject2).substring(1, i));
      localObject2 = Uri.decode(((String)localObject2).substring(i + 1));
      localObject1 = (File)this.b.get(localObject1);
      if (localObject1 == null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Unable to find configured root for ");
        ((StringBuilder)localObject1).append(paramUri);
        throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
      }
      paramUri = new File((File)localObject1, (String)localObject2);
      try
      {
        localObject2 = paramUri.getCanonicalFile();
        if (!((File)localObject2).getPath().startsWith(((File)localObject1).getPath())) {
          throw new SecurityException("Resolved path jumped beyond configured root");
        }
        return (File)localObject2;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Failed to resolve canonical path for ");
      ((StringBuilder)localObject1).append(paramUri);
      throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
    }
    
    public void a(String paramString, File paramFile)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("Name must not be empty");
      }
      try
      {
        localObject = paramFile.getCanonicalFile();
        this.b.put(paramString, localObject);
        return;
      }
      catch (IOException paramString)
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Failed to resolve canonical path for ");
        ((StringBuilder)localObject).append(paramFile);
        throw new IllegalArgumentException(((StringBuilder)localObject).toString(), paramString);
      }
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\content\FileProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */