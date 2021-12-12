package com.testinium.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;


public class WebServiceTest {

    private WebServiceAutomation webServiceAutomation = new WebServiceAutomation();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    @Test
    public void runTrelloApiTest() {
        webServiceAutomation.createBoard();
        webServiceAutomation.getListsOnBoard();
        webServiceAutomation.createCard();
        webServiceAutomation.createCard();
        webServiceAutomation.updateCard(0);
        webServiceAutomation.deleteCreatedCard(0);
        webServiceAutomation.deleteCreatedCard(1);
        webServiceAutomation.deleteCreatedBoard();
    }

}
