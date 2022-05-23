create table T_USER (
    username VARCHAR(30) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);