# Pathfinder - Travel Routes Sharing Platform

## Overview
Pathfinder is a Spring Boot MVC web application that allows users to share and discover travel routes, locations, and experiences. Users can create detailed route descriptions, upload pictures, embed YouTube videos, and interact with other travelers through comments and messages.

## Features
- **User Management**:
  - User registration and authentication
  - Role-based access control (Admin, User)
  - User profiles

- **Route Sharing**:
  - Create and edit travel routes
  - Upload multiple pictures
  - Embed YouTube videos
  - Categorize routes by location/type
  - Add detailed descriptions

- **Social Features**:
  - Comment on routes
  - Send messages to other users
  - Save favorite routes
  - Follow other travelers

## Technologies Used
### Backend
- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- MySQL Database
- Hibernate
- Maven

### Frontend
- Thymeleaf
- Bootstrap
- JavaScript
- HTML5
- CSS3

## Database Structure
- Users (Authentication and profiles)
- Roles (User authorization)
- Routes (Travel destinations and experiences)
- Pictures (Route images)
- Comments (User interactions)
- Messages (User communication)
- Categories (Route classification)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (recommended: IntelliJ IDEA or Spring Tool Suite)

### Installation
1. Clone the repository:
```sh
git clone https://github.com/JulianJekov/Pathfinder.git
```

2. Navigate to the project directory:
```sh
cd Pathfinder
```

3. Configure MySQL database:
- Create a new database named `pathfinder`
- Update `application.properties` with your database credentials

4. Build the project:
```sh
mvn clean install
```

5. Run the application:
```sh
mvn spring-boot:run
```

6. Access the application at `http://localhost:8080`

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/example/pathfinder/
│   │       ├── config/         # Configuration classes
│   │       ├── controller/     # MVC Controllers
│   │       ├── model/         # Entity classes
│   │       │   ├── entity/    # Database entities
│   │       │   └── dto/       # Data Transfer Objects
│   │       ├── repository/    # Data access layer
│   │       ├── service/       # Business logic
│   │       └── util/          # Utility classes
│   └── resources/
│       ├── static/            # CSS, JS, Images
│       ├── templates/         # Thymeleaf templates
│       └── application.properties
└── test/                      # Unit and integration tests
```

## Key Features Implementation
- **Security**: Implements Spring Security for authentication and authorization
- **File Upload**: Handles multiple image uploads for routes
- **Video Integration**: YouTube video embedding functionality
- **Responsive Design**: Mobile-friendly interface
- **Data Validation**: Server-side validation for all forms
- **Rich Text Editor**: For detailed route descriptions

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Contact
Julian Jekov - [GitHub Profile](https://github.com/JulianJekov)

Project Link: [https://github.com/JulianJekov/Pathfinder](https://github.com/JulianJekov/Pathfinder)
