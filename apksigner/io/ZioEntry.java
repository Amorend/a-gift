package apksigner.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.Date;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class ZioEntry
  implements Cloneable
{
  private static byte[] alignBytes = new byte[4];
  private int compressedSize;
  private short compression;
  private int crc32;
  private byte[] data = (byte[])null;
  private long dataPosition = -1;
  private short diskNumberStart;
  private ZioEntryOutputStream entryOut = (ZioEntryOutputStream)null;
  private int externalAttributes;
  private byte[] extraData;
  private String fileComment;
  private String filename;
  private short generalPurposeBits;
  private short internalAttributes;
  private int localHeaderOffset;
  private short modificationDate;
  private short modificationTime;
  private short numAlignBytes = (short)0;
  private int size;
  private short versionMadeBy;
  private short versionRequired;
  private ZipInput zipInput;
  
  public ZioEntry(ZipInput paramZipInput)
  {
    this.zipInput = paramZipInput;
  }
  
  public ZioEntry(String paramString)
  {
    this.filename = paramString;
    this.fileComment = "";
    this.compression = ((short)8);
    this.extraData = new byte[0];
    setTime(System.currentTimeMillis());
  }
  
  public ZioEntry(String paramString1, String paramString2)
    throws IOException
  {
    this.zipInput = new ZipInput(paramString2);
    this.filename = paramString1;
    this.fileComment = "";
    this.compression = ((short)0);
    this.size = ((int)this.zipInput.getFileLength());
    this.compressedSize = this.size;
    paramString1 = new CRC32();
    byte[] arrayOfByte = new byte['ᾠ'];
    int i = 0;
    for (;;)
    {
      if (i == this.size)
      {
        this.crc32 = ((int)paramString1.getValue());
        this.zipInput.seek(0);
        this.dataPosition = 0;
        this.extraData = new byte[0];
        setTime(new File(paramString2).lastModified());
        return;
      }
      int j = this.zipInput.read(arrayOfByte, 0, Math.min(arrayOfByte.length, this.size - i));
      if (j > 0)
      {
        paramString1.update(arrayOfByte, 0, j);
        i += j;
      }
    }
  }
  
  public ZioEntry(String paramString1, String paramString2, short paramShort, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    this.zipInput = new ZipInput(paramString2);
    this.filename = paramString1;
    this.fileComment = "";
    this.compression = paramShort;
    this.crc32 = paramInt1;
    this.compressedSize = paramInt2;
    this.size = paramInt3;
    this.dataPosition = 0;
    this.extraData = new byte[0];
    setTime(new File(paramString2).lastModified());
  }
  
  private void doRead(ZipInput paramZipInput)
    throws IOException
  {
    this.versionMadeBy = paramZipInput.readShort();
    this.versionRequired = paramZipInput.readShort();
    this.generalPurposeBits = paramZipInput.readShort();
    if ((this.generalPurposeBits & 0xF7F1) != 0) {
      throw new IllegalStateException("Can't handle general purpose bits == " + String.format("0x%04x", new Object[] { new Short(this.generalPurposeBits) }));
    }
    this.compression = paramZipInput.readShort();
    this.modificationTime = paramZipInput.readShort();
    this.modificationDate = paramZipInput.readShort();
    this.crc32 = paramZipInput.readInt();
    this.compressedSize = paramZipInput.readInt();
    this.size = paramZipInput.readInt();
    int i = paramZipInput.readShort();
    int j = paramZipInput.readShort();
    int k = paramZipInput.readShort();
    this.diskNumberStart = paramZipInput.readShort();
    this.internalAttributes = paramZipInput.readShort();
    this.externalAttributes = paramZipInput.readInt();
    this.localHeaderOffset = paramZipInput.readInt();
    this.filename = paramZipInput.readString(i);
    this.extraData = paramZipInput.readBytes(j);
    this.fileComment = paramZipInput.readString(k);
    this.generalPurposeBits = ((short)(this.generalPurposeBits & 0x800));
    if (this.size == 0)
    {
      this.compressedSize = 0;
      this.compression = ((short)0);
      this.crc32 = 0;
    }
  }
  
  public static ZioEntry read(ZipInput paramZipInput)
    throws IOException
  {
    if (paramZipInput.readInt() != 33639248)
    {
      paramZipInput.seek(paramZipInput.getFilePointer() - 4);
      return (ZioEntry)null;
    }
    ZioEntry localZioEntry = new ZioEntry(paramZipInput);
    localZioEntry.doRead(paramZipInput);
    return localZioEntry;
  }
  
  public ZioEntry getClonedEntry(String paramString)
  {
    try
    {
      ZioEntry localZioEntry = (ZioEntry)clone();
      localZioEntry.setName(paramString);
      return localZioEntry;
    }
    catch (CloneNotSupportedException paramString)
    {
      throw new IllegalStateException("clone() failed!");
    }
  }
  
  public int getCompressedSize()
  {
    return this.compressedSize;
  }
  
  public short getCompression()
  {
    return this.compression;
  }
  
  public int getCrc32()
  {
    return this.crc32;
  }
  
  public byte[] getData()
    throws IOException
  {
    if (this.data != null) {
      return this.data;
    }
    byte[] arrayOfByte = new byte[this.size];
    InputStream localInputStream = getInputStream();
    int i = 0;
    for (;;)
    {
      if (i == this.size) {
        return arrayOfByte;
      }
      int j = localInputStream.read(arrayOfByte, i, this.size - i);
      if (j < 0) {
        throw new IllegalStateException(String.format("Read failed, expecting %d bytes, got %d instead", new Object[] { new Integer(this.size), new Integer(i) }));
      }
      i += j;
    }
  }
  
  public long getDataPosition()
  {
    return this.dataPosition;
  }
  
  public short getDiskNumberStart()
  {
    return this.diskNumberStart;
  }
  
  public ZioEntryOutputStream getEntryOut()
  {
    return this.entryOut;
  }
  
  public int getExternalAttributes()
  {
    return this.externalAttributes;
  }
  
  public byte[] getExtraData()
  {
    return this.extraData;
  }
  
  public String getFileComment()
  {
    return this.fileComment;
  }
  
  public short getGeneralPurposeBits()
  {
    return this.generalPurposeBits;
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return getInputStream((OutputStream)null);
  }
  
  public InputStream getInputStream(OutputStream paramOutputStream)
    throws IOException
  {
    if (this.entryOut != null)
    {
      this.entryOut.close();
      this.size = this.entryOut.getSize();
      this.data = ((ByteArrayOutputStream)this.entryOut.getWrappedStream()).toByteArray();
      this.compressedSize = this.data.length;
      this.crc32 = this.entryOut.getCRC();
      this.entryOut = ((ZioEntryOutputStream)null);
      paramOutputStream = new ByteArrayInputStream(this.data);
      if (this.compression == 0) {
        return paramOutputStream;
      }
      return new InflaterInputStream(new SequenceInputStream(paramOutputStream, new ByteArrayInputStream(new byte[1])), new Inflater(true));
    }
    ZioEntryInputStream localZioEntryInputStream = new ZioEntryInputStream(this);
    if (paramOutputStream != null) {
      localZioEntryInputStream.setMonitorStream(paramOutputStream);
    }
    if (this.compression != 0)
    {
      localZioEntryInputStream.setReturnDummyByte(true);
      return new InflaterInputStream(localZioEntryInputStream, new Inflater(true));
    }
    return localZioEntryInputStream;
  }
  
  public short getInternalAttributes()
  {
    return this.internalAttributes;
  }
  
  public int getLocalHeaderOffset()
  {
    return this.localHeaderOffset;
  }
  
  public String getName()
  {
    return this.filename;
  }
  
  public OutputStream getOutputStream()
  {
    this.entryOut = new ZioEntryOutputStream(this.compression, new ByteArrayOutputStream());
    return this.entryOut;
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public long getTime()
  {
    return new Date((this.modificationDate >> 9 & 0x7F) + 80, (this.modificationDate >> 5 & 0xF) - 1, this.modificationDate & 0x1F, this.modificationTime >> 11 & 0x1F, this.modificationTime >> 5 & 0x3F, this.modificationTime << 1 & 0x3E).getTime();
  }
  
  public short getVersionMadeBy()
  {
    return this.versionMadeBy;
  }
  
  public short getVersionRequired()
  {
    return this.versionRequired;
  }
  
  public ZipInput getZipInput()
  {
    return this.zipInput;
  }
  
  public boolean isDirectory()
  {
    return this.filename.endsWith("/");
  }
  
  public void readLocalHeader()
    throws IOException
  {
    ZipInput localZipInput = this.zipInput;
    localZipInput.seek(this.localHeaderOffset);
    if (localZipInput.readInt() != 67324752) {
      throw new IllegalStateException(String.format("Local header not found at pos=0x%08x, file=%s", new Object[] { new Long(localZipInput.getFilePointer()), this.filename }));
    }
    localZipInput.readShort();
    localZipInput.readShort();
    localZipInput.readShort();
    localZipInput.readShort();
    localZipInput.readShort();
    localZipInput.readInt();
    localZipInput.readInt();
    localZipInput.readInt();
    int i = localZipInput.readShort();
    int j = localZipInput.readShort();
    localZipInput.readString(i);
    localZipInput.readBytes(j);
    this.dataPosition = localZipInput.getFilePointer();
  }
  
  public void setCompression(int paramInt)
  {
    this.compression = ((short)paramInt);
  }
  
  public void setName(String paramString)
  {
    this.filename = paramString;
  }
  
  public void setTime(long paramLong)
  {
    Date localDate = new Date(paramLong);
    int i = localDate.getYear() + 1900;
    if (i < 1980) {}
    int j;
    int k;
    int m;
    int n;
    for (paramLong = 2162688;; paramLong = localDate.getSeconds() >> 1 | i - 1980 << 25 | j + 1 << 21 | k << 16 | m << 11 | n << 5)
    {
      this.modificationDate = ((short)(int)(paramLong >> 16));
      this.modificationTime = ((short)(int)(paramLong & 65535));
      return;
      j = localDate.getMonth();
      k = localDate.getDate();
      m = localDate.getHours();
      n = localDate.getMinutes();
    }
  }
  
  public void write(ZipOutput paramZipOutput)
    throws IOException
  {
    paramZipOutput.writeInt(33639248);
    paramZipOutput.writeShort(this.versionMadeBy);
    paramZipOutput.writeShort(this.versionRequired);
    paramZipOutput.writeShort(this.generalPurposeBits);
    paramZipOutput.writeShort(this.compression);
    paramZipOutput.writeShort(this.modificationTime);
    paramZipOutput.writeShort(this.modificationDate);
    paramZipOutput.writeInt(this.crc32);
    paramZipOutput.writeInt(this.compressedSize);
    paramZipOutput.writeInt(this.size);
    paramZipOutput.writeShort((short)this.filename.length());
    paramZipOutput.writeShort((short)(this.extraData.length + this.numAlignBytes));
    paramZipOutput.writeShort((short)this.fileComment.length());
    paramZipOutput.writeShort(this.diskNumberStart);
    paramZipOutput.writeShort(this.internalAttributes);
    paramZipOutput.writeInt(this.externalAttributes);
    paramZipOutput.writeInt(this.localHeaderOffset);
    paramZipOutput.writeString(this.filename);
    paramZipOutput.writeBytes(this.extraData);
    if (this.numAlignBytes > 0) {
      paramZipOutput.writeBytes(alignBytes, 0, this.numAlignBytes);
    }
    paramZipOutput.writeString(this.fileComment);
  }
  
  public void writeLocalEntry(ZipOutput paramZipOutput)
    throws IOException
  {
    if ((this.data == null) && (this.dataPosition < 0) && (this.zipInput != null)) {
      readLocalHeader();
    }
    this.localHeaderOffset = paramZipOutput.getFilePointer();
    if (this.entryOut != null)
    {
      this.entryOut.close();
      this.size = this.entryOut.getSize();
      this.data = ((ByteArrayOutputStream)this.entryOut.getWrappedStream()).toByteArray();
      this.compressedSize = this.data.length;
      this.crc32 = this.entryOut.getCRC();
    }
    paramZipOutput.writeInt(67324752);
    paramZipOutput.writeShort(this.versionRequired);
    paramZipOutput.writeShort(this.generalPurposeBits);
    paramZipOutput.writeShort(this.compression);
    paramZipOutput.writeShort(this.modificationTime);
    paramZipOutput.writeShort(this.modificationDate);
    paramZipOutput.writeInt(this.crc32);
    paramZipOutput.writeInt(this.compressedSize);
    paramZipOutput.writeInt(this.size);
    paramZipOutput.writeShort((short)this.filename.length());
    this.numAlignBytes = ((short)0);
    int i;
    if ((this.compression == 0) && (this.size > 0))
    {
      i = (short)(int)((paramZipOutput.getFilePointer() + 2 + this.filename.length() + this.extraData.length) % 4);
      if (i > 0) {
        this.numAlignBytes = ((short)(4 - i));
      }
    }
    paramZipOutput.writeShort((short)(this.extraData.length + this.numAlignBytes));
    paramZipOutput.writeString(this.filename);
    paramZipOutput.writeBytes(this.extraData);
    if (this.numAlignBytes > 0) {
      paramZipOutput.writeBytes(alignBytes, 0, this.numAlignBytes);
    }
    if (this.data != null) {
      paramZipOutput.writeBytes(this.data);
    }
    long l;
    for (;;)
    {
      return;
      this.zipInput.seek(this.dataPosition);
      i = Math.min(this.compressedSize, 8096);
      byte[] arrayOfByte = new byte[i];
      int j;
      for (l = 0; l != this.compressedSize; l += j)
      {
        j = this.zipInput.in.read(arrayOfByte, 0, (int)Math.min(this.compressedSize - l, i));
        if (j <= 0) {
          break label398;
        }
        paramZipOutput.writeBytes(arrayOfByte, 0, j);
      }
    }
    label398:
    throw new IllegalStateException(String.format("EOF reached while copying %s with %d bytes left to go", new Object[] { this.filename, new Long(this.compressedSize - l) }));
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\io\ZioEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */