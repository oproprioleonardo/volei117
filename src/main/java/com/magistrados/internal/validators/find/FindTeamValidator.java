package com.magistrados.internal.validators.find;

import com.magistrados.api.validations.ErrorMessage;
import com.magistrados.api.validations.Validator;
import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.models.find.FindTeam;
import org.apache.commons.lang3.math.NumberUtils;

public class FindTeamValidator extends Validator<FindTeam> {

    @Override
    public void validate(FindTeam object) throws ValidationException {
        if(object.id().isBlank() && NumberUtils.toLong(object.id(), 0) < 1){
            this.addError("ID do time", ErrorMessage.NOT_ID);
        } else if (object.nome().isBlank()) {
            this.addError("nome do time", ErrorMessage.NOT_NULLABLE);
        }

        this.throwPossibleErrors();
    }
}
