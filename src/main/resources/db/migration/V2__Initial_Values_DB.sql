INSERT INTO local(name) VALUES ('English'), ('Русский');

INSERT INTO role(name) VALUES ('USER'), ('ADMIN');

INSERT INTO status(id, local_id, name)
VALUES
    (1, 1, 'New'), (1, 2, 'Новый'),
    (2, 1, 'Edited'), (2, 2, 'Измененный'),
    (3, 1, 'Hidden'), (3, 2, 'Скрытый');