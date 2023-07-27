create table if not exists role (
    id serial primary key,
    role varchar(30) check (role in ('USER', 'ADMIN'))
);

create table if not exists person (
    id serial primary key,
    username varchar(50) unique,
    password varchar(250) unique,
    role_id integer not null,
    primary key(id),
    foreign key (role_id) references role(id),
    check (length(username) >= 5 and length(username) <= 50)
);

create table if not exists note (
    id serial primary key,
    title varchar(200),
    content text,
    access varchar(30) check (access in ('PUBLIC', 'PRIVATE')),
    person_id bigint not null,
    foreign key (person_id) references person(id)
);

create table if not exists person_role (
    person_id int not null,
    role_id int not null,
    primary key (person_id, role_id),
    foreign key (person_id) references person(id),
    foreign key (role_id) references role(id)
);

create unique index person_index on person(id, username);
create unique index note_index on note(id);