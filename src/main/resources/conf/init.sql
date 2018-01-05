TRUNCATE TABLE users;
insert into users(name, phone, email, status) values("Tom", "13987878787", "Tom@test.com", 1);
insert into users(name, phone, email, status) values("Jack", "13987878789", "Jack@test.com", 1);
insert into users(name, phone, email, status) values("Marry", "13987878788", "Marry@test.com", 1);
insert into users(name, phone, email, status) values("Jim", "13987878786", "Jim@test.com", 1);
insert into users(name, phone, email, status) values("Tony", "13987878785", "Tony@test.com", 0);
insert into users(name, phone, email, status) values("Paul", "13987878784", "Paul@test.com", 0);
insert into users(name, phone, email, status) values("Ama", "13987878783", "Ama@test.com", 0);

TRUNCATE TABLE roles;
insert into roles(name, description, status) values("admin", "admin", 1);
insert into roles(name, description, status) values("manager", "manager", 1);
insert into roles(name, description, status) values("general", "general", 0);

TRUNCATE TABLE menus;
insert into menus(name, level, description, status) values("admin", "1", "admin", 1);
insert into menus(name, level, description, status) values("manager", "1", "manager", 1);
insert into menus(name, level, description, status) values("general", "1", "general", 0);