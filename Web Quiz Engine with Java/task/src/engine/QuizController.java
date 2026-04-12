package engine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final Quizzes quizzes;

    public QuizController(Quizzes quizzes) {
        this.quizzes = quizzes;
    }

    // CREATE QUIZ
    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
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
    public ResponseEntity<QuizResult> solveQuiz(@PathVariable int id, @RequestParam int answer) {
        Quiz quiz = quizzes.findById(id);

        if(quiz == null) {
            throw new ResourcesNotFoundException("Not Found");
        }

        if(quiz.getAnswer() != null && quiz.getAnswer() == answer) {
            return ResponseEntity.ok(new QuizResult(true,"Congratulations, you're right!"));
        }

        return ResponseEntity.ok(new QuizResult(false,"Wrong answer! Please, try again."));
    }
}
