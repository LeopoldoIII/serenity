package petstore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.core.steps.UIInteractions;
import org.hamcrest.Matchers;

import static net.serenitybdd.rest.SerenityRest.*;

public class PetApiActions extends UIInteractions {

    String animalName = "Tod";

    @Given("Kitty is available in the pet store")
    public long givenKittyIsAvailableInPetStore() {

        Pet pet = new Pet(animalName, "available");


        Long newId = given()
                .baseUri("https://petstore.swagger.io")
                .basePath("/v2/pet")
                .body(pet, ObjectMapperType.GSON)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON).post().getBody().as(Pet.class, ObjectMapperType.GSON).getId();

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(newId);
        return newId;
    }

    @When("I ask for a pet using kitty's ID: {0}")
    public void whenIAskForAPetWithId(long id) {
        when().get("/" + id);
    }

    @Then("I get Kitty as result")
    public void theISeeKittyAsResult() {
        then().body("name", Matchers.equalTo(animalName));
    }

}
