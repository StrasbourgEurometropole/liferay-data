# change user
su root

# add execution perm to the waiting script
chmod +x /opt/liferay/wait-for-it.sh

# excecute scipt for mysql
/opt/liferay/wait-for-it.sh -t 0 mysql:3306
# excecute scipt for elasticsearch
/opt/liferay/wait-for-it.sh -t 0 elasticsearch:9200