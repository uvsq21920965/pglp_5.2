package fr.uvsq21920965.pglp51;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  Classe Personnels.
 * @author sarra Belmahdi.
 *
 */
public final class Personnels implements Ipersonnels, Serializable {
  /**
   * nom du personnels.
   */
  private  String nom;

  /**
   * prenom de personnels.
   */
  private  String prenom;

  /**
   * fonctions de personnels.
   */
  private  String fonctions;

  /**
   *date de naissance de personnels.
   */
  private  LocalDate dateDeNaiss;
  
  /**
   * numero de telephone.
   */
  private  List<Integer> numeroDeTel;

  /**
   * Builder Class.
   * @author sarra belmahdi.
   *
   */
  public static class Builder {

    /**
     * nom de personnel.
     */
    private  String nom;

    /**
     * prenom de personnel.
     */
    private  String prenom;

    /**
     * fonction de personnel.
     */
    private  String fonctions;

    /**
     * date de naissance de personnel.
     */
    private  LocalDate dateDeNaiss = null;

    /**
     * numero de telephone de personnel.
    */
    private  List<Integer> numeroDeTel = new ArrayList<Integer>();

    /**
     * initialiser les attributs.
     * @param nom nom du personnel.
     * @param prenom prenom dupersonnel.
     * @param fonctions fonction de personnel.
     */
    public Builder(String nom, String prenom, String fonctions) {
      super();
      this.nom = nom;
      this.prenom = prenom;
      this.fonctions = fonctions;
    }

    /**
     * intialiser date de naissance.
     * @param dateDeNaissAtt .
     * @return date de naissance initialise.
     */
    public Builder dateNaissance(LocalDate dateDeNaissAtt) {
      this.dateDeNaiss = dateDeNaissAtt;
      return (this);
    }

    /**
     * initialiser laliste de numero de telephone.
     * @param numeroDeTelAtt .
     * @return la  liste du numero initialisee.
     */
    public Builder numeroDeTel(List<Integer> numeroDeTelAtt) {
      this.numeroDeTel = numeroDeTelAtt;
      return (this);
    }

    /**
     * methode pour creer l'objet personnel.
     * @returnnew objet personnel.
     */
    public Personnels build() {
      return new Personnels(this);
    }
  }

  /**
   * constructeur.
   * @param build pour initialser les valeurs du constructeur.
   */
  private Personnels(Builder build) {
    nom = build.nom;
    prenom = build.prenom;
    fonctions = build.fonctions;
    dateDeNaiss = build.dateDeNaiss;
    numeroDeTel = build.numeroDeTel;
  }

  /**
   * methode pour retourner nom.
   * @return nom.
   */
  public String getNom() {
    return nom;
  }

  /**
   * mehode pour retourner prenom.
   * @return prenom.
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * methode pour retourner fonction.
   * @return fonction.
   */
  public String getFonctions() {
    return fonctions;
  }

  /**
   * methode pour retourner la date de naissance.
   * @return date de naissance.
   */
  public LocalDate getDateDeNaiss() {
    return dateDeNaiss;
  }

  /**
   * methode pour retourner le numero de telephone.
   * @return nemero de telephone.
   */
  public List<Integer> getNumeroDeTel() {
    return numeroDeTel;
  }

  /**
   * methode print les cordonnees du personnel.
   */
  public String print() {
    StringBuffer numtel = new StringBuffer();
    for (int num:this.numeroDeTel) {
      numtel.append(num);
    }
    return "nom : " + this.nom + " " + "prenom : " + this.prenom + " " + "fonctions:"
      + this.fonctions + " " + "dateDeNaiss :" + this.dateDeNaiss + " " + "numero de telephone"
      + numtel;
  }
}
