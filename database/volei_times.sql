CREATE TABLE volei_times
(
    id   bigserial primary key not null,
    nome varchar unique        not null,
    vitorias int not null default 0,
    derrotas int not null default 0
);

comment on table volei_times is 'Tabela que armazena os times';