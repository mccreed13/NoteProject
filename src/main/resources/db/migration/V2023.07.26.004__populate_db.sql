insert into role(role)
values
('USER'),
('ADMIN');

insert into person(role_id,username,passwoard)
select r.id, 'admin', '$2a$04$MJ64V2eJXjaWdaUb/eD5vO4CVT2UJvZMJvr31hAq.xyMPmVCG1t9u'
from role r where r.role='ADMIN';

insert into person_role(person_id,role_id)
select p.id, r.id
from role r
inner join person p on p.username = 'admin'
where r.role='ADMIN';