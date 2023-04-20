package org.xmgdtc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GatewayApplication {


    public static void main(String[] args) {

        try {
            SpringApplication.run(GatewayApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
