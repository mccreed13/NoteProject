create table if not exists role (
    id identity,
    role varchar(30),
    primary key(id),
    check (role in('USER','ADMIN'))
    );

create table if not exists person (
    id identity,
    username varchar_ignorecase(50),
    passwoard varchar_ignorecase(250),
    role_id varchar(30) not null,
    primary key(id),
    foreign key (role_id) references role(id),
    check (length(username) >= 5 and length(username) <= 50)
    );

create table if not exists note (
    id identity,
    title varchar_ignorecase(200),
    content text,
    access varchar(30),
    person_id bigint not null,
    primary key(id),
    check (access in('PUBLIC','PRIVATE')),
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