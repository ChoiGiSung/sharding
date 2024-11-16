-- 슬레이브1에서 복제 시작
CHANGE MASTER TO
    MASTER_HOST='mysql-master-1',
    MASTER_USER='repluser',
    MASTER_PASSWORD='1234',
    MASTER_LOG_FILE='mysql-bin.000001',
    MASTER_LOG_POS=4;

START SLAVE;
