insert into role(role)
values
('USER'),
('ADMIN');

insert into person(role_id,username,password)
select r.id, 'admin', 'super_secret_password'
from role r where r.role='ADMIN';

insert into person_role(person_id,role_id)
select p.id, r.id
from role r
inner join person p on p.username = 'admin'
where r.role='ADMIN';