CREATE TABLE IF NOT EXISTS mfs_txn_type (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  core_txn_type_id BIGINT NOT NULL,
  txn_code INT ,
  name VARCHAR(200) NOT NULL, -- enum txn type
  description VARCHAR(200) ,
  is_active BOOLEAN,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  index(name)
);

CREATE TABLE IF NOT EXISTS mfs_group_config (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  group_id BIGINT NOT NULL,
  name VARCHAR(200) NOT NULL, -- enum CUSTOMER/MERCHANT
  description VARCHAR(200) ,
  is_active boolean ,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  index(name)
);

CREATE TABLE IF NOT EXISTS txn_limit_configs (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  wallet_holder VARCHAR(10) NOT NULL, -- enum FROM/TO
  txn_type VARCHAR(200) NOT NULL, -- enum AGENT_POINT_CASH_IN/AGENT_POINT_CASHOUT
  description VARCHAR(200) ,
  daily_limit decimal(19,6) ,
  monthly_limit decimal(19,6) ,
  min_amount decimal(19,6) ,
  max_amount decimal(19,6) ,
  daily_limit_count BIGINT NOT NULL,
  monthly_limit_count BIGINT NOT NULL,
  is_enable boolean ,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  last_modified_date TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  index(txn_type)
);


CREATE TABLE IF NOT EXISTS mfs_ledger_accounts (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  account_id BIGINT NOT NULL, -- system account_id , for member accountTypeId
  code VARCHAR(200) UNIQUE NOT NULL, -- enu
  description VARCHAR(200) ,
  parent_id BIGINT,
  is_active boolean,
  is_only_parent boolean,
  ledger_level INT,
  category VARCHAR(20) ,
  type VARCHAR(20) ,
  create_date TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  last_modified_date TIMESTAMP NOT NULL DEFAULT current_timestamp()
);

-- mfs_core.mfs_txn_limit_config definition
CREATE TABLE IF NOT EXISTS `mfs_txn_limit_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `min_amount_per_txn` decimal(15,6) DEFAULT NULL,
    `max_amount_per_txn` decimal(15,6) DEFAULT NULL,
    `max_number_of_txn_per_day` bigint(20) DEFAULT NULL,
    `max_number_of_txn_per_month` bigint(20) DEFAULT NULL,
    `max_amount_per_day` decimal(15,6) DEFAULT NULL,
    `max_amount_per_month` decimal(15,6) DEFAULT NULL,
    `apply_on` varchar(3) NOT NULL,
    `transfer_type_id` int(11) NOT NULL,
    `enabled` bit(1) NOT NULL,
    `created_by` int(11) DEFAULT NULL,
    `created_at` datetime DEFAULT NULL,
    `last_modified_by` int(11) DEFAULT NULL,
    `last_modified_date` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_mfs_txn_limit_config_transfer_type_id` (`transfer_type_id`),
    CONSTRAINT `FK_mfs_txn_limit_config_transfer_type_id` FOREIGN KEY (`transfer_type_id`) REFERENCES `transfer_types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COMMENT='Transaction Limit Configuration based on Type';