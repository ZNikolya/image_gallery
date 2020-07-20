package imagegalleryspring.repository;

import imagegalleryspring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
