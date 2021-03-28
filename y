[0;1;32m●[0m mysql.service - LSB: Start and stop the mysql database server daemon
     Loaded: loaded (/etc/init.d/mysql; generated)
     Active: [0;1;32mactive (running)[0m since Sun 2021-03-28 12:39:29 -03; 37s ago
       Docs: man:systemd-sysv-generator(8)
    Process: 3710 ExecStart=/etc/init.d/mysql start (code=exited, status=0/SUCCESS)
      Tasks: 33 (limit: 9300)
     Memory: 102.0M
     CGroup: /system.slice/mysql.service
             ├─3758 /bin/sh /usr/bin/mysqld_safe
             ├─3875 /usr/sbin/mysqld --basedir=/usr --datadir=/var/lib/mysql --plugin-dir=/usr/lib/x86_64-linux-gnu/mariadb19/plugin --user=mysql --skip-log-error --pid-file=/run/mysqld/mysqld.pid --socket=/var/run/mysqld/mysqld.sock
             └─3876 logger -t mysqld -p daemon error

mar 28 12:39:29 marcosNote mysqld[3876]: [0;1;31m[0;1;39m[0;1;31m2021-03-28 12:39:29 10 [Warning] Access denied for user 'root'@'localhost' (using password: NO)[0m
mar 28 12:39:29 marcosNote mysqld[3876]: [0;1;31m[0;1;39m[0;1;31m2021-03-28 12:39:29 11 [Warning] Access denied for user 'root'@'localhost' (using password: NO)[0m
mar 28 12:39:29 marcosNote /etc/mysql/debian-start[3933]: [0;1;38;5;185m[0;1;39m[0;1;38;5;185m/usr/bin/mysql_upgrade: the '--basedir' option is always ignored[0m
mar 28 12:39:29 marcosNote /etc/mysql/debian-start[3933]: [0;1;38;5;185m[0;1;39m[0;1;38;5;185mLooking for 'mysql' as: /usr/bin/mysql[0m
mar 28 12:39:29 marcosNote /etc/mysql/debian-start[3933]: [0;1;38;5;185m[0;1;39m[0;1;38;5;185mLooking for 'mysqlcheck' as: /usr/bin/mysqlcheck[0m
mar 28 12:39:29 marcosNote /etc/mysql/debian-start[3933]: [0;1;38;5;185m[0;1;39m[0;1;38;5;185mVersion check failed. Got the following error when calling the 'mysql' command line client[0m
mar 28 12:39:29 marcosNote /etc/mysql/debian-start[3933]: [0;1;38;5;185m[0;1;39m[0;1;38;5;185mERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: NO)[0m
mar 28 12:39:29 marcosNote /etc/mysql/debian-start[3933]: [0;1;38;5;185m[0;1;39m[0;1;38;5;185mFATAL ERROR: Upgrade failed[0m
mar 28 12:39:29 marcosNote mysql[3945]: ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: NO)
mar 28 12:39:29 marcosNote mysqld[3876]: [0;1;31m[0;1;39m[0;1;31m2021-03-28 12:39:29 12 [Warning] Access denied for user 'root'@'localhost' (using password: NO)[0m
