CREATE TABLE volei_sets
(
    id         bigserial primary key not null,
    id_partida bigint                not null,
    ordem      int                   not null,
    pontos_a   int                   not null default 0,
    pontos_b   int                   not null default 0,
    finalizado bool                           default false,
    vencedor   varchar
);

alter table volei_sets
    ADD CONSTRAINT ck_vencedor
        CHECK (vencedor IN ('A', 'B'));

alter table volei_sets
    ADD CONSTRAINT fk_partida
        foreign key (id_partida) references volei_partidas;

comment on table volei_sets is 'Tabela que armazena os sets';