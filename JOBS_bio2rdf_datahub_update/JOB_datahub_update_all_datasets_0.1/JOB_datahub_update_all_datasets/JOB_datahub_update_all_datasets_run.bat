%~d0
 cd %~dp0
 java -Xms256M -Xmx1024M -cp ../lib/commons-beanutils-1.8.3.jar;../lib/xom-1.2.7.jar;../lib/commons-collections-3.2.1.jar;../lib/jakarta-oro-2.0.8.jar;../lib/talend_file_enhanced_20070724.jar;../lib/staxon-1.2.jar;../lib/jaxen-1.1.1.jar;../lib/json-lib-2.4-jdk15.jar;../lib/dom4j-1.6.1.jar;../lib/ezmorph-1.0.6.jar;../lib/json-20140107.jar;../lib/commons-lang-2.6.jar;../lib/talendcsv.jar;../lib/commons-logging-1.1.1.jar;../lib/systemRoutines.jar;../lib/userRoutines.jar;.;job_datahub_update_all_datasets_0_1.jar;upload_all_datasets_0_1.jar;datahub_get_stats_0_1.jar;generate_json_datahub_file_0_1.jar; bio2rdf.job_datahub_update_all_datasets_0_1.JOB_datahub_update_all_datasets --context=Default %* 