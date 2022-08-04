<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf"%>

<section>
    <div class="container max-w-lg mt-5 px-3 mx-auto">
        <h1 class="font-bold text-lg mb-5">게시물 상세 페이지</h1>
        <div class="flex gap-3">
            <span>ID</span>
            <div>
                ${article.id}
            </div>
        </div>

        <div class="flex gap-3">
            <span>제목</span>
            <div>
                ${article.title}
            </div>
        </div>

        <div class="flex gap-3">
            <span>내용</span>
            <div>
                ${article.body}
            </div>
        </div>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>