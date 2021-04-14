CREATE DATABASE users;

CREATE TABLE users.users(
                            id INT NOT NULL auto_increment primary key,
                            name VARCHAR(255) NOT NULL,
                            password VARCHAR(255) NOT NULL
);

INSERT INTO users.users  (name,password) VALUES ('ADMIN', '1');
INSERT INTO users.users  (name,password) VALUES ('USER', '2');



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

    FOREIGN KEY (user_id) REFERENCES users.users (id),
    FOREIGN KEY (role_id) REFERENCES users.roles (id),

    UNIQUE (user_id, role_id)
);

INSERT INTO users.user_roles VALUES (1,2);
INSERT INTO users.user_roles VALUES (2,1);


# TRUNCATE users.users;
# TRUNCATE users.roles;
# TRUNCATE users.user_roles;
#
# DROP DATABASE users;