/*
Navicat MySQL Data Transfer

Source Server         : jhy
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : jhy

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2019-12-19 19:26:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attach
-- ----------------------------
DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `materialId` int(11) NOT NULL COMMENT '材料',
  `productId` int(11) NOT NULL COMMENT '产品',
  `price` decimal(4,1) NOT NULL COMMENT '价格',
  PRIMARY KEY (`id`),
  KEY `material_copy_ibfk_2` (`productId`),
  KEY `material_copy_ibfk_1` (`materialId`),
  CONSTRAINT `material_copy_ibfk_1` FOREIGN KEY (`materialId`) REFERENCES `material` (`id`),
  CONSTRAINT `material_copy_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attach
-- ----------------------------

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerNum` varchar(100) NOT NULL COMMENT '客户订单号',
  `num` varchar(100) NOT NULL COMMENT '订单号',
  `createDate` datetime NOT NULL COMMENT '下单时间',
  `endDate` datetime NOT NULL COMMENT '交货日期',
  `price` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `customerId` int(11) NOT NULL COMMENT '客户',
  `lineId` int(11) NOT NULL COMMENT '线路',
  `userId` int(11) NOT NULL COMMENT '跟单',
  `bonus` decimal(5,1) DEFAULT NULL COMMENT '奖金',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `num` (`num`) USING BTREE,
  KEY `memberId` (`customerId`),
  KEY `lineId` (`lineId`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`lineId`) REFERENCES `line` (`id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for bookitem
-- ----------------------------
DROP TABLE IF EXISTS `bookitem`;
CREATE TABLE `bookitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookNum` varchar(100) NOT NULL COMMENT '订单',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `img` varchar(100) NOT NULL DEFAULT '' COMMENT '图片',
  `price` decimal(5,2) NOT NULL COMMENT '单价',
  `totalPrice` decimal(6,2) NOT NULL COMMENT '总价',
  `totalCount` int(11) NOT NULL COMMENT '数量',
  `endDate` datetime NOT NULL COMMENT '出货日期',
  `productId` int(11) NOT NULL COMMENT '产品',
  `status` tinyint(255) NOT NULL COMMENT '状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `bookNum` (`bookNum`),
  CONSTRAINT `bookitem_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `bookitem_ibfk_2` FOREIGN KEY (`bookNum`) REFERENCES `book` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookitem
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `address` varchar(100) NOT NULL COMMENT '地址',
  `account` varchar(100) DEFAULT NULL COMMENT '账号',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '等级',
  `linkUserId` int(11) DEFAULT NULL COMMENT '跟单',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bmngciqbcrw3yqdcfdwh8ysu7` (`address`) USING BTREE,
  KEY `linkUserId` (`linkUserId`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`linkUserId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('23', '1133', '834@qq.com', '11', '88', '22', null);
INSERT INTO `customer` VALUES ('24', '66', '66', '66', '66', '66', null);
INSERT INTO `customer` VALUES ('25', '12', '12', '12', '12', '12', null);

-- ----------------------------
-- Table structure for fileinfo
-- ----------------------------
DROP TABLE IF EXISTS `fileinfo`;
CREATE TABLE `fileinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `path` varchar(100) NOT NULL,
  `printCount` int(11) DEFAULT '0',
  `page` int(11) NOT NULL DEFAULT '0' COMMENT '页数',
  `label` varchar(49) DEFAULT NULL COMMENT '标签',
  `money` int(11) DEFAULT NULL COMMENT '价钱',
  `createDate` datetime NOT NULL,
  `modifyDate` datetime NOT NULL,
  `userId` int(11) NOT NULL,
  `userName` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `fileinfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fileinfo
-- ----------------------------
INSERT INTO `fileinfo` VALUES ('3', '1212', '\\2019_12_17_ca409c1d-8f7d-420c-a57d-21bdcfe2b4d2.png', '0', '0', '1212', null, '2019-12-17 21:12:52', '2019-12-17 21:12:52', '1', 'te');

-- ----------------------------
-- Table structure for line
-- ----------------------------
DROP TABLE IF EXISTS `line`;
CREATE TABLE `line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '线路名',
  `num` int(11) NOT NULL COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of line
-- ----------------------------
INSERT INTO `line` VALUES ('2', '33', '55');

-- ----------------------------
-- Table structure for lineitem
-- ----------------------------
DROP TABLE IF EXISTS `lineitem`;
CREATE TABLE `lineitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '工序名',
  `icon` varchar(100) NOT NULL COMMENT '图标',
  `mangerId` int(11) NOT NULL COMMENT '主管',
  `position` varchar(255) NOT NULL COMMENT '位置',
  `orderNum` int(11) NOT NULL COMMENT '顺序',
  `lineId` int(11) NOT NULL COMMENT '线路',
  `stationId` int(11) NOT NULL COMMENT '工序',
  PRIMARY KEY (`id`),
  KEY `lineId` (`lineId`),
  KEY `stationId` (`stationId`),
  CONSTRAINT `lineitem_ibfk_1` FOREIGN KEY (`lineId`) REFERENCES `line` (`id`),
  CONSTRAINT `lineitem_ibfk_2` FOREIGN KEY (`stationId`) REFERENCES `station` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lineitem
-- ----------------------------

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `num` varchar(100) NOT NULL COMMENT '编号',
  `material` varchar(20) NOT NULL COMMENT '材料',
  `technology` varchar(20) NOT NULL COMMENT '工艺',
  `oriPrice` decimal(4,1) NOT NULL COMMENT '原价',
  `price` decimal(4,1) NOT NULL COMMENT '现价',
  `size` varchar(20) NOT NULL COMMENT '尺寸',
  `unit` tinyint(255) NOT NULL COMMENT '单位',
  `unitWeight` decimal(6,2) NOT NULL COMMENT '单位重量',
  `detailImg` varchar(100) NOT NULL COMMENT '图片',
  `materialTypeId` int(11) NOT NULL COMMENT '类别',
  `supplierId` int(11) DEFAULT NULL COMMENT '供应商',
  PRIMARY KEY (`id`),
  KEY `materialTypeId` (`materialTypeId`),
  KEY `supplierId` (`supplierId`),
  CONSTRAINT `material_ibfk_1` FOREIGN KEY (`materialTypeId`) REFERENCES `materialtype` (`id`),
  CONSTRAINT `material_ibfk_2` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('3', '眼镜', '11', '0', '11', '11.0', '11.0', '11', '0', '11.00', '/imgFile/2019/12/17/2019_12_17_73af88aa-2fb9-4ada-974a-06b73f42f875.png', '1', '3');
INSERT INTO `material` VALUES ('6', '666', '666', '1', '66', '66.0', '66.0', '66', '1', '66.00', '/imgFile/2019/12/17/2019_12_17_73af88aa-2fb9-4ada-974a-06b73f42f875.png', '4', '2');
INSERT INTO `material` VALUES ('7', '12', '2', '0', '12', '12.0', '12.0', '12', '0', '12.00', '\\imgFile\\2019\\12\\17\\luosi.png', '2', '3');

-- ----------------------------
-- Table structure for materialtype
-- ----------------------------
DROP TABLE IF EXISTS `materialtype`;
CREATE TABLE `materialtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of materialtype
-- ----------------------------
INSERT INTO `materialtype` VALUES ('1', '鼻托');
INSERT INTO `materialtype` VALUES ('2', '镜片');
INSERT INTO `materialtype` VALUES ('3', '螺丝');
INSERT INTO `materialtype` VALUES ('4', 'xxxx');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `title` varchar(100) NOT NULL COMMENT '简介',
  `detailImg` varchar(100) NOT NULL COMMENT '产品设计图',
  `customerNum` varchar(100) NOT NULL COMMENT '客户编号',
  `num` varchar(100) NOT NULL COMMENT '产品编号',
  `oriPrice` decimal(4,1) NOT NULL COMMENT '上一次单价',
  `price` decimal(4,1) NOT NULL COMMENT '单价',
  `splenicCuff` varchar(100) NOT NULL COMMENT '脾套',
  `glasses` varchar(100) NOT NULL COMMENT '镜片',
  `electroplatingColour` varchar(100) NOT NULL COMMENT '电镀色',
  `typeName` varchar(20) NOT NULL COMMENT '材料',
  `productTypeId` int(11) NOT NULL COMMENT '商品类别',
  `customerId` int(11) NOT NULL COMMENT '客户',
  `userId` int(11) DEFAULT NULL COMMENT '跟单',
  PRIMARY KEY (`id`),
  KEY `productTypeId` (`productTypeId`),
  KEY `customerId` (`customerId`),
  KEY `userId` (`userId`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`productTypeId`) REFERENCES `producttype` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '1122', '11', '11', '11', '11', '11.0', '11.0', '11', '11', '11', '11', '3', '25', null);
INSERT INTO `product` VALUES ('2', '22', '22', '22', '22', '22', '22.0', '22.0', '22', '22', '22', '22', '2', '25', null);
INSERT INTO `product` VALUES ('3', '44', '44', '44', '44', '44', '44.0', '44.0', '44', '44', '44', '44', '3', '24', null);
INSERT INTO `product` VALUES ('4', '999', '999', '99', '99', '99', '99.0', '99.0', '9', '99', '99', '99', '3', '24', null);

-- ----------------------------
-- Table structure for producttype
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '广西水果');
INSERT INTO `producttype` VALUES ('2', '广州水果');
INSERT INTO `producttype` VALUES ('3', '热带水果');
INSERT INTO `producttype` VALUES ('4', 'ccc');

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(100) NOT NULL COMMENT '编号',
  `createDate` datetime NOT NULL COMMENT '下单时间',
  `endDate` datetime NOT NULL COMMENT '交货日期',
  `supplierId` int(11) NOT NULL COMMENT '供应商',
  `supplierName` varchar(20) NOT NULL COMMENT '供应商',
  `price` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `payInfo` varchar(100) DEFAULT NULL COMMENT '支付信息',
  `evidenceImg` varchar(100) DEFAULT NULL COMMENT '凭据图片',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮件',
  `userId` int(11) NOT NULL COMMENT '跟单',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `supplierId` (`supplierId`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase
-- ----------------------------

-- ----------------------------
-- Table structure for purchaseitem
-- ----------------------------
DROP TABLE IF EXISTS `purchaseitem`;
CREATE TABLE `purchaseitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookNum` varchar(100) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `attachId` int(100) NOT NULL DEFAULT '0',
  `totalPrice` decimal(6,2) NOT NULL,
  `totalCount` int(11) NOT NULL,
  `endDate` datetime NOT NULL COMMENT '出货日期',
  `totalWeight` decimal(6,2) NOT NULL COMMENT '总重量',
  `status` tinyint(255) NOT NULL COMMENT '状态',
  `remark` varchar(100) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `attachId` (`attachId`),
  CONSTRAINT `purchaseitem_ibfk_1` FOREIGN KEY (`attachId`) REFERENCES `attach` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchaseitem
-- ----------------------------

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '工序名',
  `icon` varchar(100) NOT NULL COMMENT '图标',
  `managerId` int(11) NOT NULL COMMENT '主管',
  `position` varchar(255) DEFAULT NULL COMMENT '位置',
  PRIMARY KEY (`id`),
  KEY `managerId` (`managerId`),
  CONSTRAINT `station_ibfk_1` FOREIGN KEY (`managerId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `address` varchar(100) NOT NULL COMMENT '地址',
  `account` varchar(100) NOT NULL COMMENT '账户',
  `linkName` varchar(20) NOT NULL COMMENT '联系人',
  `linePhone` char(11) NOT NULL COMMENT '电话',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bmngciqbcrw3yqdcfdwh8ysu7` (`address`) USING BTREE,
  KEY `linkUserId` (`linkName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('2', '12', '12', '12', '12', '12', '12', '12');
INSERT INTO `supplier` VALUES ('3', '1212', '1212', '1212', '1212', '1212', '1212', '1');

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('53', '单位', '1', '重量单位');
INSERT INTO `t_dict` VALUES ('54', '材料', '2', '材料');

-- ----------------------------
-- Table structure for t_dictval
-- ----------------------------
DROP TABLE IF EXISTS `t_dictval`;
CREATE TABLE `t_dictval` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `dictId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4db94djtdae9nx54gfd91wa64` (`dictId`) USING BTREE,
  CONSTRAINT `t_dictval_ibfk_1` FOREIGN KEY (`dictId`) REFERENCES `t_dict` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictval
-- ----------------------------
INSERT INTO `t_dictval` VALUES ('119', '克', '0', '53');
INSERT INTO `t_dictval` VALUES ('120', '千克', '1', '53');
INSERT INTO `t_dictval` VALUES ('121', '塑胶', '0', '54');
INSERT INTO `t_dictval` VALUES ('122', '板材', '1', '54');
INSERT INTO `t_dictval` VALUES ('123', '铜管', '2', '54');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `URL` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `leaf` bit(1) NOT NULL DEFAULT b'1',
  `name` varchar(255) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  `accessToken` varchar(255) DEFAULT NULL COMMENT '调用控制器的哪个方法来处理',
  PRIMARY KEY (`id`),
  KEY `FK_36p7vtyw1vxng18fhtbd6m505` (`parentId`) USING BTREE,
  KEY `accessToken` (`accessToken`) USING BTREE,
  CONSTRAINT `t_menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '', 'fa fa-adjust', '\0', '系统管理', null, '');
INSERT INTO `t_menu` VALUES ('2', 'users', 'fa fa-adjust', '\0', '用户管理', '1', 'users!list');
INSERT INTO `t_menu` VALUES ('3', 'users/add', null, '', '添加', '2', 'users!add');
INSERT INTO `t_menu` VALUES ('4', 'menus', 'fa fa-camera-retro', '\0', '菜单管理', '1', 'menus!list');
INSERT INTO `t_menu` VALUES ('5', 'roles', 'fa fa-adjust', '\0', '角色管理', '1', 'roles!list');
INSERT INTO `t_menu` VALUES ('6', 'users/edit', 'fa fa-adjust', '', '编辑', '2', 'users!edit');
INSERT INTO `t_menu` VALUES ('7', 'users/delete', 'fa fa-adjust', '', '删除', '2', 'users!delete');
INSERT INTO `t_menu` VALUES ('37', 'roles/add', 'fa fa-etsy', '', '添加', '5', 'roles!add');
INSERT INTO `t_menu` VALUES ('38', 'roles/edit', 'fa fa-etsy', '', '更新', '5', 'roles!edit');
INSERT INTO `t_menu` VALUES ('40', 'menus/add', 'fa fa-etsy', '', '添加', '4', 'menus!add');
INSERT INTO `t_menu` VALUES ('41', 'menus/edit', 'fa fa-etsy', '', '更新', '4', 'menus!edit');
INSERT INTO `t_menu` VALUES ('42', 'menus/delete', 'fa fa-etsy', '', '删除', '4', 'menus!delete');
INSERT INTO `t_menu` VALUES ('80', null, 'fa fa-adjust', '\0', '样品中心', null, null);
INSERT INTO `t_menu` VALUES ('81', null, 'fa fa-adjust', '\0', '采购中心', null, null);
INSERT INTO `t_menu` VALUES ('82', 'suppliers', 'fa fa-etsy', '\0', '供应商管理', '81', null);
INSERT INTO `t_menu` VALUES ('83', 'materialTypes', 'fa fa-etsy', '\0', '材料类别', '81', null);
INSERT INTO `t_menu` VALUES ('84', 'materials', 'fa fa-etsy', '\0', '材料管理', '81', null);
INSERT INTO `t_menu` VALUES ('85', 'sdf', 'fa fa-etsy', '\0', '采购订单', '81', null);
INSERT INTO `t_menu` VALUES ('86', 'productTypes', 'fa fa-etsy', '\0', '产品类别', '80', null);
INSERT INTO `t_menu` VALUES ('87', 'products', 'fa fa-etsy', '\0', '产品管理', '80', null);
INSERT INTO `t_menu` VALUES ('88', null, 'fa fa-etsy', '\0', '订单中心', null, null);
INSERT INTO `t_menu` VALUES ('89', 'books', 'fa fa-etsy', '', '订单管理', '88', null);
INSERT INTO `t_menu` VALUES ('90', 'bookDetails', 'fa fa-etsy', '', '订单明细', '88', null);
INSERT INTO `t_menu` VALUES ('91', '11', 'fa fa-etsy', '\0', '11', '1', null);
INSERT INTO `t_menu` VALUES ('92', '22', 'fa fa-etsy', '', '22', '91', null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('6', '收银员');
INSERT INTO `t_role` VALUES ('8', '项目总经理');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `menuId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`),
  KEY `FK_f7xuyb3bvlxjss5yg0uv4ppvx` (`menuId`) USING BTREE,
  CONSTRAINT `t_role_menu_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_menu_ibfk_3` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '6');
INSERT INTO `t_role_menu` VALUES ('1', '8');
INSERT INTO `t_role_menu` VALUES ('2', '6');
INSERT INTO `t_role_menu` VALUES ('2', '8');
INSERT INTO `t_role_menu` VALUES ('3', '6');
INSERT INTO `t_role_menu` VALUES ('3', '8');
INSERT INTO `t_role_menu` VALUES ('5', '8');
INSERT INTO `t_role_menu` VALUES ('6', '6');
INSERT INTO `t_role_menu` VALUES ('6', '8');
INSERT INTO `t_role_menu` VALUES ('7', '6');
INSERT INTO `t_role_menu` VALUES ('7', '8');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '名称',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `loginName` varchar(255) NOT NULL COMMENT '登录名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `status` tinyint(255) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bmngciqbcrw3yqdcfdwh8ysu7` (`loginName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zs', '9@qq.com', 'zs', 'f6706d5db3ad094cfabd8fb5326f1eec', '1');
INSERT INTO `t_user` VALUES ('2', 'test', 'ww@qq.com', 'ls', 'ls', '1');
INSERT INTO `t_user` VALUES ('3', 'xx', 'xx', 'xx', 'xx', '1');
INSERT INTO `t_user` VALUES ('4', 'tt', 'tt', 'tt', 'tt', '1');
INSERT INTO `t_user` VALUES ('5', 'xx', 'xx@qq.com', 'xxxx', 'E10ADC3949BA59ABBE56E057F20F883E', '0');
INSERT INTO `t_user` VALUES ('6', 'lldd', 'll@qq.com', 'll', 'E10ADC3949BA59ABBE56E057F20F883E', '0');
INSERT INTO `t_user` VALUES ('8', 'yuyu', 'yuyu@qq.com', 'yuyu', 'E10ADC3949BA59ABBE56E057F20F883E', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK_kjp9c6hki8a1p70x44bwqex2v` (`roleId`) USING BTREE,
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '6');
INSERT INTO `t_user_role` VALUES ('1', '8');
