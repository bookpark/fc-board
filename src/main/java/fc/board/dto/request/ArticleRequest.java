package fc.board.dto.request;

import fc.board.dto.ArticleDto;
import fc.board.dto.HashtagDto;
import fc.board.dto.UserAccountDto;

import java.util.Set;

public record ArticleRequest(
        String title,
        String content
) {
    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return toDto(userAccountDto, null);
    }

    public ArticleDto toDto(UserAccountDto userAccountDto, Set<HashtagDto> hashtagDtos) {
        return ArticleDto.of(
                userAccountDto,
                title,
                content,
                hashtagDtos
        );
    }
}
