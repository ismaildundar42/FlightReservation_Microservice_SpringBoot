# 🚀 Flight Reservation Microservices

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.0-brightgreen)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.0--RC1-blue)
![Maven](https://img.shields.io/badge/Maven-Wrapper-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

A modern **microservices architecture** implementation for flight reservation system built with **Spring Boot** and **Spring Cloud**.


## 🎯 Overview

This project demonstrates a complete microservices ecosystem for airline reservation system with:

- **Service Discovery** using Netflix Eureka
- **API Gateway** for unified access point
- **Inter-service Communication** with OpenFeign
- **Load Balancing** and **Circuit Breaker** patterns
- **Distributed Configuration** management
- **Database per Service** pattern with H2

## 🏗 Architecture

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────────┐
│   Client/Web    │────│   API Gateway    │────│   Eureka Server     │
│   Applications  │    │   Port: 8080     │    │   Port: 8761        │
└─────────────────┘    └──────────────────┘    └─────────────────────┘
                                │                        │
                                │                        │
                       ┌────────┴────────┐              │
                       │                 │              │
              ┌─────────▼──────────┐   ┌──▼──────────────▼──┐
              │  Flight Service    │   │ Reservation Service │
              │  Port: 8081        │───│ Port: 8082          │
              │  Database: MySql       │   │ Database: MySql │
              └────────────────────┘   └─────────────────────┘
```

## 🛠 Technologies

| Category | Technology |
|----------|------------|
| **Language** | Java 21 |
| **Framework** | Spring Boot 4.0.0 |
| **Cloud** | Spring Cloud 2025.1.0-RC1 |
| **Service Discovery** | Netflix Eureka |
| **API Gateway** | Spring Cloud Gateway |
| **Communication** | OpenFeign |
| **Database** | H2 (In-Memory) |
| **ORM** | Spring Data JPA / Hibernate |
| **Build Tool** | Maven |
| **Code Generation** | Lombok |

## 🎪 Services

### 🔍 Eureka Server
- **Port:** `8761`
- **Purpose:** Service discovery and registration
- **URL:** http://localhost:8761

### ✈️ Flight Service
- **Port:** `8081`
- **Purpose:** Flight management and seat inventory
- **Features:**
  - CRUD operations for flights
  - Seat availability management
  - Sample data initialization
- **Database:** H2 (flight_db)

### 🎫 Reservation Service
- **Port:** `8082`
- **Purpose:** Flight reservation management
- **Features:**
  - Create/manage reservations
  - Integration with Flight Service
  - Real-time seat booking
- **Database:** H2 (reservation_db)

### 🌐 API Gateway
- **Port:** `8080`
- **Purpose:** Single entry point and routing
- **Features:**
  - Load balancing
  - Request routing
  - Cross-cutting concerns

## 🚀 Quick Start

### Prerequisites
- ☕ **Java 21** or higher
- 📦 **Maven** (or use included wrapper)
- 💻 **Windows PowerShell** or **CMD**

### Installation & Running

1. **Clone the repository**
```bash
git clone <your-repo-url>
cd flight-reservation-microservices
```

2. **Start services in order:**

#### 🔍 1. Eureka Server
```powershell
cd eureka-serve
mvnw.cmd spring-boot:run
```

#### ✈️ 2. Flight Service
```powershell
cd flight-service
mvnw.cmd spring-boot:run
```

#### 🎫 3. Reservation Service
```powershell
cd reservation-service
mvnw.cmd spring-boot:run
```

#### 🌐 4. API Gateway (Optional)
```powershell
cd api-gateway
mvnw.cmd spring-boot:run
```

> **Note:** If PowerShell doesn't recognize `mvnw.cmd`, use: `cmd /c mvnw.cmd spring-boot:run`

### ✅ Verify Installation

Check if all services are running:
```powershell
netstat -ano | findstr "8761 8080 8081 8082"
```

Visit Eureka Dashboard: http://localhost:8761

## 📚 API Documentation

### Flight Service Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/flights` | List all flights |
| `GET` | `/api/flights/{id}` | Get flight by ID |
| `POST` | `/api/flights` | Create new flight |
| `PUT` | `/api/flights/{id}` | Update flight |
| `DELETE` | `/api/flights/{id}` | Delete flight |

### Reservation Service Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/reservations` | List all reservations |
| `GET` | `/api/reservations/{id}` | Get reservation by ID |
| `POST` | `/api/reservations` | Create new reservation |
| `PUT` | `/api/reservations/{id}/status` | Update reservation status |

### Sample Requests

#### Create Reservation
```bash
curl -X POST http://localhost:8082/api/reservations \
  -H "Content-Type: application/json" \
  -d '{
    "flightId": 1,
    "passengerName": "John Doe",
    "passengerEmail": "john.doe@example.com",
    "passengerPhone": "+1234567890",
    "numberOfSeats": 2
  }'
```

## 📸 Screenshots

### Eureka Dashboard
<img width="945" height="645" alt="image" src="https://github.com/user-attachments/assets/d55d704b-5a89-4dca-8f5e-339b2219fc88" />


### Postman Collection
<img width="945" height="647" alt="image" src="https://github.com/user-attachments/assets/be73774d-24a6-4aa3-98c6-ca744f9ff6ba" />
<img width="945" height="428" alt="image" src="https://github.com/user-attachments/assets/3e61ff19-8ad9-4b2f-99c2-cda55059a206" />
<img width="945" height="451" alt="image" src="https://github.com/user-attachments/assets/a13c40aa-f2c9-4ebf-b1b7-eb35fa76e1da" />
<img width="945" height="633" alt="image" src="https://github.com/user-attachments/assets/6956c943-d6b0-421a-8c8e-656b045509d7" />
<img width="945" height="391" alt="image" src="https://github.com/user-attachments/assets/ddd2da4a-41d4-4664-8e49-6f046203d47d" />
<img width="945" height="618" alt="image" src="https://github.com/user-attachments/assets/95602dc7-758f-45dd-8504-bcd7530484e8" />



## 🧪 Testing

### Postman Collection

1. Import the Postman collection (coming soon)
2. Set environment variables:
   - `eureka_url`: http://localhost:8761
   - `flight_url`: http://localhost:8081
   - `reservation_url`: http://localhost:8082
   - `gateway_url`: http://localhost:8080

### Sample Test Scenarios

1. **Service Discovery Test**
   - Start Eureka Server
   - Start Flight Service
   - Verify registration in Eureka Dashboard

2. **Flight Management Test**
   - GET all flights
   - Create new flight
   - Update flight details

3. **Reservation Flow Test**
   - Get available flights
   - Create reservation
   - Verify seat count update

## 🔧 Troubleshooting

### Common Issues

#### Port Already in Use
```bash
# Find process using port
netstat -ano | findstr :8082

# Kill process
taskkill /PID <process_id> /F
```

#### Service Not Registered in Eureka
- Check if Eureka Server is running
- Verify `application.properties` configuration
- Check network connectivity

#### 400 Bad Request on Reservation
- Ensure `Content-Type: application/json` header
- Verify JSON payload structure
- Check if Flight Service is accessible
- Validate seat availability

#### Maven Wrapper Issues
```bash
# Use cmd wrapper instead
cmd /c mvnw.cmd spring-boot:run

# Or use system Maven
mvn spring-boot:run
```

### Debug Mode

Enable debug logging:
```properties
logging.level.com.example=DEBUG
logging.level.org.springframework.cloud=DEBUG
```

## 📈 Performance & Monitoring

- **Health Checks:** Available at `/actuator/health`
- **Metrics:** Available at `/actuator/metrics` 
- **Service Info:** Available at `/actuator/info`

## 🤝 Contributing

1. **Fork** the repository
2. Create your **feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. Open a **Pull Request**

### Code Style
- Follow Spring Boot best practices
- Use meaningful commit messages
- Add unit tests for new features
- Update documentation as needed

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot Team for the amazing framework
- Netflix OSS for Eureka
- Spring Cloud Team for microservices tools

---

<div align="center">
  <strong>Built with ❤️ using Spring Boot & Spring Cloud</strong>
</div>

<div align="center">
  <sub>If you found this project helpful, please give it a ⭐</sub>
</div>
