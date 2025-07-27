INSERT INTO users (username, email, password_hash)
VALUES
    ('alice', 'alice@example.com', '$2a$10$/MUTqR9eIGkKf9PiBKlU0.1xmSmChfiHMpZM3OnOPveHMsUaeBbum'),
    ('bob', 'bob@example.com', '$2a$10$mHjxIl9C2evN2DMMyS3dv.JGK1digObOi4.M1uMjXcIyoYq5.Noai'),
    ('carol', 'carol@example.com', '$2a$10$bewn2HbPtNK2DJfrTo/B/.ZJQ524X7CcTU75RvEo0CNj.WeRbBIXO');

INSERT INTO notes (title, content, latitude, longitude, image_url, user_id)
VALUES
    ('Первая заметка', 'Содержимое первой заметки', 55.75, 49.12, NULL, 1),
    ('Вторая заметка', 'Содержимое второй заметки', 55.80, 49.10, NULL, 1),
    ('Заметка Боба', 'Это заметка пользователя Bob', NULL, NULL, NULL, 2),
    ('Каролина заметка', 'Тут что-то интересное!', 55.78, 49.15, NULL, 3);
