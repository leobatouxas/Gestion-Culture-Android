<?php
function connexionPDO(){
    $login = "";
    $mdp = "";
    $bd = "";
    $serveur = "";
    try {
        $conn = new PDO("mysql:host=$serveur;dbname=$bd", $login, $mdp);
        return $conn;
    }catch(PDOException $e) {
        print "Erreur de connexion PDO";
        die();
    }
}