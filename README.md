# GPT-Driver Survey System

This project is a Java Spring Boot application designed to create, answer and analyse surveys using GPT technology. Admin users can define categories, subcategories and create surveys based on selected subcategories. This system automates survey analysis providing insights into the collected data.
This project is designed to work in the future with Spring Security, as for this moment there's no User / Admin registration and authentication.

## Technology Stack
- Java 21
- Maven (version 3.11.0)
- Spring Boot 3.2.2
- Hibernate

## Installation

### Steps
1. Clone the repository:
   ```bash
   git clone  https://github.com/damskw/gpt-survey.git
   ```
2. Navigate to the project directory:
   ```bash
   cd gpt-survey
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage

### Running the Application

To run the Spring application, you can use Maven. Execute the following command in your terminal:

```bash
mvn spring-boot:run
```

### Running Tests

To run all tests execute the following command in your terminal:

```bash
mvn test
```

### OpenAI API Access

This project interacts with the OpenAI API for it's functionalities. Authentication via OpenAI API key is required for the endpoints being used in this project.

### Configuration

To configure this project you need to configure data in the `application.properties` file, follow these steps:

#### Database Configuration:

Set the database connection details such as URL, username, and password.
Example:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/survey_database
spring.datasource.username=admin
spring.datasource.password=admin123
```

#### OpenAI API Key Configuration:

Set your own private API Key obtained from OpenAI.
Example:
```bash
openai.api.key=YOUR_API_KEY
```

## Features

### Category and Subcategory Management:

Admin users can define various categories (e.g., Mental Health) and associated subcategories (e.g., Emotional Well-being, Self-esteem, Mental Resilience).
The system supports a one-to-many relationship between categories and subcategories.

### Survey Creation:

Admin users can create surveys by selecting the desired subcategories.
GPT technology generates survey questions dynamically based on the chosen subcategories, ensuring relevance and diversity.

### Survey Participation:

Users can participate in surveys created within the system, answering the generated questions related to the selected subcategories.

### Automated Survey Analysis:

Upon completion of a survey Admins can use the system which utilizes GPT-driven algorithms to analyze the collected data automatically.
Analysis includes summarizing responses, identifying trends, and providing insights into the surveyed topics.

## Contact

For any inquiries or feedback feel free to get in touch with me:

- **Email**: damian.skwierawski@gmail.com
- **LinkedIn**: [Damian Skwierawski](https://www.linkedin.com/in/damian-skwierawski/)
