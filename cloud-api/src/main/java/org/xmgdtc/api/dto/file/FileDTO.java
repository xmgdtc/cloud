package org.xmgdtc.api.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.ByteArrayOutputStream;


@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class FileDTO {

    private String name;

    private ByteArrayOutputStream outputStream;
}
