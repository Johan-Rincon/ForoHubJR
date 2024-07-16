create table temas(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(500) not null unique,
    fechaCreacion DATE not null,
    estado varchar(50) not null,
    id_autor bigint not null,

    primary key(id),
    foreign key(id_autor) references usuarios(id)

);