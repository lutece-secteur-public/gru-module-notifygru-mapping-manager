ALTER TABLE workflow_task_notify_gru_mapping_manager ADD COLUMN connection_id int(11) NOT NULL default '0' AFTER beankey;
ALTER TABLE workflow_task_notify_gru_mapping_manager ADD COLUMN customer_id int(11) NOT NULL default '0' AFTER connection_id;
ALTER TABLE workflow_task_notify_gru_mapping_manager ADD COLUMN demand_reference int(11) NOT NULL default '0' AFTER demandetype;
