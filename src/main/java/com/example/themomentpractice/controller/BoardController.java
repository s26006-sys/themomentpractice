package com.example.themomentpractice.controller;


import com.example.themomentpractice.entity.BoardEntity;
import com.example.themomentpractice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/create")
    public String createBoard(@RequestParam String content) {
        BoardEntity board = new BoardEntity(content);
        boardRepository.save(board);
        return "게시글 생성 완료! ID: " + board.getId();
    }

    @GetMapping("/all")
    public List<BoardEntity> getAllBoards() {
        return boardRepository.findAll();
    }

    @GetMapping("/update/{id}")
    public String updateBoard(@PathVariable Long id, @RequestParam String content) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        board.setContent(content);
        boardRepository.save(board);
        return id + "번 게시글 수정 완료!";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id + "번 게시글 삭제 완료!";
    }
}
