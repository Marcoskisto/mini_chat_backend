

create schema mini_chat;
use mini_chat;

create user user identified by 'pass123';

grant select, insert, delete, update on mini_chat.* to user@'localhost';



create table cnv_conversa ( 
	cnv_id bigint unsigned not null auto_increment,
    cnv_titulo varchar(20) not null,
    primary key (cnv_id),
    unique key uni_cnv_titulo(cnv_titulo)
);

create table usr_usuario (
    usr_id bigint unsigned not null auto_increment,
    usr_nickname varchar(20) not null,
    usr_email varchar(40) not null,
    primary key (usr_id),
    unique key uni_usuario_nick(usr_nickname)
);

create table ucu_conversa_usuario (
	ucu_usr_id bigint unsigned not null,
    ucu_cnv_id bigint unsigned not null,
    primary key (ucu_usr_id, ucu_cnv_id),
    foreign key usr_id_fk (ucu_usr_id) references usr_usuario(usr_id),
    foreign key cnv_id_fk (ucu_cnv_id) references cnv_conversa(cnv_id)
);

create table msg_mensagem (
    msg_id bigint unsigned not null auto_increment,
    msg_description varchar(100) not null,
    msg_origin_id bigint unsigned not null,
    msg_conversa_id bigint unsigned,
    primary key (msg_id),
    foreign key msg_usr_orign_fk (msg_origin_id) references usr_usuario(usr_id),
    foreign key msg_cnv_fk (msg_conversa_id) references cnv_conversa(cnv_id)
);

insert into cnv_conversa (cnv_titulo) values("grupo_A");
insert into usr_usuario (usr_nickname, usr_email) values("joselito", "jose@teste.com");
insert into usr_usuario (usr_nickname, usr_email) values("maria", "maria@teste.com");
insert into ucu_conversa_usuario values( 1, 1);
insert into ucu_conversa_usuario values( 2, 1);
insert into msg_mensagem (msg_description, msg_origin_id, msg_conversa_id) values ("ola_maria", 1, 1);
insert into msg_mensagem (msg_description, msg_origin_id, msg_conversa_id) values ("ola_joao", 2, 1);
