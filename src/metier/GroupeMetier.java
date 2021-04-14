package metier;

import donnees.GroupeDAO;
import entite.Groupe;
import entite.Heros;
import entite.Vilain;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GroupeMetier {
    protected Groupe groupe;

    public GroupeMetier() {}

    /**
     * Permet de récupérer un groupe par son ID.
     * @param id [int] L'ID du groupe.
     * @return [Groupe] Un objet de type Groupe.
     */
    public Groupe getGroupeById(int id) {
        GroupeDAO groupeDAO = new GroupeDAO();
        Groupe groupe = new Groupe();
        groupe = groupeDAO.findById(id);
        return groupe;
    }

    /**
     * Permet d'afficher les détails d'un groupe.
     * @param groupe [Groupe] Un objet de type Groupe.
     */
    public void showGroupeForUpdate(Groupe groupe) {
        System.out.println("Nom : " + groupe.getNom());

        System.out.println("Liste des héros :");
        ArrayList<Heros> listeHeros = groupe.getListeHeros();
        for (int i = 0; i < listeHeros.size(); i++) {
            System.out.println("- Id. : " + listeHeros.get(i).getSuperPersonnageId() + ", nom : " + listeHeros.get(i).getNom());
        }

        System.out.println("Liste des vilains :");
        ArrayList<Vilain> listeVilains = groupe.getListeVilains();
        for (int i = 0; i < listeVilains.size(); i++) {
            System.out.println("- Id. : " + listeVilains.get(i).getSuperPersonnageId() + ", nom : " + listeVilains.get(i).getNom());
        }
    }

    /**
     * Permet de créer un affichage d'une liste de héros, utilisée dans le cas d'un update de groupe.
     */
    public void showAllForUpdate() {
        GroupeDAO groupeDAO = new GroupeDAO();
        ArrayList<Groupe> listeGroupes = groupeDAO.findAll();

        System.out.println("Liste des groupes :");
        for (int i = 0; i < listeGroupes.size(); i++) {
            System.out.println("- " + listeGroupes.get(i).getId() + " : " + listeGroupes.get(i).getNom());
        }
    }

    /**
     * Permet d'update le groupe ou le groupe_id d'un personnage selon l'input de l'utilisateur, et de récupérer la
     * nouvelle valeur entrée par l'utilisateur pour update en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @param groupe [Groupe] Un objet de type Groupe.
     * @param idUpdate [int] Le choix de l'utilisateur.
     */
    public void updateGroupe(Scanner scan, Groupe groupe, int idUpdate) {
        GroupeDAO groupeDAO = new GroupeDAO();
        HerosMetier herosMetier = new HerosMetier();
        VilainMetier vilainMetier = new VilainMetier();

        switch (idUpdate) {
            case 1:
                String value1;
                System.out.println("Saisissez un nouveau nom pour le groupe " + groupe.getNom() + " :");
                value1 = scan.nextLine();
                groupeDAO.updateGroupe(value1, groupe.getId());
                break;
            case 2:
                int value2;
                herosMetier.showAllForUpdate();
                System.out.println("Saisissez l'id d'un héros à ajouter au groupe " + groupe.getNom() + " :");
                value2 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(groupe.getId(), value2);
                break;
            case 3:
                int value3;
                System.out.println("Saisissez l'id d'un héros à supprimer du groupe " + groupe.getNom() + " :");
                value3 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(0, value3);
                break;
            case 4:
                int value4;
                vilainMetier.showAllForUpdate();
                System.out.println("Saisissez l'id d'un vilain à ajouter au groupe " + groupe.getNom() + " :");
                value4 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(groupe.getId(), value4);
                break;
            case 5:
                int value5;
                System.out.println("Saisissez l'id d'un vilain à supprimer du groupe " + groupe.getNom() + " :");
                value5 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(0, value5);
                break;
            default:
                break;
        }
    }

    /**
     * Permet de créer un groupe en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @return [GroupeMetier] L'instance de l'objet.
     */
    public GroupeMetier creer(Scanner scan) {
        HerosMetier herosMetier = new HerosMetier();
        VilainMetier vilainMetier = new VilainMetier();

        System.out.println("Saisissez un nom pour le groupe :");
        String nom = scan.nextLine();

        herosMetier.showAllFree();
        System.out.println("Choisissez des héros à ajouter au groupe en écrivant leurs id. séparés par une virgule (ex. : 1,4,9) :");
        String listeHeros = scan.nextLine();

        vilainMetier.showAllFree();
        System.out.println("Choisissez des vilains à ajouter au groupe en écrivant leurs id. séparés par une virgule (ex. : 1,4,9) :");
        String listeVilains = scan.nextLine();

        String listeSuper = "";
        if ((listeHeros == null || listeHeros.isEmpty()) && (listeVilains != null && !listeVilains.isEmpty())) {
            listeSuper = listeVilains;
        } else if ((listeVilains == null || listeVilains.isEmpty()) && (listeHeros != null && !listeHeros.isEmpty())) {
            listeSuper = listeHeros;
        } else {
            listeSuper = listeHeros + "," + listeVilains;
        }

        GroupeDAO groupeDAO = new GroupeDAO();
        groupeDAO.creerGroupe(nom, listeSuper);
        return this;
    }

    public void combat(Groupe groupe) {

        // Création des listes de héros et vilains :
        ArrayList<Heros> listeHeros = groupe.getListeHeros();
        ArrayList<Vilain> listeVilains = groupe.getListeVilains();
        System.out.println("---- DEBUT DU COMBAT ----");
        System.out.println("");

        // Création de l'ordre de jeu des personnages :
        // On crée également 2 tableaux contenant les listes des id héros et des id vilains (pour le ciblage) :
        ArrayList<Integer> ordre = new ArrayList<Integer>();
        ArrayList<Integer> ordreNotMoving = new ArrayList<Integer>();
        ArrayList<Integer> idHeros = new ArrayList<Integer>();
        ArrayList<Integer> idVilains = new ArrayList<Integer>();

        Map<Integer, Heros> mapHeros = new HashMap<>();
        Map<Integer, Vilain> mapVilains = new HashMap<>();

        for (int i = 0; i < listeHeros.size(); i++) {
            ordre.add(listeHeros.get(i).getSuperPersonnageId());
            ordreNotMoving.add(listeHeros.get(i).getSuperPersonnageId());
            idHeros.add(listeHeros.get(i).getSuperPersonnageId());
            mapHeros.put(listeHeros.get(i).getSuperPersonnageId(), listeHeros.get(i));
        }
        for (int i = 0; i < listeVilains.size(); i++) {
            ordre.add(listeVilains.get(i).getSuperPersonnageId());
            ordreNotMoving.add(listeVilains.get(i).getSuperPersonnageId());
            idVilains.add(listeVilains.get(i).getSuperPersonnageId());
            mapVilains.put(listeVilains.get(i).getSuperPersonnageId(), listeVilains.get(i));
        }

        // Randomize du tableau contenant l'ordre de jeu des personnages :
        Collections.shuffle(ordre);
        boolean looping = true;
        for (int i = 0; looping != false ; i++) {
            // Bouclage infini :
            if (i >= ordre.size()) {
                i = 0;
            }
            // On récupère l'id du personnage dont c'est le tour :
            int id = ordre.get(i);

            // On vérifie s'il s'agit d'un héros ou d'un vilain :
            Heros heros = mapHeros.get(id);
            Vilain vilain = mapVilains.get(id);

            /* _________________________________________________________________________
             *
             *                             HEROS VS VILAIN
             * _________________________________________________________________________
             */
            if (heros != null && vilain == null) {
                // Choix de la cible :
                Collections.shuffle(idVilains);
                int idCible = idVilains.get(0);
                Vilain vilainCible = mapVilains.get(idCible);

                // Attaque de la cible (1 chance sur 3 de faire un CC) :
                int coupCritiqueRand = ThreadLocalRandom.current().nextInt(1, 3 + 1);

                // Si on est sur un coup critique :
                if (coupCritiqueRand == 3) {
                    int totalDegats = heros.getDegats() + heros.getDegatsPouvoir();
                    System.out.println("SUPER-POUVOIR !");
                    System.out.println("Le héros [" + heros.getNom() + "] exécute son super-pouvoir [" + heros.getPouvoir() + "] face au vilain [" + vilainCible.getNom() + "] !");
                    System.out.println("Le vilain [" + vilainCible.getNom() + "] perd [" + heros.getDegats() +"] + [" + heros.getDegatsPouvoir() + "] point de vie grâce au super-pouvoir du héros.");
                    /* Si le pouvoir du héros est du même élément que la faiblesse du vilain, le vilain perd :
                     * - Les dégats de bâse
                     * - Les dégats du pouvoir du héros
                     * - Les dégats de faiblesse du vilain
                     */
                    if (heros.getElement().getId() == vilainCible.getElement().getId()) {
                        totalDegats += vilainCible.getDegatsFaiblesse();
                        System.out.println("De plus, il est particulièrement sensible à l'élément qu'utilise le super-héros, l'élément [" + heros.getElement().getNom() + "], et il perd encore [" + vilainCible.getDegatsFaiblesse() + "] points de vie !");
                    }
                    vilainCible.setPdv(vilainCible.getPdv() - totalDegats);
                }
                // Sinon, si c'est un coup normal :
                else {
                    System.out.println("Le héros [" + heros.getNom() + "] attaque le vilain [" + vilainCible.getNom() + "] et lui inflige [" + heros.getDegats() + "] points de dégâts.");
                    // Calcul des PDV restants :
                    vilainCible.setPdv(vilainCible.getPdv() - heros.getDegats());
                }

                // Si le héros ciblé a toujours des points de vie, on les affiche et on continue :
                if (vilainCible.getPdv() > 0) {
                    System.out.println("Il reste [" + vilainCible.getPdv() + "] points de vie au vilain [" + vilainCible.getNom() + "].");
                    System.out.println("");
                }
                // Sinon, on le déclare mort et on le retire de l'ordre de jeu et des arrays d'ID des personnages en vie.
                else {
                    System.out.println("Le vilain [" + vilainCible.getNom() + "] n'a plus de points de vie, il est mort !");
                    System.out.println("");
                    ordre.remove(new Integer(idCible));
                    idVilains.remove(new Integer(idCible));
                }
            }
            /* _________________________________________________________________________
             *
             *                             VILAIN VS HEROS
             * _________________________________________________________________________
             */
            else if (vilain != null && heros == null) {
                // Choix de la cible :
                Collections.shuffle(idHeros);
                int idCible = idHeros.get(0);
                Heros herosCible = mapHeros.get(idCible);
                System.out.println("Le vilain [" + vilain.getNom() + "] attaque le héros [" + herosCible.getNom() +"] et lui inflige [" + vilain.getDegats() + "] points de dégâts.");
                herosCible.setPdv(herosCible.getPdv() - vilain.getDegats());

                // Si le héros ciblé a toujours des points de vie, on les affiche et on continue :
                if (herosCible.getPdv() > 0) {
                    System.out.println("Il reste [" + herosCible.getPdv() + "] points de vie au héros [" + herosCible.getNom() + "].");
                    System.out.println("");
                }
                // Sinon, on le déclare mort et on le retire de l'ordre de jeu et des arrays d'ID des personnages en vie.
                else {
                    System.out.println("Le héros [" + herosCible.getNom() + "] n'a plus de points de vie, il est mort !");
                    System.out.println("");
                    ordre.remove(new Integer(idCible));
                    idHeros.remove(new Integer(idCible));
                }
            } else {
                // Jamais censé rentrer dans ce bloc.
                System.out.println("Ni un héros, ni un vilain.");
            }

            // Sortie de boucle :
            if (idHeros.isEmpty() || idVilains.isEmpty()) {
                looping = false;

                GroupeDAO groupeDAO = new GroupeDAO();

                // Suppression du groupe et update des personnages du groupe pour passer leur groupe_id à 0 :
                int idGroupe = groupe.getId();
                groupeDAO.deleteGroupe(idGroupe);

                for (int compteur = 0; compteur < groupe.getListeHeros().size(); compteur++) {
                    int idSuperPersonnage = groupe.getListeHeros().get(compteur).getSuperPersonnageId();
                    groupeDAO.updatePersonnage(0, idSuperPersonnage);
                }

                for (int compteur = 0; compteur < groupe.getListeVilains().size(); compteur++) {
                    int idSuperPersonnage = groupe.getListeVilains().get(compteur).getSuperPersonnageId();
                    groupeDAO.updatePersonnage(0, idSuperPersonnage);
                }

                if (idHeros.isEmpty() && !idVilains.isEmpty()) {
                    System.out.println("---- FIN DU COMBAT ----");
                    System.out.println("Les vilains l'emportent !");
                    System.out.println("");
                } else if (!idHeros.isEmpty() && idVilains.isEmpty()) {
                    System.out.println("---- FIN DU COMBAT ----");
                    System.out.println("Les héros l'emportent !");
                    System.out.println("");
                }
            }
        }
    }
}

