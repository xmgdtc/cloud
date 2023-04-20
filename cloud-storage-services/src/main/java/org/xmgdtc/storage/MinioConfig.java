package org.xmgdtc.storage;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * minioClient配置
 *
 * @author qd.liu
 * @date 2022年1月20日
 */
@Configuration
public class MinioConfig {

    @Value("${oss.endpoint}")
    private String url;
    @Value("${oss.access-key}")
    private String accessKey;
    @Value("${oss.secret-key}")
    private String secretKey;


    @Bean
    public MinioClient getMinioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(url)
                        .credentials(accessKey, secretKey)
                        .build();
        return minioClient;
    }

}
