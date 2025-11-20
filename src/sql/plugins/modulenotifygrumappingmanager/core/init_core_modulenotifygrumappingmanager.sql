-- liquibase formatted sql
-- changeset modulenotifygrumappingmanager:init_core_modulenotifygrumappingmanager.sql
-- preconditions onFail:MARK_RAN onError:WARN

--
-- Data for table core_admin_right
--
DELETE FROM core_admin_right WHERE id_right = 'MODULENOTIFYGRUMAPPINGMANAGER_MANAGEMENT';
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url, id_order ) VALUES 
('MODULENOTIFYGRUMAPPINGMANAGER_MANAGEMENT','modulenotifygrumappingmanager.adminFeature.ManageModulenotifygrumappingmanager.name',1,'jsp/admin/plugins/modulenotifygrumappingmanager/ManageNotifygruMappingManagers.jsp','modulenotifygrumappingmanager.adminFeature.ManageModulenotifygrumappingmanager.description',0,'modulenotifygrumappingmanager',NULL,NULL,NULL,4);


--
-- Data for table core_user_right
--
DELETE FROM core_user_right WHERE id_right = 'MODULENOTIFYGRUMAPPINGMANAGER_MANAGEMENT';
INSERT INTO core_user_right (id_right,id_user) VALUES ('MODULENOTIFYGRUMAPPINGMANAGER_MANAGEMENT',1);

