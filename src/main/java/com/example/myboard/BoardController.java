package com.example.myboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;

    // 게시판 생성
    @GetMapping
    public String createBoard(Model model) {
        //boardService.create();
        model.addAttribute("boards", boardService.create());
        return "board/home";
    }

    // 게시글 주제별 조회
    @GetMapping("{topic}")
    public String openBoard(
            @PathVariable("topic")
            String topic,
            Model model
    ) {
        model.addAttribute("articles", boardService.getArticleList(topic));
        model.addAttribute("board", boardService.getBoard(topic));
        return "board/open";
    }

    // 게시글 작성
    @GetMapping("write-view")
    public String writeArticle(Model model) {
        return "board/write";
    }

    @PostMapping("write")
    public String write(
            @RequestParam("topic")
            String topic,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password
            )
    {
        boardService.write(topic, title, content, password);
        return "redirect:/boards";
    }
}
