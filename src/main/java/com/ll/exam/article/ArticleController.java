package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;

import java.util.List;

public class ArticleController {
    ArticleService articleService;

    public ArticleController() {
        articleService = new ArticleService();
    }

    public void showWriteForm(Rq rq) {
        rq.view("usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body","");
        long id = articleService.write(title, body);

        rq.replace("/usr/article/list/free", "%d번 게시물이 작성되었습니다".formatted(id));
    }

    public void showList(Rq rq) {
        List<ArticleDto> list = articleService.findAll();
        rq.setAttr("articles", list);
        rq.view("usr/article/list");
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1,0);
        if(id == 0) {
            rq.appendBodyln("번호를 입력해주세요.");
            return;
        }

        ArticleDto articleDto = articleService.findById(id);
        if(articleDto == null) {
            rq.appendBodyln("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", articleDto);
        rq.view("usr/article/detail");
    }
}