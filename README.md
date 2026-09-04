# Shop Inventory Management System

A backend system that lets a shop owner manage products, track stock levels, and record sales — built with Spring Boot and MySQL.

## What it does

A shop owner can add, view, update, and delete products. Each product has a stock quantity. When a sale is recorded, the stock is automatically reduced — and the system prevents overselling by rejecting any sale that would take stock below zero. Products with low stock (below a configurable threshold) can be listed separately, so the owner always knows what needs restocking.

## Tech stack

- **Java 21**
- **Spring Boot 4.1.1** — Spring Web, Spring Data JPA, Validation
- **MySQL** — relational database
- **Lombok** — reduces boilerplate (getters/setters/constructors)
- **Maven** — build tool
- **Postman** — used for manual API testing during development

## Features

- Full CRUD on products (name, price, quantity, category)
- Suppliers as a separate entity, linked to products (one supplier can provide many products)
- Recording a sale automatically reduces the linked product's stock
- **Overselling prevention** — a sale is rejected with a clear error if requested quantity exceeds available stock
- **Low-stock detection** — fetch all products below a given quantity threshold (default: 10)
- Centralized exception handling (`@RestControllerAdvice`) — clean, readable error responses instead of raw stack traces
- Input validation on entities (e.g. price must be positive, name cannot be blank)
- Database password kept out of source control via an environment variable

## Architecture

The project follows a standard layered structure:

```
Controller  →  Service  →  Repository  →  Database
```

- **Controllers** expose REST endpoints and handle HTTP requests/responses
- **Services** hold the business logic — including the overselling check and low-stock filtering
- **Repositories** (Spring Data JPA) handle all database access, with no hand-written SQL
- **Entities** (`Product`, `Sale`, `Supplier`) map directly to database tables via Hibernate

## Entity relationships

- `Sale` → `Product` (many sales can reference the same product)
- `Product` → `Supplier` (many products can come from the same supplier)

## API Endpoints

### Products — `/api/products`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/products` | Add a new product |
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get a product by id |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |
| GET | `/api/products/low-stock?threshold=10` | Get products below the given stock threshold |

### Sales — `/api/sales`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/sales?productId={id}&quantitySold={n}` | Record a sale (reduces stock; rejected if stock is insufficient) |
| GET | `/api/sales` | Get all recorded sales |

### Suppliers — `/api/suppliers`
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/suppliers` | Add a new supplier |
| GET | `/api/suppliers` | Get all suppliers |
| GET | `/api/suppliers/{id}` | Get a supplier by id |
| PUT | `/api/suppliers/{id}` | Update a supplier |
| DELETE | `/api/suppliers/{id}` | Delete a supplier |

## Running locally

1. Clone the repo and open it in IntelliJ (or your preferred IDE)
2. Create a MySQL database (or let the app create it automatically — `createDatabaseIfNotExist=true` is set)
3. Set the `DB_PASSWORD` environment variable to your MySQL password (in your run configuration)
4. Run `ShopInventoryManagementApplication.java`
5. Test the endpoints above using Postman or any REST client

## What I'd add next

- JWT-based authentication so only a logged-in shop owner can manage inventory
- Pagination on the "get all products" and "get all sales" endpoints
- A simple frontend to visualize stock levels and sales history
