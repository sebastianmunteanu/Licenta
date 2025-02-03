create table t_sensor
(
    id  integer auto_increment,
    denumire varchar(255),
    localizare varchar(255),
    sensor_type varchar(20),
    active_message varchar(255),
    inactive_message varchar(255),
    sensorpin_id integer,
    is_activ TINYINT,
    primary key (id)
);


create table t_sensorpin
(
    id  integer auto_increment,
    pinnumber integer,
    pindescription varchar(150),
    isfree TINYINT,
    pintype varchar(20),
    primary key (id)
);

create table t_event
(
    id integer auto_increment,
    event_date datetime,
    event_place varchar(200),
    event_name varchar(120),
    primary key (id)
);