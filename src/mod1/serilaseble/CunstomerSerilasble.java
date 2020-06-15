package mod1.serilaseble;

import com.sun.istack.internal.NotNull;
import mod1.com.mybanck.domain.Cunsumers;

import java.io.*;

public class CunstomerSerilasble {

    private CunstomerSerilasble(){}

    public static boolean serialesbleCunstomer(@NotNull String path, Cunsumers cunstomer){
        File file = new File(path);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            if (cunstomer != null){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
