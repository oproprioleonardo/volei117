CREATE TABLE volei_jogadores
(
    id               bigserial primary key not null,
    id_time          bigint,
    nome             varchar               not null,
    numero           varchar               not null,
    bloqueios        int                   not null default 0,
    defesas          int                   not null default 0,
    saques           int                   not null default 0,
    pontos_feitos    int                   not null default 0,
    partidas_jogadas int                   not null default 0
);

alter table volei_jogadores
    add constraint fk_time
        foreign key (id_time) references volei_times;

comment on table volei_jogadores is 'Tabela que armazena jogadores';