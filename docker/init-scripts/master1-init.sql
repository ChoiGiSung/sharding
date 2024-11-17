-- MySQL 마스터1에서 복제 계정 생성

RESET MASTER;

CREATE USER 'repluser'@'%' IDENTIFIED WITH 'mysql_native_password' BY '1234';
GRANT REPLICATION SLAVE ON *.* TO 'repluser'@'%';
FLUSH PRIVILEGES;

-- 필요한 테이블 생성 예시
CREATE DATABASE IF NOT EXISTS master1db;
USE master1db;
CREATE TABLE IF NOT EXISTS sample_table (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
