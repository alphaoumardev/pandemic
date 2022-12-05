

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物资消费方',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `sort` int(11) DEFAULT NULL,
  `contact` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of consumer
-- ----------------------------
BEGIN;
INSERT INTO `consumer` VALUES (1, '湖北省武汉市汉阳区物资救灾区2号营地', '湖北省/武汉市/汉阳区', '2021-05-27 15:57:20', '2021-05-27 15:58:30', '15723564536', 1, '张晓阳');
INSERT INTO `consumer` VALUES (2, '湖北省武汉市江岸区南投路23号经济园区27栋5楼', '湖北省/武汉市/江岸区', '2021-05-27 16:01:19', '2021-05-27 16:01:19', '13467892345', 2, '章小意');
INSERT INTO `consumer` VALUES (3, '上海市长宁区建设路34号楼5楼', '上海市/上海市/长宁区', '2021-05-27 16:02:13', '2021-05-27 16:02:13', '15898562536', 3, '包小米');
INSERT INTO `consumer` VALUES (4, '吉林省长春市绿园区经济开发区23号', '吉林省/长春市/绿园区', '2021-05-27 16:03:09', '2021-05-27 16:03:09', '13567234609', 4, '长小半');
INSERT INTO `consumer` VALUES (5, '云南省昭通市大关县长征路23号', '云南省/昭通市/大关县', '2021-05-27 16:04:03', '2021-05-27 16:04:03', '15256378294', 5, '任晓强');
COMMIT;

-- ----------------------------
-- Table structure for health
-- ----------------------------
DROP TABLE IF EXISTS `health`;
CREATE TABLE `health` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户id号',
  `situation` int(1) NOT NULL COMMENT '健康状态',
  `touch` int(1) NOT NULL COMMENT '是否接触过疑似人',
  `passby` int(1) NOT NULL COMMENT '是否经过湖北地区(包括转车)',
  `reception` int(1) NOT NULL COMMENT '是否招待过湖北客户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of health
-- ----------------------------
BEGIN;
INSERT INTO `health` VALUES (7, '辽宁省/锦州市/北镇市', 108, 1, 0, 0, 1, '2021-03-31 23:54:03');
INSERT INTO `health` VALUES (8, '河北省/邯郸市/复兴区', 108, 1, 1, 0, 1, '2021-04-01 15:17:20');
INSERT INTO `health` VALUES (9, '河北省/唐山市/路南区', 108, 0, 0, 0, 0, '2021-04-04 22:13:15');
INSERT INTO `health` VALUES (10, '河北省/张家口市/万全区', 108, 1, 1, 1, 1, '2021-04-05 11:57:56');
INSERT INTO `health` VALUES (12, '河北省/唐山市/开平区', 108, 0, 0, 0, 0, '2021-04-06 23:59:35');
INSERT INTO `health` VALUES (13, '山西省/阳泉市/矿区', 108, 2, 0, 1, 1, '2021-04-07 20:19:36');
INSERT INTO `health` VALUES (14, '山西省/长治市/襄垣县', 108, 0, 1, 0, 1, '2021-04-10 22:30:26');
INSERT INTO `health` VALUES (15, '河北省/邯郸市/肥乡区', 109, 1, 1, 1, 1, '2021-04-11 12:48:08');
INSERT INTO `health` VALUES (16, '河北省/秦皇岛市/北戴河区', 108, 1, 1, 1, 1, '2021-04-11 13:34:22');
INSERT INTO `health` VALUES (17, '河北省/秦皇岛市/抚宁区', 109, 1, 1, 1, 1, '2021-04-12 20:31:32');
INSERT INTO `health` VALUES (18, '山西省/阳泉市/矿区', 108, 0, 0, 0, 0, '2021-04-13 22:53:03');
INSERT INTO `health` VALUES (19, '福建省/厦门市/同安区', 108, 0, 0, 0, 0, '2021-04-17 20:41:50');
INSERT INTO `health` VALUES (20, '山西省/阳泉市/平定县', 109, 0, 0, 0, 0, '2021-04-17 21:52:46');
INSERT INTO `health` VALUES (21, '天津市/天津市/河东区', 109, 0, 0, 0, 0, '2021-04-18 11:34:58');
INSERT INTO `health` VALUES (23, '甘肃省/天水市/秦安县', 109, 1, 1, 1, 1, '2021-04-19 10:09:46');
INSERT INTO `health` VALUES (24, '辽宁省/本溪市/南芬区', 108, 0, 0, 0, 0, '2021-04-22 11:52:30');
INSERT INTO `health` VALUES (25, '江西省/赣州市/章贡区', 108, 0, 0, 0, 0, '2021-05-24 10:20:25');
INSERT INTO `health` VALUES (26, '江西省/赣州市/章贡区', 108, 0, 0, 0, 0, '2021-05-27 11:47:37');
COMMIT;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `in_num` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入库单编号',
  `type` int(2) DEFAULT NULL COMMENT '类型：1：捐赠，2：下拨，3：采购, 4:退货入库',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作人员',
  `create_time` datetime DEFAULT NULL COMMENT '入库单创建时间',
  `modified` datetime DEFAULT NULL COMMENT '入库单修改时间',
  `product_number` int(11) DEFAULT NULL COMMENT '物资总数',
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '来源',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述信息',
  `status` int(1) DEFAULT '2' COMMENT '0:正常入库单,1:已进入回收,2:等待审核',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `operator_id` (`operator`) USING BTREE,
  KEY `supplier_id` (`supplier_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of stock
-- ----------------------------
BEGIN;
INSERT INTO `stock` VALUES (1, '75d81910e8244936b12cafd71fc9148b', 1, 'admin', '2021-05-27 16:06:39', NULL, 200, 4, '我们很支持你们，加油！', 0);
INSERT INTO `stock` VALUES (2, '69ca4bb2eb934999896d9f8f891ac061', 2, 'admin', '2021-05-27 16:16:45', NULL, 102, 5, '支持你们的业务，加油，支持武汉！', 0);
INSERT INTO `stock` VALUES (5, '1232a28da2fc4165b7aabf688510135e', 3, 'admin', '2021-05-27 16:42:56', NULL, 105, 3, '我们会给您最大的优惠，请放心！', 0);
INSERT INTO `stock` VALUES (6, 'a6b17ab545de4438b97552733fffbd4e', 3, 'admin', '2021-05-28 13:22:30', NULL, 100, 6, '5月28号在北京市丰台区采购口罩100包，共计500个', 0);
INSERT INTO `stock` VALUES (7, 'e32ce0a2e5804d4984b71a6f47867137', 3, 'admin', '2021-05-28 13:26:53', NULL, 10, 5, '江西省章贡区采购N95口罩 测试。。。请勿通过审核。。。', 2);
COMMIT;

-- ----------------------------
-- Table structure for stockinfo
-- ----------------------------
DROP TABLE IF EXISTS `stockinfo`;
CREATE TABLE `stockinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `in_num` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '入库单编号',
  `p_num` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编号',
  `product_number` int(11) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of stockinfo
-- ----------------------------
BEGIN;
INSERT INTO `stockinfo` VALUES (1, '75d81910e8244936b12cafd71fc9148b', '6260dfeb678e480a82d20339954b4949', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (2, '75d81910e8244936b12cafd71fc9148b', '4aacd6ec7cc24040a4a834927b093d55', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (3, '75d81910e8244936b12cafd71fc9148b', '0beae59fb7eb4dc2b78a5f2a3848c66e', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (4, '75d81910e8244936b12cafd71fc9148b', 'e6643852dcec426d8939a3ecd820e5f2', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (5, '75d81910e8244936b12cafd71fc9148b', 'eab44d927fb042c9aa1369b71c191c6f', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (6, '75d81910e8244936b12cafd71fc9148b', '2a34f46025fc4f9ba4a577e61079594f', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (7, '75d81910e8244936b12cafd71fc9148b', '40b60a3f639449f3938021330ab4f298', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (8, '75d81910e8244936b12cafd71fc9148b', 'b0927f9bed064d729ac8f59792333e38', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (9, '75d81910e8244936b12cafd71fc9148b', 'fcc51e82bafd47579a4fb80671328c50', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (10, '75d81910e8244936b12cafd71fc9148b', 'ade9fc93cdab477dba55e3f6afe1a8c0', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (11, '75d81910e8244936b12cafd71fc9148b', '41f9ffd55d504f93b7b128b331bf6081', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (12, '75d81910e8244936b12cafd71fc9148b', 'f2672d0a4a4543f6a2b70a8ff0746ee6', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (13, '75d81910e8244936b12cafd71fc9148b', '9658684bf62545faabbe523e9b2cee91', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (14, '75d81910e8244936b12cafd71fc9148b', '84fedf0594c843fb89bebd6d6ce456bf', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (15, '75d81910e8244936b12cafd71fc9148b', '00664f3d9e7542b9adb9863007c59029', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (16, '75d81910e8244936b12cafd71fc9148b', '154a86cfe26e48c5aa176a744fa5fdf0', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (17, '75d81910e8244936b12cafd71fc9148b', '85eef2ea062f407391b81b8a4964b6fb', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (18, '75d81910e8244936b12cafd71fc9148b', '36d77f457f6f4c5e9eb4727d66cb9eec', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (19, '75d81910e8244936b12cafd71fc9148b', '35d8dd946abf4cf8b4b68cdb1d7f825e', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (20, '75d81910e8244936b12cafd71fc9148b', 'f7fb7e3009fc4c9db225d9be1caf2978', 10, '2021-05-27 16:06:39', '2021-05-27 16:06:39');
INSERT INTO `stockinfo` VALUES (21, '69ca4bb2eb934999896d9f8f891ac061', '6260dfeb678e480a82d20339954b4949', 3, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (22, '69ca4bb2eb934999896d9f8f891ac061', '4aacd6ec7cc24040a4a834927b093d55', 5, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (23, '69ca4bb2eb934999896d9f8f891ac061', '0beae59fb7eb4dc2b78a5f2a3848c66e', 7, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (24, '69ca4bb2eb934999896d9f8f891ac061', 'e6643852dcec426d8939a3ecd820e5f2', 9, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (25, '69ca4bb2eb934999896d9f8f891ac061', 'eab44d927fb042c9aa1369b71c191c6f', 10, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (26, '69ca4bb2eb934999896d9f8f891ac061', '2a34f46025fc4f9ba4a577e61079594f', 2, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (27, '69ca4bb2eb934999896d9f8f891ac061', '40b60a3f639449f3938021330ab4f298', 1, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (28, '69ca4bb2eb934999896d9f8f891ac061', 'b0927f9bed064d729ac8f59792333e38', 5, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (29, '69ca4bb2eb934999896d9f8f891ac061', 'fcc51e82bafd47579a4fb80671328c50', 3, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (30, '69ca4bb2eb934999896d9f8f891ac061', 'ade9fc93cdab477dba55e3f6afe1a8c0', 6, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (31, '69ca4bb2eb934999896d9f8f891ac061', '41f9ffd55d504f93b7b128b331bf6081', 3, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (32, '69ca4bb2eb934999896d9f8f891ac061', 'f2672d0a4a4543f6a2b70a8ff0746ee6', 4, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (33, '69ca4bb2eb934999896d9f8f891ac061', '9658684bf62545faabbe523e9b2cee91', 8, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (34, '69ca4bb2eb934999896d9f8f891ac061', '84fedf0594c843fb89bebd6d6ce456bf', 2, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (35, '69ca4bb2eb934999896d9f8f891ac061', '00664f3d9e7542b9adb9863007c59029', 3, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (36, '69ca4bb2eb934999896d9f8f891ac061', '154a86cfe26e48c5aa176a744fa5fdf0', 5, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (37, '69ca4bb2eb934999896d9f8f891ac061', '85eef2ea062f407391b81b8a4964b6fb', 6, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (38, '69ca4bb2eb934999896d9f8f891ac061', '36d77f457f6f4c5e9eb4727d66cb9eec', 8, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (39, '69ca4bb2eb934999896d9f8f891ac061', '35d8dd946abf4cf8b4b68cdb1d7f825e', 5, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (40, '69ca4bb2eb934999896d9f8f891ac061', 'f7fb7e3009fc4c9db225d9be1caf2978', 7, '2021-05-27 16:16:45', '2021-05-27 16:16:45');
INSERT INTO `stockinfo` VALUES (43, '1232a28da2fc4165b7aabf688510135e', '66fe1334fb214c31b4f2115509f2abbd', 5, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (44, '1232a28da2fc4165b7aabf688510135e', 'df9e361717474ff095ad16c24ba0b1d9', 10, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (45, '1232a28da2fc4165b7aabf688510135e', '7418493128c94701865c131b3b90620d', 3, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (46, '1232a28da2fc4165b7aabf688510135e', '1305d5b7c1d745258bc016dc56f8e6d6', 4, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (47, '1232a28da2fc4165b7aabf688510135e', 'fee4a239cc364540a559e0cb26a1d0cb', 5, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (48, '1232a28da2fc4165b7aabf688510135e', 'b90d4a960dc54eb2aa4284dc909823b2', 6, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (49, '1232a28da2fc4165b7aabf688510135e', 'c15ce55ddafc48bba0f3ba1132211207', 7, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (50, '1232a28da2fc4165b7aabf688510135e', '3ef6d79631384c8786d45e97836d1ac3', 4, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (51, '1232a28da2fc4165b7aabf688510135e', '16972de3c716441eab3f6b817544e366', 5, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (52, '1232a28da2fc4165b7aabf688510135e', 'a80984c6875349c0b1c12fa57478da10', 3, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (53, '1232a28da2fc4165b7aabf688510135e', 'c7b21870aa6247d78ef877e7a3a6a923', 2, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (54, '1232a28da2fc4165b7aabf688510135e', '838232bdf767415b934c47c25c06313a', 7, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (55, '1232a28da2fc4165b7aabf688510135e', '91a0371287b043cfa8ec26564530231f', 8, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (56, '1232a28da2fc4165b7aabf688510135e', 'd594f3047a654f37b369574a1945ccaf', 9, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (57, '1232a28da2fc4165b7aabf688510135e', '9394a51f77ee4750bc92bfd1e91d9854', 4, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (58, '1232a28da2fc4165b7aabf688510135e', '07299a2b749c47e581d217342087cc49', 5, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (59, '1232a28da2fc4165b7aabf688510135e', 'f36b8b6214574845aae0619e7c33c3b8', 3, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (60, '1232a28da2fc4165b7aabf688510135e', '23128c4c5edd429d8e08026016d337ca', 5, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (61, '1232a28da2fc4165b7aabf688510135e', '7a40f8d814394067a289f70e8e7ea484', 10, '2021-05-27 16:42:56', '2021-05-27 16:42:56');
INSERT INTO `stockinfo` VALUES (62, 'a6b17ab545de4438b97552733fffbd4e', '91a0371287b043cfa8ec26564530231f', 100, '2021-05-28 13:22:30', '2021-05-28 13:22:30');
INSERT INTO `stockinfo` VALUES (63, 'e32ce0a2e5804d4984b71a6f47867137', '41f9ffd55d504f93b7b128b331bf6081', 10, '2021-05-28 13:26:53', '2021-05-28 13:26:53');
COMMIT;

-- ----------------------------
-- Table structure for outstock
-- ----------------------------
DROP TABLE IF EXISTS `outstock`;
CREATE TABLE `outstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_num` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出库单',
  `type` int(1) NOT NULL COMMENT '出库类型:0:直接出库,1:审核出库',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人',
  `create_time` datetime DEFAULT NULL COMMENT '出库时间',
  `product_number` int(11) DEFAULT NULL COMMENT '出库总数',
  `consumer_id` bigint(20) NOT NULL COMMENT '消费者id',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT NULL COMMENT '状态:0:正常入库,1:已进入回收,2:等待审核',
  `priority` int(1) NOT NULL COMMENT '紧急程度:1:不急,2:常规,3:紧急4:特急',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of outstock
-- ----------------------------
BEGIN;
INSERT INTO `outstock` VALUES (1, 'caf144d8921244ee97e74e518aa804e2', 3, 'admin', '2021-05-28 13:30:52', 20, 2, '发往湖北武汉。个人领取，紧急！！！', 2, 3);
INSERT INTO `outstock` VALUES (2, '0464e4a57ba042dd8e9b756d98f58816', 4, 'admin', '2021-05-28 13:39:52', 20, 1, '特紧急。。。快速', 0, 5);
INSERT INTO `outstock` VALUES (3, '8d631e2301bf48e5a2c069efff5c042e', 0, 'admin', '2021-05-30 10:55:26', 8, 2, 'qweqwwq', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for outstockinfo
-- ----------------------------
DROP TABLE IF EXISTS `outstockinfo`;
CREATE TABLE `outstockinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `out_num` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `p_num` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `product_number` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of outstockinfo
-- ----------------------------
BEGIN;
INSERT INTO `outstockinfo` VALUES (1, 'caf144d8921244ee97e74e518aa804e2', '91a0371287b043cfa8ec26564530231f', 20, '2021-05-28 13:30:52', '2021-05-28 13:30:52');
INSERT INTO `outstockinfo` VALUES (2, '0464e4a57ba042dd8e9b756d98f58816', '41f9ffd55d504f93b7b128b331bf6081', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (3, '0464e4a57ba042dd8e9b756d98f58816', '4aacd6ec7cc24040a4a834927b093d55', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (4, '0464e4a57ba042dd8e9b756d98f58816', '6260dfeb678e480a82d20339954b4949', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (5, '0464e4a57ba042dd8e9b756d98f58816', '0beae59fb7eb4dc2b78a5f2a3848c66e', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (6, '0464e4a57ba042dd8e9b756d98f58816', 'e6643852dcec426d8939a3ecd820e5f2', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (7, '0464e4a57ba042dd8e9b756d98f58816', 'eab44d927fb042c9aa1369b71c191c6f', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (8, '0464e4a57ba042dd8e9b756d98f58816', '2a34f46025fc4f9ba4a577e61079594f', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (9, '0464e4a57ba042dd8e9b756d98f58816', '40b60a3f639449f3938021330ab4f298', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (10, '0464e4a57ba042dd8e9b756d98f58816', 'b0927f9bed064d729ac8f59792333e38', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (11, '0464e4a57ba042dd8e9b756d98f58816', 'fcc51e82bafd47579a4fb80671328c50', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (12, '0464e4a57ba042dd8e9b756d98f58816', 'ade9fc93cdab477dba55e3f6afe1a8c0', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (13, '0464e4a57ba042dd8e9b756d98f58816', 'f2672d0a4a4543f6a2b70a8ff0746ee6', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (14, '0464e4a57ba042dd8e9b756d98f58816', '9658684bf62545faabbe523e9b2cee91', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (15, '0464e4a57ba042dd8e9b756d98f58816', '84fedf0594c843fb89bebd6d6ce456bf', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (16, '0464e4a57ba042dd8e9b756d98f58816', '00664f3d9e7542b9adb9863007c59029', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (17, '0464e4a57ba042dd8e9b756d98f58816', '154a86cfe26e48c5aa176a744fa5fdf0', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (18, '0464e4a57ba042dd8e9b756d98f58816', '85eef2ea062f407391b81b8a4964b6fb', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (19, '0464e4a57ba042dd8e9b756d98f58816', '36d77f457f6f4c5e9eb4727d66cb9eec', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (20, '0464e4a57ba042dd8e9b756d98f58816', '35d8dd946abf4cf8b4b68cdb1d7f825e', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (21, '0464e4a57ba042dd8e9b756d98f58816', 'f7fb7e3009fc4c9db225d9be1caf2978', 1, '2021-05-28 13:39:52', '2021-05-28 13:39:52');
INSERT INTO `outstockinfo` VALUES (22, '8d631e2301bf48e5a2c069efff5c042e', '91a0371287b043cfa8ec26564530231f', 8, '2021-05-30 10:55:26', '2021-05-30 10:55:26');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `p_num` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `image_url` text COMMENT '图片',
  `model` varchar(100) DEFAULT NULL COMMENT '规格型号',
  `unit` varchar(10) DEFAULT NULL COMMENT '计算单位',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `one_category_id` bigint(20) DEFAULT NULL COMMENT '1级分类',
  `two_category_id` bigint(20) DEFAULT NULL COMMENT '2级分类',
  `three_category_id` bigint(20) DEFAULT NULL COMMENT '3级分类',
  `status` int(1) DEFAULT '0' COMMENT '是否删除:1物资正常,0:物资回收,2:物资审核中',
  PRIMARY KEY (`id`),
  KEY `category_id` (`one_category_id`),
  KEY `id` (`id`),
  KEY `three_category_id` (`three_category_id`),
  KEY `two_category_id` (`two_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (2, '41f9ffd55d504f93b7b128b331bf6081', 'N95口罩', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/e4376dd1ec874c53acdc8847655de527.jpeg', '10个/包', '包', 'N95型口罩是NIOSH（美国国家职业安全卫生研究所，National Institute for Occupational Safety and Health）认证的9种颗粒物防护口罩中的一种。', 10, '2021-04-05 11:53:18', '2021-05-27 15:25:38', 6, 44, 65, 0);
INSERT INTO `product` VALUES (3, '4aacd6ec7cc24040a4a834927b093d55', '医用棉签', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/9064664608d5410f84dd485632b23003.jpeg', '50支/包', '包', '外用棉签', 2, '2021-04-11 22:10:11', '2021-05-27 13:58:31', 6, 44, 66, 0);
INSERT INTO `product` VALUES (5, '0beae59fb7eb4dc2b78a5f2a3848c66e', '仪器车', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/7dcb27b6c37a457f8ad449c9a50b7f85.jpeg', '1订单/1辆', '辆', '可以将实验室的某些仪器的组合安装在不锈钢仪器车上', 2, '2021-05-27 13:57:57', '2021-05-27 14:07:38', 4, 7, 55, 0);
INSERT INTO `product` VALUES (6, 'e6643852dcec426d8939a3ecd820e5f2', '氧气设备', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/4b6f939d0dcc4d108ae079311b883bb2.jpeg', '1订单/1罐', '罐', '制氧设备是制造气体氧气的设备。制氧设备包括三方面，工业制氧设备，家用制氧设备，医疗医用制氧设备', 3, '2021-05-27 14:00:16', '2021-05-27 14:07:39', 4, 7, 56, 0);
INSERT INTO `product` VALUES (7, 'eab44d927fb042c9aa1369b71c191c6f', '抢救车', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/6221692dea9645a987113300e095fd90.jpg', '1订单/1辆', '辆', '抢救车，包括车体及放置在车体上的医用橱柜，医用橱柜包括柜体和箱盖。', 4, '2021-05-27 14:03:01', '2021-05-27 14:07:41', 4, 7, 55, 0);
INSERT INTO `product` VALUES (8, '2a34f46025fc4f9ba4a577e61079594f', '输液泵', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/a1063cc181b74d94aaa41f86e9b48389.jpg', '1订单/1台', '台', '输液泵通常是机械或电子的控制装置，它通过作用于输液导管达到控制输液速度的目的。', 5, '2021-05-27 14:05:20', '2021-05-27 14:07:42', 4, 8, 57, 0);
INSERT INTO `product` VALUES (9, '40b60a3f639449f3938021330ab4f298', '注射泵', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/63d0715169114cec85dfa9afad6e9236.jpg', '1订单/1台', '台', '注射泵由步进电机及其驱动器、丝杆和支架等构成，具有往复移动的丝杆、螺母，因此也称为丝杆泵。螺母与注射器的活塞相连，注射器里盛放药液，实现高精度，平稳无脉动的液体传输。', 6, '2021-05-27 14:07:34', '2021-05-27 14:07:44', 4, 8, 57, 0);
INSERT INTO `product` VALUES (10, 'b0927f9bed064d729ac8f59792333e38', '电子血压计', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/1c9b6f1334904ca8ab561fd1dde8138a.jpeg', '1订单/1台', '台', '电子血压计外观轻巧，操作简便，显示清晰。测量时血压、心率一次完成。电子血压计如按测量部位来划分，可分为手臂式、手腕式与手指式。', 7, '2021-05-27 14:09:44', '2021-05-27 14:10:04', 4, 8, 58, 0);
INSERT INTO `product` VALUES (11, 'fcc51e82bafd47579a4fb80671328c50', '血糖仪', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/ac0b8ff6f35046a9a5d4d5483c45fca0.jpeg', '1订单/1个', '个', '血糖仪又称血糖计，是一种测量血糖水平的电子仪器。血糖仪从工作原理上分为光电型和电极型两种。电极型血糖仪的测试原理更科学，电极可内藏。', 8, '2021-05-27 14:11:44', '2021-05-27 14:11:55', 4, 8, 58, 0);
INSERT INTO `product` VALUES (12, 'ade9fc93cdab477dba55e3f6afe1a8c0', '全自动化分析仪', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/60aef0f32b2c48b181c1e1dd379c400f.png', '1订单/1台', '台', '全自动生化分析仪是根据光电比色原理来测量体液中某种特定化学成分的仪器。', 9, '2021-05-27 14:15:36', '2021-05-27 14:30:22', 4, 9, 59, 0);
INSERT INTO `product` VALUES (13, 'f2672d0a4a4543f6a2b70a8ff0746ee6', '全自动化唾液分析仪', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c40166f00d4f49269ce4787beec3287e.jpg', '1订单/1台', '台', '德国MERCURY INSTRUMENTS公司，十几年来一直致力于开发、生产和销售汞分析仪，对汞分析仪具有丰富的经验。', 10, '2021-05-27 14:16:59', '2021-05-27 14:30:24', 4, 9, 59, 0);
INSERT INTO `product` VALUES (14, '9658684bf62545faabbe523e9b2cee91', '半自动化血细胞分析仪', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/5a8aa7d3b718439a866b9de41ee2b7e7.jpeg', '1订单/1台', '台', '血细胞分析仪又叫血液细胞分析仪、血球仪、血球计数仪等，是医院临床检验应用非常广泛的仪器之一，随着近几年计算机技术的日新月异的发展，', 10, '2021-05-27 14:18:32', '2021-05-27 14:30:25', 4, 9, 60, 0);
INSERT INTO `product` VALUES (15, '84fedf0594c843fb89bebd6d6ce456bf', '半自动化尿液分析仪', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/2e87ea4c2d1d431e93263f378ffd2161.jpeg', '1订单/1台', '台', '尿液分析仪是测定尿中某些化学成分的自动化仪器，它是医学实验室尿液自动化检查的重要工具，具有操作简单、快速等优点。仪器在计算机的控制下通过收集、分析试带上各种试剂块的颜色信息，并经过一系列信号转化，最后输出测定的尿液中化学成分含量。', 10, '2021-05-27 14:21:04', '2021-05-27 14:30:27', 4, 9, 60, 0);
INSERT INTO `product` VALUES (16, '00664f3d9e7542b9adb9863007c59029', '半自动化粪便分析仪', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/bb75f908e3db429b8fdeee8cd8a7d1c8.jpg', '1订单/1台', '台', ' “三大常规”之一的粪便检查目前仍以手工操作为主，远远落后于“血液常规”和“尿液常规”的发展，制约了粪便检查在临床的应用。', 10, '2021-05-27 14:33:58', '2021-05-27 14:44:26', 4, 9, 60, 0);
INSERT INTO `product` VALUES (17, '154a86cfe26e48c5aa176a744fa5fdf0', '小型X光机（床边机）', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/fc31b032de824b26a343179b694b20cf.jpeg', '1订单/1台', '台', '供患者胸部、腹部、骨与软组织进行数字化X射线摄影检查用', 10, '2021-05-27 14:35:17', '2021-05-27 14:44:27', 4, 10, 61, 0);
INSERT INTO `product` VALUES (18, '85eef2ea062f407391b81b8a4964b6fb', '便携式床旁彩超', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/3f0f8176219a47abbb4073665af1bab6.jpeg', '1订单/1台', '台', '床旁彩超作为快速、简单、精准的影像学检查技术，在NICU及时地提供患儿颅脑组织病变的状况，以及心脏、腹部等超声信息，', 10, '2021-05-27 14:36:51', '2021-05-27 14:44:29', 4, 10, 62, 0);
INSERT INTO `product` VALUES (19, '36d77f457f6f4c5e9eb4727d66cb9eec', '动态DR', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/3d505cd304c847888f9ac1e974edf85f.png', '1订单/1台', '台', '动态DR是一款多功能DR，能够数字拍片、数字透视、数字造影。', 10, '2021-05-27 14:42:05', '2021-05-27 14:44:31', 4, 10, 63, 0);
INSERT INTO `product` VALUES (20, '35d8dd946abf4cf8b4b68cdb1d7f825e', '静态DR', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/da5c85aca1984f42bff01d148785f6b8.jpeg', '1订单/1台', '台', 'DR相比传统X线机具有影像更清晰、辐射量更低、检查速度更快等特点。随着技术的发展，近年来更是出现了动态DR，', 10, '2021-05-27 14:44:18', '2021-05-27 14:44:32', 4, 10, 63, 0);
INSERT INTO `product` VALUES (21, 'f7fb7e3009fc4c9db225d9be1caf2978', '无创呼吸机', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/770cd42bc17f443fbd2441f19363d2db.jpeg', '1订单/1台', '台', '无创呼吸机又称Continuous Positive Airway Pressure（持续气道正压通气）的英文缩写。CPAP在临床上用于治疗睡眠呼吸暂停综合症（SAS）及相关疾病', 10, '2021-05-27 14:45:52', '2021-05-27 15:30:37', 4, 11, 64, 0);
INSERT INTO `product` VALUES (22, '66fe1334fb214c31b4f2115509f2abbd', '有创呼吸机', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/19814b5cf7ea49b69e26ebdb73457b22.jpeg', '1订单/1台', '台', '当生物体自主呼吸不能满足正常生理需要时，用来支持人体或其他动物体呼吸。适用于各类医疗机构；用于心肺脑复苏的呼吸支持；各种原因导致的急性呼吸功能不全或氧合功能障碍；术中、术后呼吸支持；其他需要呼吸机治疗者。', 10, '2021-05-27 14:46:49', '2021-05-27 14:53:49', 4, 11, 64, 0);
INSERT INTO `product` VALUES (23, 'df9e361717474ff095ad16c24ba0b1d9', '小型麻醉机', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/46d19316eae14df0bfd7c5e0570d206b.jpeg', '1订单/1台', '台', '麻醉机是通过机械回路将麻醉药送入患者的肺泡，形成麻醉药气体分压，弥散到血液后，对中枢神经系统直接发生抑制作用，从而产生全身麻醉的效果。麻醉机属于半开放式麻醉装置。它主要由麻醉蒸发罐、流量计、折叠式风箱呼吸机、呼吸回路（含吸、呼气单向活瓣及手动气囊）、波纹管路等部件组成。', 10, '2021-05-27 14:48:03', '2021-05-27 14:53:48', 4, 11, 30, 0);
INSERT INTO `product` VALUES (24, '7418493128c94701865c131b3b90620d', '牛黄解毒丸', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c512866f6b2e4eb8adcaa0437f48a348.jpg', '50粒/盒', '盒', '牛黄解毒丸，中成药名。为清热剂，具有清热解毒之功效。用于火热内盛，咽喉肿痛，牙龈肿痛，口舌生疮，目赤肿痛。', 10, '2021-05-27 14:53:40', '2021-05-27 14:53:46', 5, 31, 69, 0);
INSERT INTO `product` VALUES (25, '1305d5b7c1d745258bc016dc56f8e6d6', '午时茶', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/1f162679ca174ada89457f07642f2150.jpg', '20小包/包', '包', '午时茶，祛风解表，化湿和中。用于外感风寒、内伤食积证，症见恶寒发热、头痛身楚、胸脘满闷、恶心呕吐、腹痛腹泻。', 10, '2021-05-27 14:54:48', '2021-05-27 15:16:37', 5, 31, 69, 0);
INSERT INTO `product` VALUES (26, 'fee4a239cc364540a559e0cb26a1d0cb', '人参药酒', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/cd9050e966fb42f08d8a3dc08ee76854.jpg', '800ml/瓶', '瓶', '补气养血，暖胃散寒。用于气血两亏，神疲乏力，胃寒作痛，食欲不振。', 10, '2021-05-27 14:56:23', '2021-05-27 15:30:41', 5, 31, 69, 0);
INSERT INTO `product` VALUES (27, 'b90d4a960dc54eb2aa4284dc909823b2', '七厘散', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/ba4352e5138f45a88b2414a0e53687a6.jpg', '1支/盒', '盒', '七厘散，中成药名。为理血剂，具有化瘀消肿，止痛止血功效。用于跌扑损伤，血瘀疼痛，外伤出血。', 10, '2021-05-27 15:01:50', '2021-05-27 15:30:38', 5, 31, 69, 0);
INSERT INTO `product` VALUES (28, 'c15ce55ddafc48bba0f3ba1132211207', '舒筋活络丸', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c71b777063eb4348af2fa98519d28b0d.jpg', '50粒/盒', '盒', '舒筋活络丸，驱风祛湿，舒筋活络。用于一般骨节风痛，腰膝酸痛。', 10, '2021-05-27 15:03:32', '2021-05-27 15:30:43', 5, 32, 70, 0);
INSERT INTO `product` VALUES (29, '3ef6d79631384c8786d45e97836d1ac3', '多烯磷脂酸酰胆碱胶囊', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/962e2a65770f4b8db209a8bdfc5f6086.jpg', '20粒/盒', '盒', '多烯磷脂酰胆碱胶囊，适应症为辅助改善中毒性肝损伤（如药物、毒物、化学物质和酒精引起的肝损伤等）以及脂肪肝和肝炎患者的食欲不振、右上腹压迫感。', 10, '2021-05-27 15:07:09', '2021-05-27 15:30:44', 5, 32, 70, 0);
INSERT INTO `product` VALUES (30, '16972de3c716441eab3f6b817544e366', '双环醇片', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/b3266dddb1c4473c8993d6d3a2be7bb2.jpg', '18粒/盒', '盒', '双环醇片，适应症为本品可用于治疗慢性肝炎所致的氨基转移酶升高。', 10, '2021-05-27 15:09:54', '2021-05-27 15:30:46', 5, 32, 70, 0);
INSERT INTO `product` VALUES (31, 'a80984c6875349c0b1c12fa57478da10', '青霉素', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/8ce64c286a644415a68da4dd9ba5c9d6.jpg', '10瓶/盒', '盒', '青霉素（Penicillin，或音译盘尼西林）又被称为青霉素G、peillin G、 盘尼西林、配尼西林、青霉素钠、苄青霉素钠、青霉素钾、苄青霉素钾。', 10, '2021-05-27 15:11:20', '2021-05-27 15:11:30', 5, 33, 71, 0);
INSERT INTO `product` VALUES (32, 'c7b21870aa6247d78ef877e7a3a6a923', '头孢唑林', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/9d6d5aa406c04cc889cbc8a49e2fe2a0.jpg', '10瓶/盒', '盒', '头孢唑林，用于敏感菌所致的呼吸道、泌尿生殖系、皮肤软组织、骨和关节、胆道等感染，也可用于心内膜炎、败血症、咽和耳部感染。', 10, '2021-05-27 15:14:23', '2021-05-27 15:30:47', 5, 33, 71, 0);
INSERT INTO `product` VALUES (33, '838232bdf767415b934c47c25c06313a', '卡那霉素', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/6e00657d2229489c856fde19de2f998f.jpg', '10瓶/盒', '盒', '卡那霉素，是一种蛋白质生物合成抑制剂，通过与30S核糖体结合从而致使mRNA密码误读。若细菌中产生一种破坏卡那霉素的酶，则可变为抗性株。', 10, '2021-05-27 15:15:48', '2021-05-27 15:16:48', 5, 33, 71, 0);
INSERT INTO `product` VALUES (34, '91a0371287b043cfa8ec26564530231f', '一次性医用口罩', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/d31022e021ea4947ac0f1b4f54c4f551.jpeg', '50个/包', '包', '一次性使用医用口罩适用于覆盖使用者的口、鼻及下颌，用于普通医疗环境中佩戴、阻隔口腔和鼻腔呼出或喷出污染物的一次性使用口罩。口罩的细菌过滤效率应不小于95%。不适用于医用防护口罩，医用外科口罩。', 10, '2021-05-27 15:18:58', '2021-05-27 15:30:48', 6, 44, 65, 0);
INSERT INTO `product` VALUES (35, 'd594f3047a654f37b369574a1945ccaf', '一次性帽子', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/1a1006da80b34cab811cd2b2d64ef663.jpeg', '10个/包', '包', '一次性使用医用帽子适用于非传染病区临床卫生护理用。', 10, '2021-05-27 15:20:10', '2021-05-27 15:30:50', 6, 44, 66, 0);
INSERT INTO `product` VALUES (36, '9394a51f77ee4750bc92bfd1e91d9854', '防护头罩', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/5fcc760fdb774b3185dfa875e08f6882.jpg', '1订单/1个', '个', '本标准规定了医用防护头罩的结构组成、性能要求、试验方法、标志、包装、贮存和运输。', 10, '2021-05-27 15:23:11', '2021-05-27 15:30:51', 6, 44, 66, 0);
INSERT INTO `product` VALUES (37, '07299a2b749c47e581d217342087cc49', '护目镜', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/dc18a07561f64b77a0071950c7799a62.jpg', '10个/盒', '盒', '防护眼镜就是一种滤光镜，可以改变透过光强和光谱。避免辐射光对眼睛造成伤害，最有效和最常用的方法是配戴防护眼镜。', 10, '2021-05-27 15:24:16', '2021-05-27 15:31:02', 6, 44, 66, 0);
INSERT INTO `product` VALUES (38, 'f36b8b6214574845aae0619e7c33c3b8', '一次性防护服', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/e3220dd5055f46bab773e214fddf4cac.jpg', '1件/套', '套', '防护服种类包括消防防护服、工业用防护服、医疗款防护服、军用防护服和特殊人群使用防护服。防护服主要应用于消防、军工、船舶、石油、化工、喷漆、清洗消毒、实验室等行业与部门。', 10, '2021-05-27 15:27:31', '2021-05-27 15:30:55', 6, 46, 67, 0);
INSERT INTO `product` VALUES (39, '23128c4c5edd429d8e08026016d337ca', '防水鞋套', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/f165e51837d24bbaab4f89d0efae813c.jpg', '20双/包', '包', '防雨鞋套又名雨鞋套或防水鞋套，是一种雨雪天保护鞋子的工具，防雨鞋套的英文OVER-SHOES。它方便易携，替代了雨靴笨重不易携带的缺点，是都市生活的必备用品。', 10, '2021-05-27 15:28:49', '2021-05-27 15:30:58', 6, 46, 68, 0);
INSERT INTO `product` VALUES (40, '7a40f8d814394067a289f70e8e7ea484', '一次性医用手套', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c98f226d0d89400e89ab1f923237e874.jpg', '20个/包', '包', '在一些手套更换频率较高的行业，通常建议使用一次性手套，这样不但可以避免交叉感染，更可以大大节约更换成本，比如医疗行业、实验室、食品加工行业等对卫生要求比较高的行业。', 10, '2021-05-27 15:30:11', '2021-05-27 15:31:00', 6, 46, 68, 0);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类别名称',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (4, '医疗设备设施', '主要的一些医疗仪器，检查仪器', 1, '2021-05-27 12:20:53', '2021-05-27 12:20:53', 0);
INSERT INTO `category` VALUES (5, '医疗药物设施', '一些治疗的物品，主要是一些轻度的', 2, '2021-05-27 12:23:01', '2021-05-27 12:23:01', 0);
INSERT INTO `category` VALUES (6, '外用防护设施', '主要是一些外用的防护物品，为必要的出行做出一些保障', 3, '2021-05-27 12:24:51', '2021-05-27 12:24:51', 0);
INSERT INTO `category` VALUES (7, '基础类设备', '一些基本的医疗设备', 1, '2021-05-27 12:25:32', '2021-05-27 12:25:32', 4);
INSERT INTO `category` VALUES (8, '抢救及生命支持类设备', '紧急情况下的必要设备，快速定位患者情况', 2, '2021-05-27 12:26:27', '2021-05-27 12:26:27', 4);
INSERT INTO `category` VALUES (9, '检验类设备', '检查，分析患者病情所需的设备', 3, '2021-05-27 12:29:19', '2021-05-27 12:29:19', 4);
INSERT INTO `category` VALUES (10, '影像类设备', '用于检查患者内部病情，查看内部肌肉组织情况', 4, '2021-05-27 12:30:35', '2021-05-27 12:30:35', 4);
INSERT INTO `category` VALUES (11, '呼麻急救类设备', '呼吸，麻醉设备', 5, '2021-05-27 12:33:03', '2021-05-27 12:33:03', 4);
INSERT INTO `category` VALUES (30, '麻醉机', '呼麻设备', 3, '2021-05-27 12:52:42', '2021-05-27 12:52:42', 11);
INSERT INTO `category` VALUES (31, '中成药剂', '主要是中药', 1, '2021-05-27 12:59:08', '2021-05-27 12:59:08', 5);
INSERT INTO `category` VALUES (32, '中西成药', '中药和西药互相作用', 2, '2021-05-27 12:59:53', '2021-05-27 12:59:53', 5);
INSERT INTO `category` VALUES (33, '抗生素', '抑制细菌感染等措施', 3, '2021-05-27 13:00:28', '2021-05-27 13:00:28', 5);
INSERT INTO `category` VALUES (44, '头部防护', '主要头部防护', 1, '2021-05-27 13:20:10', '2021-05-27 13:20:10', 6);
INSERT INTO `category` VALUES (46, '肢体防护', '主要肢体防护', 2, '2021-05-27 13:20:55', '2021-05-27 13:20:55', 6);
INSERT INTO `category` VALUES (55, '运输车', '各种仪器运输车', 1, '2021-05-27 13:32:22', '2021-05-27 13:32:22', 7);
INSERT INTO `category` VALUES (56, '气体仪器', '提供一些必须的气体', 2, '2021-05-27 13:33:16', '2021-05-27 13:33:16', 7);
INSERT INTO `category` VALUES (57, '气泵类', '提供气压，主要液体输入', 1, '2021-05-27 13:34:51', '2021-05-27 13:34:51', 8);
INSERT INTO `category` VALUES (58, '基本仪器', '一些基础设施', 2, '2021-05-27 13:35:33', '2021-05-27 13:35:33', 8);
INSERT INTO `category` VALUES (59, '全自动化', '全自动设备仪器', 1, '2021-05-27 13:36:20', '2021-05-27 13:36:20', 9);
INSERT INTO `category` VALUES (60, '半自动化仪器', '半自动化仪器设备', 2, '2021-05-27 13:36:39', '2021-05-27 13:36:39', 9);
INSERT INTO `category` VALUES (61, 'X光线类', 'x射线', 1, '2021-05-27 13:39:22', '2021-05-27 13:39:22', 10);
INSERT INTO `category` VALUES (62, '彩超', '提供彩超检验', 2, '2021-05-27 13:39:47', '2021-05-27 13:39:47', 10);
INSERT INTO `category` VALUES (63, 'DR类', 'DR类设备', 3, '2021-05-27 13:40:08', '2021-05-27 13:40:08', 10);
INSERT INTO `category` VALUES (64, '呼吸机', '呼吸机类', 1, '2021-05-27 13:40:41', '2021-05-27 13:40:41', 11);
INSERT INTO `category` VALUES (65, '口罩类', '防治飞尘等细小颗粒感染物品', 1, '2021-05-27 13:44:35', '2021-05-27 13:44:35', 44);
INSERT INTO `category` VALUES (66, '其他防护类', '主要头部，眼部等防护', 2, '2021-05-27 13:45:05', '2021-05-27 13:45:05', 44);
INSERT INTO `category` VALUES (67, '防护服装', '服装整体防护', 1, '2021-05-27 13:46:38', '2021-05-27 13:46:38', 46);
INSERT INTO `category` VALUES (68, '手脚防护', '手脚防护类目，主要是鞋套和手套', 2, '2021-05-27 13:47:11', '2021-05-27 13:47:11', 46);
INSERT INTO `category` VALUES (69, '药品', '药物', 1, '2021-05-27 14:52:20', '2021-05-27 14:52:20', 31);
INSERT INTO `category` VALUES (70, '药品', '药物', 1, '2021-05-27 14:52:37', '2021-05-27 14:52:37', 32);
INSERT INTO `category` VALUES (71, '药品', '药物', 1, '2021-05-27 14:52:49', '2021-05-27 14:52:49', 33);
COMMIT;

-- ----------------------------
-- Table structure for productstock
-- ----------------------------
DROP TABLE IF EXISTS `productstock`;
CREATE TABLE `productstock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stock` bigint(20) DEFAULT NULL COMMENT '商品库存结余',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of productstock
-- ----------------------------
BEGIN;
INSERT INTO `productstock` VALUES (1, '6260dfeb678e480a82d20339954b4949', 12);
INSERT INTO `productstock` VALUES (2, '4aacd6ec7cc24040a4a834927b093d55', 14);
INSERT INTO `productstock` VALUES (3, '0beae59fb7eb4dc2b78a5f2a3848c66e', 16);
INSERT INTO `productstock` VALUES (4, 'e6643852dcec426d8939a3ecd820e5f2', 18);
INSERT INTO `productstock` VALUES (5, 'eab44d927fb042c9aa1369b71c191c6f', 19);
INSERT INTO `productstock` VALUES (6, '2a34f46025fc4f9ba4a577e61079594f', 11);
INSERT INTO `productstock` VALUES (7, '40b60a3f639449f3938021330ab4f298', 10);
INSERT INTO `productstock` VALUES (8, 'b0927f9bed064d729ac8f59792333e38', 14);
INSERT INTO `productstock` VALUES (9, 'fcc51e82bafd47579a4fb80671328c50', 12);
INSERT INTO `productstock` VALUES (10, 'ade9fc93cdab477dba55e3f6afe1a8c0', 15);
INSERT INTO `productstock` VALUES (11, '41f9ffd55d504f93b7b128b331bf6081', 12);
INSERT INTO `productstock` VALUES (12, 'f2672d0a4a4543f6a2b70a8ff0746ee6', 13);
INSERT INTO `productstock` VALUES (13, '9658684bf62545faabbe523e9b2cee91', 17);
INSERT INTO `productstock` VALUES (14, '84fedf0594c843fb89bebd6d6ce456bf', 11);
INSERT INTO `productstock` VALUES (15, '00664f3d9e7542b9adb9863007c59029', 12);
INSERT INTO `productstock` VALUES (16, '154a86cfe26e48c5aa176a744fa5fdf0', 14);
INSERT INTO `productstock` VALUES (17, '85eef2ea062f407391b81b8a4964b6fb', 15);
INSERT INTO `productstock` VALUES (18, '36d77f457f6f4c5e9eb4727d66cb9eec', 17);
INSERT INTO `productstock` VALUES (19, '35d8dd946abf4cf8b4b68cdb1d7f825e', 14);
INSERT INTO `productstock` VALUES (20, 'f7fb7e3009fc4c9db225d9be1caf2978', 16);
INSERT INTO `productstock` VALUES (21, '66fe1334fb214c31b4f2115509f2abbd', 5);
INSERT INTO `productstock` VALUES (22, 'df9e361717474ff095ad16c24ba0b1d9', 10);
INSERT INTO `productstock` VALUES (23, '7418493128c94701865c131b3b90620d', 3);
INSERT INTO `productstock` VALUES (24, '1305d5b7c1d745258bc016dc56f8e6d6', 4);
INSERT INTO `productstock` VALUES (25, 'fee4a239cc364540a559e0cb26a1d0cb', 5);
INSERT INTO `productstock` VALUES (26, 'b90d4a960dc54eb2aa4284dc909823b2', 6);
INSERT INTO `productstock` VALUES (27, 'c15ce55ddafc48bba0f3ba1132211207', 7);
INSERT INTO `productstock` VALUES (28, '3ef6d79631384c8786d45e97836d1ac3', 4);
INSERT INTO `productstock` VALUES (29, '16972de3c716441eab3f6b817544e366', 5);
INSERT INTO `productstock` VALUES (30, 'a80984c6875349c0b1c12fa57478da10', 3);
INSERT INTO `productstock` VALUES (31, 'c7b21870aa6247d78ef877e7a3a6a923', 2);
INSERT INTO `productstock` VALUES (32, '838232bdf767415b934c47c25c06313a', 7);
INSERT INTO `productstock` VALUES (33, '91a0371287b043cfa8ec26564530231f', 100);
INSERT INTO `productstock` VALUES (34, 'd594f3047a654f37b369574a1945ccaf', 9);
INSERT INTO `productstock` VALUES (35, '9394a51f77ee4750bc92bfd1e91d9854', 4);
INSERT INTO `productstock` VALUES (36, '07299a2b749c47e581d217342087cc49', 5);
INSERT INTO `productstock` VALUES (37, 'f36b8b6214574845aae0619e7c33c3b8', 3);
INSERT INTO `productstock` VALUES (38, '23128c4c5edd429d8e08026016d337ca', 5);
INSERT INTO `productstock` VALUES (39, '7a40f8d814394067a289f70e8e7ea484', 10);
COMMIT;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商地址',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '供应商电话',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `contact` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
BEGIN;
INSERT INTO `supplier` VALUES (1, '北京 东城区市交道口南大街27号', '北京市/北京市/东城区', 'liuyong@163.com', '13456276537', '2021-05-27 15:34:30', '2021-05-27 15:34:30', 1, '刘永');
INSERT INTO `supplier` VALUES (2, '天津市河西区商业街123号', '天津市/天津市/河西区', 'zhanghe@qq.com', '13426767876', '2021-05-27 15:39:55', '2021-05-27 15:39:55', 2, '张赫');
INSERT INTO `supplier` VALUES (3, '河北省邯郸市复兴区和平路23号', '河北省/邯郸市/复兴区', 'qinhuang@hr.com', '15626265364', '2021-05-27 15:42:23', '2021-05-27 15:42:23', 3, '秦黄');
INSERT INTO `supplier` VALUES (4, '福建省厦门市思明区南投路23号', '福建省/厦门市/思明区', 'xuwenqiang@qq.com', '15767855362', '2021-05-27 15:44:06', '2021-05-27 15:44:06', 4, '许文强');
INSERT INTO `supplier` VALUES (5, '江西省赣州市章贡区客家大道156号', '江西省/赣州市/章贡区', 'fortunate_qkm@163.com', '15637374635', '2021-05-27 15:45:02', '2021-05-27 15:47:00', 5, '邱康明');
INSERT INTO `supplier` VALUES (6, '北京市丰台区长安街34号', '北京市/北京市/丰台区', 'fortunate_qkm@163.com', '13423234567', '2021-05-28 13:22:30', '2021-05-28 13:22:30', 5, '黄磊');
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系名',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系办公电话',
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '办公室地点',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES (1, '物资管理部', '15045741241', '物资储料房', '2020-03-16 00:00:00', '2021-01-25 15:32:29');
INSERT INTO `department` VALUES (2, '采购部', '15079451241', '采购中心大楼', '2020-03-16 00:00:00', '2020-08-19 16:48:20');
INSERT INTO `department` VALUES (3, '信息技术部', '18214521412', '3楼405房间', '2020-03-19 00:00:00', '2020-08-19 16:48:23');
INSERT INTO `department` VALUES (4, '行政部', '15079457458', '3栋504房间', '2020-03-19 00:00:00', '2020-03-25 11:27:35');
COMMIT;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片名称，用于删除',
  `path` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片路径',
  `size` bigint(20) DEFAULT NULL COMMENT '图片大小',
  `media_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片类型',
  `suffix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片后缀',
  `height` int(20) DEFAULT NULL COMMENT '图片高度',
  `width` int(20) DEFAULT NULL COMMENT '图片宽度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of image
-- ----------------------------
BEGIN;
INSERT INTO `image` VALUES (2, '2021/01/27/575c6d49830a4bfaadd4855979ac27dd.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/01/27/575c6d49830a4bfaadd4855979ac27dd.jpg', 747722, 'image/jpg', '.jpg', 2667, 4000, '2021-01-27 16:22:36');
INSERT INTO `image` VALUES (3, '2021/01/27/e049b1a8ecc7431bad6fe6965f97eaae.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/01/27/e049b1a8ecc7431bad6fe6965f97eaae.png', 536753, 'image/jpg', '.png', 1200, 1920, '2021-01-27 16:24:16');
INSERT INTO `image` VALUES (6, '2021/02/02/46ec0a92930b46e8a77eacb38359c3e4.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/02/02/46ec0a92930b46e8a77eacb38359c3e4.png', 78086, 'image/jpg', '.png', 70, 1920, '2021-02-02 23:11:17');
INSERT INTO `image` VALUES (8, '2021/03/05/774b20d2aaa5437699cf157cb3140fe5.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/05/774b20d2aaa5437699cf157cb3140fe5.jpeg', 17770, 'image/jpg', '.jpeg', 413, 295, '2021-03-05 00:39:17');
INSERT INTO `image` VALUES (9, '2021/03/05/00b2ec9365d047c899482388501dd5e0.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/05/00b2ec9365d047c899482388501dd5e0.jpg', 809636, 'image/jpg', '.jpg', 3000, 4000, '2021-03-05 22:44:41');
INSERT INTO `image` VALUES (10, '2021/03/06/8ae9b51752b142dc8d82c46da0275832.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/06/8ae9b51752b142dc8d82c46da0275832.png', 195941, 'image/jpg', '.png', 1042, 1920, '2021-03-06 19:51:57');
INSERT INTO `image` VALUES (11, '2021/03/06/5ca21b81f8a1440a9eee4d439b9b6335.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/06/5ca21b81f8a1440a9eee4d439b9b6335.png', 164329, 'image/jpg', '.png', 1042, 1920, '2021-03-06 19:53:36');
INSERT INTO `image` VALUES (12, '2021/03/06/7c3afcbf9a1c4e9e9aaf1f49c50bebb0.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/06/7c3afcbf9a1c4e9e9aaf1f49c50bebb0.png', 144455, 'image/jpg', '.png', 1042, 1920, '2021-03-06 19:53:36');
INSERT INTO `image` VALUES (21, '2021/03/06/c190bd78af9c4abdb320fd48bcee378b.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/06/c190bd78af9c4abdb320fd48bcee378b.png', 278472, 'image/jpg', '.png', 483, 1800, '2021-03-06 19:54:36');
INSERT INTO `image` VALUES (22, '2021/03/06/a44710bd5efc402fbdeb409a92261c9f.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/06/a44710bd5efc402fbdeb409a92261c9f.png', 278472, 'image/jpg', '.png', 483, 1800, '2021-03-06 19:54:36');
INSERT INTO `image` VALUES (26, '2021/03/28/27caa0f0379b4b118e91a5ac72fffcac.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/28/27caa0f0379b4b118e91a5ac72fffcac.jpg', 809636, 'image/jpg', '.jpg', 3000, 4000, '2021-03-28 21:18:13');
INSERT INTO `image` VALUES (42, '2021/04/12/e8d2a46eea2746998bc765e046bacf94.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/e8d2a46eea2746998bc765e046bacf94.jpeg', 57981, 'image/jpg', '.jpeg', 734, 826, '2021-04-12 23:04:58');
INSERT INTO `image` VALUES (43, '2021/04/12/d9c64c8085e643899d14636f9a47bd7b.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/d9c64c8085e643899d14636f9a47bd7b.jpeg', 15382, 'image/jpg', '.jpeg', 292, 292, '2021-04-12 23:05:17');
INSERT INTO `image` VALUES (44, '2021/04/12/e4376dd1ec874c53acdc8847655de527.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/e4376dd1ec874c53acdc8847655de527.jpeg', 30443, 'image/jpg', '.jpeg', 400, 400, '2021-04-12 23:15:10');
INSERT INTO `image` VALUES (45, '2021/04/12/9064664608d5410f84dd485632b23003.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/9064664608d5410f84dd485632b23003.jpeg', 15420, 'image/jpg', '.jpeg', 300, 296, '2021-04-12 23:15:25');
INSERT INTO `image` VALUES (46, '2021/04/18/9bf22ae9d9af4e658ad3cabceec40d52.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/18/9bf22ae9d9af4e658ad3cabceec40d52.jpeg', 30443, 'image/jpg', '.jpeg', 400, 400, '2021-04-18 18:28:58');
INSERT INTO `image` VALUES (47, '2021/05/27/3293d6749fdf45d9b1f9904df0a0c625.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/3293d6749fdf45d9b1f9904df0a0c625.jpeg', 44476, 'image/jpg', '.jpeg', 800, 800, '2021-05-27 13:50:38');
INSERT INTO `image` VALUES (48, '2021/05/27/7dcb27b6c37a457f8ad449c9a50b7f85.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/7dcb27b6c37a457f8ad449c9a50b7f85.jpeg', 66231, 'image/jpg', '.jpeg', 1200, 800, '2021-05-27 13:57:01');
INSERT INTO `image` VALUES (49, '2021/05/27/4b6f939d0dcc4d108ae079311b883bb2.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/4b6f939d0dcc4d108ae079311b883bb2.jpeg', 10992, 'image/jpg', '.jpeg', 350, 225, '2021-05-27 13:59:38');
INSERT INTO `image` VALUES (50, '2021/05/27/6221692dea9645a987113300e095fd90.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/6221692dea9645a987113300e095fd90.jpg', 117909, 'image/jpg', '.jpg', 584, 1036, '2021-05-27 14:02:41');
INSERT INTO `image` VALUES (51, '2021/05/27/a1063cc181b74d94aaa41f86e9b48389.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/a1063cc181b74d94aaa41f86e9b48389.jpg', 10284, 'image/jpg', '.jpg', 400, 400, '2021-05-27 14:05:00');
INSERT INTO `image` VALUES (52, '2021/05/27/63d0715169114cec85dfa9afad6e9236.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/63d0715169114cec85dfa9afad6e9236.jpg', 14081, 'image/jpg', '.jpg', 333, 500, '2021-05-27 14:06:54');
INSERT INTO `image` VALUES (53, '2021/05/27/1c9b6f1334904ca8ab561fd1dde8138a.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/1c9b6f1334904ca8ab561fd1dde8138a.jpeg', 23695, 'image/jpg', '.jpeg', 480, 640, '2021-05-27 14:09:12');
INSERT INTO `image` VALUES (54, '2021/05/27/ac0b8ff6f35046a9a5d4d5483c45fca0.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/ac0b8ff6f35046a9a5d4d5483c45fca0.jpeg', 5363, 'image/jpg', '.jpeg', 200, 200, '2021-05-27 14:11:19');
INSERT INTO `image` VALUES (55, '2021/05/27/60aef0f32b2c48b181c1e1dd379c400f.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/60aef0f32b2c48b181c1e1dd379c400f.png', 150277, 'image/jpg', '.png', 400, 468, '2021-05-27 14:15:05');
INSERT INTO `image` VALUES (56, '2021/05/27/c40166f00d4f49269ce4787beec3287e.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c40166f00d4f49269ce4787beec3287e.jpg', 15571, 'image/jpg', '.jpg', 267, 400, '2021-05-27 14:16:40');
INSERT INTO `image` VALUES (57, '2021/05/27/5a8aa7d3b718439a866b9de41ee2b7e7.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/5a8aa7d3b718439a866b9de41ee2b7e7.jpeg', 4536, 'image/jpg', '.jpeg', 173, 200, '2021-05-27 14:18:15');
INSERT INTO `image` VALUES (58, '2021/05/27/2e87ea4c2d1d431e93263f378ffd2161.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/2e87ea4c2d1d431e93263f378ffd2161.jpeg', 6926, 'image/jpg', '.jpeg', 253, 268, '2021-05-27 14:19:17');
INSERT INTO `image` VALUES (59, '2021/05/27/bb75f908e3db429b8fdeee8cd8a7d1c8.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/bb75f908e3db429b8fdeee8cd8a7d1c8.jpg', 24033, 'image/jpg', '.jpg', 338, 400, '2021-05-27 14:33:37');
INSERT INTO `image` VALUES (60, '2021/05/27/fc31b032de824b26a343179b694b20cf.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/fc31b032de824b26a343179b694b20cf.jpeg', 28779, 'image/jpg', '.jpeg', 470, 470, '2021-05-27 14:34:58');
INSERT INTO `image` VALUES (61, '2021/05/27/3f0f8176219a47abbb4073665af1bab6.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/3f0f8176219a47abbb4073665af1bab6.jpeg', 115247, 'image/jpg', '.jpeg', 589, 750, '2021-05-27 14:36:28');
INSERT INTO `image` VALUES (62, '2021/05/27/3d505cd304c847888f9ac1e974edf85f.png', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/3d505cd304c847888f9ac1e974edf85f.png', 286978, 'image/jpg', '.png', 340, 500, '2021-05-27 14:41:49');
INSERT INTO `image` VALUES (63, '2021/05/27/da5c85aca1984f42bff01d148785f6b8.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/da5c85aca1984f42bff01d148785f6b8.jpeg', 19642, 'image/jpg', '.jpeg', 505, 708, '2021-05-27 14:44:02');
INSERT INTO `image` VALUES (64, '2021/05/27/770cd42bc17f443fbd2441f19363d2db.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/770cd42bc17f443fbd2441f19363d2db.jpeg', 29112, 'image/jpg', '.jpeg', 194, 268, '2021-05-27 14:45:34');
INSERT INTO `image` VALUES (65, '2021/05/27/19814b5cf7ea49b69e26ebdb73457b22.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/19814b5cf7ea49b69e26ebdb73457b22.jpeg', 10637, 'image/jpg', '.jpeg', 271, 268, '2021-05-27 14:46:24');
INSERT INTO `image` VALUES (66, '2021/05/27/46d19316eae14df0bfd7c5e0570d206b.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/46d19316eae14df0bfd7c5e0570d206b.jpeg', 2463, 'image/jpg', '.jpeg', 140, 85, '2021-05-27 14:47:46');
INSERT INTO `image` VALUES (67, '2021/05/27/788bb31c8fa64ab5a0a00a2e721b98af.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/788bb31c8fa64ab5a0a00a2e721b98af.jpg', 10816, 'image/jpg', '.jpg', 300, 300, '2021-05-27 14:49:23');
INSERT INTO `image` VALUES (68, '2021/05/27/c512866f6b2e4eb8adcaa0437f48a348.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c512866f6b2e4eb8adcaa0437f48a348.jpg', 10816, 'image/jpg', '.jpg', 300, 300, '2021-05-27 14:53:10');
INSERT INTO `image` VALUES (69, '2021/05/27/1f162679ca174ada89457f07642f2150.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/1f162679ca174ada89457f07642f2150.jpg', 28456, 'image/jpg', '.jpg', 500, 500, '2021-05-27 14:54:22');
INSERT INTO `image` VALUES (70, '2021/05/27/cd9050e966fb42f08d8a3dc08ee76854.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/cd9050e966fb42f08d8a3dc08ee76854.jpg', 9876, 'image/jpg', '.jpg', 180, 180, '2021-05-27 14:55:47');
INSERT INTO `image` VALUES (71, '2021/05/27/ebdc7622bbe440ab8caaef277949cc89.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/ebdc7622bbe440ab8caaef277949cc89.jpg', 10816, 'image/jpg', '.jpg', 300, 300, '2021-05-27 14:57:05');
INSERT INTO `image` VALUES (72, '2021/05/27/ba4352e5138f45a88b2414a0e53687a6.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/ba4352e5138f45a88b2414a0e53687a6.jpg', 9853, 'image/jpg', '.jpg', 256, 320, '2021-05-27 15:01:18');
INSERT INTO `image` VALUES (73, '2021/05/27/c71b777063eb4348af2fa98519d28b0d.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c71b777063eb4348af2fa98519d28b0d.jpg', 69574, 'image/jpg', '.jpg', 468, 500, '2021-05-27 15:03:10');
INSERT INTO `image` VALUES (74, '2021/05/27/962e2a65770f4b8db209a8bdfc5f6086.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/962e2a65770f4b8db209a8bdfc5f6086.jpg', 17574, 'image/jpg', '.jpg', 160, 220, '2021-05-27 15:06:42');
INSERT INTO `image` VALUES (75, '2021/05/27/b3266dddb1c4473c8993d6d3a2be7bb2.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/b3266dddb1c4473c8993d6d3a2be7bb2.jpg', 28528, 'image/jpg', '.jpg', 500, 500, '2021-05-27 15:09:31');
INSERT INTO `image` VALUES (76, '2021/05/27/8ce64c286a644415a68da4dd9ba5c9d6.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/8ce64c286a644415a68da4dd9ba5c9d6.jpg', 25620, 'image/jpg', '.jpg', 500, 500, '2021-05-27 15:10:41');
INSERT INTO `image` VALUES (77, '2021/05/27/9d6d5aa406c04cc889cbc8a49e2fe2a0.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/9d6d5aa406c04cc889cbc8a49e2fe2a0.jpg', 8211, 'image/jpg', '.jpg', 165, 220, '2021-05-27 15:13:06');
INSERT INTO `image` VALUES (78, '2021/05/27/6e00657d2229489c856fde19de2f998f.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/6e00657d2229489c856fde19de2f998f.jpg', 12594, 'image/jpg', '.jpg', 300, 400, '2021-05-27 15:15:21');
INSERT INTO `image` VALUES (79, '2021/05/27/d31022e021ea4947ac0f1b4f54c4f551.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/d31022e021ea4947ac0f1b4f54c4f551.jpeg', 8388, 'image/jpg', '.jpeg', 191, 268, '2021-05-27 15:18:33');
INSERT INTO `image` VALUES (80, '2021/05/27/1a1006da80b34cab811cd2b2d64ef663.jpeg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/1a1006da80b34cab811cd2b2d64ef663.jpeg', 39249, 'image/jpg', '.jpeg', 500, 500, '2021-05-27 15:19:49');
INSERT INTO `image` VALUES (81, '2021/05/27/5fcc760fdb774b3185dfa875e08f6882.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/5fcc760fdb774b3185dfa875e08f6882.jpg', 27093, 'image/jpg', '.jpg', 260, 310, '2021-05-27 15:22:43');
INSERT INTO `image` VALUES (82, '2021/05/27/dc18a07561f64b77a0071950c7799a62.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/dc18a07561f64b77a0071950c7799a62.jpg', 53677, 'image/jpg', '.jpg', 482, 642, '2021-05-27 15:23:55');
INSERT INTO `image` VALUES (83, '2021/05/27/e3220dd5055f46bab773e214fddf4cac.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/e3220dd5055f46bab773e214fddf4cac.jpg', 24844, 'image/jpg', '.jpg', 702, 454, '2021-05-27 15:27:04');
INSERT INTO `image` VALUES (84, '2021/05/27/f165e51837d24bbaab4f89d0efae813c.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/f165e51837d24bbaab4f89d0efae813c.jpg', 55277, 'image/jpg', '.jpg', 500, 500, '2021-05-27 15:28:25');
INSERT INTO `image` VALUES (85, '2021/05/27/c98f226d0d89400e89ab1f923237e874.jpg', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/05/27/c98f226d0d89400e89ab1f923237e874.jpg', 31642, 'image/jpg', '.jpg', 350, 350, '2021-05-27 15:29:56');
COMMIT;

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作用户',
  `operation` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '操作内容',
  `time` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `method` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '操作方法',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '方法参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作地点',
  `result` text COMMENT '操作结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=690 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

-- ----------------------------
-- Records of logs
-- ----------------------------
BEGIN;
INSERT INTO `logs` VALUES (8, 'admin', '用户管理[修改]', 74, 'com.qkm.xinguan.controller.UserController.editUser()', 'paramName:[userDTO, id, result],args:[UserDTO(avatar=http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/03/27/817bbcee1994401bb9d043dceb6cdecf.jpg, username=admin, departmentId=3, nickname=小邱呀, sex=0, password=$2a$10$NTZ6cRR/hzKgc3OCc.Xc.ufAKi6bC85aEJtFkZMKrxgD6RBdidXtG, email=411882767@qq.com, phoneNumber=15679718827, birth=1998-10-13), 108, org.springframework.validation.BeanPropertyBindingResult: 0 errors]', '127.0.0.1', '2021-04-01 20:08:18', '内网IP|0|0|内网IP|内网IP', 'response:{\"code\":200,\"data\":\"\",\"message\":\"修改用户信息成功\",\"isSuccess\":true}');
INSERT INTO `logs` VALUES (9, 'admin', '入库记录[移入回收站]', 84, 'com.qkm.xinguan.controller.InStockController.remove()', 'paramName:[id],args:[85]', '127.0.0.1', '2021-04-03 21:55:53', '内网IP|0|0|内网IP|内网IP', 'response:{\"code\":200,\"data\":\"\",\"message\":\"操作成功\",\"isSuccess\":true}');
COMMIT;

-- ----------------------------
-- Table structure for login_logs
-- ----------------------------
DROP TABLE IF EXISTS `login_logs`;
CREATE TABLE `login_logs` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `user_system` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作系统',
  `user_browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录日志表';

-- ----------------------------
-- Records of login_logs
-- ----------------------------
BEGIN;
INSERT INTO `login_logs` VALUES (5, 'admin', '2021-03-15 17:18:38', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X', 'Chrome 8');
INSERT INTO `login_logs` VALUES (6, 'admin', '2021-03-23 21:05:50', '内网IP|0|0|内网IP|内网IP', '127.0.0.1', 'Mac OS X', 'Chrome 8');
COMMIT;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单URL',
  `perms` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '权限标识',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `order_num` bigint(20) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` int(11) DEFAULT '1' COMMENT '0：不可用，1：可用',
  `open` int(1) DEFAULT '1' COMMENT '0:不展开，1：展开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=394 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` VALUES (1, 0, '系统管理', '', NULL, 'el-icon-setting', '0', 1, '2020-03-07 17:41:30', '2021-04-11 09:38:12', 1, 1);
INSERT INTO `menu` VALUES (4, 1, '菜单权限', '/system/menus', 'menus', 'el-icon-help', '0', 3, '2020-03-07 18:57:42', '2020-12-15 17:25:02', 1, 0);
INSERT INTO `menu` VALUES (5, 0, '监控中心', '', NULL, 'el-icon-camera', '0', 6, '2020-03-07 18:58:18', '2020-12-15 19:34:38', 1, 1);
INSERT INTO `menu` VALUES (226, 1, '用户管理', '/system/users', 'users', 'el-icon-user', '0', 2, '2020-03-10 05:27:54', '2021-03-02 16:43:54', 1, 0);
INSERT INTO `menu` VALUES (230, 312, '入库记录', '/business/product/in-stocks', '', 'el-icon-date', '0', 2, '2020-03-10 05:34:28', '2021-05-27 16:54:29', 1, 0);
INSERT INTO `menu` VALUES (234, 226, '用户添加', '', 'user:add', 'el-icon-plus', '1', 1, '2020-03-10 05:50:44', '2021-03-02 16:44:00', 1, 0);
INSERT INTO `menu` VALUES (235, 1, '角色管理', '/system/roles', 'roles', 'el-icon-postcard', '0', 3, '2020-03-10 05:51:28', '2020-12-15 17:24:41', 1, 0);
INSERT INTO `menu` VALUES (239, 226, '用户删除', '', 'user:delete', 'el-icon-picture', '1', 1, '2020-03-10 06:05:30', '2020-03-10 08:10:19', 1, 0);
INSERT INTO `menu` VALUES (242, 235, '角色删除', '', 'role:delete', 'el-icon-s-marketing', '1', 3, '2020-03-10 06:15:29', '2020-03-11 11:43:36', 1, 0);
INSERT INTO `menu` VALUES (247, 4, '添加菜单', '', 'menu:add', 'el-icon-s-opportunity', '1', 1, '2020-03-10 07:55:10', '2020-04-27 09:59:43', 1, 0);
INSERT INTO `menu` VALUES (249, 4, '修改菜单', '', 'menu:update', 'el-icon-share', '1', 2, '2020-03-10 07:56:55', '2020-03-15 13:29:29', 1, 0);
INSERT INTO `menu` VALUES (250, 4, '删除菜单', '', 'menu:delete', 'el-icon-folder-opened', '1', 3, '2020-03-10 07:57:38', '2020-03-15 13:29:41', 1, 0);
INSERT INTO `menu` VALUES (251, 235, '分配权限', '', 'role:authority', 'el-icon-document-add', '1', 1, '2020-03-10 08:13:22', '2020-03-11 11:39:30', 1, 0);
INSERT INTO `menu` VALUES (254, 226, '分配角色', '', 'user:assign', 'el-icon-s-tools', '1', 3, '2020-03-11 01:32:29', '2020-04-27 10:58:30', 1, 0);
INSERT INTO `menu` VALUES (255, 235, '添加角色', '', 'role:add', 'el-icon-help', '1', 1, '2020-03-11 01:34:18', '2020-03-11 01:34:18', 1, 0);
INSERT INTO `menu` VALUES (256, 226, '禁用用户', '', 'user:status', 'el-icon-circle-close', '1', 1, '2020-03-11 06:50:04', '2020-03-14 05:05:49', 1, 0);
INSERT INTO `menu` VALUES (258, 226, '用户更新', '', 'user:update', 'el-icon-refresh', '1', 1, '2020-03-11 08:26:54', '2021-03-02 16:44:09', 1, 0);
INSERT INTO `menu` VALUES (259, 235, '角色更新', '', 'role:update', 'el-icon-refresh-left', '1', 1, '2020-03-11 11:45:20', '2020-03-11 11:45:20', 1, 0);
INSERT INTO `menu` VALUES (260, 235, '状态更新', '', 'role:status', 'el-icon-refresh', '1', 1, '2020-03-14 05:07:02', '2020-03-14 05:07:24', 1, 0);
INSERT INTO `menu` VALUES (261, 1, '部门管理', '/system/departments', '', 'el-icon-s-home', '0', 3, '2020-03-15 06:05:48', '2021-04-11 10:23:00', 1, 0);
INSERT INTO `menu` VALUES (262, 261, '添加部门', '', 'department:add', 'el-icon-plus', '1', 1, '2020-03-15 11:57:42', '2020-03-21 12:37:21', 1, 0);
INSERT INTO `menu` VALUES (263, 261, '编辑部门', '', 'department:edit', 'el-icon-edit', '1', 1, '2020-03-15 11:59:52', '2021-04-11 09:37:53', 1, 0);
INSERT INTO `menu` VALUES (264, 261, '更新部门', '', 'department:update', 'el-icon-refresh', '1', 1, '2020-03-15 12:02:34', '2021-04-11 09:38:24', 1, 0);
INSERT INTO `menu` VALUES (265, 261, '删除部门', NULL, 'department:delete', 'el-icon-delete', '1', 1, '2020-03-15 12:03:21', '2021-04-11 09:38:31', 1, 0);
INSERT INTO `menu` VALUES (267, 312, '物资资料', '/business/product/list', '', 'el-icon-goods', '0', 6, '2020-03-16 09:01:02', '2021-05-27 16:57:16', 1, 0);
INSERT INTO `menu` VALUES (268, 312, '物资类别', '/business/product/categories', '', 'el-icon-star-off', '0', 5, '2020-03-16 09:01:48', '2021-05-27 16:57:09', 1, 0);
INSERT INTO `menu` VALUES (269, 312, '物资来源', '/business/product/suppliers', '', 'el-icon-coordinate', '0', 7, '2020-03-16 12:35:10', '2021-05-27 16:58:09', 1, 0);
INSERT INTO `menu` VALUES (270, 312, '出库记录', '/business/product/out-stocks', '', 'el-icon-goods', '0', 4, '2020-03-16 13:55:49', '2021-05-27 16:54:57', 1, 0);
INSERT INTO `menu` VALUES (271, 5, '登入日志', '/system/login-log', 'login:log', 'el-icon-date', '0', 1, '2020-03-20 10:31:12', '2020-12-15 18:28:47', 1, 0);
INSERT INTO `menu` VALUES (273, 303, '全国疫情', '/healthy/yq-map', 'map:view', 'el-icon-s-opportunity', '0', 1, '2020-03-20 11:32:02', '2020-12-15 20:15:48', 1, 1);
INSERT INTO `menu` VALUES (274, 267, '添加物资', '', 'product:add', 'el-icon-s-opportunity', '1', 1, '2020-03-21 02:04:24', '2020-03-21 02:04:24', 1, 0);
INSERT INTO `menu` VALUES (277, 267, '更新物资', NULL, 'product:update', 'el-icon-folder', '1', 3, '2020-03-21 02:05:56', '2020-03-21 02:05:56', 1, 0);
INSERT INTO `menu` VALUES (279, 269, '删除来源', '', 'supplier:delete', 'el-icon-document-delete', '1', 1, '2020-03-21 02:17:29', '2020-03-21 12:32:22', 1, 0);
INSERT INTO `menu` VALUES (280, 269, '更新来源', '', 'supplier:update', 'el-icon-paperclip', '1', 1, '2020-03-21 02:18:34', '2020-03-21 12:36:41', 1, 0);
INSERT INTO `menu` VALUES (281, 269, '添加来源', NULL, 'supplier:add', 'el-icon-document-add', '1', 1, '2020-03-21 02:19:02', '2020-03-21 02:19:02', 1, 1);
INSERT INTO `menu` VALUES (283, 268, '添加类别', '', 'productCategory:add', ' el-icon-folder-add', '1', 1, '2020-03-21 02:26:12', '2020-03-21 02:44:22', 1, 0);
INSERT INTO `menu` VALUES (284, 268, '删除类别', NULL, 'productCategory:delete', 'el-icon-delete', '1', 1, '2020-03-21 02:27:05', '2020-03-21 02:28:49', 1, 0);
INSERT INTO `menu` VALUES (286, 268, '更新类别', NULL, 'productCategory:update', ' el-icon-coordinate', '1', 1, '2020-03-21 02:28:17', '2020-03-21 02:28:17', 1, 0);
INSERT INTO `menu` VALUES (296, 295, 'swagger文档', '/monitor/swagger-ui', NULL, 'el-icon-document', '0', 2, '2020-03-22 01:22:48', '2020-12-15 18:32:54', 1, 0);
INSERT INTO `menu` VALUES (298, 5, 'SQL监控', '/monitor/druid', NULL, 'el-icon-view', '0', 1, '2020-03-22 02:48:05', '2020-12-15 19:42:32', 1, 0);
INSERT INTO `menu` VALUES (299, 271, '删除日志', '', 'loginlog:delete', 'el-icon-delete', '1', 1, '2020-03-22 21:55:44', '2021-04-11 09:47:23', 1, 0);
INSERT INTO `menu` VALUES (300, 271, '批量删除', '', 'loginlog:batchDelete', 'el-icon-delete-solid', '1', 1, '2020-03-22 22:19:26', '2021-04-11 09:47:33', 1, 0);
INSERT INTO `menu` VALUES (303, 0, '健康报备', '', '', 'el-icon-platform-eleme', '0', 3, '2020-03-24 10:11:53', '2020-12-15 20:15:30', 1, 1);
INSERT INTO `menu` VALUES (304, 303, '健康打卡', '/healthy/point', '', 'el-icon-s-cooperation', '0', 1, '2020-03-24 10:12:57', '2020-12-15 20:19:14', 1, 0);
INSERT INTO `menu` VALUES (307, 5, '系统日志', '/monitor/logs', '', 'el-icon-edit', '0', 1, '2020-04-04 19:04:53', '2021-03-29 16:41:27', 1, 0);
INSERT INTO `menu` VALUES (308, 307, '删除日志', '', 'log:delete', 'el-icon-circle-close', '1', 1, '2020-04-04 19:59:20', '2020-04-04 19:59:20', 1, 1);
INSERT INTO `menu` VALUES (309, 307, '批量删除', NULL, 'log:batchDelete', 'el-icon-document-delete', '1', 2, '2020-04-04 19:59:59', '2020-04-04 19:59:59', 1, 0);
INSERT INTO `menu` VALUES (310, 312, '物资去处', '/business/product/consumers', '', 'el-icon-edit', '0', 8, '2020-04-05 10:08:21', '2021-05-27 16:58:15', 1, 0);
INSERT INTO `menu` VALUES (312, 0, '业务管理', NULL, NULL, 'el-icon-s-goods', '0', 2, '2020-04-05 10:19:07', '2020-08-19 17:57:27', 1, 1);
INSERT INTO `menu` VALUES (316, 312, '库存维护', '/business/product/stocks', '', 'el-icon-tickets', '0', 9, '2020-04-16 08:45:08', '2021-05-27 16:58:24', 1, 0);
INSERT INTO `menu` VALUES (317, 226, '导出表格', '', 'user:export', 'el-icon-edit', '1', 1, '2020-04-17 18:02:05', '2020-04-17 18:02:05', 1, 0);
INSERT INTO `menu` VALUES (318, 1, '图标管理', '/system/icons', '', 'el-icon-star-off', '0', 7, '2020-04-21 12:06:33', '2020-12-17 21:47:49', 1, 1);
INSERT INTO `menu` VALUES (321, 1, '文件管理', '/system/attachments', 'oss:upload', 'el-icon-picture-outline', '0', 2, '2020-04-25 10:52:17', '2021-04-11 16:11:57', 1, 0);
INSERT INTO `menu` VALUES (322, 310, '添加去处', '', 'consumer:add', 'el-icon-plus', '1', 2, '2020-04-27 16:57:04', '2020-04-27 16:58:21', 1, 1);
INSERT INTO `menu` VALUES (323, 310, '删除去处', NULL, 'consumer:delete', 'el-icon-delete', '1', 1, '2020-04-27 16:57:42', '2020-04-27 16:57:42', 1, 0);
INSERT INTO `menu` VALUES (324, 310, '编辑去处', '', 'consumer:edit', 'el-icon-edit', '1', 1, '2020-04-27 16:59:17', '2020-04-27 16:59:17', 1, 0);
INSERT INTO `menu` VALUES (325, 310, '更新去处', NULL, 'consumer:update', 'el-icon-star-off', '1', 1, '2020-04-27 17:00:18', '2020-04-27 17:00:18', 1, 1);
INSERT INTO `menu` VALUES (326, 230, '添加入库', '', 'inStock:add', 'el-icon-plus', '1', 3, '2020-04-27 17:07:04', '2021-04-11 21:02:46', 1, 1);
INSERT INTO `menu` VALUES (328, 230, '入库明细', NULL, 'inStock:detail', 'el-icon-zoom-in', '1', 2, '2020-04-27 17:08:25', '2020-04-27 17:08:25', 1, 0);
INSERT INTO `menu` VALUES (329, 4, '导出菜单', NULL, 'menu:export', 'el-icon-edit', '1', 1, '2020-04-27 17:26:40', '2020-04-27 17:26:40', 1, 0);
INSERT INTO `menu` VALUES (331, 267, '删除物资', NULL, 'product:delete', 'el-icon-delete', '1', 1, '2020-04-30 18:27:02', '2020-04-30 19:05:31', 1, 0);
INSERT INTO `menu` VALUES (332, 267, '回收物资', '', 'product:remove', 'el-icon-remove', '1', 1, '2020-04-30 18:56:48', '2020-04-30 18:56:48', 1, 1);
INSERT INTO `menu` VALUES (333, 267, '物资审核', NULL, 'product:publish', 'el-icon-edit', '1', 1, '2020-04-30 18:58:38', '2020-04-30 19:05:42', 1, 0);
INSERT INTO `menu` VALUES (336, 267, '物资还原', NULL, 'product:back', 'el-icon-top-left', '1', 1, '2020-04-30 19:06:22', '2020-04-30 19:06:22', 1, 0);
INSERT INTO `menu` VALUES (337, 230, '入库回收', '', 'inStock:remove', 'el-icon-remove', '1', 3, '2020-04-30 19:12:56', '2020-08-19 17:57:55', 1, 1);
INSERT INTO `menu` VALUES (338, 230, '入库审核', NULL, 'inStock:publish', 'el-icon-edit', '1', 2, '2020-04-30 19:13:32', '2020-08-19 17:57:32', 1, 0);
INSERT INTO `menu` VALUES (339, 230, '删除记录', NULL, 'inStock:delete', 'el-icon-delete', '1', 4, '2020-04-30 19:14:03', '2020-08-19 17:57:42', 1, 0);
INSERT INTO `menu` VALUES (340, 230, '入库还原', '', 'inStock:back', 'el-icon-d-arrow-left', '1', 3, '2020-04-30 19:17:27', '2020-08-19 17:57:49', 1, 0);
INSERT INTO `menu` VALUES (341, 295, '个人博客', '/blog', '', 'el-icon-view', '0', 1, '2020-05-07 19:34:31', '2020-05-07 19:34:31', 1, 0);
INSERT INTO `menu` VALUES (343, 304, '健康上报', '', 'health:report', 'el-icon-edit', '1', 1, '2020-05-14 20:21:09', '2020-05-14 20:21:09', 1, 0);
INSERT INTO `menu` VALUES (344, 5, '接口版本一', '/monitor/swagger-ui', '', 'el-icon-edit', '0', 1, '2020-12-15 18:35:18', '2021-03-29 16:10:53', 1, 1);
INSERT INTO `menu` VALUES (347, 5, '接口版本二', '/monitor/doc-ui', '', 'el-icon-edit', '0', 1, '2021-03-28 23:14:31', '2021-03-29 16:11:03', 1, 0);
INSERT INTO `menu` VALUES (348, 303, '打卡记录', '/healthy/points', '', 'el-icon-s-order', '0', 1, '2021-04-06 19:59:18', '2021-04-11 10:23:13', 1, 0);
INSERT INTO `menu` VALUES (349, 348, '打卡记录删除', '', 'health:delete', 'el-icon-delete', '1', 3, '2021-04-06 20:04:23', '2021-04-10 23:04:11', 1, 0);
INSERT INTO `menu` VALUES (351, 348, '打卡记录导出', '', 'health:export', 'el-icon-top', '1', 1, '2021-04-10 22:33:50', '2021-04-10 22:38:00', 1, 0);
INSERT INTO `menu` VALUES (352, 348, '打卡记录列表', '', 'health:query', 'el-icon-s-order', '1', 1, '2021-04-10 22:59:19', '2021-04-10 23:03:10', 1, 0);
INSERT INTO `menu` VALUES (353, 298, '获取监控密码', '', 'common:druid', 'el-icon-s-platform', '1', 1, '2021-04-11 09:35:29', '2021-04-11 09:35:29', 1, 1);
INSERT INTO `menu` VALUES (354, 310, '查询去处', '', 'consumer:query', 'el-icon-info', '1', 1, '2021-04-11 09:37:01', '2021-04-11 09:37:01', 1, 1);
INSERT INTO `menu` VALUES (355, 261, '查询部门信息', '', 'department:query', 'el-icon-info', '1', 1, '2021-04-11 09:38:58', '2021-04-11 09:38:58', 1, 0);
INSERT INTO `menu` VALUES (356, 261, '导出部门信息', '', 'department:export', 'el-icon-s-platform', '1', 1, '2021-04-11 09:40:04', '2021-04-11 09:40:04', 1, 0);
INSERT INTO `menu` VALUES (360, 230, '入库记录查询', '', 'inStock:query', 'el-icon-info', '1', 1, '2021-04-11 09:44:48', '2021-04-11 09:44:48', 1, 0);
INSERT INTO `menu` VALUES (361, 230, '入库记录导出', '', 'inStock:export', 'el-icon-s-platform', '1', 1, '2021-04-11 09:45:28', '2021-04-11 09:45:28', 1, 0);
INSERT INTO `menu` VALUES (362, 307, '操作记录列表', '', 'log:query', 'el-icon-info', '1', 1, '2021-04-11 09:46:38', '2021-04-11 10:33:04', 1, 1);
INSERT INTO `menu` VALUES (363, 271, '登录日志列表', '', 'loginlog:query', 'el-icon-info', '1', 1, '2021-04-11 09:48:12', '2021-04-11 09:48:12', 1, 0);
INSERT INTO `menu` VALUES (364, 4, '菜单按钮列表', '', 'menu:query', 'el-icon-info', '1', 1, '2021-04-11 09:53:13', '2021-04-11 09:53:13', 1, 0);
INSERT INTO `menu` VALUES (365, 321, '文件上传', '', 'oss:upload', 'el-icon-circle-plus-outline', '1', 1, '2021-04-11 09:57:15', '2021-04-11 09:57:15', 1, 0);
INSERT INTO `menu` VALUES (366, 321, '文件删除', '', 'oss:delete', 'el-icon-delete-solid', '1', 1, '2021-04-11 09:57:51', '2021-04-11 09:57:51', 1, 0);
INSERT INTO `menu` VALUES (367, 321, '文件列表', '', 'oss:query', 'el-icon-info', '1', 1, '2021-04-11 09:58:14', '2021-04-11 09:58:14', 1, 0);
INSERT INTO `menu` VALUES (368, 270, '提交发放单', '', 'outStock:add', 'el-icon-circle-plus-outline', '1', 1, '2021-04-11 10:00:02', '2021-04-11 10:00:03', 1, 0);
INSERT INTO `menu` VALUES (369, 270, '出库记录列表', '', 'outStock:query', 'el-icon-info', '1', 1, '2021-04-11 10:00:38', '2021-04-11 10:04:03', 1, 0);
INSERT INTO `menu` VALUES (370, 270, '移入回收站', '', 'outStock:remove', 'el-icon-delete-solid', '1', 1, '2021-04-11 10:01:03', '2021-04-11 10:01:03', 1, 0);
INSERT INTO `menu` VALUES (371, 270, '发放单明细', '', 'outStock:detail', 'el-icon-view', '1', 1, '2021-04-11 10:02:27', '2021-04-11 10:02:27', 1, 0);
INSERT INTO `menu` VALUES (372, 270, '删除发放单', '', 'outStock:delete', 'el-icon-delete-solid', '1', 1, '2021-04-11 10:04:40', '2021-04-11 10:04:40', 1, 0);
INSERT INTO `menu` VALUES (373, 270, '发放单审核', '', 'outStock:publish', 'el-icon-coordinate', '1', 1, '2021-04-11 10:05:45', '2021-04-11 10:05:45', 1, 0);
INSERT INTO `menu` VALUES (374, 270, '恢复发放单', '', 'outStock:back', 'el-icon-d-arrow-left', '1', 1, '2021-04-11 10:06:44', '2021-04-11 10:06:44', 1, 0);
INSERT INTO `menu` VALUES (375, 268, '物资类别列表', '', 'productCategory:query', 'el-icon-info', '1', 1, '2021-04-11 10:08:12', '2021-04-11 10:08:12', 1, 0);
INSERT INTO `menu` VALUES (376, 268, '分类树形结构', '', 'productCategory:queryTree', 'el-icon-share', '1', 1, '2021-04-11 10:09:08', '2021-04-11 10:09:08', 1, 0);
INSERT INTO `menu` VALUES (377, 268, '获取父级分类树', '', 'productCategory:queryParentTree', 'el-icon-s-marketing', '1', 1, '2021-04-11 10:10:05', '2021-04-11 10:10:05', 1, 0);
INSERT INTO `menu` VALUES (378, 267, '物资资料列表', '', 'product:query', 'el-icon-info', '1', 1, '2021-04-11 10:12:30', '2021-04-11 10:12:30', 1, 0);
INSERT INTO `menu` VALUES (379, 267, '获取库存列表', '', 'product:queryStocks', 'el-icon-info', '1', 1, '2021-04-11 10:13:26', '2021-04-11 10:13:26', 1, 0);
INSERT INTO `menu` VALUES (380, 267, '查询所有库存列表', '', 'product:queryAllStocks', 'el-icon-info', '1', 1, '2021-04-11 10:13:56', '2021-04-11 10:13:56', 1, 0);
INSERT INTO `menu` VALUES (381, 235, '导出列表', '', 'role:export', 'el-icon-s-platform', '1', 1, '2021-04-11 10:19:30', '2021-04-11 10:19:30', 1, 0);
INSERT INTO `menu` VALUES (382, 235, '获取所有角色列表', '', 'role:queryAll', 'el-icon-info', '1', 1, '2021-04-11 10:20:33', '2021-04-11 10:20:33', 1, 0);
INSERT INTO `menu` VALUES (383, 235, '角色所拥有的菜单列表', '', 'role:queryMenuList', 'el-icon-info', '1', 1, '2021-04-11 10:21:10', '2021-04-11 10:21:10', 1, 0);
INSERT INTO `menu` VALUES (384, 235, '分页查询角色信息', '', 'role:query', 'el-icon-info', '1', 1, '2021-04-11 10:22:01', '2021-04-11 10:22:01', 1, 0);
INSERT INTO `menu` VALUES (385, 269, '获取所有来源', '', 'supplier:queryAll', 'el-icon-s-order', '1', 1, '2021-04-11 10:23:59', '2021-04-11 10:23:59', 1, 0);
INSERT INTO `menu` VALUES (386, 269, '来源列表', '', 'supplier:query', 'el-icon-s-order', '1', 1, '2021-04-11 10:24:29', '2021-04-11 10:24:29', 1, 0);
INSERT INTO `menu` VALUES (387, 226, '获取单个用户', '', 'user:queryOne', 'el-icon-user-solid', '1', 1, '2021-04-11 10:28:32', '2021-04-11 10:28:32', 1, 0);
INSERT INTO `menu` VALUES (388, 226, '用户列表', '', 'user:query', 'el-icon-s-order', '1', 1, '2021-04-11 10:29:15', '2021-04-11 10:29:15', 1, 0);
INSERT INTO `menu` VALUES (389, 226, '重置用户密码', '', 'user:resetPwd', 'el-icon-refresh-right', '1', 1, '2021-04-11 10:30:50', '2021-04-11 10:30:50', 1, 0);
INSERT INTO `menu` VALUES (390, 226, '获取角色', '', 'user:queryRoles', 'el-icon-c-scale-to-original', '1', 1, '2021-04-11 10:31:51', '2021-04-11 10:31:51', 1, 0);
INSERT INTO `menu` VALUES (391, 261, '获取部门下拉信息', '', 'department:querySelect', 'el-icon-caret-bottom', '1', 1, '2021-04-11 17:55:01', '2021-04-11 17:55:20', 1, 0);
INSERT INTO `menu` VALUES (392, 312, '物资入库', '/business/product/add-stocks', '', 'el-icon-s-shop', '0', 1, '2021-04-12 20:36:59', '2021-05-27 16:54:20', 1, 0);
INSERT INTO `menu` VALUES (393, 312, '物资出库', '/business/product/publish', '', 'el-icon-s-promotion', '0', 3, '2021-04-12 20:38:28', '2021-05-27 16:55:30', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色标识码',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `forbidden` int(1) DEFAULT '0' COMMENT '是否可用,0:不可用，1：可用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_role_code_uindex` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (17, 'ADMIN', '超级管理员', '拥有系统最高权限', '2021-01-30 09:10:00', '2021-01-30 09:10:00', 1);
INSERT INTO `role` VALUES (19, 'SYSTEM_USER', '系统用户', '没有监控中心，角色，授权等操作', '2021-01-30 10:06:12', '2021-04-24 11:41:01', 1);
INSERT INTO `role` VALUES (32, 'TEST', '测试人员', '测试测试', '2021-05-30 07:54:59', '2021-05-30 07:54:59', 1);
COMMIT;

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  `menu_type` int(11) DEFAULT '0' COMMENT '菜单类型 0 菜单 1 按钮'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
BEGIN;
INSERT INTO `role_menu` VALUES (17, 1, 0);
INSERT INTO `role_menu` VALUES (17, 226, 0);
INSERT INTO `role_menu` VALUES (17, 234, 1);
INSERT INTO `role_menu` VALUES (17, 239, 1);
INSERT INTO `role_menu` VALUES (17, 256, 1);
INSERT INTO `role_menu` VALUES (17, 258, 1);
INSERT INTO `role_menu` VALUES (17, 317, 1);
INSERT INTO `role_menu` VALUES (17, 387, 1);
INSERT INTO `role_menu` VALUES (17, 388, 1);
INSERT INTO `role_menu` VALUES (17, 389, 1);
INSERT INTO `role_menu` VALUES (17, 390, 1);
INSERT INTO `role_menu` VALUES (17, 254, 1);
INSERT INTO `role_menu` VALUES (17, 321, 0);
INSERT INTO `role_menu` VALUES (17, 365, 1);
INSERT INTO `role_menu` VALUES (17, 366, 1);
INSERT INTO `role_menu` VALUES (17, 367, 1);
INSERT INTO `role_menu` VALUES (17, 4, 0);
INSERT INTO `role_menu` VALUES (17, 247, 1);
INSERT INTO `role_menu` VALUES (17, 329, 1);
INSERT INTO `role_menu` VALUES (17, 364, 1);
INSERT INTO `role_menu` VALUES (17, 249, 1);
INSERT INTO `role_menu` VALUES (17, 250, 1);
INSERT INTO `role_menu` VALUES (17, 235, 0);
INSERT INTO `role_menu` VALUES (17, 251, 1);
INSERT INTO `role_menu` VALUES (17, 255, 1);
INSERT INTO `role_menu` VALUES (17, 259, 1);
INSERT INTO `role_menu` VALUES (17, 260, 1);
INSERT INTO `role_menu` VALUES (17, 381, 1);
INSERT INTO `role_menu` VALUES (17, 382, 1);
INSERT INTO `role_menu` VALUES (17, 383, 1);
INSERT INTO `role_menu` VALUES (17, 384, 1);
INSERT INTO `role_menu` VALUES (17, 242, 1);
INSERT INTO `role_menu` VALUES (17, 261, 0);
INSERT INTO `role_menu` VALUES (17, 262, 1);
INSERT INTO `role_menu` VALUES (17, 263, 1);
INSERT INTO `role_menu` VALUES (17, 264, 1);
INSERT INTO `role_menu` VALUES (17, 265, 1);
INSERT INTO `role_menu` VALUES (17, 355, 1);
INSERT INTO `role_menu` VALUES (17, 356, 1);
INSERT INTO `role_menu` VALUES (17, 318, 0);
INSERT INTO `role_menu` VALUES (17, 312, 0);
INSERT INTO `role_menu` VALUES (17, 230, 0);
INSERT INTO `role_menu` VALUES (17, 360, 1);
INSERT INTO `role_menu` VALUES (17, 361, 1);
INSERT INTO `role_menu` VALUES (17, 328, 1);
INSERT INTO `role_menu` VALUES (17, 338, 1);
INSERT INTO `role_menu` VALUES (17, 326, 1);
INSERT INTO `role_menu` VALUES (17, 337, 1);
INSERT INTO `role_menu` VALUES (17, 340, 1);
INSERT INTO `role_menu` VALUES (17, 339, 1);
INSERT INTO `role_menu` VALUES (17, 310, 0);
INSERT INTO `role_menu` VALUES (17, 323, 1);
INSERT INTO `role_menu` VALUES (17, 324, 1);
INSERT INTO `role_menu` VALUES (17, 325, 1);
INSERT INTO `role_menu` VALUES (17, 354, 1);
INSERT INTO `role_menu` VALUES (17, 322, 1);
INSERT INTO `role_menu` VALUES (17, 392, 0);
INSERT INTO `role_menu` VALUES (17, 393, 0);
INSERT INTO `role_menu` VALUES (17, 267, 0);
INSERT INTO `role_menu` VALUES (17, 274, 1);
INSERT INTO `role_menu` VALUES (17, 331, 1);
INSERT INTO `role_menu` VALUES (17, 332, 1);
INSERT INTO `role_menu` VALUES (17, 333, 1);
INSERT INTO `role_menu` VALUES (17, 336, 1);
INSERT INTO `role_menu` VALUES (17, 378, 1);
INSERT INTO `role_menu` VALUES (17, 379, 1);
INSERT INTO `role_menu` VALUES (17, 380, 1);
INSERT INTO `role_menu` VALUES (17, 277, 1);
INSERT INTO `role_menu` VALUES (17, 268, 0);
INSERT INTO `role_menu` VALUES (17, 283, 1);
INSERT INTO `role_menu` VALUES (17, 284, 1);
INSERT INTO `role_menu` VALUES (17, 286, 1);
INSERT INTO `role_menu` VALUES (17, 375, 1);
INSERT INTO `role_menu` VALUES (17, 376, 1);
INSERT INTO `role_menu` VALUES (17, 377, 1);
INSERT INTO `role_menu` VALUES (17, 269, 0);
INSERT INTO `role_menu` VALUES (17, 279, 1);
INSERT INTO `role_menu` VALUES (17, 280, 1);
INSERT INTO `role_menu` VALUES (17, 281, 1);
INSERT INTO `role_menu` VALUES (17, 385, 1);
INSERT INTO `role_menu` VALUES (17, 386, 1);
INSERT INTO `role_menu` VALUES (17, 270, 0);
INSERT INTO `role_menu` VALUES (17, 368, 1);
INSERT INTO `role_menu` VALUES (17, 369, 1);
INSERT INTO `role_menu` VALUES (17, 370, 1);
INSERT INTO `role_menu` VALUES (17, 371, 1);
INSERT INTO `role_menu` VALUES (17, 372, 1);
INSERT INTO `role_menu` VALUES (17, 373, 1);
INSERT INTO `role_menu` VALUES (17, 374, 1);
INSERT INTO `role_menu` VALUES (17, 316, 0);
INSERT INTO `role_menu` VALUES (17, 303, 0);
INSERT INTO `role_menu` VALUES (17, 273, 0);
INSERT INTO `role_menu` VALUES (17, 304, 0);
INSERT INTO `role_menu` VALUES (17, 343, 1);
INSERT INTO `role_menu` VALUES (17, 348, 0);
INSERT INTO `role_menu` VALUES (17, 351, 1);
INSERT INTO `role_menu` VALUES (17, 352, 1);
INSERT INTO `role_menu` VALUES (17, 349, 1);
INSERT INTO `role_menu` VALUES (17, 5, 0);
INSERT INTO `role_menu` VALUES (17, 271, 0);
INSERT INTO `role_menu` VALUES (17, 299, 1);
INSERT INTO `role_menu` VALUES (17, 300, 1);
INSERT INTO `role_menu` VALUES (17, 363, 1);
INSERT INTO `role_menu` VALUES (17, 298, 0);
INSERT INTO `role_menu` VALUES (17, 353, 1);
INSERT INTO `role_menu` VALUES (17, 307, 0);
INSERT INTO `role_menu` VALUES (17, 308, 1);
INSERT INTO `role_menu` VALUES (17, 362, 1);
INSERT INTO `role_menu` VALUES (17, 309, 1);
INSERT INTO `role_menu` VALUES (17, 344, 0);
INSERT INTO `role_menu` VALUES (17, 347, 0);
INSERT INTO `role_menu` VALUES (31, 5, 0);
INSERT INTO `role_menu` VALUES (31, 307, 0);
INSERT INTO `role_menu` VALUES (31, 308, 1);
INSERT INTO `role_menu` VALUES (31, 362, 1);
INSERT INTO `role_menu` VALUES (31, 309, 1);
INSERT INTO `role_menu` VALUES (19, 387, 1);
INSERT INTO `role_menu` VALUES (19, 365, 1);
INSERT INTO `role_menu` VALUES (19, 367, 1);
INSERT INTO `role_menu` VALUES (19, 262, 1);
INSERT INTO `role_menu` VALUES (19, 263, 1);
INSERT INTO `role_menu` VALUES (19, 264, 1);
INSERT INTO `role_menu` VALUES (19, 265, 1);
INSERT INTO `role_menu` VALUES (19, 355, 1);
INSERT INTO `role_menu` VALUES (19, 356, 1);
INSERT INTO `role_menu` VALUES (19, 391, 1);
INSERT INTO `role_menu` VALUES (19, 312, 0);
INSERT INTO `role_menu` VALUES (19, 392, 0);
INSERT INTO `role_menu` VALUES (19, 230, 0);
INSERT INTO `role_menu` VALUES (19, 360, 1);
INSERT INTO `role_menu` VALUES (19, 361, 1);
INSERT INTO `role_menu` VALUES (19, 328, 1);
INSERT INTO `role_menu` VALUES (19, 338, 1);
INSERT INTO `role_menu` VALUES (19, 326, 1);
INSERT INTO `role_menu` VALUES (19, 337, 1);
INSERT INTO `role_menu` VALUES (19, 340, 1);
INSERT INTO `role_menu` VALUES (19, 339, 1);
INSERT INTO `role_menu` VALUES (19, 393, 0);
INSERT INTO `role_menu` VALUES (19, 270, 0);
INSERT INTO `role_menu` VALUES (19, 368, 1);
INSERT INTO `role_menu` VALUES (19, 369, 1);
INSERT INTO `role_menu` VALUES (19, 370, 1);
INSERT INTO `role_menu` VALUES (19, 371, 1);
INSERT INTO `role_menu` VALUES (19, 372, 1);
INSERT INTO `role_menu` VALUES (19, 373, 1);
INSERT INTO `role_menu` VALUES (19, 374, 1);
INSERT INTO `role_menu` VALUES (19, 268, 0);
INSERT INTO `role_menu` VALUES (19, 283, 1);
INSERT INTO `role_menu` VALUES (19, 284, 1);
INSERT INTO `role_menu` VALUES (19, 286, 1);
INSERT INTO `role_menu` VALUES (19, 375, 1);
INSERT INTO `role_menu` VALUES (19, 376, 1);
INSERT INTO `role_menu` VALUES (19, 377, 1);
INSERT INTO `role_menu` VALUES (19, 267, 0);
INSERT INTO `role_menu` VALUES (19, 274, 1);
INSERT INTO `role_menu` VALUES (19, 331, 1);
INSERT INTO `role_menu` VALUES (19, 332, 1);
INSERT INTO `role_menu` VALUES (19, 333, 1);
INSERT INTO `role_menu` VALUES (19, 336, 1);
INSERT INTO `role_menu` VALUES (19, 378, 1);
INSERT INTO `role_menu` VALUES (19, 379, 1);
INSERT INTO `role_menu` VALUES (19, 380, 1);
INSERT INTO `role_menu` VALUES (19, 277, 1);
INSERT INTO `role_menu` VALUES (19, 269, 0);
INSERT INTO `role_menu` VALUES (19, 279, 1);
INSERT INTO `role_menu` VALUES (19, 280, 1);
INSERT INTO `role_menu` VALUES (19, 281, 1);
INSERT INTO `role_menu` VALUES (19, 385, 1);
INSERT INTO `role_menu` VALUES (19, 386, 1);
INSERT INTO `role_menu` VALUES (19, 310, 0);
INSERT INTO `role_menu` VALUES (19, 323, 1);
INSERT INTO `role_menu` VALUES (19, 324, 1);
INSERT INTO `role_menu` VALUES (19, 325, 1);
INSERT INTO `role_menu` VALUES (19, 354, 1);
INSERT INTO `role_menu` VALUES (19, 322, 1);
INSERT INTO `role_menu` VALUES (19, 316, 0);
INSERT INTO `role_menu` VALUES (19, 303, 0);
INSERT INTO `role_menu` VALUES (19, 273, 0);
INSERT INTO `role_menu` VALUES (19, 304, 0);
INSERT INTO `role_menu` VALUES (19, 343, 1);
INSERT INTO `role_menu` VALUES (19, 348, 0);
INSERT INTO `role_menu` VALUES (19, 351, 1);
INSERT INTO `role_menu` VALUES (19, 352, 1);
INSERT INTO `role_menu` VALUES (32, 1, 0);
INSERT INTO `role_menu` VALUES (32, 226, 0);
INSERT INTO `role_menu` VALUES (32, 234, 1);
INSERT INTO `role_menu` VALUES (32, 239, 1);
INSERT INTO `role_menu` VALUES (32, 256, 1);
INSERT INTO `role_menu` VALUES (32, 258, 1);
INSERT INTO `role_menu` VALUES (32, 317, 1);
INSERT INTO `role_menu` VALUES (32, 387, 1);
INSERT INTO `role_menu` VALUES (32, 388, 1);
INSERT INTO `role_menu` VALUES (32, 389, 1);
INSERT INTO `role_menu` VALUES (32, 390, 1);
INSERT INTO `role_menu` VALUES (32, 254, 1);
INSERT INTO `role_menu` VALUES (32, 321, 0);
INSERT INTO `role_menu` VALUES (32, 365, 1);
INSERT INTO `role_menu` VALUES (32, 366, 1);
INSERT INTO `role_menu` VALUES (32, 367, 1);
INSERT INTO `role_menu` VALUES (32, 4, 0);
INSERT INTO `role_menu` VALUES (32, 247, 1);
INSERT INTO `role_menu` VALUES (32, 329, 1);
INSERT INTO `role_menu` VALUES (32, 364, 1);
INSERT INTO `role_menu` VALUES (32, 249, 1);
INSERT INTO `role_menu` VALUES (32, 250, 1);
INSERT INTO `role_menu` VALUES (32, 235, 0);
INSERT INTO `role_menu` VALUES (32, 251, 1);
INSERT INTO `role_menu` VALUES (32, 255, 1);
INSERT INTO `role_menu` VALUES (32, 259, 1);
INSERT INTO `role_menu` VALUES (32, 260, 1);
INSERT INTO `role_menu` VALUES (32, 381, 1);
INSERT INTO `role_menu` VALUES (32, 382, 1);
INSERT INTO `role_menu` VALUES (32, 383, 1);
INSERT INTO `role_menu` VALUES (32, 384, 1);
INSERT INTO `role_menu` VALUES (32, 242, 1);
INSERT INTO `role_menu` VALUES (32, 261, 0);
INSERT INTO `role_menu` VALUES (32, 262, 1);
INSERT INTO `role_menu` VALUES (32, 263, 1);
INSERT INTO `role_menu` VALUES (32, 264, 1);
INSERT INTO `role_menu` VALUES (32, 265, 1);
INSERT INTO `role_menu` VALUES (32, 355, 1);
INSERT INTO `role_menu` VALUES (32, 356, 1);
INSERT INTO `role_menu` VALUES (32, 391, 1);
INSERT INTO `role_menu` VALUES (32, 318, 0);
INSERT INTO `role_menu` VALUES (32, 5, 0);
INSERT INTO `role_menu` VALUES (32, 347, 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `avatar` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '头像',
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `forbidden` int(1) NOT NULL COMMENT '状态 0禁用 1启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '0:超级管理员，1：系统用户',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `birth` date DEFAULT NULL,
  `department_id` bigint(20) DEFAULT '1' COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_username` (`username`) USING BTREE COMMENT '用户名唯一'
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (108, 'admin', '小邱呀', '411882767@qq.com', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/d9c64c8085e643899d14636f9a47bd7b.jpeg', '15679718827', 1, '2021-03-05 00:40:17', '2021-04-12 23:05:18', 0, '1345fb30-b367-4af3-a69c-692a5c66', 0, '$2a$10$SMO7jWXkUgm2ylpeay.PX.MvGQX5R7Dtjqj0F6j2Ap3XmotpA/EEa', '1998-10-13', 3);
INSERT INTO `user` VALUES (109, 'user', '系统用户', '42288@qq.com', 'http://qkm-oss-test.oss-cn-shenzhen.aliyuncs.com/2021/04/12/e8d2a46eea2746998bc765e046bacf94.jpeg', '13423234545', 1, '2021-03-14 20:09:00', '2021-05-27 11:03:28', 0, '3ec15ff8-5311-446d-a59e-7a0f71da', 1, '$2a$10$436OcjjApU05KKQiFqwgG.3bP5hZqVTtP45Hf/gPjqjMDHeRa7xDS', '2021-03-15', 1);
INSERT INTO `user` VALUES (112, 'test', '测试人员', 'fortunate_qkm@163.com', '', '15646463535', 1, '2021-05-30 07:46:07', '2021-05-30 07:46:07', 2, 'db55d13c-c0e6-4a25-a889-49cb74a2', 1, '$2a$10$iMMUABYCyNire6Ru4YebseuWTjsTs7ucFgYpvhM91RVMmegD1KYqu', '2021-05-30', 2);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (108, 17);
INSERT INTO `user_role` VALUES (109, 19);
INSERT INTO `user_role` VALUES (108, 19);
INSERT INTO `user_role` VALUES (111, 19);
INSERT INTO `user_role` VALUES (111, 31);
INSERT INTO `user_role` VALUES (112, 32);
INSERT INTO `user_role` VALUES (113, 32);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
