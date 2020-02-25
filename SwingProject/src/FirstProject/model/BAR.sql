drop table table0;
drop table table1;
drop table table2;
drop table table3;
drop table table4;
drop table table5;
drop table snack;
drop SEQUENCE bar_seq;

create table snack(
num number primary key,
name varchar2(50),
price number,
stock number);

create SEQUENCE bar_seq
start with 1
INCREMENT by 1
nocache;

create table manager(
num number primary key,
count number,
money number
);
 
create table table0(
num number primary key,
name varchar2(50),
price number,
count number,
money number
); 
create table table1(
num number primary key,
name varchar2(50),
price number,
count number,
money number
);
create table table2(
num number primary key,
name varchar2(50),
price number,
count number,
money number
);
create table table3(
num number primary key,
name varchar2(50),
price number,
count number,
money number
);
create table table4(
num number primary key,
name varchar2(50),
price number,
count number,
money number
);
create table table5(
num number primary key,
name varchar2(50),
price number,
count number,
money number
);

delete from snack;
delete from manager;
delete from table1;
delete from table2;
delete from table3;
delete from table4;
delete from table5;
insert into snack VALUES (bar_seq.nextval,'º“¡÷',4000,100);
insert into snack VALUES (bar_seq.nextval,'∏∆¡÷ 500cc',3500,100);
insert into snack VALUES (bar_seq.nextval,'∏∆¡÷ 1700cc',9500,100);
insert into snack VALUES (bar_seq.nextval,'∏∆¡÷ 3000cc',17000,100);
insert into snack VALUES (bar_seq.nextval,'ƒ›∂Û',2500,100);
insert into snack VALUES (bar_seq.nextval,'ªÁ¿Ã¥Ÿ',2500,100);
insert into snack VALUES (bar_seq.nextval,'∞Ë∂ı∏ª¿Ã',4500,50);
insert into snack VALUES (bar_seq.nextval,'µŒ∫Œ±Ëƒ°',4500,50);
insert into snack VALUES (bar_seq.nextval,'ø¿µ≠≈¡',10000,50);  