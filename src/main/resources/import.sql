INSERT INTO rol(id_rol, tx_descripcion, is_habilitado, tx_value) VALUES('1e7a4288-2ff8-4077-84e7-6d9adb75c0c1'::uuid, 'ROLE_ADMIN', true, 'Administrador');
INSERT INTO rol(id_rol, tx_descripcion, is_habilitado, tx_value) VALUES('0a08a32a-f1b7-4e24-ac21-e6fb19cde117'::uuid, 'ROLE_ENCARGADO', true, 'Encargado');
INSERT INTO rol(id_rol, tx_descripcion, is_habilitado, tx_value) VALUES('0a08a32a-f1b7-4e24-ac21-e6fb19cde118'::uuid, 'ROLE_USUARIO', true, 'Visitante');

INSERT INTO usuario(id_usuario, tx_username, tx_password, tx_nombre, tx_apellido, tx_telefono ,dt_registro, is_habilitado) VALUES('747f8f82-f915-45e9-92b6-5dfcc7bc435c'::uuid, 'master_registry', '$2a$12$7kkjkcSUaQsdJzASrIBx.unMnkDQ9KkesO4KAH74cF3OQPM1yo8cm', 'Iglesia', 'Ibet', '123456789', now(), true);

INSERT INTO usuarios_roles(id_rol, id_usuario) VALUES('1e7a4288-2ff8-4077-84e7-6d9adb75c0c1'::uuid, '747f8f82-f915-45e9-92b6-5dfcc7bc435c'::uuid);