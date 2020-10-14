package android.support.transition;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TargetApi(19)
@RequiresApi(19)
class SceneKitKat
  extends SceneWrapper
{
  private static Field sEnterAction;
  private static Method sSetCurrentScene;
  private View mLayout;
  
  /* Error */
  private void invokeEnterAction()
  {
    // Byte code:
    //   0: getstatic 26	android/support/transition/SceneKitKat:sEnterAction	Ljava/lang/reflect/Field;
    //   3: ifnonnull +20 -> 23
    //   6: ldc 28
    //   8: ldc 30
    //   10: invokevirtual 36	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   13: putstatic 26	android/support/transition/SceneKitKat:sEnterAction	Ljava/lang/reflect/Field;
    //   16: getstatic 26	android/support/transition/SceneKitKat:sEnterAction	Ljava/lang/reflect/Field;
    //   19: iconst_1
    //   20: invokevirtual 42	java/lang/reflect/Field:setAccessible	(Z)V
    //   23: getstatic 26	android/support/transition/SceneKitKat:sEnterAction	Ljava/lang/reflect/Field;
    //   26: aload_0
    //   27: getfield 46	android/support/transition/SceneKitKat:mScene	Landroid/transition/Scene;
    //   30: invokevirtual 50	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   33: checkcast 52	java/lang/Runnable
    //   36: astore_1
    //   37: aload_1
    //   38: ifnull +9 -> 47
    //   41: aload_1
    //   42: invokeinterface 55 1 0
    //   47: return
    //   48: astore_1
    //   49: new 57	java/lang/RuntimeException
    //   52: dup
    //   53: aload_1
    //   54: invokespecial 60	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   57: athrow
    //   58: astore_1
    //   59: new 57	java/lang/RuntimeException
    //   62: dup
    //   63: aload_1
    //   64: invokespecial 60	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	this	SceneKitKat
    //   36	6	1	localRunnable	Runnable
    //   48	6	1	localNoSuchFieldException	NoSuchFieldException
    //   58	6	1	localIllegalAccessException	IllegalAccessException
    // Exception table:
    //   from	to	target	type
    //   6	23	48	java/lang/NoSuchFieldException
    //   23	37	58	java/lang/IllegalAccessException
    //   41	47	58	java/lang/IllegalAccessException
  }
  
  /* Error */
  private void updateCurrentScene(View paramView)
  {
    // Byte code:
    //   0: getstatic 68	android/support/transition/SceneKitKat:sSetCurrentScene	Ljava/lang/reflect/Method;
    //   3: ifnonnull +34 -> 37
    //   6: ldc 28
    //   8: ldc 70
    //   10: iconst_2
    //   11: anewarray 32	java/lang/Class
    //   14: dup
    //   15: iconst_0
    //   16: ldc 72
    //   18: aastore
    //   19: dup
    //   20: iconst_1
    //   21: ldc 28
    //   23: aastore
    //   24: invokevirtual 76	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   27: putstatic 68	android/support/transition/SceneKitKat:sSetCurrentScene	Ljava/lang/reflect/Method;
    //   30: getstatic 68	android/support/transition/SceneKitKat:sSetCurrentScene	Ljava/lang/reflect/Method;
    //   33: iconst_1
    //   34: invokevirtual 79	java/lang/reflect/Method:setAccessible	(Z)V
    //   37: getstatic 68	android/support/transition/SceneKitKat:sSetCurrentScene	Ljava/lang/reflect/Method;
    //   40: aconst_null
    //   41: iconst_2
    //   42: anewarray 81	java/lang/Object
    //   45: dup
    //   46: iconst_0
    //   47: aload_1
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: aload_0
    //   52: getfield 46	android/support/transition/SceneKitKat:mScene	Landroid/transition/Scene;
    //   55: aastore
    //   56: invokevirtual 85	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   59: pop
    //   60: return
    //   61: astore_1
    //   62: new 57	java/lang/RuntimeException
    //   65: dup
    //   66: aload_1
    //   67: invokespecial 60	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   70: athrow
    //   71: astore_1
    //   72: new 57	java/lang/RuntimeException
    //   75: dup
    //   76: aload_1
    //   77: invokespecial 60	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   80: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	this	SceneKitKat
    //   0	81	1	paramView	View
    // Exception table:
    //   from	to	target	type
    //   6	37	61	java/lang/NoSuchMethodException
    //   37	60	71	java/lang/IllegalAccessException
    //   37	60	71	java/lang/reflect/InvocationTargetException
  }
  
  public void enter()
  {
    if (this.mLayout != null)
    {
      ViewGroup localViewGroup = getSceneRoot();
      localViewGroup.removeAllViews();
      localViewGroup.addView(this.mLayout);
      invokeEnterAction();
      updateCurrentScene(localViewGroup);
      return;
    }
    this.mScene.enter();
  }
  
  public void init(ViewGroup paramViewGroup)
  {
    this.mScene = new Scene(paramViewGroup);
  }
  
  public void init(ViewGroup paramViewGroup, View paramView)
  {
    if ((paramView instanceof ViewGroup))
    {
      this.mScene = new Scene(paramViewGroup, (ViewGroup)paramView);
      return;
    }
    this.mScene = new Scene(paramViewGroup);
    this.mLayout = paramView;
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\transition\SceneKitKat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */