package org.xmgdtc.storage.service;

import org.springframework.web.multipart.MultipartFile;
import org.xmgdtc.api.view.oss.FileView;

public interface IFileSVC {

    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    FileView saveFile(String bucket, MultipartFile file);
}
