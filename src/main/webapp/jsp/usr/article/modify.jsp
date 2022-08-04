<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf"%>

<script>
function ArticleSave__submitForm(form) {
    form.title.value = form.title.value.trim();
    if(form.title.value.length == 0) {
        alert("제목을 입력해주세요.");
        return;
    }
    form.body.value = form.body.value.trim();
    if(form.body.value.length == 0) {
        alert("내용을 입력해주세요.");
        return;
    }
    form.submit();
}
</script>

<form method="POST" onsubmit="ArticleSave__submitForm(this); return false;">
    <section class="container max-w-lg mx-auto mt-5 px-3">
        <div>
            <span>ID</span>
            <div>${article.id}</div>
        </div>
        <div class="flex gap-3">
            <span>제목</span>
            <div>
                <input name="title" type="text" placeholder="제목을 입력해주세요." value="${article.title}" />
            </div>
        </div>
        <div class="flex gap-3">
            <span>내용</span>
            <div>
                <input name="body" type="text" placeholder="내용을 입력해주세요." value="${article.body}" />
            </div>
        </div>
        <div>
            <div>
                <input type="submit" value="수정">
            </div>
        </div>
    </section>
</form>

<%@ include file="../common/foot.jspf"%>