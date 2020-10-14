package apksigner.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipInput
{
  CentralEnd centralEnd;
  long fileLength;
  RandomAccessFile in = (RandomAccessFile)null;
  public String inputFilename;
  Manifest manifest;
  int scanIterations = 0;
  Map<String, ZioEntry> zioEntries = new LinkedHashMap();
  
  public ZipInput(File paramFile)
    throws IOException
  {
    this.inputFilename = paramFile.getName();
    this.in = new RandomAccessFile(paramFile, "r");
    this.fileLength = this.in.length();
  }
  
  public ZipInput(String paramString)
    throws IOException
  {
    this.inputFilename = paramString;
    this.in = new RandomAccessFile(new File(this.inputFilename), "r");
    this.fileLength = this.in.length();
  }
  
  private void doRead()
  {
    try
    {
      long l = scanForEOCDR(256);
      this.in.seek(l);
      this.centralEnd = CentralEnd.read(this);
      this.in.seek(this.centralEnd.centralStartOffset);
      int i = 0;
      for (;;)
      {
        if (i >= this.centralEnd.totalCentralEntries) {
          return;
        }
        ZioEntry localZioEntry = ZioEntry.read(this);
        this.zioEntries.put(localZioEntry.getName(), localZioEntry);
        i += 1;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static ZipInput read(File paramFile)
    throws IOException
  {
    paramFile = new ZipInput(paramFile);
    paramFile.doRead();
    return paramFile;
  }
  
  public static ZipInput read(String paramString)
    throws IOException
  {
    paramString = new ZipInput(paramString);
    paramString.doRead();
    return paramString;
  }
  
  public void close()
  {
    if (this.in != null) {}
    try
    {
      this.in.close();
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public Map<String, ZioEntry> getEntries()
  {
    return this.zioEntries;
  }
  
  public ZioEntry getEntry(String paramString)
  {
    return (ZioEntry)this.zioEntries.get(paramString);
  }
  
  public long getFileLength()
  {
    return this.fileLength;
  }
  
  public long getFilePointer()
    throws IOException
  {
    return this.in.getFilePointer();
  }
  
  public String getFilename()
  {
    return this.inputFilename;
  }
  
  public Manifest getManifest()
    throws IOException
  {
    if (this.manifest == null)
    {
      ZioEntry localZioEntry = (ZioEntry)this.zioEntries.get("META-INF/MANIFEST.MF");
      if (localZioEntry != null) {
        this.manifest = new Manifest(localZioEntry.getInputStream());
      }
    }
    return this.manifest;
  }
  
  public Collection<String> list(String paramString)
  {
    if (!paramString.endsWith("/")) {
      throw new IllegalArgumentException("Invalid path -- does not end with '/'");
    }
    Object localObject = paramString;
    if (paramString.startsWith("/")) {
      localObject = paramString.substring(1);
    }
    paramString = Pattern.compile(String.format("^%s([^/]+/?).*", new Object[] { localObject }));
    localObject = new TreeSet();
    Iterator localIterator = ((Collection)this.zioEntries.keySet()).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return (Collection<String>)localObject;
      }
      Matcher localMatcher = paramString.matcher((String)localIterator.next());
      if (localMatcher.matches()) {
        ((Set)localObject).add(localMatcher.group(1));
      }
    }
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return this.in.read(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public byte readByte()
    throws IOException
  {
    return this.in.readByte();
  }
  
  public byte[] readBytes(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return arrayOfByte;
      }
      arrayOfByte[i] = this.in.readByte();
      i += 1;
    }
  }
  
  public int readInt()
    throws IOException
  {
    int i = 0;
    int j = 0;
    for (;;)
    {
      if (i >= 4) {
        return j;
      }
      j |= this.in.readUnsignedByte() << i * 8;
      i += 1;
    }
  }
  
  public short readShort()
    throws IOException
  {
    int k = 0;
    int i = (short)0;
    for (;;)
    {
      if (k >= 2) {
        return i;
      }
      int j = (short)(i | this.in.readUnsignedByte() << k * 8);
      k += 1;
    }
  }
  
  public String readString(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return new String(arrayOfByte);
      }
      arrayOfByte[i] = this.in.readByte();
      i += 1;
    }
  }
  
  public long scanForEOCDR(int paramInt)
    throws IOException
  {
    if ((paramInt > this.fileLength) || (paramInt > 65536)) {
      throw new IllegalStateException("End of central directory not found in " + this.inputFilename);
    }
    int j = (int)Math.min(this.fileLength, paramInt);
    byte[] arrayOfByte = new byte[j];
    this.in.seek(this.fileLength - j);
    this.in.readFully(arrayOfByte);
    int i = j - 22;
    for (;;)
    {
      if (i < 0) {
        return scanForEOCDR(paramInt * 2);
      }
      this.scanIterations += 1;
      if ((arrayOfByte[i] == 80) && (arrayOfByte[(i + 1)] == 75) && (arrayOfByte[(i + 2)] == 5) && (arrayOfByte[(i + 3)] == 6)) {
        return this.fileLength - j + i;
      }
      i -= 1;
    }
  }
  
  public void seek(long paramLong)
    throws IOException
  {
    this.in.seek(paramLong);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\io\ZipInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */