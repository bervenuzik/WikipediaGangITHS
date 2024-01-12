-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: wikipedia
-- ------------------------------------------------------
-- Server version	8.0.35-cluster

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `author_id` int NOT NULL,
  `category_id` int NOT NULL,
  `text` mediumtext NOT NULL,
  `num_views` int NOT NULL DEFAULT '0',
  `available_as_hard_copy` varchar(5) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'under review',
  PRIMARY KEY (`id`),
  KEY `author_id_idx` (`author_id`),
  KEY `theme_id_idx` (`category_id`),
  CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `theme_id` FOREIGN KEY (`category_id`) REFERENCES `article_category` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'The James Webb telescope took some stunning images in 2023',2,2,'If the first 18 months of JWST science are any indication,\nthe telescope could be ushering in a decades-long golden age for astronomy.\nHere’s just a few of the things JWST showed us in 2023.',4,'yes','publish'),(2,'How Emma Stone found her ‘most joyous role’ in ‘Poor Things’',3,1,'“We were working on Bella – the young Bella – and she was a bit nice,” he said.\n“And I was saying, ‘it’s weird, because kids aren’t like that.’”\nMcNamara told his director Yorgos Lanthimos about a trip to a restaurant with his three-year-old son,\n who became irritated with one of its youngest customers. “Kids are just instinct. They’re like,\n  ‘That’s annoying to me. I’m going to end it,’” the screenwriter recalled.',1,'yes','publish'),(3,'Experimental cancer vaccine, combined with immunotherapy, continues to show benefits against melanoma, trial shows',4,3,'At a three-year follow-up with trial participants who had had a stage III or IV melanoma fully removed\n but were at high risk of the cancer coming back, those who got the vaccine from Moderna and Merck along\n  with Merck’s Keytruda immunotherapy had a 49% lower risk of recurrence or death and a 62% lower risk\n   of distant tumor cell spread or death compared with those who got Keytruda alone, the companies said\n    in a news release.',2,'yes','publish'),(4,'How the impasse over Ukraine aid could have critical global ramifications',5,4,'Nearly two years into the war in Ukraine, a US lifeline of arms and ammunition is for the first time in\n real danger of collapsing, 12 months after President Volodymyr Zelensky was hailed as a hero during a\n  Christmas visit to Washington. The assumption lying behind Russian President Vladimir Putin’s bid to\n   wipe Ukraine off the map – that the US will lose interest in the war – is therefore close to being\n    validated. This could set off serious consequences that would shake the foundation of US global\n     leadership, alienate allies and embolden America’s sworn enemies.',4,'yes','publish'),(5,'Everything seems more expensive, so why is a big new TV cheaper than ever?',5,5,'As the materials used to build television sets have changed, so too has the manufacturing process. One of the biggest improvements to that process is a scientific development called “mother glass,” which is what has helped to produce  ever-larger TV screens. “The easiest way to lower the cost is to make them out of a larger starting piece of glass,” Gagnon said. “That’s why as TVs have gotten bigger, the price hasn’t really gone up.”',1,'yes','publish'),(6,'african scientist could wipe out malaria by editing mosquito dna',3,5,'abdoulaye diabate faced a life-threatening bout of malaria when he was just five years old. diabate narrowly survived the mosquito-borne disease, but cousins ages three and four were not as fortunate.',1,'yes','publish'),(7,'Test Article',3,3,'\nThis article has only unncessary content for testing :)\n',2,'yes','publish'),(9,'Hyperemesis: Scientists make pregnancy sickness cure breakthrough',7,3,'Scientists say they have discovered why some women become extremely sick during pregnancy, bringing them one step closer to a potential cure. They studied women at the Rosie Maternity Hospital in Cambridge and found those with a genetic variant putting them at a greater risk of HG had lower levels of the hormone, while women with the blood disorder beta thalassemia, which causes very high levels of GDF15 prior to pregnancy, experienced very little nausea or vomiting.',3,'yes','publish'),(10,'Can signs of life be detected from Saturn\'s frigid moon?',3,2,'Now researchers from the University of California San Diego have shown unambiguous laboratory evidence that amino acids transported in these ice plumes can survive impact speeds of up to 4.2 km/s, supporting their detection during sampling by spacecraft. Their findings appear in The Proceedings of the National Academy of Sciences (PNAS).Beginning in 2012, UC San Diego Distinguished Professor of Chemistry and Biochemistry Robert Continetti and his co-workers custom-built a unique aerosol impact spectrometer, designed to study collision dynamics of single aerosols and particles at high velocities. Although not built specifically to study ice grain impacts, it turned out to be exactly the right machine to do so.Now researchers from the University of California San Diego have shown unambiguous laboratory evidence that amino acids transported in these ice plumes can survive impact speeds of up to 4.2 km/s, supporting their detection during sampling by spacecraft. Their findings appear in The Proceedings of the National Academy of Sciences (PNAS).Beginning in 2012, UC San Diego Distinguished Professor of Chemistry and Biochemistry Robert Continetti and his co-workers custom-built a unique aerosol impact spectrometer, designed to study collision dynamics of single aerosols and particles at high velocities. Although not built specifically to study ice grain impacts, it turned out to be exactly the right machine to do so. ',4,'yes','publish'),(13,'6 bean dip recipes, including cheesy, spicy and sweet',5,3,'If I’m lucky, my end-of-the-year routine is a delightful mix of lounging around\nmy apartment and spending time with loved ones. That leads to lots of fending,\n grazing or whatever else you want to call the lazy eating that takes place the\n\nweek between Christmas and New Year’s when I tend to throw my normal eating\nhabits to the wind in favor of charcuterie boards, cake, pie and cookies for\nbreakfast, and all manner of snacking. Enter: dips.\n\nBelow is a selection of dip recipes that stars beans. (Which means they offer\nsome nutritional value to help balance the other celebratory foods of the \nseason.) I’ve curated recipes with different types of beans, served at varying\ntemperatures and with a range of flavor profiles. \n',12,'yes','publish'),(14,'Stunning new images reveal ‘Christmas Tree Cluster’ and celestial snow globe in space',6,2,'\nGroupings of stars resembling a Christmas tree aglow with lights and a scintillating\nsnow globe shine in new observations taken by the Chandra X-ray Observatory and\nthe Hubble Space Telescope, respectively.\n\nNGC 2264, which is about 2,500 light-years from Earth, is also called the “Christmas\nTree Cluster,” where a group of young stars surrounded by the gaseous cloud of a\nnebula call to mind a cosmic evergreen decorated with twinkling lights.\n',10,'yes','publish'),(15,'Why cooking food in stoneware is a healthy practice',2,7,'In the age of ceramic, glassware, and non-sticks, cooking food in dull-looking\nstoneware might sound bizarre. But if you look at the nutritional benefits and\n its other properties, you might find it pleasing enough to make a switch. If\nfood historians are to be believed, centuries ago, stoneware was the only medium\nto cook food, and people sweared by it for its ability to enhance flavors, retain\nheat, and provide a natural, chemical-free cooking surface. In this article, we\nwill tell you the many reasons to make a wise switch to stoneware.\n',1,'yes','publish'),(16,'Test Article 2',2,8,'test content\n',1,'yes','publish'),(17,'How can I get the most out of my new air fryer?',6,7,'According to a report by Lakeland, 45% of UK households now own an air\nfryer, and for Poppy O’Toole it’s not hard to see why. “They really are the\nanswer to ease and comfort in the kitchen,” says the author of Poppy\nCooks: The Actually Delicious Air Fryer Cookbook. “There’s less washing\nup, less faff, and you don’t compromise on taste.” In fact, as hot air\ncirculates around whatever you’re cooking, you’ll get pleasingly crisp\nresults, and with “hardly any oil, so it’s a healthy way to cook, too”, adds\nHayley Dean, author of How to Make Anything in an Air Fryer. \n\n Perhaps the biggest win of all, though, is how efficient air fryers are. \n“They can do all the things you can do in an oven, but super-fast and \neconomically,” says Niki Webster, author of The Vegan Air Fryer. “You \n can roast vegetables in 20 minutes rather than an hour.”\n',3,'no','publish'),(18,'Fruit and Vegetable Juice: How Food Affects Health',7,7,'Fruits and vegetables are “juicy foods” that consist mostly of water, \nbut they also provide a variety of vitamins, minerals, phytonutrients,\nand a good amount of fiber that helps fill you up. Juice is another\nstory. When whole produce is processed into juice most of the fiber is\nlost and you’re left with a less nutritious end product. And consider \nthis: It takes a couple minutes to eat a 60-calorie orange but only a\ncouple seconds to guzzle down a 110-calorie glass of OJ.\n\nFruit juice, in particular, is a highly concentrated source of fruit\nsugar. This can raise your blood sugar quickly, and that’s why juice\nis not recommended for people with type 2 diabetes. Individuals with\nhigh triglycerides should avoid fruit juice as well, as its \nconcentrated simple sugars can raise triglyceride levels even higher.\n\nFruit drinks — not to be confused with 100 percent juices — are an\neven worse choice because they contain added sugars and less\nnutrition.\n',4,'no','review'),(19,'BYD Overtakes Tesla, With Other Chinese EV Makers Close Behind',6,6,'BYD has overtaken Tesla as the world’s biggest selling electric vehicle maker,\nand other Chinese manufacturers will soon join it as they lead the electric revolution\n at the expense of their Western competitors.\n\n“We believe BYD and other leading Chinese [manufacturers] are set to conquer the world\nmarket with high-tech, low-cost EVs for the masses, hereby accelerating global EV\nadoption,” investment bank UBS said in a report.\n\nUBS and other experts said only Tesla can keep pace with the Chinese.\n\nEurope will be the main target and exclude the U.S., at least for now, according to\nthe Wall Street Journal.\n\nBYD accelerated past Tesla to claim the title of the world’s biggest seller of EVs\n in 2023’s fourth quarter, selling about 530,000, beating Tesla’s 485,000.\n',3,'no','review'),(20,'7 Potential Health Benefits of HIIT',5,8,'Are you familiar with the saying “Less is more”? In the realm of fitness, high-\nintensity interval training (HIIT) is a prime example. These workouts, defined\nby alternating short bursts of vigorous exercise with brief rest periods, pack\nan impressive number of health benefits into a small time frame — sometimes as\nlittle as four minutes!\n\nBecause HIIT is more intense than other workout types, it may not be appropriate\nfor everyone. Check with your doctor first if you are new to exercise, are\nrecovering from injury, or have a medical condition like an uncontrolled heart\nrate (known as arrhythmia), diabetes, or diabetes complications like retinopathy,\nper the Harvard T.H. Chan School of Public Health.\n',0,'no','review');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_borrower_info`
--

DROP TABLE IF EXISTS `article_borrower_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_borrower_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_hard_copy_id` int NOT NULL,
  `borrower_id` int NOT NULL,
  `borrow_date` date NOT NULL,
  `expected_return_date` date DEFAULT NULL,
  `actual_return_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `borrower_id_idx` (`borrower_id`),
  KEY `article_hard_copy_id_idx` (`article_hard_copy_id`),
  CONSTRAINT `article_hard_copy_id` FOREIGN KEY (`article_hard_copy_id`) REFERENCES `article_hard_copy` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrower_id2` FOREIGN KEY (`borrower_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_borrower_info`
--

LOCK TABLES `article_borrower_info` WRITE;
/*!40000 ALTER TABLE `article_borrower_info` DISABLE KEYS */;
INSERT INTO `article_borrower_info` VALUES (63,62,4,'2024-01-03','2024-01-17','2024-01-04'),(64,63,2,'2024-01-03','2024-01-17','2024-01-04'),(65,64,2,'2024-01-03','2024-01-17','2024-01-04'),(66,65,2,'2024-01-03','2024-01-17',NULL),(67,66,2,'2024-01-03','2024-01-17',NULL),(68,67,2,'2024-01-03','2024-01-17',NULL),(69,68,2,'2024-01-03','2024-01-17','2024-01-04'),(70,69,7,'2024-01-03','2024-01-17',NULL),(71,70,7,'2024-01-03','2024-01-17',NULL),(72,71,7,'2024-01-03','2024-01-17',NULL),(73,72,7,'2024-01-03','2024-01-17',NULL),(74,73,3,'2024-01-03','2024-01-17',NULL),(75,74,3,'2024-01-03','2024-01-17',NULL),(76,75,3,'2024-01-03','2024-01-17',NULL),(77,76,3,'2024-01-03','2024-01-17',NULL),(78,77,6,'2024-01-04','2024-01-18',NULL),(79,78,6,'2024-01-04','2024-01-18',NULL),(80,79,6,'2024-01-04','2024-01-18',NULL),(81,80,6,'2024-01-04','2024-01-18',NULL),(82,81,6,'2024-01-04','2024-01-18',NULL),(83,82,5,'2024-01-04','2024-01-18',NULL),(84,83,5,'2024-01-04','2024-01-18',NULL),(85,84,5,'2024-01-04','2024-01-18',NULL),(86,85,4,'2024-01-04','2024-01-18',NULL),(87,86,4,'2024-01-04','2024-01-18',NULL),(88,87,4,'2024-01-04','2024-01-18',NULL),(89,62,2,'2024-01-04','2024-01-18',NULL),(90,63,6,'2024-01-04','2024-01-18',NULL),(91,64,5,'2024-01-04','2024-01-18',NULL),(92,68,5,'2024-01-04','2024-01-18',NULL),(93,88,7,'2024-01-08',NULL,NULL),(94,89,7,'2024-01-08',NULL,NULL),(95,90,7,'2024-01-08',NULL,NULL),(96,91,7,'2024-01-08',NULL,NULL),(97,92,7,'2024-01-08',NULL,NULL),(98,93,7,'2024-01-08',NULL,NULL);
/*!40000 ALTER TABLE `article_borrower_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_category`
--

DROP TABLE IF EXISTS `article_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_category`
--

LOCK TABLES `article_category` WRITE;
/*!40000 ALTER TABLE `article_category` DISABLE KEYS */;
INSERT INTO `article_category` VALUES (1,'Entertainment'),(2,'Space'),(3,'Health & Medicine'),(4,'Politics'),(5,'Business'),(6,'Lifestyle'),(7,'Food'),(8,'Fitness');
/*!40000 ALTER TABLE `article_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_hard_copy`
--

DROP TABLE IF EXISTS `article_hard_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_hard_copy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'available',
  `date_created` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_id3_idx` (`article_id`),
  CONSTRAINT `article_id3` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_hard_copy`
--

LOCK TABLES `article_hard_copy` WRITE;
/*!40000 ALTER TABLE `article_hard_copy` DISABLE KEYS */;
INSERT INTO `article_hard_copy` VALUES (62,1,'reserved','2024-01-03'),(63,1,'reserved','2024-01-03'),(64,1,'reserved','2024-01-03'),(65,6,'reserved','2024-01-03'),(66,2,'reserved','2024-01-03'),(67,14,'reserved','2024-01-03'),(68,15,'reserved','2024-01-03'),(69,15,'reserved','2024-01-03'),(70,16,'reserved','2024-01-03'),(71,14,'reserved','2024-01-03'),(72,9,'reserved','2024-01-03'),(73,9,'reserved','2024-01-03'),(74,10,'reserved','2024-01-03'),(75,16,'reserved','2024-01-03'),(76,13,'reserved','2024-01-03'),(77,10,'reserved','2024-01-04'),(78,15,'reserved','2024-01-04'),(79,5,'reserved','2024-01-04'),(80,2,'reserved','2024-01-04'),(81,16,'reserved','2024-01-04'),(82,2,'reserved','2024-01-04'),(83,14,'reserved','2024-01-04'),(84,10,'reserved','2024-01-04'),(85,4,'reserved','2024-01-04'),(86,9,'reserved','2024-01-04'),(87,13,'reserved','2024-01-04'),(88,10,'personal','2024-01-08'),(89,10,'personal','2024-01-08'),(90,10,'personal','2024-01-08'),(91,10,'personal','2024-01-08'),(92,10,'personal','2024-01-08'),(93,10,'personal','2024-01-08');
/*!40000 ALTER TABLE `article_hard_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_reservation_queue`
--

DROP TABLE IF EXISTS `article_reservation_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_reservation_queue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `borrower_id` int NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_id3_idx` (`article_id`),
  KEY `borrower_id2_idx` (`borrower_id`),
  CONSTRAINT `article_id4` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `borrower_id3` FOREIGN KEY (`borrower_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_reservation_queue`
--

LOCK TABLES `article_reservation_queue` WRITE;
/*!40000 ALTER TABLE `article_reservation_queue` DISABLE KEYS */;
INSERT INTO `article_reservation_queue` VALUES (25,16,5,'2024-01-04 12:34:52'),(28,10,4,'2024-01-04 13:45:26'),(29,15,4,'2024-01-04 13:47:00'),(30,10,7,'2024-01-08 14:19:42');
/*!40000 ALTER TABLE `article_reservation_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `person_id` int NOT NULL,
  `article_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `person_id_idx` (`person_id`),
  KEY `article_id2_idx` (`article_id`),
  CONSTRAINT `article_id2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `person_id2` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Wow! Was looking for such healthy recipes :)',2,13,'2024-01-03'),(2,'Easy-peasy!',2,13,'2024-01-03'),(3,'\rThis seems interesting tbh!! \r ',6,1,'2024-01-04'),(4,'\nfsefesfesfsefsefesfesfsefse   efefse\n\nthe-end\n\n',7,10,'2024-01-08'),(5,'\nDet var v\'ldigt bra jobbat\n',7,17,'2024-01-11');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error_log`
--

DROP TABLE IF EXISTS `error_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `error_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(100) NOT NULL,
  `person_id` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `person_id_idx` (`person_id`),
  CONSTRAINT `person_id3` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error_log`
--

LOCK TABLES `error_log` WRITE;
/*!40000 ALTER TABLE `error_log` DISABLE KEYS */;
INSERT INTO `error_log` VALUES (1,'There was not found a default user',7,'2024-01-08'),(2,'User trying to create a user without corresponding rights',7,'2024-01-08'),(3,'Try to delete defaultuser',7,'2024-01-09'),(4,'Try to delete defaultuser',7,'2024-01-09');
/*!40000 ALTER TABLE `error_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_info`
--

DROP TABLE IF EXISTS `login_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_info`
--

LOCK TABLES `login_info` WRITE;
/*!40000 ALTER TABLE `login_info` DISABLE KEYS */;
INSERT INTO `login_info` VALUES (1,'Svea1','Ss1'),(2,'Hayud1','Hayud11'),(3,'denis','denMaster2'),(4,'Hayud','taysoN007'),(5,'Budail','Marvel112'),(6,'Svea','OdinGud2'),(9,'def','zxncmNnjfsnkNJKfnjksnJNKn23');
/*!40000 ALTER TABLE `login_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `type_id` int NOT NULL,
  `login_info` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id_idx` (`type_id`),
  KEY `login_info_idx` (`login_info`),
  CONSTRAINT `login_info` FOREIGN KEY (`login_info`) REFERENCES `login_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (2,'Hayud','Nazari','HayudFadilahNazari@teleworm.se',2,2),(3,'Svea','Oster','svea.oster@mailinator.com',2,6),(4,'Hayud','Nabilah Masih','HayudNabilahMasih@dayrep.com',2,4),(5,'Budail','Guirguis','BudailWaliyGuirguis@dayrep.com',2,5),(6,'Anton','Todd','dennis@test.com',2,3),(7,'Changed','Oster','svea@something.se',1,1),(9,'default','default','default@mail.com',2,9);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_words`
--

DROP TABLE IF EXISTS `search_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `search_words` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_words`
--

LOCK TABLES `search_words` WRITE;
/*!40000 ALTER TABLE `search_words` DISABLE KEYS */;
INSERT INTO `search_words` VALUES (5,'The',26),(6,'James',23),(7,'Webb',23),(8,'telescope',23),(9,'took',23),(10,'some',23),(11,'stunning',30),(12,'images',30),(13,'in',49),(14,'2023',23),(15,'Experimental',3),(16,'cancer',3),(17,'vaccine,',3),(18,'combined',3),(19,'with',3),(20,'immunotherapy,',3),(21,'continues',3),(22,'to',3),(23,'show',3),(24,'benefits',3),(25,'against',3),(26,'melanoma,',3),(27,'trial',3),(28,'shows',3),(29,'Hyperemesis:',11),(30,'Scientists',11),(31,'make',11),(32,'pregnancy',11),(33,'sickness',11),(34,'cure',11),(35,'breakthrough',11),(36,'How',18),(37,'Emma',13),(38,'Stone',13),(39,'found',13),(40,'her',13),(41,'‘most',13),(42,'joyous',13),(43,'role’',13),(44,'‘Poor',13),(45,'Things’',13),(46,'Everything',10),(47,'seems',10),(48,'more',10),(49,'expensive,',10),(50,'so',10),(51,'why',16),(52,'is',16),(53,'a',16),(54,'big',10),(55,'new',19),(56,'TV',10),(57,'cheaper',10),(58,'than',10),(59,'ever?',10),(60,'african',2),(61,'scientist',2),(62,'could',3),(63,'wipe',2),(64,'out',4),(65,'malaria',2),(66,'by',2),(67,'editing',2),(68,'mosquito',2),(69,'dna',2),(70,'Test',6),(71,'Article',6),(72,'Hello',4),(73,'Can',13),(74,'signs',11),(75,'of',13),(76,'life',11),(77,'be',11),(78,'detected',11),(79,'from',11),(80,'Saturn\'s',11),(81,'frigid',11),(82,'moon?',11),(83,'reveal',7),(84,'‘Christmas',7),(85,'Tree',7),(86,'Cluster’',7),(87,'and',10),(88,'celestial',7),(89,'snow',7),(90,'globe',7),(91,'space',7),(92,'2',7),(93,'cooking',6),(94,'food',6),(95,'stoneware',6),(96,'healthy',6),(97,'practice',6),(98,'6',3),(99,'bean',3),(100,'dip',3),(101,'recipes,',3),(102,'including',3),(103,'cheesy,',3),(104,'spicy',3),(105,'sweet',3),(106,'fasfasdf',1),(107,'1',1),(108,'I',2),(109,'get',2),(110,'most',2),(111,'my',2),(112,'air',2),(113,'fryer?',2),(114,'Svea',3),(115,'Oster',3),(116,'dennis',2),(117,'todd',2),(118,'impasse',1),(119,'over',1),(120,'Ukraine',1),(121,'aid',1),(122,'have',1),(123,'critical',1),(124,'global',1),(125,'ramifications',1),(126,'Anton',1);
/*!40000 ALTER TABLE `search_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-12  9:06:09
