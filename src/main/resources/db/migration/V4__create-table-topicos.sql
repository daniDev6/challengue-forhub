CREATE TABLE topicos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_creacion timestamp not null,
    status boolean,
    titulo  varchar(255),
    curso_id bigint,
    usuario_id bigint,
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos(id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);