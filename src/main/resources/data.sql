-- Tipos de estudio
INSERT INTO study_type (id, name) VALUES (1, 'Mamografía Digital');
INSERT INTO study_type (id, name) VALUES (2, 'Resonancia Magnética');
INSERT INTO study_type (id, name) VALUES (3, 'Tomografía Multicorte');

-- Médicos (3 por estudio 1)
INSERT INTO doctor (id, first_name, last_name, study_type_id, photo_url)
VALUES
    (1, 'Ricardo', 'Gómez', 1, null),
    (2, 'Marcelo', 'Acosta', 1, null),
    (3, 'Sofía', 'Pereyra', 1, null);

-- pacientes de prueba
INSERT INTO patient (first_name, last_name, insurance_name, document_number) VALUES
                                                                                 ('Ana María', 'Giménez Sosa', 'OSDE', '30111222'),
                                                                                 ('Juan', 'Pérez', 'OSDE', '20123456'),
                                                                                 ('Lucía', 'Rodríguez', 'Swiss Medical', '27999888'),
                                                                                 ('Carlos', 'López', 'OSDE', '23111222'),
                                                                                 ('María', 'García', 'OSDE', '25987654'),
                                                                                 ('Pedro', 'Fernández', 'Galeno', '26123456'),
                                                                                 ('Laura', 'Martínez', 'OSDE', '27876543'),
                                                                                 ('Santiago', 'Ruiz', 'OSDE', '24987654'),
                                                                                 ('Paula', 'Sosa', 'OSDE', '25994433'),
                                                                                 ('Diego', 'Torres', 'OSDE', '23123456'),
                                                                                 ('Carla', 'Rojas', 'OSDE', '22111222'),
                                                                                 ('Miguel', 'Vega', 'OSDE', '20111111'),
                                                                                 ('Noelia', 'Suárez', 'OSDE', '27123456'),
                                                                                 ('Esteban', 'Corona', 'OSDE', '26111222'),
                                                                                 ('Rocío', 'Luna', 'OSDE', '25123455'),
                                                                                 ('Tomás', 'Ibarra', 'OSDE', '28123456'),
                                                                                 ('Valeria', 'Molina', 'OSDE', '24123456'),
                                                                                 ('Nicolás', 'Castro', 'OSDE', '29123456'),
                                                                                 ('Agustina', 'Figueroa', 'OSDE', '30123456'),
                                                                                 ('Bruno', 'Salas', 'OSDE', '31123456'),
                                                                                 ('Sofía', 'Lopez', 'OSDE', '32123456'),
                                                                                 ('Gustavo', 'Paz', 'OSDE', '33123456'),
                                                                                 ('Florencia', 'Mendez', 'OSDE', '34123456'),
                                                                                 ('Hernán', 'Acosta', 'OSDE', '35123456'),
                                                                                 ('Belén', 'Ramos', 'OSDE', '36123456'),
                                                                                 ('Franco', 'Silva', 'OSDE', '37123456'),
                                                                                 ('Daiana', 'Ponce', 'OSDE', '38123456'),
                                                                                 ('Luciano', 'Ayala', 'OSDE', '39123456'),
                                                                                 ('Milagros', 'Nuñez', 'OSDE', '40123456'),
                                                                                 ('Ezequiel', 'Peralta', 'OSDE', '41123456');

INSERT INTO appointment (doctor_id, patient_id, study_type_id, start_datetime, end_datetime) VALUES
                                                                                                 (1, 1, 1, '2025-10-15 09:00:00', '2025-10-15 10:00:00'),
                                                                                                 (1, 2, 1, '2025-10-15 10:00:00', '2025-10-15 11:00:00'),
                                                                                                 (1, 3, 1, '2025-10-15 11:00:00', '2025-10-15 12:00:00'),
                                                                                                 (1, 4, 1, '2025-10-15 12:00:00', '2025-10-15 13:00:00'),
                                                                                                 (1, 5, 1, '2025-10-15 13:00:00', '2025-10-15 14:00:00'),
                                                                                                 (1, 6, 1, '2025-10-15 14:00:00', '2025-10-15 15:00:00'),
                                                                                                 (1, 7, 1, '2025-10-15 15:00:00', '2025-10-15 16:00:00'),
                                                                                                 (1, 8, 1, '2025-10-15 16:00:00', '2025-10-15 17:00:00'),
                                                                                                 (1, 9, 1, '2025-10-16 09:00:00', '2025-10-16 10:00:00'),
                                                                                                 (1, 10, 1, '2025-10-16 10:00:00', '2025-10-16 11:00:00');

-- Doctor 2 (id 2)
INSERT INTO appointment (doctor_id, patient_id, study_type_id, start_datetime, end_datetime) VALUES
                                                                                                 (2, 11, 1, '2025-10-15 09:00:00', '2025-10-15 10:00:00'),
                                                                                                 (2, 12, 1, '2025-10-15 10:00:00', '2025-10-15 11:00:00'),
                                                                                                 (2, 13, 1, '2025-10-15 11:00:00', '2025-10-15 12:00:00'),
                                                                                                 (2, 14, 1, '2025-10-15 12:00:00', '2025-10-15 13:00:00'),
                                                                                                 (2, 15, 1, '2025-10-15 13:00:00', '2025-10-15 14:00:00'),
                                                                                                 (2, 16, 1, '2025-10-15 14:00:00', '2025-10-15 15:00:00'),
                                                                                                 (2, 17, 1, '2025-10-15 15:00:00', '2025-10-15 16:00:00'),
                                                                                                 (2, 18, 1, '2025-10-15 16:00:00', '2025-10-15 17:00:00'),
                                                                                                 (2, 19, 1, '2025-10-16 09:00:00', '2025-10-16 10:00:00'),
                                                                                                 (2, 20, 1, '2025-10-16 10:00:00', '2025-10-16 11:00:00');

-- Doctor 3 (id 3)
INSERT INTO appointment (doctor_id, patient_id, study_type_id, start_datetime, end_datetime) VALUES
                                                                                                 (3, 21, 1, '2025-10-15 09:00:00', '2025-10-15 10:00:00'),
                                                                                                 (3, 22, 1, '2025-10-15 10:00:00', '2025-10-15 11:00:00'),
                                                                                                 (3, 23, 1, '2025-10-15 11:00:00', '2025-10-15 12:00:00'),
                                                                                                 (3, 24, 1, '2025-10-15 12:00:00', '2025-10-15 13:00:00'),
                                                                                                 (3, 25, 1, '2025-10-15 13:00:00', '2025-10-15 14:00:00'),
                                                                                                 (3, 26, 1, '2025-10-15 14:00:00', '2025-10-15 15:00:00'),
                                                                                                 (3, 27, 1, '2025-10-15 15:00:00', '2025-10-15 16:00:00'),
                                                                                                 (3, 28, 1, '2025-10-15 16:00:00', '2025-10-15 17:00:00'),
                                                                                                 (3, 29, 1, '2025-10-16 09:00:00', '2025-10-16 10:00:00'),
                                                                                                 (3, 30, 1, '2025-10-16 10:00:00', '2025-10-16 11:00:00');