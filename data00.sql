/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : data0

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 17/10/2024 02:57:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
    `uid` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `uname` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
    `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
    `usertype` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户类型',
    PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for t_attribute_application
-- ----------------------------
DROP TABLE IF EXISTS `t_attribute_application`;
CREATE TABLE `t_attribute_application`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性申请链上Id',
  `applicant` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请机构名',
  `attribute` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请属性',
  `status` int NOT NULL COMMENT '当前状态',
  `time` datetime NULL DEFAULT NULL COMMENT '状态变更时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_attribute_application
-- ----------------------------
INSERT INTO `t_attribute_application` VALUES (54, 'AttributeApplication00000001105', '属性授权中心', 'test:test department:center', 1, '2024-10-15 21:57:40');

-- ----------------------------
-- Table structure for t_data_asset
-- ----------------------------
DROP TABLE IF EXISTS `t_data_asset`;
CREATE TABLE `t_data_asset`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产链上id',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属机构名',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `location` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地区',
  `field` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用领域',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '访问链接（通过属性加密保护）',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `AESkey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '对称密钥（通过属性加密保护，encType为0时生效）',
  `encType` int NOT NULL COMMENT '数据资产加密模式：为0是对称加密，为1是属性加密',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_data_asset
-- ----------------------------
INSERT INTO `t_data_asset` VALUES (41, 'DataAsset00000001101', '新建 文本文档 (2).txt', '属性授权中心', 'department:center', '华南', '公共安全', 'QmeRX6UK9mQSfU2JH7diQnjkWi3tbY4XSmkYLqX3yea9Jo', '2024-10-15 21:54:38', 'AAAAIGzhpNNPC5azE7BvEjthE1PVIC0NetCgNY78thubpOjwAAACLQAAAIAQfGvfdXk+OAgSTQZvZCf+jAJ0H/KNhRxk6duFGX0Uso8Mi9INln7djqyvQg08rU/ZYhvCkRTzgkTQFT2rWcdhkGHiHtqvj3ROwCraA46S5qHjsZ5G947Hv7ofE5YAr6D6e1OWZYN/T9RPnIGiADvrSdFmvLKn1YFY9mVc8CMQGwAAAICH9jLaVzSh4sW2TUv2Ryn8dPwcdNwJVmacdmLz+iecX9jfaDkdMWbrrVwAlnXHtfu2E3Xjk+20MchfZLYjMORLgci1o0TlT6M29wwD48Bqt0JdULdW9fFjxyup8Y726Qpp0ivQ/l5QV5Dgdsf57pNxJPGy/PesbkeFAp0chWAAgwAAAAEAAAAAAAAAEWRlcGFydG1lbnQ6Y2VudGVyAAAAgHPUUwF+vAl7xBoaXyvrvmTPvBmyljlamPQgEpb94H17hX2OTYqn3UOgDqAK91lKdFruio4qtaOHQ0kHggOcCGlzeGV/p31z67pW5gQBJ3iddBraNyKwj+efD+CbKMgQltsn5sUhESrJ1WjVgikVC3KwHXna0GCIThqPnc2ThMn2AAAAgIiww5WeXvo9OFbV/LzJFfFw8vlpK6cMFqJ0gHI1py1XB3plq/f3jDqmu/cWKjIvKe1jF8U1sze31yUwiIVHz6sSITxxyqsDpRCX66hShImX9Jkrq7WIRNSEpJ+e+eHC3XcAGZOwxLp3dslHeJfrv/Dq2/4SbH0soEr6v9JQfTT6', 0);
INSERT INTO `t_data_asset` VALUES (42, 'DataAsset00000001103', '新建 文本文档.txt', '属性授权中心', 'asset:file100 asset:video001 1of2', '华东', '服务业', 'QmUvHv23Hyyu9BSmwTQESbz1H8CsD6iC1B6QmW66VnCZQ2', '2024-10-15 21:56:33', 'AAAAIHfIvDj/1hRlhY1B5cFfpNA7UOVbMfxMrEplortvxHO3AAADUwAAAICYV0mpzaL5GEUnLCN3URHigaSbfJ+KH8nCpG/59hc6JvJqve6LwPVyskgTmDw3CNwuGGAdHH+7fsRiUK25jzmkEBBQ1Cn8z1D6qoxxLvQMlsosBaNOpt6rxNC2VFaaT5fyyYXl5LR9FM2883aKg67frfBmq1aMQLF384UOMFcUGgAAAIBZQYgeFS3luu1PVplHpUJH45VspC4Yhs+BJzXPU5LdvyY16PEbGsD9TgWTIEhPrY5nb+MZoFNaKp2wUWCthEFSIaSHV3/V1WjFuftL0Kq/OYAXQg5ymKOc1TN8z1i42e3antYkQ054iO+q9ncx2Mre8xFISPMWx/GtHSOIyMrAqQAAAAEAAAACAAAAAQAAAAAAAAANYXNzZXQ6ZmlsZTEwMAAAAIAm9BHkk9F4J6oaRzWRj7V6gRnnUgcnSe9pMgnWRV+zHZXjPLcbrvapajFDWmjLgs38mLivU5GDsVZlKnk+2ZPCWuRbBfZhR2eDeS7CuLi8hKXY+/XyPPaf2ymaqyr9gQBgKvU4ZAr8wDLMljFFaismfCk1887VJCdA9kQEkt6dpwAAAIA8AsFsvTV7pIwBcP3E5jjOVk7fvfmwTNUlMrbv0GlQXuAP2UulIJwN8sEIaur63vH92qMENS0EHSnve1boKTGRVa8CGyqcjji0jxL6VxWmI3DcZHbS8enu6UvKtwY4bsubymPMBQAIPuyi8Al02bD3jlTEMqeSjCsGMlZcQyx/SgAAAAEAAAAAAAAADmFzc2V0OnZpZGVvMDAxAAAAgCb0EeST0XgnqhpHNZGPtXqBGedSBydJ72kyCdZFX7MdleM8txuu9qlqMUNaaMuCzfyYuK9TkYOxVmUqeT7Zk8Ja5FsF9mFHZ4N5LsK4uLyEpdj79fI89p/bKZqrKv2BAGAq9ThkCvzAMsyWMUVqKyZ8KTXzztUkJ0D2RASS3p2nAAAAgAtGjGvYju9lD4+IYFE+o9zHUrcoFbBC4CB/4At/eEv7gEUyBuBsrbegzSL2EUXTrMqMq4YN4w6meHgN8plMIm9B8LuPKOdTljBCZwt0aEom9La9looG0yLZZequQ0Q8dhwi3Py4RcpZUKGWTqzB7nU62VDiPqD/8BxiRYDKg9up', 0);
INSERT INTO `t_data_asset` VALUES (43, 'VideoAsset00000001210', '1', '属性授权中心', 'test:test department:center 1of2', '华南', '农业', 'vdTfqvouP3Bk0Fsd11/h8w==', '2024-10-16 22:11:36', 'AAAAIDhueuJAfBq/EMZumOmfHojDPVIxf+fSEg/DpYnJNLysAAADUgAAAIAdQ2C4Lz1Ti/vrbve/UBynmFMwMcAEklnnqsDXp1jSvGX8CKFyrpz5CQLae3NFhcys+j3/J7UyUV7+fcf6b+ahdzGXWf9PR60qBIvPLsdkGl/AJxaacpzwrpoKibzT4LpOzMwTV2YF45na4ivipnjPCOuS4CvP690h/ECDZ5T2EQAAAIA2LHb3JeCdGOveQYg9KBKePZOsRNVpvoze3ERYhXllKRD8jiURma90skS97UJ9U00iTFxrds2tBcBB7s7zGKhmiWYZFixtZu7NfeGzi7PxY+Hy1kskxBg8vuUlBAHcmt+BUaYJOKQ5gMN6oNH2TIM+WyQScQDvpTD3exlpA3TVBAAAAAEAAAACAAAAAQAAAAAAAAAJdGVzdDp0ZXN0AAAAgJQZlcEPSouHoi2QZ+8ijQdzVoJUUBKJZqwDNTWKyNv2V34/fR2VSM5cmvTEPDGmytVEr0ARliOMaRFcb16u8sCl1EfxL4j/ceB0gsNwSt9AjlWcqFQSmwfWh9GFGew6hXrSjEcaQeSaMjN6gU8Dby8z1AeydMrJ9x7RS4ijXHqLAAAAgAQoL9bjn6uljM0XRiXn66NyYZjigTu7tg5Wosw6dTnfuVN3vC5NJSw4jdX/IP5d0brLrpsvXKfav5khfgiwWvw11c2dxc2T8CaOaFmyk9W2TUDCDFl6oZwLPOZ9W9lP7G63ckPvN1/xRjSeBtg8TaexlpuF7e4hCMxXKU0mED/xAAAAAQAAAAAAAAARZGVwYXJ0bWVudDpjZW50ZXIAAACAlBmVwQ9Ki4eiLZBn7yKNB3NWglRQEolmrAM1NYrI2/ZXfj99HZVIzlya9MQ8MabK1USvQBGWI4xpEVxvXq7ywKXUR/EviP9x4HSCw3BK30COVZyoVBKbB9aH0YUZ7DqFetKMRxpB5JoyM3qBTwNvLzPUB7J0ysn3HtFLiKNceosAAACAgRSMS5zWyz1SbSpweh88MYpzbItCJEZdPO3n7SYJFDwqjD83rrvbEMrZP1MhChiWAViSpQRcoVDZc3X+51unTlRg6l1fAoolh46KDgziua5d7YE6WT5sJB+4dYPoYwgLCLYZa1io29xpEKQtN+uMiIJ/i10w7/ImIyRLmXJLrHg=', 0);
INSERT INTO `t_data_asset` VALUES (44, 'DataAsset00000001261', 'ipfs.txt', '属性授权中心', 'test:test department:center 1of2', '东北', '政务界', 'QmYPfstHHSSAMEtjcJCMLw2kpUd2oi5ZvGpNjvEdeXaZLr', '2024-10-17 02:34:18', 'AAAAILzKQUF1xu/6zxk25KrnEtroUSfmi02MrB1KmQrP4H/VAAADUgAAAIBcDSysX7T6QQavQ7q2dMDOoKENf+ZqYem71Rhul3L5b8C7n9H8WfNxIY2HyZLGRS8aT5UHgXH4MvOOULN9vw3gP9R3baBRxtINfqp3oKsxnah3jNb373rYQV4JF7/FxLRxKDdZ4dLDACuE6Ql5WvkmlcPDUzDRRCZep1n85114MQAAAIB8G0KcD7CNk+5roFk3mUcqG6Y5j6cf9xW5AzEe+23Z3V3Ckdmii81dXq4cTpy1pXMbL5B3BagUdPOdDlo5pwTRPzNQb43OaHfRUIRhaz49fmKNPT/txLJ4j7mLzvQT0P8m0u77vl5EZPLXFoPUSsO5cHGdXLhRtPAEDDLf2mVhXAAAAAEAAAACAAAAAQAAAAAAAAAJdGVzdDp0ZXN0AAAAgG0TLqoJaRmh6oidUxxmrLugojdxS29TLWaNJLPMtg30OpP1ADgPVKHENIMqNkY907RpihAf3FopuSHymUbCS16NP0SfGKuXWbhizCxBx/jc9odSyeEHKhKXyr4bZq7E6YUieUAVtORr+62YQjwYy63f+aKYcsBAGtcJ/8mYtCZQAAAAgGyXd+sLBRA+CAQjzqlHhmwEI7lZjs273L71Y3IeXuLtCP1USWxppAW0yC/vrnjO0WjP5TeWVXc2RsSkmmZShYEQwQSmPnHIvRVxjxgnkvc80BIF/g9ti56/O+witqptXIFCoSVRSkobyE7Xue2XafU2BF0zm5GWZA05C6KN3JGOAAAAAQAAAAAAAAARZGVwYXJ0bWVudDpjZW50ZXIAAACAbRMuqglpGaHqiJ1THGasu6CiN3FLb1MtZo0ks8y2DfQ6k/UAOA9UocQ0gyo2Rj3TtGmKEB/cWim5IfKZRsJLXo0/RJ8Yq5dZuGLMLEHH+Nz2h1LJ4QcqEpfKvhtmrsTphSJ5QBW05Gv7rZhCPBjLrd/5ophywEAa1wn/yZi0JlAAAACAB0SwGelH6nJSpznuQ5B/zGW4/nCjvr+p4pjDSFrnq/bLRy6bJNhkxG1BNxnJA/hTGQYrU0fNOEQn6/ifoZa3oFKk0zCWHr6wkDr0RShdmC+QkQ1XUTeh2crEc5oBKNKr/HGE7cAG5uxeFKcMUxC9D6QtK/YZy+eAVFTsf4bkbRA=', 0);

-- ----------------------------
-- Table structure for t_data_asset_order
-- ----------------------------
DROP TABLE IF EXISTS `t_data_asset_order`;
CREATE TABLE `t_data_asset_order`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产订单链上Id',
  `applicant` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请平台名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权属平台名',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名称',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `status` int NOT NULL COMMENT '当前状态',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_data_asset_order
-- ----------------------------
INSERT INTO `t_data_asset_order` VALUES (58, 'DataAssetOrder00000001102', '属性授权中心', '属性授权中心', '新建 文本文档 (2).txt', 'department:center', 5, '2024-10-15 21:55:56');
INSERT INTO `t_data_asset_order` VALUES (59, 'DataAssetOrder00000001104', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 5, '2024-10-15 21:56:41');
INSERT INTO `t_data_asset_order` VALUES (60, 'DataAssetOrder00000001106', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 6, '2024-10-15 21:58:04');
INSERT INTO `t_data_asset_order` VALUES (61, 'DataAssetOrder00000001107', '属性授权中心', '属性授权中心', '新建 文本文档 (2).txt', 'department:center', 5, '2024-10-15 21:58:56');
INSERT INTO `t_data_asset_order` VALUES (62, 'DataAssetOrder00000001211', '属性授权中心', '属性授权中心', '1', 'test:test department:center 1of2', 6, '2024-10-16 22:12:06');
INSERT INTO `t_data_asset_order` VALUES (63, 'DataAssetOrder00000001225', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 6, '2024-10-17 00:57:22');
INSERT INTO `t_data_asset_order` VALUES (64, 'DataAssetOrder00000001233', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 6, '2024-10-17 01:16:07');
INSERT INTO `t_data_asset_order` VALUES (65, 'DataAssetOrder00000001265', '属性授权中心', '属性授权中心', 'ipfs.txt', 'test:test department:center 1of2', 5, '2024-10-17 02:38:40');

-- ----------------------------
-- Table structure for t_data_asset_orderer_history
-- ----------------------------
DROP TABLE IF EXISTS `t_data_asset_orderer_history`;
CREATE TABLE `t_data_asset_orderer_history`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产订单链上Id',
  `applicant` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请平台名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权属平台名',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名称',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `status` int NOT NULL COMMENT '变更后的状态',
  `time` datetime NOT NULL COMMENT '变更时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_data_asset_orderer_history
-- ----------------------------
INSERT INTO `t_data_asset_orderer_history` VALUES (58, 'DataAssetOrder00000001102', '属性授权中心', '属性授权中心', '新建 文本文档 (2).txt', 'department:center', 5, '2024-10-15 21:55:56');
INSERT INTO `t_data_asset_orderer_history` VALUES (59, 'DataAssetOrder00000001104', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 5, '2024-10-15 21:56:41');
INSERT INTO `t_data_asset_orderer_history` VALUES (60, 'DataAssetOrder00000001106', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 6, '2024-10-15 21:58:04');
INSERT INTO `t_data_asset_orderer_history` VALUES (61, 'DataAssetOrder00000001107', '属性授权中心', '属性授权中心', '新建 文本文档 (2).txt', 'department:center', 5, '2024-10-15 21:58:56');
INSERT INTO `t_data_asset_orderer_history` VALUES (62, 'DataAssetOrder00000001211', '属性授权中心', '属性授权中心', '1', 'test:test department:center 1of2', 6, '2024-10-16 22:12:06');
INSERT INTO `t_data_asset_orderer_history` VALUES (63, 'DataAssetOrder00000001225', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 6, '2024-10-17 00:57:22');
INSERT INTO `t_data_asset_orderer_history` VALUES (64, 'DataAssetOrder00000001233', '属性授权中心', '属性授权中心', '新建 文本文档.txt', 'asset:file100 asset:video001 1of2', 6, '2024-10-17 01:16:07');
INSERT INTO `t_data_asset_orderer_history` VALUES (65, 'DataAssetOrder00000001265', '属性授权中心', '属性授权中心', 'ipfs.txt', 'test:test department:center 1of2', 5, '2024-10-17 02:38:40');

-- ----------------------------
-- Table structure for t_db_asset
-- ----------------------------
DROP TABLE IF EXISTS `t_db_asset`;
CREATE TABLE `t_db_asset`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产链上id',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属机构名',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `location` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地区',
  `field` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用领域',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据库访问链接（通过属性加密保护）',
  `username` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据库用户名（通过属性加密保护）',
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据库密码（通过属性加密保护）',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `AESkey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '对称密钥',
  `encType` int NOT NULL COMMENT '数据资产加密模式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_db_asset
-- ----------------------------
INSERT INTO `t_db_asset` VALUES (1, 'DBAsset00000001209', '数据库', '属性授权中心', 'test:test department:center 1of2', '华南', '工业', 'tdUDitq8IPllHbMYffcM8A==', 'tZaq+LVqzASioYLxIxLRqw==', 'swNRQje8+dc2yXtI4k73JA==', '2024-10-16 21:57:49', 'AAAAILAlLW5znG1CYNfxOacantW1dxruJ7nLTsGrlGjsfUeYAAADUgAAAIA+eEMHySKtaim3j+U4Q6NFPxj2B+ff67LAabDtBDJohEr3LGpNRlHS9xt06FBsO59/x9hJfVSBwwCEZPF9XLhxFOkhSjzWDRU6tlvSgS63zr1Zd90Gu0jdnv/bQLSu5HSfBm636leiyadYxAOyNl7p/aH5K2N9SyrVJPYiEwMudwAAAICH2pZQAGvr8RyhbuYmUAAoiCQqpuHy4ZJgHnp+tzaJQlQJF4ttX1TTO8esJ+bTPXBzQP5jJ9NRX5PjP3RQ+K7Ql8rUn/n5Y479YqJLTx9wHlXr7dk7bDAMsX7MU+fKYeat6YYB/677TF5h90c0tYf5D3P5q5Ln5HMLSV1Da4r1wAAAAAEAAAACAAAAAQAAAAAAAAAJdGVzdDp0ZXN0AAAAgGsYIKFw5mFgIQqSMeDUbVWv7cxQeQfjgEsG5mbIUMBrvAcCf5ptLSJeSrmInNDWxK+czZHPHjn0FNSDvbvCOA97iBB1OmhivbwhWevIEp55eRE4CDli5HCD+QxCrZkQ1qHYUtJqzRrXd2+VlYTAwPrb+AGCIgcO6/j3mCpgez2YAAAAgJEx1NkL+irESFOAfqKF5MltMoxuSdzreiZWHQ5HS7N8hoYk/rI1gwYYKY8AwNl8T/hyfNHCjLpWff6N4Ge5AOMHq3YQyzRqhiRYV52Y5KQxynICJQPpWOu1wY6hdKQI6zRBS53FST9MOBIF3bimpk0GvdjaNT3WTHqGAXzEDGekAAAAAQAAAAAAAAARZGVwYXJ0bWVudDpjZW50ZXIAAACAaxggoXDmYWAhCpIx4NRtVa/tzFB5B+OASwbmZshQwGu8BwJ/mm0tIl5KuYic0NbEr5zNkc8eOfQU1IO9u8I4D3uIEHU6aGK9vCFZ68gSnnl5ETgIOWLkcIP5DEKtmRDWodhS0mrNGtd3b5WVhMDA+tv4AYIiBw7r+PeYKmB7PZgAAACANGpF9VPqmjfNC5SqsuI6tLMDy43S2Ii0aGXqr3zevKMX6HLjLQlPYFqdQ9SK5XFc9oqkNsqC/B0mW6nkshxDdjJEJOxyDBwAZ+MYBgMz8RejgQdhheWUlvE/g2dmsYHENXFBM2vUCYu2iJGE73pSVSSP+70UDnk36NfAzAgIGBA=', 0);
INSERT INTO `t_db_asset` VALUES (2, 'DBAsset00000001220', 'data0', '属性授权中心', 'test:test department:center department:policeStation 1of3', '华北', '农业', 'WlalR8jtatPj+3aiMgYFFzTJQmOtXtFM9IpdjB5IX8I9ZCjIarpLjHfyPeOSBIEY8a8kOlE+XYmf7YO3w6qiXM/gVR9dwSFluI5r9A87QpcvDoJINbHekcPvA19MC3mY', 'FBapmXUOHjm3hSUxFeQNmQ==', 'Tiji4DyDgOizFc6ZTtRmKA==', '2024-10-17 00:30:41', 'AAAAIASMJeQFCQfHQNnESUWQG31bhzYQlR9iTdKWSWizEfQXAAAEfgAAAIALEyH4GqGtg21KdpegLX2fyZZgVdS/0L5qVcPeGWnAEfyJuKhy5dYkI+mib4fNEb9tq6/ob9WUZQ0//ISbtXb+BbEc29PGXWulyUHNayVcdZhZR1WvofVmBvpf59bmovEFoLhT37LBfrJgakkzivr4lNYpp34SmLI5KlS5U/erxgAAAIAMl1Ry9WX/Jbjbun5BPBfAbo98qGy8wRZbuUuHjBkDRqjW6cz3n8ABsENPpI7EvevcwNgWTTBIGMOguoXF6A2hVhSQsugWkj/IQUbETRL6hujRtavJ7s/nNevQOIIVN40LAPEraL0VRVAwp/1nyurjSq5V6U3kubeJdV/klQOCuQAAAAEAAAADAAAAAQAAAAAAAAAJdGVzdDp0ZXN0AAAAgKWPE5dVjOJGiC8NOcpdCaBftxNfzbWib8EMpQxAhqMromPcmPXalAPbRVIeaL/RrKRAINiFQM85m/QMJdCw5vAdrlAQRZz35LQ/8aKUm8ZVO3zb7NRrcmh84MWFEoiO/02tBILQ+2dXyF0lETiffjyuqXPsfwzI9/cK8sljd97TAAAAgFMIE5h+BCfN7ZNQN9JM79DaMN7xHP7jOBVGyP3uDj/ySaWxGytOfVT4EeACRF2tDawthOoCAJ+NHwGj4/KdaClNoyGWK7eyEB1WVkqy0hRMB6QNl/D0ePofg4cOEmgx/3pqKJ0v6GaSra2JQq4kHqIEvUWaXDewd3uwhnJTn6zMAAAAAQAAAAAAAAARZGVwYXJ0bWVudDpjZW50ZXIAAACApY8Tl1WM4kaILw05yl0JoF+3E1/NtaJvwQylDECGoyuiY9yY9dqUA9tFUh5ov9GspEAg2IVAzzmb9Awl0LDm8B2uUBBFnPfktD/xopSbxlU7fNvs1GtyaHzgxYUSiI7/Ta0EgtD7Z1fIXSUROJ9+PK6pc+x/DMj39wryyWN33tMAAACAH1TY6qSUWc2t8A/uXspbSOhOIJM2obQ6vIC/UGVani4QlWJ9oOULl9Vz21Ykvs52/XFiF9JjU/bnHM2hucxI5iqhPKOGICjMXHxGXGNbEt+/y+9bQ2nYE5ai5sPRAcp36HppMYd92DQKWscSnkV6X7NzNEBJA7dKuvyz4fO9yzAAAAABAAAAAAAAABhkZXBhcnRtZW50OnBvbGljZVN0YXRpb24AAACApY8Tl1WM4kaILw05yl0JoF+3E1/NtaJvwQylDECGoyuiY9yY9dqUA9tFUh5ov9GspEAg2IVAzzmb9Awl0LDm8B2uUBBFnPfktD/xopSbxlU7fNvs1GtyaHzgxYUSiI7/Ta0EgtD7Z1fIXSUROJ9+PK6pc+x/DMj39wryyWN33tMAAACAaU68yMubAG0fKYcAt5z6YTcwMZXz6QpXGGRwn1vvBvOtMMLHtasxE7PyuXKuqvmsylFHfe9i1UoUSYN0azUOmhk5/7xoqFKWk3gDOXtZ9eNjh48SyK4+m/jkwSLNOvH3F056VZSyVAPPU4utD+N8FkGVzG7nTY5wSHJbMqty6cU=', 0);
INSERT INTO `t_db_asset` VALUES (3, 'DBAsset00000001221', '12', '属性授权中心', 'department:hospital', '华南', '城市建设', '06uemqK5z2Tx/++uCR5iUA==', '06uemqK5z2Tx/++uCR5iUA==', '06uemqK5z2Tx/++uCR5iUA==', '2024-10-17 00:33:03', 'AAAAILVl/nOUx9Ci8Q2Bi/lYQ5A0rlc/CeKuIDKnKLOaNV/AAAACLwAAAIAembkcvUImyw9AQd/ZA7x/s+85Gn338uY5HjIWlW0LxGg39vpr2HjhKpe10T68Zku3Fhzu2at9z2c6bIRznHqcCQTrv+YnkdLfvuUDq5euhKeF6dkspYh0fuEoNV2S8prIbcqkOdkf1eGNnPiiVmDeWD3k9s9UAUc/8cWPGt+20QAAAIAptXo5Wr7gMcXV3ZZuFSZfOa9s0EByHAqW/8hr35mjvHXsREU1x28Yewe3gmEbWmdvgfcctbSTCoKZPjH8EvoqSrnJts+copP/W6PiUsI3Q2z1m1xC+r/MnFZ0Ki5sbPLXB15i9to+zD/FyRTNe56pVcLzp+tvwfN4J5C3odFQ+gAAAAEAAAAAAAAAE2RlcGFydG1lbnQ6aG9zcGl0YWwAAACAOpBBDYFXK6OsHftiNswesgp0Vv/RSejrPMLTyC6kgk14BeZpYwSoySuUQwUeYGgl3++H6h9veXAaax5SMt9Cw07R0Fwee9bju/z/nolir6/63uCtjrPBBvS/n947ThEW0oYuTwlh2VNq1i4/ZdIGBXrAEwqtjTgFboq/VPgX4AgAAACAN5v9cQWMXdp5ZHqw+sIbaoASeRgpyhhaX4xjlSjiFPxmTK3O/dbHSFNUMlQGUVtGpmXklo81PfRfY0HnmOaqjAjaExMcSedAHdqLmAoEZaknC+4TMjE3lvsg6MvEansLbFEWg3WhDopJRvXdfo8QIgwmKL292P2PwIEb4LzEkrA=', 0);
INSERT INTO `t_db_asset` VALUES (4, 'DBAsset00000001262', 'aaa', '属性授权中心', 'department:center', '华北', '工业', '5/VyFGRpUscJA5FlmnIy7g==', '5/VyFGRpUscJA5FlmnIy7g==', '5/VyFGRpUscJA5FlmnIy7g==', '2024-10-17 02:34:50', 'AAAAILrGF3R1ltwI7ls0NOeH9et8ev++vAFfm9i5rOop8RzKAAACLQAAAICl3KjkYiCtneOVQglsq1/uRT4GJxVXWcsoIHKZb7uQ5/PmZdfhMwGYgyTVm71/OUOsoZ7Qjzf995czdd7eJxulbOO3kh0Q0Y6N5NM9nVzt2qxo5ien9FzDkpxJrT5CUtoP8mupSl57RYgcH7OBshekHhtEIWufO1rk3ycY/n3BhQAAAIBmpsWchtITjlfq2ZgGj/P+vik4F6JS2uuZAMXmE9V7vmSeIMm6xi+fFlY2GeZ5WcvmLu2q6HgvFV3XQVRsMDeFCbbvKC6C+QhEaPu88OQyCrBmGy82/zcb65u9ZeY53SmOLWejNRDu/IAKJ/atbtwC4BueZ4H6kgAnVgylE6IO8wAAAAEAAAAAAAAAEWRlcGFydG1lbnQ6Y2VudGVyAAAAgEK8rkZyo3m+cjMEFOqYs70wNBg0EzGCGqcWu+y431LqlOVe6OtdPr7QrE9Utfwho2Uh4FAbeWVgycCxpV1EJjpN2iCx3Sugfto4jsb0TaoZzVS7kFjmfAj1AUegsYUaUOqpoM6MK1/RPDsG4pjlrSVOrukmstI7CTP6wVgzp6AqAAAAgBVs4AyP2GUHW22CM4ZhDPIaU+qW4Ktl4Z8PwDdeOsuNGJBBcjgXHV1tCKz8lYODdl2Z8UxbAioKPfqfuylSw7Ccl4GVUU24uouQs7e3BHZptvZZC6cZZPXmSK+nMEbaE8LPddXnG4onVw5TR8JWjiB/yzOkHBIUMNry6AX81l9R', 0);

-- ----------------------------
-- Table structure for t_db_asset_order
-- ----------------------------
DROP TABLE IF EXISTS `t_db_asset_order`;
CREATE TABLE `t_db_asset_order`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产订单链上Id',
  `applicant` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请平台名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权属平台名',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名称',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `sql` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '数据库语句',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_db_asset_order
-- ----------------------------
INSERT INTO `t_db_asset_order` VALUES (1, 'DBAssetOrder00000001223', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', NULL, '2024-10-17 00:51:53');
INSERT INTO `t_db_asset_order` VALUES (2, 'DBAssetOrder00000001224', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', NULL, '2024-10-17 00:52:48');
INSERT INTO `t_db_asset_order` VALUES (3, 'DBAssetOrder00000001226', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'show databases;', '2024-10-17 00:57:54');
INSERT INTO `t_db_asset_order` VALUES (4, 'DBAssetOrder00000001227', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'show databases;', '2024-10-17 01:01:44');
INSERT INTO `t_db_asset_order` VALUES (5, 'DBAssetOrder00000001228', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'show databases', '2024-10-17 01:09:33');
INSERT INTO `t_db_asset_order` VALUES (6, 'DBAssetOrder00000001229', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'show databases', '2024-10-17 01:10:46');
INSERT INTO `t_db_asset_order` VALUES (7, 'DBAssetOrder00000001230', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'show databases', '2024-10-17 01:12:34');
INSERT INTO `t_db_asset_order` VALUES (8, 'DBAssetOrder00000001231', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'select * from data.t_data_asset', '2024-10-17 01:13:13');
INSERT INTO `t_db_asset_order` VALUES (9, 'DBAssetOrder00000001232', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'select * from data.t_data_asset2', '2024-10-17 01:13:49');
INSERT INTO `t_db_asset_order` VALUES (10, 'DBAssetOrder00000001234', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'select * from t_data_asset', '2024-10-17 01:16:41');
INSERT INTO `t_db_asset_order` VALUES (11, 'DBAssetOrder00000001235', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'select * from data.t_data_asset', '2024-10-17 01:18:08');
INSERT INTO `t_db_asset_order` VALUES (12, 'DBAssetOrder00000001236', '属性授权中心', '属性授权中心', 'data0', 'test:test department:center department:policeStation 1of3', 'select * from data.t_data_asset2', '2024-10-17 01:18:15');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构链上id',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构名称',
  `attribute` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '属性',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (25, 'Department1', '属性授权中心', 'test:test department:center');
INSERT INTO `t_department` VALUES (26, 'Department2', '公安局', 'department:policeStation');
INSERT INTO `t_department` VALUES (27, 'Department3', '医院', 'department:hospital');

-- ----------------------------
-- Table structure for t_department_attribute_history
-- ----------------------------
DROP TABLE IF EXISTS `t_department_attribute_history`;
CREATE TABLE `t_department_attribute_history`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构链上id',
  `attribute` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '变更后的属性值',
  `time` datetime NULL DEFAULT NULL COMMENT '变更发生时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_department_attribute_history
-- ----------------------------
INSERT INTO `t_department_attribute_history` VALUES (38, 'AttributeApplication00000001105', 'test:test department:center', '2024-10-15 21:57:33');

-- ----------------------------
-- Table structure for t_video_asset
-- ----------------------------
DROP TABLE IF EXISTS `t_video_asset`;
CREATE TABLE `t_video_asset`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产链上id',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属机构名',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `location` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地区',
  `field` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '应用领域',
  `url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '访问链接（通过属性加密保护）',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `AESkey` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '对称密钥',
  `encType` int NOT NULL COMMENT '数据资产加密模式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_video_asset
-- ----------------------------
INSERT INTO `t_video_asset` VALUES (1, 'VideoAsset00000001212', '111', '属性授权中心', 'test:test department:center 1of2', '华中', '农业', 'SpL6aui9XFYZXZWSDocKCQ==', '2024-10-16 22:14:25', 'AAAAIAzrH8uSpWvYHayz/vJr9bHbXDUKyrmuf+eewQqDykiUAAADUgAAAIAcsYIJ96D+X7dk8VEFHtmCNQPhkpAy110LQZINPVStc7DFepti9HzJuXZdmh5f5wlJhNMerAJBg6dL3n8W1jdNNAQ5HDXA3ysqwD+e7aky9Fpu3a/n138ZDB/8GBo157nR5/yUJXEbsyYqfCdNNkW7A7hAZBATZ6XTzGQnsDKC0AAAAIADYJrzV+ICgb1DLO9zu45JfKbia3LkbMPnOF2w9ZLnAs6Ih9VBBsqpTJ/EvswUiQXu3/EB8wEPgGXVxG9imHAfCa9pdbgQOe5/xXbqzcBmOBsCwezcVyMiaNx8RNI99oeppe7iweW/M/t9GVTr68atDNifgo5kPBOIvgv3lBuKKQAAAAEAAAACAAAAAQAAAAAAAAAJdGVzdDp0ZXN0AAAAgFklLWrI3FXFg+kqpvUitmyG13JnYfzVQAiGpkb0R67JDMwXviDuTeqO5XUweo2mpX1IZI5lTLVPBp6Bn/V0WGVbAAtApQs5lF01Z0c97aqXhz3BIAPcglUjOGpCwZTEKuK5/Fy8Hyho8KZ2ayimSybxPYczJhldAmjcUouMxv86AAAAgH7bmytvYUhx33lOVkEaKr2glffEesuAERVeOGDHhs+Whjtyf7IlUvdZkzAr1bn2GmSrCpOP7xgCMuvP4iFEFBYXD71yljhVVPxXkmtCxsCPwEyEwVhDTw5tFFb4gPZCWHSkcSUEFiGSb8k2Td7hfrHImjFRflFV78flJJh3gA4QAAAAAQAAAAAAAAARZGVwYXJ0bWVudDpjZW50ZXIAAACAWSUtasjcVcWD6Sqm9SK2bIbXcmdh/NVACIamRvRHrskMzBe+IO5N6o7ldTB6jaalfUhkjmVMtU8GnoGf9XRYZVsAC0ClCzmUXTVnRz3tqpeHPcEgA9yCVSM4akLBlMQq4rn8XLwfKGjwpnZrKKZLJvE9hzMmGV0CaNxSi4zG/zoAAACAk6v7exf42lldUQc2bSS2G7o3kfmvvxhyJ2USb0uG4NtMpzCOwuab4/CatySNGEs8UbVRn0/1MYQgty0GWNksLKUNIrzIcSjCq8skn34zCWyUkAsd67uQFfe23zGa+vA2s42UCs3LPAmtw615rPnXptZtN3WifZYsTROfLzUL8Y8=', 0);
INSERT INTO `t_video_asset` VALUES (2, 'VideoAsset00000001214', '123', '属性授权中心', 'asset:file100', '华北', '医学界', 'c9OxcjwFiKxrIPSI2y6MdA==', '2024-10-16 22:14:54', 'AAAAIOxm0vFIGNGh2CfjDMGaN+ne+kbuwTZZPnFIP0GjbDz+AAACKQAAAIB/Omg/YzI89J15y87nTp9mvOuFvaHu/Q+veZA1ekHycrZm0HCgpeVPXFfo9HMYXEuctgYs4f6638cUw6YpCcr8A+V/an92y/tLDi4BpNMUm/IrAoZfSVs9jYUl099fdoTzeiRKnsgpd8a+WAhaAJQGFAZrw7qmAMqSpYN5CpR9MgAAAICEVnaV/M7TCg3stdLy96nyS4lI5PWLkbRKdCv0ZLY3tDx6Wy3LfmwBm5nX4eJVSUxvgLY1j3RbhRs8R/h3KP++XcL1A8rW9GUQewtSUdkH9Ll0fJgXqwrXuns19bauLTav2gs1AMsa+JWfmmY2BVXJfS22Ofbx1i1JtLo1bDr66QAAAAEAAAAAAAAADWFzc2V0OmZpbGUxMDAAAACAQBcJ8dBzKsAuP8TvVacNwYJkHkQvCrd3By5OU6yF8ept/PVWlou5KaE4ejo2Lu7/K7EgDhd7x+mErN8qV1IBgHNjWIbqX+ueEMK7qWwEgP7OhA4IKw29DrxfDdgX14C/wQJ9vDaVgyTiwIwb7q8eyxG2jRGubiZWU1JEypaMOEYAAACAOy+0hkSniDWdjwMKex2YPKv+tp42ZZIconEGNsrgj8WknxMM/e5W0laE9F52HlWjvkmxXpKhau+CAXyNs80JHnToNqQwdDhFRuMHX1IaLVahdc/skLCtQjvU1HlQVwj0sHBscTv7nKR8YO/YPgcCOEq+ifqCkAcdbTo4Nv1I3GY=', 0);
INSERT INTO `t_video_asset` VALUES (3, 'VideoAsset00000001242', 'aa', '属性授权中心', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', '华南', '农业', 'HTtjtvqav/01XOD+zxk2fsviQN3PTPBCyJUSJLBIFCM+SRFbo4fPuQeHVJEwIaLELu/9HENO8bLvD04UluIeHWn6BSIiBRynDPO5TDZeGzlqceLTfeq2OkS7bFk3cPbA', '2024-10-17 01:42:02', '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', 0);
INSERT INTO `t_video_asset` VALUES (4, 'VideoAsset00000001263', 'zzzz', '属性授权中心', 'department:center', '华北', '医学界', 'sBMDo71VoxRrOjYF6aek7Q==', '2024-10-17 02:35:27', 'AAAAIIqi2p+FjtJyuJqq40E1JVQW4cv/zyhxYDBWJYZlxXtBAAACLQAAAICR3S0DtaYpdbHopRtN9B0PF0duyl74JP5In4u1TgO5VJOSxPm/m7+sf4ZDCVRUDmO7shrhLpPm5mpkCitavWuvdrIW5PZhL4okWBDuKkQD5pYfc2XldE0LDmRMfbaW6g+2grwCHckjBnzyu05qalo/mhSqP50FC7TinbfXf28GbAAAAIAopGCIqG/Ka9Ugr7Nq6GIeTNfT8NLb9eDttBlu4lWK6LP/8aL/v2Kh/V/KtylkCt+aB04K4H5jv98dJr5pY6kiadXokNgR5F6BJv2lCgAABZ08PlWzNUzjVxY8ksZwgsCbs0NnOeLfJeMbNClBrs9GGD0wz4syaZNLPbKON4oonwAAAAEAAAAAAAAAEWRlcGFydG1lbnQ6Y2VudGVyAAAAgDQFGJ7lHmtK4qKoJeLjGcvOsOXbT3EQQ8Cc9LKpV5jvE0zu/uFcmaVL9NipZuhvJAY8f6htQE1T2xmxeGAl0QtncJrz59qRotVZE3FEyPvtHnIbKUH0lkPvbshGhZCzI+NPlbne2OG3pzzao7WrYWmCLK/u/Mf7lBlT2kdYwH81AAAAgAR2Mn/q1yRTChWe3gTiRksK/+8LKl4g39gAcsBafpr9OsEf7SSk/6INwOyaaOHy7GpO9wgqX09tif9pO2h5VgcO0tkkdcBZBBB9wR/kLWZBmukFFERzOU2yqvGpj7rLMzlc32TgfYTTiJG5PYYQucK5mFDtu9OXUWfsOigHm3NG', 0);

-- ----------------------------
-- Table structure for t_video_asset_order
-- ----------------------------
DROP TABLE IF EXISTS `t_video_asset_order`;
CREATE TABLE `t_video_asset_order`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fabricId` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产订单链上Id',
  `applicant` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请平台名',
  `owner` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权属平台名',
  `name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '数据资产名称',
  `policy` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '访问策略',
  `status` int NOT NULL COMMENT '当前状态',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_video_asset_order
-- ----------------------------
INSERT INTO `t_video_asset_order` VALUES (1, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-16 22:14:34');
INSERT INTO `t_video_asset_order` VALUES (2, 'VideoAsset00000001214', '属性授权中心', '属性授权中心', '123', 'asset:file100', 6, '2024-10-16 22:15:01');
INSERT INTO `t_video_asset_order` VALUES (3, 'VideoAsset00000001214', '属性授权中心', '属性授权中心', '123', 'asset:file100', 6, '2024-10-16 23:18:35');
INSERT INTO `t_video_asset_order` VALUES (4, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-16 23:25:01');
INSERT INTO `t_video_asset_order` VALUES (5, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-16 23:35:24');
INSERT INTO `t_video_asset_order` VALUES (6, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-16 23:46:15');
INSERT INTO `t_video_asset_order` VALUES (7, 'VideoAsset00000001214', '属性授权中心', '属性授权中心', '123', 'asset:file100', 6, '2024-10-17 01:20:40');
INSERT INTO `t_video_asset_order` VALUES (8, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-17 01:21:03');
INSERT INTO `t_video_asset_order` VALUES (9, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-17 01:37:06');
INSERT INTO `t_video_asset_order` VALUES (10, 'VideoAsset00000001214', '属性授权中心', '属性授权中心', '123', 'asset:file100', 6, '2024-10-17 01:37:24');
INSERT INTO `t_video_asset_order` VALUES (11, 'VideoAsset00000001212', '属性授权中心', '属性授权中心', '111', 'test:test department:center 1of2', 5, '2024-10-17 01:37:34');
INSERT INTO `t_video_asset_order` VALUES (12, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:42:09');
INSERT INTO `t_video_asset_order` VALUES (13, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:42:41');
INSERT INTO `t_video_asset_order` VALUES (14, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:50:22');
INSERT INTO `t_video_asset_order` VALUES (15, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:51:14');
INSERT INTO `t_video_asset_order` VALUES (16, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:51:30');
INSERT INTO `t_video_asset_order` VALUES (17, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:52:41');
INSERT INTO `t_video_asset_order` VALUES (18, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:53:00');
INSERT INTO `t_video_asset_order` VALUES (19, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:54:12');
INSERT INTO `t_video_asset_order` VALUES (20, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:55:35');
INSERT INTO `t_video_asset_order` VALUES (21, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 01:58:54');
INSERT INTO `t_video_asset_order` VALUES (22, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:00:42');
INSERT INTO `t_video_asset_order` VALUES (23, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:03:14');
INSERT INTO `t_video_asset_order` VALUES (24, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:03:47');
INSERT INTO `t_video_asset_order` VALUES (25, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:12:21');
INSERT INTO `t_video_asset_order` VALUES (26, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:14:13');
INSERT INTO `t_video_asset_order` VALUES (27, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:15:55');
INSERT INTO `t_video_asset_order` VALUES (28, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:18:10');
INSERT INTO `t_video_asset_order` VALUES (29, 'VideoAsset00000001242', '属性授权中心', '属性授权中心', 'aa', 'test:test department:center department:policeStation department:hospital asset:video001 asset:file100 1of6', 5, '2024-10-17 02:19:48');
INSERT INTO `t_video_asset_order` VALUES (30, 'VideoAsset00000001263', '属性授权中心', '属性授权中心', 'zzzz', 'department:center', 5, '2024-10-17 02:36:23');

SET FOREIGN_KEY_CHECKS = 1;
