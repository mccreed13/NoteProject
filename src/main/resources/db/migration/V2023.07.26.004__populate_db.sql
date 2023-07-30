insert into role(role)
values
('USER'),
('ADMIN');

insert into "user"(role_id,username,password)
select r.id, 'admin', '$2a$10$CnGe7GA.rpCYmj0trqMKVe30AmkVB3jpmsNjgBG1Po1lPvL201msy'
from role r where r.role='ADMIN';

insert into user_role(user_id,role_id)
select p.id, r.id
from role r
inner join "user" p on p.username = 'admin';

insert into "user"(role_id,username,password)
select r.id, 'user1', 'testPassword'
from role r where r.role='USER';

insert into user_role(user_id,role_id)
select p.id, r.id
from role r
inner join "user" p on p.username = 'user1';


