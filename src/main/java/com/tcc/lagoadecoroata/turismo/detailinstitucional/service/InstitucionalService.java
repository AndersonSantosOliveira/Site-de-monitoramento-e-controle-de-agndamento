package com.tcc.lagoadecoroata.turismo.detailinstitucional.service;

import com.tcc.lagoadecoroata.turismo.detailinstitucional.dtos.*;
import org.springframework.stereotype.Service;

@Service
public class InstitucionalService {
    public SobreLagoaResponseDto sobreLagoa() {
        return new SobreLagoaResponseDto(
                "Sobre a Lagoa do Coroatá",
                "A Lagoa do Coroatá é um espaço natural que possui importância turística e ambiental " +
                        "para a região, atraindo visitantes interessados em conhecer e aproveitar suas " +
                        "características naturais. Ao longo de sua trajetória, a lagoa passou por diferentes " +
                        "momentos, incluindo um período de degradação que ameaçou suas condições naturais " +
                        "e mobilizou ações voltadas à sua recuperação e preservação. " +
                        "A recuperação do espaço contribuiu para que a lagoa voltasse a ser reconhecida como " +
                        "um local de visitação, mantendo sua relevância para a região. Com o crescimento do " +
                        "interesse turístico, principalmente em períodos de maior movimento, tornou-se cada " +
                        "vez mais importante conciliar a visitação com os cuidados necessários para preservar " +
                        "o ambiente. " +
                        "Atualmente, a Lagoa do Coroatá representa um espaço de lazer, turismo e contato com " +
                        "a natureza, cuja conservação depende também da conscientização de quem a visita. " +
                        "Conhecer sua trajetória e compreender a importância de preservar suas características " +
                        "naturais contribui para que a experiência turística aconteça de forma responsável e " +
                        "sustentável."
        );
    }

    public OrientacoesVisitantesResponseDto orientacoes() {
        return new OrientacoesVisitantesResponseDto(
                "Orientações aos visitantes",
                "Durante a visita, recomenda-se respeitar as orientações da comunidade gestora, " +
                        "preservar o ambiente natural, evitar o descarte inadequado de resíduos e contribuir " +
                        "para a conservação do espaço."
        );
    }

    public AvisosResponseDto avisos() {
        return new AvisosResponseDto(
                "Aviso aos visitantes",
                "As condições de visitação podem sofrer alterações conforme a capacidade de atendimento " +
                        "e as condições de funcionamento da Lagoa. Consulte as informações disponíveis na " +
                        "plataforma antes de realizar sua visita."
        );
    }

    public InformacoesResponseDto informacoes() {
        return new InformacoesResponseDto(
                "Informações para visitação",
                "A visitação à Lagoa do Coroatá está sujeita às condições de funcionamento do local " +
                        "e à capacidade disponível para o período. Recomenda-se que o visitante consulte " +
                        "previamente as informações disponibilizadas pela plataforma antes de se deslocar " +
                        "até a Lagoa."
        );
    }
    public InstitucionalResponseDto consultarInstitucional() {
        return new InstitucionalResponseDto(
                sobreLagoa(),
                orientacoes(),
                avisos(),
                informacoes()

        );
    }

}
