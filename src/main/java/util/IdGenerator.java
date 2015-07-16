package util;

import java.util.UUID;

/**
 * Created by hadi on 7/10/15.
 */
public class IdGenerator {

    public static String generateID(){
        return UUID.randomUUID().toString();
    }
}
