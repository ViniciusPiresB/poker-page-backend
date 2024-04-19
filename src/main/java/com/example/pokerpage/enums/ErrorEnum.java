package com.example.pokerpage.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    GENERAL(0, "Erro desconhecido!"),
    NOT_FOUND(404, "Registro não encontrado!"),
    MANDATORY_FIELD_NOT_FOUND(1, "Campo Obrigatório não preenchido"),
    ILLEGAL_BUY_IN(400, "Buy In menor do que o minimo aceitavel"),
    DATE_START_AFTER_DATE_FINAL(2, "A data inicial é maior que a final!");

    private final Integer id;
    private final String message;

    ErrorEnum(Integer id, String message){
        this.id = id;
        this.message = message;
    }
}
