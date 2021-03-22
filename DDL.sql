create schema mini_chat;

use mini_chat;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on mini_chat.* to user@'localhost';

create table grp_grupo ( 
	grp_id bigint unsigned not null auto_increment,
    grp_titulo varchar(20) not null,
    grp_descricao varchar(50),
    primary key I(grp_id),
    unique key uni_grupo_titulo(grp_titulo)
);



create table usr_usuario (
    usr_id bigint unsigned not null auto_increment,
    usr_nickname varchar(20) not null,
    usr_email varchar(40) not null,
    primary key (usr_id),
    unique key uni_usuario_nick(usr_nickname)
);

create table ugu_grupo_usuario (
	usr_id bigint unsigned not null,
    grp_id bigint unsigned not null,
    primary key (usr_id, grp_id),
    foreign key usr_id_fk (usr_id) references usr_usuario(usr_id),
    foreign key grp_id_fk (grp_id) references grp_grupo(grp_id)
);

create table msg_mensagem (
    msg_id bigint unsigned not null auto_increment,
    msg_description varchar(100) not null,
    usr_origin_id bigint unsigned not null,
    usr_destin_id bigint unsigned,
    grp_destin_id bigint unsigned,
    primary key (msg_id),
    foreign key msg_usr_orign_fk (usr_origin_id) references usr_usuario(usr_id),
    foreign key msg_usr_destin_fk (usr_destin_id) references usr_usuario(usr_id),
    foreign key msg_grp_fk (grp_destin_id) references grp_grupo(grp_id)
);