package com.arsenio.filestreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@SpringBootApplication
public class FileStreamingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStreamingApplication.class, args);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() throws IOException {
        final File file = new File("src/main/resources/files/test.zip");
        final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
