package serilaseble;


import loger.BadLog;
import loger.GoodLog;
import entety.Consumer;

import java.io.*;

/**
 * Consumers deserializable in file via Java.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class ConsumerDeserializableInByteCode implements DeserializableConsumer{
    private static final DeserializableConsumer deserializable = new ConsumerDeserializableInByteCode();
    /**
     * It is singleton.
     * */
    private ConsumerDeserializableInByteCode(){}
    /**
     * Get instance.
     * */
    public static DeserializableConsumer getIntense(){return deserializable;}

    /**
     * Deserializable from file to path.
     * @param path path to file in witch the object for deserializable.
     * @return true consumer if describable was successful, else false.
     */
    @Override
    public Consumer deserializable(String path){
        File file = new File(path);
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            GoodLog.getInstance().log("Deserialization god.");
            return(Consumer) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Deserialization bad.");
        return null;
    }

    /**
     * Deserializable from file.
     * @param path file in witch the object for deserializable.
     * @return true consumer if describable was successful, else false.
     */
    @Override
    public Consumer deserializable(File path){
        try (ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(path))){
            GoodLog.getInstance().log("Deserialization god.");
            return(Consumer) outputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Deserialization bad.");
        return null;
    }
}
