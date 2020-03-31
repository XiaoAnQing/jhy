/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50561
Source Host           : localhost:3306
Source Database       : jhy

Target Server Type    : MYSQL
Target Server Version : 50561
File Encoding         : 65001

Date: 2020-03-31 20:24:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attach
-- ----------------------------
DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `name` varchar(20) NOT NULL COMMENT '名称',
  `materialId` int(11) NOT NULL COMMENT '材料',
  `productId` int(11) NOT NULL COMMENT '产品',
  `price` decimal(4,1) NOT NULL COMMENT '价格',
  `totalCount` int(11) NOT NULL,
  `img` varchar(100) NOT NULL,
  `x` int(255) NOT NULL,
  `y` int(255) NOT NULL,
  PRIMARY KEY (`materialId`,`productId`),
  KEY `material_copy_ibfk_2` (`productId`),
  KEY `material_copy_ibfk_1` (`materialId`),
  CONSTRAINT `material_copy_ibfk_1` FOREIGN KEY (`materialId`) REFERENCES `material` (`id`),
  CONSTRAINT `material_copy_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attach
-- ----------------------------
INSERT INTO `attach` VALUES ('12', '7', '5', '12.0', '0', 'undefined', '54', '146');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '1212', '202003250000491', '2020-03-25 00:00:49', '2011-11-11 00:00:00', '176.00', '25', '2', '1', null, 'sdf', '0');
INSERT INTO `book` VALUES ('2', '1212', '202003250002551', '2020-03-25 00:02:55', '2011-11-11 00:00:00', '176.00', '25', '2', '1', null, 'sdf', '0');
INSERT INTO `book` VALUES ('3', '76867', '202003250031131', '2020-03-25 00:31:13', '2011-11-11 00:00:00', '352.00', '24', '2', '1', null, 'yftg', '0');

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
  `productId` int(11) NOT NULL COMMENT '产品',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `bookNum` (`bookNum`),
  CONSTRAINT `bookitem_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `bookitem_ibfk_2` FOREIGN KEY (`bookNum`) REFERENCES `book` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bookitem
-- ----------------------------
INSERT INTO `bookitem` VALUES ('1', '202003250002551', '999', '99', '99.00', '99.00', '1', '4');
INSERT INTO `bookitem` VALUES ('2', '202003250002551', '44', '44', '44.00', '44.00', '1', '3');
INSERT INTO `bookitem` VALUES ('3', '202003250002551', '22', '22', '22.00', '22.00', '1', '2');
INSERT INTO `bookitem` VALUES ('4', '202003250002551', '1122', '11', '11.00', '11.00', '1', '1');
INSERT INTO `bookitem` VALUES ('5', '202003250031131', '999', '99', '99.00', '198.00', '2', '4');
INSERT INTO `bookitem` VALUES ('6', '202003250031131', '44', '44', '44.00', '88.00', '2', '3');
INSERT INTO `bookitem` VALUES ('7', '202003250031131', '22', '22', '22.00', '44.00', '2', '2');
INSERT INTO `bookitem` VALUES ('8', '202003250031131', '1122', '11', '11.00', '22.00', '2', '1');

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
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(40) NOT NULL COMMENT '送货单编号',
  `price` decimal(6,2) NOT NULL COMMENT '总数量',
  `purchaseNum` varchar(40) NOT NULL,
  `createDate` datetime NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL COMMENT '签单员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `num_2` (`num`),
  KEY `userId` (`userId`),
  KEY `purchaseNum` (`purchaseNum`),
  KEY `num` (`num`),
  CONSTRAINT `delivery_ibfk_3` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `delivery_ibfk_4` FOREIGN KEY (`purchaseNum`) REFERENCES `purchase` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES ('21', '202003302324271', '66.00', '202003302157151', '2020-03-30 23:24:27', 'zs', '1');
INSERT INTO `delivery` VALUES ('22', '202003302324481', '66.00', '202003302157151', '2020-03-30 23:24:48', 'zs', '1');
INSERT INTO `delivery` VALUES ('23', '202003302325531', '66.00', '202003302157151', '2020-03-30 23:25:53', 'zs', '1');

-- ----------------------------
-- Table structure for deliveryitem
-- ----------------------------
DROP TABLE IF EXISTS `deliveryitem`;
CREATE TABLE `deliveryitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalPrice` decimal(6,2) NOT NULL COMMENT '总金额',
  `totalCount` int(11) NOT NULL COMMENT '总数量',
  `leftCount` int(255) NOT NULL DEFAULT '0' COMMENT '未入库数量',
  `price` decimal(6,2) NOT NULL COMMENT '单价',
  `name` varchar(40) NOT NULL COMMENT '材料名',
  `materialId` int(11) NOT NULL COMMENT '材料',
  `deliveryNum` varchar(40) NOT NULL COMMENT '送货单',
  `status` tinyint(255) NOT NULL DEFAULT '0' COMMENT '0:未完全入库1:已入库',
  PRIMARY KEY (`id`),
  KEY `productId` (`materialId`),
  KEY `bookItemId` (`deliveryNum`),
  CONSTRAINT `deliveryitem_ibfk_1` FOREIGN KEY (`materialId`) REFERENCES `material` (`id`),
  CONSTRAINT `deliveryitem_ibfk_2` FOREIGN KEY (`deliveryNum`) REFERENCES `delivery` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deliveryitem
-- ----------------------------
INSERT INTO `deliveryitem` VALUES ('55', '66.00', '1', '1', '66.00', '666', '6', '202003302325531', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of line
-- ----------------------------
INSERT INTO `line` VALUES ('2', '33', '55');
INSERT INTO `line` VALUES ('3', '11', '11');

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
  `command` varchar(255) DEFAULT NULL COMMENT '指令',
  `input` varchar(255) DEFAULT NULL COMMENT '任务内容',
  PRIMARY KEY (`id`),
  KEY `lineId` (`lineId`),
  KEY `stationId` (`stationId`),
  CONSTRAINT `lineitem_ibfk_1` FOREIGN KEY (`lineId`) REFERENCES `line` (`id`),
  CONSTRAINT `lineitem_ibfk_2` FOREIGN KEY (`stationId`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lineitem
-- ----------------------------
INSERT INTO `lineitem` VALUES ('3', '11', '11', '1', '11', '11', '3', '1', null, null);
INSERT INTO `lineitem` VALUES ('4', '1', '1', '1', '1', '1', '2', '1', null, null);
INSERT INTO `lineitem` VALUES ('5', 'test', 'test', '6', '{\"floor\":3,\"x\":\"1\",\"y\":\"2\"}', '12', '3', '1', null, null);
INSERT INTO `lineitem` VALUES ('6', 'test', 'test', '6', '{\"floor\":3,\"x\":\"1\",\"y\":\"2\"}', '1', '3', '1', '11', 'test');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '1122', '11', '11', '11', '11', '11.0', '11.0', '11', '11', '11', '11', '3', '25', null);
INSERT INTO `product` VALUES ('2', '22', '22', '22', '22', '22', '22.0', '22.0', '22', '22', '22', '22', '2', '25', null);
INSERT INTO `product` VALUES ('3', '44', '44', '44', '44', '44', '44.0', '44.0', '44', '44', '44', '44', '3', '24', null);
INSERT INTO `product` VALUES ('4', '999', '999', '99', '99', '99', '99.0', '99.0', '9', '99', '99', '99', '3', '24', null);
INSERT INTO `product` VALUES ('5', 'test', '11', '1', '1', '11', '11.0', '1.0', '', '11', '11', '1', '3', '25', '1');

-- ----------------------------
-- Table structure for producttype
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '广西水果');
INSERT INTO `producttype` VALUES ('2', '广州水果');
INSERT INTO `producttype` VALUES ('3', '热带水果');

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
  `email` varchar(100) DEFAULT '' COMMENT '邮件',
  `userName` varchar(40) NOT NULL,
  `userId` int(11) NOT NULL COMMENT '跟单',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `supplierId` (`supplierId`),
  KEY `num` (`num`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`supplierId`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('9', '202003292158091', '2020-03-29 21:58:09', '2020-11-11 00:00:00', '3', '比亚迪供应商', '110.00', null, null, null, 'zs', '1', '1', '不能延期');
INSERT INTO `purchase` VALUES ('10', '202003302157151', '2020-03-30 21:57:15', '2011-11-11 00:00:00', '2', '测试供应商', '66.00', null, null, null, 'zs', '1', '1', '不能延期');

-- ----------------------------
-- Table structure for purchaseitem
-- ----------------------------
DROP TABLE IF EXISTS `purchaseitem`;
CREATE TABLE `purchaseitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purchaseNum` varchar(100) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `materialId` int(100) NOT NULL DEFAULT '0',
  `price` decimal(6,2) NOT NULL,
  `totalPrice` decimal(6,2) NOT NULL,
  `totalCount` int(11) NOT NULL,
  `leftCount` int(255) NOT NULL DEFAULT '0',
  `endDate` datetime DEFAULT NULL COMMENT '出货日期',
  `status` tinyint(255) NOT NULL DEFAULT '0' COMMENT '状态',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseNum` (`purchaseNum`) USING BTREE,
  KEY `materialId` (`materialId`),
  CONSTRAINT `purchaseitem_ibfk_1` FOREIGN KEY (`materialId`) REFERENCES `material` (`id`),
  CONSTRAINT `purchaseitem_ibfk_2` FOREIGN KEY (`purchaseNum`) REFERENCES `purchase` (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchaseitem
-- ----------------------------
INSERT INTO `purchaseitem` VALUES ('5', '202003292158091', '眼镜', '3', '11.00', '110.00', '10', '10', null, '0', null);
INSERT INTO `purchaseitem` VALUES ('6', '202003302157151', '666', '6', '66.00', '66.00', '1', '1', null, '0', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('1', 'test', 'test', '6', '{\"floor\":3,\"x\":\"1\",\"y\":\"2\"}');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `x` int(255) NOT NULL COMMENT 'x轴',
  `y` int(255) NOT NULL COMMENT 'y轴',
  `z` int(255) NOT NULL COMMENT 'z轴',
  `materialCount` int(255) DEFAULT NULL COMMENT '药品数量',
  `materialId` int(11) DEFAULT NULL COMMENT '药品id',
  `materialName` varchar(30) DEFAULT NULL COMMENT '药品',
  `status` tinyint(255) NOT NULL COMMENT '状态',
  `storeTypeId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `medicineId` (`materialId`),
  KEY `storeTypeId` (`storeTypeId`),
  KEY `materialCount` (`materialCount`),
  CONSTRAINT `store_ibfk_2` FOREIGN KEY (`storeTypeId`) REFERENCES `storetype` (`id`),
  CONSTRAINT `store_ibfk_3` FOREIGN KEY (`materialId`) REFERENCES `material` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('110', '0', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('111', '0', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('112', '0', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('113', '0', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('114', '0', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('115', '0', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('116', '0', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('117', '0', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('118', '0', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('119', '0', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('120', '1', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('121', '1', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('122', '1', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('123', '1', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('124', '1', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('125', '1', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('126', '1', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('127', '1', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('128', '1', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('129', '1', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('130', '2', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('131', '2', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('132', '2', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('133', '2', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('134', '2', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('135', '2', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('136', '2', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('137', '2', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('138', '2', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('139', '2', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('140', '3', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('141', '3', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('142', '3', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('143', '3', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('144', '3', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('145', '3', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('146', '3', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('147', '3', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('148', '3', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('149', '3', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('150', '4', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('151', '4', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('152', '4', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('153', '4', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('154', '4', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('155', '4', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('156', '4', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('157', '4', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('158', '4', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('159', '4', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('160', '5', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('161', '5', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('162', '5', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('163', '5', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('164', '5', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('165', '5', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('166', '5', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('167', '5', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('168', '5', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('169', '5', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('170', '6', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('171', '6', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('172', '6', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('173', '6', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('174', '6', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('175', '6', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('176', '6', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('177', '6', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('178', '6', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('179', '6', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('180', '7', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('181', '7', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('182', '7', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('183', '7', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('184', '7', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('185', '7', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('186', '7', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('187', '7', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('188', '7', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('189', '7', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('190', '8', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('191', '8', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('192', '8', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('193', '8', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('194', '8', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('195', '8', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('196', '8', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('197', '8', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('198', '8', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('199', '8', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('200', '9', '0', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('201', '9', '1', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('202', '9', '2', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('203', '9', '3', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('204', '9', '4', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('205', '9', '5', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('206', '9', '6', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('207', '9', '7', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('208', '9', '8', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('209', '9', '9', '0', null, null, null, '0', '0');
INSERT INTO `store` VALUES ('210', '0', '0', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('211', '0', '1', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('212', '0', '2', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('213', '0', '3', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('214', '0', '4', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('215', '0', '5', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('216', '0', '6', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('217', '0', '7', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('218', '12', '12', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('219', '56', '56', '8', null, null, null, '0', '3');
INSERT INTO `store` VALUES ('220', '0', '0', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('221', '0', '1', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('222', '0', '2', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('223', '0', '3', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('224', '1', '0', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('225', '1', '1', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('226', '1', '2', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('227', '1', '3', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('228', '2', '0', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('229', '2', '1', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('230', '2', '2', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('231', '2', '3', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('232', '3', '0', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('233', '3', '1', '4', null, null, null, '0', '5');
INSERT INTO `store` VALUES ('234', '3', '2', '4', '1', '3', '眼镜', '0', '5');
INSERT INTO `store` VALUES ('235', '3', '3', '4', '1', '3', '眼镜', '0', '5');

-- ----------------------------
-- Table structure for storetype
-- ----------------------------
DROP TABLE IF EXISTS `storetype`;
CREATE TABLE `storetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '名称',
  `num` varchar(30) NOT NULL COMMENT '编号',
  `width` int(11) NOT NULL COMMENT '长度',
  `height` int(255) NOT NULL COMMENT '高度',
  `row` int(255) NOT NULL COMMENT '行',
  `col` int(255) NOT NULL COMMENT '列',
  `z` int(255) NOT NULL COMMENT 'z轴',
  `status` tinyint(255) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `num` (`num`),
  UNIQUE KEY `z` (`z`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of storetype
-- ----------------------------
INSERT INTO `storetype` VALUES ('3', '进口药品专区', '001', '1', '1', '1', '8', '8', '1');
INSERT INTO `storetype` VALUES ('5', '中西药区', '124', '80', '40', '4', '4', '4', '1');
INSERT INTO `storetype` VALUES ('6', '保健专区TESTSET', '12', '12', '12', '10', '10', '0', '1');

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
INSERT INTO `supplier` VALUES ('2', '测试供应商', '12', '12', '12', '12', '12', '12');
INSERT INTO `supplier` VALUES ('3', '比亚迪供应商', '1212', '1212', '1212', '1212', '1212', '1');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '任务名',
  `taskCount` int(255) NOT NULL COMMENT '任务数量',
  `leftCount` int(255) NOT NULL,
  `status` tinyint(255) NOT NULL COMMENT '任务状态',
  `passRate` decimal(4,2) NOT NULL COMMENT '0',
  `stationId` int(11) NOT NULL,
  `bookNum` varchar(255) NOT NULL,
  `dateline` datetime NOT NULL,
  `level` tinyint(255) DEFAULT '0' COMMENT '优先级',
  `input` varchar(255) DEFAULT NULL COMMENT '原材料',
  `extraCount` int(255) DEFAULT NULL COMMENT '额外补充',
  PRIMARY KEY (`id`),
  KEY `stationId` (`stationId`),
  KEY `bookNum` (`bookNum`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`stationId`) REFERENCES `station` (`id`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`bookNum`) REFERENCES `book` (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for task_copy
-- ----------------------------
DROP TABLE IF EXISTS `task_copy`;
CREATE TABLE `task_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalCount` int(255) NOT NULL COMMENT '任务名',
  `goodCount` int(255) NOT NULL COMMENT '任务数量',
  `badCount` int(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  `taskId` int(11) NOT NULL DEFAULT '0' COMMENT '优先级',
  `ceateDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `taskId` (`taskId`),
  CONSTRAINT `task_copy_ibfk_2` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`),
  CONSTRAINT `task_copy_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_copy
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('53', '单位', '1', '重量单位');
INSERT INTO `t_dict` VALUES ('54', '材料', '2', '材料');
INSERT INTO `t_dict` VALUES ('55', '订单状态', '3', '订单状态');
INSERT INTO `t_dict` VALUES ('56', '采购状态', '4', '采购状态');
INSERT INTO `t_dict` VALUES ('57', '用户状态', '10', '用户状态');

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
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictval
-- ----------------------------
INSERT INTO `t_dictval` VALUES ('119', '克', '0', '53');
INSERT INTO `t_dictval` VALUES ('120', '千克', '1', '53');
INSERT INTO `t_dictval` VALUES ('121', '塑胶', '0', '54');
INSERT INTO `t_dictval` VALUES ('122', '板材', '1', '54');
INSERT INTO `t_dictval` VALUES ('123', '审核中', '0', '55');
INSERT INTO `t_dictval` VALUES ('124', '审核通过', '1', '55');
INSERT INTO `t_dictval` VALUES ('125', '审核未通过', '2', '55');
INSERT INTO `t_dictval` VALUES ('126', '异常', '3', '55');
INSERT INTO `t_dictval` VALUES ('127', '已完成', '4', '55');
INSERT INTO `t_dictval` VALUES ('128', '无效', '5', '55');
INSERT INTO `t_dictval` VALUES ('129', '无效', '5', '56');
INSERT INTO `t_dictval` VALUES ('130', '已完成', '4', '56');
INSERT INTO `t_dictval` VALUES ('131', '异常', '3', '56');
INSERT INTO `t_dictval` VALUES ('132', '审核未通过', '2', '56');
INSERT INTO `t_dictval` VALUES ('133', '审核通过', '1', '56');
INSERT INTO `t_dictval` VALUES ('134', '审核中', '0', '56');
INSERT INTO `t_dictval` VALUES ('135', '删除', '1', '57');
INSERT INTO `t_dictval` VALUES ('136', '正常', '0', '57');

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
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_menu` VALUES ('85', 'purchases', 'fa fa-etsy', '\0', '采购订单', '81', null);
INSERT INTO `t_menu` VALUES ('86', 'productTypes', 'fa fa-etsy', '\0', '产品类别', '80', null);
INSERT INTO `t_menu` VALUES ('87', 'products', 'fa fa-etsy', '\0', '产品管理', '80', null);
INSERT INTO `t_menu` VALUES ('88', null, 'fa fa-etsy', '\0', '订单中心', null, null);
INSERT INTO `t_menu` VALUES ('89', '/books', 'fa fa-etsy', '', '订单管理', '88', null);
INSERT INTO `t_menu` VALUES ('90', '/bookItems', 'fa fa-etsy', '', '订单明细', '88', null);
INSERT INTO `t_menu` VALUES ('93', null, 'fa fa-etsy', '\0', '工艺中心', null, null);
INSERT INTO `t_menu` VALUES ('94', 'lines', null, '', '线路管理', '93', null);
INSERT INTO `t_menu` VALUES ('95', 'lineItems', null, '', '工段管理', '93', null);
INSERT INTO `t_menu` VALUES ('96', 'stations', 'test', '', '工艺管理', '93', null);
INSERT INTO `t_menu` VALUES ('97', '', 'fa fa-etsy', '\0', '基础数据', null, null);
INSERT INTO `t_menu` VALUES ('98', 'dicts', 'fa fa-esty', '', '字典管理', '97', null);
INSERT INTO `t_menu` VALUES ('99', 'dictVals', 'fa fa-esty', '', '字典明细', '97', null);
INSERT INTO `t_menu` VALUES ('100', 'storeTypes', 'fa fa-esty', '', '仓库类别', '97', null);
INSERT INTO `t_menu` VALUES ('101', 'stores', 'fa fa-esty', '', '仓库管理', '97', null);
INSERT INTO `t_menu` VALUES ('102', 'purchaseItems', 'fa fa-esty', '', '采购明细', '81', null);
INSERT INTO `t_menu` VALUES ('103', null, 'fa fa-esty', '', '仓库中心', null, null);
INSERT INTO `t_menu` VALUES ('104', 'deliveries', 'fa fa-esty', '', '入库管理', '103', null);
INSERT INTO `t_menu` VALUES ('105', 'deliveryItems', 'fa fa-esty', '', '入库明细', '103', null);

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
