# DiplomSAG

[План тестирования](https://github.com/Gendalf21/DiplomSAG/blob/master/documents/Plan.md)

[Отчет по тестированию](https://github.com/Gendalf21/DiplomSAG/blob/master/documents/Report.md)

[Отчет о выполнении плана тестирования](https://github.com/Gendalf21/DiplomSAG/blob/master/documents/Summary.md)

### Настройка окружения

* Java 11
* IntelliJ IDEA 2021.3.2 с подключенными библиотеками:
  * JUnit Jupiter
  * Selenide 
  * Selenium Java
  * Lombok
  * JavaFaker
  * WebDriverManager 
  * Apache Commons DbUtils
  * MySQL Connector
  * PostgreSQL JDBC Driver
  * Allure
* PyTTY
* Виртуальная машина с установленными Git, Docker, Java. 
  * доступ по ssh протоколу 
  * IP-адрес 185.119.57.47 port 22
  * логин/пароль выдается администратором

### Запуск приложения, автотестов и отчетов

* запустить IntelliJ IDEA с проектом
* подключиться к виртуальной машине с помощью клиента PuTTY по IP 185.119.57.47 port 22
* склонировать репозиторий git clone ```https://github.com/Gendalf21/DiplomSAG```
* перейти в папку cd ```DiplomSAG```
* запустить docker container ```docker-compose up```.
* дождаться запуска контейнеров
* в терминале IntelliJ IDEA запустить SUT:
    - с использованием БД MySQL
      командой ```java "-Dspring.datasource.url=jdbc:mysql://185.119.57.47:3306/app" -jar artifacts/aqa-shop.jar```
    - с использованием БД PostgreSQL
      командой ```java "-Dspring.datasource.url=jdbc:postgresql://185.119.57.47:5432/app" -jar artifacts/aqa-shop.jar```
* запустить автотесты командой:
    - для конфигурации БД MySql:  
      ```./gradlew clean test "-Ddb.url=jdbc:mysql://185.119.57.47:3306/app"```
    - для конфигурации БД PostgreSQL:  
      ```./gradlew clean test "-Ddb.url=jdbc:postgresql://185.119.57.47:5432/app"```
* запустить отчеты командой:
  - ```./gradlew allureReport (первоначальная команда)```
  - ```./gradlew allureServe (запуск и открытие отчетов)```
* остановить SUT комбинацией клавиш ```CTRL+C```
* остановить работу контейнеров ```docker stop $(docker ps –a –q)``` 
* закрыть клиент PuTTY