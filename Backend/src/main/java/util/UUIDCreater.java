package util;

import java.util.UUID;

public class UUIDCreater {
    public String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        System.out.println("Your UUID is: " + uuidAsString);
        return uuidAsString;
    }
}
