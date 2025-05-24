# Rainbow Easy Walk – Billing & Inventory Management System

A web-based inventory and billing system tailored for retail footwear shops. Built with **Spring Boot**, it simplifies day-to-day operations like product management, billing, discount handling, profit tracking, and more — with a mobile-friendly interface.

---

## 🧾 Features

- 🔍 **Advanced Product Search & Filters**
  - Search by **product code**, **name**, **category**, etc.
- 🛒 **Cart-Based Billing Workflow**
  - Add multiple items to a cart
  - Apply item-wise or total bill discount
  - Live stock validation during billing
- 📦 **Stock Management**
  - Add, update, delete products
  - Track available stock in real-time
- 📈 **Sales & Reporting**
  - Daily, weekly, monthly **sales & profit** reports
- 📱 **Mobile-Friendly UI** for use on any device

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring MVC, Spring Data JPA, Hibernate
- **Database**: MySQL (hosted on Railway)
- **Deployment**: Render (backend), Railway (DB)

---

## 📁 Project Structure

```
rainbow-easy-walk/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/rainbow/
│       │       ├── controller/
│       │       ├── model/
│       │       ├── repository/
│       │       ├── service/
│       │       └── RainbowEasyWalkApplication.java
│       └── resources/
│           ├── static/
│           ├── templates/
│           └── application.properties
├── pom.xml
└── README.md
```

---

## ⚙️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/mohamedshahban94/rainbow-easy-walk.git
cd rainbow-easy-walk
```

### 2. Configure your database

Edit `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rainbow
spring.datasource.username=root
spring.datasource.password=5432
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Visit: `http://localhost:8080`

---

## 🔌 API Endpoints (Sample)

### 📦 Product Management

| Method | Endpoint                   | Description                       |
|--------|----------------------------|-----------------------------------|
| GET    | `/products`                | Get all products                  |
| GET    | `/products/search?name=N`  | Search by product name            |
| GET    | `/products/code/{code}`    | Search by product code            |
| GET    | `/products/filter?cat=X`   | Filter by category or brand       |
| POST   | `/products`                | Add a new product                 |
| PUT    | `/products/{id}`           | Update product details            |
| DELETE | `/products/{id}`           | Delete a product                  |

---

### 🛒 Cart & Billing

| Method | Endpoint            | Description                         |
|--------|---------------------|-------------------------------------|
| POST   | `/cart/add`         | Add item to the cart                |
| GET    | `/cart`             | View cart with total + discounts    |
| DELETE | `/cart/remove/{id}` | Remove item from the cart           |
| POST   | `/billing/checkout` | Finalize bill, reduce stock, save sale |

---

### 📈 Sales & Reports

| Method | Endpoint                | Description               |
|--------|-------------------------|---------------------------|
| GET    | `/sales`                | Get all sales             |
| GET    | `/sales/daily`          | Daily sales report        |
| GET    | `/sales/weekly`         | Weekly sales report       |
| GET    | `/sales/monthly`        | Monthly sales report      |

---

## 📊 Future Enhancements

- 🔐 Role-based login (Admin / Cashier)
- 📤 Export bills and sales to **Excel or PDF**
- ☁️ Upload product images to **cloud storage**
- 🔄 Offline-first billing with sync
- 🛠️ REST API **versioning** for maintainability
- 📈 Dashboard with charts (profit trends, top sellers)


