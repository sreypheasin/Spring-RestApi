package co.istad.springapi.api.file;
import co.istad.springapi.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.server-path}")
    private  String fileServerPath;

    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.download-url}")
    private String fileDownloadUrl;
    private FileUtil fileUtil;
    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        int lastIndex = file.getOriginalFilename().lastIndexOf(("."));
        String extension = file.getOriginalFilename().substring(lastIndex+1);
        long size = file.getSize();
        String name = String.format("%s.%s", UUID.randomUUID().toString(),extension);
        String url = String.format("%s%s",fileBaseUrl,name);

        // TODO: upload process
        // 1. create path object
        Path path = Paths.get(fileServerPath+name);
        // copy path

        try {
            Files.copy(file.getInputStream(),path);
            return  FileDto.builder()
                    .name(name)
                    .url(url)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload file failed, Contact developer [015799159]!"
            );
        }

    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();

        for (MultipartFile file : files) {
            filesDto.add(fileUtil.upload(file));
        }

        return filesDto;
    }
    @Override
    public FileDto findByName(String name) throws IOException {
        Resource resource = fileUtil.findByName(name);
        return FileDto.builder()
                .name(resource.getFilename())
                .extension(fileUtil.getExtension(resource.getFilename()))
                .url(String.format("%s%s",fileUtil.getFileBaseUrl(), resource.getFilename()))
                .downloadUrl(String.format("%s%s", fileDownloadUrl, name))
                .size(resource.contentLength())
                .build();
    }

    @Override
    public List<FileDto> findAll() {
        List<FileDto> filesDto = new ArrayList<>();
        File files = new File(fileUtil.getFileServerPath());
        File [] getListOfFiles = files.listFiles();
        for(File file : getListOfFiles){
            if(file.isFile()){
                String name = file.getName();
                String url = fileUtil.getFileBaseUrl() + name;
                long size = file.length();
                int lastDotIndex = name.indexOf(".");
                String extension = name.substring(lastDotIndex +1);
//                filesDto.add(new FileDto(name, url ,extension,size));
            }
        }
        return filesDto;
    }
    @Override
    public Resource download(String name) {
        return fileUtil.findByName(name);
    }

    @Override
    public void deleteByName(String name) {
        fileUtil.deleteByName(name);
    }

    @Override
    public boolean deleteAllFile() {
        File files = new File(fileUtil.getFileServerPath());
        File [] getFileByName = files.listFiles();
        boolean isSuccess = true;
        for (File file : getFileByName) {
            if(!file.delete()){
                isSuccess=false;
            }
        }
        return isSuccess;
    }

}
