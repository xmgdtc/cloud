package org.xmgdtc.storage.service;

import org.springframework.web.multipart.MultipartFile;
import org.xmgdtc.api.view.oss.FileView;

import java.io.InputStream;

public interface IFileSVC {

    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    FileView saveFile(String bucket, MultipartFile file);

    /**
     * 保存文件 这里考虑到空间问题 没有记录历史
     *
     * @param file
     * @return
     */
    FileView updateFile(String id, MultipartFile file);

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    FileView deleteFile(String id);

    /**
     * 获取文件
     *
     * @param id
     * @return
     */
    InputStream getFile(String id);
}
