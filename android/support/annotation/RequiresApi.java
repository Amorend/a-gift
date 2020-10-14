package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.PACKAGE})
public @interface RequiresApi
{
  @IntRange(from=1L)
  int api() default 1;
  
  @IntRange(from=1L)
  int value() default 1;
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\annotation\RequiresApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */