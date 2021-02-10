package service.dispatcher.annotations.handlers;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Handler Dispatcher Servlet {@link service.dispatcher.DispatcherServlet}.
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class DispatcherServletHandler {

    private final ControlerAnnotationHandler controlerAnnotationHandler;
    private final GetAnnotationHandler getAnnotationHandler;
    private final PostAnnotationHandler postAnnotationHandler;
    private final HttpServletRequest req;
    private final Method method;
    private final String url;

    /**
     * Create DispatcherServletHandler.
     * @param req for filling req, and url.
     * */
    public DispatcherServletHandler(HttpServletRequest req) {
        this.req = req;
        this.url = req.getRequestURI();
        this.controlerAnnotationHandler = ControlerAnnotationHandler.getInstance();
        this.getAnnotationHandler = GetAnnotationHandler.getInstance();
        this.postAnnotationHandler = PostAnnotationHandler.getInstance();
        this.method = getMethod(getMapMethodsAndUrl(),url);
    }

    /**
     * Performs methods Controler which annotated {@link service.dispatcher.annotations.Post} or {@link service.dispatcher.annotations.Get}
     * @return string which have url to go to another page.
     **/
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

    /**
     * Get specified method by URL.
     * @param map take those filing methods and their URL.
     * @param url url sent by user.
     * @return Methods which is responsible for processing this URL.
     * */
    private Method getMethod(Map<Method,String> map, String url){
        Set<Map.Entry<Method, String>> entrySet = map.entrySet();

        for (Map.Entry<Method,String> entry: entrySet)
            if (url.equals(entry.getValue()))
                return entry.getKey();
        return null;
    }

    /**
     * @return map which has methods that need to performs, and URL.
     * */
    private Map<Method,String> getMapMethodsAndUrl(){
         Map<Class,String> mapClass = new HashMap<>();
         Map<Method,String> methodStringMap = new HashMap<>();
         controlerAnnotationHandler.execute(mapClass);
         methodStringMap.putAll(getAnnotationHandler.execute(mapClass));
         methodStringMap.putAll(postAnnotationHandler.execute(mapClass));
         return methodStringMap;
    }


}
