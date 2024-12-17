.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties  (Zookeeper Server Start)

.\bin\windows\kafka-server-start.bat .\config\server.properties  (Kafka Server Start)

.\bin\windows\kafka-topics.bat --create --topic <Topic Name> --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1  (Topic Createtion)

//Another tab
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic <Topic Name> 
