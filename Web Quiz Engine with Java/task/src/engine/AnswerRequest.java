package engine;

import java.util.List;

public class AnswerRequest {
    private List<Integer> answer;

    // Default constructor is required for Jackson
    public AnswerRequest() {}

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
