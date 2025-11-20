-- liquibase formatted sql
-- changeset modulenotifygrumappingmanager:create_db_modulenotifygrumappingmanager.sql
-- preconditions onFail:MARK_RAN onError:WARN

--
-- Structure for table modulenotifygrumappingmanager_
--

DROP TABLE IF EXISTS workflow_task_notify_gru_mapping_manager;
CREATE TABLE workflow_task_notify_gru_mapping_manager (
id_notifygrumappingmanager int AUTO_INCREMENT,
beankey varchar(255) default '' NOT NULL,
connection_id int default 0 NOT NULL,
customer_id int default 0 NOT NULL,
mobilephonenumber int default 0 NOT NULL,
fixedphonenumber int default 0 NOT NULL,
email int default 0 NOT NULL,
demandetype int default 0 NOT NULL,
demand_reference int default 0 NOT NULL,
PRIMARY KEY (id_notifygrumappingmanager)
);
