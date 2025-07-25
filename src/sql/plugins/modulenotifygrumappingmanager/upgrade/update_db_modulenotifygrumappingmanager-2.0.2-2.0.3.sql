--liquibase formatted sql
--changeset modulenotifygrumappingmanager:update_db_modulenotifygrumappingmanager-2.0.2-2.0.3.sql
--preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE workflow_task_notify_gru_mapping_manager MODIFY COLUMN id_notifygrumappingmanager int AUTO_INCREMENT;