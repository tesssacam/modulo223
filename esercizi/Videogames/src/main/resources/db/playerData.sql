INSERT INTO Player (id, nome, cognome, eta, ruolo, numero_maglia, team_id)
VALUES
    (NEXT VALUE FOR player_seq, 'Luca', 'Bianchi', 22, 'attaccante', 10, 1),
    (NEXT VALUE FOR player_seq, 'Marco', 'Rossi', 25, 'difensore', 4, 2),
    (NEXT VALUE FOR player_seq, 'Andrea', 'Conti', 19, 'centrocampista', 8, 3);