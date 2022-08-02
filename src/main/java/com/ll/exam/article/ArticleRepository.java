package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class ArticleRepository {
    private static List<ArticleDto> datum;
    private static int lastIndex;

    static {
        datum = new ArrayList<>();
        lastIndex = 10;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1,10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            ArticleDto articleDto = new ArticleDto(id, title, body);
            datum.add(articleDto);
        });
    }

    public long write(String title, String body) {
        long id = ++lastIndex;
        ArticleDto articleDto = new ArticleDto(id,title, body);
        datum.add(articleDto);
        return id;
    }

    public List<ArticleDto> findAll() {
        return datum;
    }

    public ArticleDto findById(long id) {
        for(ArticleDto articleDto : datum) {
            if(articleDto.getId() == id) {
                return articleDto;
            }
        }
        return null;
    }

    public void delete(long id) {
        ArticleDto articleDto = findById(id);
        if(articleDto == null) {
            return;
        }
        datum.remove(articleDto);
    }
}
