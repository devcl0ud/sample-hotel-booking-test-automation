Feature: API Testing with Rest Assured

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
            "checkin": "2026-09-10",
          "checkout": "2026-09-12"
        },
        "email": "john.doe@outlook.com",
        "phone": "04661648115"
    }
    """
    Then the response status code should be 201
    And the response should contain valid "bookingid"
    And the response should contain "firstname" and "lastname" with values "John" and "Doe"

  Scenario: Delete last created Hotel booking
    Given the API base URL is set
    And Generate the valid token
    When request to delete last booking at "/booking"
    Then the response status code should be 200
