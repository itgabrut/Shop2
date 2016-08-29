

DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS orders;



CREATE TABLE users
(
  id         INTEGER PRIMARY KEY AUTO_INCREMENT,
  name       VARCHAR(10) NOT NULL,
  surname    VARCHAR(10) NOT NULL,
  email      VARCHAR(20) NOT NULL UNIQUE ,
  password   VARCHAR(10) NOT NULL,
  dateOfBirth TIMESTAMP NOT NULL ,
  registered TIMESTAMP DEFAULT now(),

  country VARCHAR(15) NOT NULL ,
  city VARCHAR(15) NOT NULL ,
  zip VARCHAR(10) ,
  street VARCHAR(15) NOT NULL ,
  house VARCHAR(10),
  app VARCHAR(10)
)
AUTO_INCREMENT = 100000;
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(10),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE items
(
  id INTEGER PRIMARY KEY  AUTO_INCREMENT,
  name VARCHAR(20) UNIQUE ,
  price INTEGER,
  theme VARCHAR(20),
  quantity INTEGER,
  description TEXT ,
  foto MEDIUMBLOB
)
  AUTO_INCREMENT = 10000;

CREATE TABLE orders
(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  payway VARCHAR(15) NOT NULL ,
  delivery VARCHAR(15) NOT NULL ,
  pay_status VARCHAR(10) NOT NULL ,
  delivery_status VARCHAR(10) NOT NULL ,
  user_id INTEGER NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)
  AUTO_INCREMENT = 10;

CREATE TABLE order_item
(
  id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  item_id INTEGER NOT NULL ,
  order_id INTEGER NOT NULL ,
  quantity  INTEGER NOT NULL ,
  CONSTRAINT orders_items_idx UNIQUE (item_id,order_id),
  FOREIGN KEY (item_id) REFERENCES items(id) ON  DELETE CASCADE ,
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
) AUTO_INCREMENT = 10;
