package com.magistrados.internal.validations.validators.find;

import com.magistrados.internal.validations.ErrorMessage;
import com.magistrados.internal.validations.Validator;
import com.magistrados.internal.validations.exceptions.ValidationException;
import com.magistrados.models.find.FindTeam;
import org.apache.commons.lang3.math.NumberUtils;

public class FindTeamValidator extends Validator<FindTeam> {

    @Override
    public void validate(FindTeam object) throws ValidationException {
        if(object.id().isBlank() && NumberUtils.toLong(object.id(), 0) < 1){
            this.addError("ID do time", ErrorMessage.NOT_ID);
        } else return;

        if (object.nome().isBlank()) {
            this.addError("nome do time", ErrorMessage.NOT_NULLABLE);
        } else return;

        this.throwPossibleErrors();
    }
}
