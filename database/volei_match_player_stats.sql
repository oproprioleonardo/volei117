CREATE TABLE volei_match_player_stats
(
    id            bigserial not null primary key,
    id_jogador    bigint    not null,
    id_partida    bigint    not null,
    bloqueios     int       not null default 0,
    defesas       int       not null default 0,
    saques        int       not null default 0,
    pontos_feitos int       not null default 0
);

alter table volei_match_player_stats
    add constraint fk_jogador
        foreign key (id_jogador) references volei_jogadores;

alter table volei_match_player_stats
    add constraint fk_partida
        foreign key (id_partida) references volei_partidas;

comment on table volei_match_player_stats is 'Tabela que armazena dados do jogador por partida';