package apksigner.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ZipOutput
{
  List<ZioEntry> entriesWritten = new LinkedList();
  int filePointer = 0;
  Set<String> namesWritten = new HashSet();
  OutputStream out = (OutputStream)null;
  String outputFilename;
  
  public ZipOutput(File paramFile)
    throws IOException
  {
    this.outputFilename = paramFile.getAbsolutePath();
    init(paramFile);
  }
  
  public ZipOutput(OutputStream paramOutputStream)
    throws IOException
  {
    this.out = paramOutputStream;
  }
  
  public ZipOutput(String paramString)
    throws IOException
  {
    this.outputFilename = paramString;
    init(new File(this.outputFilename));
  }
  
  private void init(File paramFile)
    throws IOException
  {
    if (paramFile.exists()) {
      paramFile.delete();
    }
    this.out = new FileOutputStream(paramFile);
  }
  
  public void close()
    throws IOException
  {
    CentralEnd localCentralEnd = new CentralEnd();
    localCentralEnd.centralStartOffset = getFilePointer();
    short s = (short)this.entriesWritten.size();
    localCentralEnd.totalCentralEntries = s;
    localCentralEnd.numCentralEntries = s;
    Iterator localIterator = ((Collection)this.entriesWritten).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        localCentralEnd.centralDirectorySize = (getFilePointer() - localCentralEnd.centralStartOffset);
        localCentralEnd.fileComment = "";
        localCentralEnd.write(this);
        if (this.out == null) {}
      }
      try
      {
        this.out.close();
        return;
      }
      catch (Throwable localThrowable) {}
      ((ZioEntry)localIterator.next()).write(this);
    }
  }
  
  public int getFilePointer()
    throws IOException
  {
    return this.filePointer;
  }
  
  public void write(ZioEntry paramZioEntry)
    throws IOException
  {
    String str = paramZioEntry.getName();
    if (this.namesWritten.contains(str)) {
      return;
    }
    paramZioEntry.writeLocalEntry(this);
    this.entriesWritten.add(paramZioEntry);
    this.namesWritten.add(str);
  }
  
  public void writeBytes(byte[] paramArrayOfByte)
    throws IOException
  {
    this.out.write(paramArrayOfByte);
    this.filePointer += paramArrayOfByte.length;
  }
  
  public void writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfByte, paramInt1, paramInt2);
    this.filePointer += paramInt2;
  }
  
  public void writeInt(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4];
    int j = 0;
    int i = paramInt;
    paramInt = j;
    for (;;)
    {
      if (paramInt >= 4)
      {
        this.out.write(arrayOfByte);
        this.filePointer += 4;
        return;
      }
      arrayOfByte[paramInt] = ((byte)(i & 0xFF));
      i >>= 8;
      paramInt += 1;
    }
  }
  
  public void writeShort(short paramShort)
    throws IOException
  {
    byte[] arrayOfByte = new byte[2];
    short s = 0;
    int i = paramShort;
    paramShort = s;
    for (;;)
    {
      if (paramShort >= 2)
      {
        this.out.write(arrayOfByte);
        this.filePointer += 2;
        return;
      }
      arrayOfByte[paramShort] = ((byte)(i & 0xFF));
      i = (short)(i >> 8);
      paramShort += 1;
    }
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    paramString = paramString.getBytes();
    this.out.write(paramString);
    int i = this.filePointer;
    this.filePointer = (paramString.length + i);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\io\ZipOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */