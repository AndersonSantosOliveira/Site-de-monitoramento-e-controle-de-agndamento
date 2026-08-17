package com.tcc.lagoadecoroata.turismo.infocontrolelagoa.service.controleimpl;

public interface ControleLagoaServiceImpl {
    void registrarCheckIn(Integer quantidadePessoas);

    void registrarCheckOut(Integer quantidadePessoas);

    Integer consultarOcupacaoAtual();

    Integer consultarVagasRestantes();

    Double consultarPercentualOcupacao();


}
