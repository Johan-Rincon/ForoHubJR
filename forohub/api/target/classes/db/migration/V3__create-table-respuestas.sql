create table respuestas(

    id bigint not null auto_increment,
    mensaje varchar(500) not null unique,
    id_tema bigint not null,
    fechaCreacion DATE not null,
    id_autor bigint not null,
    solucion boolean not null,

    primary key(id),
    foreign key(id_tema) references temas(id),
    foreign key(id_autor) references usuarios(id)

);