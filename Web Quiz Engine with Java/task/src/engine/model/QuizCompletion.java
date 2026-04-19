package engine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class QuizCompletion {

    @Id
    @GeneratedValue
    private Long id;

    private Integer quizId;
    private String userEmail;
    private LocalDateTime completedAt;

    public QuizCompletion() {}

    public QuizCompletion(Integer quizId, String userEmail) {
        this.quizId = quizId;
        this.userEmail = userEmail;
        this.completedAt = LocalDateTime.now();
    }

    public Integer getQuizId() {
        return quizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
