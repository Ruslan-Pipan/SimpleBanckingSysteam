package service.dispatcher.annotations.handlers;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DispatcherServletHandler {

    private final ControlerAnnotationHandler controlerAnnotationHandler;
    private final GetAnnotationHandler getAnnotationHandler;
    private final PostAnnotationHandler postAnnotationHandler;


    private final HttpServletRequest req;

    private final Method method;
    private final String url;

    public DispatcherServletHandler(HttpServletRequest req) {
        this.req = req;
        this.url = req.getRequestURI();
        this.controlerAnnotationHandler = new ControlerAnnotationHandler();
        this.getAnnotationHandler = new GetAnnotationHandler();
        this.postAnnotationHandler = new PostAnnotationHandler();
        this.method = getMethod(getMapMethodsAndUrl(),url);
    }

    public String invokeMethod(){
        String path = url;
        if (method!=null) {
            try {
                method.setAccessible(true);
                path = (String) method.invoke(controlerAnnotationHandler.getCache().get(method.getDeclaringClass()),req);
                return path;
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return path;
    }

    private Method getMethod(Map<Method,String> map, String url){
        Set<Map.Entry<Method, String>> entrySet = map.entrySet();

        for (Map.Entry<Method,String> entry: entrySet)
            if (url.equals(entry.getValue()))
                return entry.getKey();
        return null;
    }

    private Map<Method,String> getMapMethodsAndUrl(){
         Map<Class,String> mapClass = new HashMap<>();
         Map<Method,String> methodStringMap = new HashMap<>();
         controlerAnnotationHandler.execute(mapClass);
         methodStringMap.putAll(getAnnotationHandler.execute(mapClass));
         methodStringMap.putAll(postAnnotationHandler.execute(mapClass));
         return methodStringMap;
    }


}
