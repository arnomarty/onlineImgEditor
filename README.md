# Projet de L3 - onlineImgEditor

## Résumé

> L'objectif est de mettre en place une application client-serveur d'édition d'images basique. Ce fichier README servira à suivre l'avancement du développement en fonction des spécifications demandées dans le cahier des charges.

------------------------------

## Côté serveur (backend)

* [x] Initialiser un ensemble d'images sur le serveur
  * Enregistre les images présentes dans le dossier `images` au lancement de l'application 
  * Images aux formats JPEG et TIFF
  * Erreur si le dossier `images` n'est pas présent

* [ ] Gérer les images présentes sur le serveur
  * Le serveur peut accéder/supprimer une image via son identifiant
  * Le serveur peut ajouter une image, et construire la liste des images disponibles ({id, nom, taille, format})
  
* [ ] Appliquer un algorithme de traitement d'image 

---------------

## Communication client/serveur

* [ ] Transférer la liste des images existantes
  * `GET /images` retourne la liste des images sur le serveur au format JSON: `[ {id, name, type, size} ]

* [ ] Ajout d'image
  * `POST /images` ajoute l'image spécifiée au serveur

* [ ] Récupération d'image
  * `GET images/id` renvoie l'image dont l'ID a été passé en paramètres 
  * Image retournée dans le corps de la réponse

* [ ] Suppression d'image
  * `DELETE /images/id` efface l'image stockée sous l'identifiant `id`

* [ ] Éxécution d'algorithmes
  * `GET /images/id?algorithm=X&p1=Y&p2=Z` retourne le résultat de l'algorithme **X** avec **Y** et **Z** en paramètres.
  * Gestion d'erreurs (`200 OK`, `400 BAD REQUEST`, `404 NOT FOUND`, `500 INTERNAL ERROR`)


---------------

## Côté client

* [ ] Parcourir les images disponibles sur le serveur
  * Sous forme de carousel ou de gallerie d'image

* [ ] Sélectionner une image, et y appliquer un effet
  * Lorsque l'utilisateur clique sur une image dans le carousel, elle est affichée.
  * L'utilisateur peut voir les détails de l'image et choisir un traitement à appliquer

* [ ] Enregistrer une image
  * L'utilisateur peut enregistrer une image, avec ou sans effet

* [ ] Uploader une image
  * L'utilisateur peut uploader une image sur le serveur (non-persistant)

* [ ] Supprimer une image 

---------------

## Algorithmes de traitement d'image

> *À venir*
