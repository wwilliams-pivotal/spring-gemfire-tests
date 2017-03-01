#!/bin/bash

# . set_environment.sh

#export CLASSPATH=${PROJECT_HOME}/gemfire-server/target/gemfire-server-0.0.1-SNAPSHOT.jar:${PROJECT_HOME}/domain/target/domain-0.0.1-SNAPSHOT.jar

# copy to the cluster configuration service for deployment
#cp ../server/gemfire-functions/target/gemfire-functions-0.0.1-SNAPSHOT.jar locator1/cluster_config/cluster/

#DIR=$(PWD)
#APP_JARS=$DIR/../server/gemfire-server/target/gemfire-server-0.0.1-SNAPSHOT.jar:/$DIR/../server/kafka-writer/target/kafka-writer-0.0.1-SNAPSHOT.jar

# Issue commands to gfsh to start locator and launch a server
echo "Starting locator and server..."
gfsh <<!
connect

start locator --name=locator1 --port=10334 --properties-file=config/locator.properties --load-cluster-configuration-from-dir=true --initial-heap=256m --max-heap=256m

start server --classpath=$APP_JARS --name=server1 --server-port=0 --initial-heap=2g --max-heap=2g --J=-XX:+UseParNewGC --J=-XX:+UseConcMarkSweepGC --J=-XX:+UnlockDiagnosticVMOptions --J=-XX:ParGCCardsPerStrideChunk=32768 --properties-file=config/gemfire.properties 
start server --classpath=$APP_JARS --name=server2 --server-port=0 --initial-heap=2g --max-heap=2g --J=-XX:+UseParNewGC --J=-XX:+UseConcMarkSweepGC --J=-XX:+UnlockDiagnosticVMOptions --J=-XX:ParGCCardsPerStrideChunk=32768 --properties-file=config/gemfire.properties

list members;
list regions;
!
