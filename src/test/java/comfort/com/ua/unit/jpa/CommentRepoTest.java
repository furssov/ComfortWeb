package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.Comment;
import comfort.com.ua.repos.CommentRepo;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;
import java.time.LocalDate;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class CommentRepoTest {

    @Autowired
    private CommentRepo commentRepo;

    @Test
    public void commentPost() {
        for (int i = 0; i < 1000; i++) {
            commentRepo.save(new Comment((long) (i + 1), "Bla bla bla bla bla", LocalDate.now()));
        }
        Assertions.assertEquals(1000, commentRepo.findAll().size());
        ConstraintViolationException exception = Assertions.assertThrows( ConstraintViolationException.class,() ->
        commentRepo.save(new Comment(10001L, " ", LocalDate.now()))
        );

    }
}
