package com.example.host;

        import com.example.base.ServiceInitializer;
        import org.springframework.boot.SpringApplication;

        import java.awt.*;
        import java.net.URI;

public class ServiceStarter {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(ServiceInitializer.class);
        Desktop.getDesktop().browse(new URI("http://127.0.0.1:8080/api"));
    }
}