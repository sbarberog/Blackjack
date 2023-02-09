-- Active: 1675880132561@@127.0.0.1@3306@blackjack
create user juego_blackjack IDENTIFIED by 'blackjack';

show grants for juego_blackjack;

drop user sergio@localhost;

grant insert, select, update, delete on blackjack.* to juego_blackjack;

select id_jugador,nombre,fecha_inicial, sum(resultado='V') victorias, sum(resultado='E') empates, 
sum(resultado='D') derrotas, count (id_partida) partidas_totales
from jugadores join partidas using (id_jugador)
where nombre like 'sergio'
group by id_jugador;

