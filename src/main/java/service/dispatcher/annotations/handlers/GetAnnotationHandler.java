package service.dispatcher.annotations.handlers;

import service.dispatcher.annotations.Get;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class GetAnnotationHandler {

    public Map<Method,String> execute(Map<Class,String> map){

        HashMap<Method, String> path = new HashMap<>();

        for (Map.Entry<Class, String> entry : map.entrySet()){
            Method[] methods = entry.getKey().getDeclaredMethods();
            for (Method m:methods) {
                Get annotation = m.getAnnotation(Get.class);
                    if (annotation != null)
                        path.put(m,entry.getValue().concat(annotation.value()));
            }
        }
        return path;
    }
}
