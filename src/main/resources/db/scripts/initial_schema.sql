create table users(
	id varchar(255) primary key,
	username varchar(20) not null,
	password varchar(20) not null,
	token varchar(40) not null
);


create table wallets(
	id varchar(255) primary key,
	init_balance double precision not null,
	user_id varchar(255) not null
);


create table wallet_transactions(
	id varchar(255) primary key,
	wallet_id varchar(255) not null,
	amount double precision not null,
    from_account varchar(255),
    created timestamp not null
);

