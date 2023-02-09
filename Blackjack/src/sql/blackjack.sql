-- Active: 1675880132561@@127.0.0.1@3306@blackjack

drop database if exists blackjack;

create database
    if not exists blackjack character set latin1 collate latin1_spanish_ci;

--show databases;

use blackjack;

drop table if exists jugadores;
create table
    if not exists jugadores (
        id_jugador int unsigned AUTO_INCREMENT PRIMARY KEY,
        nombre varchar(30) UNIQUE KEY,
        fecha_inicial DATE DEFAULT (CURRENT_DATE())
    );

drop table if exists partidas;
create table
    if not exists partidas (
        id_partida int unsigned AUTO_INCREMENT PRIMARY KEY,
        id_jugador int unsigned not null,
        puntos_jugador int unsigned not null,
        puntos_banca int unsigned not null,
        resultado enum ('V','E','D') not null,
        time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
    );

alter table partidas 
ADD CONSTRAINT fk_partidas_id_jugador_jugadores
Foreign Key (id_jugador) REFERENCES jugadores(id_jugador);
