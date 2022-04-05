package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.book.file.BookFileEntity;
import com.example.MyBookShopApp.data.enums.BookFileType;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {

    @Value("${upload.path}")
    public String uploadPath;

    @Value("${download.path}")
    String downloadPath;

    BookFileRepository bookFileRepository;

    @Autowired
    public ResourceStorage(BookFileRepository bookFileRepository) {
        this.bookFileRepository = bookFileRepository;
    }

    public String saveNewBookImage(MultipartFile file, Integer id) throws IOException {
        String resourceURI = null;

        Logger.getLogger(this.getClass().getSimpleName()).info("upload.path: " + uploadPath);

        // Проверка существования папки. Если папки нет, она будет создана.
        if (!new File(uploadPath).exists()){
            Files.createDirectories(Paths.get(uploadPath));
            Logger.getLogger(this.getClass().getSimpleName()).info("Создаем папку для хранения данных книжного магазина: " + uploadPath);
        }

        // Формирование имени файла
        String fileName = "book" + String.format("%06d", id) + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path path = Paths.get(uploadPath, fileName);
        resourceURI = "/Images/" + fileName;

        // Копирование файла на сервер
        file.transferTo(path);
        Logger.getLogger(this.getClass().getSimpleName()).info("Файл " + fileName + " был успешно скопирован");

        return resourceURI;
    }

    public Path getBookFilePath(String hash){
        BookFileEntity bookFile = bookFileRepository.findBookFileEntityByHash(hash);
        return Paths.get(bookFile.getPath());
    }

    public MediaType getBookFileMime(String hash){
        BookFileEntity bookFile = bookFileRepository.findBookFileEntityByHash(hash);
        String mimeType = URLConnection.guessContentTypeFromName(Paths.get(bookFile.getPath()).getFileName().toString());
        if (mimeType != null){
            return MediaType.parseMediaType(mimeType);
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public byte[] getBookFileByteArray(String hash) throws IOException {
        BookFileEntity bookFile = bookFileRepository.findBookFileEntityByHash(hash);
        Path path = Paths.get(downloadPath, bookFile.getPath());
        return Files.readAllBytes(path);
    }

}