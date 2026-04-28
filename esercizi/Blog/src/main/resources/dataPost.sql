delete from Post;
insert into Post (id, title, publicationDate, category, author,likenumber,content)
values (NEXT VALUE FOR post_seq, 'Tessa', '02/27', 'Sport', 'Rivera', 155, 'Contenuto articolo tessa'),
       (NEXT VALUE FOR post_seq, 'Linda', '03/26', 'Tech', 'Viganello', 355, 'Contenuto articolo linda'),
       (NEXT VALUE FOR post_seq, 'Bryan', '01/40', 'News', 'Claro', 23, 'Contenuto articolo bryan');