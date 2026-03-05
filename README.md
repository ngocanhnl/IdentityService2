# Identity Service 2 (ngocanhdevteria2)

Dịch vụ xác thực và quản lý danh tính (Identity Service) được xây dựng với Spring Boot 3.2.2, cung cấp các tính năng quản lý người dùng, vai trò (roles), quyền hạn (permissions) và xác thực JWT.

## 📋 Mục Lục

- [Tính Năng](#tính-năng)
- [Yêu Cầu](#yêu-cầu)
- [Cài Đặt](#cài-đặt)
- [Cấu Hình](#cấu-hình)
- [Cấu Trúc Project](#cấu-trúc-project)
- [API Endpoints](#api-endpoints)
- [Công Nghệ Sử Dụng](#công-nghệ-sử-dụng)
- [Chạy Project](#chạy-project)
- [Testing](#testing)

## ✨ Tính Năng

- ✅ Quản lý người dùng (User management)
- ✅ Quản lý vai trò (Role management)
- ✅ Quản lý quyền hạn (Permission management)
- ✅ Xác thực JWT (JWT Authentication)
- ✅ OAuth2 Resource Server
- ✅ Xác thực và phân quyền (Authentication & Authorization)
- ✅ Hỗ trợ làm mới token (Token refresh)
- ✅ Quản lý token không hợp lệ (Invalid token management)

## 🔧 Yêu Cầu

- **Java**: 21 hoặc cao hơn
- **Maven**: 3.6 hoặc cao hơn
- **MySQL**: 5.7 hoặc cao hơn (hoặc server tương thích)
- **Spring Boot**: 3.2.2

## 📦 Cài Đặt

1. **Clone repository**:
   ```bash
   git clone <repository-url>
   cd identity2
   ```

2. **Cài đặt dependencies** (Maven tự động tải khi chạy):
   ```bash
   mvn install
   ```

3. **Cấu hình cơ sở dữ liệu MySQL**:
   - Tạo cơ sở dữ liệu: `identity_service`
   - Đảm bảo MySQL chạy trên `localhost:3307`

## ⚙️ Cấu Hình


### Cấu Hình Database

Thay đổi các thông số kết nối MySQL theo yêu cầu:
- `url`: Đường dẫn MySQL (mặc định: `localhost:3307`)
- `username`: Tên người dùng (mặc định: `root`)
- `password`: Mật khẩu (mặc định: `root`)

## 📁 Cấu Trúc Project

```
identity2/
├── src/
│   ├── main/
│   │   ├── java/com/ngocanhdevteria2/demo/
│   │   │   ├── Ngocanhdevteria2Application.java       # Main class
│   │   │   ├── configuration/
│   │   │   │   ├── ApplicationInitConfig.java         # Khởi tạo ứng dụng
│   │   │   │   ├── CustomJwtDecoder.java              # Decoder JWT
│   │   │   │   ├── JwtAuthenticationEntryPoint.java   # Xử lý lỗi xác thực
│   │   │   │   └── SecurityConfig.java                # Cấu hình Spring Security
│   │   │   ├── controller/
│   │   │   │   ├── AuthenticationController.java      # Xác thực
│   │   │   │   ├── UserController.java                # Quản lý người dùng
│   │   │   │   ├── RoleController.java                # Quản lý vai trò
│   │   │   │   └── PermissionController.java          # Quản lý quyền hạn
│   │   │   ├── entity/
│   │   │   │   ├── User.java                          # Entity người dùng
│   │   │   │   ├── Role.java                          # Entity vai trò
│   │   │   │   ├── Permission.java                    # Entity quyền hạn
│   │   │   │   └── InvalidatedToken.java              # Entity token không hợp lệ
│   │   │   ├── service/                               # Business logic
│   │   │   ├── repository/                            # Data access layer
│   │   │   ├── dto/                                   # Data Transfer Objects
│   │   │   ├── mapper/                                # MapStruct mappers
│   │   │   ├── validator/                             # Custom validators
│   │   │   ├── enums/                                 # Enums
│   │   │   └── exception/                             # Custom exceptions
│   │   └── resources/
│   │       ├── application.yaml                       # Cấu hình chính
│   │       ├── static/                                # Static resources
│   │       └── templates/                             # Templates
│   └── test/
│       ├── java/com/ngocanhdevteria2/demo/
│       │   ├── controller/                            # Controller tests
│       │   └── service/                               # Service tests
│       └── resources/
├── pom.xml                                            # Maven configuration
└── README.md                                          # File này
```

## 🔌 API Endpoints

### Authentication Controller
- `POST /identity/api/auth/login` - Đăng nhập
- `POST /identity/api/auth/refresh` - Làm mới token
- `POST /identity/api/auth/logout` - Đăng xuất

### User Controller
- `GET /identity/api/users` - Lấy danh sách người dùng
- `GET /identity/api/users/{id}` - Lấy thông tin người dùng
- `POST /identity/api/users` - Tạo người dùng mới
- `PUT /identity/api/users/{id}` - Cập nhật người dùng
- `DELETE /identity/api/users/{id}` - Xóa người dùng

### Role Controller
- `GET /identity/api/roles` - Lấy danh sách vai trò
- `POST /identity/api/roles` - Tạo vai trò mới
- `PUT /identity/api/roles/{id}` - Cập nhật vai trò
- `DELETE /identity/api/roles/{id}` - Xóa vai trò

### Permission Controller
- `GET /identity/api/permissions` - Lấy danh sách quyền hạn
- `POST /identity/api/permissions` - Tạo quyền hạn mới
- `PUT /identity/api/permissions/{id}` - Cập nhật quyền hạn
- `DELETE /identity/api/permissions/{id}` - Xóa quyền hạn

## 🛠️ Công Nghệ Sử Dụng

| Công Nghệ | Phiên Bản | Mục Đích |
|-----------|-----------|---------|
| Spring Boot | 3.2.2 | Framework chính |
| Java | 21 | Ngôn ngữ lập trình |
| Spring Data JPA | 3.2.2 | ORM, Database access |
| Spring Security | Latest | Xác thực & Phân quyền |
| Spring OAuth2 Resource Server | Latest | OAuth2 support |
| MySQL | 5.7+ | Database |
| Lombok | 1.18.30 | Giảm boilerplate code |
| MapStruct | 1.5.5.Final | DTO mapping |
| JUnit 5 | Latest | Unit testing |
| Mockito | Latest | Mocking for tests |
| Springdoc OpenAPI | Latest | API documentation |

## 🚀 Chạy Project

### Sử dụng Maven

1. **Xây dựng project**:
   ```bash
   mvn clean build
   ```

2. **Chạy ứng dụng**:
   ```bash
   mvn spring-boot:run
   ```

3. **Xây dựng JAR file**:
   ```bash
   mvn clean package
   ```

4. **Chạy JAR file**:
   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

### Trên Windows

Sử dụng script Maven được cung cấp:
- `mvnw.cmd clean install`
- `mvnw.cmd spring-boot:run`

### Trên Linux/Mac

Sử dụng script Maven:
- `./mvnw clean install`
- `./mvnw spring-boot:run`

Ứng dụng sẽ khởi động trên: **http://localhost:8080/identity**

## 🧪 Testing

Chạy các kiểm tra:

```bash
# Chạy tất cả tests
mvn test

# Chạy test cụ thể
mvn test -Dtest=UserControllerTest

# Chạy test với coverage report
mvn clean test jacoco:report
```

### Test Files
- `UserControllerTest.java` - Unit tests cho UserController
- `UserControllerIntegrationTest.java` - Integration tests
- `UserServiceTest.java` - Service layer tests

## 🔐 Bảo Mật JWT

- **Signer Key**: Khóa được sử dụng để ký token
- **Valid Duration**: 20 giây
- **Refreshable Duration**: 120 giây

Thay đổi `signerKey` và `valid-duration` trong `application.yaml` để phù hợp với yêu cầu bảo mật của bạn.

## 📝 Ghi Chú

- Đảm bảo MySQL server đang chạy trước khi khởi động ứng dụng
- Port mặc định: `8080`, context path: `/identity`
- Database được tự động tạo bảng theo Hibernate DDL auto: update

## 📧 Liên Hệ

Dự án được phát triển bởi: **ngocanhdevteria2**

## 📄 License

Dự án này được cung cấp dưới mục đích học tập và sử dụng nội bộ.

---

**Được tạo**: 2026-03-05
