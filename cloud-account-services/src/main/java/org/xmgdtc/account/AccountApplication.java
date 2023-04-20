package org.xmgdtc.account;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EntityScan(basePackages = {"org.xmgdtc.account.entity", "org.xmgdtc.common.entity"})
@EnableFeignClients(basePackages = {"org.xmgdtc.cloud.client"})
@SpringBootApplication(scanBasePackages = {"org.xmgdtc.account", "org.xmgdtc.common", "org.xmgdtc.redis"})
public class AccountApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(AccountApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
