package engine;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@Validated
public class QuizController {
    private final Quizzes quizzes;

    public QuizController(Quizzes quizzes) {
        this.quizzes = quizzes;
    }

    // CREATE QUIZ
    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz) {
        Quiz saved =  quizzes.addQuiz(quiz);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    // GET ALL QUIZZES
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizzes.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable int id) {
        Quiz quiz = quizzes.findById(id);

        if(quiz  == null) {
            throw new ResourcesNotFoundException("User not found");
        }
        return ResponseEntity.ok(quiz);
    }



    @PostMapping("/{id}/solve")
    public ResponseEntity<QuizResult> solveQuiz(
            @PathVariable int id,
            @RequestBody AnswerRequest request) { // Use the wrapper here

        Quiz quiz = quizzes.findById(id);

        if (quiz == null) {
            throw new ResourcesNotFoundException("Not Found");
        }

        // Logic: Check if the user's list matches the quiz's answer list exactly
        List<Integer> userAnswers = request.getAnswer();
        List<Integer> correctAnswers = quiz.getAnswer();

        // Handle case where answer is null (empty list)
        if (correctAnswers == null) {
            correctAnswers = List.of();
        }
        if (userAnswers == null) {
            userAnswers = List.of();
        }

        if (correctAnswers.equals(userAnswers)) {
            return ResponseEntity.ok(new QuizResult(true, "Congratulations, you're right!"));
        }

        return ResponseEntity.ok(new QuizResult(false, "Wrong answer! Please, try again."));
    }
}
