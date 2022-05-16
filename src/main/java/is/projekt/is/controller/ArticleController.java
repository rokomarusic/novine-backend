package is.projekt.is.controller;

import is.projekt.is.mapper.ArticleResponseMapper;
import is.projekt.is.request.ArticleRequest;
import is.projekt.is.response.ArticleResponse;
import is.projekt.is.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<ArticleResponse> getAllArticles(){
        return articleResponseMapper.map(articleService.getAllArticles());
    }

    @GetMapping("/{id}")
    public ArticleResponse getArticleById(@PathVariable Long id){
        return articleResponseMapper.map(articleService.getArticleById(id));
    }

    @PostMapping
    public ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest){
        return articleResponseMapper.map(articleService.createArticle(articleRequest));
    }

    @PutMapping("/{id}")
    public ArticleResponse updateArticle(@PathVariable Long id, @RequestBody ArticleRequest articleRequest){
        return articleResponseMapper.map(articleService.updateArticle(id, articleRequest));
    }

    @DeleteMapping("/{id}")
    public Long deleteArticle(@PathVariable Long id){
        return articleService.deleteArticle(id);
    }
}
