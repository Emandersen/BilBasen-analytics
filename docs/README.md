# BilBasen Analytics Documentation

Welcome to the BilBasen Analytics documentation. This project consists of a Spring Boot backend API and a SvelteKit frontend application.

## Documentation Contents

- **[TECH_STACK.md](./TECH_STACK.md)** - Complete overview of technologies, frameworks, and libraries used in both backend and frontend
- **[FOLDER_STRUCTURE.md](./FOLDER_STRUCTURE.md)** - Detailed folder structure and organization of the project
- **[BILBASEN_API_REFERENCE.md](./BILBASEN_API_REFERENCE.md)** - Documented API of the popular Danish car Market, BilBasen

## Quick Overview

### Backend
A REST API built with Spring Boot 4 and Java 21, returning JSON responses and interacting with a PostgreSQL database.

**Base package:** `com.car.analytics.app`

**Key layers:**
- Controllers (REST endpoints)
- Services (business logic)
- Repositories (database access)
- Models/Entities (JPA entities)
- DTOs (data transfer objects)

### Frontend
A modern web application built with SvelteKit 2 and TypeScript, featuring file-based routing and component-based architecture.

**Key features:**
- SvelteKit 2.50.1 with Svelte 5
- TypeScript for type safety
- Vite for fast development and builds
- Vitest and Playwright for testing

## Getting Started

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

## Project Structure
```
BilBasen-analytics/
├── backend/    # Spring Boot REST API
├── frontend/   # SvelteKit application
└── docs/       # This documentation
```

For more details, see [FOLDER_STRUCTURE.md](./FOLDER_STRUCTURE.md).
