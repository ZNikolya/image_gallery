package imagegalleryspring.controller;

import imagegalleryspring.model.Category;
import imagegalleryspring.model.Image;
import imagegalleryspring.service.CategoryService;
import imagegalleryspring.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImageController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final ImageService imageService;
    private final CategoryService categoryService;

    @GetMapping("/imagePage")
    public String about(ModelMap map, @RequestParam("id") int id){
        map.addAttribute("categories", categoryService.getOne(id));
        map.addAttribute("imageI", imageService.findImageByCategoryId(id));
        return "imagePage";
    }



    @GetMapping(value = "/images", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam ("name")String imageName) throws IOException{
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
}
