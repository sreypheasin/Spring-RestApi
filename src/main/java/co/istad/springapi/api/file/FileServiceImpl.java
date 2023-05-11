package co.istad.springapi.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileServiceImpl implements FileService {
    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return null;
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> file) {
        return null;
    }
}
