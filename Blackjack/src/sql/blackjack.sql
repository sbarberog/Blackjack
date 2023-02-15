-- Active: 1675880132561@@127.0.0.1@3306@blackjack

drop database if exists blackjack;

create database
    if not exists blackjack character set latin1 collate latin1_spanish_ci;


use blackjack;

drop table if exists jugadores;
create table
    if not exists jugadores (
        id_jugador int  AUTO_INCREMENT PRIMARY KEY,
        nombre varchar(30) UNIQUE,
        fecha_registro DATE DEFAULT (CURRENT_DATE()),
        fichas int default 100
    ) engine innodb;

drop table if exists partidas;
create table
    if not exists partidas (
        id_partida int  AUTO_INCREMENT PRIMARY KEY,
        id_jugador int  not null,
        puntos_jugador int  not null,
        puntos_banca int  not null,
        resultado enum ('V','E','D') not null,
        time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
        apuesta INT,
        blackjack boolean default false
    ) engine innodb;


alter table partidas 
ADD CONSTRAINT fk_partidas_id_jugador_jugadores
Foreign Key (id_jugador) REFERENCES jugadores(id_jugador);

drop view if exists datos_jugador;
create view datos_jugador
as select j.id_jugador, nombre, fecha_registro, fichas, ifnull(sum(resultado='V'),0) victorias, ifnull(sum(resultado='E'),0) empates, 
    ifnull(sum(resultado='D'),0) derrotas, count(id_partida) partidas_totales 
    from jugadores j left join partidas p using (id_jugador)
    group by id_jugador
    order by fichas desc;