create table tarefa (

    id int not null auto_increment primary key,
    nome varchar(100) not null,
    descricao varchar (100) not null,
    id_usuario int, 

    FOREIGN KEY (id_usuario) REFERENCES usuario(id);
);