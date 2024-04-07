FROM postgres
ENV POSTGRES_DB="bakerydb"
COPY starting_data.sql /docker-entrypoint-initdb.d/