-- liquibase formatted sql
-- changeset modulenotifygrumappingmanager:update_db_modulenotifygrumappingmanager-1.0.2-1.0.3.sql
-- preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE workflow_task_notify_gru_mapping_manager ADD COLUMN connection_id int(11) NOT NULL default '0' AFTER beankey;
ALTER TABLE workflow_task_notify_gru_mapping_manager ADD COLUMN customer_id int(11) NOT NULL default '0' AFTER connection_id;
ALTER TABLE workflow_task_notify_gru_mapping_manager ADD COLUMN demand_reference int(11) NOT NULL default '0' AFTER demandetype;
