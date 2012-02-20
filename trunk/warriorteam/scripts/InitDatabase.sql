-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- G�n�r� le : Lun 20 F�vrier 2012 � 19:16
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de donn�es: `warriorteam`
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
(1, '2011-03-22 09:39:46', 'Autres exp�riences', '<ul>\r\n<li>D�velopper une application d�inscription et de gestion d�un service type V�lib (PHP, MySQL).</li>\r\n<li>D�velopper des programmes de simulation en langage C : <br/>\r\n    <b>Simulation du comportement d�une fourmili�re.</b></li>\r\n\r\n<li>Conduite de projets informatiques : estimation de la charge, des risques et du d�lai.</li>\r\n</ul>', 0),
(2, '2011-03-22 09:40:06', 'Langues', '<ul>\r\n<li><span class="blanc_gras">Anglais:</span> bon niveau, IELTS (�quivalent TOEIC) obtenu avec un score de 6 sur 9 (avril 2008).<br/>\r\n</li>\r\n<li><span class="blanc_gras">Espagnol:</span> courant</li>\r\n</ul>', 0),
(3, '2011-05-17 18:31:51', 'Un client r�alis� en J2E pour jouer avec le machiabot', '<table>\r\n<tr><td>\r\n<span class="jaune_gras">18/03/2011 Le client est op�rationnel </span><br /><br />\r\n\r\n<span class="jaune_gras">14h54 :</span> Le client est op�rationnel avec le machiabot qui r�pond � la place des coups al�atoires En effet, apr�s de longues heures de programmation, voici notre client qui est pr�t. Il nous manque � am�liorer la gestion des sessions, ensuite la gestion des couleurs,l''attente entre chaque coup la fin des parties,la beaut� de l''IHM.\r\n<br /><br />\r\nNous sommes bel et bien dans les temps !!!\r\n<br /><br />\r\nJe joins les sources sur ce SVN.\r\n<span class="jaune_gras">18/03/2011 Am�lioration des flux - redirection de l''entr�e standard vers un InputStream? \r\n</span><br /><br />\r\nHier, l''entr�e des coups �tait l''entr�e standard ce qui posait probl�mes car il fallait saisir des informations au clavier. Donc, nous venons de modifier la gestion du Thread qui concat�ne l''entr�e de l''application externe (le machiabot) avec une entr�e InputStream? du programme principal.\r\n<br /><br />\r\nBeaucoup de temps pass� hier l� dessus car il y avait beaucoup de bugs. Merci � ces sites qui m''ont bien aid�s :\r\n<br /><br />\r\n<span class="jaune_gras">17/03/2011 Les Threads de gestion des flux fonctionnent !!! </span>\r\n<br /><br />\r\n<span class="jaune_gras">09:14 </span>Le combat avec la partie la plus dure et la plus d�licate vient de s''achever. Heureusement, les vainqueurs sont "NOUS" !!! Une partie compl�te vient d''�tre jou�e avec notre module de communication.\r\n<br /><br />\r\nAnalyse du probl�me qui persistait : il y avait un probl�me dans le flux d''envoi, il ne se fermait pas proprement ... M�me pire, il �tait mal redirig� ! Ce qui provoquait une instabilit� et un deadlock des Threads. D''o� l''erreur qu''on avait souvent BrokenPipe car on saturait le flux. On �tait m�me oblig� d''envoyer en boucle, ce qui n''est plus le cas !!\r\n<br /><br />\r\nNormalement, on devrait �tre dans les temps pour mardi 22/03. Maintenant, on va remplacer le module de gestionnaire qui g�n�re des coups al�atoires par ce module de comm. Il y a quelques modifications assez lourdes � programmer.\r\n<br /><br />\r\nEn tout cas, l''aboutissement au fonctionnement du module de communication est la bonne nouvelle de la journ�e !\r\n<span class="jaune_gras">16/03/2011 Les sessions fonctionnent !!! </span><br /><br />\r\n\r\nApr�s de longs moments de programmation JEE, les sessions fonctionnent. En effet, il faut se connecter pour commencer une partie, et si une partie est en cours on informe celui qui veut se connecter qu''une partie est en cours et il est redirig� vers une page d''attente o� bient�t il pourra voir la partie en cours.\r\n<br /><br />\r\nA faire : l�g�res finitions des sessions - gestion de la vue des parties en cours.\r\n<br /><br />\r\n<span class="jaune_gras">11h48 : </span>D�couverte d''un petit bug � corriger, d�connection en cours de partie, il faut destroy() les servlets (au moins jouer.java) quand on se d�connecte avant la fin de partie. Idem pour le timeout (session se ferme naturellement ou par l''utilisateur il faut appeler destroy() de jouer.java).<br/><br/>\r\n<span class="jaune_gras">15/03/2011 Mise � jour des Threads </span>\r\n<br /><br />\r\nNous avons travaill� quelques heures cet apr�s-midi pour avancer dans la programmation des threads, ils ne sont pas encore op�rationnels mais cela nous a permit de comprendre sur quels points r�fl�chir.\r\n<br /><br />\r\nPour l''instant nous avons r�solu quelques probl�mes: le Machiabot est lanc�, envoie un coup puis est ferm�. Et m�me plus important: tous les thread sont ferm�s quand on sort du main (ce qui �vite de saturer la m�moire de la machine quand on fait plusieurs parties comme nous l''avons constat�).\r\n<br /><br />\r\nL''objectif de demain est de se focaliser sur les sessions pour ne permettre qu''une partie � la fois.\r\n<br /><br />\r\n\r\nLe premier jet de l''application J2E est publi� sur ce SVN depuis le 14/03/2011 matin. \r\n<br /><br />\r\n\r\n<span class="jaune_gras">14/03/2011 09:00 :\r\n<br /><br />\r\nLes diff�rentes classes de l''application web sont disponibles.</span>\r\n<br /><br />\r\nPrincipales difficult�s rencontr�es:\r\n<br /><br />\r\n- La gestion du module de communication avec le machiabot : on rentre dans la programmation en JAVA de Threads(envoi, r�ception, lancement du machiabot) ainsi que la synchronisation des diff�rentes m�thodes de ces Threads en utilisant des mutex. Cela fait quelques temps que nous essayons d''am�liorer ce module de communication. Nous esp�rons qu''il sera pr�t le 22/03/2011 pour la prochaine r�union.\r\n<br /><br />\r\n- La programmation de l''application de jeu en ligne quant � elle est quasi pr�te. Elle fonctionne actuellement avec des coups al�atoires. Deux points sont � modifier pour que l''on ait un premier jet totalement op�rationnel : la gestion des sessions (en cours, pr�t pour le 22/03/2011) et le remplacement de la classe Gestionnaire (g�n�re des coups al�atoires) par le module de communication avec le machiabot.\r\n<br /><br />\r\nCes deux points devraient �tre op�rationnels le 22/03/2011.\r\n<br /><br />\r\nApr�s cette date, nous nous consacrerons � des tests assidus pour s''assurer du bon fonctionnement du client, des bugs (il y en aura s�rement comme dans toute premi�re version d''une appli), des exceptions auxquelles on aura pas pens�...\r\n<br /><br />\r\nEnsuite, une fois bien d�bugg�, nous nous consacrerons � la partie beaut� de l''IHM ;) et la prise en compte des pond�rations (affichage de 3 gobans, des explications de coups), puis pour finir de la gestion des parties en pause, avec le g�n�rateur .sgf\r\n<br /><br />\r\nProchain point le 22/03.\r\n<br /><br />\r\nProgramme du 14/03 au 22/03: Fin programmation module de comm (Threads), et fin programmation sessions.\r\n<br /><br />\r\n\r\nPr�sents dans le commit : <br />\r\n<br />\r\nLes diff�rentes classes de l''application web sont disponibles.\r\n<br /><br />\r\nPremier jet de l''application JEE. Il manque la gestion des sessions � mettre en place. La gestion des threads d''envoi et de r�ception est en cours d''am�lioration\r\n<br /><br />\r\n-AppliMachiaConnect : client utilisant J2E manque les sessions.<br />Les fichiers argoUML de mod�lisation.<br />\r\n-AppliTestComm : module de communication avec le machiabot (gestion des Threads d''envoi et r�ception de coups). Ce module remplacera la classe Gestionnaire des mod�les du client AppliMachiaConnect.\r\n<br />\r\n</td></tr>\r\n</table>', 0),
(4, '2011-05-17 18:32:10', 'Les sessions sont enti�rement fonctionnelles', '<span class="jaune_gras">18/03/2011 16h45 :</span><br /><br />\r\n\r\n<table>\r\n<tr><td>\r\n\r\nLes sessions fonctionnent bien: un seul joueur � la fois, quand il se d�connecte un autre joueur peut prendre sa place et commencer une nouvelle partie. Et plus important: quand un joueur est d�connect� anormalement (fermeture du navigateur, connection avec le serveur interrompue, ...) un joueur peut se connecter apr�s un temps donn� (correspondant au time-out de la session).\r\n<br /><br />\r\nD�s lundi nous pourrons int�grer ses sessions au client, il ne manquera plus qu''� s''occuper des d�tails pratiques: l''installation sur serveur, l''impl�mentation d''une IHM fonctionnelle ainsi que le codage du site en php pour pouvoir enregistrer des joueurs (et plus tard des parties).\r\n</td></tr></table> ', 0),
(5, '2011-05-17 18:32:03', 'Finalisation des sessions', '<span class="jaune_gras">21/03/2011 :</span><br /><br />\r\n\r\n<table><tr><td>\r\n\r\nR�solution du probl�me de sessions !!! La majorit� des cas de figure se pr�sentant lors d''une partie en cours est prise en compte (malveillance utilisateur)... On a g�r� la fermeture du thread du machiabot lors de l''abandon d''une partie. La servlet jouer a �t� repens�e pour qu''elle g�re le d�but et fin de parties.\r\n<br /><br />\r\nIl restera � tester au fur et � mesures les cas que nous aurons oubli� (car il y en a s�rement au moins un !).\r\n<br /><br />\r\nLa servlet de gestion des sessions se connectera � une base de donn�es (la m�me que le site s�rement) pour v�rifier les noms d''utilisateurs et pass (identifiants de connexion)!!! \r\n\r\n</td></tr></table>\r\n', 0),
(6, '2011-05-17 18:31:29', 'Prochaines �tapes', '\r\n<span class="jaune_gras">22/03/2011 :</span><br /><br />\r\n\r\n<table><tr><td>\r\n\r\nA l''heure actuelle nous allons nous concentrer sur les cas particuliers o� l''application pourrait planter soit un arr�t fortuit soit une intervetion maladroite ou malveillante de l''utilisateur.<br /><br />\r\nLes prochaines �tapes seront donc : am�lioration de la servlet jouer pour g�rer les cas particuliers.\r\nmise en forme avec des images de la JSP jeu.jsp\r\najout d''un compteur de temps entre chaque coup\r\nprise en compte du passage des tours\r\nimpl�mentation serveur E. Bourreau et r�alisation site.\r\n</td></tr></table>', 0),
(7, '2011-05-17 18:31:13', 'Application op�rationnelle pour la conf�rence � Paris du 13/04', '\r\n<span class="jaune_gras">12/04/2011 :</span><br /><br />\r\n\r\n<table><tr><td>\r\n\r\nL''application est enti�rement fonctionnelle ! <br />\r\n\r\nNous avons r�alis� divers tests surtout pour r�soudre certains probl�mes au niveau des sessions.\r\n\r\nMachiabot est enfin valoris� puisqu''il est possible d''y jouer en ligne au travers de la rubrique du menu gauche.\r\n\r\nDans un premier temps, merci d''utiliser le compte sp�cial : <br /><br />\r\nlogin : "invite"\r\npass : "invite"\r\n<br /><br/>\r\nLes comptes cr��s sur ce site seront li�s avec l''application pour jouer en ligne prochainement.\r\n\r\n</td></tr></table>', 0),
(8, '2011-11-19 23:59:32', 'Reprise du site', 'Apr�s de longs p�riples sans donner de nouvelles, le site est enfin mis � jour.\r\n<br /><br />\r\nL''application est en cours de r�-�criture en JAVA pour cr�er une Web Application et utiliser le plugin GWT.', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
