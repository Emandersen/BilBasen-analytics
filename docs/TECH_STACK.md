# Tech Stack

## Backend

### Framework & Core
- **Java 21** - Programming language
- **Spring Boot 4.0.2** - Application framework
- **Spring Data JPA** - ORM and database abstraction
- **Spring Data JDBC** - Lightweight JDBC abstraction
- **Spring Web MVC** - RESTful API development

### Database
- **PostgreSQL** - Relational database (runtime)

### Development Tools
- **Lombok** - Code generation (annotations for getters/setters, builders, etc.)
- **Spring Boot DevTools** - Hot reload and development utilities
- **Maven** - Dependency management and build tool

### Testing
- **Spring Boot Test Starters** - Testing framework for JDBC, JPA, and Web MVC

## Frontend

### Framework & Core
- **SvelteKit 2.50.1** - Full-stack framework for Svelte
- **Svelte 5.48.2** - UI framework
- **TypeScript 5.9.3** - Type-safe JavaScript
- **Vite 7.3.1** - Build tool and dev server

### Development Tools
- **ESLint** - Code linting
- **Prettier** - Code formatting
- **Svelte Check** - Type checking for Svelte components

### Testing
- **Vitest 4.0.18** - Unit testing framework
- **Playwright 1.58.1** - Browser testing
- **@vitest/browser-playwright** - Browser-based testing integration
- **@vitest/coverage-v8** - Code coverage

### UI Libraries
- **@neoconfetti/svelte** - Confetti animations
- **@fontsource/fira-mono** - Typography

## Architecture

### Backend Architecture
- **REST API** - JSON-based API endpoints
- **Layered Architecture**:
  - Controllers handle HTTP requests
  - Services contain business logic
  - Repositories manage database operations
  - DTOs for data transfer
  - Entities (Models) for database mapping

### Frontend Architecture
- **SvelteKit** - File-based routing and SSR/SSG capabilities
- **Component-based** - Reusable Svelte components
- **Type-safe** - Full TypeScript support
