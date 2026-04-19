package engine.dto;

import java.time.LocalDateTime;

public class QuizCompletionResponse {

    private Integer id;
    private LocalDateTime completedAt;

    public QuizCompletionResponse(Integer id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public Integer getId() { return id; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}