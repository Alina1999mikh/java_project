package com.github.alina1999mikh;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {

    private Map<UUID, String> hashMap = new HashMap<>();

    private UUID getUuid(String value){
        for (Map.Entry<UUID, String> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private UUID addToMap(String value) { //возвращает uuid с которым добавили
        UUID uuid=getUuid(value);
        if(uuid==null){
            uuid=UUID.randomUUID();
            hashMap.put(uuid, value);
        }
        return uuid;
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public String index(@RequestParam String name) {
        return "You are " + name;
    }

    @RequestMapping(value = "/short", method = RequestMethod.GET)
    public ResponseEntity getFullLink(@RequestParam UUID q) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/full?Q="+hashMap.get(q)));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @RequestMapping(value = "/full", method = RequestMethod.GET)
    public String getShortLink(@RequestParam String Q) {
        UUID uuid=addToMap(Q);
        return "http://localhost:8080/short?q="+uuid.toString();
    }
}