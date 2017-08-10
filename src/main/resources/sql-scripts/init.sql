-- ----------------------------
-- Table structure for todo_label
-- ----------------------------
DROP TABLE IF EXISTS `todo_label`;
CREATE TABLE `todo_label` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`value`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`color`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`priority`  int(11) NULL DEFAULT NULL ,
`remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of todo_label
-- ----------------------------
BEGIN;
INSERT INTO `todo_label` VALUES ('1', 'work', '工作', null, null, null), ('2', 'life', '生活', null, null, null), ('3', 'study', '学习', null, null, null);
COMMIT;

-- ----------------------------
-- Table structure for todo_list
-- ----------------------------
DROP TABLE IF EXISTS `todo_list`;
CREATE TABLE `todo_list` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`label`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`state`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`create_Date`  date NULL DEFAULT NULL ,
`schedule_Date`  date NULL DEFAULT NULL ,
`update_Date`  date NULL DEFAULT NULL ,
`remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of todo_list
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for todo_user
-- ----------------------------
DROP TABLE IF EXISTS `todo_user`;
CREATE TABLE `todo_user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`sex`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`email`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`remark`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=2
;

-- ----------------------------
-- Records of todo_user
-- ----------------------------
BEGIN;
INSERT INTO `todo_user` VALUES ('1', 'Kevin', null, '13121212345', null, '$2a$10$tl6TUKhTz7wSsFNJRql32uUqwsAcHI8eEEotZpyOJZce.6Cs3.eS6', null);
COMMIT;

-- ----------------------------
-- Auto increment value for todo_label
-- ----------------------------
ALTER TABLE `todo_label` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for todo_list
-- ----------------------------
ALTER TABLE `todo_list` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for todo_user
-- ----------------------------
ALTER TABLE `todo_user` AUTO_INCREMENT=2;


-- ----------------------------
-- Table structure for task_list_activity
-- ----------------------------
DROP TABLE IF EXISTS `task_list_activity`;
CREATE TABLE `task_list_activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `activity_name` varchar(500) DEFAULT NULL COMMENT '活动名称',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for task_list_node
-- ----------------------------
DROP TABLE IF EXISTS `task_list_node`;
CREATE TABLE `task_list_node` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `pre_node_id` int(11) DEFAULT NULL COMMENT '上个node_id',
  `next_node_id` int(11) DEFAULT NULL COMMENT '下个node_id',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `due_time` datetime DEFAULT NULL COMMENT '过期时间',
  `warn_cnt` int(11) DEFAULT NULL COMMENT '提醒次数',
  PRIMARY KEY (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



