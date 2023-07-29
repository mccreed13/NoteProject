insert into role(role)
values
('USER'),
('ADMIN');

insert into "user"(role_id,username,password)
select r.id, 'admin', '$2a$10$pwEm25k7EQ42db6pExjdxuUOWCBKX3WS0hDsQkGcGKELRPu.mkNdS'
from role r where r.role='ADMIN';

insert into user_role(user_id,role_id)
select p.id, r.id
from role r
inner join "user" p on p.username = 'admin'


