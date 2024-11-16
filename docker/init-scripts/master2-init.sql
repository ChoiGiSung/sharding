-- MySQL 마스터2에서 복제 계정 생성
CREATE USER 'repluser'@'%' IDENTIFIED BY '1234';
GRANT REPLICATION SLAVE ON *.* TO 'repluser'@'%';
FLUSH PRIVILEGES;

-- 필요한 테이블 생성 예시
CREATE DATABASE IF NOT EXISTS master2db;
USE master2db;
CREATE TABLE IF NOT EXISTS sample_table (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
