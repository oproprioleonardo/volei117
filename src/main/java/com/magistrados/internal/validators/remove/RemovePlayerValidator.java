package com.magistrados.internal.validators.remove;

import com.magistrados.api.validations.ErrorMessage;
import com.magistrados.api.validations.Validator;
import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.models.remove.RemovePlayer;
import org.apache.commons.lang3.math.NumberUtils;

public class RemovePlayerValidator extends Validator<RemovePlayer> {

    @Override
    public void validate(RemovePlayer object) throws ValidationException {
        if(!object.id().isBlank() && NumberUtils.toLong(object.id(), 0) < 1){
            this.addError("ID do jogador", ErrorMessage.NOT_ID);
        } else return;

        if (!object.id_time().isBlank() && NumberUtils.toLong(object.id_time(), 0) < 1) {
            this.addError("ID do time", ErrorMessage.NOT_ID);
        }

        int numeroJogador = NumberUtils.toInt(object.numero(), 0);
        if (numeroJogador < 0 || numeroJogador > 100) {
            this.addError("número do jogador", ErrorMessage.NOT_VALID_PLAYER_NUMBER);
        }

        if (this.errors.size() > 1)
            this.throwPossibleErrors();
    }
}
