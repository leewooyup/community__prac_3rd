package com.ll.exam;

import com.ll.exam.article.dto.ArticleDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;

public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    public Rq(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
    }

    public String getMethod() {
        return req.getMethod();
    }

    public String getActionPath() {
        String[] bits = req.getRequestURI().split("/");
        return "/%s/%s/%s".formatted(bits[1], bits[2], bits[3]);
    }

    public void view(String path) {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/" + path + ".jsp");
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getParam(String paramName, String defaultValue) {
        String value = req.getParameter(paramName);
        if(value == null) {
            return defaultValue;
        }
        return value;
    }

    public void replace(String uri, String msg) {
        if(msg != null && msg.trim().length() > 0) {
           appendBodyln("""
                   <script>
                   alert("%s");
                   </script>
                   """.formatted(msg));
        }

        appendBodyln("""
                <script>
                location.replace("%s");
                </script>
                """.formatted(uri));
    }

    public void appendBodyln(String msg) {
        appendBody(msg + "\n");
    }

    public void appendBody(String msg) {
        try {
            resp.getWriter().append(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAttr(String name, Object value) {
        req.setAttribute(name, value);
    }

    public long getLongPathValueByIndex(int index, long defaultValue) {
        String value = getPathValueByIndex(1,null);
        if(value == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value);
        } catch(NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getPathValueByIndex(int index, String defaultValue) {
        String[] bits = req.getRequestURI().split("/");
        try {
            return bits[4+index];
        } catch(ArrayIndexOutOfBoundsException e) {
            return defaultValue;
        }
    }

    public void historyBack(String msg) {
        if(msg != null && msg.trim().length() > 0) {
            appendBodyln("""
                <script>
                alert("%s");
                </script>
                """.formatted(msg));
        }
        appendBodyln("""
                <script>
                    history.back();
                </script>
                """);

    }

    public String getRouteMethod() {
        String method = getParam("_method", "");

        if(method.length() > 0) {
            return method.toUpperCase();
        }

        return req.getMethod();
    }
}
