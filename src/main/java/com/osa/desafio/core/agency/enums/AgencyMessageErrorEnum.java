package com.osa.desafio.core.agency.enums;

public enum AgencyMessageErrorEnum {
    DUPLICATE_LATITUDE_LONGITUDE("Não é possível salvar agência com latitude e longitude já existente na base.");

    private final String mensagem;

    AgencyMessageErrorEnum(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
