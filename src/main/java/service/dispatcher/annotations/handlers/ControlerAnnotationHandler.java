package service.dispatcher.annotations.handlers;

import service.dispatcher.annotations.Controler;
import org.reflections.Reflections;

import java.util.*;

/**
 * Handler annotation {@link service.dispatcher.annotations.Controler}
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class ControlerAnnotationHandler {

    private static final Map<Class, Object> cache = new HashMap<>();
    private static final ControlerAnnotationHandler controlerAnnotationHandler = new ControlerAnnotationHandler();
    /**
     * It is a singleton.
     * */
    private ControlerAnnotationHandler(){}

    /**
     * Get instance ControlerAnnotationHandler.
     * */
    public static ControlerAnnotationHandler getInstance(){return controlerAnnotationHandler;}
    /**
     * Filing map and create singleton classes which market annotation {@link service.dispatcher.annotations.Controler}
     * @param map filling map value with annotation {@link service.dispatcher.annotations.Controler}
     * @return filling map.
     * */
    public Map<Class,String> execute(Map<Class,String> map){

        Reflections reflections = new Reflections("");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controler.class);

        for (Class c: annotated) {
            Controler controler = (Controler) c.getDeclaredAnnotation(Controler.class);
            map.put(c,controler.value());
            try {

                if (!cache.containsKey(c))
                    cache.put(c,c.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * @return map with singleton classes.
     * */
    protected Map<Class, Object> getCache() {
        return cache;
    }

}
