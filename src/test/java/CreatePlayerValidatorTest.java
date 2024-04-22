import com.magistrados.internal.validations.exceptions.ValidationException;
import com.magistrados.internal.validations.validators.create.CreatePlayerValidator;
import com.magistrados.models.create.CreatePlayer;
import org.junit.jupiter.api.Test;

public class CreatePlayerValidatorTest {

    @Test
    public void testValidate() {
        try {
            new CreatePlayerValidator().validate(new CreatePlayer("ogasdas", "6", "das", "dsadsa", "23", "92", "-32", "dsada"));
        } catch (ValidationException e) {
            e.printOnFile();
        }


    }


}
