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
    public void shouldNotPayCreditDeclinedCard() {
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

    @DisplayName("Card with all blank data.")
    @Test
    public void shouldNotPayEmptyForm() {
        var tourPage = new TourPage();
        var payCard = tourPage.payCard();
        var emptyCardInformation = DataHelper.getAllFieldsEmpty();
        tourPage.payCard();
        payCard.enterCardData(emptyCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit with all blank data.")
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
    public void shouldNotConfirmPaymentWithEmptyFieldCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldCardEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("Credit - Blank card number.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldCardEmpty = DataHelper.getCardNumberEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldCardEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By Card with Year field blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldYear() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldYearEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("By Credit with Year field blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldYear() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldYearEmpty = DataHelper.getYearEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldYearEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By Card with Month field blank data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldMonth() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldMonthEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("By Credit with Month field blank data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldMonth() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldMonthEmpty = DataHelper.getMonthEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldMonthEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By Card with Holder field blank data")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldHolder() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldHolderEmpty);
        payCard.requiredCardToFillIn();
    }

    @DisplayName("By Credit with Holder field blank data")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldHolder() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldHolderEmpty = DataHelper.getHolderEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldHolderEmpty);
        buyCredit.requiredCreditToFillIn();
    }

    @DisplayName("By Card with blank CVV field data.")
    @Test
    public void shouldNotConfirmPaymentWithEmptyFieldCvv() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        TourPage.payCard();
        payCard.enterCardData(fieldCvvEmpty);
        payCard.invalidCardFormat();
    }

    @DisplayName("By Credit with blank CVV field data.")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEmptyFieldCvv() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var fieldCvvEmpty = DataHelper.getCVVEmpty();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(fieldCvvEmpty);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By Card Declined card.")
    @Test
    public void shouldPaymentWithDeclinedCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        TourPage.payCard();
        payCard.enterCardData(declinedCardInformation);
        payCard.notSuccessfulPayCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusForPayment = SQLunits.getStatusPayment(paymentId);
        Assertions.assertEquals("DECLINED", statusForPayment);
    }

    @DisplayName("By Credit Declined card.")
    @Test
    public void shouldPaymentWithDeclinedCreditCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var declinedCardInformation = DataHelper.getDeclinedCard();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(declinedCardInformation);
        buyCredit.notSuccessfulCreditCardPayment();

        var paymentId = SQLunits.getPaymentId();
        var statusForPayment = SQLunits.getStatusForCredit(paymentId);
        Assertions.assertEquals("DECLINED", statusForPayment);
    }

    @DisplayName("By card expired card (in previous years)")
    @Test
    public void shouldNotConfirmPaymentWithExpiredYearCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getExpiredYear();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.expiredPayCardYear();
    }

    @DisplayName("By credit expired card (in previous years)")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredYearCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getExpiredYear();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.expiredCreditCardYear();
    }

    @DisplayName("By card expired card (previous month)")
    @Test
    public void shouldNotConfirmPaymentWithExpiredMonthCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getExpiredMonth();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.expiredPayCardMonth();
    }

    @DisplayName("By Credit expired card (previous month)")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithExpiredMonthCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getExpiredMonth();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.expiredCreditCardMonth();
    }

    @DisplayName("By card with zeros in month field.")
    @Test
    public void shouldNotConfirmPaymentWithZeroMonth() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getZeroMonth();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.expiredPayCardMonth();
    }

    @DisplayName("By credit with card with zeros in month field")
    @Test
    public void shouldConfirmBuyingOnCreditWithZeroMonth() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getZeroMonth();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.expiredCreditCardMonth();
    }

    @DisplayName("By card with an incorrect date field format")
    @Test
    public void shouldNotConfirmPaymentWithWrongFormatFieldsCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getWrongFormatDate();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("By credit with card with an incorrect date field format")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithWrongFormatFieldsCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getWrongFormatDate();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By card with the holder's name in Cyrillic")
    @Test
    public void shouldNotConfirmPaymentWithCyrillicHolderFieldCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("By Credit. Card with the holder's name in Cyrillic")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithCyrillicHolderFieldCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithCyrillicName();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By card with the holder's name in Special Symbol.")
    @Test
    public void shouldNotConfirmPaymentWithSpecialSymbolInHolderFieldCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getCardInformationSpecialSymbolInHolderFieldCard();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("By credit. Card with the holder's name Special Symbol")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithEnglishPlusNumbersPlusSpecialSymbolInHolderFieldCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getCardInformationSpecialSymbolInHolderFieldCard();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By card with numbers in the name of the holder")
    @Test
    public void shouldNotConfirmPaymentWithNumericHolderFieldCard() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getNumericName();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("By Credit. Card data with numbers in the name of the holder")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithNumericHolderFieldCard() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getNumericName();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By card with Zero Format CVV")
    @Test
    public void shouldNotConfirmPaymentWithZeroFormatCVV() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getZeroCVV();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("By credit. Card with Zero Format CVV")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithZeroFormatCVV() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getZeroCVV();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By card with Invalid Format CVV.")
    @Test
    public void shouldNotConfirmPaymentWithInvalidFormatCVV() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidFormatCVV();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();
    }

    @DisplayName("By Credit. Card with Invalid Format CVV")
    @Test
    public void shouldNotConfirmBuyingOnCreditWithInvalidFormatCVV() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidFormatCVV();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();
    }

    @DisplayName("By Card Invalid card number")
    @Test
    public void shouldNotConfirmedPaymentWithInvalidNumber() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.invalidCardFormat();

    }

    @DisplayName("By Credit invalid card number.")
    @Test
    public void shouldNotPaymentWithInvalidCreditNumber() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getCardInformationWithInvalidNumber();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.invalidCreditFormat();

    }

    @DisplayName("By card with zero in year field.")
    @Test
    public void shouldNotConfirmPaymentWithZeroYear() {
        var TourPage = new TourPage();
        var payCard = TourPage.payCard();
        var invalidCardInformation = DataHelper.getZeroYear();
        TourPage.payCard();
        payCard.enterCardData(invalidCardInformation);
        payCard.expiredPayCardYear();
    }

    @DisplayName("By credit with card with zero in year field")
    @Test
    public void shouldConfirmBuyingOnCreditWithZeroYear() {
        var TourPage = new TourPage();
        var buyCredit = TourPage.buyCredit();
        var invalidCardInformation = DataHelper.getZeroYear();
        TourPage.buyCredit();
        buyCredit.enterCreditCardData(invalidCardInformation);
        buyCredit.expiredCreditCardYear();
    }
}