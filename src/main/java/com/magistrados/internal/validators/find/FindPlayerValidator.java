package com.magistrados.internal.validators.find;

import com.magistrados.api.validations.ErrorMessage;
import com.magistrados.api.validations.Validator;
import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.models.find.FindPlayer;
import org.apache.commons.lang3.math.NumberUtils;

public class FindPlayerValidator extends Validator<FindPlayer> {

    @Override
    public void validate(FindPlayer object) throws ValidationException {
        if(!object.id().isBlank() && NumberUtils.toLong(object.id(), 0) < 1){
            this.addError("ID do jogador", ErrorMessage.NOT_ID);
        } else {
            if (!object.nome().isBlank()) {
                this.addError("nome do jogador", ErrorMessage.NOT_NULLABLE);
            } else {
                if (!object.id_time().isBlank() && NumberUtils.toLong(object.id_time(), 0) < 1) {
                    this.addError("ID do time", ErrorMessage.NOT_ID);
                }
                int numeroJogador = NumberUtils.toInt(object.numero(), 0);
                if (numeroJogador < 0 || numeroJogador > 100) {
                    this.addError("número do jogador", ErrorMessage.NOT_VALID_PLAYER_NUMBER);
                }
            }
        }

        this.throwPossibleErrors();
    }
}
