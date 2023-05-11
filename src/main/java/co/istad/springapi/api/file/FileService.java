package co.istad.springapi.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * @param files request from data from client
     * @return list of FileDto
     */
    List<FileDto> uploadMultiple(List <MultipartFile> files);
    FileDto findByName(String name) throws IOException;
    List<FileDto> findAll();


    Resource download(String name);


    void deleteByName(String name);

    boolean deleteAllFile();

    /**
     * uses to find file by name
     * @param name of file
     * @return FileDto
     * @throws IOException internal error
     */

}
