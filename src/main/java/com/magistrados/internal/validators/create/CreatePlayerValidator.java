package com.magistrados.internal.validators.create;

import com.magistrados.api.validations.ErrorMessage;
import com.magistrados.api.validations.Validator;
import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.models.create.CreatePlayer;
import org.apache.commons.lang3.math.NumberUtils;

public class CreatePlayerValidator extends Validator<CreatePlayer> {

    @Override
    public void validate(CreatePlayer object) throws ValidationException {
        if (object.nome().isBlank()) {
            this.addError("nome do jogador", ErrorMessage.NOT_NULLABLE);
        }
        if (!object.timeId().isBlank() && NumberUtils.toLong(object.timeId(), 0) < 1) {
            this.addError("ID do time", ErrorMessage.NOT_ID);
        }
        int numeroJogador = NumberUtils.toInt(object.numeroJogador(), 0);
        if (numeroJogador < 0 || numeroJogador > 100) {
            this.addError("número do jogador", ErrorMessage.NOT_VALID_PLAYER_NUMBER);
        }
        int bloqueiosQnd = NumberUtils.toInt(object.quantidadeBloqueios(), -1);
        if (bloqueiosQnd < 0) {
            this.addError("quantidade de bloqueios", ErrorMessage.NOT_NEGATIVE_NUMBER);
        }
        int defesasQnd = NumberUtils.toInt(object.quantidadeDefesas(), -1);
        if (defesasQnd < 0) {
            this.addError("quantidade de defesas", ErrorMessage.NOT_NEGATIVE_NUMBER);
        }
        int saquesQnd = NumberUtils.toInt(object.quantidadeSaques(), -1);
        if (saquesQnd < 0) {
            this.addError("quantidade de saques", ErrorMessage.NOT_NEGATIVE_NUMBER);
        }
        int pontosQnd = NumberUtils.toInt(object.quantidadePontos(), -1);
        if (pontosQnd < 0) {
            this.addError("quantidade de pontos", ErrorMessage.NOT_NEGATIVE_NUMBER);
        }

        this.throwPossibleErrors();
    }
}
