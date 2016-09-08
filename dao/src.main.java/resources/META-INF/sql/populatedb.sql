DELETE FROM user_roles;
DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT=100000;
INSERT INTO users (name,surname, email, password,country,city,street)
VALUES ('User','Petrov','user@yandex.ru', 'password','russia','st.petersburg','nevskiy');
INSERT INTO users (name,surname, email, password,country,city,street)
VALUES ('Василий','Petrov','user@yandex2.ru', 'password','russia','st.petersburg','nevskiy');
INSERT INTO users (name,surname, email, password,country,city,street)
VALUES ('User2','Petrov','user@yandex3.ru', 'password','russia','st.petersburg','nevskiy');
INSERT INTO users (name,surname, email, password,country,city,street)
VALUES ('User4','Petrov','user@yandex4.ru', 'password','russia','st.petersburg','nevskiy');



INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100003);

