import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.internal.validators.create.CreatePlayerValidator;
import com.magistrados.models.create.CreatePlayer;
import org.junit.jupiter.api.Test;

public class CreatePlayerValidatorTest {

    @Test
    public void testValidate() {
        try {
            new CreatePlayerValidator().validate(new CreatePlayer("ogasdas", "das", "dsadsa", "23", "92", "-32", "dsada"));
        } catch (ValidationException e) {
            e.printOnFile();
        }


    }


}
