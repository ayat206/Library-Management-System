# 📚 Library Management System

A Spring Boot–based RESTful API for managing library operations including books, members, transactions, system users with role-based access, and user activity logging.

---

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3  
- **Persistence**: Spring Data JPA, Hibernate  
- **Database**: MySQL  
- **Security**: Spring Security (Basic Auth)  
- **Build Tool**: Maven  

---

## 📐 Design Choices & Rationale

### 1. **Layered Architecture**

The project is split into:

- `entity`: JPA entities for data persistence  
- `repository`: Interfaces for database access using Spring Data JPA  
- `service` & `service_impl`: Business logic layer for each feature  
- `controller`: REST endpoints for interacting with the system  
- `dto`: Used only when mapping from client request bodies (e.g., during login or registration)  
- `config`: Spring Security and app configuration  

✅ **Benefit**: Separation of concerns, easier testing and maintainability.

---

### 2. **Role-Based Access Control**

Implemented three system roles:

- **ADMIN**  
- **LIBRARIAN**  
- **STAFF**  

Used Spring Security to restrict API access:

- Only admins can manage users  
- Librarians and admins can manage books and members  
- Staff can borrow/return books  

✅ **Benefit**: Enforces secure access and responsibility delegation.

---

### 3. **Secure Password Storage**

Passwords are hashed using `BCryptPasswordEncoder` before being stored in the database.

✅ **Benefit**: Protects user credentials from exposure in case of data leak.

---

### 4. **Custom User Authentication**

Implemented:

- `CustomUserDetailsService`  
- `CustomUserDetails`  
- Authentication via `/api/auth/login` using Spring Security's `AuthenticationManager`  

✅ **Benefit**: Allows authentication against our own `SystemUser` table and integration with Spring Security.

---

### 5. **Borrowing & Return Logic**

Each borrow transaction includes:

- Member  
- Book  
- Borrow Date  
- Return Date  
- Due Date (e.g., 14 days after borrow)  

✅ **Benefit**: Clear tracking of active/returned/overdue books.

---

### 6. **Hierarchical Book Classification**

- Each book is assigned a `Category`, which can be part of a hierarchy  
- Each book supports **multiple authors** and **one publisher**  

✅ **Benefit**: Models real-world metadata cleanly.

---

### 7. **User Activity Logging**

Logged user actions (like login, borrow, return) to the `user_logs` table.

✅ **Benefit**: Helps admins audit usage and detect misuse.

---

### 8. **Validation & Error Handling**

Basic validation is applied, and custom exceptions thrown for:

- Duplicate category names  
- Role not found  
- Book already borrowed  

✅ **Benefit**: Improved user feedback and debugging clarity.

---

## 📈 Future Improvements

- JWT-based authentication  
- Frontend UI (e.g., React or Angular)  
- Pagination and sorting for lists  
- Email notifications for overdue books  

---

## 📁 Sample Endpoints

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST   | `/api/auth/login`          | Public        | Login with username/password |
| POST   | `/api/users/register`      | Admin         | Create new system user |
| GET    | `/api/books`               | Authenticated | List all books |
| POST   | `/api/transactions/borrow` | Staff+        | Borrow a book |
| POST   | `/api/transactions/return` | Staff+        | Return a book |
| GET    | `/api/users/activity`      | Librarian+    | View user logs |

---

