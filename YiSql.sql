CREATE DATABASE  IF NOT EXISTS `exam` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `exam`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: exam
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminId` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `adminName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Name',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'SEX',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Phone',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Email',
  `pwd` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cardId` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'CardId',
  `role` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Rule',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`adminId`) USING BTREE,
  KEY `sex` (`sex`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9528 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='Admin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (9527,'Super Admin','M','13658377857','1253838283@qq.com','$2a$10$MAp7H/FUUpSJGCC/ocsV2OnFW5Mrn/EKsVwOMs7CPvuTo/8rVeo1e','3132','0','yzhou116@gmail.com');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_manage`
--

DROP TABLE IF EXISTS `exam_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_manage` (
  `examCode` int NOT NULL AUTO_INCREMENT COMMENT 'Code',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Desc',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Name',
  `paperId` int DEFAULT NULL COMMENT 'Id',
  `examDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Date',
  `totalTime` int DEFAULT NULL COMMENT 'Duration',
  `grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'grade',
  `term` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'semster',
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'major',
  `institute` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'college',
  `totalScore` int DEFAULT NULL COMMENT '总分',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'type',
  `tips` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'know',
  `teacher` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `student` longtext COLLATE utf8_bin,
  `ispublic` int DEFAULT NULL,
  `isSurvey` int DEFAULT NULL,
  `isPractice` int DEFAULT NULL,
  PRIMARY KEY (`examCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20190035 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='exam manage';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_manage`
--

LOCK TABLES `exam_manage` WRITE;
/*!40000 ALTER TABLE `exam_manage` DISABLE KEYS */;
INSERT INTO `exam_manage` VALUES (20190001,'2019 First Semester Final Exam','Computer Network',1001,'2019-03-21',120,'2018','1','Computer Science','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou116@gmail.com','yzhou116@gmail.com,yzhou117@gmail.com,yzhou118@gmail.com,yzhou119@gmail.com,yzhou120@gmail.com,yzhou121@gmail.com,',1,0,0),(20190002,'2019 First Semester Final Exam','Database Theory',1002,'2019-03-07',90,'2018','2','Network Eng','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou117@gmail.com','yzhou116@gmail.com,yzhou117@gmail.com,',1,0,0),(20190003,'2019 First Semester Final Exam','Data Structure',1003,'2019-02-27',90,'2017','1','Software Eng','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou116@gmail.com','yzhou116@gmail.com,',1,0,0),(20190005,'2019 First Semester Final Exam','Computer Science',1004,'2019-03-14',90,'2018','1','Computer Science','School of Software',100,'Final Exam','Happy Test ! Guys.','yzhou117@gmail.com','yzhou120@gmail.com,',1,0,0),(20190006,'2019 First Semester Final Exam','Software Eng',1005,'2019-03-21',120,'2018','1','Computer Science','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou116@gmail.com','yzhou116@gmail.com,',1,0,0),(20190007,'2019 First Semester Final Exam','Operating System',1006,'2019-03-13',120,'2018','2','Computer Science','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou116@gmail.com','yzhou116@gmail.com,',1,0,0),(20190008,'2019 First Semester Final Exam','C Language',1007,'2019-03-13',120,'2018','1','Information Tech','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou117@gmail.com','yzhou116@gmail.com,yzhou120@gmail.com,',1,0,0),(20190009,'2019 First Semester Final Exam','Discrete Mathematics',1008,'2019-03-07',90,'2018','1','Information Tech','School of Software ',100,'Final Exam','Happy Test ! Guys.','yzhou117@gmail.com','yzhou116@gmail.com,yzhou120@gmail.com,',1,0,0),(20190013,'Test','Test',1009,'2019-04-24',120,'2017',NULL,'Test','Test',100,'Final Exam','Don\'t Cheat!','yzhou116@gmail.com',NULL,1,0,0),(20190014,'Test it','YiZhouTest',1010,'2022-12-06',100,'grade1',NULL,'IT','Seneca',100,'online','asdasd','yzhou116@gmail.com','yzhou116@gmail.com,yzhou120@gmail.com,',1,0,0),(20190019,'Yi Zhou','Seneca Test',1011,'2023-01-01',123,'2312',NULL,'It','Seneca',100,'er','asdad','yzhou116@gmail.com',NULL,1,0,0),(20190021,'Yi ZHOUZZZZ','Test Survey',1012,'2023-01-11',-1,'2023',NULL,'asda','Seneca',0,'asdasd','asdasd','yzhou116@gmail.com','yzhou120@gmail.com,',1,1,0),(20190022,'asda','Test 1',1013,'2023-02-07',120,'2023',NULL,'It','Seneca',120,'IY','asdasd','yzhou116@gmail.com',NULL,1,1,1),(20190023,'YiZhou','TestAutoMark',1014,'2023-02-15',120,'2023',NULL,'IT','Seneca',14,'IU','Try it','yzhou116@gmail.com','yzhou120@gmail.com,',1,0,0),(20190024,'YIYIYI','Test Practice',1015,'2023-02-15',120,'2023',NULL,'ITIT','Seneca',6,'test it','qweqwe','yzhou116@gmail.com',NULL,1,1,1),(20190025,'Yi zhou','TestYiZhouExam',1016,'2023-02-08',120,'2023',NULL,'it','seneca',8,'None','Nothing.','yzhou116@gmail.com','yzhou120@gmail.com,',1,0,0),(20190026,'Yi','TestYiZhouPractice',1017,'2023-02-17',120,'2023',NULL,'Prj111','Zhou',8,'it','I don\'t know','yzhou116@gmail.com',NULL,1,0,1),(20190027,'Yizhou','TestYiZhouSurvey',1018,'2023-02-09',120,'2023',NULL,'it','Seneca',0,'None','none','yzhou116@gmail.com',NULL,0,1,0),(20190028,'ZJ','Test2022-02-25',1019,'2023-02-28',120,'2023',NULL,'Nobody','John',10,'Dont know','No hint for you.','yzhou116@gmail.com','yzhou120@gmail.com,',1,0,0),(20190029,'AC','TestSurvry2022-02-25',1020,'2023-02-07',120,'2023',NULL,'Cpa','Ca',NULL,'none','asdsad','yzhou116@gmail.com',NULL,1,1,0),(20190030,'asd','Test2023-03',1021,'2023-03-15',120,'2023',NULL,'asdsad','sadsad',8,'wqewqe','wqeqe','yzhou116@gmail.com',NULL,1,0,1),(20190031,'asdsa','Test203-01Survry',1022,'2023-03-15',120,'2023',NULL,'asdsa','sadad',100,'asdsad','sadsad','yzhou116@gmail.com',NULL,1,1,0),(20190032,'adasd','Test202313333',1023,'2023-03-13',120,'2023',NULL,'sadas','saddas',10,'sada','sadasd','yzhou116@gmail.com',NULL,1,1,0),(20190034,'sad','Yi Test 15',1025,'2023-03-03',120,'2023',NULL,'sadad','Ssada',6,'IY','asdas','yzhou116@gmail.com','yzhou120@gmail.com,',1,0,0);
/*!40000 ALTER TABLE `exam_manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fill_question`
--

DROP TABLE IF EXISTS `fill_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fill_question` (
  `questionId` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'subject',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'question',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'rightA',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'analysis',
  `score` int DEFAULT '2' COMMENT '分数',
  `level` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'level',
  `section` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'chapter',
  `teacher` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10047 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='fill question';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fill_question`
--

LOCK TABLES `fill_question` WRITE;
/*!40000 ALTER TABLE `fill_question` DISABLE KEYS */;
INSERT INTO `fill_question` VALUES (10000,'Computer Network','From the perspective of computer network system composition, computer networks can be divided into () and ()','Communication Subnet Resource Subnet',NULL,2,'3',NULL,'yzhou116@gmail.com'),(10001,'Computer Network','Sending and receiving e-mail belongs to the function of () layer in ISO/OSI RM.','application',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10002,'Computer Network','In the TCP/IP layer model, the main protocols corresponding to the fourth layer of the OSI reference model are () and (), the latter of which provides a connectionless unreliable transmission service','TCP (Transmission Control Protocol) UDP (User Datagram Protocol)',NULL,2,'2',NULL,'yzhou116@gmail.com'),(10003,'Computer Network','The three wired media commonly used in computer networks are ( ), ( ) and ( )','\nCoaxial cable. Twisted pair fiber optic',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10004,'Computer Network','The four earliest domestic networks include ChinaNet of the former Ministry of Posts and Telecommunications, ChinaGBN of the former Ministry of Electronics, () of the Ministry of Education and CSTnet of the Chinese Academy of Sciences','\nCERnet (or China Education and Research Network)',NULL,2,'4',NULL,'yzhou117@gmail.com'),(10005,'Computer Network','\nThe computer network covering a country, region or several continents is called (), the network in the same building or covering a few kilometers is called (), and the one in between is ()','WAN LAN MAN',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10006,'Computer Network','\nThe protocol used by common email software such as Outlook is () to receive emails, and the protocol used to send emails is ()','POP3    SMTP    ',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10007,'Computer Network','In the communication system, the electrical signal before modulation is called () signal, and the signal after modulation is called modulation signal','Baseband',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10008,'Computer Network','According to the IPV4 standard, the IP address 205.3.127.13 belongs to () class addresses','C',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10009,'Computer Network','Computer networks use () technology, while traditional telephone networks use () technology','\npacket switching circuit switching',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10010,'Computer Network','The signal transmitted in the computer is (), while the transmission system of the public telephone system can only transmit ()','Digital signal Analog signal\n',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10011,'Computer Network','\nIn the communication system, the electrical signal before modulation is called (), and the signal after modulation is called ().','Baseband Signal Modulation Signal',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10012,'Computer Network','The IP address is divided into two parts () and ()','Network ID Host ID',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10013,'Computer Network','\nThe IP address protocol is used as the () layer protocol of the Internet, providing a connectionless datagram transmission mechanism, and the IP datagram is also divided into () and () two parts','network header data area',NULL,2,'2',NULL,'yzhou116@gmail.com'),(10014,'Computer Network','\n() is a simple remote terminal protocol.','TELNET',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10015,'Computer Network','\nIn the same system, the connection points for exchanging information between adjacent layers are called (), and the low-level modules that provide functional support to the high-level are called ().','interface service',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10016,'Computer Network','\nThe e-mail transmission protocol widely used on the Internet is ( )','SMTP',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10017,'Computer Network','According to the switching mode, computer networks can be divided into three types: circuit switching network, message switching network and ()','packet switching network',NULL,2,'3',NULL,'yzhou116@gmail.com'),(10018,'Computer Network','\nIntranet hierarchical structure includes network, (), application three levels.','Serve',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10019,'Computer Network','Each web page on the WWW has an independent address, these addresses are called ()','Uniform Resource Locator/URL',NULL,2,'2',NULL,'yzhou117@gmail.com'),(10020,'Computer Network','In packet-switched networks, additional information is used for routing, () and flow control in the network','error correction',NULL,2,'4',NULL,'yzhou117@gmail.com'),(10021,'Computer Network','\nAccording to the standard of IEEE802 model, the data link layer is divided into LLC sublayer and () sublayer.',' MAC ',NULL,2,'3',NULL,'yzhou117@gmail.com'),(10022,'Computer Network','According to the different routing information exchanged, routing algorithms can be divided into two categories: () and link state algorithms','Distance Vector Algorithm',NULL,2,'3',NULL,'yzhou116@gmail.com'),(10023,'Computer Network','\r Assume that a certain channel is subject to the highest code element rate limited by Ny\'s criterion to be 2000 symbols/second. If amplitude modulation is used,then the data rate that can be obtained is () b/s.','80000 ',NULL,2,'5',NULL,'yzhou117@gmail.com'),(10024,'Computer Network','() in the switched Ethernet system is used as the core to connect sites or network segments, and the input and output of frames between ports are no longer constrained by the CSMA/CD media access control protocol.','ethernet switch',NULL,2,'5',NULL,'yzhou116@gmail.com'),(10025,'Computer Network','The local area network reference model is based on the () standard','IEEE802',NULL,2,'5',NULL,'yzhou116@gmail.com'),(10026,'Computer Network','The core of the router is () .','routing table',NULL,2,'3',NULL,'yzhou116@gmail.com'),(10027,'Computer Network','If the bit string \"01011111110\" appears in the HDLC frame data segment, the output after bit filling is ()','10111110110',NULL,2,'5',NULL,'yzhou117@gmail.com'),(10028,'Computer Network','\nThree basic forms of digital modulation: amplitude shift keying method ASK, (), phase shift keying method PSK','Frequency shift keying method FSK',NULL,2,'5',NULL,'yzhou117@gmail.com'),(10029,'YiZhouTest','I can do this () and that ()','(Do it) (Dont do it)','',2,'','',NULL),(10030,'YiZhouTest','What is the best one()','yizhou','',2,'','',NULL),(10034,'Seneca Test','fill it()','asdasd','',2,'2','123','yzhou116@gmail.com'),(10035,'Test Survey','Am I a () person','good','',2,'','','yzhou116@gmail.com'),(10036,'Test Survey','Check null value()','nothing','check nothing',2,'1','1','yzhou116@gmail.com'),(10037,'TestAutoMark','Is Java a () language','backend','Java is backend language',2,'4','4','yzhou116@gmail.com'),(10038,'Test Practice','is this ()','practice','test practice',2,'','','yzhou116@gmail.com'),(10039,'TestYiZhouExam','Which () college has PRJ666','seneca','seneca',2,'2','2','yzhou116@gmail.com'),(10040,'TestYiZhouPractice','For your health you should quit ()','smoke','You should quit smoke',2,'','','yzhou116@gmail.com'),(10041,'TestYiZhouSurvey','Can you tell me where is your address ()','','',2,'','','yzhou116@gmail.com'),(10042,'Test2022-02-25','() is the first date of the year','Jan01','',2,'','','yzhou116@gmail.com'),(10043,'Test2022-02-25','() is the best game market','Steam',NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10044,'Test2023-03','Where is Japan ()','abc','asdad',2,'','','yzhou116@gmail.com'),(10045,'Test203-01Survry','sadad','sadasdad','',2,'','','yzhou116@gmail.com'),(10046,'Yi Test 15','hi ()','yi','',2,'','','yzhou116@gmail.com');
/*!40000 ALTER TABLE `fill_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `judge_question`
--

DROP TABLE IF EXISTS `judge_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `judge_question` (
  `questionId` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'n',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'C',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'A',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `score` int DEFAULT '2' COMMENT '分数',
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'l',
  `section` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `teacher` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10042 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='tandf';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `judge_question`
--

LOCK TABLES `judge_question` WRITE;
/*!40000 ALTER TABLE `judge_question` DISABLE KEYS */;
INSERT INTO `judge_question` VALUES (10001,'Computer Network','\nCompared with wired network, the data transfer rate of wireless network is generally relatively slow','T',NULL,2,'1','','yzhou116@gmail.com'),(10002,'Computer Network','In the OSI reference model, equivalent layers of different nodes have different functions','F',NULL,2,'1',NULL,'yzhou116@gmail.com'),(10003,'Computer Network','\nOrdinary computers cannot be used as servers','F',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10004,'Computer Network','A computer without a network cable cannot connect to the Internet','F',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10005,'Computer Network','The network card must have a driver installed','T',NULL,2,'2',NULL,'yzhou116@gmail.com'),(10006,'Computer Network','UTP is shielded twisted pair','F',NULL,2,'2',NULL,'yzhou116@gmail.com'),(10007,'Computer Network','\nNetwork interface card, also known as network card, is the basic component of the network','T',NULL,2,'2',NULL,'yzhou117@gmail.com'),(10008,'Computer Network','Wireless AP can expand network coverage exponentially','T',NULL,2,'3',NULL,'yzhou117@gmail.com'),(10009,'Computer Network','SMTP is a set of protocols for sending mail from source addresses to destination addresses','T',NULL,2,'4',NULL,'yzhou116@gmail.com'),(10010,'Computer Network','Task manager can close all processes','F',NULL,2,'3',NULL,'yzhou116@gmail.com'),(10011,'Computer Network','When using BT to download, the more users, the faster the download speed','T',NULL,2,'2',NULL,'yzhou116@gmail.com'),(10012,'Computer Network','When sending e-mails to friends on the Internet, you must know the real name and home address of the other party','F',NULL,2,'1',NULL,'yzhou117@gmail.com'),(10013,'YiZhouTest','Is this right','F','',2,'','',NULL),(10014,'YiZhouTest','Is this right','T','',2,'','',NULL),(10018,'Seneca Test','T is f','F','sdadsad',2,'4','1','yzhou116@gmail.com'),(10019,'Test Survey','Is our customer a good customer','T','',2,'','','yzhou116@gmail.com'),(10020,'Test 1','Sd','T','asd',2,'','','yzhou116@gmail.com'),(10021,'TestAutoMark','Is Seneca in North York','T','',2,'','','yzhou116@gmail.com'),(10022,'TestAutoMark','Is Canada close to Japan','F','Canada is close to US',2,NULL,NULL,'yzhou116@gmail.com'),(10023,'Test Practice','Is practice','T','It is practice',2,'','','yzhou116@gmail.com'),(10024,'TestYiZhouExam','Is Canada a good country','T','',2,'3','2','yzhou116@gmail.com'),(10025,'TestYiZhouPractice','Does Amazon provide cloud service','T','Amazon is a cloud provider. ',2,'2','1','yzhou116@gmail.com'),(10026,'TestYiZhouPractice','Am I a good guy?','F','I am a BAD guy.',2,'2','1','yzhou116@gmail.com'),(10027,'TestYiZhouSurvey','Is my product better than others','T','',2,'','','yzhou116@gmail.com'),(10028,'Test2022-02-25','Is Canada a good country','T','',2,'','','yzhou116@gmail.com'),(10029,'TestSurvry2022-02-25','Am I a good guy','F','',2,'','','yzhou116@gmail.com'),(10030,'Test2023-03','Where is China','F','asdsad',2,'','','yzhou116@gmail.com'),(10031,'Test203-01Survry','aadasd','F','',2,'','','yzhou116@gmail.com'),(10032,'Test203-01Survry','adasdasd','T',NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10033,'Test202313333','asdasd','F','',2,'','','yzhou116@gmail.com'),(10034,'Test202313333','adasd','T',NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10035,'AI Exam','Q1 ','T','True is right. ',2,'','','yzhou116@gmail.com'),(10036,'AI Exam','Q2 ','F','Q2',2,NULL,NULL,'yzhou116@gmail.com'),(10037,'AI Exam','Q3','T','Q3',2,NULL,NULL,'yzhou116@gmail.com'),(10038,'AI Exam','Q4','F','Q4',2,NULL,NULL,'yzhou116@gmail.com'),(10039,'AI Exam','Q5','T','Q5',2,NULL,NULL,'yzhou116@gmail.com'),(10040,'Yi Test 15','q3','T','',2,'','','yzhou116@gmail.com'),(10041,'AI Exam','Sdadsa','F','',2,'','','yzhou116@gmail.com');
/*!40000 ALTER TABLE `judge_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ik',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 't',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `time` date DEFAULT NULL COMMENT 't',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='t';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--



--
-- Table structure for table `multi_question`
--

DROP TABLE IF EXISTS `multi_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `multi_question` (
  `questionId` int NOT NULL AUTO_INCREMENT COMMENT 'd',
  `subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `answerA` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `answerB` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `answerC` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `answerD` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `rightAnswer` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `score` int DEFAULT '2' COMMENT 'd',
  `section` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `level` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'd',
  `teacher` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10069 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='d';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multi_question`
--

LOCK TABLES `multi_question` WRITE;
/*!40000 ALTER TABLE `multi_question` DISABLE KEYS */;
INSERT INTO `multi_question` VALUES (10000,'Computer Network','\nThe role of DNS server and DHCP server is（）','Translate IP addresses into computer names and assign IP addresses to clients','Translate IP address to computer name, resolve computer MAC address','Translate computer names into IP addresses, assign IP addresses to clients','将Translate the computer name into an IP address and resolve the MAC address of the computer','C',NULL,2,'Application Layer','2','yzhou116@gmail.com'),(10001,'Computer Network','What protocol is usually used by the HTTP protocol for transmission（）','ARP','DHCP','UDP','TCP','D',NULL,2,'Application Layer','2','yzhou116@gmail.com'),(10003,'Computer Network','Command to view DNS cache records（）','ipconfig/displaydns','nslookup','ipconfig/release','ipconfig/flushdns','A',NULL,2,'Application Layer','3','yzhou116@gmail.com'),(10004,'Computer Network','DHCP(        )The destination IP address of the message is 255.255.255.255','DhcpDisover','DhcpOffer','DhcpAck','DhcpNack','A',NULL,2,'Application Layer','2','yzhou117@gmail.com'),(10005,'Computer Network','The following addresses,（  ）Not the IP address assigned by the DHCP server','196.254.109.100','169.254.12.42','69.254.48.45','96.254.54.15','B',NULL,2,'Application Layer','2','yzhou117@gmail.com'),(10006,'Computer Network','What network parameters can usually be automatically configured by DHCP for clients（）','IP, mask, gateway, DNS','IP, mask, domain name, SMTP','gateway, mask, browser, FTP','IP, gateway, DNS, server','A',NULL,2,'Application Layer','2','yzhou117@gmail.com'),(10007,'Computer Network','The correct query sequence for a DNS server during name resolution is（）','Local Cache Records → Zone Records → Forwarding Name Servers → Root Name Servers','Zone Records → Local Cache Records → Forwarding Name Servers → Root Name Servers','Local Cache Records → Zone Records → Root Name Servers → Forwarding Name Servers','\n\nZone Records → Local Cache Records → Root Name Servers → Forwarding Name Servers','A',NULL,2,'Application Layer','3','yzhou116@gmail.com'),(10008,'Computer Network','In the TCP/IP protocol, ports with serial numbers less than ( ) are called well-known ports.','1024','64','256','128','A',NULL,2,'Transport Layer','1','yzhou116@gmail.com'),(10009,'Computer Network','Use TCP/IP to play video on the Internet. If you want to use the fastest protocol at the transport layer to reduce delay, use ( )','\nLow-overhead feature of UDP protocol','High overhead characteristics of UDP protocol','The low-overhead feature of the TCP protocol','High overhead characteristics of the TCP protocol','A',NULL,2,'Transport Layer','2','yzhou116@gmail.com'),(10010,'Computer Network','Use ( ) in the TCP protocol to distinguish different application processes','PORT','IP Address','agreement type','MAC Address','A',NULL,2,'Transport Layer','3','yzhou117@gmail.com'),(10011,'Computer Network','The \"reliable\" in the reliable transport protocol refers to ( )','Use connection-oriented sessions','Use \"best effort\" transport','\nUse sliding windows to maintain reliability','\nUse the acknowledgment retransmission mechanism to ensure that the transmitted data is not lost','D',NULL,2,'Transport Layer','2','yzhou117@gmail.com'),(10012,'Computer Network','\nAssuming that the congestion window is 50KB and the receiving window is 80KB, the maximum number of bytes that TCP can send is ( )','50KB','80KB','130KB','30KB','A',NULL,2,'Transport Layer','4','yzhou117@gmail.com'),(10013,'Computer Network','Host A sends a (SYN=1, seq=2000) TCP message to host B, expecting to establish a connection with host B. If host B accepts the connection request, the correct TCP message sent by host B may be ( )','（SYN=0,ACK=0,seq=2001,ack=2001）','（SYN=1,ACK=1,seq=2000,ack=2000）','•	C.（SYN=1,ACK=1,seq=2001,ack=2001）','（SYN=0,ACK=1,seq=2000,ack=2000）','C',NULL,2,'Transport Layer','2','yzhou117@gmail.com'),(10014,'Computer Network','Host A continuously sends two TCP segments to host B, the sequence numbers of which are 70 and 100 respectively. Let me ask: (1) The first segment carries () bytes of data?',' 70','30','100','170','B',NULL,2,'Transport Layer','3','yzhou116@gmail.com'),(10015,'Computer Network','The process of PCM pulse code modulation ( )','Sampling, Quantization, Encoding','Quantize, encode, sample','Encoding, Quantization, Sampling','Sampling, Encoding, Quantization','A',NULL,2,'Physical Layer','4','yzhou116@gmail.com'),(10016,'Computer Network','\r \r If the data transmission rate of a communication link using 4-phase modulation is 2400bps, then the baud rate of the link is ()','600Baud','1200Baud','4800Baud','9600Baud','B',NULL,2,'Physical Layer','1','yzhou116@gmail.com'),(10017,'Computer Network','\nIn the following description about the data transmission rate, the wrong one is ( )','The data transmission rate indicates the number of binary bits that make up the data code transmitted per second','For binary data, the data transfer rate is S=1/T (bps)','Commonly used data transmission rate units are: 1Mbps=1.024×106bps','The data transmission rate is one of the important technical indicators describing the performance of the data transmission system','C',NULL,2,'Physical Layer','2','yzhou116@gmail.com'),(10018,'Computer Network','In the following description about the concept of time division multiplexing, the error is .( ).','Time division multiplexing divides the time used by the line into time slices','\nTime division multiplexing is divided into synchronous time division multiplexing and statistical time division multiplexing','\nThe concept and function of time division multiplexing using \"frame\" and data link layer \"frame\" are different','Statistical time division multiplexing pre-allocates time slices to individual channels','D',NULL,2,'Physical Layer','2','yzhou117@gmail.com'),(10019,'Computer Network','The transmission medium supported by the 1000BASE-T standard is ()','twisted pair','coaxial cable','Fiber','radio','A',NULL,2,'Physical Layer','1','yzhou117@gmail.com'),(10020,'Computer Network','An Ethernet switch reads the entire data frame, performs an error check on the data frame and then forwards it out. This exchange method is called ()','cut-through switching','Fragment free swap','error free exchange','store-and-forward switching','D',NULL,2,'Data Link Layer','2','yzhou117@gmail.com'),(10021,'Computer Network','Regarding VLAN, the correct one in the following description is ()','A new switch does not have VLANs configured','\nReduced the number of collision domains by configuring VLANs','A VLAN cannot span multiple switches','Each VLAN belongs to a different broadcast domain','D',NULL,2,'Data Link Layer','2','yzhou116@gmail.com'),(10022,'Computer Network','What is the function of using physical address in Ethernet protocol?','\nUsed for communication between hosts in different subnets','As the unique identifier of the second layer device','Protocol data unit used to distinguish layer 2 and layer 3','Save Host detects unknown remote devices','B',NULL,2,'Data Link Layer','2','yzhou116@gmail.com'),(10023,'Computer Network','\nIn the CSMA/CD protocol adopted by Ethernet, when a conflict occurs, the backoff delay is calculated through the binary exponential backoff algorithm. Regarding this algorithm, the error in the following discussion is ()','The more conflicts, the shorter the time to back off','\n\nThe average number of backoffs is related to the load size','\nThe average value of backoff delay is related to the load size','Give up sending after the number of retransmissions reaches a certain limit','A',NULL,2,'Data Link Layer','3','yzhou116@gmail.com'),(10024,'Computer Network','\nIn the following description about the switch obtaining the MAC address of the device connected to its port, the correct one is ()','The switch extracts the MAC address of the device from the routing table','The switch checks the source address of the incoming packet on the port','Switches exchange address tables with each other','The network administrator manually enters the MAC address of the device','B',NULL,2,'Data Link Layer','2','yzhou117@gmail.com'),(10025,'Computer Network','If G(x) is 11010010, which of the following 4 CRC check bit sequences can be correct?','1101011001','101011011','11011011','1011001','B',NULL,2,'Data Link Layer','1','yzhou117@gmail.com'),(10026,'Computer Network','In the following description about the Ethernet physical address, the error is','Ethernet physical address is also called MAC address','The 48-bit Ethernet physical address allows the number of allocated addresses to reach 247','The physical address of the network card is written into the EPROM of the host','\nThe physical address of each network card is unique in the world\n\n\n','C',NULL,2,'Data Link Layer','3','yzhou117@gmail.com'),(10027,'Computer Network','Among the following frame types, those that do not belong to the HDLC frame type are ()','information frame','confirmation frame','\nmonitor frame','\nunnumbered frame\n','B',NULL,2,'Data Link Layer','1','yzhou117@gmail.com'),(10028,'Computer Network','\nA group of sites connected through switches, the correct statement about their broadcast domain and collision domain is ()','Form a collision domain, but not a broadcast domain','Form a broadcast domain, but not a collision domain','\nForm a collision domain, which is also a broadcast domain','Neither a collision domain nor a broadcast domain','B',NULL,2,'Data Link Layer','3','yzhou116@gmail.com'),(10029,'Computer Network','The data unit of the data link layer is ()','frame\n','byte','bits','group','A',NULL,2,'Data Link Layer','1','yzhou116@gmail.com'),(10030,'Computer Network','\nThe LAN reference model can be divided into physical layer, ( )','MAC, LLC and other three layers','LLC, MHS and other three layers','\nMAC, FTAM and other three layers\n','LLC, VT and other three layers','A',NULL,2,'Data Link Layer','3','yzhou116@gmail.com'),(10031,'Test','Test','A','B','C','D','B','解析',2,'Test','4','yzhou116@gmail.com'),(10032,'Computer Network','The role of DNS server and DHCP server is ()','A','B','C','D','B','哦解析',2,'Network Layer','2','yzhou116@gmail.com'),(10033,'YiZhouTest','What is my name','kuang','john','wong','yi','','',2,'','',NULL),(10034,'YiZhouTest','What is my id','112','113','114',NULL,NULL,NULL,2,NULL,NULL,NULL),(10039,'Seneca Test','sadasf','1','2','3','4','C','asdasd',2,'13','3','yzhou116@gmail.com'),(10040,'Test Survey','Do you like my product','Like it','Don\'t like it','It is ok','bad','','',2,'','','yzhou116@gmail.com'),(10041,'Test Survey','Do you like our customer service','Very Like','It is ok','Don\'t like','Awful',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10042,'Test 1','YU','A','B','C','D','B','',2,'asd','2','yzhou116@gmail.com'),(10043,'TestAutoMark','Who is Toronto Mayor','John Tory','John Cena','King John Un','Trump','A','',2,'1','1','yzhou116@gmail.com'),(10044,'TestAutoMark','The best city in Canada','Shanghai','Delhi','New York','Toronto','D',NULL,2,'1','2','yzhou116@gmail.com'),(10045,'TestAutoMark','Find out the frontend language. ','PHP','.Net','Javascript','Python','C','Javascript is frontend side langauge',2,'2','5','yzhou116@gmail.com'),(10046,'TestAutoMark','What is the last course in Seneca college','WEB422','PRJ666','APS145','WEB322','B','',2,'','','yzhou116@gmail.com'),(10047,'Test Practice','Test practice','P','C','D','A','A','Test practice',2,'','','yzhou116@gmail.com'),(10048,'TestYiZhouExam','What is the capital of Ontario','Toronto','North York','Markham','Midtwon','A','It is toronto',2,'1','3','yzhou116@gmail.com'),(10049,'TestYiZhouExam','Where is Canada','Africa','North America','South America','Europe','B',NULL,2,'1','2','yzhou116@gmail.com'),(10050,'TestYiZhouPractice','Choose the course contains project management','APS145','WEB422','COM111','PRJ666','D','prj is the one which contains project management',2,'2','2','yzhou116@gmail.com'),(10051,'TestYiZhouSurvey','How good is my service','Great','Good','Bad','Awful','','',2,'','','yzhou116@gmail.com'),(10052,'TestYiZhouSurvey','How is my product','Good','Great','Bad','Awful',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10053,'Test2022-02-25','The good type of hacker','Black','Grey','White','Red','C','',2,'','','yzhou116@gmail.com'),(10054,'Test2022-02-25','Choose the weather you feel cold','Sun','Moon','rain','Snow','D',NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10055,'TestSurvry2022-02-25','How good is my product','Excellent','Good','Bad','Awful','','',2,'','','yzhou116@gmail.com'),(10056,'TestSurvry2022-02-25','How good is my company','Excellent','Good ','Bad','Awful',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10057,'TestSurvry2022-02-25','How good is my product A','Excellent','Good','Bad','Awful',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10058,'TestSurvry2022-02-25','How good is my product B','Excellent','Good','Bad','Awful',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10059,'Test2023-03','Where is Canada','1','2','3','4','B','sadasda',2,'','','yzhou116@gmail.com'),(10060,'Test2023-03','Where is India','2','3','4','5','D','asdasfsafsaf',2,NULL,NULL,'yzhou116@gmail.com'),(10061,'Test203-01Survry','Where is Canda','1','2','3','4','B','asdad',2,'','','yzhou116@gmail.com'),(10062,'Test203-01Survry','Where is India','23','4','5','6',NULL,'sada',2,NULL,NULL,'yzhou116@gmail.com'),(10063,'Test203-01Survry','asdasdas','6','7','8','9',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10064,'Test202313333','asdada','1','2','3','4','','',2,'','','yzhou116@gmail.com'),(10065,'Test202313333','asdadsa','3','4','5','6',NULL,NULL,2,NULL,NULL,'yzhou116@gmail.com'),(10066,'Yi Test 15','Q1','a','b','c','d','A','',2,'','','yzhou116@gmail.com'),(10067,'AI Exam','Cool','A','B','C','D','C','',2,'','','yzhou116@gmail.com'),(10068,'AI Exam','D','E','R','G',NULL,'A',NULL,2,NULL,NULL,'yzhou116@gmail.com');
/*!40000 ALTER TABLE `multi_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper_manage`
--

DROP TABLE IF EXISTS `paper_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paper_manage` (
  `paperId` int DEFAULT NULL COMMENT 'a',
  `questionType` int DEFAULT NULL COMMENT 'a',
  `questionId` int DEFAULT NULL COMMENT 'a'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='a';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper_manage`
--

LOCK TABLES `paper_manage` WRITE;
/*!40000 ALTER TABLE `paper_manage` DISABLE KEYS */;
INSERT INTO `paper_manage` VALUES (1001,1,10013),(1001,1,10005),(1001,1,10001),(1001,1,10004),(1001,1,10000),(1001,1,10016),(1001,1,10032),(1001,1,10025),(1001,1,10030),(1001,1,10017),(1001,1,10027),(1001,1,10023),(1001,1,10014),(1001,1,10009),(1001,1,10008),(1001,1,10011),(1001,1,10010),(1001,1,10021),(1001,1,10022),(1001,1,10026),(1001,2,10017),(1001,2,10012),(1001,2,10004),(1001,2,10005),(1001,2,10003),(1001,2,10008),(1001,2,10020),(1001,2,10013),(1001,2,10006),(1001,2,10015),(1001,3,10002),(1001,3,10009),(1001,3,10003),(1001,3,10005),(1001,3,10004),(1001,3,10012),(1001,3,10011),(1001,3,10006),(1001,3,10008),(1001,3,10001),(1010,1,10033),(1010,1,10034),(1010,2,10029),(1010,3,10013),(1010,1,10035),(1010,3,10014),(1011,2,10034),(1011,3,10018),(1011,1,10039),(1012,1,10040),(1012,1,10041),(1012,2,10035),(1012,3,10019),(1012,2,10036),(1013,1,10042),(1013,3,10020),(1014,1,10043),(1014,1,10044),(1014,3,10021),(1014,2,10037),(1014,3,10022),(1014,1,10045),(1014,1,10046),(1015,1,10047),(1015,2,10038),(1015,3,10023),(1016,1,10048),(1016,1,10049),(1016,2,10039),(1016,3,10024),(1017,1,10050),(1017,2,10040),(1017,3,10025),(1017,3,10026),(1018,1,10051),(1018,1,10052),(1018,3,10027),(1018,2,10041),(1019,1,10053),(1019,3,10028),(1019,2,10042),(1019,2,10043),(1019,1,10054),(1020,1,10055),(1020,1,10056),(1020,3,10029),(1020,1,10057),(1020,1,10058),(1021,1,10059),(1021,1,10060),(1021,3,10030),(1021,2,10044),(1022,1,10061),(1022,1,10062),(1022,2,10045),(1022,3,10031),(1022,3,10032),(1022,1,10063),(1023,1,10064),(1023,1,10065),(1023,3,10033),(1023,3,10034),(1024,3,10035),(1024,3,10036),(1024,3,10037),(1024,3,10038),(1024,3,10039),(1025,1,10066),(1025,2,10046),(1025,3,10040),(1024,1,10067),(1024,1,10068),(1024,3,10041);
/*!40000 ALTER TABLE `paper_manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replay`
--

DROP TABLE IF EXISTS `replay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `replay` (
  `messageId` int DEFAULT NULL COMMENT 'a',
  `replayId` int NOT NULL AUTO_INCREMENT COMMENT 'a',
  `replay` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'a',
  `replayTime` date DEFAULT NULL COMMENT 'a',
  PRIMARY KEY (`replayId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='a';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replay`
--

LOCK TABLES `replay` WRITE;
/*!40000 ALTER TABLE `replay` DISABLE KEYS */;
INSERT INTO `replay` VALUES (26,3,'Good, I am comming,do not run away from me!','2019-03-18'),(22,4,'You are a good guy.','2019-03-18'),(25,5,'I am getting Stronger','2019-03-19'),(25,6,'I am the same','2019-03-19'),(21,7,'I don\'t know.','2019-03-19'),(16,8,'There is no good food here','2019-03-19'),(15,9,'You sucks','2019-03-19'),(21,11,'I don\'t like too many people','2019-03-19'),(20,12,'Yeah, it looks good.','2019-03-19'),(26,13,'I love you too','2019-03-19'),(27,14,'You are a bad guy','2019-03-31'),(33,18,'Yes, today is Monday.','2019-04-14'),(34,19,'1111','2019-04-16'),(34,20,'2222','2019-04-16'),(35,21,'666','2019-09-05');
/*!40000 ALTER TABLE `replay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `scoreId` int NOT NULL AUTO_INCREMENT COMMENT 'a',
  `examCode` int DEFAULT NULL COMMENT 'a',
  `studentId` int DEFAULT NULL COMMENT 'c',
  `subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'c',
  `ptScore` int DEFAULT NULL COMMENT 'e',
  `etScore` int DEFAULT NULL COMMENT 'g',
  `score` int DEFAULT NULL COMMENT 'j',
  `answerDate` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 't',
  PRIMARY KEY (`scoreId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='m';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (4,20190001,20154084,'Computer Network',NULL,70,100,'2019-04-03'),(14,20190002,20154084,'Database Theory',NULL,78,100,'2019-04-20'),(15,20190002,20154084,'Database Theory',NULL,80,100,'2019-04-20'),(16,20190002,20154084,'Database Theory',NULL,83,100,'2019-04-20'),(17,20190001,20154001,'Computer Network',NULL,74,100,'2019-04-13'),(18,20190001,20154001,'Computer Network',NULL,70,100,'2019-04-10'),(19,20190001,20155003,'Computer Network',NULL,86,100,'2019-04-14'),(20,20190001,20155007,'Computer Network',NULL,90,100,'2019-04-11'),(21,20190001,20155007,'Computer Network',NULL,92,100,'2019-04-12'),(22,20190001,20155008,'Computer Network',NULL,80,100,'2019-04-12'),(23,20190001,20155003,'Computer Network',NULL,90,100,'2019-04-13'),(24,20190001,20155008,'Computer Network',NULL,88,100,'2019-04-14'),(27,20190001,20154084,'Computer Network',NULL,80,100,'2019-04-25'),(30,20190001,20154084,'Computer Network',NULL,0,100,'2019-04-29'),(31,20190001,20154084,'Computer Network',NULL,0,100,'2019-09-06'),(32,20190001,20154084,'Computer Network',NULL,2,100,'2022-12-19'),(33,20190003,20154084,'Data Structure',NULL,0,100,'2022-12-19'),(34,20190014,20154084,'YiZhouTest',NULL,0,NULL,'2022-12-19'),(35,20190001,20155008,'Computer Network',NULL,4,100,'2023-01-09'),(36,20190001,20155008,'Computer Network',NULL,4,100,'2023-01-09'),(37,20190001,20155008,'Computer Network',NULL,4,100,'2023-01-09'),(38,20190014,20155008,'YiZhouTest',NULL,6,NULL,'2023-01-19'),(39,20190014,20155008,'YiZhouTest',NULL,10,NULL,'2023-01-19'),(40,20190023,20155008,'TestAutoMark',NULL,12,14,'2023-02-07'),(41,20190023,20155008,'TestAutoMark',NULL,10,14,'2023-02-07'),(42,20190023,20155008,'TestAutoMark',NULL,10,14,'2023-02-07'),(43,20190023,20155008,'TestAutoMark',NULL,14,14,'2023-02-07'),(44,20190023,20155008,'TestAutoMark',NULL,10,14,'2023-02-07'),(45,20190023,20155008,'TestAutoMark',NULL,14,14,'2023-02-07'),(46,20190023,20155008,'TestAutoMark',NULL,12,14,'2023-02-07'),(47,20190023,20155008,'TestAutoMark',NULL,10,14,'2023-02-07'),(48,20190023,20155008,'TestAutoMark',NULL,8,14,'2023-02-07'),(49,20190023,20155008,'TestAutoMark',NULL,10,14,'2023-02-07'),(50,20190023,20155008,'TestAutoMark',NULL,12,14,'2023-02-07'),(51,20190023,20155008,'TestAutoMark',NULL,12,14,'2023-02-07'),(52,20190023,20155008,'TestAutoMark',NULL,4,14,'2023-02-07'),(53,20190023,20155008,'TestAutoMark',NULL,14,14,'2023-02-07'),(54,20190024,20155008,'Test Practice',NULL,2,6,'2023-02-12'),(55,20190024,20155008,'Test Practice',NULL,0,6,'2023-02-12'),(56,20190024,20155008,'Test Practice',NULL,6,6,'2023-02-12'),(57,20190024,20155008,'Test Practice',NULL,4,6,'2023-02-12'),(58,20190025,20155008,'TestYiZhouExam',NULL,8,8,'2023-02-15'),(59,20190025,20155008,'TestYiZhouExam',NULL,2,8,'2023-02-15'),(60,20190026,20155008,'TestYiZhouPractice',NULL,8,8,'2023-02-15'),(61,20190026,20155008,'TestYiZhouPractice',NULL,8,8,'2023-02-25'),(62,20190028,20155008,'Test2022-02-25',NULL,8,10,'2023-02-25'),(63,20190030,20155008,'Test2023-03',NULL,8,8,'2023-03-01'),(64,20190032,20155008,'Test202313333',NULL,4,10,'2023-03-01'),(65,20190032,20155008,'Test202313333',NULL,4,10,'2023-03-04'),(66,20190034,20154084,'Yi Test 15',NULL,6,6,'2023-03-15'),(67,20190028,20155008,'Test2022-02-25',NULL,0,10,'2023-03-18'),(68,20190034,20155008,'Yi Test 15',NULL,0,6,'2023-03-18'),(69,20190033,20155008,'AI Exam',NULL,16,10,'2023-03-18');
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentId` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `studentName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'n',
  `grade` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'g',
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'm',
  `clazz` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `institute` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 't',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'e',
  `pwd` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cardId` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'i',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 's',
  `role` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '2' COMMENT 'r',
  PRIMARY KEY (`studentId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20155016 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='c';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (20154001,'yzhou117@gmail.com','Timmy Wong','2015','Computer Science','2','School of Software ','13585439532','gblw@163.com','123456','3412312','M','2'),(20154084,'yzhou116@gmail.com','Fisher Comma','2015','Computer Science','2','School of Software ','13658377857','13658377857@sina.cn','$2a$10$X9T86E2FhUsRlffv/Co./uTAtFaZVssxUOBr9bsCT698tyDwtUP/C','124123124535','M','2'),(20155003,'yzhou118@gmail.com','Alex Cool','2015','Information Tech','1','School of Software ','15583829425','15583829425@163.com','123456','3242342','M','2'),(20155007,'yzhou119@gmail.com','Tracy Acy','2015','Software Eng','1','School of Software ','18734538457','18734538457@163.com','123456','2423423','F','2'),(20155008,'yzhou120@gmail.com','Aimee Uchiha','2015','Information Tech','2','School of Software ','15523619564','15523619564@163.com','$2a$10$ZmebOefQhitK.pwgeR8C/OvqDgfPxFD6XfF26ypav7.nTJz5xe6rC','500234199704022353','F','2'),(20155012,'yzhou911@gmail.com','John Tory','2023','Computer Science','-1','Seneca','911','yzhou911@gmail.com','$2a$10$UPARIaUmWVokoynjP5Y.Au2WxJh.VRqrO2AknHNic1SNotXS63Bpm','119','M','2'),(20155015,'yzhou998@gmail.com','kk','2023','Computer Science','-1','aa','17292','u@ko.com','$2a$10$OrQnJqBf7R.M3qKwlj5psuKpGLzRQVSqHacpa9I.sUz0GRs5P2GHm','998','c','2');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `surveyRs`
--

DROP TABLE IF EXISTS `surveyRs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `surveyRs` (
  `id` varchar(255) NOT NULL COMMENT 'ID',
  `examCode` int DEFAULT NULL,
  `studentId` varchar(255) DEFAULT NULL,
  `answerResult` longtext,
  `answerDate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `surveyRs`
--

LOCK TABLES `surveyRs` WRITE;
/*!40000 ALTER TABLE `surveyRs` DISABLE KEYS */;
INSERT INTO `surveyRs` VALUES ('0efae9d0-ae61-4d1f-b1cd-3501f2de00db',20190021,'yizhou000@gmail.com','{\r\n  \"10035\" : \"ccc\",\r\n  \"10019\" : \"Correct\",\r\n  \"10036\" : \"zzz\",\r\n  \"10040\" : \"C\",\r\n  \"10041\" : \"A\"\r\n}','2023-01-28'),('1310a5a9-df1c-43ef-ba70-7c21ab8cb3d2',20190029,'goodtimmy','{\r\n  \"10055\" : \"B\",\r\n  \"10056\" : \"C\",\r\n  \"10057\" : \"D\",\r\n  \"10058\" : \"A\",\r\n  \"10029\" : \"Wrong\"\r\n}','2023-02-25'),('1b6ec7d8-8a3d-4706-8290-c942d2eab77c',20190021,'yzhou120@gmail.com','{\r\n  \"10035\" : \"AAAAAA\",\r\n  \"10019\" : \"Wrong\",\r\n  \"10036\" : \"ccc\",\r\n  \"10040\" : \"B\",\r\n  \"10041\" : \"A\"\r\n}','2023-02-20'),('3e5d766a-e1d7-4399-85a2-97283f382b74',20190027,'yzhou116@gmail.com','{\r\n  \"10051\" : \"A\",\r\n  \"10052\" : \"B\",\r\n  \"10041\" : \"sdf\",\r\n  \"10027\" : \"Wrong\"\r\n}','2023-02-15'),('53337714-6924-4d1b-b7c0-9375a2b03f53',20190029,'badthomas','{\r\n  \"10055\" : \"D\",\r\n  \"10056\" : \"A\",\r\n  \"10057\" : \"B\",\r\n  \"10058\" : \"C\",\r\n  \"10029\" : \"Correct\"\r\n}','2023-02-25'),('6fe8f9df-06cb-44dd-a99d-e11ebbbdcace',20190021,'yyyzhou999@gmail.com','{\r\n  \"10035\" : \"qqq\",\r\n  \"10019\" : \"Correct\",\r\n  \"10036\" : \"www\",\r\n  \"10040\" : \"D\",\r\n  \"10041\" : \"C\"\r\n}','2023-01-28'),('8455c13d-e036-46b5-be82-7a7fd84488d8',20190032,'yzhou111@gmail.com','{\r\n  \"10064\" : \"A\",\r\n  \"10065\" : \"B\",\r\n  \"10033\" : \"Correct\",\r\n  \"10034\" : \"Wrong\"\r\n}','2023-03-01'),('9a750948-e9be-4d79-8e9d-eead42a69511',20190031,'yzhou116@gmail.com','{\r   \"10032\" : \"Correct\",\r   \"10061\" : \"A\",\r   \"10045\" : \"abc\",\r   \"10062\" : \"B\",\r   \"10063\" : \"C\",\r   \"10031\" : \"Wrong\"\r }','2023-03-01'),('a05673fc-897d-445d-938e-f706eadb88d9',20190029,'yzhou116@gmail.com','{\r\n  \"10055\" : \"A\",\r\n  \"10056\" : \"B\",\r\n  \"10057\" : \"C\",\r\n  \"10058\" : \"A\",\r\n  \"10029\" : \"Wrong\"\r\n}','2023-02-25'),('bb4ec262-b941-4094-9fdf-6f71b89f824f',20190021,'yzhou111@gmail.com','{\r\n  \"10035\" : \"ZZZZZ\",\r\n  \"10019\" : \"Correct\",\r\n  \"10036\" : \"VVVVV\",\r\n  \"10040\" : \"D\",\r\n  \"10041\" : \"D\"\r\n}','2023-02-20'),('de74faa6-bc72-4c3c-aaee-bafc3a4fdddf',20190029,'badthomas','{\r\n  \"10055\" : \"D\",\r\n  \"10056\" : \"D\",\r\n  \"10057\" : \"D\",\r\n  \"10058\" : \"D\",\r\n  \"10029\" : \"Correct\"\r\n}','2023-02-25'),('ea44dce5-027b-4688-aa6d-b57fa195459e',20190021,'yyyzhou555@gmail.com','{\r\n  \"10035\" : \"gdgf\",\r\n  \"10019\" : \"Correct\",\r\n  \"10036\" : \"dfgd\",\r\n  \"10040\" : \"B\",\r\n  \"10041\" : \"D\"\r\n}','2023-01-29'),('f1f390fe-982c-4c13-b3b2-0c69813093a7',20190021,'yyyzhou111@gmail.com','{\r\n  \"10035\" : \"qwe\",\r\n  \"10019\" : \"Wrong\",\r\n  \"10036\" : \"asd\",\r\n  \"10040\" : \"A\",\r\n  \"10041\" : \"D\"\r\n}','2023-01-28');
/*!40000 ALTER TABLE `surveyRs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacherId` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `teacherName` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'n',
  `institute` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `sex` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 't',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'e',
  `pwd` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cardId` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'i',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'c',
  `role` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'r',
  PRIMARY KEY (`teacherId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20081011 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='t';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (20081001,'yzhou116@gmail.com','Walter Bald','School of Software ','M','13598458442','yzhou116@gmail.com','$2a$10$ozHTsgz3TOQke1kJeVl6S.zqPxZIU478I9Ul9Vbthl/rm8Pvg.Klm','423423283498','User','1'),(20081002,'yzhou117@gmail.com','Beluga Cat','School of Software ','M','1231221312','yzhou117@gmail.com','$2a$10$QkUelr8RRLGdqPaQ0vOKN.Shj/qhloTsALBdIqOdhlXTrzI4byUAq','24241234','User','1'),(20081003,'yzhou118@gmail.com','Alex Cool','Seneca','F','6478878878','yzhou118@gmail.com','Sd!4888202','None','User','2'),(20081007,'yzhou911@gmail.com','John Tory','Seneca','M','911','yzhou911@gmail.com','$2a$10$UPARIaUmWVokoynjP5Y.Au2WxJh.VRqrO2AknHNic1SNotXS63Bpm','129','User','1'),(20081010,'yzhou998@gmail.com','kk','aa','c','17292','u@ko.com','$2a$10$OrQnJqBf7R.M3qKwlj5psuKpGLzRQVSqHacpa9I.sUz0GRs5P2GHm','998','Noooo','2');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-18 22:57:19
