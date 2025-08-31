-- Optional: enable pgcrypto if you plan to use gen_random_uuid() defaults later
-- create extension if not exists pgcrypto;

-- ========== OWNER ==========
create table owner (
  id          uuid not null,
  created_at  timestamp(6),
  deleted_at  timestamp(6),
  name        varchar(255),
  updated_at  timestamp(6),
  version     int4,
  constraint pk_owner primary key (id)
);

-- ========== VEHICLE ==========
create table vehicle (
  id          uuid not null,
  created_at  timestamp(6),
  deleted_at  timestamp(6),
  description varchar(255),
  type        varchar(255),
  updated_at  timestamp(6),
  version     int4,
  vin         varchar(255),
  owner_id    uuid not null,
  constraint pk_vehicle primary key (id),
  constraint fk_vehicle_owner
    foreign key (owner_id) references owner(id),
  constraint ck_vehicle_type
    check (type::text = any (array['CAR','VAN','BUS']))
);

-- ========== DRIVER ==========
create table driver (
  id          uuid not null,
  created_at  timestamp(6),
  deleted_at  timestamp(6),
  license     varchar(255),
  lat         numeric(38,2),
  lon         numeric(38,2),
  name        varchar(255),
  rate        varchar(255),
  status      varchar(255),
  updated_at  timestamp(6),
  version     int4,
  constraint pk_driver primary key (id),
  constraint ck_driver_license
    check (license::text = any (array['A','B','C'])),
  constraint ck_driver_rate
    check (rate::text = any (array['BAD','OK','GOOD','EXCELLENT'])),
  constraint ck_driver_status
    check (status::text = any (array['AVAILABLE','ON_TRIP','INACTIVE']))
);

-- ========== MOVE ==========
create table move (
  id          uuid not null,
  created_at  timestamp(6),
  deleted_at  timestamp(6),
  eta         numeric(38,2),
  from_lat    numeric(10,7),
  from_lon    numeric(10,7),
  status      varchar(255),
  to_lat      numeric(10,7),
  to_lon      numeric(10,7),
  updated_at  timestamp(6),
  version     int4,
  driver_id   uuid,
  owner_id    uuid not null,
  vehicle_id  uuid not null,
  constraint pk_move primary key (id),
  constraint fk_move_driver
    foreign key (driver_id) references driver(id),
  constraint fk_move_owner
    foreign key (owner_id) references owner(id),
  constraint fk_move_vehicle
    foreign key (vehicle_id) references vehicle(id),
  constraint ck_move_status
    check (status::text = any (array['REQUESTED','ASSIGNED','DELIVERED','CANCELED']))
);