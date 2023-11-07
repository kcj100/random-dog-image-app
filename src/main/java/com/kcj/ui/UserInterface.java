package com.kcj.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcj.randomdog.RandomDogApplication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInterface extends Application{
    Stage window;
    private static final Logger log = LoggerFactory.getLogger(RandomDogApplication.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    public void start(Stage window) throws IOException {
        setup(window);
        this.window = window;
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }


//    @Scheduled(fixedRate = 3000)
//    public void showImageEveryThreeSeconds() throws IOException {
//        setup(window);
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }

    private void setup(Stage window) throws JsonProcessingException {
        Image image = generateImage();
        ImageView imageView = new ImageView(image);
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 800, 600);
        window.setTitle("Dog Image");
        window.setScene(scene);
        window.show();
    }

    public Image generateImage() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://dog.ceo/api/breeds/image/random", String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
        Image image = new Image(rootNode.get("message").asText(), true);
//        saveImageFromUrlToResources(rootNode.get("message").asText());
        return image;
    }

//    public void saveImageFromUrlToResources(String imageUrl) {
//        try {
//            // Create a URL object from the image URL
//            URL url = new URL(imageUrl);
//
//            // Open a connection to the URL
//            InputStream inputStream = url.openStream();
//
//            // Specify the path where you want to save the image in resources
//            String destinationPath = "classpath:images/" + "image.jpg";
//
//            // Get the resource
//            Resource resource = new ClassPathResource(destinationPath);
//
//            // If the resource exists, delete it
//            if (resource.exists()) {
//                resource.getFile().delete();
//            }
//
//            // Create a FileOutputStream for the destination file
//            FileOutputStream fos = new FileOutputStream(resource.getFile());
//
//            // Copy the image data from the URL to the destination file
//            FileCopyUtils.copy(inputStream, fos);
//
//            // Close the input stream and output stream
//            inputStream.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    @Bean
//    public CommandLineRunner run() throws Exception{
//        return args -> showImageEveryThreeSeconds();
//    }

}
