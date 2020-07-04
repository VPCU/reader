package com.groupt.reader.controller;

import com.groupt.reader.dto.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;

@RestController
public class ResourceController {

    @Value("${user.resource.path}")
    private String filePath;
    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping("/upload")
    public Json update(@RequestPart(value = "file") MultipartFile multipartFile) {

        try {
            // 保存图片
            String fname = multipartFile.getOriginalFilename();
            String exname = getExtensionByStringHandling(fname).get();
            String fn = getRandomAlphabeticString() + "." + exname;
            File file = new File(filePath + fn);
            multipartFile.transferTo(file);
            return Json.succ().data(fn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Json.fail();
    }

    @RequestMapping(value = "/img/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(filePath + filename)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
          .filter(f -> f.contains("."))
          .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public String getRandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
