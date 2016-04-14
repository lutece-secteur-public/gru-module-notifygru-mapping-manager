
--
-- Structure for table modulenotifygrumappingmanager_
--

DROP TABLE IF EXISTS workflow_task_notify_gru_mapping_manager;
CREATE TABLE workflow_task_notify_gru_mapping_manager (
id_notifygrumappingmanager int(6) NOT NULL,
beankey varchar(255) NOT NULL default '',
mobilephonenumber int(11) NOT NULL default '0',
fixedphonenumber int(11) NOT NULL default '0',
email int(11) NOT NULL default '0',
PRIMARY KEY (id_notifygrumappingmanager)
);
