# excecute scipt for elasticsearch
/wait-for-it.sh -t 0 elasticsearch:9200
# excecute scipt for liferay to avoid sql transaction conflict
/wait-for-it.sh -t 0 liferay-active:8080