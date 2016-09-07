DELETE FROM user_roles;
DELETE FROM users;
INSERT INTO users (name,surname, email, password,country,city,street,DATEOFBIRTH)
VALUES ('User','Petrov','user@yandex.ru', 'password','russia','st.petersburg','nevskiy',current_date );
INSERT INTO users (name,surname, email, password,country,city,street,DATEOFBIRTH)
VALUES ('Василий','Petrov','user@yandex2.ru', 'password','russia','st.petersburg','nevskiy',current_date);
INSERT INTO users (name,surname, email, password,country,city,street,DATEOFBIRTH)
VALUES ('User2','Petrov','user@yandex3.ru', 'password','russia','st.petersburg','nevskiy',current_date);
INSERT INTO users (name,surname, email, password,country,city,street,DATEOFBIRTH)
VALUES ('User4','Petrov','user@yandex4.ru', 'password','russia','st.petersburg','nevskiy',current_date);



INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 13),
  ('ROLE_ADMIN', 14);

