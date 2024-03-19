package ru.yadoma_realty.utils;

import com.github.javafaker.Faker;
import ru.yadoma_realty.enums.BuildingForTesting;
import ru.yadoma_realty.enums.UsersForTesting;

public class GenerationData {
    static Faker faker = new Faker();

    public static String setRandomPhone () {
        return "7" + faker.phoneNumber().subscriberNumber(10);
    }

    public static String setRandomUserName() {
        return faker.name().firstName();
    }

    public static String setRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static UsersForTesting setUsersForTesting() {
        return faker.options().option(UsersForTesting.values());
    }

    public static int setRandomBuildingId() {
        return faker.options().option(BuildingForTesting.values()).id;
    }
}
