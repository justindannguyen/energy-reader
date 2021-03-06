# Aggregate Raw Energy data, Hour Windows, Per Meter

## Environment Configurations
### Eureka configuration
If not set then use default value ```http://localhost:8761/eureka/``` 
```
export EUREKA_SERVER_URI=?
```

### Kafka input topic
If not set then use default value ```energysolution_rawreading```
```
export ENERGY_INPUT_TOPIC=?
```

### Kafka Consumer group
If not set then use default value ```energy-processor``` 
```
export ENEGY_CONSUMER_GROUP=?
```

### Kafka Server
If not set then use default value ```localhost:9092``` 
```
export BROKERS=?
```

### Kafka output topic
If not set then use default value ```energysolution_parsedreading``` 
```
export ENERGY_OUTPUT_TOPIC=?
```

### Sink consumer group
If not set then use default value ```energy-hour-aggregation-sink```
```
export ENEGY_HA_CONSUMER_GROUP = ?
```
### MongoDB Sink configuration
If not set then use default value ```mongodb://localhost:27017/energy``` 
```
export MONGODB_SERVER_URI=?
```

## Build
Build at local
```
mvn clean install
```

Build & publish as docker image
```
mvn clean install -P docker
```

## Use the image
```
version: '2'
services:
  energy-hour-aggregator:
    image: justindannguyen/energy-hour-aggregator
    restart: always
```

