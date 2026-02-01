# Folder Structure

## Project Root

```
BilBasen-analytics/
├── backend/          # Spring Boot REST API
├── frontend/         # SvelteKit application
├── docs/            # Project documentation
├── .git/            # Git repository
├── .gitignore       # Git ignore rules
└── LICENSE          # Project license
```

## Backend Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/car/analytics/app/
│   │   │       ├── AppApplication.java       # Main Spring Boot application
│   │   │       ├── controller/               # REST controllers (@RestController)
│   │   │       ├── service/                  # Business logic (@Service)
│   │   │       ├── repository/               # Data access layer (@Repository)
│   │   │       ├── model/                    # JPA entities (@Entity)
│   │   │       ├── dto/                      # Data Transfer Objects
│   │   │       ├── config/                   # Configuration classes
│   │   │       └── exception/                # Custom exceptions & handlers
│   │   └── resources/
│   │       ├── application.properties        # Application configuration
│   │       └── example.application.properties # Configuration template
│   └── test/                                 # Test files
├── target/                                   # Maven build output
├── pom.xml                                   # Maven dependencies
├── mvnw                                      # Maven wrapper (Unix)
├── mvnw.cmd                                  # Maven wrapper (Windows)
└── HELP.md                                   # Spring Boot help
```

### Backend Package Descriptions

- **controller/** - Handles HTTP requests and returns JSON responses. Annotated with `@RestController` and `@RequestMapping`.
- **service/** - Contains business logic and orchestrates operations between controllers and repositories.
- **repository/** - Interfaces extending JpaRepository for database CRUD operations.
- **model/** - JPA entity classes representing database tables. Annotated with `@Entity`.
- **dto/** - Plain objects for transferring data between API and clients (request/response payloads).
- **config/** - Spring configuration classes for database, security, CORS, etc.
- **exception/** - Custom exception classes and `@ControllerAdvice` for global exception handling.

## Frontend Structure

```
frontend/
├── src/
│   ├── routes/                   # SvelteKit file-based routing
│   │   ├── +page.svelte         # Home page
│   │   ├── +layout.svelte       # Root layout
│   │   ├── about/               # About page route
│   │   └── sverdle/             # Example game routes
│   ├── lib/                     # Reusable components & utilities
│   │   └── images/              # Image assets
│   ├── stories/                 # Storybook stories (if using)
│   ├── app.html                 # HTML template
│   ├── app.d.ts                 # TypeScript type definitions
│   └── demo.spec.ts             # Demo test file
├── static/                      # Static assets (served as-is)
├── node_modules/                # NPM dependencies
├── .svelte-kit/                 # SvelteKit build artifacts
├── package.json                 # NPM dependencies & scripts
├── package-lock.json            # Locked dependency versions
├── svelte.config.js             # SvelteKit configuration
├── vite.config.ts               # Vite build configuration
├── tsconfig.json                # TypeScript configuration
├── eslint.config.js             # ESLint configuration
├── .prettierrc                  # Prettier formatting rules
├── .prettierignore              # Prettier ignore patterns
├── .nvmrc                       # Node version specification
├── .npmrc                       # NPM configuration
└── README.md                    # Frontend documentation
```

### Frontend Folder Descriptions

- **routes/** - SvelteKit's file-based routing system. Each folder represents a route, `+page.svelte` files are pages.
- **lib/** - Shared components, utilities, and helper functions that can be imported with `$lib` alias.
- **static/** - Public assets served directly (images, fonts, favicon, etc.).
- **stories/** - Component stories for development and documentation.

## Key Configuration Files

### Backend
- `pom.xml` - Maven project configuration, dependencies, and build plugins
- `application.properties` - Database connection, server port, JPA settings

### Frontend
- `package.json` - NPM scripts and dependencies
- `svelte.config.js` - SvelteKit adapter and preprocessing configuration
- `vite.config.ts` - Vite dev server and build settings
- `tsconfig.json` - TypeScript compiler options and path aliases
