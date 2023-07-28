insert into role(role)
values
('USER'),
('ADMIN');

insert into "user"(role_id,username,password)
select r.id, 'admin', 'super_secret_password'
from role r where r.role='ADMIN';

insert into user_role(user_id,role_id)
select p.id, r.id
from role r
inner join "user" p on p.username = 'admin'


