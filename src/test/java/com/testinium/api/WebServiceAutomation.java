package com.testinium.api;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.testinium.model.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class WebServiceAutomation {

    protected Logger log = Logger.getLogger(this.getClass().getName());

    private BoardResponse boardResponse;

    private List<CardResponse> cardResponseList = new ArrayList<>();

    private List<ListOnBoardResponse> listOnBoardResponseList;

    public WebServiceAutomation() {
        RestAssured.baseURI = "https://api.trello.com";
    }

    public void createBoard() {
        CreateBoardModel createBoardModel = new CreateBoardModel();
        createBoardModel.setName("Mehmet Board 1");
        createBoardModel.setKey("c4a5febaaf5483584bc37c1a2248fbc4");
        createBoardModel.setToken("a67e2c048d77e4395a08c16c30c043ecd6c490ca85d95feecbe6aa6f69d96050");
        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(createBoardModel))
                .when()
                .post("/1/boards/")
                .then()
                .extract().response();

        log.info(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());
        boardResponse = gson.fromJson(response.asString(), BoardResponse.class);
        log.info("Board oluştu");
    }

    public void getListsOnBoard() {
        AuthModel authModel = new AuthModel();
        authModel.setKey("c4a5febaaf5483584bc37c1a2248fbc4");
        authModel.setToken("a67e2c048d77e4395a08c16c30c043ecd6c490ca85d95feecbe6aa6f69d96050");
        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(authModel))
                .pathParam("id", boardResponse.getId())
                .when()
                .get("/1/boards/{id}/lists")
                .then()
                .extract().response();

        log.info(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());
        listOnBoardResponseList = gson.fromJson(response.asString(),  new TypeToken<List<ListOnBoardResponse>>(){}.getType());
        log.info("Board sütunlar çekildi");
    }

    public void createCard() {
        CreateCardModel createCardModel = new CreateCardModel();
        createCardModel.setIdList(listOnBoardResponseList.get(0).getId());
        createCardModel.setName("Card 02");
        createCardModel.setKey("c4a5febaaf5483584bc37c1a2248fbc4");
        createCardModel.setToken("a67e2c048d77e4395a08c16c30c043ecd6c490ca85d95feecbe6aa6f69d96050");
        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(createCardModel))
                .when()
                .post("/1/cards/")
                .then()
                .extract().response();

        log.info(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());
        cardResponseList.add( gson.fromJson(response.asString(), CardResponse.class));
        log.info("Card oluştu");

    }

    public void updateCard(int index) {
        UpdateCardModel updateCardModel = new UpdateCardModel();
        updateCardModel.setName("Card "+ index +" güncellendi");
        updateCardModel.setKey("c4a5febaaf5483584bc37c1a2248fbc4");
        updateCardModel.setToken("a67e2c048d77e4395a08c16c30c043ecd6c490ca85d95feecbe6aa6f69d96050");
        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(updateCardModel))
                .pathParam("id", cardResponseList.get(index).getId())
                .when()
                .put("/1/cards/{id}")
                .then()
                .extract().response();

        log.info(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());
        log.info("Card "+index+" güncellendi");
    }

    public void deleteCreatedCard(int index) {
        AuthModel authModel = new AuthModel();
        authModel.setKey("c4a5febaaf5483584bc37c1a2248fbc4");
        authModel.setToken("a67e2c048d77e4395a08c16c30c043ecd6c490ca85d95feecbe6aa6f69d96050");
        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(authModel))
                .pathParam("id", cardResponseList.get(index).getId())
                .when()
                .delete("/1/cards/{id}")
                .then()
                .extract().response();

        log.info(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());
        log.info("Card "+index+ " silindi");
    }

    public void deleteCreatedBoard() {
        AuthModel authModel = new AuthModel();
        authModel.setKey("c4a5febaaf5483584bc37c1a2248fbc4");
        authModel.setToken("a67e2c048d77e4395a08c16c30c043ecd6c490ca85d95feecbe6aa6f69d96050");
        Gson gson = new Gson();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(authModel))
                .pathParam("id", boardResponse.getId())
                .when()
                .delete("/1/boards/{id}")
                .then()
                .extract().response();

        log.info(response.asPrettyString());
        Assertions.assertEquals(200, response.statusCode());
        log.info("Board silindi");
    }
}
