package serilaseble;

import loger.BadLog;
import loger.GoodLog;
import entety.Consumer;

import java.io.*;

/**
 * Consumers serialization in file via Java.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class ConsumerSerializableInByteCode implements SerializationConsumer{
    private static final SerializationConsumer serializable = new ConsumerSerializableInByteCode();

    /**
     * It is a singleton.
     * */
    private ConsumerSerializableInByteCode(){}

    /**
     * Get Instance ConsumerSerializable.
     * */
    public static SerializationConsumer getInstance(){return serializable;}

    /**
     * Serializable from file to path.
     * @param path the pass to file by which you want to resizable into byte code the object.
     * @param consumer object with should serializable in byte code.
     * @return true if resizable was successful, else false.
     */
    @Override
    public boolean serializable( String path, Consumer consumer){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(consumer);
            outputStream.flush();
            GoodLog.getInstance().log("Serialization god.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Serialization bad.");
        return false;
    }

    /**
     * Serializable from file.
     * @param path the pass by which you want to resizable into byte code the object.
     * @param consumer object with should serializable in byte code.
     * @return true if resizable was successful, else false.
     */
    @Override
    public boolean serializable(File path, Consumer consumer){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(consumer);
            outputStream.flush();
            GoodLog.getInstance().log("Serialization god.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BadLog.getInstance().log("Serialization bad.");
        return false;
    }
}
