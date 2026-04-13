package engine;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.util.List;


public class Quiz {

    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotEmpty
    @Size(min=2)
    private List<String> options;

    private List<Integer> answer;

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<Integer> getAnswer() { return answer; }
    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}