# FixMyCity — Faculty Screenshot Evidence

These screenshots document the working project for demonstration and viva review.

| File | What it proves |
| --- | --- |
| `01-database-issue-table.png` | Local H2 database query showing the `ISSUE` table, columns, and seeded civic reports. |
| `02-live-deployed-dashboard.png` | Deployed FixMyCity frontend on Render with responsive dark-mode UI. |
| `03-live-api-dashboard.png` | Live Render Spring Boot API dashboard endpoint response. |
| `04-local-api-dashboard.png` | Local Spring Boot API dashboard endpoint response. |
| `05-production-postgresql-jdbc-config.png` | Public GitHub source view of the Spring Boot production profile, including `DATABASE_URL`, the PostgreSQL driver and PostgreSQL dialect. |
| `06-render-deployment-config.png` | Public GitHub source view of `render.yaml`, linking `fixmycity-api` to the Render `fixmycity-db` database connection string. |

## Database details

- Local development database: H2 (PostgreSQL compatibility mode)
- Production database: PostgreSQL on Render
- Entity tables demonstrated: `ISSUE`, `ISSUE_UPDATE`
- Evidence query: `SELECT ID, TITLE, CATEGORY, STATUS, PRIORITY, ADDRESS, REPORTER_NAME, CREATED_AT FROM ISSUE ORDER BY ID;`
- Production JDBC configuration: `org.postgresql.Driver` + `${DATABASE_URL}` supplied by the Render database connection string.

The screenshots deliberately exclude connection passwords and other credentials.
