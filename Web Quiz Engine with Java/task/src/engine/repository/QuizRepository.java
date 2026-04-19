package engine.repository;

import engine.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    //public Quiz findById(int id);
    //public Quiz save(Quiz quiz);
    //public List<Quiz> findAll();
}
