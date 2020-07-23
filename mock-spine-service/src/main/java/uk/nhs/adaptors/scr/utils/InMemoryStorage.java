package uk.nhs.adaptors.scr.utils;

import org.springframework.stereotype.Component;

@Component
public class InMemoryStorage implements ICacheStorage {
    private String data;

    public void saveData(String data) {
        this.data = data;
    }

    public String getData(){
        return this.data;
    }
}
