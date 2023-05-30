package fc.board.controller;

import fc.board.dto.request.ArticleCommentRequest;
import fc.board.dto.security.BoardPrincipal;
import fc.board.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/new")
    public String postNewArticleComment(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            ArticleCommentRequest articleCommentRequest) {
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(boardPrincipal.toDto()));
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }

    @PostMapping("/{articleCommentId}/delete")
    public String deleteArticleComment(
            @PathVariable Long articleCommentId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            Long articleId) {
        articleCommentService.deleteArticleComment(articleCommentId, boardPrincipal.getUsername());
        return "redirect:/articles/" + articleId;
    }
}
