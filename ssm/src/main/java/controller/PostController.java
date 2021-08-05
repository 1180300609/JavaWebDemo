package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Controller
@CrossOrigin
@ResponseBody
public class PostController {
    @PostMapping("/post")
    public String getImage(@RequestParam("image") MultipartFile image){
        try {
            System.out.println("文件上传");
            String imgName = image.getOriginalFilename();
            System.out.println("上传文件名::" + imgName);
            String imgFilePath = "/img/" + imgName;
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(image.getBytes());
            out.flush();
            out.close();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success!";
    }
}
