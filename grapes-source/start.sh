#!/bin/bash -x

KAFKA_HEADLESS_SVC="_broker._tcp.kafka.${NAMESPACE}.svc.cluster.local"
while read -r f1 f2 PORT HOST; do
  KAFKA_BROKERS="${HOST%?}:${PORT},${KAFKA_BROKERS}"
done < <(dig SRV $KAFKA_HEADLESS_SVC +short)

java -jar boot-app.jar \
  --spring.profiles.active=kafka \
  --spring.cloud.stream.kafka.binder.brokers="${KAFKA_BROKERS%?}" \
  --spring.cloud.stream.kafka.binder.zkNodes="zk:${ZK_SERVICE_PORT}"
