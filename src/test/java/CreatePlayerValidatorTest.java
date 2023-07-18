import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.internal.validators.CreatePlayerValidator;
import com.magistrados.models.create.CreateJogador;
import org.junit.jupiter.api.Test;

public class CreatePlayerValidatorTest {

    @Test
    public void testValidate() {
        try {
            new CreatePlayerValidator().validate(new CreateJogador("ogasdas", "das", "dsadsa", "23", "92", "-32", "dsada"));
        } catch (ValidationException e) {
            e.printOnFile();
        }


    }


}
