create shema mini_chat;

use mini_chat;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on mini_chat.* to user@'localhost';

create table usr_usuario (
    usr_id bigint unsigned not null auto_increment,
    usr_nickname varchar(20) not null,
    usr_email varchar(40) not null,
    primary key (usr_id),
    unique key uni_usuario_nick(usr_nickname)
);

create table msg_mensagem (
    msg_id bigint unsigned not null auto_increment,
    msg_description varchar(100) not null,
    usr_origin_id bigint unsigned not null,
    usr_destin_id bigint unsigned not null,
    primary key (msg_id),
    foreign key msg_usr_orign_fk (usr_origin_id) references usr_usuario(usr_id),
    foreign key msg_usr_destin_fk (usr_destin_id) references usr_usuario(usr_id)
);
