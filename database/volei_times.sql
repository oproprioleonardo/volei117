CREATE TABLE volei_times
(
    id   bigserial primary key not null,
    nome varchar unique        not null
);

comment on table volei_times is 'Tabela que armazena os times';