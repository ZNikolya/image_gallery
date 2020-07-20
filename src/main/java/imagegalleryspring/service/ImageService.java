package imagegalleryspring.service;

import imagegalleryspring.model.Category;
import imagegalleryspring.model.Image;
import imagegalleryspring.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void save(Image image) {
        imageRepository.save(image);
    }

    public List<Image> findImageByCategoryId(int id) {
        return imageRepository.findImageByCategoryId(id);
    }

    public void upload(Image image, MultipartFile file, String uploadDir) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File imageM = new File(uploadDir, name);
        file.transferTo(imageM);
        image.setPicUrl(name);
    }

}
