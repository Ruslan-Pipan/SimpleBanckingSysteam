package serilaseble;

import entety.Consumer;

import java.io.File;

/**
 * Provide public interface for serialisation.
 * @author Ruslan Pipan
 * @version 1.0
 * */
public interface SerializationConsumer {
    /**
     * Serializable from file to path.
     * @param path the pass to file by which you want to resizable into byte code the object.
     * @param consumer object with should serializable in byte code.
     * @return true if resizable was successful, else false.
     */
    boolean serializable(String path, Consumer consumer);
    /**
     * Serializable from file.
     * @param path the pass by which you want to resizable into byte code the object.
     * @param consumer object with should serializable in byte code.
     * @return true if resizable was successful, else false.
     */
    boolean serializable(File path, Consumer consumer);
}
