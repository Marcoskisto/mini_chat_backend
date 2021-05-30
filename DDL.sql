
drop database mini_chat;
create schema mini_chat;
use mini_chat;
drop user user;
create user user identified by 'pass123';

grant select, insert, delete, update on mini_chat.* to 'user'@'localhost';

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
    usr_senha varchar(100) not null, 
    primary key (usr_id),
    unique key uni_usuario_nick(usr_nickname)
);

create table crd_credencial(
	crd_id bigint unsigned not null auto_increment,
    crd_nome varchar (20) not null,    
    primary key (crd_id),
    unique key uni_crd_nome (crd_nome)
);

create table cuc_credencial_usuario(
	cuc_usr_id bigint unsigned not null,
    cuc_crd_id bigint unsigned not null,
    primary key (cuc_usr_id, cuc_crd_id),
    foreign key cuc_usr_id_fk (cuc_usr_id) references usr_usuario(usr_id),
    foreign key cuc_crd_id_fk (cuc_crd_id) references crd_credencial(crd_id)
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
insert into usr_usuario (usr_nickname, usr_email, usr_senha) values("joselito", "jose@teste.com", "$2y$12$GkqdLqBvtvnWR3xh50aQWOloIG5VRHBLVwOdMHmfwYUxLiokjiCta");
insert into usr_usuario (usr_nickname, usr_email, usr_senha) values("maria", "maria@teste.com", "$2y$12$GkqdLqBvtvnWR3xh50aQWOloIG5VRHBLVwOdMHmfwYUxLiokjiCta");
insert into usr_usuario (usr_nickname, usr_email, usr_senha) values("Bot", "bot@minichat.com.br", "$2y$12$GkqdLqBvtvnWR3xh50aQWOloIG5VRHBLVwOdMHmfwYUxLiokjiCta");
insert into crd_credencial (crd_nome) values("ROLE_ADMIN");
insert into crd_credencial (crd_nome) values("ROLE_DEFAULT");
insert into cuc_credencial_usuario (cuc_usr_id, cuc_crd_id) values ( 1, 1);
insert into cuc_credencial_usuario (cuc_usr_id, cuc_crd_id) values ( 2, 2);
insert into cuc_credencial_usuario (cuc_usr_id, cuc_crd_id) values ( 3, 2); 
insert into ucu_conversa_usuario values( 1, 1);
insert into ucu_conversa_usuario values( 2, 1);
insert into msg_mensagem (msg_description, msg_origin_id, msg_conversa_id) values ("ola_maria", 1, 1);
insert into msg_mensagem (msg_description, msg_origin_id, msg_conversa_id) values ("ola_joao", 2, 1);
