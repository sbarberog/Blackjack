-- Active: 1675880132561@@127.0.0.1@3306@blackjack

--drop table if exists jugadores;
create table
    if not exists jugadores (
        id_jugador integer PRIMARY KEY AUTOINCREMENT,
        nombre varchar(30) UNIQUE,
        fecha_registro DATE default CURRENT_DATE
    ) ;

--drop table if exists partidas;
create table
    if not exists partidas (
        id_partida integer PRIMARY KEY AUTOINCREMENT,
        id_jugador int unsigned not null,
        puntos_jugador int unsigned not null,
        puntos_banca int unsigned not null,
        resultado text CHECK(resultado in ('V','E','D')) not null,
        time_stamp TIMESTAMP default CURRENT_TIMESTAMP,
        Foreign Key (id_jugador) REFERENCES jugadores(id_jugador)
    ) ;

--drop view if exists datos_jugador;
create view datos_jugador
as select id_jugador,nombre,fecha_registro, ifnull(sum(resultado='V'),0) victorias, ifnull(sum(resultado='E'),0) empates, 
    ifnull(sum(resultado='D'),0) derrotas, count(id_partida) partidas_totales 
    from jugadores left join partidas using (id_jugador)
    group by id_jugador
    order by partidas_totales desc;