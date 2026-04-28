delete from Post;
insert into Post (id, title, publicationDate, category, author,likenumber,content)
values (NEXT VALUE FOR post_seq, 'Mario', '02/29', 'Sport', 'Lugano', 123, 'Contenuto articolo Mario'),
       (NEXT VALUE FOR post_seq, 'Guido', '03/27', 'Tech', 'Locarno', 456, 'Contenuto articolo Guido'),
       (NEXT VALUE FOR post_seq, 'Gino', '01/30', 'News', 'Bellinzona', 789, 'Contenuto articolo Gino');