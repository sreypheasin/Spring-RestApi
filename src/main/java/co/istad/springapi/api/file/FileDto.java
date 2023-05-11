package co.istad.springapi.api.file;

import lombok.Builder;

@Builder
public record FileDto(String name,
                      String uri,
                      String extension,
                      long size) {
}
