package com.magistrados.internal.validations.validators.find;

import com.magistrados.internal.validations.ErrorMessage;
import com.magistrados.internal.validations.Validator;
import com.magistrados.internal.validations.exceptions.ValidationException;
import com.magistrados.models.find.FindPlayer;
import org.apache.commons.lang3.math.NumberUtils;

public class FindPlayerValidator extends Validator<FindPlayer> {

    @Override
    public void validate(FindPlayer object) throws ValidationException {
        if(NumberUtils.toLong(object.id(), 0) < 1){
            this.addError("ID do jogador", ErrorMessage.NOT_ID);
        } else return;

        if (object.nome().isBlank()) {
            this.addError("nome do jogador", ErrorMessage.NOT_NULLABLE);
        } else return;

        if (NumberUtils.toLong(object.id_time(), 0) < 1) {
            this.addError("ID do time", ErrorMessage.NOT_ID);
        }

        int numeroJogador = NumberUtils.toInt(object.numero(), 0);
        if (numeroJogador < 1 || numeroJogador > 100) {
            this.addError("número do jogador", ErrorMessage.NOT_VALID_PLAYER_NUMBER);
        }

        if (this.errors.size() > 2)
            this.throwPossibleErrors();
    }
}
