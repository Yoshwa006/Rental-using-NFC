# RentalEase - Simplifying Rentals with Spring Boot

## Introduction
RentalEase is a simple yet efficient rental management system built using Spring Boot and Docker. It streamlines the process of renting items, making it easier for users to list, manage, and rent items securely. The system ensures a hassle-free experience by offering features such as user authentication, rental tracking, and item availability updates.

## Features
- **User Authentication**: Secure login and registration system.
- **Item Listing**: Users can add and manage rental items.
- **Rental Management**: Easy tracking of rented items.
- **Availability Status**: Shows item availability in real-time.
- **Secure Transactions**: Ensures safe and reliable rentals.
- **Admin Dashboard**: Admin users can manage listings and rentals.

## Technologies Used
- **Backend**: Java, Spring Boot
- **Database**: MongoDB
- **API Testing**: Postman
- **Deployment**: Render (for backend), MongoDB Atlas (for database)
- **Containerization**: Docker

## Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/rentalease.git
   ```
2. **Navigate to the project directory:**
   ```sh
   cd rentalease
   ```
3. **Configure database:**
   - Update `application.properties` for MongoDB connection.
4. **Build and run the application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
5. **Run with Docker:**
   ```sh
   docker build -t rentalease .
   docker run -p 8080:8080 rentalease
   ```

## API Endpoints
- `GET /items` - Retrieve all items
- `GET /items/{id}` - Retrieve item by ID
- `POST /items` - Register a new item
- `PUT /items/{id}` - Modify item details
- `DELETE /items/{id}` - Delete an item
- `DELETE /items/deletecomp` - Delete all items

## Future Enhancements
- **Mobile App Integration**: Extend functionality to mobile devices.
- **QR Code Support**: Scan and retrieve rental details.
- **Advanced Search**: Improve item search and filtering.

## Contributing
Contributions are welcome! Feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License.

## Contact
For any queries or feedback, please reach out at [your-email@example.com](mailto:your-email@example.com).

