package avakhidov.springpatterns.agreement.config;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.type.MethodMetadata;

import java.lang.reflect.Field;




public class BeanDefinitionUtils {


    public static String creatClassNameForJavaConfig(AbstractBeanDefinition beanDefinition) {
        try {
            Object reader =
                    Class.forName("org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader$ConfigurationClassBeanDefinition")
                    .cast(beanDefinition);
            try {
                Field field = reader.getClass().getDeclaredField("factoryMethodMetadata");
                field.setAccessible(true);
                try {
                    MethodMetadata visitor = (MethodMetadata) field.get(reader);
                    return visitor.getReturnTypeName();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
