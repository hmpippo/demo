kubectl run kafka-client --restart='Never' --image docker.io/bitnami/kafka:2.6.0-debian-10-r78 --namespace default --command -- sleep infinity

    kubectl exec --tty -i kafka-client --namespace default -- bash

    PRODUCER:
        kafka-console-producer.sh --broker-list kafka-0.kafka-headless.default.svc.cluster.local:9092 --topic test

    CONSUMER:
        kafka-console-consumer.sh --bootstrap-server kafka.default.svc.cluster.local:9092 --topic test --from-beginning



