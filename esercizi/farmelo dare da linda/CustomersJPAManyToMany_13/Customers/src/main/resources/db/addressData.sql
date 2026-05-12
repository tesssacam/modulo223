insert into Address (id, street, num, zip, city, nation)
values (NEXT VALUE FOR address_seq, 'Piazza Guisan', '6', '6512', 'Giubiasco', 'Svizzera'),
       (NEXT VALUE FOR address_seq, 'Via Bellinzona', '10', '6743', 'Bodio', 'Svizzera'),
       (NEXT VALUE FOR address_seq, 'Viale Stefano Franscini', '23', '6500', 'Lugano', 'Svizzera');
