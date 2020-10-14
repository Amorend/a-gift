package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE})
public @interface LongDef
{
  boolean flag() default false;
  
  long[] value() default {};
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\annotation\LongDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */