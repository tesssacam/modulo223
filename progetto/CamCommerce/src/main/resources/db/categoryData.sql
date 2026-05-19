INSERT INTO category (id, name, description)
VALUES
    (NEXT VALUE FOR category_seq, 'Helmets', 'Caschi da moto'),
    (NEXT VALUE FOR category_seq, 'Jackets', 'Giacche protettive'),
    (NEXT VALUE FOR category_seq, 'Gloves', 'Guanti da moto'),
    (NEXT VALUE FOR category_seq, 'Parts', 'Ricambi moto'),
    (NEXT VALUE FOR category_seq, 'Accessories', 'Accessori moto');