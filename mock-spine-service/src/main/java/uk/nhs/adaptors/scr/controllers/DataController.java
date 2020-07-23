package uk.nhs.adaptors.scr.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.nhs.adaptors.scr.utils.ICacheStorage;

@RestController
@AllArgsConstructor
@Slf4j
public class DataController {
    @Autowired
    private final ICacheStorage cacheStorage;

    @GetMapping(value = "/data", produces = APPLICATION_JSON_VALUE)
    public String get() {
        return cacheStorage.getData();
    }
}
