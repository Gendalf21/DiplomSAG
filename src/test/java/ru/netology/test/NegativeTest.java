package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLunits;
import ru.netology.page.TourPage;

import static com.codeborne.selenide.Selenide.open;


public class NegativeTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openChrome() {
        open("http://localhost:8080/");
    }

    @DisplayName("Card - Declined card.")
    @Test
    public void shouldNotPayDeclinedCard() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var declinedCard = DataHelper.getDeclinedCard();
        tourPage.payCard();
        payCard.enterCardData(declinedCard);
        payCard.notSuccessfulCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusPayment = SQLunits.getStatusPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Credit - Declined card.")
    @Test
    public void shouldNotCreditDeclinedCard() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var declinedCard = DataHelper.getDeclinedCard();
        tourPage.buyCredit();
        buyCredit.enterCreditCardData(declinedCard);
        buyCredit.notSuccessfulCreditCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusPayment = SQLunits.getStatusCredit(paymentId);
        Assertions.assertEquals("DECLINED", statusPayment);
    }

    @DisplayName("Card - All blank data.")
    @Test
    public void shouldNotPayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        tourPage.payCard();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - All blank data.")
    @Test
    public void shouldNotCreditEmptyForm() {
        var tourPage = new TourPage();
        var buyCredit = tourPage.buyCredit();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        tourPage.buyCredit();
        buyCredit.enterCreditCardData(emptyCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank card number.")
    @Test
    public void shouldNotPayEmptyCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldCardEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank card number.")
    @Test
    public void shouldNotCreditEmptyCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Year.")
    @Test
    public void shouldNotPayEmptyYear() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldYearEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank Year.")
    @Test
    public void shouldNotCreditEmptyYear() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Month.")
    @Test
    public void shouldNotPayEmptyMonth() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank Month.")
    @Test
    public void shouldNotCreditEmptyMonth() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Blank Holder")
    @Test
    public void shouldNotPayEmptyHolder() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.requiredCardToFillIn();
    }

    @DisplayName("Credit - Blank Holder")
    @Test
    public void shouldNotCreditEmptyHolder() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.requiredCreditToFillIn();
    }

    @DisplayName("Card - Blank CVV.")
    @Test
    public void shouldNotPayEmptyCvv() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank CVV.")
    @Test
    public void shouldNotCreditEmptyCvv() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Expired year.")
    @Test
    public void shouldNotPayExpiredYear() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getExpiredYear();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.expiredCardYear();
    }

    @DisplayName("Credit - Expired year.")
    @Test
    public void shouldNotCreditExpiredYear() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredYear();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardYear();
    }

    @DisplayName("Card - Expired month.")
    @Test
    public void shouldNotPayExpiredMonth() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getExpiredMonth();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.expiredPayCardMonth();
    }

    @DisplayName("Credit - Expired month.")
    @Test
    public void shouldNotCreditExpiredMonth() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getExpiredMonth();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardMonth();
    }

    @DisplayName("Card - Invalid card number.")
    @Test
    public void shouldNotPayInvalidNumber() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getInvalidNumber();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Invalid card number.")
    @Test
    public void shouldNotCreditInvalidNumber() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidNumber();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Wrong year.")
    @Test
    public void shouldNotPayWrongYear() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getWrongYear();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Wrong year.")
    @Test
    public void shouldNotCreditWrongYear() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getWrongYear();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Wrong month.")
    @Test
    public void shouldNotPayWrongMonth() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getWrongMonth();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Wrong month.")
    @Test
    public void shouldNotCreditWrongMonth() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getWrongMonth();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Сard - Numeric holder's name.")
    @Test
    public void shouldNotPayNumericHolder() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getNumericName();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Сredit - Numeric holder's name.")
    @Test
    public void shouldNotCreditNumericHolder() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getNumericName();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Invalid CVV.")
    @Test
    public void shouldNotPayInvalidCVV() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getInvalidCVV();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Invalid CVV.")
    @Test
    public void shouldNotCreditInvalidCVV() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getInvalidCVV();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Zero card number.")
    @Test
    public void shouldNotPayZeroNumber() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getZeroCard();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Zero card number.")
    @Test
    public void shouldNotCreditZeroNumber() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCard();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("Card - Zero month.")
    @Test
    public void shouldNotPayZeroMonth() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getZeroMonth();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.expiredPayCardMonth();
    }

    @DisplayName("Credit- Zero month.")
    @Test
    public void shouldNotCreditZeroMonth() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getZeroMonth();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.expiredCreditCardMonth();
    }

    @DisplayName("Card - Zero CVV")
    @Test
    public void shouldNotPayZeroCVV() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCard = DataHelper.getZeroCVV();
        TourPage.payCard();
        payCard.enterCardData(invalidCard);
        payCard.invalidCardFormat();
    }
    @DisplayName("Credit - Zero CVV")
    @Test
    public void shouldNotCreditZeroCVV() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCard = DataHelper.getZeroCVV();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCard);
        buyCredit.invalidCreditFormat();
    }

}