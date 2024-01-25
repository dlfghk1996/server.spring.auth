package server.spring.auth.common.util;//package com.ilhwa.auth.common.util;
//
//import java.security.*;
//import java.security.interfaces.ECPrivateKey;
//import java.security.interfaces.ECPublicKey;
//import java.security.spec.*;
//
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//@Slf4j
//@Component
//public class JwtKeyUtil {
//
//    static PublicKey EC_PUBLIC_KEY;
//    static PrivateKey EC_PRIVATE_KEY;
//
//    private static final int DEFAULT_KEY_SIZE = 2048;
//    private static final String DEFAULT_KEY_ALGORITHM = "EC";
//
//    public JwtKeyUtil() {
//        getPrivateKey();
//    }
//
//    // keypair 방식의 private key 전달
//    public void getPrivateKey(){
//        System.out.println("GETPrivateKey");
//
//        try {
//
//            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
//
//            generator.initialize(2048);
//
//            KeyPair keyPair = generator.generateKeyPair();
//            PublicKey publicKey = keyPair.getPublic();
//            PrivateKey privateKey = keyPair.getPrivate();
//
//            this.EC_PUBLIC_KEY = publicKey;
//            this.EC_PRIVATE_KEY = privateKey;
//
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
