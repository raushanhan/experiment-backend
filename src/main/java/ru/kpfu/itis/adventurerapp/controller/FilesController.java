package ru.kpfu.itis.adventurerapp.controller;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.adventurerapp.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FilesController {


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal User user) {
        log.info("LOG - entered files controller to upload file {}", file.getOriginalFilename());
        try {
            // Сохраняем картинку

            Path path = Paths.get("uploads/" + user.getId() + "/" + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            log.info("LOG - successfully uploaded file {}", file.getOriginalFilename());
            return ResponseEntity.ok("Файл загружен: " + path.toString());
        } catch (IOException e) {
            log.info("LOG - uploading error for file {}", file.getOriginalFilename());
            log.error("LOG - uploading error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка загрузки: " + e.getMessage());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("path") String pathStr) {
        try {
            log.info("LOG - downloading file {}", pathStr);
            Path path = Paths.get(pathStr);
            if (!Files.exists(path)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            byte[] data = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(data);

            String fileName = path.getFileName().toString();
            String mimeType = Files.probeContentType(path);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .contentType(MediaType.parseMediaType(mimeType))
                    .contentLength(data.length)
                    .body(resource);
        } catch (IOException e) {
            log.error("LOG - download error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
