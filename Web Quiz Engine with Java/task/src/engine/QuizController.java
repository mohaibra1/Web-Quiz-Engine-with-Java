package engine;

import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {


    @GetMapping("/api/quiz")
    public Quiz quiz() {
        return  new Quiz();
    }

    @PostMapping("/api/quiz")
    public QuizResult solveQuiz(@RequestParam int answer) {
        if(answer == 2) {
            return new QuizResult(true,"Congratulations, you're right!");
        }else {
            return new QuizResult(false,"Wrong answer! Please, try again.");
        }
    }
}
