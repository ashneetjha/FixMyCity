# FixMyCity

FixMyCity is a civic intelligence platform for reporting local infrastructure problems. It combines citizen reports with duplicate detection, ward clusters, transparent progress updates, and a public dashboard.

## Stack

- **Frontend:** React, TypeScript, Vite, responsive CSS, Leaflet map
- **Backend:** Java 21, Spring Boot, Spring Web, Spring Data JPA, Bean Validation
- **Data:** H2 for local development, PostgreSQL on Render in production

## Run locally

Start the API:

```bash
cd backend
mvn spring-boot:run
```

Start the web app in a second terminal:

```bash
cd frontend
npm install
npm run dev
```

The client runs on `http://localhost:5173` and forwards `/api` calls to the API on port `8080`.

## API overview

| Method | Path | Purpose |
| --- | --- | --- |
| GET | `/api/issues` | Filtered list of civic issues |
| POST | `/api/issues` | Report an issue and receive a duplicate signal |
| GET | `/api/issues/{id}` | Issue detail and timeline |
| PATCH | `/api/issues/{id}/status` | Update status (staff workflow) |
| GET | `/api/dashboard` | Dashboard metrics and category distribution |
| GET | `/api/clusters` | Location-based issue clusters |
| GET | `/api/issues/nearby` | Nearby reports for duplicate awareness |

## Deployment

`render.yaml` defines a PostgreSQL database, Spring Boot API, and static React site. Push this repository to GitHub, then create a Render Blueprint from the repository. Set `VITE_API_URL` on the static site to the deployed API URL plus `/api` if it differs from the same Render origin.

