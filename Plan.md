# План автоматизации

# 1. Перечень автоматизируемых сценариев

**Проверяемая функциональность:**

Приложение в собственной СУБД сохраняет информацию о том, каким способом был совершён платёж и успешно ли он был совершён (не допуская сохранения данных карт).
Все тесты проводим в двух вариантах:
1) для блока "КУПИТЬ"
2) для блока "КУПИТЬ В КРЕДИТ"

**Карты, предоставленные для тестирования в файле data.json:**

4444 4444 4444 4441, status "APPROVED"

4444 4444 4444 4442, status "DECLINED"

**Характеристика вводимых валидных тестовых данных:**

поле "Номер карты": 16 цифр

поле "Месяц": двузначное числовое значение от 01 до 12

поле "Год": двузначное числовое значение;

поле "Владелец": допустимые значения из латинских букв, дефисов, пробелов

поле "CVC/CCV": три цифры

**1. Покупка с валидным номером карты:**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "Текущий год + 1"
3.	В поле "Месяц" ввести "Текущий месяц + 1"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление "Успешно! Операция одобрена банком". В БД статус операции: "APPROVED"*

**Негативные сценарии:**

**1. Покупка с невалидным номером карты:**
1.	В поле "Номер карты" ввести "4444 4444 4444 4442".
2.	В поле "Год" ввести "Текущий год + 1"
3.	В поле "Месяц" ввести "Текущий месяц + 1"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить"

*Ожидаемый результат: уведомление "Ошибка! Банк отказал в проведении операции"."Отказано". В БД статус операции: " DECLINED"*

 **2. Отправка формы со всеми пустыми полями :**

1.	Оставить все поля пустыми
2.	Нажать кнопку "Продолжить"

*Ожидаемый результат: уведомление об ошибках под всеми полями. Форма не отправлена.*

**3. Отправки формы с пустым полем "Номер карты" :**
1.	Поле "Номер карты" оставить пустым.
2.	В поле "Год" ввести "Текущий год + 1"
3.	В поле "Месяц" ввести "Текущий месяц + 1"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить"

*Ожидаемый результат: В поле "Номер карты" отображается сообщение "Поле обязательно для заполнения"*

**4. Отправки формы с пустым полем "Год" :**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441".
2.	Поле "Год" оставить пустым.
3.	В поле "Месяц" ввести "Текущий месяц + 1"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить"

*Ожидаемый результат: В поле "Год" отображается сообщение "Поле обязательно для заполнения"*

**5. Отправки формы с пустым полем "Месяц":**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441".
2.	В поле "Год" ввести "Текущий год + 2"
3.	Поле "Месяц" оставить пустым
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить"
	
*Ожидаемый результат: В поле "Месяц" отображается сообщение "Поле обязательно для заполнения"*

**6. Отправки формы с пустым полем "Владелец":**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441”
2.	В поле "Год" ввести "Текущий год + 2"
3.	В поле "Месяц" ввести "Текущий месяц + 2"
4.	Поле "Владелец" оставить пустым
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить"

*Ожидаемый результат: В поле "Владелец" отображается сообщение "Поле обязательно для заполнения"*

**7. Отправки формы с пустым полем "CVC/CVV":**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441".
2.	В поле "Год" ввести "Текущий год + 3"
3.	В поле "Месяц" ввести "Текущий месяц + 2"
4.	В поле "Владелец" вводятся данные Faker
5.	Поле "CVC/CVV" оставить пустым
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: В поле "CVC/CVV" отображается сообщение "Поле обязательно для заполнения"*

**8. Покупка с карты с истекшим сроком действия (прошлый год):**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441".
2.	В поле "Год" ввести "Текущий год - 1"
3.	В поле "Месяц" ввести "Текущий месяц"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" ввести "123"
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление "Истёк срок действия карты" под полем "Год". Форма не отправлена.*

**9. Покупка с карты с истекшим сроком действия (предыдущий месяц) :**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "Текущий год"
3.	В поле "Месяц" ввести "Текущий месяц - 1"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить"

*Ожидаемый результат: уведомление "Неверно указан срок действия карты" под полем "Месяц". Форма не отправлена.*

**10. Покупка с указанием неверного формата поля "Номер карты":**
1.	В поле "Номер карты" ввести "4444 4444"
2.	В поле "Год" ввести "Текущий год + 1"
3.	В поле "Месяц" ввести "Текущий месяц + 2"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление об ошибке под полем "Номер карты" , форма не отправлена.*

**11. Покупка с указанием неверного формата поля Год:**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "158"
3.	В поле "Месяц" ввести "Текущий месяц +2"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление об ошибке под полем "Год" , форма не отправлена.*

**12. Покупка с указанием неверного формата поля Месяц:**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "Текущий год +3"
3.	В поле "Месяц" ввести "27"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление об ошибке под полем "Месяц" , форма не отправлена.*

**13. Покупка с указанием неверного формата поля Владелец:**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "Текущий год +2"
3.	В поле "Месяц" ввести "Текущий месяц +2"
4.	В поле "Владелец" ввести "13 !+"
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление об ошибке под полем "Владелец" , форма не отправлена.*

**14. Покупка с указанием неверного формата поля CVC/CVV:**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "Текущий год +2"
3.	В поле "Месяц" ввести "Текущий месяц +2"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" ввести "99"
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление об ошибке под полем "CVC/CVV" , форма не отправлена.*

**15. Покупка с несуществующим номером карты:**
1.	В поле "Номер карты" ввести "0000 0000 0000 0000"
2.	В поле "Год" ввести "Текущий год +2"
3.	В поле "Месяц" ввести "Текущий месяц +2"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление "Ошибка! Банк отказал в проведении операции"."Отказано". 

**16. Покупка с нулями в поле "Год"**
1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "00"
3.	В поле "Месяц" ввести "Текущий месяц"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление "Истёк срок действия карты" под полем "Год". Форма не отправлена.*

**17. Покупка с нулями в поле "Месяц"**

1.	В поле "Номер карты" ввести "4444 4444 4444 4441"
2.	В поле "Год" ввести "Текущий год"
3.	В поле "Месяц" ввести "00"
4.	В поле "Владелец" вводятся данные Faker
5.	В поле "CVC/CVV" вводятся данные Faker
6.	Нажать кнопку "Продолжить" 

*Ожидаемый результат: уведомление "Неверно указан срок действия карты" под полем "Месяц". Форма не отправлена.*

# 2 Перечень используемых инструментов с обоснованием выбора

* **Java 11** - язык программирования для написания автотестов
* **IntelliJ IDEA 2021.3.2 (Community Edition)** - среда разработки ПО с поддержкой Java 11
* **Gradle** - cистема автоматической сборки проектов, позволяет подключать нужные библиотеки через настройку зависимостей
* **Lombok** - библиотека для генерации кода
* **JavaFaker** -библиотека, которая позволяет генерировать случайные данные
* **JUnit Jupiter** - библиотека для модульного тестирования программного обеспечения на языке Java
* **Selenide** - библиотека для автоматизированного тестирования веб-приложений на основе Selenium WebDriver, позволяющая использовать CSS селекторы
* **Docker&DockerCompose** - система контейнеризации для управления базами данных. Позволяет экономить ресурсы хостовой машины
* **Allure** - фреймворк от Яндекса для создания простых и понятных отчётов автотестов
* **Git** - система управления версиями с распределенной архитектурой, обеспечивающая отслеживание изменений программного кода и управления ими
* **GitHub** - веб-сервис для хостинга IT-проектов и их совместной разработки
* **База данных SQL** – БД для хранения информации о том, каким способом был совершён платёж и успешно ли он был совершён

# 3 Перечень и описание возможных рисков при автоматизации

• Отсутствие документации по описанию работы приложения

•	Сложность первого запуска SUT и БД

•	Увеличение времени работы из-за недостаточной мощности ПК

•	Увеличение стоимости работы из-за увеличения времени

•	Cложность в поддержке актуального состояния тестов при изменении данных

# 4 Интервальная оценка с учётом рисков

•	Написание плана тестирования - 8ч.

•	Подготовка и настройка проекта, установка необходимого ПО, запуск SUT - 8ч.

•	Написание тестов - от 24ч до 72ч.

•	Написание отчетов о тестировании - 8ч.

•	Написание отчетов об автоматизации - 6ч.

# 5 План сдачи работ

•	Написание плана тестирования - 1 день

•	Написание автотестов - от 14 до 21 дня после согласования плана тестирования

•	Написание отчетов о тестировании и автоматизации - 1 день после написания автотестов