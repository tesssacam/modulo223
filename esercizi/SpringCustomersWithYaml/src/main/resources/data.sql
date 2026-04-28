delete from Customer;
insert into Customer (id, name, surname, age, city,ccnumber,ccexpiration,cccvv)
values (NEXT VALUE FOR customer_seq, 'Mario', 'Rossi', 25,'lugano','1111222233334444','02/29', 123),
       (NEXT VALUE FOR customer_seq, 'Guido', 'Bianchi', 34,'locarno','1111333355557777','12/26', 456),
       (NEXT VALUE FOR customer_seq, 'Gino', 'Verdi', 57,'bellinzona','5555666677778888','07/27',  789);