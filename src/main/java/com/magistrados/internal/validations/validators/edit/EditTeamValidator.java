package com.magistrados.internal.validations.validators.edit;

import com.magistrados.internal.validations.ErrorMessage;
import com.magistrados.internal.validations.Validator;
import com.magistrados.internal.validations.exceptions.ValidationException;
import com.magistrados.models.edit.EditTeam;
import org.apache.commons.lang3.math.NumberUtils;

public class EditTeamValidator extends Validator<EditTeam> {

    @Override
    public void validate(EditTeam object) throws ValidationException {
        if (!object.id().isBlank() && NumberUtils.toLong(object.id(), 0) < 1) {
            this.addError("ID do time", ErrorMessage.NOT_ID);
        }
        if (object.nome().isBlank()) {
            this.addError("nome do time", ErrorMessage.NOT_NULLABLE);
        }
        int vitorias = NumberUtils.toInt(object.vitorias(), -1);
        if (vitorias < 0) {
            this.addError("vitórias", ErrorMessage.NOT_NEGATIVE_NUMBER);
        }
        int derrotas = NumberUtils.toInt(object.derrotas(), -1);
        if (derrotas < 0) {
            this.addError("derrotas", ErrorMessage.NOT_NEGATIVE_NUMBER);
        }

        this.throwPossibleErrors();
    }
}
