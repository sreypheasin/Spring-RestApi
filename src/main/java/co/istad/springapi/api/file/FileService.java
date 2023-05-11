package co.istad.springapi.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    /**
     * Upload a single file
     * @param file request from data from client
     * @return FileDto
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     * Upload multiple file
     * @param file request from data from client
     * @return list of FileDto
     */
    List<FileDto> uploadMultiple(List <MultipartFile> file);
}
