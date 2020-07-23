package uk.nhs.adaptors.scr.controllers;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.nhs.adaptors.scr.utils.ICacheStorage;

@RestController
@AllArgsConstructor
@Slf4j
public class SetupController {
    @Autowired
    private final ICacheStorage cacheStorage;

    @PostMapping(value = "/setup", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(ACCEPTED)
    public void postSetup(@RequestBody String input) {
        cacheStorage.saveData(input);
    }
}
