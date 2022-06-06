
insert into ROLES(id,name) value(1,'ROLE_USER');
insert into ROLES(id,name) value(2,'ROLE_ADMIN');

insert into USERS(id,email,password,username) value(1,'isaachome@gmail.com','$2a$10$Bp8hy3jHM76iEcmKzy5Dpe3nFHBs8bu1UnFGB6nEwolFtE8MdNaCa','isaachome');
insert into USERS(id,email,password,username) value(2,'admin@gmail.com','$2a$10$G40wKjw1nmSiZba2pDC8iOZ5uYEaOZFP7zPDKwJ0QfIABFq2Hi8wm','admin');
insert into USER_ROLES(user_id,role_id) value(1,1);
insert into USER_ROLES(user_id,role_id) value(2,2);