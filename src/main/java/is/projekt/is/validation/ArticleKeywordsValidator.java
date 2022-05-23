package is.projekt.is.validation;

import is.projekt.is.request.ArticleRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArticleKeywordsValidator implements ConstraintValidator<ArticleKeywordsValid, ArticleRequest> {
    @Override
    public boolean isValid(ArticleRequest value, ConstraintValidatorContext context) {
        String[] keywords = value.getKeywords().split(",");
        for(String keyword: keywords){
            char[] array = keyword.toCharArray();
            for(char c: array){
                if(!(Character.isDigit(c) || Character.isAlphabetic(c))){
                    return false;
                }
            }
        }
        return true;
    }
}
