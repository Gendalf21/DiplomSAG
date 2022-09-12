# DiplomSAG

## Дипломный проект профессии «Тестировщик»

Данный проект представляет собой тестирование веб-сервиса покупки тура "Путешествие дня - Марракэш" 
(предоставленного в виде приложения). Приложение предлагает купить тур по определённой цене с помощью 
двух способов: обычная оплата по дебетовой карте и выдача кредита по данным банковской карты. 
Приложение в собственной СУБД сохраняет информацию о том, каким способом был совершён платёж и успешно 
ли он был совершён. 

В рамках проекта составляется план тестирования. На основании плана создаются автотесты. Они проверяют
позитивные и негативные сценарии покупки. Результаты тестов обрабатываются и визуализируются во внешней
системе отчетов. На основании полученного отчета даются рекомендации по улучшению сервиса.

[План тестирования](https://github.com/Gendalf21/DiplomSAG/blob/master/documents/Plan.md)

[Отчет по тестированию](https://github.com/Gendalf21/DiplomSAG/blob/master/documents/Report.md)

[Отчет о выполнении плана тестирования](https://github.com/Gendalf21/DiplomSAG/blob/master/documents/Summary.md)

## Подготовка и настройка окружения

* Java 11
* IntelliJ IDEA 2021.3.2 с подключенными библиотеками файл [build.gradle](https://github.com/Gendalf21/DiplomSAG/blob/master/build.gradle) :
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
* в качестве SUT используется 
  [aqa-shop.jar](https://github.com/netology-code/qa-diploma/blob/master/aqa-shop.jar)

## Установка и запуск приложения, автотестов и отчетов

Учётные данные и url для подключения задаются в файле [application.properties](https://github.com/Gendalf21/DiplomSAG/blob/master/application.properties)

* запустить IntelliJ IDEA с проектом с депозитария
  * git clone ```https://github.com/Gendalf21/DiplomSAG```
* подключиться к виртуальной машине с помощью клиента PuTTY
  * склонировать репозиторий на виртуальной машине 
    * git clone ```https://github.com/Gendalf21/DiplomSAG```
  * перейти в папку с проектом 
    * cd ```DiplomSAG```
  * запустить docker container (настройки в файле docker-compose.yml)
    * ```docker-compose up```   
  * дождаться запуска контейнеров
* в терминале IntelliJ IDEA запустить SUT:
    - с использованием БД MySQL командой 
      ```java "-Dspring.datasource.url=jdbc:mysql://185.119.57.47:3306/app" -jar artifacts/aqa-shop.jar```
    - с использованием БД PostgreSQL командой
      ```java "-Dspring.datasource.url=jdbc:postgresql://185.119.57.47:5432/app" -jar artifacts/aqa-shop.jar```
* в терминале IntelliJ IDEA запустить автотесты командой:
    - для конфигурации БД MySql:  
      ```./gradlew clean test "-Ddb.url=jdbc:mysql://185.119.57.47:3306/app"```
    - для конфигурации БД PostgreSQL:  
      ```./gradlew clean test "-Ddb.url=jdbc:postgresql://185.119.57.47:5432/app"```
* в терминале IntelliJ IDEA запустить отчеты командой:
    - ```./gradlew allureReport (первоначальная команда)```
    - ```./gradlew allureServe (запуск и открытие отчетов)```
* в терминале IntelliJ IDEA остановить SUT
  * ```CTRL+C```
* на виртуальной машине остановить работу контейнеров
  * ```docker stop $(docker ps –a –q)``` 
* закрыть клиент PuTTY

## Лицензии

Все указанное ПО и библиотеки статус freeware или open-source software. 