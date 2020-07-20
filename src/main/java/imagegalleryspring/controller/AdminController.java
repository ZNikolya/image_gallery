package imagegalleryspring.controller;

import imagegalleryspring.model.Category;
import imagegalleryspring.model.Image;
import imagegalleryspring.service.CategoryService;
import imagegalleryspring.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @Value("${file.upload.dir}")
    private String uploadDir;
    private final ImageService imageService;
    private final CategoryService categoryService;

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category, @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.upload(category,file,uploadDir);
        categoryService.save(category);
        return "redirect:/";
    }

    @PostMapping("/addImage")
    public String addImage(@ModelAttribute Image image, @RequestParam("images") MultipartFile file) throws IOException {
        imageService.upload(image,file,uploadDir);
        imageService.save(image);
        return "redirect:/imagePage?id=" + image.getCategory().getId();
    }
}
