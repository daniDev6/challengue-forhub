CREATE TABLE respuestas(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje varchar(255) not null,
    fecha_creacion timestamp not null,
    usuario_id bigint,
    topico_id bigint,
    solucion varchar(255),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos(id)
);