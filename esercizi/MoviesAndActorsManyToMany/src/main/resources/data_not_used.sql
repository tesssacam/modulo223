delete from Director;
insert into Director (id, name, surname) values
    (NEXT VALUE FOR director_seq, 'Federico', 'Fellini'),
    (NEXT VALUE FOR director_seq, 'Sergio', 'Leone'),
    (NEXT VALUE FOR director_seq, 'Giancarlo', 'Pieraccioni');


delete from Movie;
insert into Movie (id, title, description, director_id) values
    (NEXT VALUE FOR movie_seq, 'La dolce vita', 'gente che viene e che va...', (select distinct id from Director where name = 'Federico' and surname = 'Fellini')),
    (NEXT VALUE FOR movie_seq, 'Il buono, il brutto e il cattivo', 'gente che spara...', (select distinct id from Director where name = 'Sergio' and surname = 'Leone')),
    (NEXT VALUE FOR movie_seq, 'i laureati', 'ehm...', (select distinct id from Director where name = 'Giancarlo' and surname = 'Pieraccioni'));


delete from Actor;
insert into Actor (id, name, surname) values
    (NEXT VALUE FOR actor_seq, 'Clint', 'Eastwood'),
    (NEXT VALUE FOR actor_seq, 'Lee', 'Van Cleef'),
    (NEXT VALUE FOR actor_seq, 'Eli', 'Wallach'),
    (NEXT VALUE FOR actor_seq, 'Rocco', 'Papaleo'),
    (NEXT VALUE FOR actor_seq, 'Marcello', 'Mastroianni'),
    (NEXT VALUE FOR actor_seq, 'Sofia', 'Loren'),
    (NEXT VALUE FOR actor_seq, 'Maria Grazia', 'Cucinotta');