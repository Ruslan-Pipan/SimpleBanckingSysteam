package serilaseble;

import entety.Consumer;

import java.io.File;

/**
 * Provide public interface for deserialization.
 * @author Ruslan Pipan
 * @version 1.0
 * */
public interface DeserializableConsumer {
    /**
     * Deserializable from file to path.
     * @param path path to file in witch the object for deserializable.
     * @return true consumer if describable was successful, else false.
     */
    Consumer deserializable(String path);
    /**
     * Deserializable from file.
     * @param path file in witch the object for deserializable.
     * @return true consumer if describable was successful, else false.
     */
    Consumer deserializable(File path);
}
