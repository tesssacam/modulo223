INSERT INTO game_profile (id, username, livello, punti, data_creazione, player_id)
VALUES
    (NEXT VALUE FOR profile_seq, 'LucaPro10', 5, 1200, '2025-01-10', 1),
    (NEXT VALUE FOR profile_seq, 'MarcoDef', 3, 800, '2025-02-12', 2),
    (NEXT VALUE FOR profile_seq, 'AndrePlay', 7, 2000, '2025-03-20', 3);