INSERT INTO order (id, order_date, total, status)
VALUES
    (NEXT VALUE FOR order_seq, '2026-05-19 12:00:00', 849.98, 'SHIPPED'),
    (NEXT VALUE FOR order_seq, '2026-05-18 17:30:00', 249.99, 'DELIVERED');