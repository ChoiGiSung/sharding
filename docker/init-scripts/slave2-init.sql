-- 슬레이브2에서 복제 시작
RESET SLAVE ALL;
STOP SLAVE;

CHANGE MASTER TO
    MASTER_HOST='mysql-master-2',
    MASTER_USER='repluser',
    MASTER_PASSWORD='1234',
    MASTER_LOG_FILE='mysql-bin.000001',
    MASTER_LOG_POS=157;

START SLAVE;
