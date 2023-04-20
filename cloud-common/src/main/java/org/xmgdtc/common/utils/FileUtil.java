package org.xmgdtc.common.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

    public static String getFileSuffix(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }

}
