package apksigner;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class abc
{
  public abc()
    throws NoSuchAlgorithmException, IOException, CertificateException, KeyStoreException
  {
    FileInputStream localFileInputStream = new FileInputStream(".keystore");
    KeyStore localKeyStore = KeyStore.getInstance("JKS");
    localKeyStore.load(localFileInputStream, "123456".toCharArray());
    localKeyStore.getCertificate((String)null);
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\apksigner\abc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */