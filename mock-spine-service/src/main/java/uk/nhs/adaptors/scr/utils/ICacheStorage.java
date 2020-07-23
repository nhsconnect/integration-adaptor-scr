package uk.nhs.adaptors.scr.utils;

public interface ICacheStorage {
    void saveData(String data);
    String getData();
}
