package fc.board.service;

import fc.board.domain.Article;
import fc.board.domain.UserAccount;
import fc.board.dto.ArticleCommentDto;
import fc.board.repository.ArticleCommentRepository;
import fc.board.repository.ArticleRepository;
import fc.board.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;
    @Mock
    private ArticleCommentRepository articleCommentRepository;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 받아온다.")
    @Test
    public void getCommentListTest() throws Exception {
        //given
        Long articleId = 1L;
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("booki", "pw", null, null, null));
        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of(userAccount, "title", "content", "#java"))
        );

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }
}