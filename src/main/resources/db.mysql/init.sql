CREATE USER 'luke'@'%' IDENTIFIED WITH mysql_native_password BY 'luke@!prod';
CREATE DATABASE luke;

GRANT ALL ON luke.* TO 'luke'@'%';

FLUSH PRIVILEGES;
