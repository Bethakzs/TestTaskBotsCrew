-- Вставка даних у таблицю departments
INSERT INTO departments (id, name, head_id) VALUES (1, 'Computer Science', NULL);
INSERT INTO departments (id, name, head_id) VALUES (2, 'Mathematics', NULL);

-- Вставка даних у таблицю lectors
INSERT INTO lectors (id, name, surname, degree, department_id, salary) VALUES (1, 'Ivan', 'Ivanov', 'ASSISTANT', 1, 5000.00);
INSERT INTO lectors (id, name, surname, degree, department_id, salary) VALUES (2, 'Olga', 'Petrova', 'ASSOCIATE_PROFESSOR', 1, 6000.00);
INSERT INTO lectors (id, name, surname, degree, department_id, salary) VALUES (3, 'Serhiy', 'Kovalenko', 'PROFESSOR', 2, 7000.00);
INSERT INTO lectors (id, name, surname, degree, department_id, salary) VALUES (4, 'Natalia', 'Semenova', 'ASSISTANT', 2, 5500.00);

-- Оновлення значення поля head_id в таблиці departments
UPDATE departments SET head_id = 1 WHERE id = 1;
UPDATE departments SET head_id = 3 WHERE id = 2;

-- Оновлення послідовностей
ALTER SEQUENCE departments_id_seq RESTART WITH 3;
ALTER SEQUENCE lectors_id_seq RESTART WITH 5;

-- Who is head of department Computer Science
