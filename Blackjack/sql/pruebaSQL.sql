-- Active: 1675880132561@@127.0.0.1@3306@blackjack
create user juego_blackjack IDENTIFIED by 'blackjack';

show grants for juego_blackjack;

drop user sergio@localhost;

grant insert, select, update, delete on blackjack.* to juego_blackjack;

select id_jugador,nombre,fecha_registro, sum(resultado='V') victorias, sum(resultado='E') empates, 
sum(resultado='D') derrotas, count (id_partida) partidas_totales
from jugadores join partidas using (id_jugador)
where nombre like 'pepe'
group by id_jugador;
