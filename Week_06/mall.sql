-- MySQL dump 10.13  Distrib 8.0.16, for osx10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: local
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_commodity`
--

DROP TABLE IF EXISTS `tbl_commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_commodity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品昵称',
  `name` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `description` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品描述',
  `store_id` bigint NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '商品上架状态',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `stock` int NOT NULL DEFAULT '0' COMMENT '库存',
  `crime` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `utime` bigint NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_store_id` (`store_id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_order`
--

DROP TABLE IF EXISTS `tbl_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单总价',
  `number` int NOT NULL DEFAULT '0' COMMENT '商品个数',
  `status` smallint NOT NULL DEFAULT '0' COMMENT '订单状态',
  `phone` char(11) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单电话',
  `address` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单地址',
  `utime` bigint NOT NULL DEFAULT '0' COMMENT '更新时间',
  `crime` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_order_detail`
--

DROP TABLE IF EXISTS `tbl_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL DEFAULT '0' COMMENT '订单ID',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户ID',
  `commodity_id` bigint NOT NULL DEFAULT '0' COMMENT '商品ID',
  `store_id` bigint NOT NULL DEFAULT '0' COMMENT '店铺ID',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `utime` bigint NOT NULL DEFAULT '0' COMMENT '更新时间',
  `crime` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名字',
  `loginname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '登录密码密文',
  `phone` char(11) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `address` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `crime` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `utime` bigint NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_loginname` (`loginname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'local'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-08  1:44:45
