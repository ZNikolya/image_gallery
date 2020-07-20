package imagegalleryspring.service;

import imagegalleryspring.model.Category;
import imagegalleryspring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category getOne(int id) {
        return categoryRepository.getOne(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public void upload(Category category, MultipartFile file,String uploadDir) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir,name);
        file.transferTo(image);
        category.setPicUrl(name);
    }
}
