# FixMyCity — Faculty Screenshot Evidence

These screenshots document the working project for demonstration and viva review.

| File | What it proves |
| --- | --- |
| `01-database-issue-table.png` | Local H2 database query showing the `ISSUE` table, columns, and seeded civic reports. |
| `02-live-deployed-dashboard.png` | Deployed FixMyCity frontend on Render with responsive dark-mode UI. |
| `03-live-api-dashboard.png` | Live Render Spring Boot API dashboard endpoint response. |
| `04-local-api-dashboard.png` | Local Spring Boot API dashboard endpoint response. |

## Database details

- Local development database: H2 (PostgreSQL compatibility mode)
- Production database: PostgreSQL on Render
- Entity tables demonstrated: `ISSUE`, `ISSUE_UPDATE`
- Evidence query: `SELECT ID, TITLE, CATEGORY, STATUS, PRIORITY, ADDRESS, REPORTER_NAME, CREATED_AT FROM ISSUE ORDER BY ID;`

The screenshots deliberately exclude connection passwords and other credentials.
