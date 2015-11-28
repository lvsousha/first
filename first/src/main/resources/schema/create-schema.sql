create table zcl_users(
	usr_id int NOT NULL IDENTITY PRIMARY KEY,
	usr_code varchar(64) NULL UNIQUE,
	usr_name varchar(64) NOT NULL,
	usr_password varchar(64) NOT NULL,
	usr_comment varchar(64) NOT NULL,
	usr_created datetime NOT NULL,
	usr_updated datetime NOT NULL
)
create table zcl_roles(
	rol_id int NOT NULL IDENTITY PRIMARY KEY,
	rol_user int NOT NULL,
	rol_code varchar(64) NULL UNIQUE,
	rol_name varchar(64) NULL,
	rol_comment varchar(64) NOT NULL,
	rol_created datetime NOT NULL,
	rol_updated datetime NOT NULL
	foreign key (rol_user) references zcl_users(usr_id)
)
