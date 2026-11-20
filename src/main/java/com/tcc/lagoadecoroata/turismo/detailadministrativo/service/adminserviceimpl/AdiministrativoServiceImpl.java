package com.tcc.lagoadecoroata.turismo.detailadministrativo.service.adminserviceimpl;

import com.tcc.lagoadecoroata.turismo.detailadministrativo.dtos.RegistroLocalRequestDto;
import com.tcc.lagoadecoroata.turismo.detailadministrativo.entity.RegistroLocal;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.dtos.AgendamentoResponseDto;

public interface AdiministrativoServiceImpl {
    void realizarCheckIn(String codigoReserva);

    void realizarCheckOut(String codigoReserva);

    RegistroLocal registrarEntradaLocal(RegistroLocalRequestDto dto);

    void realizarSaidaLocal(String codigo);

    AgendamentoResponseDto buscarPorCodigoReserva(String codigoReserva);

}
