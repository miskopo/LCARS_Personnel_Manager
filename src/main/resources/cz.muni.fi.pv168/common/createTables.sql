CREATE TABLE "CREWMAN" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "NAME" VARCHAR(255) NOT NULL,
    "RANK" VARCHAR(255) NOT NULL
);

CREATE TABLE "SHIP" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "DESIGNATION" VARCHAR(255) NOT NULL,
    "TYPE" VARCHAR(12) NOT NULL,
    "WARP" FLOAT NOT NULL);


CREATE TABLE "ASSIGNMENT" (
    "ID" BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "SHIP" BIGINT NOT NULL,
    "CREWMAN" BIGINT NOT NULL,
    "STARTDATE" DATE,
    "ENDDATE" DATE
);