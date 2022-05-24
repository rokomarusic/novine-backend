package is.projekt.is.repository;

import is.projekt.is.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> getArticleByContent_Employee_Id(Long id);

    @Query("SELECT a FROM Article a WHERE lower(a.content.name) LIKE lower(:name)")
    List<Article> searchByName(@Param("name") String name);
}
