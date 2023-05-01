package ru.ezhov.umnei.mathematics.imagegenerator;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@SessionScope
public class SessionImageStore {
    private Map<String, byte[]> images = new ConcurrentHashMap<>();

    public void put(String guid, byte[] bytes) {
        images.put(guid, bytes);
    }

    public Optional<byte[]> get(String guid) {
        return Optional.ofNullable(images.get(guid));
    }

    public void delete(String guid) {
        images.remove(guid);
    }
}
