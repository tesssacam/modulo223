insert into Customer (id, name, surname, age, address_id)
values (NEXT VALUE FOR customer_seq, 'Mario', 'Rossi', 24, (
            select distinct id from address where street = 'Piazza Guisan' and num = '6' and zip = '6512' and city = 'Giubiasco' and nation = 'Svizzera'
       )),
       (NEXT VALUE FOR customer_seq, 'Guido', 'Bianchi', 34, (
           select distinct id from address where street = 'Via Bellinzona' and num = '10' and zip = '6743' and city = 'Bodio' and nation = 'Svizzera'
       )),
       (NEXT VALUE FOR customer_seq, 'Gino', 'Verdi', 57, (
           select distinct id from address where street = 'Viale Stefano Franscini' and num = '23' and zip = '6500' and city = 'Lugano' and nation = 'Svizzera'
       )
       );
