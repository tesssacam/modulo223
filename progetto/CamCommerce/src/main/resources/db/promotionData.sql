INSERT INTO promotion (id, name, description, discount_percent, start_date, end_date)
VALUES
    (NEXT VALUE FOR promotion_seq, 'Moto Summer Sale', 'Sconti estivi accessori moto', 20, '2026-06-01', '2026-06-30'),

    (NEXT VALUE FOR promotion_seq, 'Safety Week', 'Sconti su caschi e protezioni', 25, '2026-05-01', '2026-05-15');