INSERT INTO product (id, name, description, price, quantity, available, image_url, category_id)
VALUES
    (NEXT VALUE FOR product_seq, 'AGV K1 Helmet', 'Casco integrale sportivo', 249.99, 15, true, '/immagini/prodotti/agv_k1.jpg', 1),

    (NEXT VALUE FOR product_seq, 'Shoei NXR2 Helmet', 'Casco premium leggero e sicuro', 499.99, 8, true, '/immagini/prodotti/shoei_nxr2.jpg', 1),

    (NEXT VALUE FOR product_seq, 'Dainese Racing Jacket', 'Giacca protettiva in pelle', 599.99, 10, true, '/immagini/prodotti/dainese_jacket.jpg', 2),

    (NEXT VALUE FOR product_seq, 'Alpinestars Gloves', 'Guanti da moto rinforzati', 89.99, 25, true, '/immagini/prodotti/quanti_1.jpg', 3),

    (NEXT VALUE FOR product_seq, 'Brembo Brake Disc', 'Disco freno ad alte prestazioni', 179.99, 12, true, '/immagini/prodotti/brembo.jpg', 4),

    (NEXT VALUE FOR product_seq, 'Givi Tank Bag', 'Borsa serbatoio magnetica', 69.99, 30, true, '/immagini/prodotti/givi_bag.jpg', 5);