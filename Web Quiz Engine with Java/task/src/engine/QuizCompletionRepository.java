package engine;

import engine.model.QuizCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizCompletionRepository extends JpaRepository<QuizCompletion,Long> {
    Page<QuizCompletion> findByUserEmailOrderByCompletedAtDesc(String userEmail, Pageable pageable);
}
