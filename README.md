 
# Hotel Booking API — Test Automation

Lightweight test automation repository for the Hotel Booking API hosted at:
https://automationintesting.online/api

Tested and verified with Java 25 in latest IntelliJ.

## Overview
Automated API tests implemented with:
- REST-assured for HTTP interactions and assertions
- Cucumber for BDD-style feature files
- Maven test support (examples below)

## Verified with 
- Java 25 SDK
- IntelliJ IDEA (latest)
- Maven
- Git

## Quick start

1. Clone repository
    git clone https://github.com/devcl0ud/sample-hotel-booking-test-automation.git

2. Configure Java SDK to Java 25 in IntelliJ (Project Structure → SDKs → add JDK 25)

3. Run tests
- Maven
  mvn clean test

4. Run specific Cucumber feature
 mvn -Dtest=CucumberRunner test

## Configuration
Base URL is set to the API under test:
- Default: https://automationintesting.online/api

environment variable are set:
-Environment variable: USERNAME=admin and PASSWORD=*****

## Example feature (features/hotel_booking.feature)
```gherkin
Feature: Hotel booking API

  Scenario: New Hotel booking
    Given the API base URL is set
    When request to create new booking at "/booking" with content:
    """
    {
        "firstname": "John",
        "lastname": "Doe",
         "depositpaid": true,
         "roomid": 2,
        "bookingdates": {
            "checkin": "2026-08-06",
          "checkout": "2026-08-08"
        },
        "email": "john.doe@outlook.com",
        "phone": "04661648115"
    }
    """
    Then the response status code should be 201
    And the response should contain valid "bookingid"
    And the response should contain "firstname" and "lastname" with values "John" and "Doe"


  Scenario: Retrieve last created Hotel booking
    Given the API base URL is set
    And generate the valid token
    When request to get last booking at "/booking"
    Then the response status code should be 200
    And the response should contain "firstname" and "lastname" with values "John" and "Doe"
    And the response should contain the same "bookingid"

  Scenario: Update partially last created Hotel booking
    Given the API base URL is set
    And generate the valid token
    When request to update partially last booking at "/booking" with content:
        """
    {

        "lastname": "Doe2"
    }
        """
    Then the response status code should be 200
    And the response should contain "firstname" and "lastname" with values "John" and "Doe2"
    And the response should contain the same "bookingid"


  Scenario: Update the same booking
    Given the API base URL is set
    And generate the valid token
    When request to update same booking at "/booking" with content:
    """
    {
        "firstname": "John",
        "lastname": "Doe",
         "depositpaid": true,
         "roomid": 2,
        "bookingdates": {
            "checkin": "2026-08-02",
          "checkout": "2026-08-04"
        },
        "email": "john.doe@outlook.com",
        "phone": "04661648115"
    }
    """
    Then the response status code should be 200


  Scenario: New Hotel booking with same dates
    Given the API base URL is set
    When request to create new booking at "/booking" with content:
    """
    {
        "firstname": "John",
        "lastname": "Doe",
         "depositpaid": true,
         "roomid": 2,
        "bookingdates": {
           "checkin": "2026-08-02",
          "checkout": "2026-08-04"
        },
        "email": "john.doe@outlook.com",
        "phone": "04661648115"
    }
    """
    Then the response status code should be 409

  Scenario: Delete last created Hotel booking
    Given the API base URL is set
    And generate the valid token
    When request to delete last booking at "/booking"
    Then the response status code should be 200

```

