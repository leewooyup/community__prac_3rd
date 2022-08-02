<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>
<section>
    <div class="container max-w-lg mx-auto mt-5">
        <h1 class="font-bold text-lg mb-5">게시물 리스트</h1>
        <ul>
        <c:forEach items="${articles}" var="article">
        <li class="flex">
            <a class="w-[40px]" href="#">${article.id}</a>
            <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/${article.id}">${article.title}</a>
            <a class="hover:underline hover:text-[red] mr-3" href="/usr/article/delete/free/${article.id}">삭제</a>
            <a class="hover:underline hover:text-[red]" href="#">수정</a>
        </li>
        </c:forEach>
        </ul>
    <div>
</section>

<%@ include file="../common/foot.jspf"%>