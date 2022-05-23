package is.projekt.is.controller;

import is.projekt.is.mapper.ArticleResponseMapper;
import is.projekt.is.request.ArticleRequest;
import is.projekt.is.response.ArticleResponse;
import is.projekt.is.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleResponseMapper articleResponseMapper;

    @Autowired
    public ArticleController(ArticleService articleService, ArticleResponseMapper articleResponseMapper) {
        this.articleService = articleService;
        this.articleResponseMapper = articleResponseMapper;
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAllArticles(){
        return ResponseEntity.ok(articleResponseMapper.map(articleService.getAllArticles()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleResponseMapper.map(articleService.getArticleById(id)));
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody @Valid ArticleRequest articleRequest){
        return ResponseEntity.ok(articleResponseMapper.map(articleService.createArticle(articleRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @RequestBody ArticleRequest articleRequest){
        return ResponseEntity.ok(articleResponseMapper.map(articleService.updateArticle(id, articleRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteArticle(@PathVariable Long id){
        return ResponseEntity.ok(articleService.deleteArticle(id));
    }
}
