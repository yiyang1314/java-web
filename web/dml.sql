insert into move_booking(id, area, cartype, movedate, contact, phone, status)values(move_seq.nextval, '朝阳区','1041货车' , to_date('2011-03-06','yyyy-MM-dd'), '复议', '13978692345', '0');
insert into move_booking(id, area, cartype, movedate, contact, phone, status)values(move_seq.nextval, '大兴区','箱式货车' , to_date('2011-03-06','yyyy-MM-dd'), '王林', '13978692345', '1');
insert into move_booking(id, area, cartype, movedate, contact, phone, status)values(move_seq.nextval, '东城区', '皮卡', to_date('2011-03-06','yyyy-MM-dd'), '李峰', '13978692345', '2');
insert into users(id, name, password)values(users_seq.nextval, 'zhangsan', '123456');
insert into users(id, name, password)values(users_seq.nextval, 'lisi', '123456');
insert into users(id, name, password)values(users_seq.nextval, 'wanger', '123456');
commit;
--create sequence move_seq increment by 2 start with 1110 ;
--create sequence users_seq increment by 2 start with 1110;

select id, area, cartype, movedate, contact, phone, status from move_booking
update move_booking set area =?,cartype =?,movedate =?,contact = ?,phone =?,status =? where id =?;
delete move_booking where id = v_id;
insert into move_booking(id, area, cartype, movedate, contact, phone, status)values(v_id, v_area, v_cartype, v_movedate, v_contact, v_phone, v_status);

update users set id = v_id,name = v_name,password = v_password where id = v_id;
delete users where id = v_id;
select id, name, password from users
insert into users (id, name, password)values(v_id, v_name, v_password);
		
select id, area, cartype, movedate, contact, phone, status from move_booking 