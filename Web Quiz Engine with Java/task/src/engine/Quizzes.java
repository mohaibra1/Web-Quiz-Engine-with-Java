package engine;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Quizzes {

    private final List<Quiz> quizzes = new ArrayList<>();
    private int currentId = 0;

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public Quiz addQuiz(Quiz quiz) {
        quiz.setId(currentId++);
        quizzes.add(quiz);
        return quiz;
    }

    public Quiz findById(int id) {
        return quizzes.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElse(null);
    }
}