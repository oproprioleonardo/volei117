package com.magistrados.internal.validators.remove;

import com.magistrados.api.validations.ErrorMessage;
import com.magistrados.api.validations.Validator;
import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.models.remove.RemoveTeam;
import org.apache.commons.lang3.math.NumberUtils;

public class RemoveTeamValidator extends Validator<RemoveTeam> {

    @Override
    public void validate(RemoveTeam object) throws ValidationException {
        if(object.id().isBlank() && NumberUtils.toLong(object.id(), 0) < 1){
            this.addError("ID do time", ErrorMessage.NOT_ID);
        } else return;
        if (object.nome().isBlank()) {
            this.addError("nome do time", ErrorMessage.NOT_NULLABLE);
        } else return;

        this.throwPossibleErrors();
    }
}
