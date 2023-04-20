package org.xmgdtc.storage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"org.xmgdtc.storage.entity", "org.xmgdtc.common.entity"})
@SpringBootApplication(scanBasePackages = {"org.xmgdtc.storage", "org.xmgdtc.common"})
public class StorageApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(StorageApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
