insert into Reservation (id, room, checkin, checkout, customer_id)
values
    (
        NEXT VALUE FOR reservation_seq, '437', '23.12.2024', '06.01.2025', (
        select distinct id from customer where name = 'Mario' and surname = 'Rossi'
    )
    ),
    (   NEXT VALUE FOR reservation_seq, '124', '01.08.2025', '15.08.2025', (
        select distinct id from customer where name = 'Mario' and surname = 'Rossi'
    )
    ),
    (
        NEXT VALUE FOR reservation_seq, '437', '27.12.2024', '02.01.2025', (
        select distinct id from customer where name = 'Guido' and surname = 'Bianchi'
    ));
