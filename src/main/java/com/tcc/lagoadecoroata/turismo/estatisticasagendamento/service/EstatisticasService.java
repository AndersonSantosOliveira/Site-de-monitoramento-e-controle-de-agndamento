package com.tcc.lagoadecoroata.turismo.estatisticasagendamento.service;

import com.tcc.lagoadecoroata.turismo.estatisticasagendamento.dtos.DadosEstatisticaResponse;
import com.tcc.lagoadecoroata.turismo.estatisticasagendamento.service.estatisticasserviceimpl.EstatisticaAgendamentoServiceImpl;
import com.tcc.lagoadecoroata.turismo.infostructagendamento.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstatisticasService implements EstatisticaAgendamentoServiceImpl {
    private static final int CAPACIDADE_MAXIMA = 50;
    private final AgendamentoRepository agendamentoRepository;

    public EstatisticasService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    private Integer calcularVagasRestante(Integer agendados) {
        return CAPACIDADE_MAXIMA - agendados;
    }

    private Double calcularPercentualOcupacao(Integer agendados) {
        return (agendados * 100.0) / CAPACIDADE_MAXIMA;
    }

    @Override
    public DadosEstatisticaResponse consultarEstatistica(LocalDate data) {
        Integer agendados = agendamentoRepository.obterTotalPessoasAgendadas(data);
        Integer vagasRestante = calcularVagasRestante(agendados);
        Double percentualOcuapacao = calcularPercentualOcupacao(agendados);
        return new DadosEstatisticaResponse(data, agendados, vagasRestante, percentualOcuapacao);
    }

    @Override
    public Integer consultarVagas(LocalDate data) {
        Integer agendados = agendamentoRepository.obterTotalPessoasAgendadas(data);
        return calcularVagasRestante(agendados);
    }

    @Override
    public Double consultarPercentual(LocalDate data) {
        Integer agendamentos = agendamentoRepository.obterTotalPessoasAgendadas(data);
        return calcularPercentualOcupacao(agendamentos);
    }

    @Override
    public Boolean consultarDisponibilidade(LocalDate data) {
        Integer agendados =
                agendamentoRepository.obterTotalPessoasAgendadas(data);

        Integer vagasRestantes =
                calcularVagasRestante(agendados);

        return vagasRestantes > 0;
    }
}
