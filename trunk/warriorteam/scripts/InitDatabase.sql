-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le : Lun 20 Février 2012 à 19:16
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `warriorteam`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_categorie` varchar(15) NOT NULL,
  `dossier_fichiers` varchar(15) NOT NULL,
  `date_creation` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `categories`
--

INSERT INTO `categories` (`id`, `nom_categorie`, `dossier_fichiers`, `date_creation`) VALUES
(1, 'portugal', 'portugal_2011', '2012-02-01'),
(2, 'maroc', 'maroc_2010', '2012-02-15');

-- --------------------------------------------------------

--
-- Structure de la table `comptes`
--

CREATE TABLE IF NOT EXISTS `comptes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(90) NOT NULL,
  `nom` int(11) NOT NULL,
  `prenom` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `mail` int(11) NOT NULL,
  `date_inscription` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`,`mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `comptes`
--

INSERT INTO `comptes` (`id`, `login`, `password`, `nom`, `prenom`, `age`, `mail`, `date_inscription`) VALUES
(1, 'yvan', '098f6bcd4621d373cade4e832627b4f6', 1, 1, 24, 24, '2011-11-03');

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

CREATE TABLE IF NOT EXISTS `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie_fk` varchar(15) NOT NULL,
  `nom_image` varchar(90) NOT NULL,
  `posteur` varchar(30) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `image`
--

INSERT INTO `image` (`id`, `categorie_fk`, `nom_image`, `posteur`, `date`) VALUES
(1, '0', '061011_lapin_cretin_2 - Copie ', '1', '0000-00-00'),
(2, '0', '061011_lapin_cretin_2 - Copie ', '1', '0000-00-00'),
(3, 'maroc_2010', '061011_lapin_cretin_2 - Copie (6).jpg', '1', '2012-02-25');

-- --------------------------------------------------------

--
-- Structure de la table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `titre` text NOT NULL,
  `texte` text NOT NULL,
  `reservee` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `news`
--

INSERT INTO `news` (`id`, `date`, `titre`, `texte`, `reservee`) VALUES
(1, '2011-03-22 09:39:46', 'Autres expériences', '<ul>\r\n<li>Développer une application d’inscription et de gestion d’un service type Vélib (PHP, MySQL).</li>\r\n<li>Développer des programmes de simulation en langage C : <br/>\r\n    <b>Simulation du comportement d’une fourmilière.</b></li>\r\n\r\n<li>Conduite de projets informatiques : estimation de la charge, des risques et du délai.</li>\r\n</ul>', 0),
(2, '2011-03-22 09:40:06', 'Langues', '<ul>\r\n<li><span class="blanc_gras">Anglais:</span> bon niveau, IELTS (équivalent TOEIC) obtenu avec un score de 6 sur 9 (avril 2008).<br/>\r\n</li>\r\n<li><span class="blanc_gras">Espagnol:</span> courant</li>\r\n</ul>', 0),
(3, '2011-05-17 18:31:51', 'Un client réalisé en J2E pour jouer avec le machiabot', '<table>\r\n<tr><td>\r\n<span class="jaune_gras">18/03/2011 Le client est opérationnel </span><br /><br />\r\n\r\n<span class="jaune_gras">14h54 :</span> Le client est opérationnel avec le machiabot qui répond à la place des coups aléatoires En effet, après de longues heures de programmation, voici notre client qui est prêt. Il nous manque à améliorer la gestion des sessions, ensuite la gestion des couleurs,l''attente entre chaque coup la fin des parties,la beauté de l''IHM.\r\n<br /><br />\r\nNous sommes bel et bien dans les temps !!!\r\n<br /><br />\r\nJe joins les sources sur ce SVN.\r\n<span class="jaune_gras">18/03/2011 Amélioration des flux - redirection de l''entrée standard vers un InputStream? \r\n</span><br /><br />\r\nHier, l''entrée des coups était l''entrée standard ce qui posait problèmes car il fallait saisir des informations au clavier. Donc, nous venons de modifier la gestion du Thread qui concatène l''entrée de l''application externe (le machiabot) avec une entrée InputStream? du programme principal.\r\n<br /><br />\r\nBeaucoup de temps passé hier là dessus car il y avait beaucoup de bugs. Merci à ces sites qui m''ont bien aidés :\r\n<br /><br />\r\n<span class="jaune_gras">17/03/2011 Les Threads de gestion des flux fonctionnent !!! </span>\r\n<br /><br />\r\n<span class="jaune_gras">09:14 </span>Le combat avec la partie la plus dure et la plus délicate vient de s''achever. Heureusement, les vainqueurs sont "NOUS" !!! Une partie complète vient d''être jouée avec notre module de communication.\r\n<br /><br />\r\nAnalyse du problème qui persistait : il y avait un problème dans le flux d''envoi, il ne se fermait pas proprement ... Même pire, il était mal redirigé ! Ce qui provoquait une instabilité et un deadlock des Threads. D''où l''erreur qu''on avait souvent BrokenPipe car on saturait le flux. On était même obligé d''envoyer en boucle, ce qui n''est plus le cas !!\r\n<br /><br />\r\nNormalement, on devrait être dans les temps pour mardi 22/03. Maintenant, on va remplacer le module de gestionnaire qui génère des coups aléatoires par ce module de comm. Il y a quelques modifications assez lourdes à programmer.\r\n<br /><br />\r\nEn tout cas, l''aboutissement au fonctionnement du module de communication est la bonne nouvelle de la journée !\r\n<span class="jaune_gras">16/03/2011 Les sessions fonctionnent !!! </span><br /><br />\r\n\r\nAprès de longs moments de programmation JEE, les sessions fonctionnent. En effet, il faut se connecter pour commencer une partie, et si une partie est en cours on informe celui qui veut se connecter qu''une partie est en cours et il est redirigé vers une page d''attente où bientôt il pourra voir la partie en cours.\r\n<br /><br />\r\nA faire : légères finitions des sessions - gestion de la vue des parties en cours.\r\n<br /><br />\r\n<span class="jaune_gras">11h48 : </span>Découverte d''un petit bug à corriger, déconnection en cours de partie, il faut destroy() les servlets (au moins jouer.java) quand on se déconnecte avant la fin de partie. Idem pour le timeout (session se ferme naturellement ou par l''utilisateur il faut appeler destroy() de jouer.java).<br/><br/>\r\n<span class="jaune_gras">15/03/2011 Mise à jour des Threads </span>\r\n<br /><br />\r\nNous avons travaillé quelques heures cet après-midi pour avancer dans la programmation des threads, ils ne sont pas encore opérationnels mais cela nous a permit de comprendre sur quels points réfléchir.\r\n<br /><br />\r\nPour l''instant nous avons résolu quelques problèmes: le Machiabot est lancé, envoie un coup puis est fermé. Et même plus important: tous les thread sont fermés quand on sort du main (ce qui évite de saturer la mémoire de la machine quand on fait plusieurs parties comme nous l''avons constaté).\r\n<br /><br />\r\nL''objectif de demain est de se focaliser sur les sessions pour ne permettre qu''une partie à la fois.\r\n<br /><br />\r\n\r\nLe premier jet de l''application J2E est publié sur ce SVN depuis le 14/03/2011 matin. \r\n<br /><br />\r\n\r\n<span class="jaune_gras">14/03/2011 09:00 :\r\n<br /><br />\r\nLes différentes classes de l''application web sont disponibles.</span>\r\n<br /><br />\r\nPrincipales difficultés rencontrées:\r\n<br /><br />\r\n- La gestion du module de communication avec le machiabot : on rentre dans la programmation en JAVA de Threads(envoi, réception, lancement du machiabot) ainsi que la synchronisation des différentes méthodes de ces Threads en utilisant des mutex. Cela fait quelques temps que nous essayons d''améliorer ce module de communication. Nous espérons qu''il sera prêt le 22/03/2011 pour la prochaine réunion.\r\n<br /><br />\r\n- La programmation de l''application de jeu en ligne quant à elle est quasi prête. Elle fonctionne actuellement avec des coups aléatoires. Deux points sont à modifier pour que l''on ait un premier jet totalement opérationnel : la gestion des sessions (en cours, prêt pour le 22/03/2011) et le remplacement de la classe Gestionnaire (génère des coups aléatoires) par le module de communication avec le machiabot.\r\n<br /><br />\r\nCes deux points devraient être opérationnels le 22/03/2011.\r\n<br /><br />\r\nAprès cette date, nous nous consacrerons à des tests assidus pour s''assurer du bon fonctionnement du client, des bugs (il y en aura sûrement comme dans toute première version d''une appli), des exceptions auxquelles on aura pas pensé...\r\n<br /><br />\r\nEnsuite, une fois bien débuggé, nous nous consacrerons à la partie beauté de l''IHM ;) et la prise en compte des pondérations (affichage de 3 gobans, des explications de coups), puis pour finir de la gestion des parties en pause, avec le générateur .sgf\r\n<br /><br />\r\nProchain point le 22/03.\r\n<br /><br />\r\nProgramme du 14/03 au 22/03: Fin programmation module de comm (Threads), et fin programmation sessions.\r\n<br /><br />\r\n\r\nPrésents dans le commit : <br />\r\n<br />\r\nLes différentes classes de l''application web sont disponibles.\r\n<br /><br />\r\nPremier jet de l''application JEE. Il manque la gestion des sessions à mettre en place. La gestion des threads d''envoi et de réception est en cours d''amélioration\r\n<br /><br />\r\n-AppliMachiaConnect : client utilisant J2E manque les sessions.<br />Les fichiers argoUML de modélisation.<br />\r\n-AppliTestComm : module de communication avec le machiabot (gestion des Threads d''envoi et réception de coups). Ce module remplacera la classe Gestionnaire des modèles du client AppliMachiaConnect.\r\n<br />\r\n</td></tr>\r\n</table>', 0),
(4, '2011-05-17 18:32:10', 'Les sessions sont entièrement fonctionnelles', '<span class="jaune_gras">18/03/2011 16h45 :</span><br /><br />\r\n\r\n<table>\r\n<tr><td>\r\n\r\nLes sessions fonctionnent bien: un seul joueur à la fois, quand il se déconnecte un autre joueur peut prendre sa place et commencer une nouvelle partie. Et plus important: quand un joueur est déconnecté anormalement (fermeture du navigateur, connection avec le serveur interrompue, ...) un joueur peut se connecter après un temps donné (correspondant au time-out de la session).\r\n<br /><br />\r\nDès lundi nous pourrons intégrer ses sessions au client, il ne manquera plus qu''à s''occuper des détails pratiques: l''installation sur serveur, l''implémentation d''une IHM fonctionnelle ainsi que le codage du site en php pour pouvoir enregistrer des joueurs (et plus tard des parties).\r\n</td></tr></table> ', 0),
(5, '2011-05-17 18:32:03', 'Finalisation des sessions', '<span class="jaune_gras">21/03/2011 :</span><br /><br />\r\n\r\n<table><tr><td>\r\n\r\nRésolution du problème de sessions !!! La majorité des cas de figure se présentant lors d''une partie en cours est prise en compte (malveillance utilisateur)... On a géré la fermeture du thread du machiabot lors de l''abandon d''une partie. La servlet jouer a été repensée pour qu''elle gère le début et fin de parties.\r\n<br /><br />\r\nIl restera à tester au fur et à mesures les cas que nous aurons oublié (car il y en a sûrement au moins un !).\r\n<br /><br />\r\nLa servlet de gestion des sessions se connectera à une base de données (la même que le site sûrement) pour vérifier les noms d''utilisateurs et pass (identifiants de connexion)!!! \r\n\r\n</td></tr></table>\r\n', 0),
(6, '2011-05-17 18:31:29', 'Prochaines étapes', '\r\n<span class="jaune_gras">22/03/2011 :</span><br /><br />\r\n\r\n<table><tr><td>\r\n\r\nA l''heure actuelle nous allons nous concentrer sur les cas particuliers où l''application pourrait planter soit un arrêt fortuit soit une intervetion maladroite ou malveillante de l''utilisateur.<br /><br />\r\nLes prochaines étapes seront donc : amélioration de la servlet jouer pour gérer les cas particuliers.\r\nmise en forme avec des images de la JSP jeu.jsp\r\najout d''un compteur de temps entre chaque coup\r\nprise en compte du passage des tours\r\nimplémentation serveur E. Bourreau et réalisation site.\r\n</td></tr></table>', 0),
(7, '2011-05-17 18:31:13', 'Application opérationnelle pour la conférence à Paris du 13/04', '\r\n<span class="jaune_gras">12/04/2011 :</span><br /><br />\r\n\r\n<table><tr><td>\r\n\r\nL''application est entièrement fonctionnelle ! <br />\r\n\r\nNous avons réalisé divers tests surtout pour résoudre certains problèmes au niveau des sessions.\r\n\r\nMachiabot est enfin valorisé puisqu''il est possible d''y jouer en ligne au travers de la rubrique du menu gauche.\r\n\r\nDans un premier temps, merci d''utiliser le compte spécial : <br /><br />\r\nlogin : "invite"\r\npass : "invite"\r\n<br /><br/>\r\nLes comptes créés sur ce site seront liés avec l''application pour jouer en ligne prochainement.\r\n\r\n</td></tr></table>', 0),
(8, '2011-11-19 23:59:32', 'Reprise du site', 'Après de longs périples sans donner de nouvelles, le site est enfin mis à jour.\r\n<br /><br />\r\nL''application est en cours de ré-écriture en JAVA pour créer une Web Application et utiliser le plugin GWT.', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
