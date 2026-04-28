delete from Item;
insert into Item (id, code, type, name, price, itemcount)
values (NEXT VALUE FOR item_seq, '469375', 'attrezzo', 'pala da giardino',24.9,2),
       (NEXT VALUE FOR item_seq, '847209', 'pianta', 'lavanda in vaso',12.5,16),
       (NEXT VALUE FOR item_seq, '143546', 'accessorio','annaffiatoio 5 litri',18.0,4)