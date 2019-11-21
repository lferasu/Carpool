TOPIC1=passenger-trip-topic
TOPIC2=NEWRESERVATION
TOPIC3=registredTrip
TOPIC4=searchregistredTrip

KAFKA-POD=carpoolkafka-0
ZOOKEEPER-SERVICE=zookeeper

K8S-install:
	kubectl apply -f ./*.yaml

K8S-delete:
	kubectl delete -f ./*.yaml

K8S-add-topic:
	kubectl exec -ti ${KAFKA-POD} -- /opt/bitnami/kafka/bin/kafka-topics.sh --create --zookeeper ${ZOOKEEPER-SERVICE}:2181 --replication-factor 1 --partitions 1 --topic ${TOPIC1}
	kubectl exec -ti ${KAFKA-POD} -- /opt/bitnami/kafka/bin/kafka-topics.sh --create --zookeeper ${ZOOKEEPER-SERVICE}:2181 --replication-factor 1 --partitions 1 --topic ${TOPIC2}
	kubectl exec -ti ${KAFKA-POD} -- /opt/bitnami/kafka/bin/kafka-topics.sh --create --zookeeper ${ZOOKEEPER-SERVICE}:2181 --replication-factor 1 --partitions 1 --topic ${TOPIC3}
	kubectl exec -ti ${KAFKA-POD} -- /opt/bitnami/kafka/bin/kafka-topics.sh --create --zookeeper ${ZOOKEEPER-SERVICE}:2181 --replication-factor 1 --partitions 1 --topic ${TOPIC4}
K8S-consumer-console:
	echo 'Example:----->  make K8S-consumer-console topic=passenger-trip-topic'
	kubectl exec -ti ${KAFKA-POD} -- /opt/bitnami/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ${topic} --from-beginning

K8S-producer-console:
	echo 'Example:----->  make K8S-producer-console topic=passenger-trip-topic'
	kubectl exec -ti ${KAFKA-POD} -- /opt/bitnami/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic ${topic}



