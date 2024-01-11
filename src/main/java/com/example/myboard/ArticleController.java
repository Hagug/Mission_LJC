package com.example.myboard;

import com.example.myboard.entity.Comment;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
@RequestMapping("boards/article")
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 단일 조회 + 댓글 전체 조회
    @GetMapping("{id}")
    public String readArticle(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.getArticle(id));
        model.addAttribute("comments", articleService.getCommentList(id));
        return "article/read";
    }

    // 게시글 수정
    @GetMapping("{id}/update-view")
    public String updateArticle(
            @PathVariable("id")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.getArticle(id));
        return "article/update";
    }

    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id")
            Long id,
            @RequestParam("topic")
            String topic,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content
    ) {
        articleService.updateArticle(id, topic, title, content);
        return String.format("redirect:/boards/article/%d", id);
    }

    // 게시글 삭제
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/boards";
    }

    // 댓글 작성
    @PostMapping("{id}/comment")
    public String writeComment(
            @PathVariable("id")
            Long id,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
    ) {
        articleService.writeComment(id, content, password);
        return String.format("redirect:/boards/article/%d", id);
    }

    // 댓글 삭제
    @PostMapping("{id}/comment/{commentId}/delete")
    public String deleteComment(
            @PathVariable("id")
            Long id,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("password")
            String password,
            HttpServletResponse response
    ) throws Exception{
        Comment comment = articleService.getComment(commentId);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 댓글의 비밀번호가 일치할때만 삭제
        if(password.equals(comment.getPassword())) {
            articleService.deleteComment(commentId);
            out.println("<script>alert('댓글이 삭제되었습니다.'); history.go(-1);</script>");
        }
        else {
            out.println("<script>alert('비밀번호가 틀렸습니다.'); history.go(-1);</script>");
        }
        out.flush();
        return String.format("redirect:/boards/article/%d", id);
    }
}
