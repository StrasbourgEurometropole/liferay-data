#!/bin/sh

# ----------------------------------------------------
# INIT
# ----------------------------------------------------
export HOME=${LIFERAY_HOME}
pid=0

# ----------------------------------------------------
# TERMINATION EVENTS
# ----------------------------------------------------
# SIGTERM-handler
shutdown_handler() {
  echo "shutdown_handler() invoked!"
  if [ $pid -ne 0 ]; then
    echo "shutdown_handler() clean up tasks!"
    
    echo "Shutdown Tomcat"
    /liferay/tomcat-9.0.17/bin/shutdown.sh

    local SLEEP_TIME=5
    local PATTERN=java
    local JPID=`ps -ef | grep -i $PATTERN| grep -v grep |grep -v sshd | awk -F' ' '{print $2}'`

    while [ ! -z $JPID ] && [ $JPID -gt 0 ]
    do
        echo "The process is still running... waiting" $SLEEP_TIME "seconds."
        sleep $SLEEP_TIME
        JPID=`ps -ef | grep -i $PATTERN| grep -v grep |grep -v sshd | awk -F ' ' '{print $2}'`
    done
    echo "Attention! The process has been finally stopped OK"
  fi

  exit 143; # 128 + 15 -- SIGTERM
}
# ----------------------------------------------------------------------------------------------------
# FINALIZE
# ----------------------------------------------------------------------------------------------------

# Trap to intercept SIGTERM and to shutdown tomcat

trap 'kill ${!}; shutdown_handler' TERM
trap 'kill ${!}; shutdown_handler' KILL
trap 'kill ${!}; shutdown_handler' INT

# ----------------------------------------------------------------------------------------------------
# LILFERAY STARTUP
# ----------------------------------------------------------------------------------------------------
# excecute scipt to wait mysql
/liferay/wait-for-it.sh -t 0 mysql:3306
# excecute scipt to wait elasticsearch
/liferay/wait-for-it.sh -t 0 elasticsearch:9200

/liferay/tomcat-9.0.17/bin/catalina.sh run &

pid="$!"
# wait forever
while true
do
    tail -f /dev/null & wait ${!}
done