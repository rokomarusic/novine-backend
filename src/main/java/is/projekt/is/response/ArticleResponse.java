package is.projekt.is.response;

import is.projekt.is.model.Content;

import javax.persistence.*;

public class ArticleResponse {

    private Long id;

    private Content content;

    private String keywords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
