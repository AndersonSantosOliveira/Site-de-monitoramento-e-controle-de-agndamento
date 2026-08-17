# Plataforma de Apoio à Gestão da Lagoa do Coroatá

## Visão Geral

A plataforma de apoio à gestão da Lagoa do Coroatá é uma aplicação web desenvolvida como parte de um Trabalho de
Conclusão de Curso do curso de Análise e Desenvolvimento de Sistemas.

O projeto tem como objetivo apoiar a gestão comunitária da Lagoa do Coroatá, localizada no município de Cachoeira
Grande, no Maranhão, por meio da organização das informações, da comunicação com os visitantes e do planejamento da
visitação.

A proposta parte de um problema existente: embora a Lagoa do Coroatá já possua visibilidade turística e receba
visitantes de diferentes localidades, sua gestão ocorre de maneira predominantemente informal e presencial, sem uma
estrutura tecnológica centralizada para organização das informações e acompanhamento da visitação.

Atualmente, informações sobre o local encontram-se dispersas entre redes sociais, relatos de visitantes e conhecimentos
mantidos pela própria comunidade. A ausência de um canal oficial dificulta a comunicação de orientações, avisos e
informações atualizadas aos visitantes.

Além disso, períodos de maior procura podem gerar aumento significativo no fluxo de visitantes sem que a comunidade
disponha de mecanismos adequados para acompanhar esse movimento ou planejar a visitação.

A plataforma foi concebida para funcionar como um instrumento de apoio à comunidade gestora, não tendo como objetivo
substituir suas decisões, mas fornecer informações organizadas que possam auxiliar no processo de gestão.

## Objetivo

Desenvolver uma plataforma web de apoio à gestão comunitária da Lagoa do Coroatá, voltada à organização das informações,
à comunicação com os visitantes e ao planejamento da visitação, contribuindo para a preservação ambiental e para o
fortalecimento da gestão local.

## Público-alvo

A plataforma possui dois públicos principais:

- **Visitantes:** pessoas interessadas em conhecer e visitar a Lagoa do Coroatá.
- **Comunidade gestora:** responsáveis pela organização, acompanhamento e gestão do espaço.

## Principais objetivos da plataforma

- Centralizar informações relevantes sobre a Lagoa do Coroatá;
- Facilitar a comunicação entre a comunidade gestora e os visitantes;
- Apoiar o planejamento da visitação;
- Permitir o registro e acompanhamento do fluxo de visitantes;
- Auxiliar no controle da capacidade de visitação;
- Disponibilizar informações para apoiar decisões futuras da comunidade;
- Contribuir para a preservação ambiental;
- Fortalecer a organização da gestão comunitária.

## Escopo do Sistema

A plataforma contempla funcionalidades relacionadas à gestão da visitação e ao suporte às atividades da comunidade
gestora.

Entre os principais componentes atualmente desenvolvidos estão:

- Cadastro de visitantes;
- Cadastro de funcionários;
- Agendamento de visitas;
- Controle da ocupação da lagoa;
- Registro de check-in e check-out;
- Estatísticas relacionadas aos agendamentos;
- Geração de QR Code para reservas;
- Envio de confirmação de agendamento por e-mail;
- Tratamento global de exceções da API.

## Arquitetura

O backend está sendo desenvolvido em Java utilizando Spring Boot, seguindo uma organização modular, na qual as
principais funcionalidades do sistema são separadas em módulos específicos.

A estrutura busca manter responsabilidades bem definidas entre:

- Controllers;
- Services;
- Repositories;
- Entities;
- DTOs;
- Infraestrutura;
- Migrations do banco de dados.

## Módulos do Sistema

A aplicação está organizada em módulos responsáveis por diferentes funcionalidades do sistema:

- **Cadastro de Visitantes:** responsável pelo gerenciamento dos dados dos visitantes.
- **Cadastro de Funcionários:** responsável pelo gerenciamento dos funcionários envolvidos na gestão.
- **Agendamento:** responsável pelo registro e gerenciamento das visitas.
- **Controle da Lagoa:** responsável pelo acompanhamento da ocupação e do fluxo de visitantes.
- **QR Code:** responsável pela geração do QR Code associado às reservas.
- **E-mail:** responsável pelo envio das confirmações de agendamento aos visitantes.
- **Estatísticas de Agendamento:** responsável pela disponibilização de informações estatísticas relacionadas aos agendamentos.
- **Infraestrutura:** contém componentes compartilhados pela aplicação, como o tratamento global de exceções.

## Status do Desenvolvimento

O projeto encontra-se em desenvolvimento.

Até o momento, os seguintes módulos e componentes foram implementados:

| Módulo                        | Status       |
|-------------------------------|--------------|
| Cadastro de visitantes        | Implementado |
| Cadastro de funcionários      | Implementado |
| Agendamento                   | Implementado |
| Controle da Lagoa             | Implementado |
| QR Code                       | Implementado |
| E-mail                        | Implementado |
| Estatísticas de agendamento   | Implementado |
| Tratamento global de exceções | Implementado |
| Testes automatizados          | Pendente     |

## Testes

A implementação dos testes automatizados será realizada em uma etapa posterior do desenvolvimento.

Os testes unitários serão aplicados principalmente sobre os métodos e regras de negócio dos serviços de cada módulo,
buscando validar os diferentes cenários de funcionamento da aplicação.

## Tecnologias
### Backend

- Java 21
- Spring Boot 4.1.0
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Lombok

### Banco de dados

- PostgreSQL
- Flyway para gerenciamento e versionamento das migrações do banco de dados

### Recursos adicionais

- ZXing 3.5.3 — geração de QR Codes
- Spring Mail — envio de e-mails
- SpringDoc OpenAPI 3.0.3 — documentação da API e interface Swagger

### Gerenciamento do projeto

- Maven
- Maven Wrapper

## Principais dependências

O projeto utiliza os seguintes componentes principais:

| Tecnologia | Finalidade |
|---|---|
| Spring Boot | Base da aplicação |
| Spring Web MVC | Desenvolvimento da API REST |
| Spring Data JPA | Persistência e acesso aos dados |
| Spring Validation | Validação dos dados recebidos pela API |
| PostgreSQL | Banco de dados relacional |
| Flyway | Controle e versionamento das migrações |
| ZXing | Geração de QR Codes |
| Spring Mail | Envio de e-mails |
| SpringDoc OpenAPI | Documentação da API |
| Lombok | Redução de código boilerplate |


## Contexto do Projeto

A plataforma não pretende substituir a gestão realizada pela comunidade da Lagoa do Coroatá. Seu propósito é fornecer
uma estrutura tecnológica capaz de organizar informações e apoiar decisões relacionadas à visitação e ao funcionamento
do espaço.

Dessa forma, o sistema atua como uma ferramenta de apoio à gestão comunitária, contribuindo para uma organização mais
estruturada das informações e para o acompanhamento do fluxo de visitantes.