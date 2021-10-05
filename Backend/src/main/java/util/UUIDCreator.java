package util;

import java.util.UUID;

public class UUIDCreator {
    public String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
