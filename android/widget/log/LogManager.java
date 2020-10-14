package android.widget.log;

public final class LogManager
{
  private static Logger logger = new LoggerDefault();
  
  public static Logger getLogger()
  {
    return logger;
  }
  
  public static void setLogger(Logger paramLogger)
  {
    logger = paramLogger;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\widget\log\LogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */