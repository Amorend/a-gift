package com.luajava;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Console
{
  public static void main(String[] paramArrayOfString)
  {
    for (;;)
    {
      int i;
      try
      {
        LuaState localLuaState = LuaStateFactory.newLuaState();
        localLuaState.openLibs();
        int j;
        Object localObject;
        if (paramArrayOfString.length > 0)
        {
          i = 0;
          if (i >= paramArrayOfString.length) {
            break;
          }
          int k = localLuaState.LloadFile(paramArrayOfString[i]);
          j = k;
          if (k == 0) {
            j = localLuaState.pcall(0, 0, 0);
          }
          if (j != 0)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Error on file: ");
            ((StringBuilder)localObject).append(paramArrayOfString[i]);
            ((StringBuilder)localObject).append(". ");
            ((StringBuilder)localObject).append(localLuaState.toString(-1));
            throw new LuaException(((StringBuilder)localObject).toString());
          }
        }
        else
        {
          System.out.println("API Lua Java - console mode.");
          localObject = new BufferedReader(new InputStreamReader(System.in));
          paramArrayOfString = System.out;
          paramArrayOfString.print("> ");
          paramArrayOfString = ((BufferedReader)localObject).readLine();
          if ((paramArrayOfString != null) && (!paramArrayOfString.equals("exit")))
          {
            j = localLuaState.LloadBuffer(paramArrayOfString.getBytes(), "from console");
            i = j;
            if (j == 0) {
              i = localLuaState.pcall(0, 0, 0);
            }
            if (i != 0)
            {
              PrintStream localPrintStream = System.err;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Error on line: ");
              localStringBuilder.append(paramArrayOfString);
              localPrintStream.println(localStringBuilder.toString());
              System.err.println(localLuaState.toString(-1));
            }
            paramArrayOfString = System.out;
            continue;
          }
          localLuaState.close();
          return;
        }
      }
      catch (Exception paramArrayOfString)
      {
        paramArrayOfString.printStackTrace();
        return;
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\com\luajava\Console.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */