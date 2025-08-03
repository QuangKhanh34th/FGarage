# 🚗 FGarage - Automotive Garage Management System

A web-based garage management system built with **Java EE** and **SQL Server** for managing automotive services, customer relationships, and business operations.

## 🔧 Core Features

- **Multi-Role Authentication** - Customer, Sales, and Mechanic dashboards
- **Service Ticket Management** - Track repairs, parts, and labor
- **Customer & Car Inventory** - Complete customer and vehicle database
- **Sales & Invoicing** - Handle transactions and generate reports
- **Parts Management** - Track inventory and usage
- **Statistics & Reports** - Business analytics and performance metrics

## 🛠️ Technology Stack

- **Backend**: Java EE (Servlets, JSP, JSTL)
- **Database**: Microsoft SQL Server
- **Frontend**: Bootstrap 5, JavaScript, CSS
- **Web Server**: Apache Tomcat 10.0.27
- **Build Tool**: Apache Ant
- **Architecture**: MVC Pattern with DAO Layer

## 🚀 Quick Start

1. **Setup Database**: Run the CarDealership SQL script in SQL Server
2. **Configure Connection**: Update `DBUtils.java` with your SQL Server credentials
3. **Deploy**: Build with Ant and deploy WAR to Tomcat
4. **Access**: Navigate to `/Login/index.jsp` and authenticate by role and Name provided in the script (use vietnamese name with tone marks)

## 📊 Database Schema

Core entities: `Customer`, `Cars`, `ServiceTicket`, `Mechanic`, `Parts`, `Service`, `SalesInvoice`

---

*Built as an assignment for PRJ301 course*
