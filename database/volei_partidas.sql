CREATE TABLE volei_partidas
(
    id        bigserial primary key not null,
    id_time_a bigint                not null,
    id_time_b bigint                not null,
    local     varchar               not null,
    qntd_sets int                   not null,
    data_hora timestamp             not null,
    vencedor  varchar
);

alter table volei_partidas
    add constraint fk_time_a
        foreign key (id_time_a) references volei_times;

alter table volei_partidas
    add constraint fk_time_b
        foreign key (id_time_b) references volei_times;

alter table volei_partidas
    ADD CONSTRAINT ck_vencedor
        CHECK (vencedor IN ('A', 'B'));

comment on table volei_partidas is 'Tabela que armazena partidas';