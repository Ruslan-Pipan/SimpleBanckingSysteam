package service.dispatcher.annotations.handlers;

import service.dispatcher.annotations.Post;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * Handler annotation {@link service.dispatcher.annotations.Post}.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class PostAnnotationHandler {
    private static final PostAnnotationHandler postAnnotationHandler = new PostAnnotationHandler();
    /**
     * It is a singleton.
     * */
    private PostAnnotationHandler(){}

    /**
     * Get instance PostAnnotationHandler.
     * */
    public static PostAnnotationHandler getInstance(){return postAnnotationHandler;}

    /**
     * @param map filling map values {@link service.dispatcher.annotations.Post}.
     * @return filling map.
     * */
    public Map<Method,String> execute(Map<Class,String> map){

        HashMap<Method, String> path = new HashMap<>();

        for (Map.Entry<Class, String> entry : map.entrySet()){
            Method[] methods = entry.getKey().getDeclaredMethods();
            for (Method m:methods) {
                Post annotation = m.getAnnotation(Post.class);
                if (annotation != null)
                    path.put(m,entry.getValue().concat(annotation.value()));
            }
        }
        return path;
    }
}
