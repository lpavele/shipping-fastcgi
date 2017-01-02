# Undertow vs Grizzly

## Запросы
- GET /hello
- GET /couriers/{courierId}/orders

## Сборка
mvn clean package

## Запуск
java -cp shipping-service-1.0-SNAPSHOT.jar by.lpe.shipping.UndertowMain 

java -cp shipping-service-1.0-SNAPSHOT.jar by.lpe.shipping.GrizzlyMain
