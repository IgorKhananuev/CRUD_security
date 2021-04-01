CREATE DATABASE users;

CREATE TABLE users.users(
                            id INT NOT NULL auto_increment primary key,
                            name VARCHAR(255) NOT NULL,
                            password VARCHAR(255) NOT NULL
);

INSERT INTO users.users  (name,password) VALUES ('persone1', 'pass1');
INSERT INTO users.users  (name,password) VALUES ('persone2', 'pass2');
INSERT INTO users.users  (name,password) VALUES ('persone3', 'pass3');


CREATE TABLE users.roles
(
    id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO users.roles VALUES (1, 'ROLE_USER');
INSERT INTO users.roles VALUES (2, 'ROLE_ADMIN');


CREATE TABLE users.user_roles
(
    user_id   INT     NOT NULL,
    role_id   INT (100) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
);

INSERT INTO users.user_roles VALUES (1,1);
INSERT INTO users.user_roles VALUES (2,1);
INSERT INTO users.user_roles VALUES (3,2);

TRUNCATE users.users