CREATE DATABASE barbermanager;

\c barbermanager;

CREATE TABLE clientes (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

CREATE TABLE barberos (
    id_barbero SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100)
);

CREATE TABLE servicios (
    id_servicio SERIAL PRIMARY KEY,
    nombre_servicio VARCHAR(100) NOT NULL,
    precio NUMERIC(10,2) NOT NULL
);

CREATE TABLE citas (
    id_cita SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    id_cliente INT,
    id_barbero INT,
    id_servicio INT,

    CONSTRAINT fk_cliente
        FOREIGN KEY(id_cliente)
        REFERENCES clientes(id_cliente),

    CONSTRAINT fk_barbero
        FOREIGN KEY(id_barbero)
        REFERENCES barberos(id_barbero),

    CONSTRAINT fk_servicio
        FOREIGN KEY(id_servicio)
        REFERENCES servicios(id_servicio)
);

INSERT INTO clientes(nombre, telefono, correo) VALUES
('Juan Perez', '3001234567', 'juan@gmail.com'),
('Carlos Lopez', '3015552211', 'carlos@gmail.com');

INSERT INTO barberos(nombre, especialidad) VALUES
('Andres Gomez', 'Fade'),
('Luis Martinez', 'Barba');

INSERT INTO servicios(nombre_servicio, precio) VALUES
('Corte Clasico', 25000),
('Corte + Barba', 40000);

INSERT INTO citas(fecha, hora, id_cliente, id_barbero, id_servicio) VALUES
('2026-05-15', '14:00:00', 1, 1, 1),
('2026-05-16', '16:30:00', 2, 2, 2);
