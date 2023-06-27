# Virtual Power Plant System

This project is a virtual power plant system that aggregates distributed power sources into a single cloud-based energy
provider. It implements a REST API using Spring Boot framework with the following functionality.

## Functionality

### 1. Endpoint to Accept a List of Batteries

- **Endpoint:** `/api/power`
- **HTTP Method:** POST
- **Request Body:**
    - The request body should contain a JSON array of battery objects. Each battery object should have the following
      properties:
        - `name` (string): The name of the battery.
        - `postcode` (string): The postcode associated with the battery.
        - `wattCapacity` (number): The watt capacity of the battery.
- **Response:**
    - The API will persist the battery data in a database (in-memory database).
    - The response will contain a success message indicating that the batteries have been successfully stored.

### 2. Endpoint to Retrieve Batteries within a Postcode Range

- **Endpoint:** `/api/power`
- **HTTP Method:** GET
- **Query Parameters:**
    - `startPostcode` (string): The start postcode of the range.
    - `endPostcode` (string): The end postcode of the range.
- **Response:**
    - The response body will contain a JSON array of battery names that fall within the specified postcode range, sorted
      alphabetically.
    - Additionally, the response will include statistics (aggregate) for the returned batteries:
        - `total` (number): The total watt capacity of the batteries within the range.
        - `average` (number): The average watt capacity of the batteries within the range.
        - `min` (number): The minimum watt capacity of the batteries within the range.
        - `max` (number): The maximum watt capacity of the batteries within the range.

## Getting Started

To run the virtual power plant system and access the API endpoints, follow the steps below:

1. Clone the repository:

   ```shell
   git clone https://github.com/sagarsaud51/power-plant 
   ```

# Note:

    The postman collection for this root folder name: postman_collection.json
