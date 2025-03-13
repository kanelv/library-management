# Library Management
Description: an simple snippet project to simulate the issue that we faced when using OrientDb v3.2.24 in embedded mode

## Technologies used:
- Spring Boot v3.3.3
- Spring Data JPA (Hibernate 6 is the default JPA implementation)
- PostgreSQL 15
- Java 17
- Maven 3

# Setup
## Requirement
- Java 17


## Build project
```bash
mvn spring-boot:run
```

## Database

### Database migration guidelines

### Migration using Flyway

#### Flyway uses SQL scripts for migrations.
Please follow the steps when you are making new changes in database.
1. Never edit/update the old sql files in src/main/resources/db/migration
2. Write new sql script files. Naming convention for migration scripts:
   ```V[date_in_which_file_is_created]_01__[two underscores][file_name(describe_what_is_sql_script_is_about].sql```

For example:
V20200924_01__update_baseline_table.sql

Make sure to check if the migration works(has been applied or not).
You can check flyway_schema_history in pgadmin to check if migration has been applied or not.

#### How to run it
1. create the flyway_schema_history table if it doesn't already exist. The table is used by Flyway to track the 
applied migrations.
```bash
mvn flyway:baseline
```
2. to apply pending migrations
```bash
mvn flyway:migrate
```

### Fixture
```bash

```

## References
https://www.coursera.org/in/articles/java-projects
https://neon.tech/guides/spring-boot-flyway
