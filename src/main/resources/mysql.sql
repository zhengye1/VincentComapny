create table if not exists USER(
id BIGINT NOT NULL AUTO_INCREMENT,
userid varchar(30) NOT NULL,
password VARCHAR(100) NOT NULL,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
email varchar(100) NOT NULL,
primary key(id),
UNIQUE(userid))

insert into user(userid, password, first_name, last_name, email) 
values('alice', '$2a$10$/fGLATiV8STMQ97sD4d6rOc7Z/fscO00JyvJGj.QhIuGvR98AS9nW', 'Alice', 'Lin', 'alice.lin@vincentCom.com');

create table if not exists PROFILE(
id BIGINT NOT NULL AUTO_INCREMENT,
name varchar(30) not null,
primary key(id),
unique(name));

insert into PROFILE(name)
values('ADMIN'),
('USER');

/* Join table for many to many relationship */
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE if not exists USER_PROFILE (
    userid BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    PRIMARY KEY (userid, profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (userid) REFERENCES USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (profile_id) REFERENCES PROFILE (id)
);

insert into USER_PROFILE(userid, profile_id)
select user.id, profile.id from user, profile where user.userid='alice' and profile.name='ADMIN';