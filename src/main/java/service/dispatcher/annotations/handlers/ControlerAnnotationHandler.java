package service.dispatcher.annotations.handlers;

import service.dispatcher.annotations.Controler;
import org.reflections.Reflections;

import java.util.*;


public class ControlerAnnotationHandler {


    private static final Map<Class, Object> cache = new HashMap<>();

    public Map<Class,String> execute(Map<Class,String> map){

        Reflections reflections = new Reflections("");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controler.class);

        for (Class c: annotated) {
            Controler controler = (Controler) c.getDeclaredAnnotation(Controler.class);
            map.put(c,controler.value());
            try {

                if (!cache.containsKey(c))
                    cache.put(c,c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    protected Map<Class, Object> getCache() {
        return cache;
    }

}
