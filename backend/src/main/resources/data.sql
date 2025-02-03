insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (4, 'GPIO_4', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (17, 'GPIO_17', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (27, 'GPIO_27', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (22, 'GPIO_22', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (5, 'GPIO_5', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (6, 'GPIO_6', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (13, 'GPIO_13', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (19, 'GPIO_19', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (26, 'GPIO_26', 1, 'analogic');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (18, 'GPIO_18', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (23, 'GPIO_23', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (24, 'GPIO_24', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (25, 'GPIO_25', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (12, 'GPIO_12', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (26, 'GPIO_16', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (20, 'GPIO_20', 1, 'digital');

insert into t_sensorpin(pinnumber, pindescription, isfree, pintype)
values (21, 'GPIO_21', 1, 'digital');

insert into t_sensor(denumire, localizare, sensor_type, active_message, inactive_message, sensorpin_id, is_activ)
values ('senzor inundatie', 'apartament 10', 'analogic', 'inundatie detectata', 'inundatie nedetectata', 9, 1);

insert into t_sensor(denumire, localizare, sensor_type, active_message, inactive_message, sensorpin_id, is_activ)
values ('senzor usa', 'apartament 20', 'digital', 'usa deschisa', 'usa inchisa', 17, 1);

insert into t_event(event_date, event_place, event_name)
values ('2024-06-01 12:12:12', 'apartament 10', 'inundatie detectata');
