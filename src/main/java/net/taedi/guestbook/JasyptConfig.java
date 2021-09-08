package net.taedi.guestbook;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String encryptKey;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptKey);
        System.out.println(encryptKey);
//        config.setPassword(System.getenv("TAEDI_ENC_KEY"));
        config.setAlgorithm("PBEWithHmacSHA512AndAES_256");
        config.setKeyObtentionIterations("100000");
        config.setPoolSize("1");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        encryptor.setConfig(config);
        return encryptor;
    }

}
