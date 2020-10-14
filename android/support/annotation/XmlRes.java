package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.LOCAL_VARIABLE})
public @interface XmlRes {}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\annotation\XmlRes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */