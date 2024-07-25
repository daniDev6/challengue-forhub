CREATE TABLE perfiles(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(255) not null,
    usuario_id bigint,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);