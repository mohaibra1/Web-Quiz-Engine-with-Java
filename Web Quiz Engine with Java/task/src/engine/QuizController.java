package engine;

import engine.repository.QuizRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@Validated
public class QuizController {
    private final Quizzes quizzes;
    private final QuizRepository quizRepository;

    public QuizController(Quizzes quizzes, QuizRepository quizRepository) {
        this.quizzes = quizzes;
        this.quizRepository = quizRepository;
    }

    // CREATE QUIZ
    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody Quiz quiz) {
        Quiz saved = quizRepository.save(quiz); // quizzes.addQuiz(quiz);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    // GET ALL QUIZZES
    @GetMapping
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizRepository.findAll()); // quizzes.getQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable int id) {
        Quiz quiz = quizRepository.findById(id); // quizzes.findById(id);

        if(quiz  == null) {
            throw new ResourcesNotFoundException("User not found");
        }
        return ResponseEntity.ok(quiz);
    }


    @PostMapping("/{id}/solve")
    public ResponseEntity<QuizResult> solveQuiz(
            @PathVariable int id,
            @RequestBody AnswerRequest request) { // Use the wrapper here

        Quiz quiz = quizRepository.findById(id); // quizzes.findById(id);

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

        List<Integer> finalCorrectAnswers = correctAnswers;
        List<Integer> finalUserAnswers = userAnswers;
        boolean isCorrect = correctAnswers.size() == userAnswers.size() &&
                java.util.stream.IntStream.range(0, correctAnswers.size())
                        .allMatch(i -> finalCorrectAnswers.get(i).equals(finalUserAnswers.get(i)));


        if (isCorrect) {
            return ResponseEntity.ok(new QuizResult(true, "Congratulations, you're right!"));
        }

        return ResponseEntity.ok(new QuizResult(false, "Wrong answer! Please, try again."));
    }
}
