# 🍱 Tiffyn — System Design

## 1. Overview

Tiffyn is a console-based Java application designed to simplify meal subscription management for students and working professionals.

The first version focuses on managing customers, vendors, meal plans, subscriptions, and orders through a menu-driven console interface.

The project is being developed using Core Java and will evolve gradually toward a database-backed and eventually web-based application.

---

## 2. Version 1 Scope

The initial version of Tiffyn will focus on:

* Customer management
* Vendor management
* Meal plan management
* Subscription management
* Order management
* File-based data persistence
* Input validation
* Exception handling
* Console-based interaction

Advanced features such as authentication, payment processing, admin dashboards, and online deployment are outside the scope of Version 1.

---

# 3. Actors

## 3.1 Customer

A customer uses Tiffyn to:

* Register an account
* View available meal plans
* Subscribe to a meal plan
* View their active subscription
* Cancel their subscription
* View their orders

---

## 3.2 Vendor

A vendor uses Tiffyn to:

* Register as a food/tiffin provider
* Add meal plans
* View their meal plans
* Update meal plans
* Remove meal plans
* View orders associated with their meal plans

---

## 3.3 Admin

An Admin is planned for a future version.

Potential responsibilities may include:

* Managing customers
* Managing vendors
* Monitoring the system
* Generating reports

Admin functionality will not be implemented in Version 1.

---

# 4. Core Domain Entities

Tiffyn Version 1 will contain five primary entities:

```text
Customer
Vendor
MealPlan
Subscription
Order
```

---

# 5. Customer

Represents a person who uses Tiffyn to purchase meal subscriptions.

### Attributes

```text
customerId
name
phone
email
address
```

### Responsibilities

* Store customer information
* Subscribe to meal plans
* View subscription information
* Cancel active subscriptions
* View orders

---

# 6. Vendor

Represents a person or business that provides tiffin/meal services.

### Attributes

```text
vendorId
name
phone
email
businessName
address
```

### Responsibilities

* Store vendor information
* Create meal plans
* Update meal plans
* Remove meal plans
* View orders associated with their meal plans

---

# 7. MealPlan

Represents a meal subscription plan offered by a vendor.

### Attributes

```text
mealPlanId
name
description
price
duration
vendorId
```

### Responsibilities

* Store meal plan information
* Define the price and duration of a plan
* Associate a meal plan with its vendor

### Example

```text
ID          : MP001
Name        : Monthly Veg Plan
Description : Lunch + Dinner
Price       : ₹3000
Duration    : 30 days
Vendor      : V001
```

---

# 8. Subscription

Represents a customer's subscription to a meal plan.

### Attributes

```text
subscriptionId
customerId
mealPlanId
startDate
endDate
status
```

### Responsibilities

* Track the customer's subscription
* Maintain subscription dates
* Maintain subscription status
* Support subscription cancellation

### Example

```text
ID          : S001
Customer    : C001
Meal Plan   : MP001
Start Date  : 01-09-2026
End Date    : 30-09-2026
Status      : ACTIVE
```

---

# 9. Order

Represents an individual meal/order associated with a customer and meal plan.

### Attributes

```text
orderId
customerId
mealPlanId
orderDate
status
```

### Responsibilities

* Track customer orders
* Track order dates
* Maintain order status

### Example

```text
ID          : O001
Customer    : C001
Meal Plan   : MP001
Date        : 01-09-2026
Status      : DELIVERED
```

---

# 10. Entity Relationships

The primary relationships in Tiffyn are:

```text
Vendor
   │
   │ offers
   ▼
MealPlan
   │
   │ subscribed through
   ▼
Subscription
   │
   │ belongs to
   ▼
Customer
   │
   │ places
   ▼
Order
```

### Vendor → MealPlan

One vendor can offer multiple meal plans.

```text
Vendor 1 ──────── * MealPlan
```

### Customer → Subscription

A customer can have multiple subscriptions over time.

For Version 1:

> A customer can have only one ACTIVE subscription at a time.

```text
Customer 1 ──────── * Subscription
```

### MealPlan → Subscription

A meal plan can have multiple customers subscribed to it.

```text
MealPlan 1 ──────── * Subscription
```

### Customer → Order

A customer can have multiple orders.

```text
Customer 1 ──────── * Order
```

---

# 11. Business Rules

The following rules will be enforced by the application.

### BR-01 — Valid Meal Plan

A customer cannot subscribe to a meal plan that does not exist.

### BR-02 — One Active Subscription

A customer cannot have more than one active subscription at the same time.

### BR-03 — Valid Subscription Dates

A subscription must have a valid start date and end date.

### BR-04 — Subscription Ownership

A customer can cancel only their own active subscription.

### BR-05 — Vendor Ownership

A vendor can modify or remove only their own meal plans.

### BR-06 — Valid Price

A meal plan must have a positive price.

### BR-07 — Valid Duration

A meal plan must have a valid positive duration.

### BR-08 — Valid Order

An order must reference an existing customer and meal plan.

---

# 12. Application Flow

When the application starts, the user will see the main menu.

```text
========================================
              🍱 TIFFYN
       Meal Subscription Manager
========================================

1. Customer
2. Vendor
0. Exit
```

---

## Customer Menu

```text
1. Register
2. View Meal Plans
3. Subscribe
4. View Subscription
5. Cancel Subscription
6. View Orders
0. Back
```

---

## Vendor Menu

```text
1. Register
2. Add Meal Plan
3. View My Meal Plans
4. Update Meal Plan
5. Remove Meal Plan
6. View Orders
0. Back
```

---

# 13. Package Architecture

The initial Java package structure will follow a simple layered design.

```text
com.tiffyn
│
├── model
│   ├── Customer
│   ├── Vendor
│   ├── MealPlan
│   ├── Subscription
│   └── Order
│
├── service
│   ├── CustomerService
│   ├── VendorService
│   ├── MealPlanService
│   ├── SubscriptionService
│   └── OrderService
│
├── repository
│
├── exception
│
├── ui
│
├── util
│
└── Main
```

### Model

Contains the core domain entities.

### Service

Contains application/business logic.

### Repository

Responsible for data persistence.

Version 1 will use file-based storage.

### Exception

Contains custom exceptions used by the application.

### UI

Handles console interaction and menus.

### Util

Contains reusable utility classes and helper methods.

---

# 14. Data Persistence Strategy

Tiffyn will evolve its persistence layer over multiple versions.

### Version 1

```text
Application
     ↓
Service
     ↓
Repository
     ↓
Files
```

File handling will be used to persist application data.

### Version 2

```text
Application
     ↓
Service
     ↓
Repository
     ↓
JDBC
     ↓
MySQL
```

This will allow the project to transition from Core Java to database-backed development without completely redesigning the application.

---

# 15. Future Architecture

As Tiffyn evolves toward Spring Boot, the architecture is expected to become:

```text
Client
   ↓
REST Controller
   ↓
Service Layer
   ↓
Repository Layer
   ↓
Database
```

The current Version 1 design intentionally avoids introducing this complexity too early.

---

# 16. Development Principles

The following principles will guide development:

* Keep each class responsible for a clear purpose.
* Use encapsulation for domain objects.
* Validate input before processing it.
* Keep business logic inside the service layer.
* Avoid unnecessary duplication.
* Handle expected errors using meaningful exceptions.
* Write readable and maintainable code.
* Commit changes in small, meaningful increments.
* Add tests for important business logic.
* Document major architectural decisions.

---

# 17. Version 1 Development Flow

```text
System Design
      ↓
Domain Models
      ↓
Services
      ↓
Console UI
      ↓
Validation & Exceptions
      ↓
File Persistence
      ↓
JUnit Tests
      ↓
Integration & Refinement
```

---

# 18. Future Scope

The following features may be introduced in future versions:

* Authentication and authorization
* Admin module
* MySQL database
* JDBC
* Servlets and JSP
* Spring Framework
* Spring Boot
* REST APIs
* Spring Security
* Payment integration
* Web-based frontend
* Cloud deployment
* Notifications
* Advanced reporting

These features are intentionally excluded from Version 1 to keep the initial implementation focused and manageable.

---

# 19. Current Status

```text
System Design       ✅ Completed
Domain Modeling     🔄 Next
Implementation      ⏳ Pending
Testing             ⏳ Pending
Persistence         ⏳ Pending
```

---

## 🎯 Next Task

The next implementation task is to create the domain model classes:

```text
Customer
Vendor
MealPlan
Subscription
Order
```

These classes will form the foundation of the Tiffyn application.
