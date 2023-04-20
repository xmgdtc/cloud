package org.xmgdtc.storage.service.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.xmgdtc.api.enums.CloudExceptionEnum;
import org.xmgdtc.api.excetpion.CloudFileException;
import org.xmgdtc.api.view.oss.FileView;
import org.xmgdtc.common.service.BaseSVC;
import org.xmgdtc.common.utils.FileUtil;
import org.xmgdtc.storage.dao.FileDAO;
import org.xmgdtc.storage.entity.FileEntity;
import org.xmgdtc.storage.service.IFileSVC;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class FileSVCImpl extends BaseSVC implements IFileSVC {

    @Autowired
    private FileDAO fileDAO;

    @Autowired
    private MinioClient ossClient;

    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    @Override
    @Transactional
    public FileView saveFile(String bucket, MultipartFile file) {

        try {


            bucketExists(bucket);

            @Cleanup InputStream is = file.getInputStream();

            //生成上传的文件名
            String suffix = FileUtil.getFileSuffix(file);
            String saveId = UUID.randomUUID().toString();
            String saveFileName = saveId + "." + suffix;

            PutObjectArgs args = PutObjectArgs.builder().bucket(bucket).object(saveFileName).stream(is, is.available(), -1).build();

            //元数据存储
            FileEntity f = new FileEntity();
            f.setBucket(bucket);
            f.setName(file.getOriginalFilename());
            f.setSaveName(saveFileName);
            f.setSuffix(suffix);

            //保存
            fileDAO.save(f);
            ossClient.putObject(args);
            return buildCommonMapper().map(f, FileView.class);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            throw new CloudFileException(CloudExceptionEnum.ERR_FILE_SAVE, e.getMessage());
        }

    }


    /**
     * 检查桶是否存在 不存在则创建
     *
     * @param bucketName
     * @throws Exception
     */
    private void bucketExists(String bucketName) throws
            ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean flag = ossClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (flag) {
            return;
        }
        ossClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    }
}
