-- MySQL 마스터2에서 복제 계정 생성

RESET MASTER;

CREATE USER 'repluser'@'%' IDENTIFIED WITH 'mysql_native_password' BY '1234';
GRANT REPLICATION SLAVE ON *.* TO 'repluser'@'%';
FLUSH PRIVILEGES;

-- 필요한 테이블 생성 예시
CREATE DATABASE IF NOT EXISTS master2db;
USE master2db;
CREATE TABLE friend (
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
