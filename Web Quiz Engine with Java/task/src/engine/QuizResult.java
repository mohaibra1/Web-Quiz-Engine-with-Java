package engine;

public class QuizResult {
    private boolean success;
    private String feedback;

    public QuizResult(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() { return success; }
    public String getFeedback() { return feedback; }
}
