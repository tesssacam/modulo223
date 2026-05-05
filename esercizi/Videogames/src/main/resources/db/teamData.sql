INSERT INTO Team (id, nome, citta, data_fondazione)
VALUES
    (NEXT VALUE FOR team_seq, 'FC Lugano', 'Lugano', '1908-01-01'),
    (NEXT VALUE FOR team_seq, 'AC Bellinzona', 'Bellinzona', '1904-01-01'),
    (NEXT VALUE FOR team_seq, 'FC Basel', 'Basel', '1893-11-15');