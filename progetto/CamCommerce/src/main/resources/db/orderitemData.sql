INSERT INTO order_item (id, quantity, price, order_id, product_id)
VALUES
    (NEXT VALUE FOR order_item_seq, 1, 599.99, 1, 3),
    (NEXT VALUE FOR order_item_seq, 1, 249.99, 2, 1);